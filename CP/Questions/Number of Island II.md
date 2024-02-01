### Idea:
- same as [[827]]

### Code:
```java
import java.util.*;
public class Solution {
    private static int[] find(int x, int y, int[][][] par){
        if(par[x][y][0]==x && par[x][y][1]==y){
            return par[x][y];
        }
        return find(par[x][y][0], par[x][y][1], par);
    }
    private static void union(List<Integer> a, List<Integer> b, boolean[][] vis, int[][][] par){
        int[] ap = find(a.get(0), a.get(1), par);
        int[] bp = find(b.get(0), b.get(1), par);
        par[ap[0]][ap[1]][0] = bp[0];
        par[ap[0]][ap[1]][1] = bp[1];
    }
    private static List<List<Integer>> surr(int[] ch, int[][][] par, boolean[][] vis, int n, int m){
        int[] xax = {-1,1,0,0};
        int[] yax = {0,0,1,-1};
        int nx,ny;
        List<List<Integer>> aa = new ArrayList<>();
        List<Integer> ag;
        for(int i=0;i<4;i++){
            nx = ch[0]+xax[i];
            ny = ch[1]+yax[i];
            if(nx<0 || nx>=n || ny<0 || ny>=m || !vis[nx][ny]){
                continue;
            }
            int[] pr = find(nx, ny, par);
            if(vis[pr[0]][pr[1]]){
                vis[pr[0]][pr[1]] = false;
                ag = new ArrayList<>();
                ag.add(pr[0]);
                ag.add(pr[1]);
                aa.add(ag);
            }
        }
        for(List<Integer> pts: aa){
            vis[pts.get(0)][pts.get(1)] = true;
        }
        return aa;

    }   
    public static int[] numOfIslandsII(int n, int m, int[][] q) {
        int ans = 0;
        boolean[][] vis = new boolean[n][m];
        for(int i=0;i<n;i++){
            Arrays.fill(vis[i], false);
        }
        int[][][] par = new int[n][m][2];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                par[i][j][0] = i;
                par[i][j][1] = j;
            }
        }

        List<Integer> md = new ArrayList<>();
        List<Integer> tmp;
        for(int[] ch: q){
            vis[ch[0]][ch[1]] = true;
            List<List<Integer>> lst = surr(ch, par, vis, n, m);
            if(lst.size()==0){
                ans++;
            }else{
                for(int i=1;i<lst.size();i++){
                    union(lst.get(i), lst.get(0), vis, par);
                }
                tmp = new ArrayList<>();
                tmp.add(ch[0]);
                tmp.add(ch[1]);
                union(tmp, lst.get(0), vis, par);
                ans = ans - (lst.size()-1);
            }
            md.add(ans);
        }

        return md.stream().mapToInt(i->i).toArray();
    }
}
```