https://medium.com/@andreaspoyias/design-patterns-a-quick-guide-to-bridge-pattern-9ebf6a77baed

**Definition:**

- A structural design pattern that decouples an abstraction from its implementation so they can vary independently.
- Helps separate high-level abstraction from low-level implementation logic, improving code clarity and scalability.

---

### **Problem Addressed by Bridge Design Pattern**

- Large, complex classes mixing high-level logic with implementation details become difficult to manage.
- Changes to one part of a class require modifying other parts, increasing coupling.
- Adding new functionality leads to redundant code if behaviors are tightly coupled to classes.

**Example Problem:**

- Different animals move in distinct ways (walking, swimming, flying).
- Without the Bridge Pattern, each animal class must implement its movement logic directly, leading to redundant and unmanageable code.

---

### **Solution**

- Use **Abstraction** for high-level behavior (e.g., Animal with `move()` method).
- Separate **Implementation** logic (e.g., walking, flying) into its own hierarchy.
- Connect Abstraction and Implementation through composition, where Abstraction contains a reference to the Implementation object.

---

### **Java Example**

#### **Step 1: Implementation Interface and Concrete Classes**

```java
interface MoveLogic {
    void move();
}

class Walk implements MoveLogic {
    public void move() {
        System.out.println("Move alternating legs");
    }
}

class Fly implements MoveLogic {
    public void move() {
        System.out.println("Flap wings");
    }
}
```

#### **Step 2: Abstraction and Concrete Classes**

```java
abstract class Animal {
    protected MoveLogic moveLogic;

    public Animal(MoveLogic moveLogic) {
        this.moveLogic = moveLogic;
    }

    abstract void howDoIMove();
}

class Person extends Animal {
    public Person(MoveLogic moveLogic) {
        super(moveLogic);
    }

    public void howDoIMove() {
        moveLogic.move();
    }
}

class Bird extends Animal {
    public Bird(MoveLogic moveLogic) {
        super(moveLogic);
    }

    public void howDoIMove() {
        moveLogic.move();
    }
}
```

#### **Step 3: Client Code**

```java
public class Main {
    public static void main(String[] args) {
        MoveLogic walk = new Walk();
        MoveLogic fly = new Fly();

        Animal person = new Person(walk);
        Animal bird = new Bird(fly);

        person.howDoIMove(); // Output: Move alternating legs
        bird.howDoIMove();   // Output: Flap wings
    }
}
```

---

### **Key Benefits**

1. **Reduced Complexity:**
    - High-level abstraction focuses on object definitions.
    - Low-level implementation handles specific behavior.
2. **Extensibility:**
    - Easily add new implementations or abstractions.
3. **Reusability:**
    - Share implementation logic across multiple abstractions.

### **Drawbacks**

- Increased number of classes due to separation.
- Slightly more complex structure than tightly coupled alternatives.

---

Let me know if you need any further details or enhancements!