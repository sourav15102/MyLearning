```java
StringBuilder sb = new StringBuilder();
sb.append("Hello");
sb.append(" ");
sb.append("world");
```

```java
StringBuilder sb = new StringBuilder("Hello");
sb.append(" world");
String str = sb.toString();
```

Reverse:
```java
StringBuilder sb = new StringBuilder();
sb.append("Hello");
String gh = sb.reverse().toString();
```

Delete:
```java
StringBuilder sb = new StringBuilder();
sb.append("Hello");
sb.delete(0,3); // "lo" //it doesnt count last element [0,3)
```
