### **Functions, Procedures, and Stored Procedures**

In database systems, **functions** and **procedures** (or stored procedures) are reusable blocks of code that encapsulate logic for tasks such as calculations, data manipulations, or complex queries. Here's a breakdown of their differences and use cases:

---

### **1. Functions**

#### **Definition**:

- A **function** is a database object that takes input parameters, performs a specific task, and returns a value.
- It is often used in SQL queries to encapsulate reusable logic or calculations.

#### **Key Features**:

1. **Returns a Value**:
    - Always returns a single scalar value (e.g., INT, VARCHAR) or a table (if a table-valued function is supported).
2. **Deterministic**:
    - Functions produce the same output for the same input (e.g., `ABS()` or `ROUND()`).
3. **No Side Effects**:
    - Functions cannot modify database objects or data.

#### **Usage**:

- Ideal for calculations, string manipulations, and reusable logic in queries.

#### **Example**:

**Scalar Function**:

```sql
CREATE FUNCTION GetFullName(@FirstName NVARCHAR(50), @LastName NVARCHAR(50))
RETURNS NVARCHAR(100)
AS
BEGIN
    RETURN @FirstName + ' ' + @LastName;
END;
```

**Using the Function**:

```sql
SELECT GetFullName('John', 'Doe') AS FullName;
```

**Table-Valued Function**:

```sql
CREATE FUNCTION GetEmployeesByDepartment(@Department NVARCHAR(50))
RETURNS TABLE
AS
RETURN (
    SELECT id, name, salary
    FROM employees
    WHERE department = @Department
);
```

**Using the Table-Valued Function**:

```sql
SELECT * FROM GetEmployeesByDepartment('HR');
```

---

### **2. Procedures**

#### **Definition**:

- A **procedure** is a block of SQL code that can perform a series of operations such as querying, updating, or inserting data.
- Unlike functions, procedures can return **multiple output parameters** or no return value at all.

#### **Key Features**:

1. **Can Modify Data**:
    - Procedures can perform `INSERT`, `UPDATE`, or `DELETE` operations.
2. **Multiple Parameters**:
    - Can accept both **input** and **output parameters**.
3. **No Return Value**:
    - Procedures typically do not return a single value like functions. Instead, they can return output parameters or a result set.

#### **Usage**:

- Used for complex database logic, reusable multi-step tasks, or operations that modify data.

#### **Example**:

**Procedure with Input Parameters**:

```sql
CREATE PROCEDURE GetEmployeesByDepartment
    @Department NVARCHAR(50)
AS
BEGIN
    SELECT id, name, salary
    FROM employees
    WHERE department = @Department;
END;
```

**Executing the Procedure**:

```sql
EXEC GetEmployeesByDepartment 'HR';
```

**Procedure with Input and Output Parameters**:

```sql
CREATE PROCEDURE GetEmployeeCountByDepartment
    @Department NVARCHAR(50),
    @EmployeeCount INT OUTPUT
AS
BEGIN
    SELECT @EmployeeCount = COUNT(*)
    FROM employees
    WHERE department = @Department;
END;
```

**Executing the Procedure with Output**:

```sql
DECLARE @Count INT;
EXEC GetEmployeeCountByDepartment 'HR', @Count OUTPUT;
PRINT @Count;
```

---

### **3. Stored Procedures**

#### **Definition**:

- A **stored procedure** is a procedure saved in the database for repeated use.
- The term "stored procedure" is often used interchangeably with "procedure" since both serve the same purpose.

#### **Key Features**:

1. **Precompiled**:
    - Stored procedures are compiled once and executed many times, improving performance for repetitive tasks.
2. **Stored in the Database**:
    - Once created, they are saved in the database and can be executed directly.
3. **Security and Permissions**:
    - Users can be granted permission to execute a procedure without granting direct access to underlying tables.

#### **Example**:

```sql
CREATE PROCEDURE AddEmployee
    @Name NVARCHAR(50),
    @Department NVARCHAR(50),
    @Salary INT
AS
BEGIN
    INSERT INTO employees (name, department, salary)
    VALUES (@Name, @Department, @Salary);
END;
```

**Executing the Stored Procedure**:

```sql
EXEC AddEmployee 'John Doe', 'Finance', 70000;
```

---

### **Comparison: Functions vs. Procedures**

|**Aspect**|**Function**|**Procedure (Stored Procedure)**|
|---|---|---|
|**Return Value**|Always returns a single value or table.|Can return multiple output parameters or none.|
|**Data Modification**|Cannot modify database objects or data.|Can perform `INSERT`, `UPDATE`, or `DELETE`.|
|**Usage in Queries**|Can be used in SELECT, WHERE, GROUP BY, etc.|Cannot be used directly in SQL queries.|
|**Parameters**|Only input parameters allowed.|Supports input and output parameters.|
|**Precompilation**|Not precompiled (evaluated at runtime).|Precompiled and stored in the database.|
|**Performance**|Generally slower for complex operations.|Faster for repetitive, complex tasks.|

---

### **When to Use Functions vs. Procedures**

1. **Use Functions When**:
    
    - You need a reusable calculation or logic in SQL queries.
    - Example: `GetFullName`, `CalculateTax`.
2. **Use Procedures When**:
    
    - You need to modify data (e.g., insert, update).
    - You require complex operations involving multiple steps.
    - Example: Adding a new employee, generating reports.

---

### **Key Points**

- **Functions** are for lightweight operations that return values or tables.
- **Procedures** (stored procedures) are for complex, multi-step operations that may or may not modify data.
- Use both strategically to encapsulate logic and reduce redundancy in your database code.

Let me know if you'd like further examples or clarifications!

### JDBC

