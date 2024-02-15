1. Functional Requirements
2. Non-Functional Requirements
	1. Scalable
	2. Highly Available
	3. Fault tolerant
	4. Consistent (required or not, it depends)
	5. Minimum read/write latency
3. APIs
4. [[Capacity Planning]]
	5. Traffic Estimates
	6. Storage Estimates
	7. Memory Estimates
	8. Latency Bandwidth Estimates
	9. CAP tradeoff
5. Main Design: 
	1. make distributed
6. Data model & Data Store
	1. type of data and db
	2. make distributed
	3. Cache (IR): 
		1. Cache invalidation (eg cache aside)
		2. cache eviction (eg LRU)
7. Focus on few components for detailed design
8. Identify bottlenecks: single point of failure, few replicas
9. (Security)
10. (DB Purge or DB clean)


IR: If required