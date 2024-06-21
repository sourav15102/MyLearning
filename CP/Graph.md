Link: https://cp-algorithms.com/graph/depth-first-search.html
### Theory:
1. [[BFS]]
	1. when distances are same, bfs can be used to see if path exists or whats the shortest distance or even how many shortest or al paths exist.
	- for shortest path: we need to check if current distance is better than proposed one or not.
	- [[126]] (read comment in code here)
	- [[0-1 BFS]]
	- [[787]]***
	- [[Multisource BFS]]
		- [[542]]
		- [[1020]]
	- General rule of thumb: when putting x's children into queue, make them visited before putting them in queue, so you dont end up with duplicate values in queue.
1. DFS
	1. [[DFS CAUTION]]
2. Paths
	1. [[Dijkstra]] 
		1. Read 'Important points' inside here.
		2. Read 'Using lambda' inside [[Comparator Function]]
	2. [[Bellman Ford]] (Can detect -ve cycle.)
	3. [[Single-Single Shortest Path]]
	4. [[Floyd Warshall Algorithm]] (All Pair Shortest Algorithm)
		1. [[1334]]
3. Cycle detection
	1. [[207]]** cycle in directed graph.
	2. [[Eulerian path and circuits]]
	3. Negative cycle: [[Bellman Ford]] & [[Floyd Warshall Algorithm]]
	4. Undirected graph, **Union find and dfs**
		1. Property: if `no. of nodes == no. of edges`, then there is bound to be a cycle. 
		2. [[684]]
5. Topological sort [[210]]*
4. Connected components: [[Discovery vs Lowest time]]
	3. Find bridges: https://cp-algorithms.com/graph/bridge-searching.html
		1. offline: (Tarjan's algorithm) [[1192]] *an imp note here*
	4. SCC: [[Kosaraju]]
	5. [[Articulation]] (Trajan's Algo)
	6. Strong orientation
5. Bipartite graph [[785]]
6. [[Spanning Trees]]
	1. [[Prims]]
	2. [[Kruskal]]
7. Dynamic Graph using union find
	1. [[352]]**
	2. [[827]]**
8. [[Second Best MST]]
9. Flow Algorithms

> Alternative to mark something as visited via `boolean[]` we can use a HashSet instead. 


Finding Bridges algorithm (offline): https://www.youtube.com/watch?v=qrAub5z8FeA
Idea: For a vertex 'v', when were doing dfs traversal, the edge 'e' between v and p  can be a bridge if-and-only-if there is no backedge from p's descendents to v's ancestors in dfs traversal. 
### Questions:
Coding Ninjas:
- [[Distinct Islands]]**
LC:
- [[133]]
- [[127]]**
- [[126]]***
- [[785]]**
- [[417]]*
- [[130]]
- [[994]]**
- [[663]] (walls and gates)* **(multisource bfs)**
- [[207]]**
- [[210]]*
- [[684]]
- [[733]] (easy but an interesting catch)
- [[542]] ([[Multisource BFS]])
- [[1020]] ([[Multisource BFS]])
- [[802]]**
- [[269 - Alien Dictionary]]**
- [[1334]]
- Dij:
- [[743]]
- [[1976]]**
- Min stops with K stops:
- [[787]]
- Graphs using Union-find:
- [[352]]**
- [[827]]**
	- Combine all nodes in a component under one hood.
- [[Number of Island II]]*
- [[1319]]**
- Eulerian
- [[332]]
- [[1192]]

Pending:
- [[332]]
- [[787]]**
- [[947]]
- https://www.codingninjas.com/studio/problems/articulation-point_840708?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
