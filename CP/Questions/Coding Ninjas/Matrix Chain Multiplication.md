Link: https://www.codingninjas.com/studio/problems/matrix-chain-multiplication_975344?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos

```java
import java.util.* ;
import java.io.*; 

public class Solution {
	private static int sol(int[] arr, int x, int y, int[][] dp){
		if(x==y){
			return 0;
		}

		if(dp[x][y]!=-1){
			return dp[x][y];
		}

		int a,b,mn,tmp;
		mn = -1;
		for(int i=x;i<y;i++){
			a = sol(arr,x,i,dp);
			b = sol(arr,i+1,y,dp);
			tmp = a+b+(arr[x]*arr[i+1]*arr[y+1]);
			if(mn==-1){
				mn = tmp;
			}else{
				mn = Math.min(mn, tmp);
			}
		}
		dp[x][y] = mn;
		return mn;
	}
	public static int matrixMultiplication(int[] arr , int N) {
		if(N<=2){
			return 0;
		}
		int[][] dp = new int[N][N];
		for(int i=0;i<N;i++){
			Arrays.fill(dp[i],-1);
		}

		return sol(arr, 0, N-2, dp);
	}
}

```