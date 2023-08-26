### Define
**Decorator** is a structural design pattern that lets you attach new behaviors to objects by placing these objects inside special wrapper objects that contain the behaviors.

### Problem:
We might need to send combination of notifications, if we go by [[Strategy]] pattern, in future we would just keep on adding more subclasses, because the customer might ask for combination of these subclasses and since inheritance is not flexible, it will be more complicated.
![[Pasted image 20230813173522.png]]

### Solution:
- Inheritance is specific and composition can provide us with the flexibility we need.
- ![[Pasted image 20230813175119.png]]
	![[Pasted image 20230813175151.png]]
### When to use:
- we can achieve this behavior using [[Strategy]] pattern with multiple strategies, however if we want more flexibility then inheritance or we need to change the object on runtime, we can use this.
- Use the Decorator pattern when you need to be able to assign extra behaviors to objects at runtime without breaking the code that uses these objects.
- Use the pattern when it’s awkward or not possible to extend an object’s behavior using inheritance.