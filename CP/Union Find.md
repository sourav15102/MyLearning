```java
private int find(int x, int[] p){
        if(x==p[x]){
            return x;
        }
        return find(p[x],p);
    }
    private void union(int x, int y, int[] p, int[] r){
        if(r[x]<r[y]){
            p[x] = y;
            r[y] += r[x];
        }else{
            p[y] = x;
            r[x] += r[y];
        }
    }
```

Optimized:

Questions:
LC:
1. [[684]]
2. [Find connected components](https://leetcode.ca/all/323.html)
3. [Graph valid tree](https://leetcode.ca/all/261.html)
4. [[2092]]**