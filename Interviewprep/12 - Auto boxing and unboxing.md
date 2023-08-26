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



