https://codeforces.com/blog/entry/22276

Here we are using a thoerm:
"During the execution of BFS, the queue holding the vertices only contains elements from at max two successive levels of the BFS tree."

Idea:
- Lets say we are at "u" , travelling an edge (u,v) would make sure that v is either in the same level as u or at the next successive level. This is because the edge weights are 0 and 1. 
- An edge weight of 0 would mean that they lie on the same level , whereas an edge weight of 1 means they lie on the level below. 
- We also know that during BFS our queue holds vertices of two successive levels at max. So, when we are at vertex "u" , our queue contains elements of level L[u] or L[u] + 1. 

