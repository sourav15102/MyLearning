### **1. `@SpringBootApplication`**

#### **Explanation**:

`@SpringBootApplication` is a meta-annotation that combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`. It marks the entry point of a Spring Boot application, enabling component scanning, configuration, and auto-configuration of the application context based on the classpath dependencies.

#### **Example**:

```java
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

#### **Interview Tip**:

- **Q**: Explain the significance of the annotations combined in `@SpringBootApplication`?
- **A**: `@Configuration` defines bean configurations, `@EnableAutoConfiguration` enables auto-configuration based on dependencies, and `@ComponentScan` scans the package and sub-packages for Spring components.

---

### **2. `@EnableAutoConfiguration`**

#### **Explanation**:

This annotation enables Spring Boot's auto-configuration mechanism by scanning the `META-INF/spring.factories` file for classes to auto-configure based on dependencies and properties.

#### **Example**:

```java
@EnableAutoConfiguration
@Configuration
public class MyAppConfig {
    // Custom bean definitions
}
```

#### **Interview Tip**:

- **Q**: How can you disable or customize specific auto-configurations in Spring Boot?
- **A**: Use the `exclude` attribute in `@EnableAutoConfiguration` or define the property `spring.autoconfigure.exclude` in `application.properties`.

---

### **3. `@ContextConfiguration`**

#### **Explanation**:

Used for integration testing, `@ContextConfiguration` specifies how the Spring context is loaded in test classes, either using XML configuration files or Java-based configurations.

#### **Example**:

```java
@ContextConfiguration(classes = MyAppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MyIntegrationTest {
    @Autowired
    private MyService myService;

    @Test
    public void testService() {
        assertNotNull(myService);
    }
}
```

#### **Interview Tip**:

- **Q**: What is the difference between `@ContextConfiguration` and `@SpringBootTest`?
- **A**: `@ContextConfiguration` loads specific configurations for testing, while `@SpringBootTest` loads the full Spring Boot application context.

---

### **4. `@SpringApplicationConfiguration`**

#### **Explanation**:

This annotation was used in earlier Spring Boot versions for configuring integration tests. It has been deprecated in favor of `@SpringBootTest`.

#### **Example**:

```java
// Deprecated in Spring Boot 1.4
@SpringApplicationConfiguration(classes = MyAppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MyIntegrationTest {
    // Test logic
}
```

#### **Interview Tip**:

- **Q**: Why was `@SpringApplicationConfiguration` deprecated?
- **A**: It was replaced by `@SpringBootTest` to provide a unified and more flexible testing mechanism.

---

### **5. `@ConditionalOnBean`**

#### **Explanation**:

`@ConditionalOnBean` creates a bean only if specific beans are already present in the Spring context, enabling conditional configuration.

#### **Example**:

```java
@Configuration
public class MyConfig {
    @Bean
    @ConditionalOnBean(name = "dataSource")
    public MyRepository myRepository() {
        return new MyRepository();
    }
}
```

#### **Interview Tip**:

- **Q**: How do `@ConditionalOnBean` and `@ConditionalOnMissingBean` work together?
- **A**: `@ConditionalOnBean` ensures a bean is created if a specific bean exists, while `@ConditionalOnMissingBean` creates a fallback bean if the primary one is absent.

---

### **6. `@Qualifier`**

#### **Explanation**:

`@Qualifier` resolves ambiguity when multiple beans of the same type exist by specifying the bean name to be injected.

#### **Example**:

```java
@Component
public class MyService {
    @Autowired
    @Qualifier("secondaryDataSource")
    private DataSource dataSource;
}
```

#### **Interview Tip**:

- **Q**: When is `@Qualifier` necessary?
- **A**: When multiple beans of the same type are available, `@Qualifier` specifies which one to inject.

---

### **7. `@Async`**

#### **Explanation**:

`@Async` allows methods to execute asynchronously in a separate thread, improving application performance by offloading time-consuming tasks.

#### **Example**:

```java
@Service
public class NotificationService {
    @Async
    public void sendEmail(String email) {
        // Email sending logic
    }
}
```

#### **Interview Tip**:

- **Q**: How do you configure custom thread pools for `@Async`?
- **A**: Define a `TaskExecutor` bean and annotate it with `@EnableAsync`.

---

### **8. `@RestController` vs `@Controller`**

#### **Explanation**:

- `@Controller` is used for handling web requests and rendering views.
- `@RestController` combines `@Controller` and `@ResponseBody`, simplifying the development of RESTful APIs.

#### **Example**:

```java
@RestController
@RequestMapping("/api")
public class MyRestController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
```

#### **Interview Tip**:

- **Q**: When should you use `@RestController` instead of `@Controller`?
- **A**: Use `@RestController` for REST APIs and `@Controller` for applications requiring view rendering.

---

### **9. `@Conditional`**

#### **Explanation**:

`@Conditional` allows conditional bean registration based on custom logic by implementing the `Condition` interface.

#### **Example**:

```java
@Configuration
@Conditional(MyCondition.class)
public class MyConfig {
    @Bean
    public MyBean myBean() {
        return new MyBean();
    }
}
```

#### **Interview Tip**:

- **Q**: How do you create a custom condition?
- **A**: Implement the `Condition` interface and override the `matches` method to define your logic.

---

### **10. `@Transactional`**

#### **Explanation**:

`@Transactional` ensures that a method executes within a transactional context, enabling rollback on failures.

#### **Example**:

```java
@Service
public class MyService {
    @Transactional
    public void performTransaction() {
        // Transactional logic
    }
}
```

#### **Interview Tip**:

- **Q**: What are different propagation behaviors in `@Transactional`?
- **A**: Propagation types like `REQUIRED`, `REQUIRES_NEW`, and `MANDATORY` control how transactions are managed across method calls.

---

Let me know if you’d like further elaboration or examples on any annotation!