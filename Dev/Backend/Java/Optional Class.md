https://medium.com/javarevisited/optional-class-in-java-8-making-your-code-more-clear-and-concise-62af0712910d'
An Optional is a wrapper that can hold either a value or nothing (an empty Optional). It implements the Null Object pattern, aiming to minimize the need for null checks in code.

Example (Problem with null):

```java
public class Main {  
    public static void main(String[] args) {  
  
        Student Student = getStudentWithName("hamza");  
        if(student != null){  
             System.out.println(Student.getName());  
        }else {  
          System.out.println("no Student with the given name ");  
        }  
  
    }  
  
   public  static Student  getStudentWithName(String name ){  
        // lets suppose that our database contain only 2 students ahmed and hamza .  
        if (name.equals("hamza") || name.equals("ahmed")) {  
            return new Student(name , 22 , "Morocco");  
        } else {  
            return null ;  
        }  
    }  
  
}
```

Methods:
1. ofNullable()
```java
Optional<T> optional = Optional.ofNullable(T)
```
On this we have `.isPresent()` and `.get()` function to check if object is present and get the object.
2. of()
```java
Optional<Student> student = Optional.of(null); // it will throw null pointer exception.
```

Even after using these methods we will need to use if-else blocks in the code.

3. orElse()
Returns the value wrapped in the Optional, if it is present, or a default value if it is not. It takes a default value as an argument and returns it if the Optional is empty.

```java
public class Main {  
    public static void main(String[] args) {  
        Student  student = Optional.ofNullable(getStudentWithName("hamza")).orElse(new Student("no one", 0, "Unknown"));  
            System.out.println(student.getName());  
    }  
  
    public  static Student  getStudentWithName(String name ){  
        // lets suppose that our database contain only 2 students ahmed and hamza .  
        if (name.equals("hamza") || name.equals("ahmed")) {  
            return new Student(name , 22 , "Morocco");  
        } else {  
            return null ;  
        }  
    }  
  
}
```

4. orElseThrow()
`orElseThrow` is a method in the Java Optional class that is used to throw an exception if the Optional is empty. Unlike `orElse`, which returns a default value, `orElseThrow` allows you to throw a custom exception.

```java
public class Main {  
    public static void main(String[] args) throws StudentNotFoundException {  
        Student  student = Optional.ofNullable(getStudentWithName("fs")).orElseThrow(()-> new StudentNotFoundException("the Student is not Present "));  
            System.out.println(student.getName());  
    }  
  
    public  static Student  getStudentWithName(String name ){  
        // lets suppose that our database contain only 2 students ahmed and hamza .  
        if (name.equals("hamza") || name.equals("ahmed")) {  
            return new Student(name , 22 , "Morocco");  
        } else {  
            return null ;  
        }  
    }  
  
}
```
