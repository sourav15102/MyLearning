### **Ownership: Tell me about a time you missed a deadline/failed to deliver something**

#### **Proposed Story: CRA - Distributed Transactions**

#### **Situation**

At CRA, I was responsible for implementing a distributed transaction mechanism to maintain strong consistency between two independent services: Refund Management Service (RMS) and Disbursement Service (DS). I initially committed to delivering a complete Two-Phase Commit (2PC) implementation within six weeks.

#### **Task**

The goal was to ensure atomicity across the two services to avoid mismatched transactions during real-time refunds.

#### **Action**

Despite my best efforts, I underestimated the complexity of testing the 2PC protocol under high transaction loads. This resulted in a two-week delay in deploying the solution. To address the delay:

1. I immediately informed stakeholders about the delay, explaining the risks of rushing deployment without proper testing.
2. Reallocated resources to prioritize testing while continuing other development tasks.
3. Created a fallback mechanism for transaction retries to mitigate the impact of any interim failures.

#### **Result**

Though delayed, the deployment was successful and achieved near-zero transaction mismatches. Post-project retrospectives helped me refine my time estimation techniques and improve resource planning​