> Regression Testing is **a type of testing in the software development cycle that runs after every change to ensure that the change introduces no unintended breaks**.

> A stub is a simple piece of code that provides a placeholder response for a module that isn't yet available.

> Drivers are used when you're testing a low-level module in isolation, before the higher-level modules are ready. The driver acts as a temporary replacement for the higher-level module.
> Driver calls the low level modules and passes it the test data.

>  Mock vs Stub: 
>  Stub: Replaces dependencies; outputs pre-programmed outputs; 
>  Mocks: Replaces dependencies; outputs pre-programmed outputs; Verifies interaction; number of arguments, type of arguments, how many times method was being called.
### Good Unit test:
1. Isolated
2. Readable
3. Test one particular thing

> Mocking frameworks like Mockito typically work by creating _proxy objects_ of the class you want to mock. These proxies intercept method calls to _instances_ of the class, allowing you to define mock behavior.


Types:
1. Unit Testing
2. Integration Testing
3. End-to-End (E2E) Testing
4. Regression Testing:
5. Performance Testing
	1. Load Testing
	2. Stress Testing
	3. Endurance Testing
6. Security Testing

Questions:
```txt

When asked about testing, structure your answer like this:

1. **Types of Testing:** "I'm familiar with various testing types, including unit, integration, end-to-end, regression, performance, and security testing. My primary focus has been on unit, integration, and end-to-end testing, as these are most directly related to development."
    
2. **Unit Testing:** "I wrote unit tests for most of the code I developed, using JUnit and Mockito. I ran these tests frequently in my IDE and as part of the build process (Maven/Gradle). I aimed for high code coverage with my unit tests."
    
3. **Integration Testing:** "I performed integration testing on a development environment. I used Spring Test/REST Assured (or similar tools) to test the interactions between different components. These tests were automated and run as part of our CI/CD pipeline."
    
4. **End-to-End Testing:** "We had end-to-end tests that were run on a staging environment. We used Selenium/Cypress (or similar tools) to automate these tests, simulating user flows. These were also integrated into our CI/CD pipeline."
    
5. **Regression Testing:** "Regression tests were run automatically by our CI/CD system after each commit to ensure that new code didn't break existing functionality."
    
6. **CI/CD (Jenkins):** "Our CI/CD pipeline, managed by Jenkins, automated the execution of unit, integration, and end-to-end tests. This allowed us to catch issues early and ensure code quality."
    
7. **Branching Strategy:** "We used a branching strategy like Gitflow, where features were developed on feature branches, tested, and then merged into a development branch. Integration tests were run on the development branch. After successful integration tests, the code was merged into the main branch, and end-to-end and regression tests were run on the staging environment before deployment to production."
    
```
### Test the exceptions
- Assert the status code and response bodies.
```java
import org.junit.Test;

public class MyClassTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInput() {
        MyClass myClass = new MyClass();
        myClass.processInput(null); // This should throw IllegalArgumentException
    }
}

class MyClass {
    public void processInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        // ... process input ...
    }
}
```
- It only checks for the _exact_ exception type. If a subclass of the expected exception is thrown, the test will fail.
- You can't perform any assertions on the exception itself (e.g., check the error message).

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyClassTest {

    @Test
    void testInvalidInput() {
        MyClass myClass = new MyClass();

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            myClass.processInput(null);
        });

        assertEquals("Input cannot be null", thrown.getMessage()); // Assert on the exception message
    }
}


class MyClass {
    public void processInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        // ... process input ...
    }
}
```
- While you specify the exception type, `assertThrows` will also pass if a _subclass_ of that exception is thrown. If you need to check for the _exact_ type, you can use `assertSame` on the class of the thrown exception.
- You can now capture the actual thrown exception and perform assertions on it (like checking the error message, as shown in the example).

### Testing Asynchronous functions
- assertTimeout()
```java
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

@Test
void testAsyncOperationWithTimeout() {
    MyAsyncClass myAsyncClass = new MyAsyncClass();

    assertTimeout(Duration.ofSeconds(5), () -> {
        String result = myAsyncClass.asyncMethod(); // Call the async method
        // ... assertions on the result ...
    });
}

