
### **2. Monitoring and Identifying Bottlenecks**
#### **Where Monitoring Is Done**:

- **Monitoring Tools**:
    - **Prometheus + Grafana**: For real-time metrics and alerts.
    - **AWS CloudWatch**: To monitor infrastructure and application metrics (e.g., CPU, memory usage).
    - **Datadog** or **New Relic**: For full-stack monitoring, including APM (Application Performance Monitoring).
    - **Jaeger** or **Zipkin**: For distributed tracing in microservices.
#### **How Bottlenecks Are Identified**:

1. **Application-Level Bottlenecks**:
    - Use APM tools like Datadog or New Relic to trace API latency, slow queries, or inefficient code paths.
    - Example: Identify slow database queries using a profiler or query plan analysis.
2. **Infrastructure-Level Bottlenecks**:
    - Monitor CPU, memory, and disk usage on instances using Spring Boot actuator and Prometheus.
    - Detect network latencies or throttling using load balancer metrics.
3. **Service-Level Bottlenecks**:
    - Use distributed tracing tools (e.g., Jaeger) to identify bottlenecks in inter-service calls.
    - Example: Detecting that Service A is waiting too long for a response from Service B.

### **2. Monitoring and Identifying Bottlenecks**
#### **Where Monitoring Is Done**:
- **Monitoring Tools**:
    - **Prometheus + Grafana**: For real-time metrics and alerts.
    - **AWS CloudWatch**: To monitor infrastructure and application metrics (e.g., CPU, memory usage).
    - **New Relic**: For full-stack monitoring, including APM (Application Performance Monitoring).
    - **Jaeger** : For distributed tracing in microservices.

#### **How Bottlenecks Are Identified**:
1. **Application-Level Bottlenecks**:
    - Use APM tools like Datadog or New Relic to trace API latency, slow queries, or inefficient code paths.
    - Example: Identify slow database queries using a profiler or query plan analysis.
2. **Infrastructure-Level Bottlenecks**:
    - Monitor CPU, memory, and disk usage on instances using CloudWatch or Prometheus.
    - Detect network latencies or throttling using load balancer metrics.
3. **Service-Level Bottlenecks**:
    - Use distributed tracing tools (e.g., Jaeger) to identify bottlenecks in inter-service calls.
    - Example: Detecting that Service A is waiting too long for a response from Service B.

---

### **3. Handling Microservice Changes**
#### **Challenges**:
- **Backward Compatibility**: New changes in a microservice may break existing consumers.
- **Versioning**: Changes must be carefully managed to avoid downtime.
#### **Approaches**:
1. **Versioning**:
    - APIs are versioned to allow coexistence of old and new functionality.
    - Example: `GET /v1/resource` and `GET /v2/resource`.
4. **Canary Releases**:
    - Deploy the new version of a microservice to a small subset of users before full rollout.
5. **Blue-Green Deployment**:
    - A new version is deployed to a separate environment, and traffic is gradually switched from the old to the new version.
- 2. **Feature Toggles**:
    - Deploy new features behind a toggle, allowing gradual rollout.