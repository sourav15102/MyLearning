It is just a way to support the ACID(Atomicity, consistency, isolation, durability) properties. The most important one is Isolation: When concurrent request are coming in, they will produce same results as if they were treated serially.

How to achieve that:
1. Actual serial execution: We need single thread operating for it.
	1. We should not have partitions as that would require multiple network calls and that would be an overhead for single thread.
	2. That should use stored procedure as it would be faster for single thread
2. Two Phase locking:
	1. A lock can be in shared or exclusive phase.
	2. multiple threads can have shared lock for reading.
	3. For writing a lock can be upgraded to exclusive phase, but it should be the only one locking that object.
3. All this would protect against concurrent updating, but how to grab lock on rows that doesnt't even exist.
	2. Predicate Lock: We grab locks on rows which doesn't even exist yet, or grab locks on rows that satisfy a condition, so no one can read/write on rows that satisfy same condition. For example: Room booking, both threads would read entry for a room X, which doesn't exist, so one of them grab predicate lock on  Room X, so no one can read for that room, first thread makes entry and second thread when reads the entry wont try to do same as it is already booked.
4. Serializable snapshot: It basically keep track of all reads on an object and the moment someone makes/commit write to that object, all reads are aborted. 