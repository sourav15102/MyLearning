### Define:
**Strategy** is a behavioral design pattern that lets you define a family of algorithms, put each of them into a separate class, and make their objects interchangeable.
### Problem:
Lets say we have client code which is using some algorithm to perform operation, and there can be multiple algorithms like that. Each time we add a new algorithm, we need to modify client code or complicate it to accommodate new kind of algorithm.

### Solution:
We can define a property in client class where it will refer to the interface of strategy and a method which will implement the desired functionality of the strategy object.
![[Pasted image 20230813164149.png]]

### When to use:
- When we have multiple ways of doing similar things
- when we want to use different variants of an algorithm within an object and be able to switch from one algorithm to another during runtime.
- Use the pattern when your class has a massive conditional statement that switches between different variants of the same algorithm.