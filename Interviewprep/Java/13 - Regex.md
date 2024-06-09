**Q: What are meta characters**
A:
Metacharacters have the special meaning to the regular expression engine. The metacharacters are ^, $, ., *, +, etc. The regular expression engine does not consider them as the regular characters. 
 
|Regular Expression|Description|
|---|---|
|`\d`|Any digit, short for `[0-9]`|
|`\D`|A non-digit, short for `[^0-9]`|
|`\s`|A whitespace character, short for `[ \t\n\x0b\r\f]`|
|`\S`|A non-whitespace character, short for|
|`\w`|A word character, short for `[a-zA-Z_0-9]`|
|`\W`|A non-word character `[^\w]`|
|`\S+`|Several non-whitespace characters|
|`\b`|Matches a word boundary where a word character is `[a-zA-Z0-9_]`|


To enable the regular expression engine treating the metacharacters as ordinary characters, we need to escape the metacharacters with the backslash.
import java.util.regex.*;

```java
public class MetacharactersExample {
    public static void main(String[] args) {
        String text = "The price of the product is $100.50.";

        // Using backslash to escape the metacharacter '$'
        Pattern pattern = Pattern.compile("\\$\\d+\\.\\d+");
        Matcher matcher = pattern.matcher(text);

        // Finding and printing matched price using Matcher
        if (matcher.find()) {
            System.out.println("Matched price: " + matcher.group());
        }
    }
}
```

**Q: Why are we using double slashes `(\\)` in above pattern?**
A:
two fold reason:
1. We need to use `\d` and `\d+` in above statements
2. In java `\` itself has a special meaning, it conveys that whatever follows `\` has a special meaning
	1. `\\` represents a single backslash (`\`) in a string.
	2. `\n` represents a newline character.
	3. `\t` represents a tab character.
	4. `\"` represents a double quotation mark (`"`).

Q: What are quantifiers**
A:
A quantifier defines how often an element can occur. The symbols ?, *, + and {} are qualifiers.
  
|Regular Expression|Description|Examples|
|---|---|---|
|`*`|Occurs zero or more times, is short for `{0,}`|`X*` finds no or several letter X, <sbr /> `.*` finds any character sequence|
|`+`|Occurs one or more times, is short for `{1,}`|`X+`- Finds one or several letter X|
|`?`|Occurs no or one times, `?` is short for `{0,1}`.|`X?` finds no or exactly one letter X|
|`{X}`|Occurs X number of times, `{}` describes the order of the preceding liberal|`\d{3}` searches for three digits, `.{10}` for any character sequence of length 10.|
|`{X,Y}`|Occurs between X and Y times,|`\d{1,4}` means `\d` must occur at least once and at a maximum of four.|
|`*?`|`?` after a quantifier makes it a _reluctant quantifier_. It tries to find the smallest match. This makes the regular expression stop at the first match.||

**Q: Classes of regex**
A:
**Pattern:**
- The `Pattern` class represents a compiled regular expression pattern. It's created using a regular expression string and can be reused for multiple matching operations.
- You can create a `Pattern` object using its `compile` method.
- Example:
```java
`Pattern pattern = Pattern.compile("[0-9]+"); // Compiles a pattern to match one or more digits`
```

- **Matcher:**
    - The `Matcher` class is used to match a `Pattern` against an input string. It performs actual matching and provides methods to access matched substrings and to perform operations based on matches.
    - You obtain a `Matcher` instance by calling the `matcher` method on a `Pattern` object.
    - Example:
```java
Matcher matcher = pattern.matcher("12345"); // Creates a matcher for the input string boolean matches = matcher.matches(); // Checks if the entire string matches the patter
```

- **PatternSyntaxException:**
    - The `PatternSyntaxException` class is an exception that's thrown if there's a syntax error in the regular expression pattern.
    - It provides information about the location of the error in the pattern string.
    - Example:
```java
try {     
Pattern.compile("["); // This will throw PatternSyntaxException due to unclosed character  
} 
catch (PatternSyntaxException e) {     
System.out.println("Syntax error: " + e.getMessage()); 
}
```

- **find():**

- The `find()` method of the `Matcher` class is used to search for the next occurrence of the regular expression pattern in the input string.
- It returns a boolean value: `true` if a match is found, and `false` if no further matches are found.
- Each time you call `find()`, it searches for the next match in the input string.
- Example:
```java
`Matcher matcher = pattern.matcher("123 abc 456"); while (matcher.find()) {      }`
```
        
- **group():**
    - The `group()` method of the `Matcher` class is used to retrieve the actual substring that matches the pattern during the most recent call to `find()`.
    - You can also provide an argument to `group()` to specify which capturing group to retrieve (capturing groups are defined using parentheses in the regular expression).
    - Group index 0 (default) refers to the entire matched substring.     
    - Example:
```java
Matcher matcher = pattern.matcher("123 abc 456");
while (matcher.find()) {
    String matchedText = matcher.group(); // Retrieves the matched substring
    System.out.println("Matched: " + matchedText);
```


> lets the pattern is `(###) (###) `# is gibberish
> then for each occurance there are two groups, som for each occuerance, we can have matcher..group(1), (2), sometihng like that and (0) will give entire string.

```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        String input = "(123) (456) 7890 (789) (012) 3456";
        String patternString = "\\((\\d{3})\\) \\((\\d{3})\\) (\\d{4})";
        
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input);
        
        while (matcher.find()) {
            String fullMatch = matcher.group(0);
            String areaCode = matcher.group(1);
            String secondNumbers = matcher.group(2);
            String lastNumbers = matcher.group(3);
            
            System.out.println("Full Match: " + fullMatch);
            System.out.println("Area Code: " + areaCode);
            System.out.println("Second Numbers: " + secondNumbers);
            System.out.println("Last Numbers: " + lastNumbers);
            System.out.println();
        }
    }
}
```

```java
Full Match: (123) (456) 7890
Area Code: 123
Second Numbers: 456
Last Numbers: 7890

Full Match: (789) (012) 3456
Area Code: 789
Second Numbers: 012
Last Numbers: 3456
```

