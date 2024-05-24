> Can be used with replication, as a table can be divided in multiple shards, and each shard can be replicated on multiple nodes.

Partitioning strategies:
1. Key ranges: a range of key on particular shard.
	1. pros: simple implementation, good for range queries.
	2. cons: one range might get all the node.
2. hash range:  we hash keys and then store a range to a shard.
	1. pros: good hash function, will evenly distribute
	2. cons: range queries become tough, contiguous keys, x,y might have very diff hash, so, we might need to span over many shards to do range queries.

Secondary index: Used to query on alternate key, non-primary key, for example age key has all the primary key corresponding to that age mapped to it.
1. Local index: each shard has local index,  index carries keys only for that shard, if for that shard, age 23 doesnt exist, then for its local index 23 mapping will be empty.
	1. write is fast, as we need to update local index of only the partition (as its replicas), we are appending the new key of age 23.
	2. read is slow, as we need to go to all partition and collect rows mapped to 23 in all shards.
2. Global index: each shard has global index, Index carries keys for all shards.
	1. write is slow, as if we add row for age 23 in one of the shards (and its replicas), we need to go multiple global index and update mapping for age 23.
	2. read is fast, as we globally store all PK for age 23.