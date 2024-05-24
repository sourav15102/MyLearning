##### 2 Phase commit:
We have a coordinator node and service nodes.
1. Coordinator node first sends prepare broadcast message to all nodes.
2. If it gets OK from all, it sends message to them to go ahead and commit changes.
3. if it doesnt get OK from all, it sends abort.
Cons:
1. If one of the nodes go down, it will keep trying indefinitely.
2. If coordinator goes down, it is an major issue.


##### RAFT:
https://www.youtube.com/watch?v=IujMVjKvWP4

Lets say in single leader setting, the leader sends heartbeats to all followers, and when leader is down, and followers dont receive heartbeats for 'x' seconds (it can be different for different followers), they become candidates and participate in an election. 

> If all followers had same value of 'x', they would become candidates and participate in election at the same time, and vote for themselves, having no leader at the end. Thats why we have different values of 'x' for different followers.

Now a followers votes for a candidate to be a new leader on two conditions:
1. Its term number is greater than the previous term number.
2. Its log length is greater than followers own log length, which would mean candidate has more updated data.

Once a candidate has majority of votes, it will be chosen as new leader.

Once leader is chosen, how broadcasting works? (Watching video attached above is lot more helpful here)
So, in log of each followers we have entries, and each entry has an index and term number, when leader send new log entry to all followers, it send previous term number along with it as well.

Lets say a follower has
entry 1: index 1, term number 1
entry 2: index 2, term number 1
entry 3: index 3, term number 1

Now when leader sends entry 4, it sends index 3 and term number 1 too, and if previous term number it sends (in this case 3) matches with the index and term number log already has (in this case it matches) only then new entry will be added.

If lets say for some time this follower was down, and it couldn't receive 4,5,6 and now when leader sends entry 7, index 7 and term 1, it will send previous index 6, it wont match with the 3, so for this follower leader will decrement the positioning of log entries, now it will try sending entry 6, with previous index 5, and so on until it start sending entry 4.

> Leader has positioning for each follower, as to what latest entries each follower has.