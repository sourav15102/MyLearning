https://www.interviewbit.com/courses/programming/backtracking/

- It is known for solving problems recursively one step at a time and removing those solutions that that do not satisfy the problem constraints at any point of time.

> the algorithm tries to find a path to the feasible solution which has some intermediary checkpoints. In case they don’t lead to the feasible solution, the problem can backtrack from the checkpoints and take another path in search of the solution

```pseudocode
The final algorithm can be summarised as:  
1. Step 1 − if current point is a feasible solution, return success  
2. Step 2 − else if all paths are exhausted (i.e current point is an end point) return failure, since we have no feasible solution.
3. Step 3 − else if current point is not an end point, backtrack and explore other points and repeat above steps.
```
### Types of Backtracking Problems:
- **Decision Problems** – Here, we search for a feasible solution.
- **Optimization Problems** – For this type, we search for the best solution.
- **Enumeration Problems** – We find set of all possible feasible solutions to the problems of this type.

### Caution:
- There can be scenarios where we are using same memory in multiple locations messing up the entire state.

Problems:
1. [[78]] & [[90]]
2. [[39]]
3. [[46]]*
4. [[40]] (DP and BKTR) (Read issue inside) **
5. [[79]]*
6. [[131]]
7. [[51]]**
8. [[212]]** (Trie and Backtracking)








