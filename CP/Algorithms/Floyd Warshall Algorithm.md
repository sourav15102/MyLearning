https://medium.com/@abhinandansh28/guide-to-floyd-warshall-algorithm-5d5e8ee5bbd1

> Can handle -ve weights
> Can detect -ve cycles
> Assumption:
> - For shortest path, -ve cycles shouldnt be there.

> The Floyd Warshall Algorithm is a classic example of dynamic programming at work. 


### Algorithm:
1. Start by setting up a two-dimensional matrix, `dist[][]`. The value of `dist[i][j]` corresponds to the weight of the connection from node `i` to node `j`, assuming such a connection is present. If there's no direct link from `i` to `j`, assign it a value of `infinity`. In cases where `i` is identical to `j`, the value will be `0`.
2. For each node, denoted as `k`, execute the following actions for every pair of nodes `i` and `j`:
- If the sum of `dist[i][k]` and `dist[k][j]` is less than `dist[i][j]`, this indicates a shorter path has been found. Consequently, update `dist[i][j]` to be equal to `dist[i][k] + dist[k][j]`.

```python
import numpy as np  
  
def floyd_warshall(graph):  
  
    n = len(graph)  
  
    dist = np.array(graph)  
  
  
    for k in range(n):  
        for i in range(n):  
            for j in range(n):  
                # If the distance through k is less than the current distance  
                if dist[i][k] + dist[k][j] < dist[i][j]:  
                    # Update the distance  
                    dist[i][j] = dist[i][k] + dist[k][j]  
                      
    return dist
```
Detect -ve cycle:
```java
import numpy as np

def detect_negative_cycle(graph):
    n = len(graph)
    dist = np.array(graph)

    # Run the Floyd-Warshall algorithm
    for k in range(n):
        for i in range(n):
            for j in range(n):
                if dist[i][k] + dist[k][j] < dist[i][j]:
                    dist[i][j] = dist[i][k] + dist[k][j]

    # Check for negative cycle
    for i in range(n):
        if dist[i][i] < 0:
            return True  # Negative cycle detected

    return False  # No negative cycle detected

```
