**https://www.youtube.com/watch?v=qrAub5z8FeA&t=624s**
**Watch till 7:00**

The terms "discovery time" and "lowest time" are commonly associated with the depth-first search (DFS) traversal of graphs, particularly in the context of finding articulation points and bridges in the graph. Let's understand the significance of these concepts:

1. **Discovery Time:**
    - **Definition:** The time at which a vertex is first encountered (discovered) during a DFS traversal.
    - **Significance:**
        - Helps in identifying the order in which vertices are discovered in the DFS traversal.
        - Used to classify edges in the graph (tree edges, back edges, forward edges, and cross edges).
        - Crucial for determining the structure of the DFS tree and understanding the connectivity of the graph.
2. **Lowest Time (or Lowest Discovery Time):**
    - **Definition:** For each vertex, the lowest discovery time of any vertex reachable from that vertex, excluding the parent of the vertex in the DFS tree.
    - **Significance:**
        - Key in identifying back edges during the DFS traversal.
        - Used in finding bridges and articulation points in the graph.
        - Helps in determining the earliest ancestor (lowest discovery time) that a vertex can reach in the DFS tree, other than its parent.
3. **Significance in Finding Bridges and Articulation Points:**
    
    - **Bridges:** An edge in a graph is a bridge if removing it increases the number of connected components. The lowest time is crucial in identifying bridges.
    - **Articulation Points:** A vertex in a graph is an articulation point if removing it increases the number of connected components. The lowest time is used to determine if a vertex is an articulation point.