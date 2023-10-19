
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