```java
int compare(T o1, T o2);
```
where `o1` and `o2` are the two objects to be compared. The return value of the method is an integer that has the following properties:

-   If `o1` should come before `o2` in the sorted order, a negative integer is returned.
-   If `o1` and `o2` are equal, 0 is returned.
-   If `o1` should come after `o2` in the sorted order, a positive integer is returned.

```java
class Person {
    private String name;
    private int age;
    
    // constructor, getters, and setters omitted for brevity
    
    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

List<Person> people = Arrays.asList(
        new Person("Alice", 25),
        new Person("Bob", 30),
        new Person("Charlie", 20),
        new Person("David", 35),
        new Person("Eva", 28)
);

Collections.sort(people, new Comparator<Person>() {
    public int compare(Person p1, Person p2) {
        return p1.getAge() - p2.getAge();
    }
});

System.out.println(people);
// Output: [Charlie (20), Alice (25), Eva (28), Bob (30), David (35)]

```

##### Using lambda:
```java
class Node {
            public int a;
            public int b;

            public Node(int a, int b) {
                this.a = a;
                this.b = b;
            }
        }

        // Create a list of Node objects
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(new Node(5, 10));
        nodeList.add(new Node(2, 15));
        nodeList.add(new Node(8, 5));
        nodeList.add(new Node(1, 20));
        nodeList.add(new Node(1, 15));

        // Sort the list based on 'a' in ascending order, and if 'a' values are the same, sort by 'b' in descending order
        nodeList.sort(
                Comparator.comparing((Node node) -> node.a)
                          .thenComparing((Node node) -> node.b)
                          .reversed()
        );
```