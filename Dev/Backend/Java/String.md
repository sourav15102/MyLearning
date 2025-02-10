https://www.interviewbit.com/java-string-interview-questions/
- Placeholder
```java
String name = "Alice";
int age = 30;
double salary = 50000.0;
System.out.printf("My name is %s, I'm %d years old, and I earn $%.2f per year.", name, age, salary);
```

Same using "replace":
```java
String x = "q=%s";
String query = "US";
String result = x.replace("%s", query);
System.out.println(result); // Output: "q=US"
```

- Index of
```java
String str = "Hello, World!";
int index = str.indexOf("World");
System.out.println("Index of 'World' in the string is: " + index);
// index = 7
```

- compareTo
```java
1. class Teststringcomparison4{  
2.  public static void main(String args[]){  
3.    String s1="Sachin";  
4.    String s2="Sachin";  
5.    String s3="Ratan";  
6.    System.out.println(s1.compareTo(s2));//0  
7.    System.out.println(s1.compareTo(s3));//>0(because s1>s3)  
8.    System.out.println(s3.compareTo(s1));//<0(because s3 < s1 )  
9.  }  
10. }
```
----
```java
1. class Teststringcomparison2{  
2.  public static void main(String args[]){  
3.    String s1="Sachin";  
4.    String s2="SACHIN";  

6.    System.out.println(s1.equals(s2));//false  
7.    System.out.println(s1.equalsIgnoreCase(s2));//true  
8.  }  
9. }
```

### Is String a primitive or derived type in Java?
Strings are derived data types. Strings are Java objects that represent sequences of characters. String objects are created using the java.lang.String class. There are many functions that need to be called upon when processing a string, such as substring(), indexof(), equals(), toUppercase(), etc, which primitives types do not have

> String is immutable in Java because of many reasons like security, caching, synchronization and concurrency, and class loading.

### Why immutability:
### 1️⃣ **Security**
- Many Java applications deal with sensitive data, such as **usernames, passwords, and URLs**. If `String` were mutable, an attacker could modify the contents of a string that represents, for example, a database connection URL or a security token.
### 3️⃣ **Synchronization & Concurrency**
- Strings are widely used in **multi-threaded** applications. If `String` were mutable, multiple threads modifying the same string could lead to unpredictable behavior.
- **Immutable objects** are naturally **thread-safe**, as their state cannot be changed after creation.
### 4️⃣ **Class Loading**
- Strings are used in **class loading**, e.g., when passing class names to `Class.forName()`.
- If `String` were mutable, an attacker could change the class name dynamically, leading to **security vulnerabilities**.
### 5️⃣ **Hashcode Consistency**
- `String` is commonly used as a **key in HashMap, HashSet, and HashTable**.
- If `String` were mutable, its **hashcode would change** after modification, making retrieval impossible.

> **String Equals Ignore Case**: By using this method, the two strings are compared without taking into account the case (upper or lower). It returns true if the two values are the same and not null.

> **Object Equals Method**: The method returns true if its arguments are equal, otherwise, it returns false. Accordingly, if both arguments are null, the result is true, and if just one argument is null, the result is false.

> - **String Compare To Method**: This method compares input strings with each other. Upon comparison, the following value is returned:
>1. If (str1>str2), a positive value is returned.
>2. If (str1==str2), 0 is returned.
> 1. If (str1<str2), a negative value is returned.

> `switch` compares `String` using `.equals()` internally, not `==`
```java
public class SwitchStringExample {
    public static void main(String[] args) {
        String fruit = "apple";  // String literal (stored in String Pool)

        switch (fruit) {
            case "apple":
                System.out.println("It's an apple!");
                break;
            case "banana":
                System.out.println("It's a banana!");
                break;
            default:
                System.out.println("Unknown fruit");
        }
    }
}

```

### Explain the string subSequence method.
The Java String subSequence() method is a built-in function that returns a charSequence (a subsequence) from a string.
```java
CharSequence subSeq = str.subSequence(0, 5); System.out.println("Original String: " + str); System.out.println("Subsequence: " + subSeq); System.out.println("Is subSeq a String? " + (subSeq instanceof String));
```

### CharSequence
**`CharSequence`** is an interface representing a readable sequence of characters, implemented by `String`, `StringBuilder`, `StringBuffer`, etc.
```java
public interface CharSequence {
    int length();
    char charAt(int index);
    CharSequence subSequence(int start, int end);
    default String toString() { ... }
}
```

### What do you mean by StringJoiner?
StringJoiner is a Java class that allows you to construct or create a sequence of strings (characters) that are separated by delimiters like a hyphen(-), comma(,), etc. Optionally, you can also pass suffix and prefix to the char sequence.
```java
public final class StringJoiner implements CharSequence

```
```java
import java.util.StringJoiner;  
public class ExampleofStringJoiner
{  
   public static void main(String[] args) 
   {  
       StringJoiner joinStrings = new StringJoiner(",", "[", "]");
       // passing comma(,) and square-brackets as delimiter   
         
       // Adding values to StringJoiner  
       joinStrings.add("Scaler");  
       joinStrings.add("By");  
       joinStrings.add("InterviewBit");            
       System.out.println(joinStrings);  
   }  
}
```

### How can a Java string be converted into a byte array?
The getBytes() method allows you to convert a string to a byte array by encoding or converting the specified string into a sequence of bytes using the default charset of the platform. Below is a Java program to convert a Java String to a byte array.
```java
import java.util.Arrays;
public class StringToByteArray 
{
   public static void main(String[] args)         
   {
      String str = "Scaler";
 byte[] byteArray = str.getBytes();
  // print the byte[] elements
     System.out.println("String to byte array: " + Arrays.toString(byteArray));
   }
}
```

### In Java, how do you convert a string to an integer and vice versa?
```java
public class StringtoInteger {
  public static void main(String args[]) 
  {
     String str1 = "1296";
     int  i= Integer.parseInt(str1);
     System.out.println(i);
     String str2 = Integer.toString(i);
     System.out.println(str2);
  }
}
```

