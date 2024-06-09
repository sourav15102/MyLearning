Examining or modifying the runtime behavior of a class at runtime. 
The java.lang.Class class performs mainly two tasks:
- Provides methods to get the metadata of a class at runtime.
- Provides methods to examine and change the runtime behavior of a class.

1. The forName() method is used to load the class dynamically. It returns the instance of Class class. It should be used if you know the fully qualified name of the class. This cannot be used for primitive types.

```java
try {
    Class<?> dynamicClass = Class.forName("com.example.DynamicClass");
    Object instance = dynamicClass.newInstance();
    System.out.println("Dynamic class name: " + dynamicClass.getName());
    System.out.println("Instance created: " + instance);
} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
    e.printStackTrace();
}
```

2. It returns the instance of Class class. It should be used if you know the type. Moreover, it can be used with primitives.

```java
String str = "Hello, World!";
Class<?> strClass = str.getClass();
System.out.println("Class name for str: " + strClass.getName());
Object strInstance;
try {
    strInstance = strClass.newInstance();
    System.out.println("Instance created: " + strInstance);
} catch (InstantiationException | IllegalAccessException e) {
    e.printStackTrace();
}
```

3. If a type is available, but there is no instance then it is possible to obtain a Class by appending ".class" to the name of the type. It can be used for primitive data type also.

```java
Class<?> intClass = int.class;
Class<?> doubleClass = double.class;
System.out.println("Class name for int: " + intClass.getName());
System.out.println("Class name for double: " + doubleClass.getName());
Object intInstance;
Object doubleInstance;
try {
    intInstance = intClass.newInstance();
    doubleInstance = doubleClass.newInstance();
    System.out.println("Instance created for int: " + intInstance);
    System.out.println("Instance created for double: " + doubleInstance);
} catch (InstantiationException | IllegalAccessException e) {
    e.printStackTrace();
}
```

### Javap and accessing private methods:

1. Using `javap` to Examine a Class:

Suppose you have a class named `MyClass`:

```java
public class MyClass {
    private void privateMethod() {
        System.out.println("This is a private method.");
    }
}
```

You can use the `javap` command to examine the class file and see its methods and attributes. Open your command prompt or terminal and navigate to the directory containing `MyClass.class`, then run:

```bash
javap -private MyClass
```

This command will display information about the class, including its private methods.

2. Accessing a Private Method Using Reflection:

Here's an example of how to access and invoke a private method of a class using Java reflection:

```java
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {
        // Create an instance of MyClass
        MyClass obj = new MyClass();

        // Get the Class object for MyClass
        Class<?> clazz = obj.getClass();

        // Get the private method "privateMethod" by name
        Method method = clazz.getDeclaredMethod("privateMethod");

        // Make the private method accessible
        method.setAccessible(true);

        // Invoke the private method on the object
        method.invoke(obj);
    }
}
```

In this example, we use reflection to access the private method `privateMethod` of the `MyClass` class and invoke it. The `setAccessible(true)` call is necessary to allow access to the private method.