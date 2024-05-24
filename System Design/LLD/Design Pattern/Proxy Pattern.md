
https://www.youtube.com/watch?v=9MxHKlVc6ZM&list=PL6W8uoQQ2c61X_9e6Net0WdYZidm7zooW&index=15

Use Cases:
1. Pre-processing
2. Accesss control.
3. Cache
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

[[Adaptor]] vs [[Proxy Pattern]]:
- Purpose of both is different, proxy acts as same object and does some preprocessing before calling actual service, whereas adaptor is used to make both client and service compatible.
- Proxy and actual service extend same base interface.
- Above is not true for Adaptor design pattern.