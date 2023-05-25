Entry Set:
```java
import java.util.*;

public class MapEntrySetExample {
    public static void main(String[] args) {
        // Create a HashMap
        Map<String, Integer> map = new HashMap<>();
        
        // Add some key-value pairs
        map.put("apple", 10);
        map.put("banana", 5);
        map.put("orange", 8);
        
        // Get the entry set
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        
        // Iterate over the entries
        for (Map.Entry<String, Integer> entry : entrySet) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            
            System.out.println("Key: " + key + ", Value: " + value);
        }
    }
}

```
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
