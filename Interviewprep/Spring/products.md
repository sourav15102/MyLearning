### 🌩️ **Common Spring Boot Cloud Products & Their Use Cases**

| **Tool/Product**                  | **Description / Use Case**                                                                                       |
| --------------------------------- | ---------------------------------------------------------------------------------------------------------------- |
| **Spring Cloud Gateway**          | Modern, non-blocking **API Gateway** built on **Reactor**; routes requests, applies filters, rate limiting, etc. |
| **Netflix Zuul**                  | Older, now legacy API Gateway (uses servlet stack); used for routing and filtering requests.                     |
| **Spring Cloud Config**           | Centralized **configuration management** (externalized configs from Git, etc.) for all services.                 |
| **Spring Cloud Eureka**           | **Service discovery** — apps register themselves, and clients can discover each other (Netflix OSS).             |
| **Spring Cloud Consul**           | Alternative to Eureka using **HashiCorp Consul** for service discovery and key/value config.                     |
| **Spring Cloud Bus**              | Propagates config changes via a **message broker** (like Kafka/RabbitMQ) to all services dynamically.            |
| **Spring Cloud Sleuth**           | Adds **tracing IDs** to logs for distributed tracing; integrates with Zipkin.                                    |
| **Spring Cloud Zipkin**           | Collects and visualizes **trace data** from Sleuth; helps diagnose latency in microservices.                     |
| **Spring Cloud OpenFeign**        | Declarative REST client — easily call other services with a simple interface + annotations.                      |
| **Spring Cloud Circuit Breaker**  | Handles failures gracefully; integrates with Resilience4j or Hystrix (fallbacks, retries).                       |
| **Spring Cloud LoadBalancer**     | Client-side **load balancing** alternative to Ribbon (used with Feign and RestTemplate).                         |
| **Spring Cloud Stream**           | Abstraction for **event-driven microservices** using messaging systems like Kafka, RabbitMQ.                     |
| **Spring Cloud Kubernetes**       | Integrates Spring Boot apps with **Kubernetes features** like ConfigMaps, Secrets, Service Discovery.            |
| **Spring Cloud AWS**              | Integrates with **Amazon AWS services** (S3, SNS, SQS, Secrets Manager, etc.).                                   |
| **Spring Cloud Vault**            | Integrates with **HashiCorp Vault** to manage secrets and secure credentials.                                    |
| **Spring Cloud Gateway + OAuth2** | For **securing routes** via authentication/authorization (e.g., JWT validation, token relays).                   |

---

### 🧠 When to Use What (Quick Guide):

* **Routing/API Gateway?** → Use **Spring Cloud Gateway**
* **Config management?** → Use **Spring Cloud Config**
* **Service discovery?** → Use **Eureka** or **Consul**
* **Inter-service calls?** → Use **OpenFeign**
* **Logging/tracing?** → Use **Sleuth + Zipkin**
* **Failure handling?** → Use **Circuit Breaker (Resilience4j)**
* **Event-driven apps?** → Use **Spring Cloud Stream**
