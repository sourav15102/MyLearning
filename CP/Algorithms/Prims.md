### Algorithm:
1. Start with an arbitrary vertex as the initial tree.
2. Maintain a set V for visited vertices.
4. Repeat until all vertices are included:
   - Select the minimum weight edge that connects the current tree with a vertex outside the tree.
   - Add the selected edge and the connected vertex to the tree.
5. Terminate when the tree contains all vertices, forming the minimum spanning tree.

   
### Code:
```java
import java.util.*;
public class Solution {

	private static class Node{
		int d;
		int w;
		Node(int a, int b){
			this.d = a;
			this.w = b;
		}
	}

	private static void prims(PriorityQueue<List<Integer>> pq, List<Node>[] g, boolean[] vis, int ind){
		vis[ind] = true;
		List<Integer> lst;
		for(Node ch: g[ind]){
			if(!vis[ch.d]){
				lst = new ArrayList<>();
				lst.add(ind);
				lst.add(ch.d);
				lst.add(ch.w);
				pq.add(lst);
			}
		}
	}

	public static int minimumSpanningTree(ArrayList<ArrayList<Integer>> edges, int n) {
		int cnt = n;
		List<Node>[] g = new List[n];
		for(int i=0;i<n;i++){
			g[i] = new ArrayList<>();
		}
		for(ArrayList<Integer> e: edges){
			g[e.get(0)].add(new Node(e.get(1), e.get(2)));
			g[e.get(1)].add(new Node(e.get(0), e.get(2)));
		}
		boolean[] vis = new boolean[n];
		Arrays.fill(vis, false);
		PriorityQueue<List<Integer>> pq = new PriorityQueue<>(Comparator.comparing(arr -> arr.get(2)));

		prims(pq,g,vis,0);
		n--;
		List<Integer> lst;
		int ans=0;
		while(n>0){ 
			lst = pq.poll();
			if(vis[lst.get(1)]){
				continue;
			}
			ans = ans + lst.get(2);
			prims(pq,g,vis,lst.get(1));
			n--;
		}
		return ans;
	}
}
```