While reading caching works good, as server can cache requests and save db interactions, however, when data we are dealing with is mutable, then writing data can be tricky with caching, as while writing if we just update the cache there will be inconsistency, or if we just update to db, there will be inconsistency as well. 

Caching can be at many levels in a fully designed architecture,  CDN, Load balancer, redis, memcache, but can be categorized into two kinds:
1. client-side
2. server-side

Eviction policy:
1. LRU
2. LFU
3. FIFO
4. LIFO

```text
There are multiple levels you can cache that fall into two general categories: **database queries** and **objects**:
- Row level
- Query-level
- Fully-formed serializable objects
- Fully-rendered HTML
Generally, you should try to avoid file-based caching, as it makes cloning and auto-scaling more difficult.
### Caching at the database query level
Whenever you query the database, hash the query as a key and store the result to the cache. This approach suffers from expiration issues:
- Hard to delete a cached result with complex queries
- If one piece of data changes such as a table cell, you need to delete all cached queries that might include the changed cell
### Caching at the object level
See your data as an object, similar to what you do with your application code. Have your application assemble the dataset from the database into a class instance or a data structure(s):
- Remove the object from cache if its underlying data has changed
- Allows for asynchronous processing: workers assemble objects by consuming the latest cached object

reference: https://github.com/donnemartin/system-design-primer#cache
```

Caching patterns: https://newsletter.systemdesigncodex.com/p/database-caching-strategies
1. Cache aside: application interacts with cache, cache doesnt interact with db
	1. Under normal cases, the application checks value in cache, if hit then fine, but if miss, it fetches value from db, populates cache, and return.
	2. load data **lazily**, that is, only when it is first read.
	3. Problem:
		1. data can be stale, if value changes in db, cache can still serve old value. To mitigate this, we can either use 
			1. TTL
			2. whenever value changes in db it invalidates cache. (no useless data in cache)
			3. immediately updates in cache(like write through), useless data in cache.
	4. Adv:
		1. even if cache goes down, app will work
		2. data modelling between db and cache can be diff
2. Read through: cache fetches data in case of miss.
	1. If there is cache hit then fine, but if miss, cache is responsible for fetching data from db and updating itself and returning to app.
	2. load data **lazily**, that is, only when it is first read.
	3. writing to cache: can use multiple patterns 1) write through 2) write back(behind) 3) write around.
	4. Adv:
		1. simple to implement.
		2. if cache fails, can replace it with empty node and works fine, will be slow start as more cache miss will happen.
3. write through: Cache sits between app and db; app talks to cache, cache talks to db.
	1. App will read/write to cache, it is cache responsibility to write to db synchronously.
	2. for reading it uses read-through.
	3. Adv:
		1. Good for read heavy load.
		2. Data is not stale.
		3. data might not be read after writing, so useless data is in cache.
	4. Problem:
		1. if cache goes down app goes down; new node due to failure or scaling, the new node will not cache entries until the entry is updated in the database. Cache-aside in conjunction with write through can mitigate this issue.
		2. cold start, can be mitigated by pre-heating cache, by predicting what data request is going to be.
		3. slow overall operation due to the write operation.
		4. Useless data can be in cache (cache pollution)
4. Write behind (write back)
	1. Since write through had bad write performance, it is similar to write through, we read from cache, write to cache, but cache writes to db async.
	2. problems:
		1. complex to implement.
		2. if cache goes down, data can loss.
		3. Useless data can be in cache (cache pollution)
5. Refresh Ahead:
	1. issue here is with cache miss, we need to go to db and invalidate/update cache, now it introduces latency.
	2. We can refresh cache asynchronously before data expires.
6. (READ MORE) write around: app will talk to both;
	1. app will write to db, read from cache.
	2. if miss, cache will fetch from db.
	3. after writing, cache should be invalidated.
	4. Adv:
		1. good for write heavy load.

[[Memcache vs Redis]]