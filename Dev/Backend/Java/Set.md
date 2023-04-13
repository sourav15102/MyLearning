Initialize set:
```java
Set<String> set = new HashSet<>(Arrays.asList("one", "two", "three"));

Set<String> set = new HashSet<>(){{
    add("one");
    add("two");
    add("three");
}};
```