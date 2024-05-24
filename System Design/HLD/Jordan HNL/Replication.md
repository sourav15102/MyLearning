### Single Leader Replication (SLR):
Read/write to master, read to slaves node.
1. Availability:
	1. Adding new slave node: take snapshot of master and replicate it in new node.
	2. Recover slave node: it has marked the point it was at in replication log, during recovery it will continue to get updates after 'x' in replication log.
	3. master down: consensus algo to choose, new master
		1. we need to make sure that when previous master comes back, it doesnt identify itself as master, (split brain)
2. Types of SLR:
	1. Sync replication
	2. Async replication
		1. Problems:
			1. Reading own writes: wrote at x(master) node, before replication, read from y node.
			2. Monotonic reads: like in chat applications, it might look like the messages are re-ordered.
			3. Consistent prefix read: one write make sense after second write (Causal relationship), the might be on diff nodes, causing issues.
		2. Solutions:
			1. Reading from only master node, for editable sections of page. OR read from any node after certain seconds after writing.
			2. Mark all user to one partition.
			3. Give some timestamp to each msg and resolve Causal dependencies.

### Replication Log:
1. Copy SQL statements: wouldn't work, cos statements can be non-deterministic, (mean diff things on diff nodes), eg: timestamp
2. Internal write ahead log: these are based on bytes -> data mapping, if size of another nodes, change, bytes would mean diff thing, so wont work.
3. Logical log: basically means, change data to x in row y.

### Multileader replication:
1. Multiple leaders share data via specified topology
	1. Circular and star topology: one node fails, entire topology fail.
	2. All-to-all topology: Good fault tolerance, but order in which a node receives updates from multiple other nodes can mess up.
2. Pros:
	1. good for write heavy apps
	2. master nodes can be in diff data center, help making app more global.
3. Cons:
	1. Conflicts between master nodes.

Resolve Conflicts:
1. Conflict Avoidance
	1. All writes for same item go to the same partition.
2. Last write wins
	1. Each write has a time stamp attached, and the last update to an item is considered the correct one.
3. On read
	1. DB keep both writes, but while reading at user end we merge, like shopping cart.
4. On write
	1. DB keep both and merge them while writing using some function, which needs to be commutative (order of updates doesnt matter), associative (grouping of changes doesnt matter)

### Concurrent writes:

What are concurrent writes:
It has nothing to do with actual time where two writes were written. It has to do with the knowledge two parties had when they made the write. 
For example: When A wrote at 't', B read what A wrote and wrote itself on  't+1' , then it wont be a concurrent write cos, B already knew what A had written. However, If A wrote at 't' and B hasn't refreshed the page lets say and doesnt now what A had written and writes itself at 't+1', then it would be considered a concurrent write, as B didnt know about A's update.

Version Vectors:
It is a way for us to keep track what knowledge does each DB and client have about a key.

### Leaderless replication:

We read and write to multiple nodes, there is/are no leader(s) per say.

Quorum:
1. N = Number of total nodes on quorum.
2. W = Number nodes we need to write to consider it successful.
3. R = Number nodes we need to read from to consider it successful.

When we write to W nodes, how do we make sure all of them gets the updated data, also when we read from R nodes, how do we get the updated version.
1. Anti Entropy:
	1. A background process, which look at versions of all keys at all DBs, and update them with the latest version.
2. Read repair (used usually)
	1. When we read from R nodes, lets say X had the latest key, then we accept the X's value and update it to the R-1 nodes.

But, all of R nodes might not have the latest data, to counter that we keep N odd and,
`W = R = (N+1)/2`
That way we will have at least some nodes with latest data while reading. 

Issues with Quorum:
1. Write to W nodes fails for 2 nodes, but for rest we cant roll back and that might get replicated using read repair.
2. Read conflicts can happen here as well, resolved by version vectors.