class MyAsyncClass {
    public String asyncMethod() {
        // Simulate an asynchronous operation (e.g., a network call)
        try {
            Thread.sleep(3000); // Simulate a 3-second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
        }
        return "Async Result";
    }
}
```
- `assertTimeout()` takes a `Duration` and a lambda expression. If the code within the lambda doesn't complete within the specified duration, the test fails.

- CompletableFuture
```java
import org.junit.jupiter.api.Test;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import static org.junit.jupiter.api.Assertions.*;

@Test
void testAsyncOperationWithCompletableFuture() throws ExecutionException, InterruptedException, TimeoutException {
    MyAsyncClass myAsyncClass = new MyAsyncClass();

    CompletableFuture<String> future = myAsyncClass.asyncMethodCompletableFuture(); // Returns CompletableFuture

    String result = future.get(5, TimeUnit.SECONDS); // Get the result with a timeout

    assertEquals("Async Result", result);
}

class MyAsyncClass {
    public CompletableFuture<String> asyncMethodCompletableFuture() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Async Result";
        });
    }
}
```
- The asynchronous method returns a `CompletableFuture`. The test can then use methods like `get()` (with a timeout) to retrieve the result. This allows you to control the timeout and handle potential exceptions (`TimeoutException`, `ExecutionException`).

- Awaitility
```java
import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;
import static org.awaitility.Awaitility.*;
import static java.util.concurrent.TimeUnit.*;
import static org.hamcrest.Matchers.*;

@Test
void testAsyncOperationWithAwaitility() {
    MyAsyncClass myAsyncClass = new MyAsyncClass();

    myAsyncClass.startAsyncOperation(); // Start the async operation

    await().atMost(5, SECONDS).until(() -> myAsyncClass.isOperationComplete(), is(true)); // Wait and check

    String result = myAsyncClass.getResult();
    assertThat(result, containsString("Result")); // Assert on the result
}

class MyAsyncClass {
    private volatile boolean operationComplete = false;
    private volatile String result = null;

    public void startAsyncOperation() {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                result = "Some Result";
                operationComplete = true;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public boolean isOperationComplete() {
        return operationComplete;
    }

    public String getResult() {
        return result;
    }
}
```
- Awaitility allows you to wait for a condition to be met before continuing the test.
- Always use timeouts when testing asynchronous operations to prevent tests from hanging.
- Awaitility's `await().until()` method is designed specifically for this purpose



### Integration testing:
**Integration Testing Approaches:**
Integration testing focuses on testing the interaction between different modules or components of a system. Here's a breakdown of common approaches:

- **Big-Bang Integration:**
    - **Description:** All modules are developed independently and then integrated all at once.
    - **Pros:** Can be quicker to set up initially, as you don't need to create stubs or drivers (explained below)
    - **Cons:** Very difficult to debug when failures occur because you don't know which interaction is causing the problem. High risk and generally not recommended for complex systems.
- **Top-Down Integration:**
    - **Description:** Testing starts with the top-level modules and works its way down to the lower-level modules. High-level modules are tested first, and stubs are used to simulate the behavior of lower-level modules that haven't been integrated yet. A stub is a simple piece of code that provides a placeholder response for a module that isn't yet available.
    - **Pros:** Good for revealing major design flaws early on. You get a working high-level skeleton of the system early.
    - **Cons:** Requires creating many stubs, which can be time-consuming. Lower-level, critical modules might not get tested as thoroughly early in the process.
- **Bottom-Up Integration:*
    - **Description:** Testing starts with the lowest-level modules and works its way up to the higher-level modules. Lower-level modules are tested first, and drivers are used to simulate the behavior of higher-level modules that haven't been integrated yet. A driver is a piece of code that calls the module being tested and passes it test data.
    - **Pros:** Good for testing reusable components thoroughly. Reduces the risk of surprises later in the development cycle.
    - **Cons:** Requires creating drivers. You don't get a working end-to-end system until the very end, so you can't demonstrate progress as easily.
- **Sandwich (Hybrid) Integration:*
    - **Description:** A combination of top-down and bottom-up approaches. Some modules are integrated from the top down, while others are integrated from the bottom up. This is often used in large, complex projects.
    - **Pros:** Attempts to leverage the advantages of both top-down and bottom-up approaches.
    - **Cons:** Can be more complex to manage.
- **Spine Integration:**
    - **Description:** The 'spine' of the application (usually the database and core functionality) is integrated first, and then other features are added incrementally.
    - **Pros:** Provides a solid foundation early on.
    - **Cons:** Requires careful planning to identify the core 'spine'.

### MockBean vs Mockito
### MockMVC for Testing Controller in Spring Boot
**Why MockMvc is Preferred for Controller Testing (Unit/Integration):**
- **Isolation:** MockMvc allows you to test the controller in a simulated web environment.
- **Control:** You have fine-grained control over the request and response. You can set headers, parameters, and request bodies.
- **Dependency Injection:** MockMvc works seamlessly with Spring's dependency injection. You can mock or stub out the service layer dependencies using @MockBean or other mocking frameworks (like Mockito) to isolate the controller's logic.
Let's say you have a UserController with a createUser endpoint:
```java
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
```

Here's how you might test it:

```java
@WebMvcTest(UserController.class) // Important: Only load the UserController context
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean  // Mocks the UserService dependency
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper; // For converting objects to JSON

