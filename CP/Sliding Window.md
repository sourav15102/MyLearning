
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
        

### Quick Questions:
1. [[1358]]
2. [1343](https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/)
3. [[3]]
4. [[76]]

### Questions:

