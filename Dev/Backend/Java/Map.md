
Get values:
```java
Map<String, Integer> map = new HashMap<>();
map.put("apple", 3);
map.put("banana", 2);
map.put("orange", 1);

Collection<Integer> values = map.values();

ArrayList<Integer> arrayList = new ArrayList<>(values);

System.out.println(arrayList);
```

Get keys:
```java
Map<String, Integer> map = new HashMap<>();
map.put("apple", 3);
map.put("banana", 2);
map.put("orange", 1);

Set<String> keys = map.keySet();

System.out.println(keys);
```

Traverse:
```java
Map<String, Integer> map = new HashMap<>();
map.put("apple", 3);
map.put("banana", 2);
map.put("orange", 1);

for (Map.Entry<String, Integer> entry : map.entrySet()) {
    String key = entry.getKey();
    Integer value = entry.getValue();
    System.out.println("Key: " + key + ", Value: " + value);
}
```

Sort: [[Sort Map]]
