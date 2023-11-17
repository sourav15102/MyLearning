https://www.youtube.com/watch?v=7pnhv842keE

> ***CONDITION***: This algorithm ONLY works when we can guarantee that there will be a majority algorithm.

### Code:
```java
class Solution {
    public int majorityElement(int[] nums) {
        int win = nums[0];
        int cnt=0;
        for(int n: nums){
            if(n==win){
                cnt++;
            }else{
                cnt--;
            }

            if(cnt<0){
                cnt = 1;
                win = n;
            }
        }

        return win;
    }
}
```


Problem:
1. [[169]]
2. [[229]]
3. 
