Sure, here are detailed answers to each question from the image:

1. **Inheritance vs Composition**:
   - **Inheritance**: This is a mechanism where a new class inherits properties and behavior (methods) from an existing class. It represents an "is-a" relationship. For example, a `Dog` class can inherit from an `Animal` class.
   - **Composition**: This is a design principle where a class is composed of one or more objects from other classes. It represents a "has-a" relationship. For example, a `Car` class can have an `Engine` object. Composition is generally preferred over inheritance because it provides greater flexibility and modularity.

2. **Can you override a private or static method in Java?**:
   - No, you cannot override private or static methods in Java. Private methods are not visible to subclasses, hence they cannot be overridden. Static methods belong to the class rather than an instance, and method overriding relies on polymorphism which is not applicable to static methods.

3. **default vs protected access specifiers**:
   - **default** (no modifier): Accessible only within the same package.
   - **protected**: Accessible within the same package and by subclasses, even if they are in different packages.

4. **Why Strings are immutable in Java?**:
   - Security: Immutable objects are inherently thread-safe and secure.
   - Caching: Since they are immutable, they can be cached and reused without duplication.
   - Class Loading: String immutability ensures that the same String instance can be shared, optimizing memory and improving performance.

5. **Write your own immutable class**:
   ```java
   public final class ImmutableClass {
       private final int value;
       private final String name;

       public ImmutableClass(int value, String name) {
           this.value = value;
           this.name = name;
       }

       public int getValue() {
           return value;
       }

       public String getName() {
           return name;
       }
   }
   ```
> Here methods are final also because we need internal immutability as well, if we they are just private and not final, some methods might change them.

6. **How can we write our own custom exception?**:
   ```java
   public class MyCustomException extends Exception {
    // Default constructor
    public MyCustomException() {
        super();
    }

    // Constructor that accepts a message
    public MyCustomException(String message) {
        super(message);
    }

    // Constructor that accepts a message and a cause
    public MyCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor that accepts a cause
    public MyCustomException(Throwable cause) {
        super(cause);
    }
}
   ```
> We can have any combination of constructors as per our needs.

1. **Explain Producer/Consumer problem using wait and notify**:
   - In the Producer/Consumer problem, producers generate data and put it into a shared buffer, and consumers take data from the buffer. The buffer is typically protected by synchronization. `wait()` and `notify()` (or `notifyAll()`) are used to coordinate the access:
     ```java
     public class ProducerConsumer {
         private List<Integer> buffer = new ArrayList<>();
         private int capacity = 10;

         public void produce() throws InterruptedException {
             synchronized (this) {
                 while (buffer.size() == capacity) {
                     wait();
                 }
                 buffer.add(1); // Add item to buffer
                 notifyAll();
             }
         }

         public void consume() throws InterruptedException {
             synchronized (this) {
                 while (buffer.isEmpty()) {
                     wait();
                 }
                 buffer.remove(buffer.size() - 1); // Remove item from buffer
                 notifyAll();
             }
         }
     }
     ```

8. **NoClassDefFoundError vs ClassNotFoundException**:
   - **NoClassDefFoundError**: Thrown when the JVM or a ClassLoader instance tries to load a class but the class definition is not found. This error is typically thrown at runtime after the class was already compiled successfully.
   - **ClassNotFoundException**: Thrown when an application tries to load a class at runtime using `Class.forName()`, `ClassLoader.findSystemClass()`, or `ClassLoader.loadClass()` and the class is not found.

9. **How is the diamond problem resolved in interfaces after Java 8?**:
   - Java 8 introduced default methods in interfaces which can lead to the diamond problem. This is resolved by:
     - The class that implements multiple interfaces must override the default methods if they conflict.
     - The class can explicitly specify which interface's default method it wants to use using `InterfaceName.super.methodName()` syntax.

10. **What is double check locking in singletons?**: (https://www.youtube.com/watch?v=upfrQvOgC24&list=PL6W8uoQQ2c61X_9e6Net0WdYZidm7zooW&index=33)
    - Double-check locking is a technique to reduce the overhead of acquiring a lock by first testing the locking criterion (the `null` check) without actually acquiring the lock. Only if the check indicates that locking is required, the lock is acquired.
    ```java
    public class Singleton {
        private static volatile Singleton instance;

        private Singleton() {}

        public static Singleton getInstance() {
            if (instance == null) {
                synchronized (Singleton.class) {
                    if (instance == null) {
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }
    }
    ```

11. **Are enums Singleton in Java?**:
    - Yes, Java enums are singletons by design. Each enum value is instantiated only once and the same instance is used whenever it is referenced.

12. **Shallow vs deep cloning**:
    - **Shallow Cloning**: Creates a new object and copies the references of the fields from the original object to the new object. The new object and the original object share the same references to the nested objects.
    - **Deep Cloning**: Creates a new object and recursively copies all objects referenced by the original object, creating completely independent clones of the original object.

13. **Why do we use readResolve in singletons?**:
    - The `readResolve` method is used in the context of serialization to ensure that the singleton property is maintained. It replaces the deserialized object with the singleton instance.
    ```java
    protected Object readResolve() {
        return getInstance();
    }
    ```

14. **Why wait(), notify(), and notifyAll() methods have to be called from synchronized method or block?**:
    - Because these methods are used to communicate between threads that are accessing shared data. Using them outside a synchronized context can result in an `IllegalMonitorStateException`.

15. **Why are wait, notify, and notifyAll in the Object class?**:
    - These methods are in the `Object` class because they are meant to be used on any object that is used as a monitor (lock) for synchronization, and every class in Java inherits from `Object`.

16. **Can we run a class in Java 1.9, which was compiled in Java 1.8?**:
    - Yes, Java maintains backward compatibility, so a class compiled in Java 1.8 can typically run in Java 1.9 without issues.

17. **What happens if your Serializable class contains a member which is not serializable? How do you fix it?**:
    - If a class contains a non-serializable member, a `NotSerializableException` will be thrown. You can fix it by marking that member as `transient`, so it will not be serialized.
    ```java
    class Example implements Serializable {
        private transient NonSerializableClass nonSerializableMember;
    }
    ```

18. **What will happen if you put the return statement or System.exit() on the try or catch block? Will finally block execute?**:
    - If a `return` statement or `System.exit()` is called in the `try` or `catch` block, the `finally` block will still execute, unless `System.exit()` is called.

19. **If a method throws NullPointerException in the superclass, can we override it with a method that throws RuntimeException?**:
    - Yes, since `NullPointerException` is a subclass of `RuntimeException`, you can override a method in the subclass to throw `RuntimeException`.

20. **Is it possible to load a class by two ClassLoaders?**:
    - Yes, it is possible to load the same class by different class loaders, which will result in two different class objects. This can lead to issues such as `ClassCastException`.

These explanations should help you understand the concepts better. If you need more detailed examples or further clarification, feel free to ask!