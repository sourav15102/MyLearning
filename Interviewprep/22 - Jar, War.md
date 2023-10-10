
- Jar vs war
JAR:
JAR – or Java Archive – is a package file format. JAR files have the _.jar_ extension and may contain **libraries, resources, and metadata files.**
Essentially, it’s a zipped file containing the compressed versions of _.class_ files and resources of compiled Java libraries and applications.
For example, here’s a simple JAR file structure:

```plaintext
META-INF/
    MANIFEST.MF
com/
    baeldung/
        MyApplication.class
```

The META-INF/MANIFEST.MF file may contain additional metadata about the files stored in the archive.

We can create a JAR file using the _jar_ command or with tools like Maven.
```shell
javac com/baeldung/jar/*.java
```
The _javac_ command creates _JarExample.class_ in the _com/baeldung/jar_ directory. We can now package that into a jar file.
_c_ option to indicate that we’re creating a file and the _f_ option to specify the file:
```shell
jar cf JarExample.jar com/baeldung/jar/*.class
```
we can specify our entry point, and the _jar_ command will add it to the generated manifest file.
Let’s run _jar_ with an entry point specified:
```shell
jar cfe JarExample.jar com.baeldung.jar.JarExample com/baeldung/jar/*.class
```
Maven:
```shell
mvn package
```
Spring Boot with Maven, we should first confirm that our packaging setting is set to _jar_ rather than _war_ in our _pom.xml_ file.
```xml
<modelVersion>4.0.0</modelVersion>
<artifactId>spring-boot</artifactId>
<packaging>jar</packaging>
<name>spring-boot</name>
```
Once we know that’s configured, we can run the _package_ goal:
```shell
mvn package
```

|Aspect|JAR File|WAR File|
|---|---|---|
|**File Extension**|.jar|.war|
|**Purpose**|Used for libraries, plugins, or applications|Specifically for web applications|
|**Structure**|No predefined structure, can have any desired structure|Predefined structure with WEB-INF and META-INF directories|
|**Execution**|Can be executed from the command line (if built as an executable JAR) or used as a library|Requires a server to execute as it's designed for web applications|

- fat jar
**Skinny** – Contains _only_ the bits you literally type into your code editor, and _nothing_ else.

**Thin** – Contains all of the above _plus_ the application’s direct dependencies of your application (db drivers, utility libraries, etc.).

**Hollow** – The inverse of thin. It contains only the bits needed to run your application but does _not_ contain the application itself. Basically a pre-packaged “application server” to which you can later deploy your application, in the same style as traditional Java EE application servers, but with important differences.

**Fat/Uber** – Contains the bit you literally write yourself _**plus**_ the direct dependencies of your application _PLUS_ the bits needed to run your application “on its own”.
```
maven-assembly-plugin
is used to make fat jar
```

- maven
Why maven and how would we operate without it?
**Tasks in a Software Build Process**:
1. **Downloading Dependencies**: Projects often rely on external libraries or dependencies. Build tools manage the download and management of these dependencies. They fetch the required libraries from repositories, so you don't have to manually download them.
2. **Setting Classpath**: The classpath is a list of directories or JAR files that the Java Virtual Machine (JVM) uses to find classes and resources. Build tools help manage the classpath, ensuring that your project can access the necessary classes during compilation and runtime.
3. **Compiling Source Code**: Compiling involves translating your source code (written in a high-level language like Java) into binary code (bytecode in the case of Java). Build tools handle the compilation process, ensuring that all source files are compiled correctly.
4. **Running Tests**: Testing is a crucial part of software development. Build tools can automate the execution of test suites, ensuring that your code behaves as expected and doesn't introduce regressions.
5. **Packaging Artifacts**: Once your code is compiled and tested, it needs to be packaged into deployable artifacts. For Java projects, this often means creating JAR (Java Archive) files or WAR (Web Application Archive) files.
6. **Deployment**: Deploying your artifacts to an application server or repository is essential for making your software available to users. Build tools can assist in deploying artifacts to the correct locations.

**Operating Without Maven (Example)**:

Let's consider an example where you're working on a simple Java project without using a build tool like Maven. Here's how you might perform the tasks mentioned above manually:

1. **Downloading Dependencies**: Instead of relying on a tool like Maven, you would need to find and download the necessary libraries or JAR files yourself. This can be time-consuming and error-prone.
2. **Setting Classpath**: Manually configuring the classpath can be error-prone. You would need to ensure that all required directories and JAR files are included in the classpath when compiling and running your code.
3. **Compiling Source Code**: You would use the Java compiler (`javac`) to compile your source code. You'd need to specify all source files and ensure that dependencies are correctly referenced.
4. **Running Tests**: Executing tests would involve running test classes using the `java` command or a testing framework like JUnit. You'd need to manage test execution and reporting yourself.
5. **Packaging Artifacts**: To create a JAR or WAR file, you would need to use the `jar` command or create the archive manually. This includes organizing your compiled classes and resources into the correct structure.
6. **Deployment**: Deploying your artifacts would involve copying them to the appropriate location on an application server or repository, which can be a manual and error-prone process.

- buildpath vs classpath
**The classpath is an environment variable used by the Java Virtual Machine (JVM) to locate and load classes when running a Java program**. and by compiler while compiling as well.
Running java file with a classpath;
```java
java -classpath /path/to/class/files MyProgram
```
```java
java -classpath /path/to/classes:/path/to/lib.jar MyProgram
```
> if `CLASSPATH` is not explicitly set, it is defaulted to the current directory. However, if `CLASSPATH` is explicitly set, it does not include the current directory unless the current directory is included.

Build path:
**The build path is a list of all the resources that are required to build a Java project, including source files, class files, libraries, and other dependencies**. The Java development environment such as Eclipse, IntelliJ IDEA, or NetBeans uses the build path to compile and build the Java project.
- **Managing Dependencies:**
    - In a Java project, you often use external libraries, frameworks, or modules. These dependencies need to be included in your project's classpath to compile and run your code.
    - The build path in an IDE allows you to specify which external JAR files, libraries, and projects your project depends on. This is done through the IDE's user interface.
- **Configuring the Classpath:**
    - The IDE uses the information in the build path to configure the classpath for your project.
    - This means that when you compile and run your code within the IDE, it automatically includes the required dependencies specified in the build path in the classpath.
Finally, let’s explore the main difference between Classpath and Build path. **While the JVM uses the classpath at runtime to find class files and libraries, IDEs use the build path during the build process to find dependencies**.
Here’s a quick comparison between JVM usage of the Class path and IDEs’ usage of the Build path:

|   |   |   |
|---|---|---|
||**Classpath**|**Build Path**|
|**Usage**|JVM at runtime to find files|IDEs during the build process|
|**Set up**|Command line/environment|In the IDE|
|**Purpose**|Find class files and libraries|Find dependencies|


Q: Manifest.mf
A:
Java Archive (JAR) is described by its manifest file.
".MF" extension stands for "Manifest File."
The manifest file is named _MANIFEST.MF_ and is located under the _META-INF_ directory in the JAR. It’s simply **a list of key and value pairs, called _headers_ or _attributes_, grouped into sections.**
```
Manifest-Version: 1.0
Main-Class: com.example.MySpringBootApplication
Spring-Boot-Version: 2.5.4
Class-Path: lib/some-library.jar
Start-Class: com.example.MySpringBootApplication
Spring-Boot-Classes: BOOT-INF/classes/
Spring-Boot-Lib: BOOT-INF/lib/
Created-By: Maven
Build-Jdk: 11.0.12
```
