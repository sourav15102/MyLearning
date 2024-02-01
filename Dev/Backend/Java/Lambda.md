Ref link: https://www.youtube.com/watch?v=tj5sLSFjVj4

> Lambdas only work with [[Functional Interface]].

Example:
```java
@FunctionalInterface
interface MyFunctionalInterface {
    void myMethod();
}
```

```java
public class Main {
    public static void main(String[] args) {
        // Using a lambda expression to implement the abstract method of the functional interface
        MyFunctionalInterface myFunctionalInterface = () -> {
            System.out.println("Executing myMethod using a lambda expression");
        };

        // Calling the method defined in the functional interface
        myFunctionalInterface.myMethod();
    }
}
```

Explanation:
- Behind the scenes, it is creating a class implementing that functional interface and with method body as mentioned in lambda.