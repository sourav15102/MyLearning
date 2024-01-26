It is a single source shortest path algorithm, where we have a source and we want to calculate the shortest path to each node from source. 
> Assumption: No negative weights are present.
### Intuition
- we are assuming that in beginning we only know source, and no-one else, so as we discover nodes, lets say `x,y,z` now the one which is closest to source, we will go there and try to discover rest of the nodes, because it will give us best chance of discovering minimum weights, `(HOWEVER: We could have visited one which is farther from source and it might have helped us discover shorter distances with negative weights, A: Yes, thats why DIjkstra wont work for -ve distances.)`.
### Algorithm
- Have distance and visited array for all nodes.
- Mark distance of source as 0, and inf for rest.
- put them all in min priority queue.
- pop minimum weighted node that is NOT present in visited, update its neighbors' weights, if updated put them in queue again `(wont it add duplicates to Priority queue?, A: it would but it wont matter as the updated (min) weight will be on top and will be chosen first, will go to visited array already and when it appears second time, wont be considered)`.
### How to print paths:
- While updating each node, we can have another array as parent, we we can keep parent of ith node as  `parent[i]`.
- [[1976]]

##### Important points:
- In this case mark node visited after polling out of queue, unlike BFS.
	- why? 
		1. when the node is in the queue, but not at the top, we can find a better distance for that node.
		2. once node is polled out of queue, meaning it has the smallest distance, (inferring others in queue have higher distance), so there is no way that those others (with higher distance)+something can make it lesser than < the current smallest one in queue (GIVEN there are no -ve weights).
- Node in graph and Node in pq meant different things.
	- Node in graph: d=vertex and w = weight between vertex i and vertex d
	- Node in pq: d= vertex and w = global distance b/w d and source.

https://www.codingninjas.com/studio/problems/shortest-path-in-dag_8381897?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
```java
import java.util.*;
public class Solution {
    private static final int INF = Integer.MAX_VALUE;   
    private static class Node{
        int d;
        int w;
        Node(int a, int b){
            this.d = a;
            this.w = b;
        }
    }
    private static List<Node> get(Node s, List<Node>[] g, boolean[] vis){
        List<Node> ans = new ArrayList<>();
        for(Node ch: g[s.d]){
            if(!vis[ch.d]){
                ans.add(ch);
            }
        }
        return ans;
    } 
    private static void dij(PriorityQueue<Node> pq, List<Node>[] g, int[] d, boolean[] vis){
        Node s;
        while(!pq.isEmpty()){
            s = pq.poll();
            if(vis[s.d]){
                continue; //even if we dont do it, its fine because since s.d with lessser dis has already been visisted, now when we have one instance with higher dis, none of its children will accept higher dis coming from it.
            }
            vis[s.d] = true;
            for(Node ch: get(s, g, vis)){
                if(d[ch.d]>(d[s.d]+ch.w)){
                    d[ch.d] = d[s.d]+ch.w;
                    pq.add(new Node(ch.d, d[ch.d]));
                }
            }
        }
    }
    public static int[] shortestPathInDAG(int n, int m, int [][]edges) {
        List<Node>[] g = new List[n];
        for(int i=0;i<n;i++){
            g[i] = new ArrayList<>();
        } 

        for(int[] edge: edges){
            g[edge[0]].add(new Node(edge[1], edge[2]));
        }

        int[] d = new int[n];
        boolean[] vis = new boolean[n];
        Arrays.fill(d, INF);
        Arrays.fill(vis, false);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(node -> node.w));

        d[0] = 0;
        pq.add(new Node(0,d[0]));

        dij(pq, g, d, vis);

        for(int i=0;i<n;i++){
            if(d[i]==INF){
                d[i]=-1;
            }
        }

        return d;
    }
}
```