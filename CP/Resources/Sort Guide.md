Apologies for the oversight. Here are the sorting examples for all data structures:

### Data Structures:

1. ArrayList
2. LinkedList
3. Set (HashSet)
4. Map (HashMap)
5. PriorityQueue

### Sorting Examples:

#### 1. ArrayList:

```java
List<Node> arrayList = new ArrayList<>();
// Add nodes to arrayList

// 1. Sort by 'a' ascending
arrayList.sort(Comparator.comparing(Node::getA));

// 2. Sort by 'b' ascending when 'a' is clashed
arrayList.sort(Comparator.comparing(Node::getA).thenComparing(Node::getB));

// 3. Sort by 'a' descending
arrayList.sort(Comparator.comparing(Node::getA).reversed());

// 4. Sort by 'b' descending when 'a' is clashed
arrayList.sort(Comparator.comparing(Node::getA).thenComparing(Node::getB).reversed());
```

#### 2. LinkedList:

```java
LinkedList<Node> linkedList = new LinkedList<>();
// Add nodes to linkedList

// 1. Sort by 'a' ascending
linkedList.sort((node1, node2) -> Integer.compare(node1.getA(), node2.getA()));

// 2. Sort by 'b' ascending when 'a' is clashed
linkedList.sort((node1, node2) -> {
    if (node1.getA() == node2.getA()) {
        return Integer.compare(node1.getB(), node2.getB());
    }
    return Integer.compare(node1.getA(), node2.getA());
});

// 3. Sort by 'a' descending
linkedList.sort((node1, node2) -> Integer.compare(node2.getA(), node1.getA()));

// 4. Sort by 'b' descending when 'a' is clashed
linkedList.sort((node1, node2) -> {
    if (node1.getA() == node2.getA()) {
        return Integer.compare(node2.getB(), node1.getB());
    }
    return Integer.compare(node1.getA(), node2.getA());
});
```

Apologies for the oversight. Let me provide sorting examples for all four scenarios for TreeSet and TreeMap:

#### 3. TreeSet:

```java
Set<Node> treeSet = new TreeSet<>((node1, node2) -> Integer.compare(node1.getA(), node2.getA()));

// Add nodes to treeSet
// Note: TreeSet automatically maintains elements in sorted order based on the comparator provided

// 1. Sort by 'a' ascending
// This is the default behavior of TreeSet

// 2. Sort by 'b' ascending when 'a' is clashed
Set<Node> treeSetByBWhenClashed = new TreeSet<>((node1, node2) -> {
    if (node1.getA() == node2.getA()) {
        return Integer.compare(node1.getB(), node2.getB());
    }
    return Integer.compare(node1.getA(), node2.getA());
});

// 3. Sort by 'a' descending
Set<Node> descendingTreeSetByA = new TreeSet<>((node1, node2) -> Integer.compare(node2.getA(), node1.getA()));

// 4. Sort by 'b' descending when 'a' is clashed
Set<Node> descendingTreeSetByBWhenClashed = new TreeSet<>((node1, node2) -> {
    if (node1.getA() == node2.getA()) {
        return Integer.compare(node2.getB(), node1.getB());
    }
    return Integer.compare(node1.getA(), node2.getA());
});
```

#### 4. TreeMap:

```java
Map<Integer, Node> treeMap = new TreeMap<>((key1, key2) -> Integer.compare(key1, key2));

// Add nodes to treeMap
// Note: TreeMap maintains keys in sorted order based on the comparator provided

// 1. Sort by 'a' ascending
// This is the default behavior of TreeMap

// 2. Sort by 'b' ascending when 'a' is clashed
Map<Integer, Node> treeMapByBWhenClashed = new TreeMap<>((key1, key2) -> {
    if (key1.equals(key2)) {
        return Integer.compare(treeMap.get(key1).getB(), treeMap.get(key2).getB());
    }
    return Integer.compare(key1, key2);
});

// 3. Sort by 'a' descending
Map<Integer, Node> descendingTreeMapByA = new TreeMap<>(Collections.reverseOrder());

// 4. Sort by 'b' descending when 'a' is clashed
Map<Integer, Node> descendingTreeMapByBWhenClashed = new TreeMap<>((key1, key2) -> {
    if (key1.equals(key2)) {
        return Integer.compare(treeMap.get(key2).getB(), treeMap.get(key1).getB());
    }
    return Integer.compare(key2, key1);
});
```

These examples cover all four scenarios for sorting using lambdas with TreeSet and TreeMap, as per your request. Let me know if you need further clarification!
#### 5. PriorityQueue:

```java
PriorityQueue<Node> priorityQueueAscA = new PriorityQueue<>(Comparator.comparing(Node::getA));
// Add nodes to priorityQueueAscA

PriorityQueue<Node> priorityQueueAscB = new PriorityQueue<>((node1, node2) -> {
    if (node1.getA() == node2.getA()) {
        return Integer.compare(node1.getB(), node2.getB());
    }
    return Integer.compare(node1.getA(), node2.getA());
});
// Add nodes to priorityQueueAscB

PriorityQueue<Node> priorityQueueDescA = new PriorityQueue<>((node1, node2) -> Integer.compare(node2.getA(), node1.getA()));
// Add nodes to priorityQueueDescA

PriorityQueue<Node> priorityQueueDescB = new PriorityQueue<>((node1, node2) -> {
    if (node1.getA() == node2.getA()) {
        return Integer.compare(node2.getB(), node1.getB());
    }
    return Integer.compare(node1.getA(), node2.getA());
});
// Add nodes to priorityQueueDescB
```
