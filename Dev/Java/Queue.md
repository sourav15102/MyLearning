Functions:
1. add: adds element
2. poll: will pop the element and return it
3. peek: can just see the element
4. isEmpty: tells if queue is empty
5. clear: removes all element from queue
6. contains(Object o) will return true it the object is present
7. size() gets the size

Comparator:
Iterator:

### Deque:
- its an interface implemented by ArrayDeque.
- it is a double ended queue.
- it can used as both stack and queue.
Functions:
- add: by default add at tail
- addLast: add at tail
- addFirst: add at head.
- push: add at first,, for stack.
- getFirst: get from head, doesnt remove
- getLast: get from tail, doesnt remove
- peek: see at head, doesnt remove
- peekFirst: see at head
- peekLast: see at tail
- poll: get and delete from head
- pollFirst: from head
- pollLast: from tail
- pop(): delete from head adn return.



### LinkedlList:
### PriorityQueue:
- removing top elements take O(logn), but removing random element takes O(n).