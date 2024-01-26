https://yuminlee2.medium.com/kruskals-algorithm-minimum-spanning-tree-db96e91d0aed

### Algorithm: Union Find
1. **Sort edges by ascending weight.**
2. **Initialize parent and size arrays for each node.**
   - Each node is initially its own parent.
   - Each tree has a size of 1.
3. **Initialize `edgeCount` to 0.**
4. **Pick the smallest edge:**
   - Find the root parents of the connected nodes.
   - If the nodes are already in the same tree, discard the edge.
   - If not, merge the smaller tree into the larger tree.
   - Increment the size of the larger tree.
   - Increment `edgeCount` by 1.
5. **Repeat step 4 until `edgeCount` equals `(numOfNodes - 1)`.**
6. **The resulting edges form the minimum spanning tree (MST).**

### Code:
```java
import java.util.*;
public class Solution {

    private static int find(int x, int[] p){
        if(x==p[x]){
            return x;
        }
        return find(p[x],p);
    }
    private static boolean union(int x, int y, int[] r, int[] p){
        int a = find(x,p);
        int b = find(y,p);
        if(a==b){
            return false;
        }
        if(r[a]>r[b]){
            p[b] = a;
            r[a] += r[b];
        }else{
            p[a] = b;
            r[b] += r[a];
        }
        return true;
    }
    public static int kruskalMST(int n,int [][]edges) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[2]));
        for(int[] e: edges){
            pq.add(e);
        }
        int[] p = new int[n+1];
        int[] r = new int[n+1];
        for(int i=1;i<=n;i++){
            p[i] = i;
            r[i] = 1;
        }

        int[] tmp;
        int ans = 0;
        while(!pq.isEmpty()){
            tmp = pq.poll();
            if(union(tmp[0],tmp[1],r,p)){
                ans += tmp[2];
            }
        }

        return ans;
    }
}
```