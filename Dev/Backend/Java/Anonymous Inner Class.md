Ref link: https://www.youtube.com/watch?v=DwtPWZn6T1A

### Basics:
- It doesnt have a name.
- It is exactly like creating a unnamed subclass of a class with on-time single object.
Example:
```java
class Animal {
    void makeNoise() {
        System.out.println("Woof Woof");
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating an anonymous class for Animal
        Animal cat = new Animal() {
            @Override
            void makeNoise() {
                System.out.println("Meow Meow");
            }
        };

        // Calling makeNoise on the anonymous class instance
        cat.makeNoise();
    }
}
```

Explanation:
1. cat is basically an object of type Animal but is object is of type "that unnamed anonymous class" with body mentioned in {}.
2. "Unnamed anonymous" class is a subclass or child class of Animal class.
 