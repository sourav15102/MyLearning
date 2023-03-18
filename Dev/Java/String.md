
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

