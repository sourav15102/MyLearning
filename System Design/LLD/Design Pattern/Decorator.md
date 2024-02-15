https://www.baeldung.com/java-decorator-pattern
### Define
**Decorator** is a structural design pattern that lets you attach new behaviors to objects by placing these objects inside special wrapper objects that contain the behaviors.

### Problem:
We might need to send combination of notifications, if we go by [[Strategy]] pattern, in future we would just keep on adding more subclasses, because the customer might ask for combination of these subclasses and since inheritance is not flexible, it will be more complicated.

![[8poz64T.jpg]]


```java
public interface ChristmasTree {
    String decorate();
}
```

```java
public class ChristmasTreeImpl implements ChristmasTree {
    @Override
    public String decorate() {
        return "Christmas tree";
    }
}
```

```java
public abstract class TreeDecorator implements ChristmasTree {
    private ChristmasTree tree;
    
    // standard constructors
    @Override
    public String decorate() {
        return tree.decorate();
    }
}
```

```java
public class BubbleLights extends TreeDecorator {

    public BubbleLights(ChristmasTree tree) {
        super(tree);
    }
    
    public String decorate() {
        return super.decorate() + decorateWithBubbleLights();
    }
    
    private String decorateWithBubbleLights() {
        return " with Bubble Lights";
    }
}
```

Process:
1. We can take `ChristmasTreeImpl` as basic tree. (any direct subclass (not decorator) of Christmas tree can be taken as "base" class) it will be the starting point, on top of which we can add decorations. 
2. We we pass base class `ChristmasTreeImpl` to `BubbleLights`, and when we call decorator on `BubbleLights`, it will call decorator of `ChristmasTreeImpl` + its own thing.
3. Now recursively when we pass `BubbleLights` to another decorator it will add `BubbleLights`'s decoration to its own and so on.


![[Pasted image 20230813173522.png]]

### Solution:
- Inheritance is specific and composition can provide us with the flexibility we need.
- ![[Pasted image 20230813175119.png]]
	![[Pasted image 20230813175151.png]]
### When to use:
- we can achieve this behavior using [[Strategy]] pattern with multiple strategies, however if we want more flexibility then inheritance or we need to change the object on runtime, we can use this.
- Use the Decorator pattern when you need to be able to assign extra behaviors to objects at runtime without breaking the code that uses these objects.
- Use the pattern when it’s awkward or not possible to extend an object’s behavior using inheritance.