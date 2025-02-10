
> Brought something of outside world inside team

> During internal discussions, the team initially proposed asking the customer to introduce a new configuration block in their model to manage rate differences. This would have required significant changes to their braking system design and added complexity to their simulation setup. Recognizing that this approach would disrupt the customer’s workflow and contradict their expectation of a seamless tool
> 
> Rather than implementing a quick patch to reduce immediate computational overhead, I designed an **event-driven execution system** that not only addressed the inefficiencies but also ensured scalability and maintainability for increasingly complex simulations in the future.


### **Situation**

At **MathWorks**, I was mentoring a junior developer working with an **electric vehicle (EV) manufacturer** that used Simulink to design and test their **multi-rate control systems**. The customer reported inefficiencies in their braking simulation models:

1. Their system had a **speed sensor** that updated every **10ms** and a **braking block** that computed braking force every **1ms**.
1. **High CPU Usage**:
    - The braking block, running every **1ms**, unnecessarily processed stale speed data from the sensor (updated every **10ms**).
    - **80% of CPU cycles** were wasted, pushing **CPU utilization to 90%**, causing longer simulation times.
3. **Customer Expectation**:
    - The customer relied on Simulink’s promise of seamless and intuitive tools. A proposed internal solution suggested requiring customers to modify their models with new configuration blocks, adding complexity and disrupting their workflow.

The goal was to **reduce wasted cycles** and improve simulation efficiency while ensuring accuracy, even in a **multi-rate environment** with components running at different rates.

---
### **Task**
My task was to design an **efficient, customer-friendly solution** to:
1. Eliminate unnecessary computations in multi-rate models.
2. Maintain seamless integration without requiring disruptive changes to customer workflows.
3. Improve CPU usage by at least **50%** to enable scalable simulations.

---
### **Action**

#### **1. Analyzing and Addressing the Problem**

- **Root Cause Analysis**:
    - Used Simulink’s **Performance Advisor** to pinpoint inefficiencies. The braking block executed every **1ms**, even though speed data only updated every **10ms**, consuming significant computational resources.
    - Identified that the braking block’s **time-driven execution model** caused stale data processing, wasting **90% of cycles**.
- **Customer Considerations**:
    - The proposed solution from internal discussions required customers to introduce new configuration blocks to manage rate mismatches, contradicting Simulink’s seamless integration philosophy.
    - Highlighted how this would disrupt the customer’s workflow, especially for large models with hundreds of components.

---

#### **2. Proposing an Event-Driven Execution System**

- **Concept**:
    - Designed an **event-driven execution model** where the speed sensor triggers the braking block only when new speed data is available.
    - Avoided requiring configuration changes by leveraging Simulink’s **Function-Call Subsystems** for event-based triggering.
- **Implementation**:
    - The speed sensor block emitted an event every **10ms** upon updating speed data.
    - The braking block subscribed to this event and executed only when triggered.
    - This ensured the braking block processed fresh data while eliminating unnecessary computations.

---

#### **3. Collaborating with Stakeholders**

- **Customer Collaboration**:
    - Worked closely with the EV manufacturer to test the system on their vehicle models.
    - Assured backward compatibility, ensuring the solution worked seamlessly with their existing workflows without requiring configuration changes.
- **Mentoring**:
    - Guided a junior developer in implementing the event-driven system using Simulink’s tools, improving team capability and ensuring future maintainability.

---

#### **4. Benchmarking and Refinement**

- Ran extensive benchmarks comparing the **event-driven system** to the time-driven setup:
    - Tested on models with **20-50 subsystems**, including high-complexity simulations.
    - Collected metrics on CPU usage, runtime, and accuracy to validate improvements.

---

### **Result**
1. **Efficiency Gains**:
    - Reduced CPU utilization by **80%**, freeing up resources for other computations.
2. **Improved Scalability**:
    - Enabled the EV manufacturer to scale their models from **20 to 50 subsystems**, accelerating testing and development timelines.

### **Leadership Principles Catered**

1. **Customer Obsession**
2. **Dive Deep**
3. **Invent and Simplify**
4. **Think Big**
5. **Deliver Results**
6. **Hire and Develop the Best**

---
### **Questions Answered by This Story**

#### **Customer Obsession**
- _How did you ensure the solution aligned with customer needs?_
- _Describe a time you went beyond initial expectations to deliver value for the customer._
#### **Dive Deep**
- _How did you identify inefficiencies in the existing system?_
- _What tools or methods did you use to analyze the problem?_
- _Describe a situation where you had to uncover a root cause before proposing a solution._
#### **Invent and Simplify**
- _Tell me about a time you improved a process to make it more efficient._
- _How did you ensure your solution was both innovative and easy to implement?_
#### **Think Big**
- _Can you share a time you proposed a scalable solution to address a complex challenge?_
- _How did your solution prepare the system for future growth?_
#### **Deliver Results**
- _What were the measurable outcomes of your solution?_
- _How did your solution impact the customer’s operations or objectives?_
#### **Hire and Develop the Best**
- _Can you share a time when you mentored a junior team member?_
- _How did you ensure that your team could maintain the system after implementation?_

