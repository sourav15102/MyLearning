
https://youtu.be/4wIU4rTV-JU?list=PLjTveVh7FakJOoY6GPZGWHHl4shhDT8iV

Requirements:
1. Users can create and upload files
2. Files can be shared across multiple different users
3. Changes to files should be propagated to all devices with access
4. We should be able to see older versions of files

## Services:
#### Adding permissions to files:
```
It is like followers and following relationship, bunch of people can follow a file and files can have followers.
```
#### Uploading files:
```
Client can upload the file to the a object storage like S3 bucket.
We can break the file into chunks.
In db we can have fileID, chunkID, hash of content, version.
We could have leaderless or multi leader replication model however, two different people can modify same chunk and submit conflicting changes.
Better to have MySQL, single leader and have lock on chunk one is working on
> We cant have Cassandra, cos, it uses last write wins to resolve conflicts,. on anti-entropy
> We cant have Raik cos, CRDT wouldn't know how to resolve the Conflicting images/docs. 

OR

We can have leaderless replication, but maintain all versions, and leave it to the user to resolve it.
```
#### Propagating changes:
```
We can use the pattern of CDC (Change data capture), into Kafak and then into Flink. Now flink can push update for that chunk in another Kafak topic (for that chunk), all the servers which have subscribed to the topic (cos they have connection with users whos has that file opened) 

OR FLink can send it manually to all subscribers of the file (only when file has relatively less number of users.)
```
