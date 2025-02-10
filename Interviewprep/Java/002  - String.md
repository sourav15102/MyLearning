JVM has string pool(in heap memory) where it keeps string literal objects.
so, when we create a String literal:
```java
String x = "abc";
String y = "abc";
```
- Here the pool has object `abc` and x points to it.
- y points to same object as well (i.e, they have same hashcode)
- x.hashCode() and y.hashCode() will be same.
if we do:
```java
x = x + " pqr";
```
- it wont change the string object, rather it will create new String literal in heap and string constant pool and x will start pointing to that heap literal instead. 
```java
        String str1 = "Hello";  
        String str2 = "World";  
        String str4 = str1 + str2;  // is heap pool.
        String x = "HelloWorld";  // litral in string constant pool.
        System.out.println(x==str4);  
        System.out.println(x==str4.intern());
```
```
false
true
```

Why is it so?: there are many benefits:
- Saves memory: pointing to same object literal.
- Security: 2 String variables pointing to same String literal in memory, if it is immutable, none of them can change it and hence mess up another variables' value.
- Thread safe: no thread can change the string.

> Java uses String pool in heap memory with just "String literal".

**However:**
If we were to do:
```java
String p = new String("abc");
```
- here instead of using same shared literal of "abc" from string pool, java will create another object in heap BUT outside of string pool of "abc", which will be different from the previous "abc" where everybody else was pointing to.

Proof:
```java
(x==y) will return true.
(x==p) wont, because they are not pointing to same object.
```

**Q: == vs .equals**
A:
Generally:
1. `equals(...)` method compares exactly what it's coded to compare, no more or less.
2. If a class doesn't override `equals`, it uses the closest parent class's `equals(Object o)` method that's overridden.
3. If no parent class overrides it, it defaults to `Object#equals(Object o)`, which checks if references are the same `==`.
4. Always override `hashCode` if you override `equals` to maintain the contract: `hashCode` must be same for equivalent objects.
5. `hashCode` ensures consistency between object equality and hash-based data structures.
6. HOWEVER, with Integer, Double, they will act like int , double only.
	1. because:
	2. `Integer x = 1`, looks like `Integer x = Integer.valueOf(1)` inside.
	3. and `a==b` looks like `a.intValue() == b` inside.
7. Above will work for Integer, Long, Boolean etc, but not for Double, cos Double doesnt use pooling.
String perspective:
- String has implemented equals as they will check for values of string.
- `==` will check for object reference as usual.

BUT:
- if we are talking about primitive data types like int, then it will check for values directly.

**Q:  What is String Pool?**
A:
- String pool is the space reserved in the heap memory that can be used to store the strings. 
- The JVM checks the "string constant pool" first. If the string already exists in the pool, a reference to the pooled instance is returned. If the string doesn't exist in the pool, a new string instance is created and placed in the pool. 
- Therefore, it saves the memory by avoiding the duplicacy.

**Q: How string.concat works?**
A:
```java
s.concat("dsafs"); //it will return a new string but wont change s
```

**Q: How many ways can we create the string object?**
A:
1. String literal
2. new keyword.

**Q: How many objects will be created in the following code?**
```java
1. String s1="Welcome";  
2. String s2="Welcome";  
3. String s3="Welcome";  
```
A: Only one object will be created using the above code because strings in Java are immutable

**Q: How many objects will be created in the following code?**
 ```java
1. String s = new String("Welcome");  
```
A: Two objects, one in string constant pool and other in non-pool(heap), but, s will point to the non-pool(heap) one.

**Q: What is string's intern method?**
A:
As we know that when we do:
```java
String x = new String("fdgd");
```
It creates two objects, one in string constant pool and another in non-pool but within heap and x will point to the non-pool(heap) one.
but if we want x to point to its canonical counterpart, i.e., the string constant pool obj, we can use `intern()` method.
```java
x = x.intern(); // will point to string constant pool obj.
```

**Q: What is the output of the following Java program?**
```java
1. public class Test   
2. {  
3.     public static void main (String args[])  
4.     {  
5.         String s1 = "Sharma is a good player";  
6.         String s2 = new String("Sharma is a good player");  
7.         s2 = s2.intern();  
8.         System.out.println(s1 ==s2);  
9.     }  
10. }
```
A: output is true
Explanation: refer to previous question.

**Q: String, StringBuffer, StringBuilder?**
A:

|Criteria|String|StringBuffer|StringBuilder|
|---|---|---|---|
|Mutability|Immutable|Mutable|Mutable|
|Thread Safety|Yes|Yes|No|
|Memory Consumption|High (creates new instances)|Low|Low|
|Performance for Concatenation|Slow (creates new instances)|Fast|Fast|
|`equals()` Method|Overrides `equals()` for content comparison|Does not override `equals()`|Does not override `equals()`|
|Efficiency|-|Less efficient due to synchronization|More efficient (no synchronization)|

> Synchronization: if there is sync, it means two threads can't call the methods of StringBuffer simultaneously.

**Q: what is an immutable class and how to create it in java?**
A:
Immutable class in java means that once an object is created, we cannot change its content. In Java, all the [wrapper classes](https://www.geeksforgeeks.org/wrapper-classes-java/) (like Integer, Boolean, Byte, Short) and String class is immutable

Process of creating immutable class:
- The class must be declared as final so that child classes can’t be created.
- Data members in the class must be declared private so that direct access is not allowed.
- Data members in the class must be declared as final so that we can’t change the value of it after object creation.
- While setting initially and getting the values, Deep copy should be set or get:
	- A parameterized constructor should initialize all the fields performing a deep copy so that data members can’t be modified with an object reference.
	- Deep Copy of objects should be performed in the getter methods to return a copy rather than returning the actual object reference)

