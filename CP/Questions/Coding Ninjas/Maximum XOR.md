### Trick:
- Brute force would have been to compare each number with each and check, o(n,m).
- However, we know that with [[Trie]] we have the ability to check suffixes of multiple words/numbers/sequences simultaneously.
- So, this ability will give us an edge.

> **IMP:** The way we can find maximum using this is, that at each node of trie, we can find the best path to go next depending upon which one is giving us the maximum.

### Code:
```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Trie{
    Map<Integer, Trie> mp;
    Trie(){
        mp = new HashMap<>();
    }
}
public class Solution
{
    private static void add(Trie t, int num, int ind){
        if(ind<0){
            return;
        }
        Map<Integer,Trie> pm = t.mp;
        int ver = (((1<<ind)&(num))==0)?0:1;
        if(!pm.containsKey(ver)){
            pm.put(ver, new Trie());
        }

        add(pm.get(ver), num, ind-1);
    }

    private static int calc(Trie t, int num, int ind, int ans){
        if(ind<0){
            return ans;
        }
        Map<Integer, Trie> pm = t.mp;
        int ver = (((1<<ind)&(num))==0)?0:1;

        int mx = -1;
        Trie nd = null;
        for(Map.Entry<Integer, Trie> me: pm.entrySet()){
            int verr = ver^(me.getKey());
            if(verr>mx){
                mx = verr;
                nd = me.getValue();
            }
        }

        ans = (mx<<ind)|(ans);
        return calc(nd, num, ind-1, ans);

    }
    public static int maxXOR(int n, int m, ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
        Trie t = new Trie();
        for(int x: arr1){
            add(t,x,31);
        }
        int ans = 0;
        for(int x: arr2){
            ans = Math.max(ans, calc(t,x,31,0));
        }

        return ans;
    }

//     public static void main(String[] args) {
// //        int[] on = {6,6,0,6,8,5,6};
// //        int[] tn = {1,7,1,7,8,0,2};
//         int[] on = {25,10,2};
//         int[] tn = {8,5,3};
//         ArrayList<Integer> arr1 = new ArrayList<>();
//         ArrayList<Integer> arr2 = new ArrayList<>();
//         for(int x: on){
//             arr1.add(x);
//         }

//         for(int x: tn){
//             arr2.add(x);
//         }

//         System.out.println(Solution.maxXOR(arr1.size(), arr2.size(), arr1, arr2));
//     }
}

```
