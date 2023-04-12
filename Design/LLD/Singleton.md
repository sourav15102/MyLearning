
#### ***Why:***
It serves two purpose:
1. ensures class has just single instance.
2. provides global access point to that instance.

#### ***How***
- Make the default constructor private, to prevent other objects from using the `new` operator with the Singleton class.
- Create a static creation method that acts as a constructor. Under the hood, this method calls the private constructor to create an object and saves it in a static field. All following calls to this method return the cached object.

#### ***When:***
1. Use the Singleton pattern when a class in your program should have just a single instance available to all clients; for example, a single database object shared by different parts of the program.
2. Use the Singleton pattern when you need stricter control over global variables.

Naive (Not thread-safe):
```java
public class Singleton {  
    private static Singleton instance=null;  
    private int x;  

    public int getX(){  
        return x;  
    }  
  
    public void setX(int x){  
        this.x = x;  
    }  
  
    private Singleton(){}  
    public static Singleton getInstance(){  
        if(instance==null){  
            return (instance = new Singleton());  
        }else{  
            return instance;  
        }  
    }  
}
```


