https://www.youtube.com/watch?v=OuNOyFg942M&list=PL6W8uoQQ2c61X_9e6Net0WdYZidm7zooW&index=31
https://www.youtube.com/watch?v=upfrQvOgC24&list=PL6W8uoQQ2c61X_9e6Net0WdYZidm7zooW&index=32
### Eager Initialization
Eager initialization creates the Singleton instance at the time of class loading. This method ensures thread safety and simplicity but may lead to resource wastage if the instance is never used.

```java
public class Singleton {
    private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
```
### Why Eager Initialization Doesn't Solve Lazy Issues

Eager initialization creates the instance at the time of class loading, which guarantees thread safety but may result in unnecessary resource usage if the instance is never utilized. Lazy initialization, on the other hand, creates the instance only when it is needed, which can conserve resources but requires careful handling to ensure thread safety.

### Cons of Eager Initialization

1. **Resource Usage**: The instance is created even if it is never used, which can be inefficient in terms of memory and processing.
2. **Performance Overhead**: The creation of the instance at class loading time can slow down the application startup.
### Lazy Initialization
Lazy initialization delays the creation of the Singleton instance until it's needed. However, this approach is not thread-safe.

```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

### Synchronized Method
To make lazy initialization thread-safe, the `getInstance` method can be synchronized. This ensures only one thread can access the method at a time, but it can lead to performance issues.

```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

### Double Locking:
```java
public class DoubleLockingSingleton {
    // Private static variable to hold the single instance of the class
    private static volatile DoubleLockingSingleton instance;

    // Private constructor to prevent instantiation from outside
    private DoubleLockingSingleton() {
    }

    // Public static method to get the single instance of the class
    public static DoubleLockingSingleton getInstance() {
        // Check if the instance is null
        if (instance == null) {
            // Synchronize to ensure thread safety
            synchronized (DoubleLockingSingleton.class) {
                // Double check if instance is still null inside synchronized block
                if (instance == null) {
                    // Create a new instance
                    instance = new DoubleLockingSingleton();
                }
            }
        }
        // Return the single instance
        return instance;
    }

    // Other methods can be added here
}
```

Two issues with double locking:
1. For line: `instance = new DoubleLockingSingleton();`, the compiler can do re-ordering of statements where instance might get assigned memory block but memory block doesnt have data.
2. If there are two cores, and two caches, so generally, `instance` might get assigned to one of the caches and another might try to read from cache 2, so, 2nd thread might think it is still null.

Solution: Use volatile, cos it has 2 properties
1. Volatile variables get directly dumped into memory not caches.
2. all instructions written before volatile variable will stay before it and the ones after it, will stay after it, and all instructions before it will get dumped into memory.

```java
public class DoubleLockingSingleton {
    // Private static volatile variable to hold the single instance of the class
    private static volatile DoubleLockingSingleton instance;

    // Private constructor to prevent instantiation from outside
    private DoubleLockingSingleton() {
    }

    // Public static method to get the single instance of the class
    public static DoubleLockingSingleton getInstance() {
        // Check if the instance is null
        if (instance == null) {
            // Synchronize to ensure thread safety
            synchronized (DoubleLockingSingleton.class) {
                // Double check if instance is still null inside synchronized block
                if (instance == null) {
                    // Create a new instance
                    instance = new DoubleLockingSingleton();
                }
            }
        }
        // Return the single instance
        return instance;
    }

    // Other methods can be added here
}
```
#### ***Why:***
It serves two purpose:
1. ensures class has just single instance.
2. provides global access point to that instance.

#### ***How***
- Make the default constructor private, to prevent other objects from using the `new` operator with the Singleton class.
- Create a static creation method that acts as a constructor. Under the hood, this method calls the private constructor to create an object and saves it in a static field. All following calls to this method return the cached object.

#### ***When:***
1. Use the Singleton pattern when a class in your program should have just a single instance available to all clients; for example, a single database object shared by different parts of the program.
2. Use the Singleton pattern when you need stricter control over global variables.

Naive (Not thread-safe):
```java
public class Singleton {  
    private static Singleton instance=null;  
    private int x;  

    public int getX(){  
        return x;  
    }  
  
    public void setX(int x){  
        this.x = x;  
    }  
  
    private Singleton(){}  
    public static Singleton getInstance(){  
        if(instance==null){  
            return (instance = new Singleton());  
        }else{  
            return instance;  
        }  
    }  
}
```


