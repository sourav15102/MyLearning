Analytics:
	1. So basically our mysql has column for clicks against a URL, f multiple users are trying to update it, it ca cause race conditions.
	2. Solution: streaming, we can use messagr brokers, submit our clicks to messqag broker, but these are in memory and hence no fault tolerant. So, we can use log based message broker, which use write ahead logs. 
	3. We can use Kafka message queues distributed by key range.
	4. If we need analytics not too **frequently**: we can have message broker consumer to dump everything in a file/S3, and run batch job to add all clicks up.
	5. If we need analytics frequently: We can use Spark streaming as consumer to message broker, where we can use mini-batch size of lets say 100, collect 100 clicks and add them up.


### **Approaches for Event Aggregation:**

#### **1. Task Runners with Kafka and Two Databases**

- **Workflow:**
    
    - **Task Runner 1:**
        - Collects events from **Kafka**, assigns a **UUID** to each event to ensure **idempotency**.
        - Dumps events into a **first database**.
    - **Task Runner 2:**
        - Reads events from the first database.
        - Aggregates counts and writes results to the **second database**.
- **Pros:**
    
    - **Flexibility:** Decouples event collection and aggregation logic.
    - **Idempotency Control:** UUID ensures no duplicate event processing.
    - **Retry Mechanism:** Easier to replay failed tasks by reprocessing from the first database.
- **Cons:**
    
    - **Higher Latency:** Events need to go through multiple steps (Kafka → DB1 → DB2).
    - **Operational Overhead:** Two databases and multiple task runners require more management.

---

#### **2. Flink with Direct Aggregation**

- **Workflow:**
    
    - **Flink:**
        - Directly processes events (from Kafka or a database).
        - Performs **real-time aggregation** (e.g., summing counts).
        - Writes the aggregated results directly to the **second database**.
- **Pros:**
    
    - **Low Latency:** Direct aggregation without intermediate storage.
    - **Scalability:** Flink handles massive event streams efficiently.
    - **Simpler Architecture:** Eliminates the need for multiple task runners and an intermediate database.
- **Cons:**
    
    - **Complex Development:** Requires expertise in **Flink programming** and real-time stream processing.
    - **Less Retry Flexibility:** Reprocessing failed events may be harder without a persistent intermediary like DB1.

---

### **When to Use Which:**

1. **Task Runners Approach (Option 1):**
    
    - If you need **flexibility**, **manual control**, or **high fault tolerance**.
    - Suitable for **batch processing** or systems that prioritize **data reliability** over latency.
2. **Flink Approach (Option 2):**
    
    - Best for **real-time processing** with **low-latency requirements**.
    - Suitable for systems with **high-throughput streaming data** and direct aggregation needs.

---

**Decision Factors:**

- **Latency Critical?** Use Flink.
- **Fault Tolerance and Control?** Use Task Runners.
- **Skill Availability?** Task runners are simpler; Flink requires expertise.