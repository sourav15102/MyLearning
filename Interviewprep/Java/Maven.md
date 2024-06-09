**Transitive dependencies** are dependencies that are pulled into your project because they are required by one of your project's direct dependencies.

```xml
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>junit</groupId>
					<artifactId>junit</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
```

It's excluding the `junit` dependency from being pulled into your project as a transitive dependency. This means that when you use `spring-boot-starter-test`, it won't bring in `junit` as a dependency.


Spring boot starter parent:
_spring-boot-starter-parent_ project is a special starter project that provides default configurations for our application and a complete dependency tree to quickly build our _Spring Boot_ project. It also provides default configurations for Maven plugins, such as _maven-failsafe-plugin_, _maven-jar-plugin_, _maven-surefire-plugin_, and _maven-war-plugin_.
inherits dependency management from _spring-boot-dependencies,_ which is the parent to the s_pring-boot-starter-parent_.
To manage a different version of a dependency provided by the starter parent:

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
            <version>2.4.0</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

Q: can we change target/classes fodler
A: Sing outputDirectory
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <version>3.2.2</version> <!-- Adjust the version as needed -->
            <configuration>
                <outputDirectory>${project.build.directory}/custom-folder</outputDirectory>
            </configuration>
        </plugin>
    </plugins>
</build>

```

Relativepath:
if the parent POM is not located in the parent folder, we need to use the _relativePath_ tag to refer to the location.
[![module2](https://www.baeldung.com/wp-content/uploads/2021/09/module2.svg)](https://www.baeldung.com/wp-content/uploads/2021/09/module2.svg)

```xml
<artifactId>module2</artifactId>
<parent>
    <groupId>com.baeldung.maven-parent-pom-resolution</groupId>
    <artifactId>module1</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <relativePath>../module1/pom.xml</relativePath>
</parent>
```

The `<relativePath/>` element in the `<parent>` section of your POM file is empty. When `<relativePath/>` is left empty like this, it indicates that the parent POM (in this case, "spring-boot-starter-parent" with version "3.0.2") should be resolved from the remote Maven repository based on the coordinates specified in the `<groupId>`, `<artifactId>`, and `<version>` elements.


Q: properties file
A:
Most Java application need to use properties at some point, generally to store simple parameters as key-value pairs, outside of compiled code.
used for:
1. application name
2. port
3. db configurations
And so the language has first class support for properties – the _java.util.Properties_ – a utility class designed for handling this type of configuration files.

```java
String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
String appConfigPath = rootPath + "app.properties";

Properties appProps = new Properties();
appProps.load(new FileInputStream(appConfigPath));
  
String appVersion = appProps.getProperty("version");
appProps.setProperty("name", "NewAppName"); // update an old value
appProps.setProperty("downloadAddr", "www.baeldung.com/downloads"); // add new key-value

```

Spring Boot reads database properties from an `application.properties` or `application.yml` file in the classpath, matches property keys with predefined database-related settings, and auto-configures the database connection based on these properties. You can customize the configuration and use profiles for different environments.

```
spring.profiles.active=test
```
