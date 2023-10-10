Automatic conversion of primitive data types into its equivalent Wrapper type is known as boxing and opposite operation is known as unboxing

It works fine everywhere but for overloading there are some rules:
- **Widening beats boxing**
- **Widening beats varargs**
- **Boxing beats varargs**

> "widening" refers to the automatic conversion of a smaller data type to a larger data type

> varags is: `static void m(Integer... i){System.out.println("Integer...");}`

But in case of both widening and boxing: compile time errror:
```java
- class Boxing4{  
-   static void m(Long l){System.out.println("Long");}  

-   public static void main(String args[]){  
-    int a=30;  
-    m(a);  
-  }   
- }
```


Q: What is the output of the below Java program?
```java
1. public class Test1  
2. {  
3.   public static void main(String[] args) {  
4.   Integer i = new Integer(201);  
5.   Integer j = new Integer(201);  
6.   if(i == j)  
7.   {  
8.     System.out.println("hello");  
9.   }  
10.   else   
11.   {  
12.     System.out.println("bye");  
13.   }  
14.   }  
15. }  
```
A:
**Output**
```
bye
```
**Explanation**
In Java, for small integers within the range of -128 to 127 (inclusive), there is a special feature called *==*"integer caching" or "integer pool." *==* Integer values in this range are cached, and when you create `Integer` objects with values in this range, they are actually referencing the same cached instances.
```java
Integer i = 100; 
Integer j = 100;
```
In this case, `i` and `j` would actually reference the same cached `Integer` object for the value 100, and when you compare them with `==`, it would result in "hello" because they point to the same object in memory.

However, for values outside the range of -128 to 127, new `Integer` objects are created, and `==` would compare their references, which would be different.
The Integer class caches integer values from -127 to 127. Therefore, the Integer objects can only be created in the range -128 to 127. The operator **==** will not work for the value greater than 127; thus **bye** is printed.
Same goes for Long.

For beyonf -128 to 127, it will create different objects.


Q: what happens when we compare Integer with int?
A:
1. The `Integer` object is unboxed, meaning it is converted to its primitive `int` value.  
2. The comparison is then performed using the two `int` values.
3. The result of the comparison is a boolean value (`true` or `false`) based on the comparison result.
```java
Integer integerObj = 5; // Autoboxing, creating an Integer object int intValue = 5; 
boolean result = (integerObj == intValue); // Comparison between Integer and int  
System.out.println(result); // This will print "true"
```
In this example, the `integerObj` is automatically unboxed to its `int` value, and then the comparison is done between two `int` values, which results in `true` because both values are equal.

