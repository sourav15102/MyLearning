## 1. IoC Container (Inversion of Control Container)

In Spring, the **IoC Container** (also referred to as the Spring Container) is responsible for managing the **lifecycle** and **dependencies** of your application objects, called **beans**. Traditionally, we might say “Class A instantiates Class B” (A depends on B). With IoC, instead of having classes manually instantiate their dependencies, the framework (IoC container) creates and wires those dependencies together.

- **Inversion of Control**: The control over object creation is “inverted” from user code to the container.
- **Dependency Injection**: The container injects dependencies into classes that declare what they need.

### Example

```java
@Service
public class MyService {
    
    private final MyRepository myRepository;

    @Autowired  // or constructor injection
    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }
    
    // ...
}
```

- The `MyService` class does not create an instance of `MyRepository`. Instead, Spring’s IoC container automatically provides (injects) a `MyRepository` object.

#### **SQL Indexes**
- **What is an Index?**
    - A data structure that improves the speed of data retrieval.
- **Types of Indexes**:
    1. **Clustered Index**:
        - Data is stored in the order of the index.
        - A table can have only one clustered index.
    2. **Non-Clustered Index**:
        - Points to the actual data stored in the table.
        - A table can have multiple non-clustered indexes.
- **How to Create an Index**:
```java
CREATE INDEX idx_employee_name ON employees(name);
```

#### **Microservices Communication**
1. **API Gateway**:
    - Acts as a single entry point for all microservices.
    - Common gateways:
        - **Spring Cloud Gateway**.
        - **Zuul**.
2. **RestTemplate**:
    - Used for synchronous HTTP calls between microservices.
    - Example:
```java
@Service
public class ApiService {
    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getData() {
        return restTemplate.getForObject("http://service-b/data", String.class);
    }
}
```
- **Other Communication Mechanisms**:
	- **gRPC**: High-performance, lightweight framework.
	- **Message Queues**: Asynchronous communication (e.g., Kafka, RabbitMQ).
## 4. `@PersistenceContext`

`@PersistenceContext` is a JPA annotation used to **inject an `EntityManager`** into your class. The `EntityManager` interface is part of the Java Persistence API (JPA) and provides methods for performing CRUD operations on entities. In Spring, the container automatically configures and wires the entity manager factory, so you can simply declare:

```java
@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    // ...
}
```

- The `EntityManager` is tied to the **persistence context**, which is a set of managed entity instances in a particular scope (often a transaction).
- Any entity returned by the `EntityManager` is in a **managed state**—meaning changes to that entity are tracked and can be automatically persisted when the transaction commits.

---

## 5. `persist()`

`persist()` is a method on the **`EntityManager`** used to make a **new entity** persistent in the database.

- **Usage**: When you have a new entity instance with `null` or unset primary key, you call `persist(entity)` to add it to the persistence context.
- Once persisted, the entity is **managed**, and changes to it within the same transaction are tracked and will be flushed to the database on commit.

### Example

```java
Employee e = new Employee("Alice", "Developer");
entityManager.persist(e);  // e is now managed, and "insert" will occur on commit
```

If the `id` of `Employee` is set to auto-generation, the `persist()` call triggers the JPA provider to assign an ID.

---

#### Kinds of Injection
In Spring, **Dependency Injection (DI)** can be done primarily in three ways:
1. **Constructor Injection**
    - You provide dependencies via the class’s constructor.
    - **Preferred** in modern Spring because it makes classes easier to test (dependencies must be supplied at creation).
    
    ```java
    @Service
    public class MyService {
    
        private final MyRepository myRepository;
    
        // Constructor Injection
        public MyService(MyRepository myRepository) {
            this.myRepository = myRepository;
        }
    }
    ```
2. **Setter Injection**
    - You provide dependencies via setter methods.
    - Sometimes used when the dependency is **optional**.
    
    ```java
    @Service
    public class MyService {
    
        private MyRepository myRepository;
    
        // Setter Injection
        @Autowired
        public void setMyRepository(MyRepository myRepository) {
            this.myRepository = myRepository;
        }
    }
    ```
3. **Field Injection**
    - You directly annotate the class field with `@Autowired`.
    - Generally **not recommended** because it’s harder to test (you can’t create the object without reflection-based hacks). However, it’s quick for small demos or prototypes.
    
    ```java
    @Service
    public class MyService {
    
        @Autowired
        private MyRepository myRepository;
    
        // ...
    }
    ```
## 2. IoC Container Implementations: BeanFactory and ApplicationContext

**Inversion of Control (IoC)** is the principle where an external system (the **IoC container**) is responsible for **instantiating**, **configuring**, and **managing** objects (Spring beans).

- **`BeanFactory`**
    - The most basic IoC container in Spring.
    - Provides basic DI features.
    - Used less frequently in modern applications because you typically want advanced features from ApplicationContext.
