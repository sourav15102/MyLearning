### **CRA - Account Transaction Query Optimization**

- **Situation**:  
    The **Account Transaction System** supported analytical queries over historical data for different teams and clients. However, despite implementing time-based indexing, querying older transactions was inefficient due to long table scans, particularly when filtering by user ID. This led to high latency and resource usage, especially for queries spanning multiple years.
    
    - **Why Not NoSQL?** The system handled **transactional data** where **ACID compliance** was critical to maintain financial integrity. Relational databases were essential to ensure consistent results for multi-table joins and aggregate functions.
    - **Existing Infrastructure**: CRA's infrastructure was built on a **relational database**, and transitioning to a NoSQL or TimeSeries DB wasn’t feasible without significant re-engineering.
- **Task**:  
    Improve query performance for historical data while maintaining consistency, reducing costs, and ensuring scalability.
    
- **Action**:
    
    1. **Sharding by Year**: Analyzed query patterns and partitioned the data by year (e.g., `transactions_2020`, `transactions_2021`) to limit query scope and reduce table scan times.
    2. **Materialized Views**: Created precomputed aggregates and summaries for commonly queried metrics, reducing computation time for frequent queries.
    3. **Caching Frequently Accessed Data**: Implemented a caching layer using Redis to store results for high-frequency queries, minimizing repetitive database hits.
    4. **Cold Storage Strategy**: Moved older data (20+ years) to **Amazon S3**, balancing cost-efficiency with easy access for occasional queries.
- **Result**:
    
    - **Performance**: Reduced query response time by **60%** (from ~2 seconds to ~800ms).
    - **Scalability**: Improved the system's ability to handle **2x traffic spikes** during peak reporting periods.
    - **Operational Efficiency**: Reduced manual database optimizations by automating archival processes and leveraging caching.
    - **Cost Savings**: Achieved significant savings by minimizing active storage requirements using a hybrid on-premises and cloud-based archival strategy.

---

### **Key Points for the Interview**:

- Highlight how **query pattern analysis** informed the decision to shard by year.
- Justify the use of **materialized views and caching** as optimizations within the relational database model.
- Emphasize the **customer-first approach**, focusing on minimal disruption during implementation and enhanced user experience.

---

### Revised Hashtags:


---

This version balances technical credibility with practical decision-making. Let me know if you’d like further adjustments!