Relational Database: Not necessarily SQL
1. Have fixed schema
2. Rows can have relations with each other using foreign key.
3. Vertical scaling is easier here.
4. Horizontal scaling: sharding
	1. Transactions are way more tough here, as we need distributed transaction.
	2. Too any joins would be a bottleneck.
5. Locking: Two phase locking especially Two phase commit in distributed transactions are way too slow.
6. Indexing: B tree indexing is slow.


Non-relational Database:
1. Document based: Object is completely stored as document.
2. Locality: obj is stored near to each other so, quickly available.
3. Easier to shard
4. Schemeless: flexible
5. A lot of data duplication, when there are relations between data.
6. Solution: Graph DB, can be used in case of many relations.