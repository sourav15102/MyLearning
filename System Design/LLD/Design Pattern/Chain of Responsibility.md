### Define
**Chain of Responsibility** is a behavioral design pattern that lets you pass requests along a chain of handlers. Upon receiving a request, each handler decides either to process the request or to pass it to the next handler in the chain.
### Problem
Lets say we have a request where we need to pass it through bunch of 'checks' before passing it to the main method where it will perform its intended functions.
These 'checks' can change and become complicated with the time.

### Solution
we can arrange handlers or checks in this case in sequential manner where each handler can decide to stop or pass it to further handler after processing it.
![[Pasted image 20230814232230.png]]
![[Pasted image 20230814233628.png]]

### When to use
- Use the Chain of Responsibility pattern when your program is expected to process different kinds of requests in various ways, but the exact types of requests and their sequences are unknown beforehand.
- Use the pattern when it’s essential to execute several handlers in a particular order.

Difference between [[Chain of Responsibility]] and [[Proxy Pattern]]:
1. Proxy has one object where COR can have multiple.
2. Proxy object never processes the request whereas in COR, the handlers can choose to process the requests themselves.