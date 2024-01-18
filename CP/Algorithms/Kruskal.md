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