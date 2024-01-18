It is a single source shortest path algorithm, where we have a source and we want to calculate the shortest path to each node from source. 
> Assumption: No negative weights are present.
### Intuition
- we are assuming that in beginning we only know source, and no-one else, so as we discover nodes, lets say `x,y,z` now the one which is closest to source, we will go there and try to discover rest of the nodes, because it will give us best chance of discovering minimum weights, `(HOWEVER: We could have visited one which is farther from source and it might have helped us discover shorter distances with negative weights, A: Yes, thats why DIjkstra wont work for -ve distances.)`.
### Algorithm
- Have distance and visited array for all nodes.
- Mark distance of source as 0, and inf for rest.
- put them all in min priority queue.
- pop minimum weighted node that is NOT present in visited, update its neighbors' weights, if updated put them in queue again `(wont it add duplicates to Priority queue?, A: it would but it wont matter as the updated (min) weight will be on top and will be chosen first, will go to visited array already and when it appears second time, wont be considered)`.
### How to print paths:
- While updating each node, we can have another array as parent, we we can keep parent of ith node as  `parent[i]`.