    @Test
    public void createUser_ValidInput_ReturnsCreatedUser() throws Exception {
        // Arrange
        User newUser = new User("John Doe", "john.doe@example.com");
        User createdUser = new User("John Doe", "john.doe@example.com");
        createdUser.setId(1L); // Simulate the database assigning an ID

        when(userService.createUser(any(User.class))).thenReturn(createdUser); // Mock the service call

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser))) // Convert object to JSON
                .andExpect(status().isCreated()) // Verify HTTP status code
                .andExpect(jsonPath("$.id", is(1))) // Verify the ID in the JSON response
                .andExpect(jsonPath("$.name", is("John Doe"))) // Verify the name
                .andExpect(jsonPath("$.email", is("john.doe@example.com"))); // Verify the email

        verify(userService, times(1)).createUser(any(User.class)); // Verify the service method was called
    }
}
```

### **TestRestTemplate:**
TestRestTemplate is used for full-fledged integration tests where you want to test the entire application, including the web server. It starts a real web server and makes actual HTTP requests to your application. This is useful for testing the end-to-end flow of your application


**When to Use TestRestTemplate vs. MockMvc:*
- **MockMvc:** Use for focused unit/integration tests of individual controllers. It's faster and more isolated. You mock out the service layer.
- **TestRestTemplate:** Use for end-to-end integration tests where you want to test the entire application stack. You don't typically mock out the service layer (or any other layers).

Use:
**Key Changes When Using TestRestTemplate:**
8. **Remove @WebMvcTest and Add @SpringBootTest with webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT:**
    - @WebMvcTest(UserController.class) is specific to MockMvc and tells Spring to only load the web layer. We need to load the entire application context for TestRestTemplate.
    - @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) tells Spring Boot to start a full application context, including a web server, on a random port. This is necessary because TestRestTemplate makes real HTTP requests.
9. **Replace MockMvc with TestRestTemplate:**
    - Instead of autowiring MockMvc, you'll autowire TestRestTemplate. Spring Boot provides a pre-configured TestRestTemplate for you to use in your tests.
    - @Autowired private TestRestTemplate restTemplate;
10. **Remove @MockBean:**
    - Since you're testing the entire application stack, you generally don't mock out the service layer. You want the real UserService to be used. (In some cases, you might mock out external dependencies, but not typically the core service layer).
11. **Make a Real HTTP Request:**
    - Instead of using mockMvc.perform(), you'll use methods on TestRestTemplate like restTemplate.postForEntity(), restTemplate.getForEntity(), etc., to send actual HTTP requests to your application.
    - You'll need the base URL of your running application, which you can get from the @LocalServerPort annotation
    
**Example (Illustrative - Not Complete Code):**
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Full application context
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;  // Use TestRestTemplate

    @LocalServerPort
    private int port; // Get the random port

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createUser_ValidInput_ReturnsCreatedUser() throws Exception {
        // Arrange
        User newUser = new User("John Doe", "john.doe@example.com");
        String url = "http://localhost:" + port + "/users"; // Construct the URL

        // Act
        ResponseEntity<User> response = restTemplate.postForEntity(url, newUser, User.class); // Make the HTTP request

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED); // Check status code
        assertThat(response.getBody().getName()).isEqualTo("John Doe"); // Check the response body
        // ... other assertions
    }
}
```


