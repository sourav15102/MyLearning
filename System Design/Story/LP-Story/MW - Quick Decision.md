### **Story: Critical Decision During Baseline Generation Tool Deployment**

#### **Situation**

At MathWorks, I was leading the **deployment of the Baseline Generation Tool**, designed to automate the creation of baseline results for validating Simulink models across different configurations. The tool had been extensively tested and was performing well under normal use cases. However, during a **late-stage test cycle** a week before deployment, multiple Simulink models began failing the validation process despite passing earlier in the pipeline.

#### **How I Identified the Issue**

While investigating the failures, I discovered that the tool couldn’t handle **Simulink models with deeply nested structures**, particularly when the nesting exceeded **50 levels**. This issue only surfaced with a subset of edge-case models that used heavy hierarchy for modular design. Using MATLAB Profiler and diagnostic logs, I traced the problem to the tool’s **custom baseline serialize**, which serialized and processed model hierarchies. It failed when the nesting went beyond 10 levels due to:

- **Stack Overflow in Serialization**: Recursive calls in the generator didn’t account for extreme nesting, leading to stack overflow errors.
- **Inefficient Metadata Handling**: The generator added redundant metadata at each level of nesting about all its parent block, exponentially increasing processing time and memory usage.

---

#### **Task**

The tool was critical for ensuring the accuracy of baseline results, and deployment delays would disrupt upcoming regression cycles. I needed to:

1. Quickly stabilize the tool while handling the immediate failures.
2. Make a decision **without managerial approval** due to the urgency, balancing long-term risks with the immediate need to deploy.

---

#### **Action**

1. **Making the Decision**:
    
    - After confirming the root cause, I decided to rewrite the **baseline generator’s hierarchy handling logic**, recursive to  iterative, removing the capturing of parent metadata.
    - This decision was risky because it required altering a core component of the tool under tight timelines, with limited time for full validation.
3. **Transparent Communication**:
    
    - On the call, I informed the stakeholders of the issue, the proposed fix, and the trade-offs involved. I explained that while this solution addressed the immediate problem, further optimization might be needed post-deployment.
4. **Planning for Long-Term Resilience**:
    
    - Documented the edge case and proposed adding a **stress-testing framework** to validate the tool with increasingly nested models, ensuring future stability.
    - Changed algo to store immediate parent metadata.

---

#### **Result**

The updated generator was deployed within 12 hours, resolving the immediate failures and stabilizing the Baseline Generation Tool. The tool was successfully deployed on schedule, supporting regression cycles without disruptions. Post-deployment, the iterative approach proved robust, and the additional stress-testing framework enhanced the tool’s reliability. My ability to manage the crisis and implement a rapid fix was well-received by leadership.