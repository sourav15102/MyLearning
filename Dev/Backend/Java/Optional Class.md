https://medium.com/javarevisited/optional-class-in-java-8-making-your-code-more-clear-and-concise-62af0712910d'
An Optional is a wrapper that can hold either a value or nothing (an empty Optional). It implements the Null Object pattern, aiming to minimize the need for null checks in code.

Example (Problem with null):

```java
public class Main {  
    public static void main(String[] args) {  
  
        Student Student = getStudentWithName("hamza");  
        if(student != null){  
             System.out.println(Student.getName());  
        }else {  
          System.out.println("no Student with the given name ");  
        }  
  
    }  
  
   public  static Student  getStudentWithName(String name ){  
        // lets suppose that our database contain only 2 students ahmed and hamza .  
        if (name.equals("hamza") || name.equals("ahmed")) {  
            return new Student(name , 22 , "Morocco");  
        } else {  
            return null ;  
        }  
    }  
  
}
```

Methods:
1. ofNullable()
```java
Optional<T> optional = Optional.ofNullable(T)
```
On this we have `.isPresent()` and `.get()` function to check if object is present and get the object.
2. of()
```java
Optional<Student> student = Optional.of(null); // it will throw null pointer exception.
```

Even after using these methods we will need to use if-else blocks in the code.

3. orElse()
Returns the value wrapped in the Optional, if it is present, or a default value if it is not. It takes a default value as an argument and returns it if the Optional is empty.

```java
public class Main {  
    public static void main(String[] args) {  
        Student  student = Optional.ofNullable(getStudentWithName("hamza")).orElse(new Student("no one", 0, "Unknown"));  
            System.out.println(student.getName());  
    }  
  
    public  static Student  getStudentWithName(String name ){  
        // lets suppose that our database contain only 2 students ahmed and hamza .  
        if (name.equals("hamza") || name.equals("ahmed")) {  
            return new Student(name , 22 , "Morocco");  
        } else {  
            return null ;  
        }  
    }  
  
}
```

4. orElseThrow()
`orElseThrow` is a method in the Java Optional class that is used to throw an exception if the Optional is empty. Unlike `orElse`, which returns a default value, `orElseThrow` allows you to throw a custom exception.

```java
public class Main {  
    public static void main(String[] args) throws StudentNotFoundException {  
        Student  student = Optional.ofNullable(getStudentWithName("fs")).orElseThrow(()-> new StudentNotFoundException("the Student is not Present "));  
            System.out.println(student.getName());  
    }  
  
    public  static Student  getStudentWithName(String name ){  
        // lets suppose that our database contain only 2 students ahmed and hamza .  
        if (name.equals("hamza") || name.equals("ahmed")) {  
            return new Student(name , 22 , "Morocco");  
        } else {  
            return null ;  
        }  
    }  
  
}
```

### **Step-by-Step Guide to `Optional` in Java 8 (From Basic to Advanced)**

`Optional<T>` is a **container object** introduced in Java 8 to **avoid `null` references** and handle values safely.

---

## **📌 Step 1: Creating an `Optional`**

### **1️⃣ Creating an `Optional` with a Value**

If you **know** the value is **not null**, use `Optional.of(value)`:

```java
Optional<String> optional = Optional.of("Hello");
System.out.println(optional); // Output: Optional[Hello]
```

### **2️⃣ Creating an Empty `Optional`**

Use `Optional.empty()` when **no value is present**:

```java
Optional<String> emptyOptional = Optional.empty();
System.out.println(emptyOptional); // Output: Optional.empty
```

### **3️⃣ Creating an `Optional` with a Potentially Null Value**

Use `Optional.ofNullable(value)` to avoid `NullPointerException`:

```java
Optional<String> nullableOptional = Optional.ofNullable(null);
System.out.println(nullableOptional); // Output: Optional.empty
```

---

## **📌 Step 2: Checking Presence of a Value**

### **4️⃣ `isPresent()` – Check if Value Exists**

```java
Optional<String> optional = Optional.of("Hello");
if (optional.isPresent()) {
    System.out.println("Value exists: " + optional.get());
}
// Output: Value exists: Hello
```

⚠️ **Avoid calling `.get()` directly without checking `isPresent()`, as it throws `NoSuchElementException` if empty!**

### **5️⃣ `ifPresent()` – Execute Code if Value Exists**

Instead of `if-else`, use `ifPresent()`:

```java
optional.ifPresent(value -> System.out.println("Value: " + value)); 
// Output: Value: Hello
```

---

## **📌 Step 3: Handling `null` Values**

### **6️⃣ `orElse()` – Provide Default Value**

If `Optional` is **empty**, return a **default value**:

```java
String result = optional.orElse("Default Value");
System.out.println(result); // Output: Hello

Optional<String> emptyOpt = Optional.empty();
System.out.println(emptyOpt.orElse("Default Value")); // Output: Default Value
```

