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


Transactions ensure the **ACID properties** (Atomicity, Consistency, Isolation, Durability) to maintain data integrity and reliability in database systems. Here's how each aspect is handled:

---

### **1. Atomicity (All or Nothing)**

- **Definition:** A transaction is treated as a single, indivisible unit. Either all operations within the transaction succeed, or none of them are applied.
- **How Handled:**
    - **Commit/Rollback Mechanisms:**
        - If a transaction completes successfully, the database commits the changes.
        - If any operation fails, the database rolls back all changes to the state before the transaction started.
    - **Logs:** Write-ahead logging ensures that no changes are made permanent unless the transaction is fully successful.

---

### **2. Consistency (Preserve Data Integrity)**

- **Definition:** A transaction ensures that the database remains in a valid state before and after the transaction, adhering to all constraints, rules, and triggers.
- **How Handled:**
    - **Validation:**
        - Transactions check for constraints (e.g., primary keys, foreign keys, unique constraints) and abort if a violation occurs.
    - **Triggers and Business Rules:** Ensure that operations respect predefined rules.
    - **Cascading Updates/Deletes:** Handle changes to related data to maintain referential integrity.

---

### **3. Isolation (Concurrent Transactions)**

- **Definition:** Transactions are executed in isolation from one another to prevent interference and ensure data consistency during concurrent operations.
- **How Handled:**
    - **Isolation Levels:** Define how transactions can interact:
        1. **Read Uncommitted:** Least isolation, allowing dirty reads.
        2. **Read Committed:** Prevents dirty reads but allows non-repeatable reads.
        3. **Repeatable Read:** Prevents dirty and non-repeatable reads, but phantom reads are possible.
        4. **Serializable:** Highest isolation level, preventing all anomalies.
    - **Locking Mechanisms:** Use locks (e.g., shared or exclusive locks) to control data access.
    - **MVCC (Multi-Version Concurrency Control):** Creates multiple versions of a record to allow transactions to read consistent data.

---

### **4. Durability (Permanent Results)**

- **Definition:** Once a transaction is committed, its changes are permanent and will survive any system failure.
- **How Handled:**
    - **Write-Ahead Logging:** Changes are recorded in logs before being applied to the database.
    - **Checkpointing:** Periodically saves the current state of the database to stable storage.
    - **Replication and Backups:** Distribute transaction logs and data across systems to ensure persistence and recovery in case of hardware failure.

---

### **Illustrative Example**

**Scenario:** A bank transfer of $500 from Account A to Account B.

1. **Atomicity:** Both debiting Account A and crediting Account B succeed, or neither does.
2. **Consistency:** The total sum of money remains constant, and no constraints (like a negative balance) are violated.
3. **Isolation:** Other transactions cannot see the intermediate state (e.g., Account A debited but Account B not credited yet).
4. **Durability:** After committing the transaction, the changes remain even if a system crash occurs.

---

### **Summary Table**

|**ACID Property**|**Ensured By**|
|---|---|
|**Atomicity**|Commit/Rollback, Transaction Logs|
|**Consistency**|Constraints, Business Rules, Triggers|
|**Isolation**|Isolation Levels, Locks, MVCC|
|**Durability**|Logs, Checkpointing, Replication|

These mechanisms collectively ensure that transactions maintain the integrity and reliability of database operations.