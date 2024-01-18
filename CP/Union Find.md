```java
static int find(int parent[], int i)
{
    if (parent[i] == -1)
        return i;
    return find(parent, parent[i]);
}
   
// Naive implementation of union()
static void Union(int parent[], int x, int y)
{
    int xset = find(parent, x);
    int yset = find(parent, y);
    parent[xset] = yset;
}
```

Optimized:

Questions:
LC:
1. [[684]]
2. [Find connected components](https://leetcode.ca/all/323.html)
3. [Graph valid tree](https://leetcode.ca/all/261.html)
4. 