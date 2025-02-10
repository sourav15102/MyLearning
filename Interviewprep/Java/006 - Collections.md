![[Pasted image 20250206184529.png]]
### ArrayList vs LinkedList

| **Operation**                  | **ArrayList Code (TC)**               | **LinkedList Code (TC)**          |
| ------------------------------ | ------------------------------------- | --------------------------------- |
| **Access (`get()`)**           | `list.get(index); // O(1)`            | `list.get(index); // O(n)`        |
| **Modify (`set()`)**           | `list.set(index, value); // O(1)`     | `list.set(index, value); // O(n)` |
| **Append (`add()`)**           | `list.add(value); // O(1) amortized`  | `list.add(value); // O(1)`        |
| **Insert at Index**            | `list.add(index, value); // O(n)`     | `list.add(index, value); // O(n)` |
| **Remove at Index**            | `list.remove(index); // O(n)`         | `list.remove(index); // O(n)`     |
| **Remove First (`poll()`)**    | `list.remove(0); // O(n)`             | `list.pollFirst(); // O(1)`       |
| **Remove Last (`pollLast()`)** | `list.remove(list.size()-1); // O(1)` | `list.pollLast(); // O(1)`        |
| **Contains (`contains()`)**    | `list.contains(value); // O(n)`       | `list.contains(value); // O(n)`   |
| **Size (`size()`)**            | `list.size(); // O(1)`                | `list.size(); // O(1)`            |

### Unmodifiable list
1. Collections.unmodifiableList()
2. Arrays.asList()
3. List.of()

### **`Arrays.asList()` in Java**
📌 **What it does?**
- Converts an **array into a fixed-size list** (backed by the original array).
- The list **allows modification** (`set()`) but **does not allow adding/removing elements**.
- **Faster than manually iterating and adding elements**.
```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry");

        System.out.println(fruits); // ✅ Output: [Apple, Banana, Cherry]

        fruits.set(1, "Mango"); // ✅ Allowed, modifies the list
        System.out.println(fruits); // ✅ Output: [Apple, Mango, Cherry]

        // fruits.add("Orange"); ❌ UnsupportedOperationException
        // fruits.remove("Apple"); ❌ UnsupportedOperationException
    }
}
```

### **`List.of()` in Java (Java 9+)**
📌 **What it does?**
- Creates an **immutable list** (cannot be modified after creation).
- **Faster** than `Arrays.asList()` because it **doesn’t allow modifications**.
- Returns an **optimized implementation** (not `ArrayList` or `LinkedList`).

## **✅ Example Usage**

```java
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> fruits = List.of("Apple", "Banana", "Cherry");

        System.out.println(fruits); // ✅ Output: [Apple, Banana, Cherry]

        // fruits.add("Mango");  ❌ Throws UnsupportedOperationException
    }
}
```

### How can you make Arraylist synchronized?
Collections.synchronizedList

> LinkedHashSet: maintians the order in which the elements were added.

#### You need to use a HashMap where the keys are complex objects, such as a Person class with attributes like name, age, and address. How would you design this key class to ensure that it works correctly in a HashMap?
Answer:
To design the Person class as a key in a HashMap:
1. override the hashCode() method to generate a consistent hash code based on significant fields, such as name and age.
2. override the equals() method to compare the significant fields and ensure that two Person objects are considered equal only if they have the same name, age, and address
3. ensure that these fields are immutable to maintain consistent behavior of the hash code and equality over the lifetime of the key.


### What would happen if you override only the equals() method and not hashCode() in a custom key class used in HashMap?
Answer:
Overriding only the equals() method and not hashCode() can lead to inconsistent behavior.
Two keys that are considered equal by equals() might not have the same hash code, causing them to be placed in different buckets.
This inconsistency can result in incorrect retrieval of values and can break the contract of the HashMap, leading to subtle bugs.

### What would happen if you override only the equals() method and not hashCode() in a custom key class used in HashMap?
Answer:
Overriding only the equals() method and not hashCode() can lead to inconsistent behavior.
Two keys that are considered equal by equals() might not have the same hash code, causing them to be placed in different buckets.
This inconsistency can result in incorrect retrieval of values and can break the contract of the HashMap, leading to subtle bugs.


### How would you implement a thread-safe HashMap without using ConcurrentHashMap?
Answer:
You can make a HashMap thread-safe by:
1. Using Collections.synchronizedMap(new HashMap<>()), which wraps the HashMap with synchronized methods.
2. Manually synchronizing access to the HashMap by using synchronized blocks around the critical sections of code that access or modify the map.
Implementing your own lock mechanisms, like using ReentrantLock, to control access to the HashMap at a finer granularity (e.g., segmenting the map).

### What were the changes made to HashMap implementation in Java 8?
Answer:
In Java 8, HashMap was optimized to improve performance when there are many hash collisions. Instead of using a linked list for bucket storage, Java 8 introduced a balanced tree (red-black tree) structure when the number of elements in a bucket exceeds a certain threshold (default is 8).
This change improves the worst-case time complexity for operations from O(n) to O(log n).


### Collections storing null values
**Summary Table:**

|   |   |   |   |
|---|---|---|---|
|Data Structure|Can Store null Key|Can Store null Elements|Underlying Structure|
|HashMap|Yes (1)|Yes|Hash Table|
|TreeMap|No|No|Red-Black Tree|
|TreeSet|N/A|No|Red-Black Tree (via TreeMap)|
|ArrayList|N/A|Yes|Array|
because it relies on Comparable or Comparator to sort the keys, and comparing null with any other key would result in a NullPointerException.

### What is the difference between HashMap and IdentityHashMap in terms of how they handle keys?
Answer:
The key difference between HashMap and IdentityHashMap is in how they compare keys:
1. HashMap uses the equals() method to compare keys and the hashCode() method to determine the bucket location.
2. IdentityHashMap uses the == operator to compare keys, meaning that keys are considered equal only if they are the same instance (reference equality).
> This is useful in scenarios where logical equality is not sufficient, and you need to distinguish between different instances of the same value.

### What is Map.Entry?
Answer:
- Map.Entry is a static nested interface within the Map interface in Java. 
- When you iterate over a Map's entries, you're working with objects that implement the Map.Entry interface. 

### How does Collections.sort() work internally?
Answer:
Collections.sort() internally uses the List.sort() method, which was introduced in Java 8. This method, in turn, uses the TimSort algorithm, which is a hybrid sorting algorithm derived from merge sort and insertion sort.
TimSort is highly optimized for real-world data, especially for data that is already partially sorted. It has a worst-case time complexity of O(n log n), and it is stable, meaning it maintains the relative order of equal elements.

### What would happen if you try to sort a list containing null elements using Collections.sort()?
NullPointerException: we have seen before that if there is null value, it has hard time in sorting algos.

### Can you sort a list of custom objects using Collections.sort() without providing a Comparator?
Answer:
Yes, you can sort a list of custom objects without providing a Comparator if the objects implement the Comparable interface.
The Comparable interface requires the class to implement the compareTo() method, which defines the natural ordering of the objects. Collections.sort() will use this natural order to sort the list.
If the objects do not implement Comparable, a ClassCastException will be thrown.


### What is the difference between using Collections.sort() and Stream.sorted() in Java 8+ ?
Answer:
Collections.sort() sorts the list in place, modifying the original list.
Stream.sorted() returns a new stream that is sorted according to the provided comparator or the natural order. It does not modify the original list.
Stream.sorted() is more suitable for use cases where you want to keep the original list unmodified or when working with parallel streams to potentially improve performance with large datasets.