### API Testing:
Here are some common types of API tests and what they verify
12. **Functional Testing:**
    - **Purpose:** Verifies that the API functions correctly according to its specifications.
    - **What it Verifies:**
        - **Correctness:** Does the API return the expected results for valid inputs?
        - **Error Handling:** Does the API handle invalid inputs and error conditions gracefully? Does it return appropriate error codes and messages?
        - **Data Validation:** Does the API validate data correctly before processing it?
13. **Performance Testing:**
    - **Purpose:** Evaluates the API's performance under various conditions.
    - **What it Verifies:**
        - **Response Time:** How long does it take for the API to respond to a request?
        - **Throughput:** How many requests can the API handle per second?
        - **Scalability:** Can the API handle increasing loads without performance degradation?
        - **Stress Testing:** How does the API behave under extreme load conditions?
14. **Security Testing:**
    - **Purpose:** Identifies security vulnerabilities in the API.
    - **What it Verifies:**
        - **Authentication:** Does the API correctly authenticate users and prevent unauthorized access?
        - **Authorization:** Does the API correctly authorize users to access specific resources?
        - **Input Validation:** Does the API properly validate inputs to prevent injection attacks (e.g., SQL injection, cross-site scripting)?
        - **Data Encryption:** Is sensitive data encrypted in transit and at rest?
        - **Rate Limiting:** Does the API implement rate limiting to prevent abuse?
15. **Reliability Testing:**
    - **Purpose:** Checks the API's stability and reliability.
    - **What it Verifies:**
        - **Error Rates:** How often does the API return errors?
        - **Recovery:** Can the API recover from failures gracefully?
        - **Availability:** How often is the API available and responsive?
16. **Negative Testing:**
    - **Purpose:** Checks how the API behaves with invalid/unacceptable input
    - **What it Verifies:**
        - **Error Codes:** That appropriate error codes are returned for invalid input
        - **Error Messages:** That the error messages are meaningful and helpful.
        - **Security:** That no sensitive data is leaked when invalid input is provided.


### Input Validation API testing
We need to perform input sanitization, to prevent server from attacks
17. Command injection
18. SQL injection
Assertion:
- Other than just checking the inputs we need to verify that the system is intact.
- No side effects: Monitor System Logs, Processes

### Sanitization:
19. **Whitelist Validation (Preferred):**
    - **Description:** Define a strict set of allowed characters or patterns for each input field. Reject any input that doesn't conform to the whitelist
20. **Blacklist Validation (Less Secure):**
    - **Description:** Identify a set of characters or patterns that are known to be dangerous and remove or escape them from the input.
21. **Data Type Validation:**
    - **Description:** Ensure that the input is of the correct data type (e.g., integer, string, date).
22. **Length Validation:**
    - **Description:** Ensure that the input does not exceed the maximum allowed length.
23. **Encoding:**
    - **Description:** Encode the input to prevent it from being interpreted as code.

