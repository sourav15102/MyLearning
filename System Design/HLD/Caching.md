While reading caching works good, as server can cache requests and save db interactions, however, when data we are dealing with is mutable, then writing data can be tricky with caching, as while writing if we just update the cache there will be inconsistency, or if we just update to db, there will be inconsistency as well. 

so, there are two types of caching:
1. write-through cache: writes to
2. write-back cache

- If we care about the staleness of data, we should have a single source of truth and have one redis like cache for all servers.
- If we dont, we can have in-memory cache within each server

Eviction policy:
1. LRU
2. LFU
3. FIFO
4. LIFO

