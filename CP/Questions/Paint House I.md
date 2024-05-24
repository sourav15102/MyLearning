https://www.lintcode.com/problem/515/description

```java
public class Solution {
    /**
     * @param costs: n x 3 cost matrix
     * @return: An integer, the minimum cost to paint all houses
     */
    private int sol(int[][] costs, int l, int m, int[][] dp){
        if(l<0){
            return 0;
        }

        if(dp[l][m]!=-1){
            return dp[l][m];
        }

        List<Integer> arr = new ArrayList<>();
        for(int i=0;i<3;i++){
            if(i==m){
                continue;
            }
            arr.add(costs[l][m] + sol(costs, l-1, i, dp));
        }

        return dp[l][m] = Collections.min(arr);

    }
    public int minCost(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n][3];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }

        return Math.min( Math.min(sol(costs, n-1, 0, dp), sol(costs, n-1, 1, dp)), sol(costs, n-1, 2, dp) ); 
    }
}
```