It is used to find the longest common substring.

### O(n^2)
Intuition: We can consider each element as center and expand from there to find the palindrome for that center and choose the longest one. (this is for odd length palindromes).
For even ones: convert "abc" to "#a#b#c#"
```java
class Solution {

    private int sol(char[] a, int x){
        return sol(a, x-1, x+1);
    }

    private int sol(char[] a, int x, int y){
        if(x<0 || y>=a.length){
            return 0;
        }
        if(a[x]!=a[y]){
            return 0;
        }
        return 1 + sol(a, x-1, y+1);
    }

    public String longestPalindrome(String s) {
        char[] a = s.toCharArray();
        int mx = 0;
        String ans = "";
        int l,r;
        List<Integer> lst = new ArrayList<>();
        for(int i=0;i<a.length;i++){
            l = sol(a,i);
            if((2*l+1)>mx){
                mx = (2*l+1);
                ans = s.substring(i-l, i+l+1);
            }
            l = sol(a,i,i+1);
            if((2*l)>mx){
                mx = 2*l;
                ans = s.substring(i-l+1, i+l+1);
            }
        }
        
        return ans;
    }
}
```

### Optimized:
https://www.youtube.com/watch?v=YVZttWzvyw8

Problem:
1. https://leetcode.com/problems/longest-palindromic-substring/