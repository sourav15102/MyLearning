 https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/

> Works for -ve weights.
> Can detect -ve cycles.
> Assumption:
> - For shortest path, -ve cycles shouldnt be there.
### Algorithm:
- have V as number of vertices, E as set of (u,v,w) `w as weight of edge u-->v`.
- We will run function V-1 times.
- for V-1 times, we will update all edges.
- Update: meaning if new distance is < than current one, then update it.
- If any distances still gets updated Vth time, then it would mean that a -ve cycle exists.
### How to print paths:
- While updating each node, we can have another array as parent, we we can keep parent of ith node as  `parent[i]`.

```java
import java.util.*;
public class Solution {
    private static final int INF = 100000000;
    private static void relax(List<List<Integer>> edges, int ind, int[] dis){
        List<Integer> ed = edges.get(ind);
        int s = dis[ed.get(0)];
        int t = dis[ed.get(1)];
        int w = ed.get(2);

        if((s+w)<t){
            dis[ed.get(1)] = s+w;
        }
    }
    public static int[] bellmonFord(int n, int m, int src, List<List<Integer>> edges) {
       int[] dis = new int[n+1];
       Arrays.fill(dis,INF);

       dis[src] = 0;

       for(int i=0;i<(n-1);i++){
           for(int j=0;j<m;j++){
               relax(edges, j, dis);
           }
       } 

       return dis;
    }
}

```