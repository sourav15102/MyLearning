
### 1️⃣ `@SpringBootApplication` (Mandatory Annotation for Spring Boot)
✅ What it does?
    Marks the entry point of a Spring Boot application.
    Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan.
✅ How to explain in an interview?
"@SpringBootApplication is the core annotation in Spring Boot. It enables auto-configuration, component scanning, and acts as the main configuration class. It eliminates the need for XML configuration and lets Spring Boot automatically set up beans based on the classpath dependencies."
```java
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

> @Component: This annotation is the most generic annotation for any Spring-managed component. It is used to mark a class as a Spring bean that will be managed by the Spring container.
## **2️⃣ `@Configuration` (Defining Spring Beans)**
✅ **What it does?**
- This annotation is used to indicate that a class contains configuration methods for the application context
- Marks a class as a **Spring configuration class**.
- Allows defining **Spring Beans using `@Bean` methods** instead of XML configuration.
✅ **How to explain in an interview?**  
_"`@Configuration` is used to define Spring beans in a Java-based configuration class. It tells Spring that this class contains bean definitions, which can be created using `@Bean` methods. It replaces the traditional XML configuration."_
```java
@Configuration
public class AppConfig {
    @Bean
    public String sampleBean() {
        return "Hello, Spring!";
    }
}
```

## **3️⃣ `@ComponentScan` (Scanning for Components)**
✅ **What it does?**
- Instructs Spring to **scan specific packages** for Spring-managed components (`@Component`, `@Service`, `@Repository`, `@Controller`).
- By default, it scans the package where the **main class is located**.
✅ **How to explain in an interview?**  
_"`@ComponentScan` is used to tell Spring where to look for beans and components. By default, `@SpringBootApplication` already includes component scanning, but we can customize it to scan additional packages if needed."_
```java
@ComponentScan(basePackages = {"com.example.services", "com.example.repositories"})
public class AppConfig { }
```

## **4️⃣ `@EnableAutoConfiguration` (Spring Boot’s Magic)**
✅ **What it does?**
- Tells Spring Boot to **automatically configure beans** based on **classpath dependencies**.
- **Example:** If `spring-boot-starter-web` is on the classpath, it auto-configures **Tomcat, DispatcherServlet, and Jackson**.
✅ **How to explain in an interview?**  
_"`@EnableAutoConfiguration` allows Spring Boot to automatically configure beans based on the dependencies in the classpath. It eliminates the need for manual configuration and allows for a convention-over-configuration approach."_
```java
@Configuration
@EnableAutoConfiguration
public class MyApp { }
```


## **5️⃣ `@RestController` (REST API Controller)**
✅ **What it does?**
- Combines `@Controller` and `@ResponseBody`.
- Allows defining **RESTful APIs** where methods return **JSON responses instead of views**.
✅ **How to explain in an interview?**  
_"`@RestController` is a specialized version of `@Controller` that eliminates the need for `@ResponseBody`. It is used to build RESTful APIs where methods return JSON responses directly."_
```java
@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("/{id}")
    public String getUser(@PathVariable String id) {
        return "User ID: " + id;
    }
}
```


### 1️⃣ @Controller (MVC Controller)
✅ What it does?
    Marks a class as a Spring MVC Controller.
    Handles web requests and returns view templates (Thymeleaf, JSP, etc.).
    Works with Spring MVC (not REST APIs).
✅ How to explain in an interview?
"The @Controller annotation is used in Spring MVC applications to define controllers that handle web requests and return views. It works with View Resolvers like Thymeleaf or JSP to render HTML responses."

## **2️⃣ `@Service` (Business Logic Layer)**
✅ **What it does?**
- Marks a class as a **service component** in the **business layer**.
- Encapsulates **business logic** and interacts with the repository layer.
✅ **How to explain in an interview?**  
_"The `@Service` annotation marks a class as a business layer component, ensuring separation of concerns in the MVC architecture. It is used to handle business logic independently of controllers and repositories."_

## **3️⃣ `@Repository` (Data Access Layer)**
✅ **What it does?**
- Marks a class as a **Data Access Object (DAO)**.
- Handles **database interactions**.
- Provides **exception translation** (wraps SQL exceptions into Spring’s `DataAccessException`).
✅ **How to explain in an interview?**  
_"The `@Repository` annotation marks a class as a DAO component that interacts with the database. It also provides exception handling by converting SQL exceptions into Spring-specific exceptions."_

## **4️⃣ `@Autowired` (Dependency Injection)**
✅ **What it does?**
- Injects **Spring-managed beans automatically**.
- Can be used on **fields, constructors, or setters**.
✅ **How to explain in an interview?**  
_"The `@Autowired` annotation enables dependency injection in Spring by automatically resolving and injecting beans. It follows Spring’s IoC (Inversion of Control) principle to reduce manual wiring of dependencies."_

## **1️⃣ `@RequestMapping` (Mapping URLs to Controllers)**
✅ **What it does?**
- Maps **HTTP requests (GET, POST, PUT, DELETE, etc.)** to **specific methods** in a controller.
- Can be used at **class level** (applies to all methods) or **method level** (applies to a single API).
✅ **How to explain in an interview?**  
_"`@RequestMapping` is used to define an endpoint in a Spring Boot REST API. It can map an entire class to a base path or individual methods to specific HTTP verbs."_

## **2️⃣ `@RequestBody` (Binding Request JSON to Java Object)**
✅ **What it does?**
- Converts **incoming JSON request body into a Java object** using **Jackson**.
- Used in **POST, PUT requests** to **send data to the server**.
✅ **How to explain in an interview?**  
_"`@RequestBody` is used to map the JSON request body to a Java object in a REST API. Spring automatically converts JSON into a POJO using Jackson."_

## **3️⃣ `@ResponseBody` (Returning JSON Response)**
✅ **What it does?**
- Converts **Java object to JSON response** using Jackson.
- **`@RestController` includes `@ResponseBody` automatically**.
✅ **How to explain in an interview?**  
_"`@ResponseBody` tells Spring to return data as a JSON response instead of rendering a view. It is automatically applied when using `@RestController`."_

## **4️⃣ `@PathVariable` (Extracting Data from URL)**
✅ **What it does?**
- Extracts **variables from the URL path** and binds them to method parameters.
- Used for **RESTful APIs where the resource ID is part of the URL**.
✅ **How to explain in an interview?**  
_"`@PathVariable` extracts a variable from the URL path and passes it as a method parameter in a Spring Boot REST API."_

## **5️⃣ `@RequestParam` (Extracting Query Parameters from URL)**
✅ **What it does?**
- Extracts **query parameters** from the URL (`?key=value`).
- Used in **GET requests** for optional filtering, pagination, sorting.
✅ **How to explain in an interview?**  
_"`@RequestParam` extracts a query parameter from the URL and binds it to a method parameter. It is useful for filtering and pagination in REST APIs."_

### **`@Qualifier` and `@Primary` in Spring Boot**
✅ **`@Qualifier`** → Explicitly specifies which bean to inject when multiple beans of the same type exist.  
✅ **`@Primary`** → Marks one bean as the default when multiple beans of the same type exist.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("englishService")
class EnglishGreetingService implements GreetingService {
    public String greet() { return "Hello!"; }
}

@Component("spanishService")
class SpanishGreetingService implements GreetingService {
    public String greet() { return "Hola!"; }
}

@Component
class GreetingController {
    @Autowired
    @Qualifier("spanishService") // Explicitly selecting the Spanish service
    private GreetingService greetingService;

    public void sendGreeting() {
        System.out.println(greetingService.greet());
    }
}
```

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // Marks this as the default bean
class DefaultGreetingService implements GreetingService {
    public String greet() { return "Hi!"; }
}

@Component
class GreetingController {
    @Autowired
    private GreetingService greetingService; // No @Qualifier needed, @Primary bean is injected

    public void sendGreeting() {
        System.out.println(greetingService.greet());
    }
}
```

## @Profile
@Profile defines profiles for different environments or scenarios in your application, allowing you to configure different sets of beans, properties, or components for different runtime environments.