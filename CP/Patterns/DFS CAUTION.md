```java
private int dfs(int[][] grid, boolean[][] vis, int[][] sz, int[][][] p, int x, int y, int px, int py){
        if(vis[x][y]){
            return 0;
        }
        /**
        Here lets say this above check wasnt there, below at 'for' loop we get all children of x,y at once, now it can happen that we dfs the 1st children and it reaches 2nd children of x,y but after returning from 1st child, it will go to 2nd children anyways, but if we have we have abive block there, we can prevent it.
        */
        p[x][y][0] = px;
        p[x][y][1] = py;
        vis[x][y] = true;
        int asz=0;
        for(int[] ch: getCh(grid,x,y,vis)){
            asz += dfs(grid,vis,sz,p,ch[0],ch[1],px,py);
        }
        sz[x][y] = asz+1;
        return sz[x][y];
    }
```

