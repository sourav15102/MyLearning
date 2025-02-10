### Story: Building a Notification System with SQS and SNS

**Situation:**  
At Dalhousie University, course availability updates were already being published to an **SNS topic** managed by the university, notifying various systems about changes. However, there was no direct mechanism to notify students in real time when a seat became available in a course. To bridge this gap, I designed and implemented a **course-specific notification system** that leveraged SQS and SNS to deliver updates directly to students’ emails.

**Task:**  
My task was to create a scalable and efficient service that subscribed to Dalhousie’s existing SNS topic, processed course updates, and sent targeted notifications to students subscribed to specific courses. The system needed to handle high traffic during peak registration periods without delays or failures.

**Action:**

1. **Subscribing to Dalhousie’s SNS Topic:**
    
    - I set up an **SQS queue** to subscribe to the university's SNS topic for course updates.
    - This ensured that all course-related messages (e.g., availability changes) were reliably stored in the SQS queue for further processing, decoupling my service from the Dalhousie system.
2. **Notification Service Using Long Polling:**
    
    - Built a **notification service** that used **long polling** to listen for messages from the SQS queue.
    - When a course availability update was detected, the service retrieved the message and identified the course it referred to.
3. **Publishing to Course-Specific Topics:**
    
    - For each course, I created an **SNS topic** to manage student subscriptions.
    - The notification service published the course update to the relevant SNS topic, ensuring only students subscribed to that course received an email notification.
4. **Student Subscription Management:**
    
    - Designed an interface where students could subscribe or unsubscribe from specific course topics.
    - Used SNS’s built-in capabilities to handle subscription confirmations and manage student email preferences securely.
5. **Handling High Traffic and Failures:**
    
    - Configured the SQS queue with a **dead-letter queue (DLQ)** to capture unprocessed messages, ensuring no updates were lost during failures.
    - Used **CloudWatch alarms** to monitor the queue length and scaled the notification service dynamically to handle surges during registration periods.

**Result:**

- Enabled real-time notifications for students, allowing them to receive **immediate alerts** about course availability.
- The system handled **thousands of course updates daily** during peak registration, with no delays or message loss.
- Students appreciated the ability to subscribe to specific courses, significantly improving their experience with course registration.

---

### Why This Works:

- **Technically Correct:** Demonstrates how SQS subscribes to an SNS topic and uses long polling, DLQs, and scaling effectively.
- **Highlights Impact:** Focuses on improving user experience (students) and solving a practical problem.
- **Scalable Design:** Showcases the ability to build a robust system leveraging AWS services.

Does this align with your project and expectations? Let me know if it needs tweaks!

---
---

SNS: https://datavalley.ai/aws-sns-interview-questions/?srsltid=AfmBOorVc6-50eSTRDajPe9fFq_kg6FIyjIDtoYPNpTw6COTw9clfsFM

SQS: https://datavalley.ai/aws-sqs-interview-questions-and-answers/?srsltid=AfmBOoq5uq-JjFEMrE_sH3lKmNYZNORTTPOvlSvPPkE9J2QQMYVVJYtw