Statement**

#### **What It Is**:

- A **basic SQL execution tool** provided by JDBC.
- Used for executing simple SQL queries (e.g., `SELECT`, `INSERT`, `UPDATE`, `DELETE`).

#### **Key Features**:

1. **No Parameters**:
    - Cannot accept input parameters, so SQL queries are constructed as plain strings.
2. **Dynamic SQL**:
    - Query strings are sent to the database and compiled at runtime.
3. **Prone to SQL Injection**:
    - Since queries are built as strings, it's vulnerable to SQL injection if user inputs are not properly sanitized.

#### **Usage**:

- Best suited for **one-time queries** or simple, static SQL statements.

#### **Example**:

```java
try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password")) {
    Statement statement = connection.createStatement();

    // Execute a SELECT query
    ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

    while (resultSet.next()) {
        System.out.println("Employee ID: " + resultSet.getInt("id"));
        System.out.println("Name: " + resultSet.getString("name"));
    }

    // Execute an UPDATE query
    int rowsAffected = statement.executeUpdate("UPDATE employees SET salary = 5000 WHERE id = 1");
    System.out.println("Rows affected: " + rowsAffected);
} catch (SQLException e) {
    e.printStackTrace();
}
```

#### **Limitations**:

- Not suitable for dynamic queries with parameters.
- Performance issues with repeated queries because SQL is compiled each time.
- Vulnerable to SQL injection.

---

### **2. PreparedStatement**

#### **What It Is**:

- A **precompiled SQL statement** that allows you to execute the same query multiple times with different parameters.
- Provides better **performance** and **security** than `Statement`.

#### **Key Features**:

1. **Parameterized Queries**:
    - Allows the use of placeholders (`?`) in the SQL query, which are replaced with actual values at runtime.
2. **Precompiled**:
    - The SQL query is compiled once and reused, improving performance for repeated executions.
3. **Prevents SQL Injection**:
    - Parameters are handled as data, not part of the SQL string, eliminating SQL injection risks.

#### **Usage**:

- Best suited for queries executed multiple times with varying parameters.

#### **Example**:

```java
try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password")) {
    String sql = "SELECT * FROM employees WHERE department = ? AND salary > ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);

    // Set parameters
    preparedStatement.setString(1, "HR");
    preparedStatement.setInt(2, 3000);

    // Execute the query
    ResultSet resultSet = preparedStatement.executeQuery();

    while (resultSet.next()) {
        System.out.println("Employee ID: " + resultSet.getInt("id"));
        System.out.println("Name: " + resultSet.getString("name"));
    }

    // Reuse the same PreparedStatement with different parameters
    preparedStatement.setString(1, "Finance");
    preparedStatement.setInt(2, 4000);
    resultSet = preparedStatement.executeQuery();

    while (resultSet.next()) {
        System.out.println("Employee ID: " + resultSet.getInt("id"));
        System.out.println("Name: " + resultSet.getString("name"));
    }
} catch (SQLException e) {
    e.printStackTrace();
}
```

#### **Advantages**:

- Prevents SQL injection.
- Improved performance due to query precompilation.
- Supports input/output parameters for dynamic queries.

---

### **3. CallableStatement**

#### **What It Is**:

- Used to **execute stored procedures** in the database.
- Allows calling database logic written as procedures or functions.

#### **Key Features**:

1. **Supports Input/Output Parameters**:
    - Parameters can be both input (`IN`) and output (`OUT`), allowing the stored procedure to return values.
2. **Encapsulates Complex Logic**:
    - Ideal for invoking prewritten, reusable database logic.

#### **Usage**:

- Best suited for **complex queries** or database operations encapsulated in stored procedures.

#### **Example**:

**Stored Procedure in MySQL**:

```sql
DELIMITER $$

CREATE PROCEDURE GetEmployeeDetails (IN dept VARCHAR(255), OUT empCount INT)
BEGIN
    SELECT COUNT(*) INTO empCount FROM employees WHERE department = dept;
END$$

DELIMITER ;
```

**Calling the Stored Procedure in Java**:

```java
try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password")) {
    CallableStatement callableStatement = connection.prepareCall("{CALL GetEmployeeDetails(?, ?)}");

    // Set input parameter
    callableStatement.setString(1, "HR");

    // Register output parameter
    callableStatement.registerOutParameter(2, Types.INTEGER);

    // Execute the stored procedure
    callableStatement.execute();

    // Get the output parameter value
    int employeeCount = callableStatement.getInt(2);
    System.out.println("Number of employees in HR: " + employeeCount);
} catch (SQLException e) {
    e.printStackTrace();
}
```

#### **Advantages**:

- Allows calling precompiled, complex logic.
- Supports both input and output parameters.

#### **Limitations**:

- Requires stored procedures to be written in the database.
- Debugging stored procedures can be challenging compared to inline queries.

---

### **Comparison of JDBC Statement Types**

|**Feature**|**Statement**|**PreparedStatement**|**CallableStatement**|
|---|---|---|---|
|**Dynamic Parameters**|Not supported|Supported|Supported|
|**Precompilation**|No|Yes|Yes|
|**SQL Injection**|Vulnerable|Prevents|Prevents|
|**Reusability**|Limited|High|High|
|**Stored Procedures**|Not supported|Not directly supported|Fully supported|
|**Performance**|Lower (compiles each time)|Better (compiles once)|Similar to PreparedStatement|

---

### **Key Takeaways**

1. Use **Statement** for simple, one-off queries without parameters.
2. Use **PreparedStatement** for queries executed multiple times with dynamic parameters.
3. Use **CallableStatement** for invoking stored procedures.

Let me know if you’d like deeper examples or clarifications!