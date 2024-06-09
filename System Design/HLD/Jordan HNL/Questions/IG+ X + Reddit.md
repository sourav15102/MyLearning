https://youtu.be/S2y9_XYOZsg?list=PLjTveVh7FakJOoY6GPZGWHHl4shhDT8iV

### Requirements:
1. Users can ***quicky see*** followers and following.
2. Quickly load all posts.
3. Can comment on posts, nested comments
4. User can load news feed, with low latency
5. Adjust the privacy setting

### Capacity estimation:
1. Traffic:
	1. 100 Million posts per month.
	2. 500:1 read to write ratio.
2. Storage:
	1. 100M X 200 char per post
	2. Calculate it for 100 years
3. Memory:

### Featuere
1. Followers/Following:
Thoughts:
1.
If we have a following table, then either we can sort it by user id or followers, but if we lets say sort it by user id, i.e., have an index on user id, it would be easy to calc followers for a user but tough to calc following for a user.

| User ID | Followers |
| ------- | --------- |
|         |           |
2.
Maybe we can have two separate tables, SQL or NoSQL, where we can keep followers and following separate. This would work but we would need to commit a transaction, 2Phase commit, which would be slower, since we would have a lot of follower-following requests coming in.

3.
In such cases where we might need to use 2P commit, but we need it quicker, we can try different approach.
Basically even with 2P commit, what we need is when we add someone as a follower, it should not go wasted and should be processed for following table as well.

![[Pasted image 20240607180209.png]]

Why? Here one can say that the transaction issue still persists, what if we submit to first db but to second db we fail? Actually flink has mechanism where it retries submitting data to sink.

> Flink cant directly add to db, we have a sink connector added to flink and trhat sink connector actually writes to db.

so why do we even need flink?
Actually, what is happening here is once we send CDC to kafka, it just gives us the snapshot of changes (which in kafka are indexed by follower id). we need to process those snapshots so that they can be sent to sink and then sent to db.

Now what actual DB to use?
1. Earlier when we though of using transactions we though of using MongoDB since it uses Btree indexing, single leader replication, and supports transactions.
2. But, now since we dont need transactions, we can use CouchDB, it has multi leader replication, (faster writes), B tree indexing (faster reads), but do we need to worry about conflicts?  
3. We could use Cassandra, faster writes, but do we need to worry about conflicts? since it uses Last write wins.

Conflicts ([[Cassandra]]): Possible solution
- First of all we are using Cassandra here which is a wide-column db, meaning data will stored like following table
- and conflict occur when there is data modified for the same row.
- So if Node 1 adds D for X and node 2 adds E for X, there will be NO conflicts, The data in question involves a user gaining new followers. This is an append-only operation where each new follower is a distinct entity that doesn't overwrite existing followers. (See Jordan's video on how vector clocks works). Each entry in table has a vector. when two nodes modify that vector in conflicting manner (without knowing about each other), there is our conflicting situation. Which is not going to happen in this case.

| User ID | Follower |
| ------- | -------- |
| X       | A        |
| X       | B        |
| X       | C        |
Conflicts ([[Raik]]): Possible solution
- Raik is key value store so, it would look like:
- ```
```json
{
  "userX": ["A", "B", "C"]
}
```
- also Raik uses CRDT, so they both can be merged.

Conflicts ([[CouchDB]]):
```json
{
  "_id": "userX",
  "_rev": "1-abcdef123456",
  "followers": ["A", "B", "C"]
}
```
There would be conflicts in CouchDB as it uses documents, however they use some in-house algo to resolve conflicts, not a good choice.


Posts:
Cassandra can be used here as well, because faster writes.

News Feed:
Naive:
1. Basically query the Query the following Db to get the following
2. Query post DB To get all the posts aggregate them sort them by time.
3. This is too slow.

Optimal:
1. We put change data capture or cdc on the postdb.
2. We send the data to the Kafka and Kafka's consumer is the flink, After receiving a post from a particular user id the link will make a network call to the user followers DB and will get all the followers for that user.
3. Then Flink will basically store the copy of that particular tweet in all of users followers cache.

Exception: Some of the users will have Million of followers.

 Hybrid Approach 1:
 - Basically the new fee service while calculating the news feed for a particular user id will go through its following list and for the verified followers it will directly reach out to the postdb and get the post from there and for the unverified followers it will go to the new feed cache.

 Hybrid Approach 2:
 - Some of the users have millions of followers so copying it with millions time will be very slow
 - It's a simple modification on the  previous hybrid approach where we can put a CDC on the posts db and if the user is verified we can cache its posts.
 - So now the newsfeed service, While getting the newsfeed for a particular user it will go to the popular cache for all the verified user the user is following and for the rest of its followings  it will go to the newsfeed cache.

> - For the second hybrid approach for the Third Point there is a catch, The task is that the user will have to calculate which of its followings are verified and which of it followings are not
> - For this it will have to go to the user following stable and then for each following it will have to get to the users table to cheque if they are verified or not, Which is very slow
> - Now we can have a Flink which will accept the data from two CDC's, From user stable and from user-following stable, Every time a user get verified and also every time a user follow someone, The flink can aggregate the data and it can calculate for each user, Which of it followings are verified.


> NOTE: Flink can help us pay aggregate or pre join the data from two different dbs


Nested Comments:
- First thought process here is that we can have a separate database where against the post id we can save its comments.
- Now there are chances of conflicts here in multiple Leader setup because let's say user a writes its comment to the leader 1 and User B reads from it and writes reply to leader 2, wont make sense..
- We can use Mongodb here which is a document-db where against a post id we can have a document which will save comments and each comment can have comments, It is a single leader replication model database so no chances of write conflicts.

OR

-  Alternative is that we can use native craft databases where we can have a note basically a pointer and that can points to a children note and then to its grandchildren notes and so on.
- These databases tends to be a bit slower because the disc has to jump from 1 pointer to the another in a mechanical way.

OR

DFS Index (Kinda like geohash):
![[Pasted image 20240608223508.png]]

Full Design:
![[Pasted image 20240608224245.png]]