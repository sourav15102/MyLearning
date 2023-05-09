
### Theory:

Stack follows LIFO:
Basic functions:
```java
Stack<Integer> stack = new Stack<Integer>();

stack.push(10);
int element = stack.pop();
int element = stack.peek();
boolean isEmpty = stack.empty();

Iterator<String> iterator = stack.iterator();
while (iterator.hasNext()) {
    String fruit = iterator.next();
    System.out.println(fruit);
}
```
Basic pattern:
```java
        for(/**/){
            while(!st.isEmpty() && /*condition*/){
                //do something
                st.pop();
            }
            st.push(/**/);
        }
```

### Quick Questions:
1. [[20]]
2. [[71]]
3. [[84]]
4. [[22]]
5. [[155]]
7. [[735]]
8. [[1856]]
9. [[853]]
10. [[225]]
11. [[901]]
12. [[402]]
13. [[895]]**
14. [[1209]]
15. 


> hashset contains function has average o(1) TC.