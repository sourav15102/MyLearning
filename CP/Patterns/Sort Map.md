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
See: comparator Function [[Comparator Function]]

> this is an example of descending sort.
```java
Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
	public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
		return entry1.getKey().compareTo(entry2.getKey());
    }
});
```
