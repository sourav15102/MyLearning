Link: https://www.codingninjas.com/studio/problems/complete-string_2687860?utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_tries_videos

### Trick:
- insert all words.
- check all words if it is complete
- [[String]]: read about `equals` and `compareTo`(for lexographical checking) 

```java
import java.util.* ;
import java.io.*; 

class Trie {
    Map<Character, Trie> mp;
    boolean word;
    Trie(){
        mp = new HashMap<>();
        word = false;
    }
}
class Solution {
    private static void insert(Trie trie, char[] word, int ind){
        if(ind == word.length){
            trie.word = true;
            return;
        }
        Map<Character, Trie> pm = trie.mp;
        if(!pm.containsKey(word[ind])){
            pm.put(word[ind], new Trie());
        }
        insert(pm.get(word[ind]), word, ind+1);
    }
    private static boolean isComplete(Trie trie, char[] word, int ind){
        if(ind == word.length){
            return true;
        }
        Map<Character, Trie> pm = trie.mp;
        Trie node = pm.get(word[ind]);
        if(!node.word){
            return false;
        }

        return isComplete(node, word, ind+1);
    }
    public static String completeString(int n, String[] a) {
        Trie trie = new Trie();
        for(String x: a){
             insert(trie, x.toCharArray(), 0);
        }
        String ans = "";
        for(String x: a){
            if(isComplete(trie, x.toCharArray(), 0)){
                if(x.length()>ans.length()){
                    ans = x;
                }else if(x.length()==ans.length()){
                    if(ans.compareTo(x)>0){
                        ans = x;
                    }
                }
            }
        }
        if(ans.equals("")){
          return "None";
        }
        return ans;
    }
}
```