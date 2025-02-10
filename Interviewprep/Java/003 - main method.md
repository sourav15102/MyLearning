## **Evolution of `main()` Over Java Versions**

### **🟢 Java 1.0 - Java 1.6 (Legacy Rules)**

- The `main()` method was **strictly defined** as:
```java
public static void main(String[] args)
```

- **No flexibility**—modifications (e.g., `final`, different parameter names) were not officially supported.

📌 **Common Interview Question:**

- What happens if `main()` is declared **without `public` or `static`**?

**Answer:**

- Compilation fails if `public` is removed.
- Runtime error occurs if `static` is removed because Java needs to call `main()` without an instance.

---

### **🟢 Java 1.7 - Introduction of `varargs` in `main()`**

- From **Java 1.7**, `main()` started supporting **varargs (`...`)** instead of an array:
```java
public static void main(String... args)
```
✅ This means both `String[] args` and `String... args` are valid.
📌 **Common Interview Question:**

- How is `String... args` different from `String[] args`?
- Answer: Both are equivalent; `String... args` is a more flexible syntax.

### **Quick Summary: Java 11 vs. Java 21 - `main()` Requirement & Execution**

---

## **🟢 Java 11 - `main()` Still Required for Methods & Classes**

✅ **Single-file execution** introduced (`java File.java`) → No need for manual compilation.  
✅ **Allows top-level expressions** without `main()`.  
❌ **Methods & multiple statements still need a class and `main()`**.

### **✅ Java 11 Working Example (Single Statement Without `main()`)**

```java
System.out.println("Hello, Java 11!");
```

📌 **Run directly:**

```sh
java Hello.java
```

✅ **Works because Java automatically wraps it inside `main()`.**

---

### **❌ Java 11 Example That Won't Work (Multiple Methods Without Class)**

```java
void greet() {
    System.out.println("Hello, Java 11!");
}
greet();  // ❌ Compilation Error in Java 11
```

📌 **Why?**

- Java 11 **requires methods inside a class**.
- **To fix it, wrap it inside `main()` and a class**.

✅ **Fixed Java 11 Code**

```java
public class Hello {
    static void greet() {
        System.out.println("Hello, Java 11!");
    }

    public static void main(String[] args) {
        greet();
    }
}
```

---

## **🟢 Java 21 - No `main()`, No Class Required!**

✅ **Unnamed Classes** → Methods can exist without a class.  
✅ **No `main()` required** for scripting and functional programs.  
✅ **Supports multiple statements & methods directly**.

### **✅ Java 21 Working Example (Multiple Methods Without a Class)**

```java
void greet() {
    System.out.println("Hello, Java 21!");
}
greet();  // ✅ Works in Java 21
```

📌 **Why does this work in Java 21?**

- Java 21 **automatically generates a class** and `main()` behind the scenes.

---

### **❌ Java 21 Example That Won't Work (Complex Multi-Class Setup)**

```java
class A {
    void sayHello() { System.out.println("Hello from A"); }
}

class B {
    void sayHello() { System.out.println("Hello from B"); }
}

sayHello();  // ❌ Compilation Error in Java 21
```

📌 **Why?**

- **Java 21 does not support multiple named classes inside an unnamed class.**
- **To fix this, we need an explicit `main()` and class.**

✅ **Fixed Java 21 Code (Explicit `main()` Required for Multi-Class Programs)**

```java
class A {
    void sayHello() { System.out.println("Hello from A"); }
}

class B {
    public static void main(String[] args) {
        new A().sayHello();
        System.out.println("Hello from B");
    }
}
```

```java
static void greet() {  // ❌ Static methods are not allowed
    System.out.println("Hello!");
}

greet();
```