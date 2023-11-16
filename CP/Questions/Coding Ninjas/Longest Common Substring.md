Link: https://www.codingninjas.com/studio/problems/longest-common-substring_1235207?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM

### Idea:
- start thinking from brute force.
- 2 arrays with indexes i and j.
- WE CANNOT DO same as subsequence, i.e., `dp[i][j] = max(dp[i][j-1], dp[i-1][j])` if `dp[i]!=dp[j]`
- In this case if `dp[i]!=dp[j], then dp[i][j]=0`
- WHY?
	- because the state of i and j cannot include results of i,j-1 or i-1,j. as it is substring, they need to be contiguous.
	- if it did, the following situation could arise:
		- 0  1  2  3  4 (indexes)
		- -   -  5  -  6
		- -   -  4  5 6
		- where since 6 and 6 are same 4th index, 3,3 index would include result from 2,3 index with 5 and 5 where 5 is not contiguous with 6 in upper arr.


### Code:
```java
import java.util.*;

public class Solution {
    private static int sol(char[] a, char[] b, int i, int j, int[][] dp){
        if(i<0 || j<0){
            return 0;
        }
        
        if(dp[i][j]!=-1){
            return dp[i][j];
        }

        if(a[i]==b[j]){
            dp[i][j] = 1 + sol(a,b,i-1,j-1,dp);
        }else{
            dp[i][j] = 0;
        }

        return dp[i][j];
    }
    public static int lcs(String str1, String str2){
        char[] a = str1.toCharArray();
        char[] b = str2.toCharArray();
        int l = a.length;
        int r = b.length;
        int ans = 0;
        int[][] dp = new int[l][r];
        for(int i=0;i<l;i++){
            Arrays.fill(dp[i],-1);
        }
        for(int i=l-1;i>=0;i--){
            for(int j=r-1;j>=0;j--){
                ans = Math.max(ans, sol(a,b,i,j,dp));
            }
        }
        return ans;
    }
}

```