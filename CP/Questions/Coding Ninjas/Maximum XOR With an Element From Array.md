### Code:
```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Trie{
    Map<Integer, Trie> mp;
    int value;
    Trie(int x){
        mp = new HashMap<>();
        value = x;
    }
}
public class Solution {
    private static void add(Trie t, int num, int ind){
        if(ind<0){
            return;
        }

        Map<Integer, Trie> pm = t.mp;
        int n = ((1<<ind)&num);
        n = (n==0)?0:1;
        if(!pm.containsKey(n)){
            int pt = ((n<<ind)|(t.value));
            pm.put(n, new Trie(pt));
        }

        add(pm.get(n), num, ind-1);
    }

    private static int check(Trie t, int num, int ind, int lm, int ans){
        if(ind<0){
            return ans;
        }
        if(t==null){
            return -1;
        }

        int n = ((1<<ind)&(num));
        n = (n==0)?0:1;
        int mx = -1;
        Trie nd=null;
        Map<Integer, Trie> pm = t.mp;
        for(Map.Entry<Integer, Trie> me: pm.entrySet()){
            if(me.getValue().value>lm){
                continue;
            }

            int tmp = (me.getKey()^n);
            int newAns = ((tmp<<ind)|ans);
            int mem = check(me.getValue(), num, ind-1, lm, newAns);
            if(mem>mx){
                mx = mem;
            }
        }

        return mx;
    }

    public static ArrayList<Integer> maxXorQueries(ArrayList<Integer> arr, ArrayList<ArrayList<Integer>> queries) {
        ArrayList<Integer> ans = new ArrayList<>();
        Trie t = new Trie(0);
        for(int num: arr){
            add(t, num, 31);
        }

        int x, a;
        for(ArrayList<Integer> pair: queries){
            x = pair.get(0);
            a = pair.get(1);

            int getVal = check(t, x, 31, a, 0);
            ans.add(getVal);
        }

        return ans;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr1 = new ArrayList<>();
        arr1.add(89);arr1.add(54);arr1.add(28);arr1.add(87);arr1.add(85);

        ArrayList<ArrayList<Integer>> arr2 = new ArrayList<>();
        ArrayList<Integer> p1 = new ArrayList<>();
        p1.add(17);p1.add(73);
//        ArrayList<Integer> p2 = new ArrayList<>();
//        p2.add(45);p2.add(25);
        arr2.add(p1);

        ArrayList<Integer> xxx = maxXorQueries(arr1, arr2);
        for(int yyy: xxx){
            System.out.print(yyy+" ");
        }
    }
}
```
- it is going to all valid pathways and checking for max.

Optimized:
```java
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.Map;  
  
class Trie{  
    Map<Integer, Trie> mp;  
    int value;  
    Trie(int x){  
        mp = new HashMap<>();  
        value = x;  
    }  
}  
public class Solution {  
    private static int add(Trie t, int num, int ind){  
        if(ind<0){  
            t.value = num;  
            return t.value;  
        }  
  
        Map<Integer, Trie> pm = t.mp;  
        int n = ((1<<ind)&num);  
        n = (n==0)?0:1;  
        if(!pm.containsKey(n)){  
            pm.put(n, new Trie(Integer.MAX_VALUE));  
        }  
  
        int gt = add(pm.get(n), num, ind-1);  
        t.value = Math.min(t.value, gt);  
        return t.value;  
    }  
  
    private static int check(Trie t, int num, int ind, int lm, int ans){  
        if(ind<0){  
            return ans;  
        }  
        if(t==null){  
            return -1;  
        }  
  
        int n = ((1<<ind)&(num));  
        n = (n==0)?0:1;  
        int mx = -1;  
        Trie nd=null;  
        Map<Integer, Trie> pm = t.mp;  
        for(Map.Entry<Integer, Trie> me: pm.entrySet()){  
            if(me.getValue().value>lm){  
                continue;  
            }  
  
            int tmp = (me.getKey()^n);  
            if(tmp>mx){  
                mx = tmp;  
                nd = me.getValue();  
            }  
        }  
  
        int newAns=ans;  
        if(mx!=-1){  
            newAns = ((mx<<ind)|newAns);  
        }  
  
        return check(nd, num, ind-1, lm, newAns);  
    }  
  
    public static ArrayList<Integer> maxXorQueries(ArrayList<Integer> arr, ArrayList<ArrayList<Integer>> queries) {  
        ArrayList<Integer> ans = new ArrayList<>();  
        Trie t = new Trie(Integer.MAX_VALUE);  
        for(int num: arr){  
            add(t, num, 31);  
        }  
  
        int x, a;  
        for(ArrayList<Integer> pair: queries){  
            x = pair.get(0);  
            a = pair.get(1);  
  
            int getVal = check(t, x, 31, a, 0);  
            ans.add(getVal);  
        }  
  
        return ans;  
    }  
  
    public static void main(String[] args) {  
        ArrayList<Integer> arr1 = new ArrayList<>();  
        arr1.add(89);arr1.add(54);arr1.add(28);arr1.add(87);arr1.add(85);  
  
        ArrayList<ArrayList<Integer>> arr2 = new ArrayList<>();  
        ArrayList<Integer> p1 = new ArrayList<>();  
        p1.add(17);p1.add(73);  
//        ArrayList<Integer> p2 = new ArrayList<>();  
//        p2.add(45);p2.add(25);  
        arr2.add(p1);  
  
        ArrayList<Integer> xxx = maxXorQueries(arr1, arr2);  
        for(int yyy: xxx){  
            System.out.print(yyy+" ");  
        }  
    }  
}
```