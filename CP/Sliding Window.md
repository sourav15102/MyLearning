
### Theory:

In sliding window, we need to first identify the when the "array" will become "valid" and when is it not.
We take two pointers in general.
So, we move the first pointer until our "array" is "valid" and then we start moving the second one until it is again "invalid", it can vice-versa as well, depending upon the questions.

Basic template:

```java
        while(j<l){
            if(condition to go on){
                //operation
                j++;
            }else{
                while(condition to stop){
                    //oepration
                    i++;
                }
            }
        }
```
        
Monotnic Queue:
```
1. It helps you maintain the maximum or minimum within a queue.
2. if we have a window of size k: [ ..... k ..... ].
3. adn there are two elements somwhere like [... a ... b ... ]
4. and b is smaller than a: there is no chance 'a' will ever the be answer for the smallest number in queue or window, as along as b is there and since we are moving window from left to right, it will never be the case that a exists but b doesnt.
5. so, there is no point in keeping a.
```


### Questions:
1. [[1358]]
2. [1343](https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/)
3. [[3]]
4. [[76]]*
5. [[424]]
6. [[438]]*
7. [[239]]*
8. [[1838]]**
9. [[1888]]**
10. [[567]]

