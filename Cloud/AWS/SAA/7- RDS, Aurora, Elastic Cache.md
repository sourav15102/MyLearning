
### RDS:
- Managed service.
- Cant SSH into service.
- Storage is backed by EBS, (gp2, and io1)

RDS Storage Auto Scaling:
- initially we need to setup storage for RDS.
- RDS can monitor and if we are running out of free storage, it will scale dynamically.
- We need to setup up max limit for storage scaling.
- Automatically modify storage if:
	- Free storage is less than 10% of allocated storage
	- Low-storage lasts at least 5 minutes
	- 6 hours have passed since last modification
- Useful for applications with unpredictable workloads
- Supports all RDS database engines (MariaDB, MySQL, PostgreSQL, SQL Server, Oracle)


RDS replicas:
- 15 read replicas
- same AZ, cross AZ, cross Region.
- ASYNC -- eventually consistent.
- replica can be promoted to own db
- application using these db has to update connection string in order to use it.

Normally: data travel cross AZ costs money. HOWEVER:
there are some exceptions: Usually for managed services.
RDS Read replicas:
1. Same Region, Diff AZ: dont pay replication fee
2. Cross Region: Pay replication fee


RDS Multi-AZ
- used for disaster recovery
- there is a standby instance in different AZ
- standby instance gets SYNC replication.
- we get one DNS name out of it
- automatic failover happens in case of an issue.
- no manual intervention needed.
- standby cant do anything.

> Read replicas can be setup as Multi AZ for disaster recovery.

Process of single AZ to multi AZ:
1. just clicking `modify` will do the job.
2. no need to stop the DB.
3. 0  downtime
Internally:
1. snapshot is taken
2. new db is restored in new AZ
3. SYNC replication is enabled

RDS Custom: 
- With RDS we dont have access to underlying OS or any sort of DB customization.
- BUT with custom we do.
- It is for Managed Oracle and Microsoft SQL Server Databases with OS and Customization:
- RDS: automates setup, operation, and scaling of databases on AWS.
- RDS: Custom provides access to both the OS and the database for:
  - Configuration adjustments
  - Installing patches
  - Activating native features
- Access the underlying EC2 Instance via SSH or SSM Session Manager.

Tips:
> 1. Deactivate Automation Mode before customization
> 2. take a DB snapshot first.

RDS Backups:
Automated Backups:
- Full daily database backup during backup window.
- RDS backs up transaction logs every 5 minutes.
- Enables restoration to any point within the last 5 minutes.
- Retention of backups for 1 to 35 days;
- set 0 to disable it.
Manual DB Snapshots:
- User-triggered snapshots.
- Backups retained based on user preference.

> Note: To save costs, lets say you only use db for 2 hours then instead of stopping (cos you still pay for stopped instance) Storage costs continue for stopped RDS databases; consider snapshot & restore for extended downtime.


>Oracle doesnt support IAM authentication


### Aurora: Proprietary AWS Database Technology
- Not open sourced, exclusive to AWS.
- Supports both Postgres and MySQL (compatible drivers).
- "AWS cloud optimized" with significant performance gains.
- RDS MySQL: 5X faster and RDS Postgres 3X faster.
- Storage automatically scales in 10GB --> up to 128 TB.
- Supports up to 15 replicas with fast replication (sub 10 ms replica lag), ASYNC
	- all replicas has shared storage volume (10gb - 128tb)
- Instantaneous failover, HA native, cloud native
- Higher cost compared to RDS (20% more), but more efficient.

Aurora Data Redundancy and Resilience, high availability
- it maintains 6 data copies across 3 Availability Zones (AZ).
- 4 of 6 copies should be up for writes, 3 of 6 for reads.
- Self-healing via peer-to-peer replication.
- Storage striped across numerous volumes.
- One Aurora Instance is the write master.
- Automated master failover within 30 seconds.
- Master and(+) up to 15 Read Replicas for reads.
- Cross-Region Replication supported.

Aurora DB cluster:
- we have shared storage volume.
- only master can write
- BUT master can change (failover).
- we have writer endpoint: always connect to current master.
- for read replicas, they can auto scale.
- they have reader endpoint (load balancer)
	- connects automatically yo all read replicas
	- happens at connection level not statement level.
	
