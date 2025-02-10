https://newsletter.systemdesigncodex.com/p/amazon-dynamo-breakdown

1. Consistent hashing is used for sharding.
2. Virtual nodes are used to overcome issues with normal Consistent Hashing: They are points spread across ring, mapped to a single actual node.
	1. Non-uniform distribution: Virtual nodes can help with that, by spreading virtual nodes across ring.
	2. Not taking capacity into consideration: more capacity, more virtual nodes across ring. 
3. Replication: Data is replicated to many nodes, One coordinated node and other replication nodes. Replication Lag is next problem solved by vector clocks.

Two phase commit in [[My learnings/System Design/HLD/Theory/Microservices]] is for transactions, making sure in distributed system, either all nodes commit or all abort.

Vector Locking is for managing async updates of objects for all replicas. 