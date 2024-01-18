### Algorithm:
1. Start with an arbitrary vertex as the initial tree.
2. Maintain a set V for visited vertices.
4. Repeat until all vertices are included:
   - Select the minimum weight edge that connects the current tree with a vertex outside the tree.
   - Add the selected edge and the connected vertex to the tree.
5. Terminate when the tree contains all vertices, forming the minimum spanning tree.


