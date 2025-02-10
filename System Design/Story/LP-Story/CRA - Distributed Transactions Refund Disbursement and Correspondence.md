### **Situation**

At CRA, the refund processing system handled millions of refunds annually, combining three key steps:
1. **Refund Approval**: Validating and approving refund requests (Refund Management Service - RMS).
2. **Fund Disbursement**: Reserving and disbursing funds (Disbursement Service - DS).
3. **Correspondence Generation**: Sending refund confirmation documents (Correspondence Service - CS).

#### **Manual Flow (Initial State)**:
1. **RMS** approved refunds and generated flat files.
2. These files were manually processed by DS for fund disbursement.
3. Correspondence notices were sent independently by CS based on logs from RMS and DS.

**Issues with Manual Flow**:
- **High Latency**: The manual process delayed refunds and documents, with some refunds taking up to **48 hours** to complete.
- **Error-Prone**: Refund-document mismatches occurred in **5% of cases**, requiring extensive manual reconciliation.
- **Poor Scalability**: The system struggled during peak tax seasons, with **40% slower response times** under heavy load.

---

> I acknowledged my teammates' concerns about abandoning the familiar distributed transaction model and validated their points on consistency risks. To address this, I demonstrated the scalability benefits of an event-driven approach using concrete metrics and a prototype. By involving them in refining the solution and showing fallback mechanisms like DLQs for safety, I built trust and aligned the team toward the new architecture.
### **Task**
A colleague proposed implementing a **distributed transaction system** to address the issues. The approach involved RMS synchronously orchestrating distributed transactions between DS and CS. However, I identified critical bottlenecks in this design:

1. **High Latency**:
    
    - RMS would block on distributed transactions, delaying responses to customers.
2. **Scalability Concerns**:
    
    - Distributed locks in DS would be triggered for every individual refund, causing contention and slowing down fund reservations.
3. **Complexity in Coordination**:
    
    - Coordinating synchronous distributed transactions between RMS, DS, and CS added significant overhead.

I proposed an alternative **event-driven architecture** leveraging **Kafka, Spark Streaming**, and **Flink**, which decoupled services and improved scalability while maintaining eventual consistency.

---

### **Action**

#### **Proposed Solution: Event-Driven Architecture**

1. **Decoupling with Kafka**:
    - RMS no longer orchestrated refunds synchronously. Instead, it pushed refund requests as events to **Kafka**.
    - This reduced RMS response time, allowing it to immediately return a "Pending" status to customers.
2. **Batch Processing with Spark Streaming**:
    
    - Spark consumed refund events from Kafka and grouped them into **batches by refundTypeID** (e.g., tax refunds, benefit refunds).
    - Each batch was sent to DS, minimizing the frequency of distributed locks.
3. **Distributed Locking in DS**:
    
    - DS acquired locks for entire batches, reducing contention and ensuring atomicity during fund reservations.
4. **Document Processing with Flink**:
    
    - DS sent successful refund events to Kafka, which were consumed by Flink.
    - Flink ensured refund confirmation documents were generated in CS with **exactly-once processing semantics**.
5. **RMS Final Updates**:
    
    - RMS consumed success/failure events from Kafka to update refund statuses and notify customers.

---

#### **Implementation Details**

- **Idempotency**:
    
    - Every refund event included a unique **idempotency key** (e.g., refundID).
    - This ensured retries didn’t result in duplicate fund disbursements or document generation.
- **Dead-Letter Queue (DLQ)**:
    
    - Failed transactions were sent to a DLQ for retry or manual resolution, ensuring no refunds were lost.
- **Monitoring and Scalability**:
    
    - Used **Kafka metrics**, **Spark monitoring**, and **Flink dashboards** to track pipeline performance and detect bottlenecks.

---

### **Result**

1. **Improved Scalability**:
    - The system processed **2 million refunds  during peak tax season, compared to **<1M million previously**, without performance degradation.
2. **Faster Response Times**:
    - RMS response time to customers decreased from **1 Day seconds to <10 second**, providing near-instant feedback.
