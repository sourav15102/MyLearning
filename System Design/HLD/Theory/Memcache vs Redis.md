Memcache:
- Basic key value store
- distributed
- can have variable number of partitions in each node.
- uses consistent hashing to decide which partition to go which node.
- LRU eviction policy.
- multithreading

Redis:
- persistent (periodically dump data to disk) (can be configured as 'we dont need persistence')
- Support data structures
- fixed number of partitions
- if a node goes down, uses gossip protocol to decide which partition of down node will go where,
- ACID transactions
	- write-ahead log: we write to write-ahead log first and if node goes down, we can still write.
	- single threaded.
- Single leader replication
- Can be used as pub/sub, with topics n all.
- LRU eviction

https://www.youtube.com/watch?v=5TRFpFBccQM
Q: why redis is faster even with single thread?
A: multithreading needs locks, add complexity, this redis can be fast.
Q: But, wont single thread be blocked for others when it is handleing one request.
A: I/O multiplexing(apparent concurrency), (https://youtu.be/h30k7YixrMo?t=642)

Conclusion:
Both:
- are distributed and can be scaled independently.

Redis: managed, helps single developer, manages a lot on it own.
Memcache: allows flexibility cos we need to implement a lot of logic ourself, having developers help here.