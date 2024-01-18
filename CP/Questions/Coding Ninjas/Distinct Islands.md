Link: https://www.codingninjas.com/studio/problems/distinct-islands_630460?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM

### Idea:
- The issue was, how to capture he island, and check if 2 islands are equals.
- Sol: We can calc relative position for all points in an island, we can make the first position we encounter from a particular island as the reference point and then every point from that island will be saved as relative to that reference point.
### Code:
```java
import java.util.*;
public class Solution 
{
	private static class Point{
		int x;
		int y;
		Point(int i, int j){
			this.x = i;
			this.y = j;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}
			Point other = (Point) obj;
			return x == other.x && y == other.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

	}
	private static void dfs(int[][] arr, int i, int j, int x, int y, boolean[][] vis, List<Point> lst){
		if(i<0 || i>=arr.length || j<0 || j>=arr[0].length || vis[i][j] || arr[i][j]==0){
			return;
		}

		vis[i][j] = true;
		lst.add(new Point(i-x,j-y));
		int[] a = {1,-1,0,0};
		int[] b = {0,0,1,-1};
		int ni,nj;
		for(int p=0;p<4;p++){
			ni = i+a[p];
			nj = j+b[p];
			dfs(arr, ni,nj, x,y, vis,lst);
		}

	}
	private static void pr(List<Point> x){
		for(Point p: x){
			System.out.print(p.x);
			System.out.print("/");
			System.out.print(p.y);
			System.out.print("-");
		}
		System.out.println();
	}
	public static int distinctIsland(int [][] arr, int n, int m) 
	{
		boolean[][] vis = new boolean[n][m];
		Set<List<Point>> st = new HashSet<>();
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				if(!vis[i][j] && arr[i][j]==1){
					List<Point> lst = new ArrayList<>();
					dfs(arr,i,j,i,j,vis,lst);
					st.add(lst);
				}

			}
		}
		return st.size();
	}
}
```