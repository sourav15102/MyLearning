

|Nested Class Type|Access Modifiers|Association|Access to Enclosing Class Members|Members Defined|
|---|---|---|---|---|
|Static Nested Classes|All|Class-level|Only static members|Static & Non-static|
|Non-Static (Inner) Classes|All|Instance-level|All members|Non-static|
|Local Classes|None|Context-level|Both static & non-static|Instance|
|Anonymous Classes|None|Context-level|Both static & non-static|Instance, no constructors|

> Anonymous class: 
> - only one without constructors
> - cannot be static: also true for local class.
> Practically found: everyone can define static members.
> Although top level classes can only have public and default but inner classes can have all.


Static nested class:
```java
public class Enclosing {
    
    private static int x = 1;
    
    public static class StaticNested {

        private void run() {
            // method implementation
        }
    }
    
    @Test
    public void test() {
        Enclosing.StaticNested nested = new Enclosing.StaticNested();
        nested.run();
    }
}
```

Non-static nested class;
```java
public class Outer {
    
    public class Inner {
        // ...
    }
}


Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();
```

Local class:
```java
public class NewEnclosing {
    
    void run() {
        class Local {

            void run() {
                // method implementation
            }
        }
        Local local = new Local();
        local.run();
    }
    
    @Test
    public void test() {
        NewEnclosing newEnclosing = new NewEnclosing();
        newEnclosing.run();
    }
}
```

Anonymous class:
```java
abstract class SimpleAbstractClass {
    abstract void run();
}

public class AnonymousInnerUnitTest {
    
    @Test
    public void whenRunAnonymousClass_thenCorrect() {
        SimpleAbstractClass simpleAbstractClass = new SimpleAbstractClass() {
            void run() {
                // method implementation
            }
        };
        simpleAbstractClass.run();
    }
}

```
**Q: What are the advantages of Java inner classes?**
A:
There are two types of advantages of Java inner classes.
- Nested classes represent a special type of relationship that is it can access all the members (data members and methods) of the outer class including private.
- Nested classes are used to develop a more readable and maintainable code because it logically groups classes and interfaces in one place only.
- **Code Optimization:** It requires less code to write.

**Q: What are the disadvantages of using inner classes?**
A:
There are the following main disadvantages of using inner classes.

- Inner classes increase the total number of classes used by the developer and therefore increases the workload of JVM since it has to perform some routine operations for those extra classes which result in slower performance.
- IDEs provide less support to the inner classes as compare to the top level classes and therefore it annoys the developers while working with inner classes.

**Q: Can we access the non-final local variable, inside the local inner class?**
A:
till Java 7: no
After that: yes


**Q: How many class files are created on compiling the OuterClass in the following program?**
```java

1. public class Person {  
2. String name, age, address;  
3. class Employee{  
4.   float salary=10000;  
5. }  
6. class BusinessMen{  
7.   final String gstin="£4433drt3$";   
8. }  
9. public static void main (String args[])  
10. {  
11.   Person p = new Person();  
12. }  
13. }  
```
A:
3 class-files will be created named as Person.class, Person$BusinessMen.class, and Person$Employee.class.


**Q: What is shadowing?**
A:
**The declaration of the members of an inner class shadow those of the enclosing class** if they have the same name.

In this case, the _this_ keyword refers to the instances of the nested class and the members of the outer class can be referred to using the name of the outer class.

```java
public class NewOuter {

    int a = 1;
    static int b = 2;

    public class InnerClass {
        int a = 3;
        static final int b = 4;

        public void run() {
            System.out.println("a = " + a); //3
            System.out.println("b = " + b); //4
            System.out.println("NewOuterTest.this.a = " + NewOuter.this.a); //1
            System.out.println("NewOuterTest.b = " + NewOuter.b); //2
            System.out.println("NewOuterTest.this.b = " + NewOuter.this.b); //2
        }
    }

    @Test
    public void test() {
        NewOuter outer = new NewOuter();
        NewOuter.InnerClass inner = outer.new InnerClass();
        inner.run();

    }
}
`
```

Q: What is nested interface?
- Nested interfaces are `static` by default, irrespective of you declare it `static` or not.
- Nested interfaces are accessed using outer interface or class name.
- Nested interfaces declared inside a class can have any access modifier, while nested interfaces declared inside another interfaces are `public` by default.
- Classes implementing inner interface are required to implement only inner interface methods, not outer interface methods.
- Classes implementing outer interface are required to implement only outer interface methods, not inner interface methods.

>Both class can have interface and vica-versa

