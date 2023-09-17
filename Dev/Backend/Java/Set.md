Initialize set:
```java
Set<String> set = new HashSet<>(Arrays.asList("one", "two", "three"));

Set<String> set = new HashSet<>(){{
    add("one");
    add("two");
    add("three");
}};
```

1. add()
2. contains()
3. remove()
4. isEmpty()
5. size()