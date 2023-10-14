**Q: Is Java a Compiled or an Interpreted programming language ?**
A: Java implementations typically use a two-step compilation process. Java source code is compiled down to _bytecode_ by the Java compiler. The bytecode is executed by a Java Virtual Machine (JVM). 
1. Modern JVMs use a technique called [Just-in-Time (JIT) compilation](http://en.wikipedia.org/wiki/Just-in-time_compilation) to compile the bytecode to native instructions understood by hardware CPU on the fly at runtime.
2. Some implementations of JVM may choose to interpret the bytecode instead of JIT compiling it to machine code, and running it directly. While this is still considered an "interpreter," It's quite different from interpreters that read and execute the high level source code (i.e. in this case, Java source code is not interpreted directly, the bytecode, output of Java compiler, is.) 

**Q: What is JDK, JRE and JVM?**
A: 
JVM:
1. converts bytecode into machine code.
2. Is platform dependent BUT provides an interface which is independent of underlying hardware, OS making java program platform independent.
3. handles garbage collection, memory management and security etc.
4. It doesn't physically exists, it is a process, a runtime instance which is created when we run the Java class(bytecode).
5. JVM is the one that actually calls the main method present in Java code
6. **JVM** perform some particular **types of operations**: 
	1. Loading of code
	2. Verification of code
	3. Executing the code
	4. It provides a run-time environment to the users
7. It contains class loader, bytecode verifier.
JRE:
1. Implementation of JVM.
2. platform to execute java program.
3. Consists of JVM, Java binaries, and other classes to execute any program successfully. 
4. JRE doesn’t contain any development tools such as Java compiler, debugger, JShell, etc.
5. If we just need to execute java we only need JRE not JDK.
6. it actually physically exists.
JDK:
1. JDK is a platform-specific software 
2. need separate installers for Windows, Mac, and Unix systems.
3. contains JRE with Java compiler, debugger, and core classes.
4. superset of JRE.
5. it actually physically exists.

![[Pasted image 20230822163756.png]]

>JDK is for development purpose whereas JRE is for running the java programs.


**Q: what is JIT compiler?**
A: The Just-In-Time (JIT) compiler is **a component of the runtime environment** that improves the performance of Java™ applications by compiling bytecodes to native machine code at run time.


![java jit compiler working](https://sp-ao.shortpixel.ai/client/to_auto,q_glossy,ret_img,w_520,h_481/https://simplesnippets.tech/wp-content/uploads/2018/03/java-jit-compiler-working.png)

- **Case 1:**
    - In the Case 1 there is no JIT involved. thus the interpreter converts each line into its corresponding machine code line. However if you notice the last 2 lines are the same (consider it a redundant line inserted by mistake). Clearly that line is redundant and does not have any effect on the actual output but yet since the interpreter works line by line it still creates 5 lines of machine code for 5 lines of the bytecode.
    - Now this is inefficient right? lets see case 2
- **Case 2:**
    - In case 2 we have the JIT compiler. Now before the bytecode is passed onto the interpreter for conversion to machine code, the **JIT compiler scans** the full code to see if it can be **optimized**. As it finds the last line is redundant it removes it from the bytecode and passes only 4 lines to the interpreter thus making it more **efficient** and **faster** as the interpreter now has 1 line less to interpret.
    - So this is how JIT compiler speeds up the overall execution process.


**Q: Why is Java Platform independent?**
A: Java compiler javac compiles the java code in bytecode(.java --> .class), which is platform independent, then we have JVM which is platform dependent, and it interprets the bytecode into machine code.

> Java uses Compiler and Interpreter both.

**Q: Types of memory allocated by JVM?**
A: 
1. Heap: 
	1. JVM starts up, it creates heap memory.
	2. it stores all objects created by app.
	3. its size can increase or decrease while running the app.
	4. If application’s heap usage reaches the maximum size and it still requires more memory,  OutOfMemoryError: Java heap space_ error is produced.
2. Stack:
	1. stores local variable and method information.
	2. Each thread has its own JVM stack.
	3. fixed size, which is determined by the JVM at runtime.
	4. stack runs out of memory, the JVM throws the StackOverflowError error.
3. Class Area:
	1. The class method area is the memory block that stores the class code, variable code(static variable, runtime constant), method code, and the constructor of a Java program.
4. Program Counter Register:
	1. PC (program counter) register contains the address of the Java virtual machine instruction currently being executed.
5. Native Method Stack: It contains all the native methods used in the application.

**Q: What is native method?**
A: 
- Native methods are Java methods that start in a language other than Java. Native methods can access system-specific functions and APIs that are not available directly in Java.
- The use of native methods limits the portability of an application, because it involves system-specific code. Native methods can either be new native code statements or native code statements that call existing native code.
- The JNI is a set of interfaces that permit a native method to interoperate with the Java virtual machine in numerous ways.

**Q: What is the platform?**
A: A platform is the hardware or software environment in which a piece of software is executed. There are two types of platforms, software-based and hardware-based. Java provides the software-based platform.

**Q: What is ClassLoader?**
A: It is a subsystem of JVM which is used to load class files. It is mainly responsible for three activities.
- Loading
- Linking
- Initialization
Types:
1. Bootstrap or primordial:
	1. Java classes are loaded by an instance of _java.lang.ClassLoader_, but, who loads the _java.lang.ClassLoader_ itself? This is where the bootstrap or primordial class loader comes into play.
	2. It loads the _rt.jar_ file which contains all class files of Java Standard Edition like java.lang package classes, java.net package classes, java.util package classes, java.io package classes, java.sql package classes, etc.
	3. Bootstrap class loader serves as the parent of all the other _ClassLoader_ instances.
	4. This bootstrap class loader is part of the core JVM and is written in native code
	5. Different platforms might have different implementations of this particular class loader.
	6. If we try to print the class loader name, this classloader will be printed as null.
	7. for example: classloader of ArrayList is null.
	8. Part of JVM.
2. Extension Class Loader
	1. The extension class loader is a child of the bootstrap class loader.
	2. takes care of loading the extensions of the standard core Java classes so that they're available to all applications running on the platform.
	3. The extension class loader loads from the JDK extensions directory, usually the _$JAVA_HOME/lib/ext_ directory, or any other directory mentioned in the _java.ext.dirs_ system property.
	4. part of JRE
3. System Class Loader or Application
	1. takes care of loading all the application level classes into the JVM. 
	3. It's also a child of the extensions class loader.
	4. part of JRE
	5. by default the classpath is set to current dir, but can be changed using -cp or -classpath flag.


**Q: Empty java file?**
A: we can create .java file and we can compile it ujsing javac .java and it will create classfile with the same name as its class lets say that is A, so we can run it using java A

**Q: Some words that sound like keywords in java**
A: delete, main, exit, next, null.

> No argument on command lin while calling main method will pass empty to args not null.
> Order of specifiers doest matter in Java: we can write static public instead of public static

**Q: Diff b/w all access specifiers?**
A: 

| Access Modifier | Within Class | Within Package | Outside Package by Subclass Only | Outside Package |
|------------------|--------------|----------------|----------------------------------|-----------------|
| Private          | Y            | N              | N                                | N               |
| Default          | Y            | Y              | N                                | N               |
| Protected        | Y            | Y              | Y                                | N               |
| Public           | Y            | Y              | Y                                | Y               |



**Q: Default value of variables local and otherwise?**
A: 

| Data Type             | Default Value (for fields) |
|-----------------------|----------------------------|
| byte                  | 0                          |
| short                 | 0                          |
| int                   | 0                          |
| long                  | 0L                         |
| float                 | 0.0f                       |
| double                | 0.0d                       |
| char                  | '\u0000'                   |
| String (or any object)| null                       |
| boolean               | false                      |

**Q: Interaction of Strings and Integers?**
A: concatenatio happens in case of 
```java
string x = "sdfs";
int g = 5;
g+x will give 5sdfs
```
**Q: Order of evaluation**
A: 
From left to right.
```java
System.out.println(10 + 20 + "Javatpoint");  // 30Javatpoint
System.out.println("Javatpoint" + 10 + 20); // Javatpoint1020
`System.out.println("Result"+2+3+4)` yields Result234 
`System.out.println("Result"+2+3*5)` returns Result215
```
Explanation:
The String concatenation operator (`+`) triggers the need for String conversion of the `int` value because the first argument on the left is a String. Order of precedence causes the multiplication of the two ints to occur first in the second example before the concatenation operator is evaluated.

I'd need to check the bytecode but I believe the compiler uses a `StringBuilder` here so the real code would look like:

```java
System.out.println(new StringBuilder("Result").append(2).append(3).append(4));
System.out.println(new StringBuilder("Result").append(2).append(3 * 5));
```

**Edit for completeness:**

Your examples, because they are made up entirely of literals (String and primitive), are dealt with at compile time and simply turned into a string literal. This is a [Compile Time Constant Expression](http://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.28). Precedence still applies so the result is the same.

If any of those were to have been a variable, for example:

```java
int x = 5;
System.out.println("Result"+2+x);
```

then a `StringBuilder` is used at runtime to concatenate the literal part with the variable's value.

(Edited to be authoritative after looking at the bytecode and JLS)

**Q: Ranges of data types?**
A: 

| Datatype | Bits Acquired In Memory |
|----------|-------------------------|
| boolean  | 1                       |
| byte     | 8 (1 byte)              |
| char     | 16 (2 bytes)            |
| short    | 16 (2 bytes)            |
| int      | 32 (4 bytes)            |
| long     | 64 (8 bytes)            |
| float    | 32 (4 bytes)            |
| double   | 64 (8 bytes)            |


**Q: int vs unsigned int?**
A: 
Java doesnt have unsigned int

**Q: Use of packages**
A:
There are various advantages of defining packages in Java.
- Packages avoid the name clashes.
- The Package provides easier access control.
- We can also have the hidden classes that are not visible outside and used by the package.
- It is easier to locate the related classes.
types:
1. in-built
2. user built
![[Pasted image 20230822010629.png]]


**Q: Static keyword**
A:
The keyword _static_ means that the particular member belongs to a type itself, rather than to an instance of that type.
Can be used with:
1. Variable
2. method
3. block
4. inner class

When a member(block, variable, method, nested class) of the class is declared as static, it can be accessed before the objects of its class are created, and without any object reference.

> _static_ fields and _static_ blocks are resolved and run in the same order as they are present in the class.

1.  **Static Block**
If you need to do the computation in order to initialize your **static variables**, you can declare a static block that gets executed exactly once, when the class is first loaded.
Key points:
1. A **class can have multiple _static_ blocks**.
2. **Static blocks of parent class execute first because the compiler loads parent class before child class.**

3. **Static Fields:**
when we declare a field _static_, exactly a single copy of that field is created and shared among all instances of that class.
Static variables are stored in the class area.
Inheritance in static fields: [Link](https://stackoverflow.com/questions/37226269/are-static-variables-inherited)

3. **Static Method:**
Use _static_ methods to perform an operation that's not dependent upon instance creation.
few reasons why we'd want to use _static_ methods:
- to access/manipulate static variables and other static methods that don't depend upon objects.
- _static_ methods are widely used in utility and helper classes.
- Static method overriding: It is because the static method is the part of the class, and it is bound with class whereas instance method is bound with the object, and static gets memory in class area, and instance gets memory in a heap.

Key Points:
1. _static_ methods in Java are resolved at compile time. Since method overriding is part of Runtime Polymorphism, **_static_ methods can't be overridden.**
	1. **Static methods in Java are inherited**, but can not be overridden. If you declare the same method in a subclass, you hide the superclass method instead of overriding it. Static methods are not polymorphic.
	2. so, `Child.parentStaticMethod()` would work.
	3. and since static methods are inherited, there are some things to keep in mind
		1. lets say there is method `static public int gata(){}`.
		2. we can define exactly same method `static public int gata(){}` in child class as well and the inherited one will be hidden
		3. but if we remove static and `public int gata(){}`, then it means we are trying to override the inherited method which is NOT allowed.
		4. although, we can keep or not keep the `static` and we can change the signature, that will make it overloading which is allowed.
			1. `public int gata(int x){}`.
			2. `static public int gata(int x){}`.
	4. So if we `Parent parent = new Child("6");`, if we call parent.staticFunction(), no matter if the object is child or parent, it will call parent's static function, cos it is resolved at compile time.
	5. 
3. **Abstract methods can't be static.**
4. **can't use _this_ or _super_ keywords.**
5. instance methods can access everyone.
6. static methods can only access static methods and variables.
	1. they need obj to access instance methods and variables.

7. Static class:
In general, the nested class architecture is divided into two types:
- nested classes that we declare _static_ are called **_static_ nested classes**
- nested classes that are non-_static_ are called **inner classes**
DIff:
Inner classes have access to all members of the enclosing class (including _private_ ones), whereas the _static_ nested classes only have access to static members of the outer class.

Key points:
1. static nested classes behave exactly like any other top-level class, but are enclosed in the only class that will access it, to provide better packaging convenience.**
2. **Java programming specification doesn't allow us to declare the top-level class as _static_**

**Q: Diff between static blocka and instance initializer***
A:
> Instance initializer: The Java compiler copies initializer blocks into every constructor. Therefore, multiple constructors can use this approach to share a block of code:

| Feature                                   | Static Block                            | Instance Initializer Block                |
|-------------------------------------------|----------------------------------------|-------------------------------------------|
| Execution                                 | During class loading                   | During class instantiation               |
| Variable Usage                            | Only static variables can be used      | Static or non-static variables can be used|
| Usage of "this"                           | Cannot use "this"                      | Can use "this"                            |
| Execution Frequency                       | Once during class loading              | Whenever the constructor is called        |


> For loop: for(int i=0; <> ; i++): the second part demands a boolean value, and 0 is not considered boolean false in java.


**Q:**  object-oriented programming language and object-based programming language?
A:  
- Object-oriented languages follow all the concepts of OOPs whereas, the object-based language doesn't follow all the concepts of OOPs like inheritance and polymorphism.
- Object-oriented languages do not have the inbuilt objects whereas Object-based languages have the inbuilt objects, for example, JavaScript has window object.
- Examples of object-oriented programming are Java, C#, Smalltalk, etc. whereas the examples of object-based languages are JavaScript, VBScript, etc.

Facts About constructor:
> - Constructor: It is invoked when the class is instantiated, and the memory is allocated for the object. Every time, an object is created using the **new** keyword, the default constructor of the class is called. The name of the constructor must be similar to the class name. The constructor must not have an explicit return type.
> 1. Default constructor
> 	1. A default constructor is invoked implicitly by the compiler if there is no constructor defined in the class.
> 2. Parameterized constructor
> - The constructor implicitly returns the current instance of the class (You can't use an explicit return type with the constructor)
> - The constructor is not inherited.
> - The constructor can't be final.
>3. Copy constructor:
>	1. Java doesnt have copy constructor like C++.
>	2. We can make a constructor in class that accepts an object of the same class and copy values.
>	3. We can manually copy values from one obj to another.
>	5. Protected constructor: [Link](https://stackoverflow.com/questions/5150748/protected-constructor-and-accessibility)
>4. Modifiers **public, protected and, private** are allowed with constructors.
>5. Rules for creating constructor
>	1. no return type
>	2. same name as class.

**Q: What is the default constructor?**
A:
- A default constructor is a constructor created by the compiler if we do not define any constructor(s) for a class.
- If we have defined a constructor it is not default.
- This is a constructor initializes the variables of the class with their respective default values (i.e. null for objects, 0.0 for float and double, false for boolean, 0 for byte, short, int and, long).
- Even no-arg empty constructor will work same but wont be considered default constructor. 


**Q: How object cloning works, deep and shallow cloning?** [Link](https://www.digitalocean.com/community/tutorials/java-clone-object-cloning-java) 
To understand deeply: [Link2](https://stackoverflow.com/questions/16507676/what-is-the-point-in-letting-my-class-implement-cloneable)
A: For using the clone() method by Object class, we need to implement the Cloneable interface in our class.
so, when we use it, a diff object, a copy of original one will be created, it will have diff hash, a completely diff object. But lets say if obj A had a hashmap, then B is a clone of A, `A==B` will eb false but `A.hashmap == B.hashmap` will be true, cos it is a shallow copy.
For Deep copy we need to implement out clone method in class A and define there, how exactly to copy stuff.

**Q: Calling constructors in java from methods** 
A: 
**No, you cannot call a constructor from a method**. The only place from which you can invoke constructors using “this()” or, “super()” is the first line of another constructor. If you try to invoke constructors explicitly elsewhere, a compile time error will be generated.

**Q: Static constructors**
A: Strictly speaking, Java does not have static constructors because a constructor, by definition, cannot _be_ static.


**Q: How exactly is memory allocation work**
A:
[Link1](https://www.guru99.com/java-stack-heap.html)
![[Pasted image 20230819231044.png]]


**Q: Why main method is necessary to call java program, what if we dont have one or if it is not static**
A: 
1. Entry point of program, this is where program starts.
2. If we dont have one, will compile fine but at runtime we get error.
3. Compile fine but at runtime we get error

**Q: What exactly is super**
A:
- It is a reference variable that is used to refer to the immediate parent class object 
- Whenever you create the instance of the subclass, an instance of the parent class is created implicitly which is referred by super reference variable.
- Every constructor be it the implicit default one or user defined, with parameters or without, it will call the `super()` i.e., call the parent's default constructor implicitly.
- BUT: if there is a parameterized constructor in parent and we call it explicitly (ofcourse) in child class's constructor using `super(x)` then the implicit call to the parent's default constructor `super()` will be overwritten.
- IF: the default constructor doesnt exists in parent, then:
	- Compilation error will be thrown.
	- we need to explicitly call the parameterized constructor in child's constructor using `super(x)`.
Uses:
- super can be used to refer to the immediate parent class instance variable.
- super can be used to invoke the immediate parent class method.
- super() can be used to invoke immediate parent class constructor.


**Q: What exactly is `this`.
A:
- The **this** keyword is a reference variable that refers to the current object. There are the various uses of this keyword in Java.
- The only place from which you can invoke constructors using “this()” or, “super()” is the FIRST LINE of another constructor. If you try to invoke constructors explicitly elsewhere, a compile time error will be generated.
	- this and super can be used anywhere in class methods, it is just that this() and super() should only be in the first line of constructor.
- we cant assign it any reference.
- this = null; cant be done.
- this can be used to call static methods/fields.
- this is final variable.
- this can be used in synchronized block.
- There are the following uses of **this** keyword.
	- **this** can be used to refer to the current class instance variable.
	- **this** can be used to invoke current class method (implicitly)
	- **this()** can be used to invoke the current class constructor.
	- **this** can be passed as an argument in the method call.
	- **this** can be passed as an argument in the constructor call.
	- **this** can be used to return the current class instance from the method.

**Q: Can we use both this() and super() in constructor together?**
A: 
No, they both need to be first statement in constructor.

**Q: Inheritance in Java**
A:
- Multiple inheritance: can be achieved through interface in Java.
	- will throw compile time error if we try to extend multiple classes.
- Hybrid inheritance can be achieved through combination of diff kinds of inheritance:
	- Single and Multiple Inheritance (not supported but can be achieved through interface)
	- Multilevel and Hierarchical Inheritance
	- Hierarchical and Single Inheritance
	- Multiple and Multilevel Inheritance
![[Pasted image 20230820213901.png]]![[Pasted image 20230820213925.png]]

**Q: Order of execution when we have parent child class relationship, static blocks, initializer, constructor class.**
A: 
1. Parent class static block: parent class is loaded first.
2. Child class static block.
3. parent class initializer
4. parent class constructor.
5. child class initializer
6. child class constructor.

**Q: Aggregation and composition**
A: 
Aggregation: has-a relationship, where the aggregate class contains another class.
Composition: a stronger has-a relationship, where object wont exist without container object.

**Q: Why pointers are not supported**
A: Because they are unsafe., point to memory.

**Q: what is method signature**
A: 
Method name and type of parameters in java (which also implies the order and number of arguments).
So, the modifiers, return type, parameter names, exception list, and body are not part of the signature

**Q: What exactly is overloading**
A:
Overloading: Create multiple methods with the same name but different signature.
which can be done using:
1. number of parameters.
2. type
3. order
> Changing return type wont make method change.
> Why just changing return type wont work?
> 1. Ambiguity.
> 2. it doesn't change signature.
> We CANNOT overload method just by making it static.
> We CAN overload the main method.
> After changing the signature correctly, we can change return type, access modifier. etc.

With Type promotion:
- byte can be promoted to short, int, long, float, double.
- char can be promoted to int, long, float, double.
- similarly for other...
- First byte will try to find short, if not short, then it will go it int, and so on.
![[Pasted image 20230820222432.png]]

**What is the output of the following Java program?**
A:
```java
1. class OverloadingCalculation3{    
2.   void sum(int a,long b){System.out.println("a method invoked");}    
3.   void sum(long a,int b){System.out.println("b method invoked");}    

5.   public static void main(String args[]){    
6.   OverloadingCalculation3 obj=new OverloadingCalculation3();    
7.   obj.sum(20,20);//now ambiguity    
8.   }    
9. }    

/**
Output
OverloadingCalculation3.java:7: error: reference to sum is ambiguous
obj.sum(20,20);//now ambiguity  
     ^ 
      both method sum(int,long) in OverloadingCalculation3 
      and method sum(long,int) in OverloadingCalculation3 match
1 error
*/
```
**Explanation**
- There are two methods defined with the same name, i.e., sum.
- This is correct overloading by definition.
- The parameter passed that are a = 20, b = 20. We can not tell that which method will be called as there is no clear differentiation mentioned between integer literal and long literal. This is the case of ambiguity. Therefore, the compiler will throw an error.

**Q: What exactly is overriding**
A:  Subclass provides a specific implementation of a method that is already provided by its parent class, it is known as Method Overriding
**Rules for Method overriding**
- The method must have the same name as in the parent class.
- The method must have the same signature as in the parent class.
- Two classes must have an IS-A relationship between them.
**Scope of overridden methods:** We can change scope as it is not part of signature.
- We can change the scope of the overridden method in the subclass.
- However we can only increase the accessibility, cant decrease it:
	- The protected can be changed to public or default.
	- The default can be changed to public.
	- The public will always remain public.
**Return type of overridden method**
- We can do it, however there are rules:
	- CANNOT change if parent method's return type is primitive or void.
	- the return type of child's overridden method must be same or subclass of return type of parent's method.
	- This is done to save liskov substitution principle.
	- **covariant return type is – when we override a method – what allows the return type to be the subtype of the type of the overridden method**.
**Can we modify the throws clause of the superclass method while overriding it in the subclass?**
- yes, we can do it as it is not the part of signature.
- Rules:
	- If parent method throws X exception, child's overridden method can only throw either same X or subclass of X, but never parent of X.
	- If parent/superclass doesnt throw any exception, the child method cannot throw checked(compile time exceptions), but CAN only throw unchecked(runtime) exceptions.

```java
1. class Base  
2. {  
3.     void method(int a)  
4.     {  
5.         System.out.println("Base class method called with integer a = "+a);  
6.     }  

8.     void method(double d)  
9.     {  
10.         System.out.println("Base class method called with double d ="+d);  
11.     }  
12. }  

14. class Derived extends Base  
15. {  
16.     @Override  
17.     void method(double d)  
18.     {  
19.         System.out.println("Derived class method called with double d ="+d);  
20.     }  
21. }  

23. public class Main  
24. {      
25.     public static void main(String[] args)  
26.     {  
27.         new Derived().method(10);  
28.     }  
29. }  

**Output**

Base class method called with integer a = 10
```
Explanation: 
- the derived class inherits all functions from parent class and overrides one.
- now when it gets the call for an 'int' parameter, it becomes a overloading problem.
- it first check for best match which is base class's method.
For variables: [Link](https://stackoverflow.com/questions/32422923/why-does-java-bind-variables-at-compile-time)
- Just like static methods, variables are resolved at compile time.
- so if we do `parent x = new child()`, then `x.fieldVariable` will return whatever there is in parent class rather than child class.
- if we call lets say getter function, then ofcourse it will be runtime resolution.

**Q: Final Keyword?**
A: 
Allows us to set limitations on extensibility.
Final Class:
- Cant be extended
- Doesn't mean its objects are immutable.
Final Methods:
- Cant be overridden.
Final Primitive Variables:
- Cant be reassigned, once they are initialized, cant be altered after that.
Final Reference Variable:
- We can’t reassign it. 
- But this doesn’t mean that the object it refers to is immutable.
- We can change the values of the object.
Final Fields:
- according to naming conventions, class constants should be uppercase, with components separated by underscore (“_”) characters:

```java
static final int MAX_WIDTH = 999;
```
- final field must be initialized before the constructor completes.
- For _static final_ fields, this means that we can initialize them:
	- upon declaration as shown in the above example
	- in the static initializer block
	- or we can initialize it once, but only once. (Check #HERE mark)
- For instance _final_ fields, this means that we can initialize them:
	- upon declaration
	- in the instance initializer block
	- in the constructor
	- or we can initialize it once, but only once. (Check #HERE mark)
- It CAN only be initialized once, so this is possible: #HERE
```java
public static void main(String[] dfgdf) {  
    final int x;  
    x = 6;  
}
// Output: works fine
```

Final Arguments:
- It cant be changed inside a method.
```java
public void methodWithFinalArguments(final int x) {
    x=1;
}
```
The above assignment causes the compiler error:

**Q: What is final blank variable**
A: A final variable, not initialized at the time of declaration, is known as the final blank variable.

**Q: Can constructors be final**
A: 
No, They are not inherited so.

**Q: Can we declare an interface as final?**
A:
No, we cannot declare an interface as final because the interface must be implemented by some class to provide its definition

**Q: Can we declare an abstract method as final?**
A:
No, Abstract methods cannot have body, hence needs to be overridden.

**Q: main method things**
A: 
- We CANNOT change anything about main method EXCEPT the name of argument.
- it has to exactly same as `public static void main(String[] args)`
- CANNOT change argument type.
- CANNOT even add more arguments.
- It CAN be declared as final: cos as it is static, it cant be overridden anyways.

**Q: Access modifiers on classes?**
A: 
classes can have two access modifiers: `public` and default (no modifier).
1. **public:** A class marked as `public` can be accessed from any other class or package. It has the widest scope of visibility and can be instantiated and used by code from any package.
2. **default (no modifier):** If a class does not have any explicit access modifier (default), it can only be accessed within the same package. This means that classes with default access can be used by other classes within the same package, but they are not accessible from classes in other packages.

Here's a summary of the access modifiers for classes:

|Access Modifier|Same Package|Other Packages|
|---|---|---|
|public|Yes|Yes|
|default|Yes|No|

Choosing the appropriate access modifier for your classes depends on whether you want them to be accessible outside the package or restricted to the same package.

>only public classes needs to be defined as same name a filename, it is not needed for default classes.
>there cant be two public class in one file, for obvious reasons.

**Q: Compile time and runtime polymorphism**
A:

| Aspect                  | Compile-Time Polymorphism          | Runtime Polymorphism              |
|-------------------------|-----------------------------------|-----------------------------------|
| Terminology          | Static binding, early binding, overloading | Dynamic binding, late binding, overriding, dynamic method dispatch |
| Execution Speed      | Fast execution, type determined at compile-time | Slower execution, type determined at run-time |
| Flexibility          | Less flexibility, resolved at compile-time | More flexibility, resolved at runtime |

**Q: Can you achieve Runtime Polymorphism by data members?**
A:
No, because method overriding is used to achieve runtime polymorphism and data members cannot be overridden. We can override the member functions but not the data members. Consider the example given below.

```java
1. class Bike{  
2.  int speedlimit=90;  
3. }  
4. class Honda3 extends Bike{  
5.  int speedlimit=150;  
6.  public static void main(String args[]){  
7.   Bike obj=new Honda3();  
8.   System.out.println(obj.speedlimit);//90  
9.    }

// Output: 90
```

**Q: What is Java instanceOf operator?**
A:
- The instanceof in Java is also known as type comparison operator because it compares the instance with type. 
- ``(object) instanceof (type)``
- It returns either true or false. 
- If we apply the instanceof operator with any variable that has a null value, it returns false. Consider the following example.
- if A extends B, then object of A is type of B as well. 
```java
1. class Simple1{  
2.  public static void main(String args[]){  
3.  Simple1 s=new Simple1();  
4.  System.out.println(s instanceof Simple1);//true  
5.  }  
6. }
```

**Q: encapsulation vs abstraction**
A:
Abstraction hides the implementation details whereas encapsulation wraps code and data into a single unit.
Abstraction: can be achieved by abstract class or interface.
Encapsulation:  can be achieved using class.

**Q: Abstract class**
A:
- we extend a abstract class.
- an abstract method needs abstract class.

**Q: Interface**
A: with java 9
- there can be private, default, static methods in interface. (but still final is not allowed here)
- they all must have a body in interface and no need to be implemented in child class.
- static:
	- We can call it as `I.x()`
- private:
	- can be used inside default and static methods.
- default: (normally no access modifier is default, but in interface it is public,)
	- if we had to add new methods to all classes implementing an interface.
	- we can use default methods in such cases.
- interface variables are public, _static_, and _final_ by definition, but we still cant use final keyword in interface.

**Q: Diff b/w abstract class and interface**
A:
Certainly, here's the table comparing abstract classes and interfaces in Java:

| Aspect               | Abstract Class                                       | Interface                                            |
|----------------------|------------------------------------------------------|------------------------------------------------------|
| Methods              | Can have abstract and non-abstract methods           | Can have only abstract methods (default/static too) |
| Multiple Inheritance | Does not support multiple inheritance               | Supports multiple inheritance                        |
| Variables            | Can have final, non-final, static, and non-static variables | Has only static and final variables                 |
| Implementation       | Can provide implementation for methods in interface | Cannot provide implementation for abstract class    |
| Keyword              | Declared using the "abstract" keyword                | Declared using the "interface" keyword              |
| Extending/Implementing | Can extend a class and implement interfaces       | Can extend only interfaces                         |
| Access Modifiers     | Can have private, protected, and other modifiers    | Members are public by default                       |
| Example              | `public abstract class Shape {`<br>`public abstract void draw();`<br>`}` | `public interface Drawable {`<br>`void draw();`<br>`}` |


**Q: How to make read only class?**
A:
1. make all instance variables private.
2. only keep getters.

**Q: How to make write only class?**
A:
1. make all instance variables private.
2. only keep settters.


**Q: What is a marker interface?**
A:
A Marker interface can be defined as the interface which has no data member and member functions. For example, Serializable, Cloneable are marker interfaces. The marker interface can be declared as follows.

```java
1. public interface Serializable{    
2. }
```
Uses:
- It **provides run-time type information about objects, so the compiler and JVM have additional information about the object**.
- It is also called tagging interface.

**Q: How can we access some class in another class in Java?**
A:
There are two ways to access a class in another class.

- **By using the fully qualified name:** To access a class in a different package, either we must use the fully qualified name of that class, or we must import the package containing that class.
- **By using the relative path**, We can use the path of the class that is related to the package that contains our class. It can be the same or subpackage.

> java.lang is implicitly loaded by JVM.

**Q: Can we import a class multiple time, if yes, will JVM load it multiple times?**
A:
We can import it multiple times, but classloader in JVM wont load it multiple times.

**Q: Static import**
A:
Allow to use any static member of a class directly. There is no need to qualify it by the class name.
```java
import static java.lang.Math.*;
class Test2 {
    public static void main(String[] args)
    {
        System.out.println(sqrt(4));
        System.out.println(pow(2, 2));
        System.out.println(abs(6.3));
    }
}
```