### **7️⃣ `orElseGet()` – Provide Default Value Using a Supplier**

Like `orElse()`, but **executes only if needed**:

```java
String result = optional.orElseGet(() -> "Generated Default Value");
System.out.println(result); // Output: Hello
```

📌 **Difference Between `orElse()` and `orElseGet()`**

- `orElse()` **always executes** the default value computation, even if it's not needed.
- `orElseGet()` **only executes** when the `Optional` is empty.

### **8️⃣ `orElseThrow()` – Throw an Exception if Empty**

```java
String value = optional.orElseThrow(() -> new IllegalStateException("No value present"));
```

---

## **📌 Step 4: Transforming and Filtering Values**

### **9️⃣ `map()` – Transform the Value**

Use `map()` to **modify the value** inside `Optional`:

```java
Optional<String> upper = optional.map(String::toUpperCase);
System.out.println(upper.orElse("No Value")); // Output: HELLO
```

⚠️ If `Optional` is empty, `map()` **returns an empty Optional** instead of throwing an error.

### **🔟 `flatMap()` – Use Nested `Optional`**

If `map()` returns another `Optional`, use `flatMap()` to avoid `Optional<Optional<T>>`:

```java
Optional<Optional<String>> nestedOptional = Optional.of(Optional.of("Nested"));
Optional<String> flatMapped = nestedOptional.flatMap(opt -> opt);
System.out.println(flatMapped.orElse("Empty")); // Output: Nested
```

### **1️⃣1️⃣ `filter()` – Apply a Condition**

```java
Optional<String> filtered = optional.filter(value -> value.startsWith("H"));
System.out.println(filtered.orElse("Filtered Out")); // Output: Hello

Optional<String> empty = optional.filter(value -> value.startsWith("X"));
System.out.println(empty.orElse("Filtered Out")); // Output: Filtered Out
```

---

## **📌 Step 5: Chaining Multiple Operations**

### **1️⃣2️⃣ `Optional` with Method Chaining**

```java
Optional<String> name = Optional.of("Java 8");

String result = name.filter(n -> n.length() > 3)
                   .map(String::toUpperCase)
                   .orElse("Default");

System.out.println(result); // Output: JAVA 8
```

---

## **📌 Step 6: Using `Optional` in Methods**

### **1️⃣3️⃣ Returning `Optional` from Methods**

Avoid returning `null`, return `Optional<T>` instead:

```java
public static Optional<String> findUserById(int id) {
    return id == 1 ? Optional.of("John Doe") : Optional.empty();
}
```

**Usage:**

```java
String user = findUserById(1).orElse("Unknown User");
System.out.println(user); // Output: John Doe
```

---

## **📌 Step 7: Combining Multiple Optionals**

### **1️⃣4️⃣ `Optional` with `or()` – Provide an Alternative `Optional`**

```java
Optional<String> optional1 = Optional.empty();
Optional<String> optional2 = Optional.of("Fallback Value");

String result = optional1.or(() -> optional2).get();
System.out.println(result); // Output: Fallback Value
```

---

## **📌 Step 8: Avoiding Common Mistakes**

### ❌ **1. Don't Use `Optional` as a Parameter**

Bad:

```java
public void process(Optional<String> input) { ... } // ❌ Don't do this
```

Instead, accept **non-optional parameters** and handle nulls inside the method.

### ❌ **2. Don't Use `.get()` Without Checking `isPresent()`**

Bad:

```java
String value = optional.get(); // ❌ Throws NoSuchElementException if empty
```

Use:

```java
String value = optional.orElse("Default");
```

### ❌ **3. Don't Use `Optional` for Fields in DTOs**

Bad:

```java
class User {
    Optional<String> name; // ❌ Don't do this
}
```

Instead, use:

```java
class User {
    String name; // ✅ Use regular fields
}
```

---

## **📌 Summary of `Optional` Functions**

|Method|Purpose|
|---|---|
|`of(value)`|Create `Optional` with non-null value|
|`empty()`|Create an empty `Optional`|
|`ofNullable(value)`|Create `Optional` that can hold null|
|`isPresent()`|Check if value is present|
|`ifPresent(Consumer)`|Perform an action if value exists|
|`orElse(defaultValue)`|Return value or default if empty|
|`orElseGet(Supplier)`|Return value or computed default|
|`orElseThrow(Supplier)`|Throw exception if empty|
|`map(Function)`|Transform the value|
|`flatMap(Function)`|Transform and unwrap nested `Optional`|
|`filter(Predicate)`|Keep value only if condition matches|
|`or(Supplier<Optional<T>>)`|Provide an alternative Optional|

---

## **🚀 Next Steps**

Now that you've mastered **`Optional`**, what would you like next?

1. **Practice Questions on `Optional`**?
2. **Move to Java 8 `Streams` Step-by-Step**?
3. **Deep dive into `CompletableFuture`**?

Let me know how you'd like to continue! 🚀