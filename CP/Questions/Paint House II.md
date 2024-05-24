https://www.lintcode.com/problem/516

### Code:
```java
import java.util.*;
public class Solution {
    /**
     * @param costs: n x k cost matrix
     * @return: an integer, the minimum cost to paint all houses
     */
     private int getMin(int[] arr, int ind){
         List<Integer> lst = new ArrayList<>();
         for(int i=0;i<arr.length;i++){
             if(i==ind){
                 continue;
             }
             lst.add(arr[i]);
         }
         return Collections.min(lst);
     }
    public int minCostII(int[][] costs) {
        if(costs.length==0){
            return 0;
        }
        for(int i=1;i<costs.length;i++){
            for(int j=0;j<costs[0].length;j++){
                costs[i][j] += getMin(costs[i-1], j);
            }
        }
        List<Integer> lst = new ArrayList<>();
        for(int x: costs[costs.length-1]){ lst.add(x); }
        return Collections.min(lst);
    }
}
```