> backtrack, restore at any point without taking backups.

Advanced concepts:
- we can have subsets of aurora replicas to be diff i.e., db.r3.large or db.r3/2xlarge
- we can have custom endpoints for such replicas.
- geenral flow: many custom ndpoints are made.

Aurora Serverless:
uses:
- Ideal for infrequent, intermittent, or unpredictable workloads.
- Cost-efficient payment on a per-second basis.
- clients talk to Proxy fleet.
	- many instances are created in the backend.

 Aurora Multi master:
 Different from normal where we have diff read and writer nodes.
 To ensure continuous write availability for writer nodes:
- All nodes perform both read and write operations.
- This is in contrast to promoting a Read Replica to become the new master for write operations.


 Global Aurora:
 - cross region read replicas for DR
 - Recommended way:
	 - 1 Primary region: read/write
	 - 5 secondary region: read
		 - 16 replicas / secondary region
- RTO < 1min
- cross region replication < 1 Sec


Aurora ML:
Allows integration of ML-based predictions into applications VIA SQL:
- Simple, optimized, and secure integration with AWS ML services.
- Supported services: Amazon SageMaker (for any ML model) and Amazon Comprehend (for sentiment analysis).
- No requirement for ML expertise.
- Useful for various applications like fraud detection, ads targeting, sentiment analysis, and product recommendations.

Aurora Backups:
Automated Backups:
- Retained for 1 to 35 days (cannot be disabled), In RDS we can disable
- Allows point-in-time recovery within that duration.
Manual DB Snapshots:
- User-initiated snapshots.
- Retention duration based on user preference.

Restoration Process for RDS/Aurora Backups or Snapshots: Creating a new database instance.
Steps for MySQL RDS using S3:
  - Backup on-premises database.
  - Store on Amazon S3.
  - Restore backup onto new RDS instance.
Steps for MySQL Aurora using S3:
  - Backup using Percona XtraBackup.
  - Store backup on Amazon S3.
  - Restore backup onto new Aurora cluster.

Aurora Db cloning:
Creating a New Aurora DB Cluster from an Existing One:
- Faster than snapshot & restore.
	- How?
	- Utilizes copy-on-write protocol.
	- Initial new cluster shares the same data volume as the original (no copying).
	- Additional storage and data separation occurs as updates are made.
- Fast, efficient, and cost-effective.
- Useful for staging databases without affecting production.

RDS & Aurora Security:
At-rest encryption:
• Database master & replicas encryption using AWS KMS – must be defined as launch time
• If the master is not encrypted, the read replicas cannot be encrypted
• To encrypt an un-encrypted database, go through a DB snapshot & restore as encrypted
In-flight encryption: TLS-ready by default, BUT use the AWS TLS root certificates client-side
IAM Authentication: IAM roles to connect to your database (instead of username/pw)
Security Groups: Control Network access to your RDS / Aurora DB
No SSH available except on RDS Custom
Audit Logs can be enabled and sent to CloudWatch Logs for longer retention

Amazon RDS Proxy: Fully Managed Database Connection Management
- We know we can have RDS deployed in our VPC.
- we can have multiuple connections from multiple dbs to our rds
- we can pool our connections uing proxy
- Improving database efficiency by reducing the stress on database resources (e.g., CPU, RAM) and min
- Serverless, autoscaling, highly available (multi-AZ).
- Speeds up RDS & Aurora failover by up to 66%.
	- it also includes maintenance times.
- Supports RDS (MySQL, PostgreSQL, MariaDB, MS SQL Server) and Aurora (MySQL, PostgreSQL).
- Most apps don't require code changes.
- Enforces IAM Authentication, securely stores credentials in AWS Secrets Manager.
- Accessible only within VPC, not publicly accessible.
- works with lambda asq well:
	- lambda scales in/out fast
	- proxy helps here


#### ElasticCache

ElastiCache: Managed Caching Solution
- Manages Redis or Memcached.
- High-performance, low-latency in-memory databases.
- Relieves database load for read-heavy workloads.
- Promotes statelessness in applications.
- AWS handles OS maintenance, patching, setup, monitoring, backups, and more.
- Requires significant application code adjustments.
- Cache must have an invalidation strategy to make sure only the most current data is used in there.

