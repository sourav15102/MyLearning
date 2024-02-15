**Sharding Overview:**

- **Horizontal Sharding (Sharding by Rows):**
    
    - In horizontal sharding, also known as sharding by rows or range-based sharding, the dataset is partitioned based on specific criteria, often a range of values in a particular column.
    - Each shard is a subset of the entire dataset, containing rows that fall within a defined range. For example, rows with specific date ranges or numeric values might be grouped together in a shard.
    - This approach helps distribute the data evenly across multiple servers or nodes, improving parallelism and scalability.
- **Vertical Sharding (Sharding by Columns):**
    
    - In vertical sharding, also known as sharding by columns, different columns of a table are split into separate shards or databases.
    - Each shard holds a subset of columns, and collectively they represent the complete set of columns for the original table.
    - Vertical sharding is useful when certain columns are accessed less frequently or have different access patterns compared to others.

Drawbacks:
1. **Join Operations:**
	- In a traditional database with a single, non-sharded table, joins between tables are straightforward. However, when data is sharded across multiple servers or nodes, performing joins becomes more complex.
	- Joining data from two different shards may require coordination between the servers, potentially leading to increased latency and reduced performance.
	- Solution:
		- **Denormalization:**
	    - Denormalization involves duplicating and storing related data from multiple tables in a single table. This reduces the need for complex joins as more information is stored in a single location. However, denormalization comes with trade-offs, including increased storage requirements and potential data inconsistency.
- Tree Shard
	- Sometimes one shard can be lobsided (people with name 'p' are much more).
	- We might need to shard it further,
	- This can be solved by [[Consistent Hashing]].

