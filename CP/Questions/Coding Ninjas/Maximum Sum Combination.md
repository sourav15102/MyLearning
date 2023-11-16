
Question link: https://www.codingninjas.com/studio/problems/k-max-sum-combinations_975322?leftPanelTabValue=SUBMISSION
### Idea 1: (more memory)
- To sort both arrays in desc order.
![[Pasted image 20231111145029.png]]
### Code:
```java
import java.util.*;

public class Solution {
    private static void reverse(int[] x){
        int i = 0;
        int j = x.length-1;
        int tmp;
        while(i<j){
            tmp = x[i];
            x[i] = x[j];
            x[j] = tmp;
            i++;
            j--;
        }
    }
    public static int[] kMaxSumCombination(int[] A, int[] B, int n, int k){
        int aPt = 0;
        int bPt = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        Arrays.sort(A);
        Arrays.sort(B);
        reverse(A);
        reverse(B);
        int[] ans = new int[k];
        int i=0;
        int x,y;
        while(i<k){
            x = y = 0;
            while(x<bPt && x<A.length && bPt<B.length){
                pq.add(A[x]+B[bPt]);
                x++;
            }
            while(y<aPt && y<B.length && aPt<A.length){
                pq.add(B[y]+A[aPt]);
                y++;
            }
            if(aPt<A.length && bPt<B.length){
                pq.add(A[aPt]+B[bPt]);
            }
            aPt++;
            bPt++;
            ans[i] = pq.poll();
            i++;
        }

        return ans;
    }
}
```

