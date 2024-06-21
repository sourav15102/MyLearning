
it is a subprogram

```
CREATE OR REPLACE PROCEDURE <your_procedure>  
(  
<parameterl IN/OUT <datatype>  
...  
)  
[ IS | AS ]<declaration_part>  
BEGIN  
<execution part>  
EXCEPTION  
<exception handling part>  
END;
```

```sql
CREATE OR REPLACE PROCEDURE ProcessTransaction(
  IN transaction_id INT,
  OUT status VARCHAR(20),
  INOUT balance DECIMAL
)
IS
  transaction_amount DECIMAL;
BEGIN
  -- Retrieve transaction amount
  SELECT amount INTO transaction_amount FROM transactions WHERE id = transaction_id;
  
  -- Update balance
  balance := balance - transaction_amount;
  
  -- Set status based on new balance
  IF balance >= 0 THEN
    status := 'Completed';
  ELSE
    status := 'Insufficient Funds';
  END IF;
EXCEPTION
  WHEN OTHERS THEN
    status := 'Error';
    balance := NULL;
END;

```

Stored functions:
```sql
CREATE OR REPLACE FUNCTION calculate_bonus (
    emp_id IN NUMBER
) 
RETURN NUMBER 
IS
    emp_salary NUMBER;
    bonus NUMBER;
BEGIN
    -- Retrieve the employee's salary
    SELECT salary INTO emp_salary
    FROM employees
    WHERE employee_id = emp_id;
    
    -- Calculate the bonus as 10% of salary
    bonus := emp_salary * 0.1;
    
    RETURN bonus;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 0;
    WHEN OTHERS THEN
        RETURN -1;
END;

```

Q: Tell me how you can recompile stored procedures at runtime?
A:
To recompile stored procedures at runtime, you can use the `WITH RECOMPILE` option. This forces the procedure to be recompiled every time it's executed, ensuring that the query plan is always optimized for the current set of input parameters.

```sql
CREATE OR REPLACE PROCEDURE my_procedure
WITH RECOMPILE
AS
BEGIN
    -- procedure logic here
END;
```
### 2. What do you mean by NOCOPY?

In PL/SQL, when passing parameters to subprograms (procedures or functions), the default behavior is to pass parameters by value. This means that a copy of the actual parameter is passed to the subprogram. However, sometimes you may want to pass large variables or objects by reference to avoid the overhead of copying. Here's where `NOCOPY` comes into play:

#### Example:

```sql
CREATE OR REPLACE PROCEDURE process_data (data IN OUT NOCOPY VARCHAR2) IS
BEGIN
    -- Manipulate data without creating a copy
    data := 'Processed ' || data;
END;

```

### When would you use stored procedures or functions?

Since functions are computed values, they cannot perform permanent environmental changes to the SQL server. You cannot insert them in the statement or update them. If a function returns a scalar value, or you can combine it with another after it returns a scalar set, you can use it in line with the SQL statements.