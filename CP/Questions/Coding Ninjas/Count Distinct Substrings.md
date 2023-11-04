### Trick:
- this problem basically deals with finding multiple instances of substrings within a single string.
- whenever handling with multiple string and comparing them, prefixes n all, trie should be considered.
- [[Knuth–Morris–Pratt - Pattern]] wouldn't have worked here because, it basically finds if current ongoing substring, has any similarities from the beginning of the string. for example: abcdefabc
- Actual Solution:

### Code:
```java
import java.util.*;  
class Trie {  
    Map<Character, Trie> mp;  
    Trie(){  
        mp = new HashMap<>();  
    }  
}  
public class Solution {  
  
    private static int add(Trie t, char[] wr, int ind){  
        if(ind == wr.length){  
            return 0;  
        }  
        Map<Character, Trie> pm = t.mp;  
        int ans = 0;  
        if(!pm.containsKey(wr[ind])){  
            pm.put(wr[ind], new Trie());  
            ans++;  
        }  
  
        return ans + add(pm.get(wr[ind]), wr, ind+1);  
    }  
    public static int countDistinctSubstrings(String s) {  
        Trie t = new Trie();  
        char[] wr = s.toCharArray();  
        int ans = 0;  
        for(int i=0;i<s.length();i++){  
            ans += (add(t,wr, i));  
        }  
  
        return ans+1;  
    }  
  
    public static void main(String[] args) {  
        String x = "aabaabaaa";  
        System.out.println(Solution.countDistinctSubstrings(x));  
    }  
}
```