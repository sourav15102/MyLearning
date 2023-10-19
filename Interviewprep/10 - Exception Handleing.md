
**Q: Types of exception**
A:
Three types:
1. Checked
	1. which are checked at compile-time. For example, SQLException, ClassNotFoundException, etc.
2. Unchecked
	1. handled at runtime because they can not be checked at compile-time. For example, ArithmaticException, NullPointerException, ArrayIndexOutOfBoundsException, etc.
3. Error
	1. cause the program to exit since they are not recoverable. For Example, OutOfMemoryError, AssertionError, etc.


**Q: Hierarchy in exception**
A:
The `java.lang.Throwable` class is the root class of Java Exception hierarchy which is inherited by two subclasses: Exception and Error. A hierarchy of Java Exception classes are given below:

![hierarchy of exception handling](https://static.javatpoint.com/images/throwable.png)


**Q: finally block**
A:
- The _finally_ block executes regardless of whether an exception is thrown or caught.
- In case there is `return statement` anywhere, it executes before control is given to calling method.
Use:
- to execute clean up code like closing connections, closing files, or freeing up threads, as it executes regardless of an exception.

Order of execution for final:
```java
try {
    System.out.println("Inside try");
} finally {
    System.out.println("Inside finally");
}
//output
Inside try
Inside finally
```

```java
try {
    System.out.println("Inside try");
    throw new Exception();
} finally {
    System.out.println("Inside finally");
}
//output
Inside try
Inside finally
Exception in thread "main" java.lang.Exception
```

```java
try {
    System.out.println("Inside try");
    throw new Exception();
} catch (Exception e) {
    System.out.println("Inside catch");
} finally {
    System.out.println("Inside finally");
}
//output
Inside try
Inside catch
Inside finally
```

```java
try {
    System.out.println("Inside try");
    return "from try";
} finally {
    System.out.println("Inside finally");
}
//output
Inside try
Inside finally
```

```java
try {
    System.out.println("Inside try");
    throw new Exception();
} catch (Exception e) {
    System.out.println("Inside catch");
    return "from catch";
} finally {
    System.out.println("Inside finally");
}
//output
Inside try
Inside catch
Inside finally
```

When finally isn't executed:
```java
try {
    System.out.println("Inside try");
    System.exit(1);
} finally {
    System.out.println("Inside finally");
}
//output
Inside try
```

```java
try {
    System.out.println("Inside try");
    Runtime.getRuntime().halt(1);
} finally {
    System.out.println("Inside finally");
}
//output
Inside try
```

```java
try {
    System.out.println("Inside try");
    while (true) {
    }
} finally {
    System.out.println("Inside finally");
}
// Though it's not specific to _finally_, it's worth mentioning that if the _try_ or _catch_ block contains an infinite loop, the JVM will never reach any block beyond that loop.
```

> it's considered bad practice to have a _return_ statement or throw an exception from a _finally_ block


**Q: is it necessary to have catch with try?**
A:
No, it is not necessary that each try block must be followed by a catch block. It should be followed by either a catch block OR a finally block

**Q: Throw vs Throws**
A:
Let us talk about the differences between Throw and Throws in Java.
- Throw can only throw objects of `Throwable`.


|   |   |   |
|---|---|---|
|**Parameters**|**Throw in Java**|**Throws in Java**|
|Definition|The throw keyword helps in throwing an exception in the program, explicitly inside some block of code or inside any function.|We use the throws keyword in the method signature. We use it to declare some exceptions thrown by a function when the code is getting executed.|
|Internal Implementation|The throw keyword is implemented internally because it is only allowed to throw a single exception at once. It means that it is not possible to throw multiple exceptions when we are using the throw keyword.|With the throws keyword, on the other hand, one can easily declare various exceptions. These could be thrown by any function when using the throws keyword.|
|Type of Exception|**One can only propagate the unchecked exceptions using the throw keyword only**. It means that no checked exception can be propagated when we use the throw keyword.|When we use the throws keyword, we can declare both unchecked and checked exceptions. The checked expression must always use the throws keyword for propagation followed by a specific name of the exception class.|
|Syntax|The instance variable follows the throw keyword.|The exception class names follow the throws keyword.|
|Declaration|When we are using the throw keyword, we must ensure that we are using the throw keyword within the available method.|The throws keyword, on the other hand, must always be used within the signature of a method (method signature).|

**Q: in above question what is meant by 
"One can only propagate the unchecked exceptions using the throw keyword only. It means that no checked exception can be propagated when we use the throw keyword."**

A: It means that we can use throw to throw any object of throwable be it error, checked or unchecked exceptions.
But, with checked exceptions we need to either specify it with 'throws' or handle it then and there with try and catch. so, it cant be propagated with 'throw' ONLY, but with throws it can be propagated.
With runtime exceptions we dont need to use 'throws'
	- Unchecked exceptions are subclasses of `RuntimeException` (or any of its subclasses), and they are not required to be caught or declared in a `throws` clause. They can be propagated through the call stack without the need for explicit handling.
	- On the other hand, checked exceptions (exceptions that are not subclasses of `RuntimeException`) must be either caught using a `try-catch` block or declared in a `throws` clause in the method signature. You cannot throw checked exceptions using the `throw` keyword without providing proper handling.

**What is the output of the following Java program?**
```java

1.   public class Main{  
2.      public static void main(String []args){  
3.         try  
4.         {  
5.             throw 90;   
6.         }  
7.         catch(int e){  
8.             System.out.println("Caught the exception "+e);  
9.         }  

11.     }  
12. }
```
A:
Only throwable objects can only be thrown using `throw`. If we try to throw an integer object, The compiler will show an error since we can not throw basic data type from a block of code.


> An exception can be rethrown.

**Q: What is exception propagation?**
A:
An exception is first thrown from the top of the stack and if it is not caught, it drops down the call stack to the previous method, If not caught there, the exception again drops down to the previous method, and so on until they are caught or until they reach the very bottom of the call stack. This procedure is called exception propagation. 
By DEFAULT, checked exceptions are not propagated.	- what it means is that:
		- cos it is a compile time exception, whenever we throw it, compiler will force us to either handle it there or use throws: in this case it will be propagated, which we can that it wasn't propagated by default.
		- where was in case of runtime exception:
		- for instance here we didn't need to do anything:
```java
1. class TestExceptionPropagation1{  
2.   void m(){  
3.     int data=50/0;  
4.   }  
5.   void n(){  
6.     m();  
7.   }  
8.   void p(){  
9.    try{  
10.     n();  
11.    }catch(Exception e){System.out.println("exception handled");}  
12.   }  
13.   public static void main(String args[]){  
14.    TestExceptionPropagation1 obj=new TestExceptionPropagation1();  
15.    obj.p();  
16.    System.out.println("normal flow...");  
17.   }  
18. }
```

**Q: What is the output of the following Java program?**
```java

1. public class Calculation   
2. {  
3.     int a;   
4.     public Calculation(int a)  
5.     {  
6.         this.a = a;  
7.     }  
8.     public int add()  
9.     {  
10.         a = a+10;   
11.         try   
12.         {  
13.             a = a+10;   
14.             try   
15.             {  
16.                 a = a*10;   
17.                 throw new Exception();   
18.             }catch(Exception e){  
19.                 a = a - 10;  
20.             }  
21.         }catch(Exception e)  
22.         {  
23.             a = a - 10;   
24.         }  
25.         return a;  
26.     }  

28.     public static void main (String args[])  
29.     {  
30.         Calculation c = new Calculation(10);  
31.         int result = c.add();  
32.         System.out.println("result = "+result);  
33.     }  
34. }
// **Output**
// result = 290
```

**Explanation**
The instance variable a of class Calculation is initialized to 10 using the class constructor which is called while instantiating the class. The add method is called which returns an integer value result. In add() method, a is incremented by 10 to be 20. Then, in the first try block, 10 is again incremented by 10 to be 30. In the second try block, a is multiplied by 10 to be 300. The second try block throws the exception which is caught by the catch block associated with this try block. The catch block again alters the value of a by decrementing it by 10 to make it 290. Thus the add() method returns 290 which is assigned to result. However, the catch block associated with the outermost try block will never be executed since there is no exception which can be handled by this catch block.


