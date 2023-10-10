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
- it wont change the string object, rather it will create new String literal and x will start pointing to that literal instead. 

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


