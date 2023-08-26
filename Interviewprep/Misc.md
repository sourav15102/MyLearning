
**Q: Difference between package, class and project?**
A:
A **project** is an IDE-level grouping; its a set of source files, configurations, assets, ect. that make up a working application.
A **package**, on the other hand, is a grouping of related classes/source files, it provides Namespace. It is a way of organizing your code, and also works as the addressing scheme used by Java to find code you are importing.
A **Library**, set of packages, provides a functionality.
A **Class**: blueprint of an object

**Q: Compilation vs execution**
A:
**Compilation is the process of converting the Java source code into an executable form, known as bytecode.** **Interpretation is the process of executing the Java bytecode directly by the JVM**


**Q: what is javap command?**
A:
The `javap` command disassembles one or more class files. The output depends on the options used. When no options are used, the `javap` command prints the protected and public fields, and methods of the classes passed to it.
The **_javap_** command displays information about the fields, constructors, and methods of a class.
It is used after compiling the java file.
it runs with `javap ABC.class` NOT `javap ABC.java`.

**Q: == vs .equals**
A:
Generally:
1. `equals(...)` method compares exactly what it's coded to compare, no more or less.
2. If a class doesn't override `equals`, it uses the closest parent class's `equals(Object o)` method that's overridden.
3. If no parent class overrides it, it defaults to `Object#equals(Object o)`, which checks if references are the same `==`.
4. Always override `hashCode` if you override `equals` to maintain the contract: `hashCode` must be same for equivalent objects.
5. `hashCode` ensures consistency between object equality and hash-based data structures.
6. HOWEVER, with Integer, Double, they will act like int , double only.
	1. because:
	2. `Integer x = 1`, looks like `Integer x = Integer.valueOf(1)` inside.
	3. and `a==b` looks like `a.intValue() == b` inside.
7. Above will work for Integer, Long, Boolean etc, but not for Double, cos Double doesnt use pooling.
String perspective:
- String has implemented equals as they will check for values of string.
- `==` will check for object reference as usual.

BUT:
- if we are talking about primitive data types like int, then it will check for values directly.

