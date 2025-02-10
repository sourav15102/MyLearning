
**Situation:**  
Just to give you little bit of background,  CRA had diff services for diff types of accounts for one feature, as there were redundancies, first, first projects I worked on when I joined company in January, 2024. After the implementation, I raised an issue with a piece of code resulted due to amalgamation.  a piece of code had become complex and hard to maintain,  as it required a lot of change requested by customers and by ministries (10–12 change requests / month). 

Had a meeting with business team to understand intricacies. Customer uploaded docs (5-10) for reps and each doc can have multiple accounts at the same time, order of checks can change for each account. There are seldom cyclic order in which they have to be run, that has to be reported then. and there are lot of commonalities(~40%) between the checks for each account.

---

**Task:**  
Design a **configuration-driven compliance system** that:

- Dynamically handling inactive or irrelevant rules are excluded.
- Supports documents associated with multiple accounts having common checks.
- Prevents issues like chain cycles and ensures audit readiness.
- Delivers results quickly to improve customer satisfaction.

---

**Action:**

1. **Centralized Configuration Store**:  
    I created a **DynamoDB-based configuration** that defined rules, their applicability, and execution order.  
    Example Configuration:
    
    ```json
    [
        {
            "rule_id": "rule_1",
            "name": "Taxpayer ID Validation",
            "accounts": [{"individual": true}, {"business": true}],
            "next_rule_id": "rule_2"
        },
        {
            "rule_id": "rule_2",
            "name": "Document Verification",
            "accounts": [{"individual": true}, {"business": false}],
            "next_rule_id": "rule_3"
        },
        {
            "rule_id": "rule_3",
            "name": "License Validation",
            "accounts": [{"individual": true}],
            "next_rule_id": null
        }
    ]
    ```
    
    This allowed real-time updates to rules without code changes.
    
2. **Cross-Functional Collaboration**:
    
    - I worked closely with **business teams** to identify key insights:
        - There were a **limited number of total rules**, but their order frequently changed.
        - Rules could be active for some accounts but not others, requiring a dynamic solution.
        - Documents often belonged to **multiple account types**, requiring careful rule inclusion.
    - These insights shaped the design of the configuration format and chain logic.
3. **Dynamic Chain Construction (Factory Method):**
    
    - Built a **factory method** to:
        - Fetch configurations dynamically.
        - Exclude inactive rules or irrelevant accounts.
        - Dynamically assign the `next_rule_id` to link handlers.
    - This ensured the chain was constructed correctly at runtime without manual updates.
4. **Edge Case Handling**:
    
    - Implemented a **cycle detection algorithm** to verify that chains did not contain circular dependencies.
    - If a cycle was detected during chain construction, the system threw an exception and logged the issue for debugging.
5. **Rule Execution (Chain of Responsibility):**
    
    - Each handler:
        - **Fetched associated accounts** for the document from an external service.
        - Checked its configuration to verify if the rule applied to any account type.
        - Executed logic if applicable; otherwise, passed the document to the next handler.
    - Example Execution:
        - A document associated with an **Individual Account** and a **Business Account** would trigger `Taxpayer ID Validation` but skip `License Validation`.
6. **Testing and Validation**:
    
    - Used **JaCoCo** for code coverage, achieving **95% coverage** across all handlers and chain construction logic.
    - Automated unit tests verified:
        - Chains were constructed correctly based on configurations.
        - Rules executed only for applicable accounts.
    - Conducted performance testing with **mock traffic spikes**, simulating **2x peak load**.
7. **Scalability Enhancements**:
    
    - Offloaded non-critical rules (e.g., document verification) to background processing via **AWS SQS**.
    - Leveraged **AWS Lambda** for on-demand scaling of critical rules.

---

**Result:**

- **Dynamic Flexibility**: The system dynamically excluded inactive rules, reducing manual intervention.
- **Improved Customer Experience**:
    - Reduced compliance processing times by **30%**, due to reduction in common checks using new relics. enabling faster document approvals and onboarding. (from 10-12 secs to 6-7 secs)
    - Increased system responsiveness during peak loads.
- **Audit Readiness**: Cycle detection and centralized logging ensured a **100% pass rate** in compliance audits.
- **Reduced Maintenance Costs**: Updates to compliance rules required only configuration changes, reducing time-to-market from **weeks to 1.5 day**.

---

**Follow-Up Questions**:

- **Why use configuration-driven chains?**  
    To minimize code changes and adapt to frequent business requirements dynamically, delivering faster results to customers.
    
- **How did you ensure resilience?**  
    Implemented cycle detection during chain construction and validated configurations to prevent runtime failures.
    
- **How did you measure customer impact?**  
    Metrics like compliance processing time, customer onboarding time, and system downtime were tracked using real-time dashboards. Results showed a **40% improvement in approval time**, directly enhancing customer satisfaction.
    

---
---
---
### **Relevant Leadership Principles**:

1. **Customer Obsession**:
    
    - Focused on delivering quicker results for customers by reducing processing times and scaling effectively.
    - Metrics-driven approach to measure customer satisfaction (e.g., compliance time reduction, faster approvals).
2. **Dive Deep**:
    
    - Identified redundancies in data and code after amalgamation.
    - Worked with business teams to understand intricate dependencies and patterns in compliance rules.
    - Implemented cycle detection and validated chain construction for correctness.
3. **Invent and Simplify**:
    
    - Transitioned from a complex, hard-to-maintain system to a configuration-driven model.
    - Simplified updates by centralizing rule definitions and dynamically constructing chains, reducing maintenance overhead.
4. **Ownership**:
    
    - Raised the issue proactively, took full ownership of the design and implementation, and collaborated cross-functionally.
    - Ensured audit readiness and system resilience.
5. **Deliver Results**:
    
    - Achieved a **30% reduction in compliance time** and improved scalability to handle **2x traffic**, meeting customer expectations.
    - Delivered a production-ready, scalable solution in a timely manner, directly impacting business SLAs.
6. **Earn Trust**:
    
    - Collaborated with the business team to shape the solution and addressed their concerns effectively.
    - Used testing (JaCoCo and performance profiling) to ensure high-quality delivery.

---

### **Behavioral Questions to Use This Story For**:

#### **1. "Tell me about a time when you simplified a complex problem."**

- Use this story to demonstrate how you simplified the compliance system by introducing configuration-driven chains and eliminating redundant checks, significantly reducing maintenance overhead.

#### **2. "Give an example of when you took ownership of a problem and drove it to resolution."**

- Highlight how you raised the issue of system complexity post-amalgamation, proposed a solution, and worked collaboratively with business and technical teams to implement it.

#### **3. "Describe a time when you had to work with cross-functional teams to deliver a solution."**

- Discuss how you worked with business teams to understand the dynamic nature of compliance rules, such as shared checks, changing order, and multi-account documents, and used this understanding to shape the configuration format.

#### **6. "How do you handle failure or unexpected edge cases?"**

- Use this story to discuss how you implemented cycle detection to prevent runtime issues, and how you ensured system resilience through robust logging and validation mechanisms.


XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

#### **4. "How do you ensure scalability in your designs?"**

- Explain how you leveraged AWS services (SQS, Lambda) to ensure the system scaled seamlessly, even under peak load conditions.

What could have done better?
requests per second