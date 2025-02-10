 ### **Situation**

At CRA, the **Formal File Dispute (FFD)** system pushed taxpayer disputes into a task queue for processing by a downstream service. These tasks were then stored in a **backlog database** if no worker is free or are dynamically assigned to workers. During **peak tax seasons**, the following issues arose:

1. **Queue Dominance by Large Tasks**:
    - Large, complex disputes took significant time to process, delaying smaller, simpler tasks.
2. **Inefficient Worker Utilization**: 
    - Junior workers often remained idle while senior workers were overloaded with complex disputes.
3. **SLA Breaches**:
    - Simplified disputes that could be resolved quickly were delayed, leading to **40% SLA violations** and taxpayer dissatisfaction.

**Total Disputes Received:**
- **Off-Peak Periods**: ~**50,000 disputes/week**.
- **Peak Tax Seasons**: ~**70,000 disputes/week**.
**Distribution by Complexity:**
- **Low Complexity (50%)**:
- **Medium Complexity (30%)**:.
- **High Complexity (20%)**:

---

### **Task**

I was tasked with optimizing the system to:

1. **Prioritize simpler disputes**, ensuring they weren’t delayed by larger, more complex ones.
2. **Distribute workloads** effectively across workers, based on their expertise and availability.
3. Ensure **scalability** for handling increased dispute volumes during peak seasons.

---

### **Action**

#### **1. Proposing Solutions**

**My Proposal: Multi-Queue Priority Scheduling**

- Create three separate queues in FFD for disputes based on complexity (Low, Medium, High).
- Add a layer in FFD to calculate dispute complexity upfront before pushing tasks into the appropriate queue.
- Assign workers to process tasks using a **weighted round-robin scheduler**:
    - **70% Low Complexity**, **20% Medium**, and **10% High.
- **Concerns**:
    - FFD would need to calculate complexity, increasing its processing time.
    - Most tasks already flow into the backlog DB, so splitting queues wouldn’t make a meaningful difference.

**Colleague’s Proposal: Backlog-Based Dynamic Assignment**

- Retain the single queue in FFD and allow tasks to flow into the **backlog database** as usual.
- Optimize the backlog DB:
    - **Index by complexity and time** for efficient retrieval.
    - Shard the DB to scale with task volume during peak periods.
- Assign tasks dynamically:
    - Small tasks prioritized for junior workers.
    - Large, complex disputes routed to senior workers.
- **Strengths**:
    - Avoids adding overhead to FFD.
    - Centralized backlog allows for real-time prioritization and assignment.
    - Scales seamlessly by sharding or indexing.

---

#### **2. Resolving the Conflict**

- After reviewing both solutions, I recognized that:
    
    - My approach added unnecessary complexity to FFD for limited benefit, as tasks were rarely processed directly from the queue.
    - The backlog-based system better utilized existing infrastructure and allowed for dynamic prioritization without burdening FFD.
- I conceded to my colleague’s proposal but contributed by:
    
    - Designing **dynamic prioritization logic** to ensure tasks were processed based on urgency and complexity.
    - Adding **starvation prevention** mechanisms to promote older tasks in the backlog.
    - Recommending **sharding and indexing** to improve the scalability of the backlog database.

---

#### **3. Implementation **

- **Backlog Optimization**:
    
    - Tasks stored in a **PostgreSQL database**, indexed by:
        - **Complexity** (low, medium, high).
        - **Deadline** to prioritize SLA-sensitive tasks.
    - Sharded the DB to handle increased volumes during peak seasons.
- **Dynamic Assignment**:
    
    - Workers pulled tasks from the backlog based on:
        - Complexity (e.g., junior workers assigned simpler tasks).
        - Deadline (e.g., tasks nearing SLA limits were prioritized).
- **Real-Time Tracking**:
    
    - Used **Redis** to monitor worker availability and workloads, ensuring fair distribution of tasks.
    - Implemented a feedback loop to refine complexity calculations based on actual processing times.

---

### **Result**

2. **Faster Resolution for Simpler Tasks**:
    - Reduced processing time for small disputes by **50%**, significantly improving taxpayer satisfaction.
4. **Scalability**:
    - during peak seasons without degradation.

**Distribution by Complexity:**
- **Low Complexity (50%)**:
	- Reduced average processing time from **2 hours to 1 hour**, a **50% improvement**.
- **Medium Complexity (30%)**:.
	-  Reduced processing time from **6 hours to 4 hours**, a **33% improvement**.
- **High Complexity (20%)**:
	- reduced processing time by **10%**