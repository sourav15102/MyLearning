Some (but not all) single-source shortest paths algorithms can be easily modified to return the shortest path between two nodes by stopping the algorithm early. A simple example of this is breadth-first search in unweighted graphs. For example, to find the shortest path from a node u to a node v, start a BFS from u. As soon as v is found, the path from u to v discovered that way is the shortest path. Dijkstra’s algorithm can also do this: if you run a Dijkstra’s algorithm starting at node u, you can stop the algorithm as soon as you dequeue v from the priority queue to get the shortest path there.

when there are negative edge weights but no negative cycles, Bellman-Ford can still compute shortest paths. However, it may continually revise node distances as it runs, up to the last round of the algorithm. Cutting the search off early can give back the wrong answer.

But if you’re interested specifically in finding a path from one node to another, you might want to look at the A* search algorithm

ref: https://stackoverflow.com/questions/72197891/shortest-path-between-two-nodes-vs-shortest-path-from-one-node-to-all-other-node