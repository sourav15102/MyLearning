Ref link: https://www.youtube.com/watch?v=tklkyVa7KZo&t=458s

### Basics:
-  we can process a stream only one time. 

- Min/Max
```java
import java.util.Arrays;

public class Test {
    public static void main(String[] args){
        int[] tab = {12, 1, 21, 8};
        int min = Arrays.stream(tab).min().getAsInt();
        int max = Arrays.stream(tab).max().getAsInt();
        System.out.println("Min = " + min);
        System.out.println("Max = " + max)
    }

}
```
