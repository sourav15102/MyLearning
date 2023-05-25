On key:

Using TreeMap :
```java
// INC order
Map<String, Integer> unsortedMap = new HashMap<>();
        unsortedMap.put("apple", 5);
        unsortedMap.put("banana", 2);
        unsortedMap.put("cherry", 8);
        unsortedMap.put("date", 1);
        unsortedMap.put("elderberry", 3);
        
TreeMap<String, Integer> sortedMap = new TreeMap<>(unsortedMap);

// DEC order
TreeMap<String, Integer> sortedMap = new TreeMap<>(Collections.reverseOrder());
sortedMap.putAll(unsortedMap);
```

Using Collections:
```
Basic pattern about Collections is as follows:
Collections.sort(x, func);
for example:

List<Integer> numbers = Arrays.asList(10, 5, 8, 3, 1);
Collections.sort(numbers, Collections.reverseOrder());
System.out.println(numbers);
// Output: [10, 8, 5, 3, 1]
```

### Comparator Function
See: comparator Function [[Comparator Function]]

```
we can convert the map to list and the sort list using Collections.sort()
```

```java
```java
public class Testing {
    public static void main(String[] args) {
        HashMap<String, Double> map = new HashMap<String, Double>();
        ValueComparator bvc = new ValueComparator(map);
        TreeMap<String, Double> sorted_map = new TreeMap<String, Double>(bvc);

        map.put("A", 99.5);
        map.put("B", 67.4);
        map.put("C", 67.4);
        map.put("D", 67.3);

        System.out.println("unsorted map: " + map);
        sorted_map.putAll(map);
        System.out.println("results: " + sorted_map);
    }
}

class ValueComparator implements Comparator<String> {
    Map<String, Double> base;

    public ValueComparator(Map<String, Double> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with
    // equals.
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}
```
```

> this is an example of descending sort.
```java
Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
	public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
		return entry1.getKey().compareTo(entry2.getKey());
    }
});
```