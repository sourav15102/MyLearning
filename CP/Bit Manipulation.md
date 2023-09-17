
Questions:
1. [GFG](https://www.geeksforgeeks.org/length-longest-consecutive-1s-binary-representation/)
2. [Codility Bitwise](https://app.codility.com/c/run/training82SWCH-7KZ/)
```java
// you can also use imports, for example:

// import java.util.*;

  

// you can write to stdout for debugging purposes, e.g.

// System.out.println("this is a debug message");

  

class Solution {

    private int get(int N){

        while((N&1)==0){

            N = N>>1;

        }

        return N;

    }

    private int flip(int N){

        int mask = 0;

        int x = N;

        while(N!=0){

            mask = mask<<1;

            mask  = mask | 1;

            N = N>>1;

        }

  

        return x^mask;

    }

    public int solution(int N) {

        N = get(N);

        int x = flip(N);

        int ans = 0;

        while(x!=0){

            x = x&(x<<1);

            ans++;

        }

  

        return ans;

    }

}
```
How to flip 100 to 001 like that

Knowing that $slowDist = 2 \cdot (fastDist)$