```java
// Java Program to Create An Immutable Class

// Importing required classes
import java.util.HashMap;
import java.util.Map;

// Class 1
// An immutable class
final class Student {

	// Member attributes of final class
	private final String name;
	private final int regNo;
	private final Map<String, String> metadata;

	// Constructor of immutable class
	// Parameterized constructor
	public Student(String name, int regNo,
				Map<String, String> metadata)
	{

		// This keyword refers to current instance itself
		this.name = name;
		this.regNo = regNo;

		// Creating Map object with reference to HashMap
		// Declaring object of string type
		Map<String, String> tempMap = new HashMap<>();

		// Iterating using for-each loop
		for (Map.Entry<String, String> entry :
			metadata.entrySet()) {
			tempMap.put(entry.getKey(), entry.getValue());
		}

		this.metadata = tempMap;
	}

	// Method 1
	public String getName() { return name; }

	// Method 2
	public int getRegNo() { return regNo; }

	// Note that there should not be any setters

	// Method 3
	// User -defined type
	// To get meta data
	public Map<String, String> getMetadata()
	{

		// Creating Map with HashMap reference
		Map<String, String> tempMap = new HashMap<>();

		for (Map.Entry<String, String> entry :
			this.metadata.entrySet()) {
			tempMap.put(entry.getKey(), entry.getValue());
		}
		return tempMap;
	}
}

// Class 2
// Main class
class GFG {

	// Main driver method
	public static void main(String[] args)
	{

		// Creating Map object with reference to HashMap
		Map<String, String> map = new HashMap<>();

		// Adding elements to Map object
		// using put() method
		map.put("1", "first");
		map.put("2", "second");

		Student s = new Student("ABC", 101, map);

		// Calling the above methods 1,2,3 of class1
		// inside main() method in class2 and
		// executing the print statement over them
		System.out.println(s.getName());
		System.out.println(s.getRegNo());
		System.out.println(s.getMetadata());

		// Uncommenting below line causes error
		// s.regNo = 102;

		map.put("3", "third");
		// Remains unchanged due to deep copy in constructor
		System.out.println(s.getMetadata());
		s.getMetadata().put("4", "fourth");
		// Remains unchanged due to deep copy in getter
		System.out.println(s.getMetadata());
	}
}

/*
Output:
ABC
101
{1=first, 2=second}
{1=first, 2=second}
{1=first, 2=second}
*/
```


**Q: why class should be final**
A: [reason](https://stackoverflow.com/questions/12306651/why-would-one-declare-an-immutable-class-final-in-java)

**Q: toString() method?**
A:
1. The `toString()` method returns the string representation of an object.
2. When an object is printed, the Java compiler internally calls the `toString()` method on the object.
3. Overriding the `toString()` method allows you to customize the output, such as the state of an object.
5. The `toString()` method is inherited from the `Object` class.
6. Overriding the `toString()` method eliminates the need for writing additional code to display object information.

**Why CharArray() is preferred over String to store the password?**
A:
String stays in the string pool until the garbage is collected. If we store the password into a string, it stays in the memory for a longer period, and anyone having the memory-dump can extract the password as clear text. On the other hand, Using CharArray allows us to set it to blank whenever we are done with the password. It avoids the security threat with the string by enabling us to control the memory.


https://www.interviewbit.com/java-string-interview-questions/
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



### Split:
### **Example of `String.split(String regex, int limit)` in Java**

The `split(String regex, int limit)` method splits a string based on a **regular expression** (`regex`) and limits the **number of resulting substrings**.

---
### **Syntax*

```java
public String[] split(String regex, int limit)
```

- **`regex`** → The delimiter (regular expression) used to split the string.
- **`limit`** → Specifies **how many splits** should be performed:
    - `limit > 0` → Maximum **(limit - 1)** splits, remaining part as last element.
    - `limit = 0` → Normal split (removes trailing empty substrings).
    - `limit < 0` → No limit (splits all occurrences, including empty substrings).

---
### **1️⃣ Example: `limit > 0` (Restricting Splits)**

```java
public class SplitExample {
    public static void main(String[] args) {
        String str = "apple,banana,orange,grape";

        // Split at most 2 times (creates 3 parts)
        String[] result = str.split(",", 3);

        for (String s : result) {
            System.out.println(s);
        }
    }
}
```

✅ **Output:**

```
apple
banana
orange,grape
```

- Splits **only twice**, the **remaining part stays together**.

---
### **2️⃣ Example: `limit = 0` (Default Behavior)**

```java
public class SplitExample {
    public static void main(String[] args) {
        String str = "apple,banana,,grape,";

        // Default split (removes trailing empty parts)
        String[] result = str.split("," , 0);

        for (String s : result) {
            System.out.println(s);
        }
    }
}
```

✅ **Output:**

```
apple
banana

grape
```

- **Trailing empty substring is removed**.
- Empty part **in the middle ("") is kept**.

---

### **3️⃣ Example: `limit < 0` (No Limit)**

```java
public class SplitExample {
    public static void main(String[] args) {
        String str = "apple,banana,,grape,";

        // Split without limit (keeps all empty substrings)
        String[] result = str.split(",", -1);

        for (String s : result) {
            System.out.println(s);
        }
    }
}
```

✅ **Output:**

```
apple
banana

grape
(empty line)
```

- **No limit means all empty strings are kept**, including the **last empty substring**.

