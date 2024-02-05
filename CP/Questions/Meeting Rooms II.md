
### Idea:
- Uses same concept as [[435]].
#### Code:
```java
import java.util.*;
class Solution {

    private boolean overlap(int[] a, int[] b){
        return a[0]<b[1] && b[0]<a[1];
    }
    private int sol(int[][] meetings){
        Arrays.sort(meetings, (int[] a, int[] b) -> {
            if(a[1]!=b[1]){
                return a[1]-b[1];
            }else{
                return a[0]-b[0];
            }
        });

        int i,j;
        i=0;
        int[] tmp;
        int ans,pt;
        ans = 1;
        while(i<meetings.length){
            tmp = meetings[i];
            j = i+1;
            pt=1;
            while(j<meetings.length && overlap(tmp, meetings[j])){
                pt++;
                j++;
            }
            i = j;
            ans = Math.max(ans, pt);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] meetings = {{0, 12},{5, 10},{15, 20}};
        //int[][] meetings = {{0, 7},{5, 10},{2, 20}};
        //int[][] meetings = {{7,10},{2,4}};
        Solution solution = new Solution();
        System.out.println(solution.sol(meetings));
    }
}
```