- **`ApplicationContext`**
    - Extends `BeanFactory`.
    - Provides enterprise-level functionality such as event-publishing, internationalization, and more.
    - When you start a Spring Boot app, under the hood, it creates an `ApplicationContext` (often `AnnotationConfigApplicationContext` or `AnnotationConfigServletWebServerApplicationContext` in web apps).

## 3. If Multiple Child Classes Exist, Which Will Be Injected?

If you have multiple beans of the same **type** or **interface**, Spring will not know which one to inject by default. You can resolve this ambiguity in a few ways:

1. **`@Primary`**
    
    - Annotate one bean as the primary candidate:
        
        ```java
        @Service
        @Primary
        public class PrimaryEmployeeService implements EmployeeService { ... }
        ```
        
    - Spring will choose this bean if no other qualifiers are specified.
2. **`@Qualifier`**
    
    - Assign a qualifier name to each bean and specify which one you want in the injection point:
        
        ```java
        @Service
        @Qualifier("firstImplementation")
        public class EmployeeServiceImpl1 implements EmployeeService { ... }
        
        @Service
        @Qualifier("secondImplementation")
        public class EmployeeServiceImpl2 implements EmployeeService { ... }
        
        @Autowired
        public MyService(@Qualifier("firstImplementation") EmployeeService employeeService) {
            this.employeeService = employeeService;
        }
        ```
        
3. **Bean names**
    
    - By default, a bean’s name is the class name in camelCase if you don’t specify a custom name. You can also refer to the bean by that name.

Without `@Primary` or `@Qualifier`, you’ll typically get a `NoUniqueBeanDefinitionException` if multiple beans of the same type exist.

## 4. JPA, Hibernate, and JDBC — What Are They, and How Do They Relate?

- **JDBC (Java Database Connectivity)**
    
    - A **low-level** Java API for interacting with relational databases.
    - You write SQL statements (e.g., `SELECT * FROM employees`) in strings and use `ResultSet` objects to parse the results.
    - Great control but very **verbose**.
- **JPA (Java Persistence API)**
    
    - A **specification** (not an implementation) for **Object Relational Mapping (ORM)** in Java.
    - Defines annotations (`@Entity`, `@Id`, etc.) and standard interfaces (`EntityManager`, `EntityTransaction`).
    - It’s just an interface/contract. It does **not** provide the actual ORM logic.
- **Hibernate**
    
    - A popular **implementation** (or provider) of the JPA specification.
    - You code against JPA’s interfaces/annotations, and Hibernate does the heavy lifting: generating SQL, managing the persistence context, caching, etc.
    - Under the hood, Hibernate uses JDBC to actually execute SQL on your database.

---

## 5. Common Spring Annotations

There are many annotations in Spring. Let’s list some of the most common ones, grouped by their roles.
### Stereotype Annotations
- **`@Component`**
    - Marks a class as a Spring-managed bean (generic component).
- **`@Service`**
    - Specialization of `@Component` indicating that the class holds business logic.
- **`@Repository`**
    - Specialization of `@Component` indicating that the class interacts with the database or data storage (DAO layer). Also provides translation of persistence exceptions.
- **`@Controller`**
    - Specialization of `@Component` indicating an MVC controller (for web applications).
- **`@RestController`**
    - Combination of `@Controller` + `@ResponseBody`. All methods return JSON or XML responses by default.

### Configuration/Bean Annotations
- **`@Configuration`**
    - Marks a class that declares one or more `@Bean` methods; used by Spring to build the application context.
- **`@Bean`**
    - Marks a method that returns a bean to be managed by Spring (used mostly within `@Configuration` classes).

### Dependency Injection Annotations
- **`@Autowired`**
    - Instructs Spring to inject a matching bean into this variable/constructor/setter.
- **`@Qualifier`** and **`@Primary`**
    - Used to resolve ambiguity when multiple beans of the same type are present.

### Spring Boot Annotationn
- **`@SpringBootApplication`**
    - Combination of `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.
    - Main entry point for Spring Boot applications.

### AOP & Transaction Annotations
- **`@Transactional`**
    - Indicates that a method/class runs within a database transaction (commit/rollback around the method execution).

### Web / MVC Annotations

- **`@RequestMapping`** (and specialized versions like `@GetMapping`, `@PostMapping`, `@PutMapping`, etc.)
    
    - Maps HTTP requests to handler methods in your controllers.
- **`@RequestBody`, `@PathVariable`, `@RequestParam`, etc.**
    
    - Bind parts of an HTTP request (body, path parameter, query parameter) to method arguments.

### JPA/Hibernate Annotations
- **`@Entity`**
    - Marks a class as a JPA entity (mapped to a DB table).
- **`@Id`**
    - Marks the primary key field of an entity.
- **`@GeneratedValue`**
    - Instructs JPA/Hibernate to generate primary keys automatically.
- **`@Table`**, **`@Column`**
    - Specify details about the database table/column (name, length, etc.).

#### **4. Multithreading and Executors Framework**
- **Executor Framework**:
    - Simplifies thread management by providing pre-configured thread pools.
    - Common Implementations:
        - **FixedThreadPool**: Fixed number of threads.
        - **CachedThreadPool**: Dynamic thread allocation.
        - **SingleThreadExecutor**: One thread for sequential tasks.
- **Example: Using FixedThreadPool**:
```java
ExecutorService executor = Executors.newFixedThreadPool(3);

