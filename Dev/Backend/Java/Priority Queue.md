   ```java
   import java.util.PriorityQueue;
   ```

2. **Create a Priority Queue:**
   ```java
   PriorityQueue<Integer> minHeap = new PriorityQueue<>();
   PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
   ```

3. **Adding Elements:**
   ```java
   minHeap.add(5);
   minHeap.offer(3);
   ```

4. **Removing Elements:**
   ```java
   int highestPriority = minHeap.poll();
   ```

5. **Peeking at the Top Element:**
   ```java
   int topElement = minHeap.peek();
   ```

6. **Custom Comparators:**
   ```java
   PriorityQueue<CustomObject> maxHeap = new PriorityQueue<>(new AgeComparator());
   ```

```java
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}

class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        // Compare based on age in reverse order for a max-heap
        return Integer.compare(person2.getAge(), person1.getAge());
    }
}
```
Side note about compare function:
```java
- Return Value:
    - If `compare(o1, o2)` returns a negative integer, it means `o1` is considered "smaller" or "comes before" `o2` in the ordering you define.
    - If `compare(o1, o2)` returns a positive integer, it means `o1` is considered "larger" or "comes after" `o2`.
    - If `compare(o1, o2)` returns 0, it means `o1` and `o2` are considered equal in the ordering.
```

7. **Iterating through a Priority Queue:**
   ```java
   while (!minHeap.isEmpty()) {
       int element = minHeap.poll();
       // Process the element
   }
   ```