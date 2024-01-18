https://www.topcoder.com/thrive/articles/kosarajus-algorithm-for-strongly-connected-components
### SCC:
Strongly Connected Components are subsets of vertices within a directed graph where every vertex is reachable from every other vertex in the subset. In simpler terms, it represents a group of nodes that can reach each other through directed paths. SCCs provide insights into the cohesive substructures and interconnections within a graph.

### Idea:
we must take a note on reversing all the edges of a graph. The type of graph won’t change, as a SCC will still remain a SCC.  Only the direction between components is reversed. This property is used in our article to detect SCCs.

### Algorithm:
Basically, we have forward and backward passes here.
In Forward Pass (dfs from 0):  We have all the nodes reachable BY 0 in graph collected in stack.
In backward pass (dfs from 0): We get all nodes reachable TO 0.

**PSEUDOCODE:**
- Perform DFS traversal of the graph. Push node to stack before returning.
- Find the transpose graph by reversing the edges.
- Pop nodes one by one from the stack and again to DFS on the modified graph.

Let’s show an example to support our algorithm:

![image](https://images.contentful.com/piwi0eufbb2g/6oUnkJaqwSOwrkEAbLAho3/d4aa587a030f9e870abc88e993cc0e2a/image.png)

We will begin DFS at 0 on the above graph and traverse every outgoing edge from each node that is unvisited. For visiting information we will make a visited array, and we should also maintain a stack to store the nodes in the order of which we exhausted their outgoing edges. Now we will see the result:

![image](https://images.contentful.com/piwi0eufbb2g/3ClZTtAmFjkwFR2jFmkUiv/56e7fa90f1eef283e2cc9e7eb4e1e3bc/image.png)

Now we pop the stack one by one and do DFS on the modified/traversed graph while popping nodes. At the end of each successful DFS we will have an SCC.

Now go ahead and perform this. For the sake of the article I won’t be illustrating it (might take too many pages), I will just show the final result. Here you’ll see each SCC colored differently. We will do the steps the same as the previous one, but the next node to traverse will be given by stack. Ignore those stack elements which have already been considered in previous SCCs.

![image](https://images.contentful.com/piwi0eufbb2g/1TE2fX2imobJziKfSHRdF8/83f5a74f7bccfee976af2e635844226d/image.png)


