### Idea:
- Used merge sort.
- Divide array into 2, while merging. left = size is m, right = size of n.
- if `left[i]>right[j]`, ans would be increased by `m-i`.
- in any other case no addition to ans.
- see `// CAUTION` for merge sort

### Code:
```java
public class Solution {
    private static int ans;
    private ArrayList<Integer> merge(ArrayList<Integer> lft, ArrayList<Integer> rgt){
        int m = lft.size();
        int n = rgt.size();
        
        ArrayList<Integer> sm = new ArrayList<Integer>();
        
        int i=0;
        int j=0;
        
        while(i<m && j<n){
            if(lft.get(i)<=rgt.get(j)){
                sm.add(lft.get(i));
                i++;
            }else{
                sm.add(rgt.get(j));
                j++;
                ans += (m-i);
            }
        }
        
        while(i<m){
            sm.add(lft.get(i));
            i++;
        }
        
        while(j<n){
            sm.add(rgt.get(j));
            j++;
        }
        
        return sm;
    }
    private ArrayList<Integer> sort(ArrayList<Integer> A, int l, int r){
	    if(l>r){
	    return new ArrayList<Integer>(); 
	    }
        if(l==r){//CAUTION: if we keep it (l>r), then it would be INF loop, cos, 
		        // lft = sort(A,l,md), there is no decreemet here.
            ArrayList<Integer> df = new ArrayList<Integer>();
            df.add(A.get(l));
            return df;
        }
        int md = l+(r-l)/2;
        ArrayList<Integer> lft = sort(A,l,md);
        ArrayList<Integer> rgt = sort(A,md+1,r);
        return merge(lft,rgt);
    }
    public int countInversions(ArrayList<Integer> A) {
        ans =0;
        sort(A, 0, A.size()-1);
        return ans;
    }
}

```