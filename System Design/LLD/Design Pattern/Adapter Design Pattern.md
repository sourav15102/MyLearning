**Definition:**

- A structural design pattern allowing incompatible interfaces to work together.
- Introduces an adapter class as a bridge to facilitate communication between two unrelated interfaces.

---

### **Problem Addressed by Adapter Design Pattern**

- Reusing a class when its interface doesn't match client requirements.
- Making incompatible interfaces collaborate.
- Providing an alternative interface for a class.

**Real-life analogy:**

- A mobile charger acts as an adapter, converting power socket voltage to a device-compatible level.

---

### **Components of Adapter Design Pattern**

1. **Target:**
    
    - The interface expected by the client.
    - Defines the operations used by the client.
2. **Adaptee:**
    
    - The existing class or interface to be adapted.
    - Has a different interface from what the client requires.
3. **Adapter:**
    
    - A bridge between the Target and Adaptee.
    - Implements the Target interface while using the Adaptee to perform operations.

---

### **Types of Adapter Patterns**

1. **Class Adapter Pattern:**
    
    - Adapter class extends both Target and Adaptee.
    - Uses multiple inheritance (not always supported by all languages).
    - Inherits Adaptee behavior and adapts it to Target interface.
2. **Object Adapter Pattern:**
    
    - Adapter implements the Target interface and contains an Adaptee instance.
    - Uses composition for flexibility.
    - Delegates Target interface calls to the Adaptee instance.

---

### **Implementation Steps**

1. **Define Target Interface:**
    
    - Specifies operations expected by the client.
    
    ```java
    public interface IPhone {
        public void OnCharge();
    }
    ```
    
2. **Implement Adaptee Class:**
    
    - Existing component with incompatible interface.
    
    ```java
    public class Iphone4sCharger implements Charger {
        public void charge() {
            System.out.println("charging with 4s charger");
        }
    }
    ```
    
3. **Create Adapter Class:**
    
    - Implements Target and wraps Adaptee for compatibility.
    
    ```java
    public class Iphone4sTo6sAdapter implements Charger {
        private Iphone4sCharger iphone4sCharger = new Iphone4sCharger();
        public void charge() {
            iphone4sCharger.charge();
        }
    }
    ```
    
4. **Connect Client Code:**
    
    - Uses Adapter as a bridge to interact with Adaptee.
    
    ```java
    public class IPhone6s implements IPhone {
        private Charger charger;
        public IPhone6s(Charger adapter) {
            this.charger = adapter;
        }
        public void OnCharge() {
            charger.charge();
        }
    }
    ```