### Prevention:
- SQL Injection:
	**Parameterized Queries (Prepared Statements): The Gold Standard for Preventing SQL Injection**
	**How They Work:**
	1. **Prepare the Statement:** You create a SQL query with placeholders for the data. The database parses and compiles the query, creating an execution plan. This happens only once.
	2. **Bind the Parameters:** You provide the actual data for the placeholders. The database treats the data as data, not as SQL code.
	3. **Execute the Query:** The database executes the prepared statement with the bound parameters.
	**Why They Prevent SQL Injection:**
	- **Data is Never Interpreted as Code:** Because the database knows that the placeholders represent data, it never attempts to interpret the data as SQL code. 
	- **Automatic Escaping/Quoting:** The database driver automatically handles the necessary escaping or quoting of the data to ensure that it is safe to use in the query.

- **Cross-Site Scripting (XSS):**
	- XSS attacks occur when an attacker is able to inject malicious code (typically JavaScript) into a web page that is viewed by other users
	- **Output Encoding:** Encoding is the process of converting characters into a different representation to prevent them from being interpreted as code.
- **Example (HTML Encoding):**
	```java
	String username = request.getParameter("username");
	String encodedUsername = StringEscapeUtils.escapeHtml4(username); // Use a library like Apache Commons Text
	out.println("Hello, " + encodedUsername + "!");
	```


## Spring Boot Testing

### Unit Testing:
**1. Unit Tests**
- **Purpose:** To test individual units of code (e.g., methods, classes) in isolation. The goal is to verify that each unit of code behaves correctly according to its specification.
- **Scope:** Focuses on testing a small, specific piece of code. Dependencies are typically mocked or stubbed out.
- **Speed:** Unit tests should be fast to execute.
- **Key Annotations:**
    - @Test: Marks a method as a test method (from JUnit).
    - @BeforeEach (or @Before in JUnit 4): Marks a method that should be executed before each test method. Used for setting up test data or initializing mocks.
    - @AfterEach (or @After in JUnit 4): Marks a method that should be executed after each test method. Used for cleaning up resources.
    - @ExtendWith(MockitoExtension.class) (or @RunWith(MockitoJUnitRunner.class) in JUnit 4): Enables Mockito for creating and injecting mocks. (Not strictly required, but very common when unit testing).
    - @Mock: Creates a mock object using Mockito.
    - @InjectMocks: Injects the mocks into the class under test.
    - assert*() methods (e.g., assertEquals(), assertTrue(), assertNotNull()): JUnit assertions for verifying expected results.

- **Example:**
 ```java
 @ExtendWith(MockitoExtension.class)
class MyServiceTest {

    @Mock
    private MyRepository myRepository;

    @InjectMocks
    private MyService myService;

    @BeforeEach
    void setUp() {
        // Setup code before each test
    }

    @Test
    void testGetData() {
        when(myRepository.findById(1L)).thenReturn(Optional.of(new MyEntity("test")));

        String result = myService.getData(1L);

        assertEquals("test", result);
        verify(myRepository, times(1)).findById(1L);
    }
}
```
Alternative with @Mock and  @InjectMock
```java
    void testProcessData() {
        // 1. Create a mock of the dependency
        DataProcessor dataProcessorMock = Mockito.mock(DataProcessor.class);

        // 2. Define the behavior of the mock (stubbing)
        Mockito.when(dataProcessorMock.process(Mockito.anyString())).thenReturn("Processed Data");

        // 3. Inject the mock into the class under test (MyService)
        MyService myService = new MyService(dataProcessorMock); // Constructor injection

        // 4. Call the method being tested
        String result = myService.processData("Input Data");

        // 5. Assertions
        assertEquals("Processed Data", result);

        // 6. Verify interactions (optional, but good practice)
        Mockito.verify(dataProcessorMock).process("Input Data"); // Check that process() was called with the correct argument
    }
}
```
24. **Create Mocks:** You use @Mock to create mock objects for all the dependencies of the class you want to test.
25. **Create the System Under Test:** You use @InjectMocks to create an instance of the class you want to test.
26. **Mockito Injects the Mocks:** Mockito automatically injects the @Mock objects into the @InjectMocks instance. It looks for constructor parameters, fields, or setter methods that match the types of the mocks.

