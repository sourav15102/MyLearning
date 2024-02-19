
https://www.pramp.com/question/8noLWxLP6JUZJ2bA2rnx

### Code:
```java
import java.io.*;  
import java.util.*;  
    
class Solution {  
    private static int toInt(char x){  
        return (int) x;  
    }  
    private static char toChar(int x){  
        return (char) x;  
    }  
    private static int get(int x){  
        if(x>122){  
            return x-26;  
        }  
        return get(x+26);  
    }  
  
    static String decrypt(String word) {  
        char[] wr = word.toCharArray();  
        int[] nm = new int[wr.length];  
        int[] mn = new int[wr.length];  
  
        for(int i=0;i<wr.length;i++){  
            nm[i] = toInt(wr[i]);  
        }  
  
        if(nm[0]==97){  
            nm[0] = 123;  
        }  
        mn[0] = nm[0];  
        for(int i=1;i<nm.length;i++){  
            mn[i] = nm[i]-nm[i-1];  
        }  
  
        for(int i=1;i<mn.length;i++){  
            mn[i] = get(mn[i]);  
        }  
        mn[0] = mn[0]-1;  
  
        for(int i=0;i<mn.length;i++){  
            wr[i] = toChar(mn[i]);  
        }  
        System.out.println(mn[0]);  
        String ans = new String(wr);  
        return ans;  
    }  
  
    public static void main(String[] args) {  
        String xx = "atn";  
        System.out.println(Solution.decrypt(xx));  
    }  
  
}
```