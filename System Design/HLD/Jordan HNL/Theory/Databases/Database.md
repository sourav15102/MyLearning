Three types of indexes:
1. Hashmap: 
	1. Easy to implement
	2. Bad for range queries.
2. LSM + SSTables
	1. Good for writes, as it writes in memory, buffer table.
	2. Buffer table is then written to disk.
	3. Good for range queries.
3. B-Trees
	1. SLower write as it writes directly to disk.
	2. Faster read , logarithmic time