3. **Reduced Errors**:
    - Refund-document mismatches dropped to ~0, compared to 5% with the manual system.
4. **Operational Efficiency**:
    - Automated retries and error handling reduced manual reconciliation efforts by **90%**, saving **hundreds of hours monthly**.
5. **Cost Savings**:
    - Batch processing in Spark reduced the frequency of distributed locks in DS, cutting infrastructure costs by **25%**.

---

### **Scalability in Practice**

1. **Batch Size Optimization**:
    - Spark processed refunds in batches of 500-1,000 (based on refundTypeID), balancing latency and throughput.
2. **Horizontal Scaling**:
    - Kafka, Spark, and Flink consumers were horizontally scaled, allowing the system to handle **3x traffic spikes** without bottlenecks.
3. **Distributed Locking Efficiency**:
    - decreased by factor pf 500 == `1/500`

---

### **Key Justifications**

1. **Why Not Distributed Transactions?**
    
    - Distributed transactions added significant latency and complexity. The event-driven architecture achieved **eventual consistency** without blocking RMS.
2. **Why Use Spark and Flink?**
    
    - **Spark Streaming**: Efficient for batch processing refunds, reducing DS contention.
    - **Flink**: Ideal for ensuring consistency in correspondence generation with exactly-once semantics.
3. **Customer-Centric Improvements**:
    
    - Decoupling RMS allowed customers to receive faster responses, even during backend processing.


### LPs:
- **Customer Obsession**:  
    Improved customer experience by reducing refund response times and eliminating refund-document mismatches.
- **Dive Deep**:  
    Identified bottlenecks in the proposed distributed transaction system and provided a scalable alternative.
- **Invent and Simplify**:  
    Replaced a manual, error-prone system with an event-driven architecture using Kafka, Spark, and Flink.
- **Bias for Action**:  
    Acted quickly to address system bottlenecks with a practical and scalable solution.
- **Insist on the Highest Standards**:  
    Delivered a system with nearly 0% refund-document mismatches and scalable to 6 million weekly refunds during peak.
- **Think Big**:  
    Designed a solution that future-proofed CRA’s refund processing while addressing current operational inefficiencies.
- **Deliver Results**:  
    Achieved measurable improvements in scalability, latency, and operational efficiency, with significant cost savings.


#### **Behavioral Questions and One-Line Answers**
1. **Customer Obsession**:
    - **Q**: Tell me about a time you improved a process to better serve customers.
    - **A**: I reduced refund response times to under 10 seconds, ensuring taxpayers received real-time updates.
2. **Dive Deep**:
    - **Q**: Describe a time you uncovered the root cause of a problem.
    - **A**: I identified that synchronous distributed transactions introduced latency and locking contention, leading to inefficiency.
3. **Invent and Simplify**:
    - **Q**: How have you simplified a complex process or system?
    - **A**: I replaced a complex distributed transaction workflow with an event-driven architecture, significantly improving performance.
4. **Bias for Action**:
    - **Q**: Tell me about a time you acted quickly to address a challenge.
    - **A**: I quickly proposed and implemented an event-driven architecture that avoided the delays of a distributed transaction model.
5. **Insist on the Highest Standards**:
    - **Q**: How have you ensured high-quality results in a project?
    - **A**: I eliminated refund-document mismatches and ensured 90% reduction in manual reconciliation.
6. **Think Big**:
    - **Q**: Describe a time you proposed a bold solution to solve a significant problem.
    - **A**: I designed a future-proof system that scaled refund processing to handle 6 million weekly transactions during peak tax season.
7. **Earn Trust (Disagreement)**:
    - **Q**: Tell me about a time you disagreed with a colleague and had to convince them.
    - **A**: I convinced a colleague to abandon the distributed transaction approach by presenting concrete metrics showing the scalability and latency benefits of my event-driven architecture.
8. **Deliver Results**:
    - **Q**: How did you ensure the success of a high-impact project?
    - **A**: I delivered a solution that improved scalability, reduced latency, and saved significant costs while ensuring consistent customer satisfaction.