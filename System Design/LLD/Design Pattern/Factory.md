### Define:
**Factory Method** is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.

### Problem: 
If our client code is dependent on one 'kind' of object(which can change), it will be less scalable.

### Solution:
![[Pasted image 20230814215128.png]]

### When to use:
Use the Factory Method when you don’t know beforehand the exact types and dependencies of the objects your code should work with.

Here’s the **Factory Method Design Pattern** example implemented in **Java**.

---

### **Step-by-Step Implementation**

#### **Step 1: Define the Product Interface**

The `Shape` interface will define the common behavior for all shapes.

```java
// Product Interface
public interface Shape {
    void draw();
}
```

---

#### **Step 2: Create Concrete Products**

Implement specific shapes, such as **Circle** and **Rectangle**.

```java
// Concrete Product 1
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

// Concrete Product 2
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle");
    }
}
```

---

#### **Step 3: Define the Creator (Abstract Class)**

The abstract factory declares a factory method `createShape()` that will be overridden by subclasses.

```java
// Abstract Creator
public abstract class ShapeFactory {
    public abstract Shape createShape();
}
```

---

#### **Step 4: Implement Concrete Creators**

Concrete factories override the `createShape()` method to create specific shapes.

```java
// Concrete Creator 1
public class CircleFactory extends ShapeFactory {
    @Override
    public Shape createShape() {
        return new Circle();
    }
}

// Concrete Creator 2
public class RectangleFactory extends ShapeFactory {
    @Override
    public Shape createShape() {
        return new Rectangle();
    }
}
```

---

#### **Step 5: Client Code**

The client uses the factories without knowing the exact implementation details of the shapes.

```java
public class Client {
    public static void main(String[] args) {
        // Create a Circle using CircleFactory
        ShapeFactory circleFactory = new CircleFactory();
        Shape circle = circleFactory.createShape();
        circle.draw(); // Output: Drawing a Circle

        // Create a Rectangle using RectangleFactory
        ShapeFactory rectangleFactory = new RectangleFactory();
        Shape rectangle = rectangleFactory.createShape();
        rectangle.draw(); // Output: Drawing a Rectangle
    }
}
```

---

### **How It Works**

1. **`Shape` Interface**:
    - Provides the common contract for all shapes (`draw()` method).
2. **Concrete Shapes**:
    - `Circle` and `Rectangle` implement `Shape`.
3. **`ShapeFactory` Abstract Class**:
    - Defines the factory method `createShape()` as abstract.
4. **Concrete Factories**:
    - `CircleFactory` and `RectangleFactory` override `createShape()` to instantiate their respective products.
5. **Client Code**:
    - Works with the `ShapeFactory` abstraction, making it flexible to add new shapes in the future without changing existing code.

---

### **Class Diagram (Java Implementation)**

The structure remains the same as the earlier UML diagram. Let me know if you'd like me to adapt it further for the Java example!

