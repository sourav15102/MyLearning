https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/

> Works for -ve weights.
> Can detect -ve cycles.
> Assumption:
> - For shortest path, -ve cycles shouldnt be there.
### Algorithm:
- have V as number of vertices, E as set of (u,v,w) `w as weight of edge u-->v`.
- We will run function V-1 times.
- for V-1 times, we will update all edges.
- Update: meaning if new distance is < than current one, then update it.
- If any distances still gets updated Vth time, then it would mean that a -ve cycle exists.
### How to print paths:
- While updating each node, we can have another array as parent, we we can keep parent of ith node as  `parent[i]`.