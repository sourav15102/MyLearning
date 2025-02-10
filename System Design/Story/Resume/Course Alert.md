### **Course Alert**

#### **Possible Interview Questions and Responses**
1. **What were the key features of the Course Alert tool?**
    - **Response**:
        - It allowed users to subscribe to courses and receive notifications for updates (e.g., schedule changes, deadlines).
        - Notifications were sent via **email and SMS** using **AWS SNS**.
        - Included a dashboard where users could manage subscriptions.
2. **How did you design the notification system?**
    - **Response**:
        - Implemented a pub-sub model using AWS SNS:
            1. Subscribed users were added to a topic in AWS SNS.
            2. When a course update occurred, the backend triggered a message to the topic, and all subscribers received notifications.
        - AWS Lambda was used to process events and send notifications to users.
3. **How did you use MongoDB in this project?**
    - **Response**:
        - MongoDB stored course information and user subscriptions.
        - Schema:
```
	{
    "courseId": "COURSE123",
    "subscribers": [
        { "userId": "USER1", "email": "user1@example.com", "phone": "+1234567890" },
        { "userId": "USER2", "email": "user2@example.com", "phone": "+9876543210" }
    ]
}			    
```

- **What challenges did you face with integrating AWS SNS and Lambda?**
    - **Response**:
        - Initially, there were latency issues when triggering notifications for large subscriber lists.
        - Resolved by batching notifications in Lambda functions and using parallel invocations.
- **How did you ensure cloud resource provisioning?**
    - **Response**:
        - Used **Terraform** to provision infrastructure, including:
            - AWS Lambda, RDS, and SNS topics.
        - Automated environment setup and teardown for development and production.