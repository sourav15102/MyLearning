https://www.youtube.com/watch?v=wXvljefXyEo

Partitioning happens at a data level. Sharding happens at a database level.

Ex:
- We had 1000 GB data, we partitioned it into 5, 200GB data..
	- All the partitions can stay at one DB server as well. Doesn't necessarily have to be diff server.
	- Provides us the isolation at data level.
- Where as when we make them diff DB servers, those diff DB servers are called Shards.

Lets we had 5 partition: A B C D E
and 2 Shards P Q
P might have partitions: A B C
Q = D E
