SQL databases:
1. Must use ACID transactions
	1. Atomicity: whether all txn should happen or not at all.
	2. Consistency: db should go from one consistent to another consistent state. (any txn should abide by all rules of db).
	3. Isolation: multiple txn can happen parallelly, but should happen consistently. should be isolated
	4. durability: txn should be permanent.
	5. BEGIN TRANSACTION; AND COMMIT;  can be used to write transactions.
	6. if we perform diff txns in diff terminals, it will happen concurrently but under the hood it will happen sequentially.
	7. data is stored in buffer before committing.
	8. txn logs are used to rollback.
2. Indexes:
	1. query: name if x>10 or max x
	2. this is linear time.
	3. we need indexes
	4. it is a DB which is used for searching on specific attribute, types:
		1. bitmap
		2. reverse
		3. dense
	5. a db containing pointers to items in table where data is actually stored, they can ordered, BS can be used here to search.
	6. increase time for write oeprations
	7. fasten read operations 
3. 