for (int i = 0; i < 5; i++) {
    executor.execute(() -> {
        System.out.println("Task executed by " + Thread.currentThread().getName());
    });
}

executor.shutdown();

```
- **Key Interfaces**:
	- `Executor`: Basic interface for executing tasks.
	- `ExecutorService`: Adds features like thread pools and task scheduling.


#### **Hibernate and Data Connection Pooling**
- **What is Connection Pooling?**
    - A technique where a pool of database connections is maintained and reused.
    - Reduces the overhead of creating new connections.
- **Spring Boot Default Connection Pool**:
    - Uses **HikariCP** for connection pooling.
    - Configured in `application.properties`:
```
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.hikari.maximum-pool-size=10
```

---

---

---

## EXTRAS
# Coming Back to: “Why do we need a transaction while saving an employee?” and “What does ‘managed’ mean in `merge()`?”

Now that we’ve covered the basics, let’s address these points in detail.

## Why Transactions Matter in the Repository (or Service) Class

1. **Atomic Operations**: Imagine you’re saving an employee and also saving their associated department or role in the same operation. If something fails halfway (like a constraint violation), you want **all or nothing**. That’s where transactions come in:
    
    - If an exception occurs, the transaction **rolls back** all changes—so the database is left in a consistent state.
    - If everything succeeds, the transaction **commits** your data changes.
2. **Isolation**: In multi-user scenarios, transactions ensure that incomplete data changes are not visible to other processes. For instance, if your code partially updates a record, it remains invisible to others until the transaction commits.
    
3. **Performance / Batch**: JPA (Hibernate) can queue up changes and only flush them to the database at commit time, reducing the overhead of frequent writes.
    

Even if you’re just saving one Employee row, it’s good practice to do it in a transaction:

- If your service method gets an exception, changes are rolled back automatically.
- JPA always needs a transaction for **write operations** (persist, merge, remove). If you don’t explicitly mark it, Spring will create one behind the scenes in many cases—but it’s better to be explicit or use higher-level annotation-driven transactions for clarity.

In short, **transactions** guarantee data **consistency** and **integrity**.

---

## What Does “Managed” Mean in `merge()`?

### Entity States in JPA

In JPA, an entity can be in one of several **states**:

1. **New (Transient)**: An object not associated with any persistence context. E.g., `new Employee(...)` with no database record.
2. **Managed**: The entity is associated with a persistence context. Any changes you make to it will be tracked and, upon commit, persisted (synced) to the database.
3. **Detached**: The entity was once managed, but the persistence context ended (e.g., the transaction ended, or it was explicitly detached). Changes to a detached entity are _not_ automatically saved.
4. **Removed**: The entity is scheduled for removal from the database.

### `merge(entity)` Operation

- When you call `merge()` on a **detached** or **new** entity:
    
    1. If there’s an existing record in the database with the same ID, Hibernate/JPA retrieves that **existing managed instance** from the persistence context (or creates a new managed one if not found).
    2. It **copies** the state from your **detached** entity into the **managed** entity.
    3. It returns the **managed entity**.
    4. Changes will be **persisted** on commit because the returned object is now tracked by JPA.
- The entity you pass in to `merge()` does **not** become managed itself. Instead, a new or existing entity in the context is updated and returned.
    

#### Example Scenario

1. You load an `Employee` from the database in one transaction. The transaction finishes, so `Employee` is now **detached**.
2. You modify some fields on that detached `Employee` object (e.g., set a new name or role).
3. In a new transaction, you call `merge(detachedEmployee)`.
4. Hibernate checks if there’s an existing `Employee` with `id=X` in the current persistence context. If not, it fetches it or creates it in memory.
5. It copies the changes from your detached object into that newly fetched or created managed instance.
6. The returned `Employee` from `merge()` is now **managed**, meaning further changes are tracked.
7. On commit, the changes are written to the database.

Hence, “managed” means **“being tracked by the current persistence context”** so that changes get synchronized with the database automatically at the end of the transaction.

---

## Final Takeaways

1. **Transactions** are essential for ensuring **data consistency** and **all-or-nothing** behavior in database operations. Even if saving a single record, wrapping it in a transaction is standard practice in JPA-based apps.
2. **`merge()`** is used to bring a **detached** entity (or a new entity with an ID) back under the management of the current persistence context. The key idea is that only **managed** entities will be synchronized with the database when a transaction commits.

Hopefully, this clarifies the “why” behind transactions and what “managed” means in JPA. If you still have follow-up questions, feel free to ask!