### Integration Tests
**2. Integration Tests**
- **Purpose:** To test the interaction between different components or modules of the application. The goal is to verify that the components work together correctly.
- **Scope:** Tests the interaction between multiple components, but may still mock external dependencies.
- **Speed:** Integration tests are typically slower than unit tests.
- **Key Annotations:**
    - @SpringBootTest: Indicates that this is a Spring Boot integration test. It loads the full application context (or a slice of it).
    - @AutoConfigureMockMvc: Configures MockMvc for testing web endpoints (REST controllers). This is used when you want to test the controller layer without starting a full web server.
    - @Autowired: Used to inject beans from the Spring application context into the test class.
    - @MockBean: Replaces an existing bean in the Spring application context with a Mockito mock. Useful for isolating components and controlling the behavior of dependencies.
    - @DataJpaTest: Configures a Spring Boot test that focuses on JPA components. It sets up an in-memory database and configures JPA repositories. Useful for testing the data access layer.
    - @Test: Marks a method as a test method.
    - assert*() methods: JUnit assertions for verifying expected results.
- **Example (Testing a REST Controller with MockMvc):**
```java
@SpringBootTest
@AutoConfigureMockMvc
class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyService myService;

    @Test
    void testGetHello() throws Exception {
        when(myService.getHello()).thenReturn("Hello, Mocked!");

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Mocked!"));

        verify(myService, times(1)).getHello();
    }
}
```
```java
@DataJpaTest
class MyRepositoryTest {

    @Autowired
    private MyRepository myRepository;

    @Test
    void testFindById() {
        MyEntity entity = new MyEntity("test");
        myRepository.save(entity);

        Optional<MyEntity> found = myRepository.findById(entity.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("test");
    }
}
```



- **@SpringBootTest:** Loads the full Spring Boot application context. This means it starts up all the beans, configurations, and web server (if applicable). It's suitable for integration tests where you want to test the entire application or a large slice of it.
- **@DataJpaTest:** Loads a slice of the Spring Boot application context that is specifically related to JPA

- **@Mock (Mockito):**
    - **Scope:** Class-level. It creates a mock object within the scope of the test class.
    - **Purpose:** To create a mock object that you can use to control the behavior of a dependency within a unit test.
    - **Injection:** You typically use @InjectMocks to inject the @Mock into the class you're testing.
    - **Spring Context:** @Mock does not interact with the Spring application context. It's purely a Mockito feature.
- **@MockBean (Spring Boot):**
    - **Scope:** Application context-level. It replaces a bean in the Spring application context with a mock object.
    - **Purpose:** To replace a real bean in the Spring application context with a mock. This allows you to isolate components and control the behavior of dependencies within an integration test.
    - **Injection:** Spring automatically injects the @MockBean into any beans that depend on it.
    - **Spring Context:** @MockBean directly interacts with the Spring application context.


**3. End-to-End (E2E) Tests*
- **Purpose:** To test the entire application flow, from the user interface (or API endpoint) down to the database and back. The goal is to verify that the application works correctly as a whole. No use of mocks.
- **Scope:** Tests the entire application stack, including all components and dependencies.
- **Speed:** E2E tests are typically the slowest and most brittle type of test.
- **Key Annotations:**
    - @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT): Starts the full application context, including a web server, on a random port.
    - @LocalServerPort: Injects the port number that the web server is running on.
    - TestRestTemplate: A Spring class for making HTTP requests to a running application.
    - @Autowired: Used to inject beans.
    - @Sql: Can be used to execute SQL scripts before or after the test to set up or clean up the database.
    - assert*() methods: JUnit assertions.
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MyEndToEndTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MyRepository myRepository;

    @Test
    @Sql({"/data.sql"}) // Load data before the test
    void testCreateAndRetrieveData() {
        // Create some data via the API
        ResponseEntity<String> response = restTemplate.postForEntity("/data", "test", String.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Retrieve the data via the API
        String result = restTemplate.getForObject("/data/1", String.class);
        assertEquals("test", result);

        // Verify the data in the database
        Optional<MyEntity> entity = myRepository.findById(1L);
        assertThat(entity).isPresent();
        assertEquals("test", entity.get().getName());
    }
}
```

