
```java
//import java.util.*;

class Solution {
    private int get(int N) {
        while ((N & 1) == 0) {
            N = N >> 1;
        }
        return N;
    }

    private int flip(int N) {
        int mask = 0;
        int x = N;
        while (N != 0) {
            mask = mask << 1;
            mask = mask | 1;
            N = N >> 1;
        }
        return x ^ mask;
    }

    public int solution(int N) {
        N = get(N);
        int x = flip(N);
        int ans = 0;
        while (x != 0) {
            x = x & (x << 1);
            ans++;
        }
        return ans;
    }
}

```
How to flip 100 to 001 like that

Knowing that $slowDist = 2 \cdot (fastDist)$

### Theory:

Q: 1) **How to check if a given number is a power of 2 ?**
```java
bool isPowerOfTwo(int x)
    {
        // x will check if x == 0 and !(x & (x - 1)) will check if x is a power of 2 or not
        return (x && !(x & (x - 1)));
    }
```

Q: **Count the number of ones in the binary representation of the given number.**
```java
int count_one (int n)
        {
            while( n )
            {
            n = n&(n-1);
               count++;
            }
            return count;
    }
```

Q: **Check if the ith bit is set in the binary form of the given number.**
```java
bool check (int N)
    {
        if( N & (1 << i) )
            return true;
        else
            return false;
    }
```

Q: Find most significant set bit of a number
```java
class GFG {

	static int setBitNumber(int n)
	{
		n |= n >> 1;

		n |= n >> 2;

		n |= n >> 4;
		n |= n >> 8;
		n |= n >> 16;

		n = n + 1;

		return (n >> 1);
	}

	public static void main(String arg[])
	{
		int n = 273;
		System.out.print(setBitNumber(n));
	}
}
```

Q: Length of the Longest Consecutive 1s in Binary Representation [GFG](https://www.geeksforgeeks.org/length-longest-consecutive-1s-binary-representation/)
```java
class MaxConsecutiveOnes
{
	private static int maxConsecutiveOnes(int x)
	{
		// Initialize result
		int count = 0;

		// Count the number of iterations to
		// reach x = 0.
		while (x!=0)
		{
			// This operation reduces length
			// of every sequence of 1s by one.
			x = (x & (x << 1));

			count++;
		}

		return count;
	}

	// Driver code
	public static void main(String strings[])
	{
		System.out.println(maxConsecutiveOnes(14));
		System.out.println(maxConsecutiveOnes(222));
	}
}
```

Q: No. of 1s in binary representation of i, i -> 1-N.
A: [[338]]
1. If `x` is even, then the number of set bits in `x` is the same as the number of set bits in `x/2`.
2. If `x` is odd, then the number of set bits in `x` is one more than the number of set bits in `x/2`.
```java
class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n+1];
        ans[0] = 0;

        for(int i=1;i<=n;i++){
            if((i&1) == 0){
                ans[i] = ans[i>>1];
            }else{
                ans[i] = ans[i>>1] + 1;
            }
        }

        return ans;
    }
}
```

Q: XOR of numbers 1->N [[268]]
```java
if(x%4 == 0){
            return x;
        }else if(x%4 == 1){
            return 1;
        }else if(x%4 == 2){
            return x+1;
        }else{
            return 0;
        }
```

Q: Add two numbers:
A:
```java
class Solution {
    public int getSum(int a, int b) {
        if(a==0){
            return b;
        }

        return getSum(((a&b)<<1), a^b);
    }
}
```

Questions:
3. [[338]]
4. [[190]]
5. [[268]]
6. [[371]]
7. [[7]]*
8. [[Single Number II]]