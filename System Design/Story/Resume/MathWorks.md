###  Managerial Tracking Tool Architecture**

#### **Purpose**
The managerial tracking tool was designed to assist managers in the EDG group by aggregating employee metrics, enabling threshold-based alerts, and generating daily reports. Unlike real-time systems, the dashboard was updated at the end of the day with processed data.

---

### **Architecture**
1. **Monolithic Architecture**:
    
    - Built as a single monolithic application using **Spring Boot**.
    - The entire codebase—including the backend logic, API handlers, database interactions, and cron job scheduling—resided in one deployable unit (e.g., a `.jar` or `.war` file).
2. **Backend**:
    
    - **Batch Data Processing**:
        - A **cron job** was scheduled to fetch and aggregate metrics from external sources (existing dashboards) at the end of each day.
        - Metrics were compared with preconfigured thresholds, and notifications were triggered for breaches.
    - **Threshold and Alert Management**:
        - Thresholds were stored in the database and accessed during batch processing.
        - Alerts were queued for delivery using AWS SNS or email services like SES.
    - **Report Generation**:
        - Generated daily reports in PDF or CSV format summarizing the metrics and alerts.
3. **Frontend**:
    
    - **Non-Real-Time Dashboard**:
        - The dashboard displayed aggregated data from the previous day, updated after each cron job execution.
        - Implemented using **Angular** with REST API integration for data retrieval.
4. **Database**:
    
    - **Primary Storage**:
        - A relational database (e.g., **PostgreSQL**) was used for:
            - Storing employee metrics, thresholds, and alert configurations.
            - Generating reports based on historical data.
    - **Time-Series Database Consideration**:
        - A **time-series database** (e.g., **InfluxDB**, **TimescaleDB**) could be a better fit if:
            - High-frequency metric updates were needed (even if only once per day).
            - The ability to efficiently query trends or historical patterns in metrics was required.
        - Time-series databases excel at managing metrics over time, enabling easy comparisons and trend analysis.

---

### **Cron Job Implementation**

A **cron job** automates tasks at scheduled intervals. In this case, the job was scheduled to execute at the end of each day.

1. **Using Spring Boot**:
    
    - Spring Boot provides a built-in mechanism for scheduling tasks with the `@Scheduled` annotation.
    - Example:
        
        ```java
        @Service
        public class MetricAggregator {
            
            @Scheduled(cron = "0 0 23 * * ?") // Runs every day at 11:00 PM
            public void processDailyMetrics() {
                // Step 1: Fetch metrics from APIs or database
                List<Metric> metrics = fetchMetrics();
        
                // Step 2: Compare with thresholds
                for (Metric metric : metrics) {
                    if (metric.getValue() > metric.getThreshold()) {
                        sendAlert(metric);
                    }
                }
        
                // Step 3: Generate and save daily report
                generateReport(metrics);
            }
        }
        ```
        
    - **Key Steps**:
        1. Fetch data from external APIs or the database.
        2. Compare metrics with stored thresholds.
        3. Generate a report and store it in the database or file storage (e.g., AWS S3).
2. **Deployment Considerations**:
    
    - Ensure the application runs continuously on a stable server (e.g., AWS EC2).
    - Use a **scheduler manager** like Quartz if finer-grained control over cron jobs is needed.

---

### **Thresholds and Alerts**
1. **Storing Thresholds**:
    - Store thresholds in a `thresholds` table in the database:
        ```sql
        CREATE TABLE thresholds (
            id SERIAL PRIMARY KEY,
            metric_name VARCHAR(255),
            threshold_value FLOAT,
            manager_id INT,
            alert_type ENUM('EMAIL', 'SMS'),
            created_at TIMESTAMP
        );
        ```
        
2. **Generating Alerts**:
    
    - During the cron job:
        - Fetch metrics and thresholds from the database.
        - Compare each metric’s value with its corresponding threshold.
        - For breaches, enqueue an alert for delivery.
3. **Alert Delivery**:
    
    - **AWS SNS** or a custom notification service was used to send alerts via email/SMS.

---

### **Database Considerations**

#### **Relational Database**:

- **When to Use**:
    - If the system doesn’t require frequent updates to metrics.
    - When relationships (e.g., manager-employee) are central to the system.
- **Advantages**:
    - Easy integration with reporting tools.
    - ACID compliance ensures consistent and reliable data storage.

#### **Time-Series Database**:

- **When to Use**:
    - If metrics are updated frequently, even daily.
    - If querying historical data trends (e.g., comparing this month’s metrics to last month’s) is a primary use case.
