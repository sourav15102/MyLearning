### **Summary of Partition Key, Sort Key, Secondary Index, and Global Index**

#### **1️⃣ Partition Key**

- **Defines how data is distributed across nodes** in a database (e.g., Cassandra, DynamoDB).
- Ensures **efficient lookups** by directly mapping queries to the correct node.
- **Must be chosen carefully** to avoid hotspots (e.g., all data landing on one node).
- **Example**:
    
    ```sql
    PRIMARY KEY (stock_id)
    ```
    
    - `stock_id` determines which node stores the data.

---

#### **2️⃣ Sort Key (Clustering Key in Cassandra)**

- **Defines how data is ordered within a partition**.
- Helps **query and sort** rows efficiently without scanning the entire dataset.
- **Must be combined with a partition key** to uniquely identify a row.
- **Example**:
    
    ```sql
    PRIMARY KEY (stock_id, timestamp)
    ```
    
    - Data for each `stock_id` is **sorted by `timestamp`** within its partition.

---

#### **3️⃣ Secondary Index (Local Index)**

- **Allows queries on non-partition key columns**, but is **not globally distributed**.
- Stored **locally on each node**, meaning queries may scan multiple nodes.
- Can be inefficient if **high-cardinality** (many unique values).
- **Example**:
    
    ```sql
    CREATE INDEX ON stock_prices (price);
    ```
    
    - Enables querying **`WHERE price > 5`**, but requires scanning nodes.

---

#### **4️⃣ Global Index (Materialized View in Cassandra, GSI in DynamoDB)**

- **A distributed index that spans multiple nodes**, allowing efficient lookups on non-partition key columns.
- Unlike **secondary indexes, global indexes distribute data across all nodes** for better scalability.
- **Example** (Materialized View in Cassandra):
    
    ```sql
    CREATE MATERIALIZED VIEW stock_by_price AS
    SELECT stock_id, price
    FROM stock_prices
    WHERE price IS NOT NULL
    PRIMARY KEY (price, stock_id);
    ```
    
    - Enables **efficient querying on `price`** without scanning partitions.

---

### **🚀 Choosing the Right Index for PayPal-Level Scale**

|Use Case|Best Choice|
|---|---|
|**Fast lookups by key**|Partition Key|
|**Sorting within a partition**|Sort Key (Clustering Key)|
|**Occasional filtering on non-key columns**|Secondary Index (Local Index)|
|**Frequent lookups on non-key columns**|Global Index (Materialized View)|

Would you like to see a **comparison with other databases** (e.g., MySQL, Elasticsearch)? 🚀