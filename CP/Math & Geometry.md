### Theory:
https://www.geeksforgeeks.org/mathematical-and-geometric-algorithms-data-structure-and-algorithm-tutorials/

##### Euclid Algorithm (GCD):
GCD: Among all divisors of both numbers, the largest common one is the answer.
- If one number is 0 and the other is non-zero, the non-zero number is the GCD
- If we subtract a smaller number from a larger one, GCD doesn’t change. So if we repeatedly keep subtracting the smaller from the larger we end up with GCD (the larger is the GCD).
- We can subtract the smaller number (say ****b****) from the larger number (say ****a****), at most ****floor(a/b)**** times. So after repeated subtraction, `a` becomes ****a – b*floor(a/b)**** which is `a%b`. Instead of repeated subtraction we can do `a%b` and stop when it becomes 0 then b becomes the GCD.
```java
// Java program to demonstrate Basic Euclidean Algorithm
import java.lang.*;
import java.util.*;

class GFG {
	// Function to return gcd of a and b
	public static int gcd(int a, int b)
	{
		if (a == 0)
			return b;

		return gcd(b % a, a);
	}
	// Driver code
	public static void main(String[] args)
	{
		int a = 10, b = 15, g;

		// Function call
		g = gcd(a, b);
		System.out.println("GCD(" + a + ", " + b
						+ ") = " + g);
	}
}
```

#### Modulo
Properties:
```
- (a % b) % b = a % b
- (ab) % a = 0
- (a + b) % m = ((a % m) + (b % m)) % m
- (a * b) % m = ((a % m) * (b % m)) % m
- (a – b) % m = ((a % m) – (b % m) + m) % m
- (a / b) % m ≠ ((a % m) / (b % m)) % m
```

##### Sieve of Eratosthenes:
It is used to find all the prime numbers upto any value ****N****. It takes time of ****O(N*sqrt(N))**** time, whereas, using the Sieve of Eratosthenes, we can find the primes in ****O(N*log(logN))**** time.
```java
// Java program to print all primes smaller than
// or equal to n using Sieve of Eratosthenes
import java.io.*;

class SieveOfEratosthenes {
	void sieveOfEratosthenes(int n)
	{
		// Create a boolean array "prime[0..n]" and
		// initialize all entries it as true.
		// A value in prime[i] will finally be false
		// if i is Not a prime, else true.
		boolean prime[] = new boolean[n + 1];
		for (int i = 0; i <= n; i++)
			prime[i] = true;

		for (int p = 2; p * p <= n; p++) {

			// If prime[p] is not changed,
			// then it is a prime
			if (prime[p] == true) {

				// Update all multiples of p greater than or
				// equal to the square of it numbers which
				// are multiple of p and are less than p^2
				// are already been marked.
				for (int i = p * p; i <= n; i += p)
					prime[i] = false;
			}
		}

		// Print all prime numbers
		for (int i = 2; i <= n; i++) {
			if (prime[i] == true)
				System.out.print(i + " ");
		}
	}

	// Driver Code
	public static void main(String args[])
	{
		int n = 30;
		SieveOfEratosthenes g = new SieveOfEratosthenes();
		System.out.println(
			"Following are the prime numbers "
			+ "smaller than or equal to " + n);
	
		// Function call
		g.sieveOfEratosthenes(n);
	}
}

```

> GCD and LCM: There is an important relation between GCD and LCM which is 
> `GCD*LCM = multiplication of both numbers`.

### Perfect Square 1 -> n 
![[Pasted image 20250115132951.png]]
> SO the number of integers between 1->n for which X^2 is < n is bs(1,n)

```java
class Solution {
    private int sol(int x, int y, int n){
        if(x>y){
            return y;
        }

        int md = x+(y-x)/2;
        if((n/md) < md){
            return sol(x,md-1,n);
        }else{
            return sol(md+1,y,n);
        }
    }
    public int bulbSwitch(int n) {
        if(n==1 || n==0){
            return n;
        }
        int a = 1;
        int b = n;

        return sol(a,b,n);
    }
}
```

### **1. Finding Divisors of a Number**
#### **Problem**: Find all divisors of a number nn.
- **Approach**: Use a loop up to n\sqrt{n}. For every number ii, if n%i==0n \% i == 0, ii and n/in/i are divisors.
- 
**Code**:

```java
import java.util.*;

public class Divisors {
    public static List<Integer> findDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                divisors.add(i); // Add i
                if (i != n / i) { // Add n/i only if it's distinct
                    divisors.add(n / i);
                }
            }
        }
        Collections.sort(divisors); // Optional: To sort the divisors
        return divisors;
    }

    public static void main(String[] args) {
        int n = 28;
        System.out.println("Divisors of " + n + ": " + findDivisors(n));
    }
}
```

**Output**:

```
Divisors of 28: [1, 2, 4, 7, 14, 28]
```

---
### **2. Prime Number Check**
#### **Problem**: Determine if a number is prime.
- **Approach**: Check divisibility up to n\sqrt{n}.
**Code**:

```java
public class PrimeCheck {
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 29;
        System.out.println(n + " is prime? " + isPrime(n));
    }
}
```

**Output**:

```
29 is prime? true
```

---
### **6. Modular Exponentiation**

#### **Problem**: Compute abmod  ma^b \mod m efficiently.

- **Approach**: Use the **exponentiation by squaring** method.

**Code**:

```java
public class ModularExponentiation {
    public static int modExp(int a, int b, int m) {
        int result = 1;
        a = a % m;

        while (b > 0) {
            if ((b & 1) == 1) { // If b is odd
                result = (result * a) % m;
            }
            a = (a * a) % m; // Square the base
            b >>= 1; // Divide b by 2
        }
        return result;
    }

    public static void main(String[] args) {
        int a = 2, b = 10, m = 1000;
        System.out.println(a + "^" + b + " % " + m + " = " + modExp(a, b, m));
    }
}
```

**Output**:

```
2^10 % 1000 = 24
```

---

### **7. Counting Factors**

#### **Problem**: Count the number of factors of nn.

- **Approach**: Iterate up to n\sqrt{n}, counting factors.

**Code**:

```java
public class CountFactors {
    public static int countFactors(int n) {
        int count = 0;
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                count++; // Factor i
                if (i != n / i) count++; // Factor n / i
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 36;
        System.out.println("Number of factors of " + n + ": " + countFactors(n));
    }
}
```

**Output**:

```
Number of factors of 36: 9
```

---
### **8. Combinatorics: nCr (Binomial Coefficient)**
#### **Problem**: Compute (nr)=n!r!(n−r)!\binom{n}{r} = \frac{n!}{r!(n-r)!}.
- **Approach**: Use iterative multiplication to avoid factorial overflow.
**Code**:

```java
public class BinomialCoefficient {
    public static int nCr(int n, int r) {
        if (r > n) return 0;
        if (r == 0 || r == n) return 1;

        int result = 1;
        for (int i = 1; i <= r; i++) {
            result = result * (n - i + 1) / i;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 5, r = 2;
        System.out.println(n + " choose " + r + " = " + nCr(n, r));
    }
}
```

**Output**:

```
5 choose 2 = 10
```

---

These algorithms cover a range of math-related concepts frequently tested in interviews. Let me know if you’d like to explore any of these further!

### Questions:
- [[73]]**
- [[50]]*
- [[43]]*
- [[342]]
- [[319]]** (BS)