Spring Boot follows the principle of "convention over configuration.
Scalable, faster and production ready than Spring

1. What are the Features of Spring Boot?
- ****Auto-configuration –**** Spring Boot automatically configures dependencies by using ****@EnableAutoconfiguration**** annotation and reduces boilerplate code.
- ****Spring Boot Starter POM**** – These Starter POMs are pre-configured dependencies for functions like database, security, maven configuration etc.
- ****Spring Boot CLI (Command Line Interface)**** – This command line tool is generally for managing dependencies, creating projects and running the applications.
- ****Actuator –**** Spring Boot Actuator provides health check, metrics and monitors the endpoints of the application. It also simplifies the troubleshooting management.
- ****Embedded Servers –**** Spring Boot contains embedded servers like Tomcat and Jetty for quick application run. No need of external servers.

**Starter POMs:**
Starter POMs (Project Object Models) are a  Collection bundle of pre-configured dependencies we might need for specific use case.
Here's how they work:
1. **Bundled Dependencies:** Each starter POM contains a collection of related dependencies.
2. **Simplified Dependency Management:** Instead of adding individual dependencies one by one, you can simply add the starter POM to your project.
3. **Auto-Configuration Enablement:** Starter POMs also play a role in enabling auto-configuration.
Examples:
1. Web starter
2. test starter
3. Security starter
4. Data JPA starter

### How Spring Boot Auto Configuration works?
Build phase:
- Build tool looks at the pom xml for dependencies, specially starter POMs.
Startup:
- Spring boot app starts, while Application Context is being initialized, Auto Configuration is triggered,
- It has @Connfiguration classes with @Conditional on them: they look at:
	- Classes present in classpath (which depends on dependencies we added in POM)
	- beans present in classpath.
- Based on conditions certain configurations are activated and beans are created by @Bean and are injected.

### What does the @SpringBootApplication annotation do internally?
The ****@SpringBootApplication**** annotation combines three annotations. Those three annotations are: ****@Configuration, @EnableAutoConfiguration,**** and ****@ComponentScan**** .
- ****@EnableAutoConfiguration**** : This annotation automatically configuring beans in the class path and automatically scans the dependencies according to the application need.
- ****@ComponentScan**** : This annotation scans the components (@Component, @Service, etc.) in the package of annotated class and its sub-packages.
- ****@Configuration:**** This annotation configures the beans and packages in the class path.


### **How a Spring Boot Application Starts (Detailed and Precise):**
1. **`main()` Method Execution:** The Java `main()` method in your Spring Boot application class is the entry point
2. The @SpringBootApplication annotation, present on your main application class. It's a composite annotation are processed.
3. **`SpringApplication.run()` Invocation:** Inside the `main()` method, the static method `SpringApplication.run()` is called and creates an instance of the `SpringApplication` and is responsible for managing the application's lifecycle.
4. **Application Context Creation and Auto-Configuration:** The `SpringApplication` instance then creates and initializes the _Application Context_.  _During this process_, Spring Boot's auto-configuration is triggered. Based on the dependencies you've included (especially via starter POMs) and conditional checks, Spring Boot automatically configures various aspects of your application.
5. **Embedded Server Startup (If Web Application):** If your application is a web application (if you've included `spring-boot-starter-web`), the `SpringApplication` instance automatically starts an embedded web server (like Tomcat, Jetty, or Undertow).
6. **Application Ready:** Once the Application Context is fully initialized (including auto-configuration) and the embedded server (if applicable) is started, your Spring Boot application is ready to handle requests.

### Spring Boot CLI
Built on top of Groovy
Helps create, run and manage spring boot application
```groovy
spring init
spring run
```


### Spring Boot's dependency management
management does work via
1. Starter POMs
2. Transitive Dependency Management: only need to include the direct dependencies, and Spring Boot will take care of the rest.
3. Automatic Version Management: has a curated list of compatible versions; You don't have to specify versions for individual libraries.

How versions are choosen:
**Shortest Path Wins:** Maven generally favors the _shortest path_ to a dependency. This means it chooses the version that is declared closest to your project in the dependency tree.
- **Example:**
    - Your project depends on Library A (version 1.0)
    - Library A depends on Library B (version 2.0)
    - Your project _also_ depends on Library B (version 3.0) -- Choosen


>Change port: `application.properties == server.port=8081` default is 8080
>set it to `-1` to disable default web server

### How to disable a specific auto-configuration class?
To disable a specific auto-configuration class in a Spring Boot application, we can use the ****@EnableAutoConfiguration**** annotation with the ” ****exclude”**** attribute.
```java
@EnableAutoConfiguration(exclude = {classname})
```









