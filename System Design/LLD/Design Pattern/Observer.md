### Define:
**Observer** is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they’re observing.
### Problem:
Shop owner needs to notify all customers about a product, but to only those who want to know about that product.

### Solution:
![[Pasted image 20230813170923.png]]

### When to use:
- Use the Observer pattern when changes to the state of one object may require changing other objects, and the actual set of objects is unknown beforehand or changes dynamically.
- Use the pattern when some objects in your app must observe others, but only for a limited time or in specific cases.