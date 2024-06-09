Q: Why BFS cant be used for weighted graph for shortest distance
A: Because, imagine if shortest distance to X is found after it has been added to q and polled out.

Q: How to print all shortest path and all paths, unweighted graph
A: maintain a distance array for each element, and whenever a same distance is coming from a different parent, just add that parent to X's parent list.