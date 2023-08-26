### Define:
**Proxy** is a structural design pattern that lets you provide a substitute or placeholder for another object. A proxy controls access to the original object, allowing you to perform something either before or after the request gets through to the original object.

### Problem:
If we have a class and client calling those classes' object need to do some initializing task, we make write that code in object itself, but that would make it heavy.

### Solution:
We can use proxy design pattern where  a proxy can used in front of an object which perform:
1. logging
2. access checks
and then delegate the work to actual object when needed.
> They both are from same interface.
![[Pasted image 20230815143511.png]]

### When to use:
1. for performing access checks.
2. for logging

Difference between [[Chain of Responsibility]] and [[Proxy Pattern]]:
1. Proxy has one object where COR can have multiple.
2. Proxy object never processes the request whereas in COR, the handlers can choose to process the requests themselves.
