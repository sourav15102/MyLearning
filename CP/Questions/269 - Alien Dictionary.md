> Assumption: No cycles are present.
### Idea:
- The main task is to build the map.
	- Compare two strings one by one and the index where they differ would mean that there is an edge present.
	- for example: "abcd", "abce" so there is an edge d-->e  or d comes before e.
- After building map it is just topo sort.
### Code:
```java
import java.util.*;
class Solution {

    private void dfs(Map<Character, List<Character>> g, char k, Set<Character> vis, Deque<Character> st){
        vis.add(k);
        if(!g.containsKey(k)){
            st.add(k);
            return;
        }
        for(char ch: g.get(k)){
            if(!vis.contains(ch)){
                dfs(g, ch, vis, st);
            }
        }
        st.add(k);
    }

    private String floodFill(String[] words){
        Map<Character, List<Character>> g = getMap(words);
        Deque<Character> st = new ArrayDeque<>();
        Set<Character> vis = new HashSet<>();

        for(Character k: g.keySet()){
            if(!vis.contains(k)){
                dfs(g, k, vis, st);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()){
            sb.append(st.pop());
        }
        return sb.reverse().toString();
    }
    public Map<Character, List<Character>> getMap(String[] words) {
        Map<Character, List<Character>> g = new HashMap<>();

        char[] temp = new char[0];
        char[] nw;
        int i,j;
        for(String w: words){
            i = j = 0;
            nw = w.toCharArray();
            while(i<temp.length && j<nw.length && temp[i]==nw[j]){
                i++;j++;
            }
            if(i<temp.length && j<nw.length){
                List<Character> chs = g.getOrDefault(temp[i], new ArrayList<>());
                chs.add(nw[j]);
                g.put(temp[i],chs);
            }
            temp = nw;
        }
        return g;
    }
    public static void main(String[] args) {
        String[] words = {"wrt",   "wrf",   "er",   "ett",   "rftt"};
        //String[] words = {"z",   "x"};
        Solution solution = new Solution();

        System.out.println(solution.floodFill(words));
    }
}
```