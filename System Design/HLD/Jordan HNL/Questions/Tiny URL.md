https://www.youtube.com/watch?v=5V6Lam8GZo4&list=PLjTveVh7FakJOoY6GPZGWHHl4shhDT8iV&index=1
Steps:
1. We cannot do monotonically increasing numbers for urls, cos, that would require the locking on that number, hence making the 'assigning of tiny url' process slow.
2. We can assume 0-9 and a-z characters and 8 size hash value, giving us 2 trillion slots available, assuming we will have 1 trillion urls, 2 should be enough.
3. But what happens in case of clashing, we can look for next hash, for example if it 'X' hash, we will start looking for 'X+1' and so on, until we get the empty place.
4. Optimize: writes, reads, analytics, expired urls
5. Now to decide database:
	1. Replication
		1. Multi leader replication would not work, two people can see that X hash is empty and submit for X at different leaders and that would be clash. 
			1. Couchbase Server, CouchDB
		2. In leaderless relocation conflict resolution can be tricky and writes would take much longer.
			1. Cassandra, Raik
		3. Single leader replication is the way to go. (CHOSEN)
			1. MySQL, MongoDB
	2. Indexing
		1. LSM + SS Tables: good for writes as it uses buffer but not for reads as it might need to use multiple tables for answer.
			1. Apache Cassandra, HBase (It uses LSM+SS but is based on HDFS, which generally writes directly on disk)
		2. B Tree: Good for faster reads, for not for writes. (CHOSEN)
			1. mySQL, postreSQL
	3. Data type
		1. Data is predicted and fixed schema
	4. Decision: with single leader replication, B tree indexing and fixed schema, we can use MySQL.
6. Write optimize:
	1. Sharding: Hash range  and key range
		1. Key range is good for range based queries and since we need to look for X+1 when X is takebn, we can go for key based sharding.
		2. Consistent hashing, can be used here.
7. Read optimize:
	1. Caching: Write back: can cause write conflicts, as it copies data to db async manner, we might think X is available when it is not.
		1. Write through: not write conflicts but take more to write,
		2. write around: directly write to db and read from cache, (CHOSEN) with LRU eviction. REDIS can be used here.
		3. Cache can be distributed by key range as well
8. Analytics:
	1. So basically our mysql has column for clicks against a URL, f multiple users are trying to update it, it ca cause race conditions.
	2. Solution: streaming, we can use messagr brokers, submit our clicks to messqag broker, but these are in memory and hence no fault tolerant. So, we can use log based message broker, which use write ahead logs. 
	3. We can use Kafka message queues distributed by key range.
	4. If we need analytics not too **frequently**: we can have message broker consumer to dump everything in a file/S3, and run batch job to add all clicks up.
	5. If we need analytics frequently: We can use Spark streaming as consumer to message broker, where we can use mini-batch size of lets say 100, collect 100 clicks and add them up.
9. Expire URLs
	1. Simple batch job would work here with the lock on row currently being processed, as someone might try to write on it while it being deleted.



Questions: 
1. For spark streaming is int only one consumer and one message broker and can be bottleneck, and even if we add more, cant one click go to both message brokers and then their consumers can add them up twice.
2. What if  DB fails to ACK  to spark streaming (the consumer) that we have added 100 clicks, it consumer would try to add that 100 rows once more