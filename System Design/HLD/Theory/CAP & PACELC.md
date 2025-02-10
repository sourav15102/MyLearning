https://www.designgurus.io/blog/system-design-interview-basics-cap-vs-pacelc
## What is missing in the CAP theorem?

We cannot avoid partition in a distributed system, therefore, according to the CAP theorem, a distributed system should choose between consistency or availability. **ACID** (Atomicity, Consistency, Isolation, Durability) databases, such as RDBMSs like MySQL, Oracle, and Microsoft SQL Server, chose consistency (refuse response if it cannot check with peers), while **BASE** (Basically Available, Soft-state, Eventually consistent) databases, such as NoSQL databases like MongoDB, Cassandra, and Redis, chose availability (respond with local data without ensuring it is the latest with its peers).

**One place where the CAP theorem is silent is what happens when there is no network partition?** What choices does a distributed system have when there is no partition?

So basically:
1. When there is partition: trade of b/w availability and consistency.'
2. else: tradeoff b/w latency and consistency.

**Tradeoff between Latency and Consistency (L vs C):**
- **Low Latency (L):** Aiming for low latency often involves minimizing the time it takes for a request to be completed. This might lead to sacrificing strong consistency, allowing nodes to diverge slightly to reduce the time it takes to respond.
- **Consistency (C):** Prioritizing consistency may involve ensuring that all nodes have an identical view of the data, even if it means waiting longer for all nodes to agree before responding to a request.
