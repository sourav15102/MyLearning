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

Reduce:
```java
//reduce(T identity, BinaryOperator<T> accumulator)
T result = identity;
     for (T element : this stream)
         result = accumulator.apply(result, element)
     return result;
     
public int sumContactsAgesUsingReduce(List<Contact> contacts) {
   return contacts.stream()
           .map(contact -> Period.between(contact.getBirthDate(), LocalDate.now()).getYears())
           .reduce(0, (contactAge, otherContactAge) -> contactAge + otherContactAge);
}
```


https://stackify.com/streams-guide-java-8/