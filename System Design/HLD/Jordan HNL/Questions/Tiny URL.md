https://www.youtube.com/watch?v=5V6Lam8GZo4&list=PLjTveVh7FakJOoY6GPZGWHHl4shhDT8iV&index=1

and 

https://youtu.be/YN7hCvBOFxk?t=1545

Steps:
1. We cannot do monotonically increasing numbers for urls, cos, that would require the locking on that number, hence making the 'assigning of tiny url' process slow.
2. We can assume 0-9 and a-z characters and 8 size hash value, giving us 2 trillion slots available, assuming we will have 1 trillion urls, 2 should be enough.
3. But what happens in case of clashing, we can look for next hash, for example if it 'X' hash, we will start looking for 'X+1' and so on, until we get the empty place. This is called Probing.
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


# Algorithm REST Endpoints

Let’s starts by making two functions accessible through REST API:

> **create(** long_url, api_key, custom_url**)**
> 
> POST  
> Tinyrl : POST : [https://tinyurl.com/app/api/create](https://tinyurl.com/app/api/create)  
> Request Body: {url=long_url}  
> Return OK (200), with the generated short_url in data

long_url: A long URL that needs to be shortened.

api_key: A unique API key provided to each user, to protect from the spammers, access, and resource control for the user, etc.

custom_url_(optional)_: The custom short link URL, user want to use.

**Return Value:** The short Url generated, or error code in case of the inappropriate parameter.

> GET: /{short_url}  
> Return a http redirect response(302)

**Note** : “HTTP 302 Redirect” status is sent back to the browser instead of “HTTP 301 Redirect”. A **301** redirect means that the page has permanently moved to a new location. A **302** redirect means that the move is only temporary. Thus, returning 302 redirect will ensure all requests for redirection reaches to our backend and we can perform analytics (Which is a functional requirement)

short_url: The short URL generated from the above function.

**Return Value:** The original long URL, or invalid URL error code.


Capacity estimation:
https://medium.com/@sandeep4.verma/system-design-scalable-url-shortener-service-like-tinyurl-106f30f23a82
Traffic
Storage
Memory

If we need fix length:
1. Custom hash function: like 6 characters, and a-z, 0-9 (36), might have clashes, need to handle those (Cant have multi master replication model).
2. multiple servers will reach out to counter service, ask it for transaction and get 1-100 counters from it (it means 1-100 from a-z,A-Z,0-9 6 character pool).
3. Key generation service.
4. Zookeeper to maintain counter (same as Pt. 2)

No length contraint:
1. MD5 = 128 bit, then base64 encoding (each character 6 bits so 21 characters).