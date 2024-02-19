https://www.interviewbit.com/problems/unique-binary-search-trees/

### Idea:
- for range `x-y` and ind `i`, get answer for `x-(i-1)` and `(i+1)-y`.
- Then integrate.
- In such cases DP cause issues as you are using same obj space in multiple location and that can mess up things, but in this case it doesnt. see [[40]].

### Code:
```java
/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
	private ArrayList<TreeNode> get(int x, int y, ArrayList<TreeNode>[][] dp){
		if(x>y){
			return new ArrayList<>();
		}
		
		if(dp[x][y]!=null){
			return dp[x][y];
		}
		
		ArrayList<TreeNode> ll;
		ArrayList<TreeNode> rl;
		ArrayList<TreeNode> ans = new ArrayList<>();
		for(int i=x;i<=y;i++){
			ll = get(x,i-1,dp);
			rl = get(i+1,y,dp);
			
			ans.addAll(me(i,ll,rl));
		}
		dp[x][y] = ans;
		return ans;
	}
	private ArrayList<TreeNode> me(int x, ArrayList<TreeNode> ll, ArrayList<TreeNode> rl){
		
		ArrayList<TreeNode> ans = new ArrayList<>();
		TreeNode node;
		
		if(ll.isEmpty() && rl.isEmpty()){
			node = new TreeNode(x);
			node.left = null;
			node.right = null;
			ans.add(node);
			return ans;
		}
		
		if(ll.isEmpty()){
			for(TreeNode rn: rl){
				node = new TreeNode(x);
				node.left = null;
				node.right = rn;
				ans.add(node);
			}
			return ans;
		}
		
		if(rl.isEmpty()){
			for(TreeNode ln: ll){
				node = new TreeNode(x);
				node.left = ln;
				node.right = null;
				ans.add(node);
			}
			return ans;
		}
		
		for(TreeNode ln: ll){
			for(TreeNode rn: rl){
				node = new TreeNode(x);
				node.left = ln;
				node.right = rn;
				ans.add(node);
			}
		}
		return ans;
	}
	public ArrayList<TreeNode> generateTrees(int a) {
		ArrayList<TreeNode>[][] dp = new ArrayList[a+1][a+1];
		for(int i=0;i<=a;i++){
			for(int j=0;j<=a;j++){
				dp[i][j] = null;
			}
		}
		
		ArrayList<TreeNode> ans = get(1,a,dp);
		
		return ans;
		
	}
}

```