- **Advantages**:
    - Optimized for handling time-stamped data.
    - Better performance for trend analysis and retention policies.

#### **Hybrid Approach**:

- Use a relational database (PostgreSQL) for storing structured data like employees, thresholds, and alerts.
- Use a time-series database (e.g., InfluxDB) for storing metrics and enabling advanced trend queries.

---

### **Summary of Architecture**

1. **Monolithic System**:
    
    - Backend: Spring Boot with cron job for daily batch processing.
    - Frontend: Angular for a non-real-time dashboard updated once per day.
2. **Data Processing**:
    
    - Cron job fetches metrics, processes them against thresholds, and generates reports.
3. **Database**:
    
    - Relational DB for structured data (PostgreSQL).
    - Time-series DB (optional) for efficient handling of historical metrics.
4. **Notifications**:
    
    - Alerts sent via AWS SNS or a custom notification service.



### **Microservices Optimization**

**Question**: How did you optimize microservices with Redis caching and AWS SNS?

- **Response**:
    - **Context**:
        - The microservices were part of a backend system designed to handle data-intensive operations, such as retrieving and processing employee metrics.
        - The bottleneck was the repeated retrieval of frequently accessed data and inter-service communication delays.
    - **Optimization**:
        1. **Redis Caching**:
            - Added a Redis layer to store frequently queried data, such as user permissions and precomputed aggregates of metrics.
            - Reduced database calls by approximately 30%.
            - Cached entries were invalidated using a TTL (time-to-live) strategy to ensure consistency with the source data.
        2. **AWS SNS**:
            - Used AWS SNS for event-driven communication between microservices.
            - For example, when a user completed an action, the event was published to an SNS topic, and subscribing services received the update asynchronously.
    - **Impact**:
        - Response times improved significantly due to caching.
        - Asynchronous communication ensured the system remained decoupled and scalable.

**Follow-Up Question**: What were the challenges with caching dynamic data and ensuring message consistency in SNS?

- **Response**:
    - **Dynamic Data**:
        - Challenge: Keeping the cache consistent with frequent data updates.
        - Solution: Used a combination of TTL and event-driven cache invalidation. For example, when a new metric was added, an event was published to update the cache.
    - **SNS Message Consistency**:
        - Challenge: Handling duplicate or out-of-order messages.
        - Solution: Used idempotency tokens in message payloads to ensure the same message wasn’t processed multiple times.


### **Baseline Generation Tool**
The **Baseline Generation Tool** was designed to handle the upload and processing of Simulink models for clients. It automated the workflow of baseline generation and status tracking, ensuring efficiency and accuracy.
#### **Purpose**
- Automate the process of uploading Simulink models, running them, and generating baselines for clients.
- Provide a system to track the status of each request (e.g., pending, in-progress, completed).
#### **Architecture and Workflow**
1. **Data Upload**:
    - Clients uploaded Simulink models via a frontend.
    - Files were stored in **AWS S3**, and an event was triggered upon upload.
2. **Event-Driven Processing**:
    - **Kafka** acted as the messaging system.
        - Each upload generated a Kafka event containing metadata like model ID, S3 location, and client details.
    - Spring Boot workers (consumers) subscribed to Kafka topics, pulled the event, and initiated processing.
3. **Model Processing**:
    - The worker fetched the Simulink model from S3.
    - Used native MATLAB functions to run simulations and generate baselines.
    - Baselines were uploaded back to S3, and the metadata was updated in the database.
4. **Status Tracking**:
    - The **tracker** maintained the status of each request:
        - **Pending**: When the model was uploaded and an event was published to Kafka.
        - **In-Progress**: When a worker pulled the event and began processing the model.
        - **Complete**: When the baseline was successfully uploaded to S3, and the database was updated.
5. **Status Updates**:
	- Used **atomic transactions** to ensure status updates were accurate:
    - The database was updated at each stage using a transactional service in Spring Boot.

### **General Questions**
#### **1. How did you approach performance bottlenecks in your applications?**
- **Response**:
    - Used **profiling tools** like Spring Boot Actuator to identify slow database queries and CPU-intensive processes.
    - Optimized SQL queries:
        - Added materialized views for complex aggregations.
#### **2. What did you learn during your tenure at MathWorks?**
- **Response**:
    - **Technical Skills**:
        - Gained hands-on experience with Spring Boot, Redis caching, and AWS services.
        - Learned best practices for CI/CD pipelines and microservices deployment.
    - **Soft Skills**:
        - Improved teamwork and communication through Agile practices.
        - Learned to prioritize features and technical debt by understanding business needs.