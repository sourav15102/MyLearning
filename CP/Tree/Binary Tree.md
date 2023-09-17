
### Theory:

Traversal:
DFS:
1. preorder traversal
2. inorder traversal
3. postorder traversal
BFS:
1. level order traversal

### Properties:
1. The maximum number of nodes at level 'l' of a binary tree is 2^l.
2. The Maximum number of nodes in a binary tree of height 'h' is (2^h) - 1.
	Derivation
	 ```markdown
   - 1 + 2 + 4 + 2^h = summation is (2^h) - 1
   ```
3. In a binary tree with N nodes, the minimum possible height or the minimum number of levels is ⌊log2(N+1)⌋.
   **Derivation**:
   ```markdown
   - Each level can have at least one element.
   - The height of the tree cannot exceed N.
   - A binary tree of height 'h' can have a maximum of 2^h - 1 nodes.
   - Therefore, the minimum height possible is ⌊log2(N + 1)⌋.
   ```
4. A Binary Tree with L leaves has at least ⌊log2(L)⌋ + 1 levels.
   **Derivation**:
   ```markdown
   - A binary tree with maximum leaves has all levels fully filled.
   - Assuming all leaves are at level 'l', we have L <= 2^l - 1.
   - Thus, the minimum number of levels is ⌊log2(L)⌋ + 1.
   ```
5. In a Binary tree where every node has 0 or 2 children(we are talking about full binary tree), the number of leaf nodes is always one more than nodes with two children: L = T + 1.
   **Derivation**:
   ```markdown
   - The total number of leaf nodes (L) in a binary tree is 2^h - 1, where 'h' is the height.
   - The total number of internal nodes (T) with two children is 0.
   - Hence, L = T + 1.
   ```
6. In a non-empty binary tree, if n is the total number of nodes and e is the total number of edges, then e = n-1.

### Types:
**Based on the Number of Children:**
1. **Full Binary Tree:**
    - Every node has 0 or 2 children.
    - All nodes except leaf nodes have two children.
2. **Degenerate (or Pathological) Tree:**
    - Every internal node has only one child.
    - Similar to a linked list in terms of performance.
3. **Skewed Binary Tree:**
    - Pathological tree dominated by left or right nodes.
    - Two types: left-skewed and right-skewed.

**Based on the Completion of Levels:**
1. **Complete Binary Tree:**
    - All levels are filled except possibly the last level.
    - Leaf elements lean towards the left.
    - The last leaf might not have a right sibling.
2. **Perfect Binary Tree:**
    - All internal nodes have two children.
    - All leaf nodes are at the same level.
    - The number of leaf nodes equals the number of internal nodes plus one (L = I + 1).
3. **Balanced Binary Tree:**
    - Height of the tree is O(log n) where n is the number of nodes.
    - Examples include AVL and Red-Black trees.
    - Provides efficient search, insert, and delete operations.

**Special Types Based on Node Values:**

1. **Binary Search Tree (BST):**    
    - Left subtree contains nodes with keys less than the parent's key.
    - Right subtree contains nodes with keys greater than the parent's key.
2. **AVL Tree:**
    - Self-balancing BST where height differences are at most one for all nodes.
3. **Red-Black Tree:**
    - Self-balancing BST using colors to maintain balance during operations.
4. **B-Tree:**
    - Self-balancing tree for efficient access, insertion, and deletion of data.
    - Used in databases and file systems.
5. **B+ Tree:**
    - Variation of B-Tree optimized for file systems and databases.
    - All data stored in leaf nodes; internal nodes for indexing.
6. **Segment Tree:**
    - Used for storing information about intervals or segments.
    - Supports queries for intervals containing a given point
### Insertion in Normal tree (not BST)
- Insertion of a node in tree while level order traversing, add wherever you find first null
	- do the bfs level order traversal, and then add node whenever you find null.
### Deletion in Normal tree (not BST)
- Delete node in tree and replace it with deepest rightmost node in the tree.
- Algo:
	1. traverse level order and find the node that we need to delete.
	2. last node in the queue will be the rightmost in the tree.
	3. keep track of parent of each node added to queue.
	4. and delete the rightmost node and put its value in the deleted one.

> [Read some other important traversals](https://www.geeksforgeeks.org/binary-tree-data-structure/)

### Tools
- calculate depth
- spiral level traversal using: stack, recursion, queue
- LCA
- diameter
### Questions:
- [[104]] (depth)
- [[543]] (diameter)
- [[110]]
- 