> makes apps stateless, even if user hits another db, cache can be used to get session data.

|Aspect|Redis|Memcached|
|---|---|---|
|Multi-AZ|Supported with Auto-Failover|Not supported|
|Read Replicas|Available for scaling reads and HA|Not available|
|Data Durability|AOF persistence for durability|Non-persistent storage|
|Backup/Restore|Backup and restore features|No backup and restore|
|Data Partitioning|Sharding using multi-node architecture|Multi-node for data partitioning (sharding)|
|Sorted Sets|Supports sets & sorted sets data structure|Not supported|
|High Availability|Relies on Multi-AZ and Read Replicas|Lacks high availability (no replication)|
|Architecture|Single-threaded|Multi-threaded|


Cache security:
ElastiCache's IAM Authentication for Redis:
- ElastiCache supports **IAM Authentication** for Redis. (only for redis)
- IAM policies on ElastiCache are focused on **AWS API-level security**.
- Redis instances also support traditional **AUTH** authentication using a password or token.
- When creating a Redis cluster, you can set a **password/token** for an additional layer of cache security, in addition to using security groups.
- ElastiCache also provides **SSL in-flight encryption** for secure data transfer.
- For Memcached, **SASL-based authentication** is supported, which is a more advanced form of authentication.

Patterns for Cache storage:
Cache-Aside (Lazy Loading)
A cache-aside cache is the most common caching strategy available. The fundamental data retrieval logic can be summarized as follows:
1. When your application needs to read data from the database, it checks the cache first to determine whether the data is available.
2. If the data is available (_a cache hit_), the cached data is returned, and the response is issued to the caller.
3. If the data isn’t available (_a cache miss_), the database is queried for the data. The cache is then populated with the data that is retrieved from the database, and the data is returned to the caller.
4. This approach has a couple of advantages:
	- The cache contains only data that the application actually requests, which helps keep the cache size cost-effective.
	- Implementing this approach is straightforward and produces immediate performance gains, whether you use an application framework that encapsulates lazy caching or your own custom application logic.
- Disadvantage:
	- A disadvantage when using cache-aside as the only caching pattern is that because the data is loaded into the cache only after a cache miss, some overhead is added to the initial response time because additional roundtrips to the cache and database are needed.


5. write-through (a proactive approach)
	1. A write-through cache reverses the order of how the cache is populated. Instead of lazy-loading the data in the cache after a cache miss, the cache is proactively updated immediately following the primary database update. The fundamental data retrieval logic can be summarized as follows:
		1. The application, batch, or backend process updates the primary database.
		2. Immediately afterward, the data is also updated in the cache.
	2. A write-through cache
		The write-through pattern is almost always implemented along with lazy loading. If the application gets a cache miss because the data is not present or has expired, the lazy loading pattern is performed to update the cache.
		The write-through approach has a couple of advantages:
		- Because the cache is up-to-date with the primary database, there is a much greater likelihood that the data will be found in the cache. This, in turn, results in better overall application performance and user experience.
		- The performance of your database is optimal because fewer database reads are performed.
		A disadvantage of the write-through approach is that infrequently-requested data is also written to the cache, resulting in a larger and more expensive cache.
		A proper caching strategy includes effective use of both write-through and lazy loading of your data and setting an appropriate expiration for the data to keep it relevant and lean.
1. Session Storage:
	1. store temporary session data in storage.


Redis use case:
Gaming Leaderboards are computationally complex
• Redis Sorted sets guarantee both uniqueness and element ordering
• Each time a new element added, it’s ranked in real time, then added in correct order


**Important ports:**
- FTP: 21
- SSH: 22
- SFTP: 22 (same as SSH)
- HTTP: 80
- HTTPS: 443
**vs RDS Databases ports:**
- PostgreSQL: 5432
- MySQL: 3306
- Oracle RDS: 1521
- MSSQL Server: 1433
- MariaDB: 3306 (same as MySQL)
- Aurora: 5432 (if PostgreSQL compatible) or 3306 (if MySQL compatible)