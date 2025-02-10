## 1. Project Setup

1. **Create a new Spring Boot project** using [Spring Initializr](https://start.spring.io/) or your IDE.
2. Include the following dependencies (or their equivalents):
    - **Spring Web** (if you need REST endpoints or controllers)
    - **Spring Data JPA**
    - **H2** (or MySQL/PostgreSQL driver, depending on your needs)

Example of `pom.xml` dependencies for Maven:

```xml
<dependencies>
    <!-- Spring Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- H2 database (in-memory) -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- For MySQL or other relational DB:
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    -->
</dependencies>
```

In your `application.properties` (or `application.yml`), you can configure the database connection. For H2 in-memory:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

This will set up an in-memory H2 database with a console accessible at `/h2-console` in your browser.

---

## 2. Create an Entity

Let’s say we have an `Employee` entity:

```java
package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String role;
    
    // Constructors
    public Employee() {}
    
    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    // Getters and setters omitted for brevity
}
```

This class maps to a table named `employee` in the database.

---

## 3. Create a DAO Interface

In a classic DAO pattern, you would manually define the interface methods (and possibly implement them). However, with Spring Data JPA, you can let Spring generate default implementations for common operations (CRUD). Below is a hybrid approach:

### 3.1. Option A — Using Spring Data Repositories

Create a repository interface that extends `JpaRepository` or `CrudRepository`:

```java
package com.example.demo.dao;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // You can define custom query methods here if needed.
}
```

This already takes care of most common DAO methods—no need to manually implement the DAO.

### 3.2. Option B — Defining a Custom DAO

If you want to manually implement the DAO pattern without Spring Data’s dynamic proxies, you could do something like:

1. Define the DAO interface:
    
    ```java
    package com.example.demo.dao;
    
    import com.example.demo.model.Employee;
    import java.util.List;
    
    public interface EmployeeDAO {
        Employee findById(Long id);
        List<Employee> findAll();
        Employee save(Employee employee);
        void deleteById(Long id);
        // Add more methods as needed
    }
    ```
    
2. Implement it in a class annotated with `@Repository`:
    
    ```java
    package com.example.demo.dao.impl;
    
    import com.example.demo.dao.EmployeeDAO;
    import com.example.demo.model.Employee;
    import jakarta.persistence.EntityManager;
    import jakarta.persistence.PersistenceContext;
    import org.springframework.stereotype.Repository;
    import org.springframework.transaction.annotation.Transactional;
    
    import java.util.List;
    
    @Repository
    @Transactional
    public class EmployeeDAOImpl implements EmployeeDAO {
    
        @PersistenceContext
        private EntityManager entityManager;
    
        @Override
        public Employee findById(Long id) {
            return entityManager.find(Employee.class, id);
        }
    
        @Override
        public List<Employee> findAll() {
            return entityManager.createQuery("SELECT e FROM Employee e", Employee.class)
                                .getResultList();
        }
    
        @Override
        public Employee save(Employee employee) {
            if (employee.getId() == null) {
                entityManager.persist(employee);
            } else {
                employee = entityManager.merge(employee);
            }
            return employee;
        }
    
        @Override
        public void deleteById(Long id) {
            Employee employee = findById(id);
            if (employee != null) {
                entityManager.remove(employee);
            }
        }
    }
    ```
    

This is the traditional DAO pattern, giving you full control over the queries and the entity manager usage.

---

