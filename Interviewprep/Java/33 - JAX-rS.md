### 2. What makes Spring Boot superior to JAX-RS?
A: 
1. Production ready
2. auto configuration
3. embedded server

#### how would one use actuator?
### Steps to Use Spring Boot Actuator

#### 1. Add the Actuator Dependency

Add the Actuator dependency to your `pom.xml` file:

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

```

```
# Expose all Actuator endpoints over HTTP
management.endpoints.web.exposure.include=*

```

### **Spring Cloud Integration**

- **Microservices Tooling**: Spring Cloud provides a suite of tools specifically designed for building and managing microservices. These tools include:
    - **Service Discovery**: Using Spring Cloud Netflix Eureka for service registration and discovery.
    - **Circuit Breakers**: Using Spring Cloud Netflix Hystrix for resilience and fault tolerance.
    - **Configuration Management**: Using Spring Cloud Config for external configuration management.
    - **API Gateway**: Using Spring Cloud Gateway for routing and filtering requests.
    - **Distributed Tracing**: Using Spring Cloud Sleuth and Zipkin for distributed tracing and monitoring.


The `@SpringBootApplication` annotation itself does not define beans directly. Instead, it combines three key annotations:

1. **@Configuration**: Indicates that the class can define bean methods.
2. **@EnableAutoConfiguration**: Automatically configures beans based on the classpath and existing beans.
3. **@ComponentScan**: Scans for components (like `@Service`, `@Repository`, etc.) in the specified package and registers them as beans.

**Entity**:

- **Persistability**: An entity can be stored in a database and retrieved later.
- **Persistent Identity**: Each entity has a unique identifier, often mapped to a primary key in the database.
- **Transactionality**: Entities participate in transactions, meaning changes to them can be committed or rolled back.
- **Granularity**: Entities should represent meaningful concepts in the application's domain model, not just primitive data types.