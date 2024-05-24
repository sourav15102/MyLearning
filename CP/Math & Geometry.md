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
### Questions:
- [[73]]**
- [[50]]*
- [[43]]*
- [[342]]
- 