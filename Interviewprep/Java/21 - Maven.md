- [[My learnings/Interviewprep/Maven]]

Maven is a project management tool. It is based on POM (Project Object Model).

How Maven works?
The configuration of a Maven project is done via a _Project Object Model (POM)_, represented by a _pom.xml_ file
basic structure of a typical _POM_ file:
```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.baeldung</groupId>
    <artifactId>baeldung</artifactId>
    <packaging>jar</packaging>
    <version>1.0-SNAPSHOT</version>
    <name>com.baeldung</name>
    <url>http://maven.apache.org</url>
    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.8.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
            //...
            </plugin>
        </plugins>
    </build>
</project>
```
##### Project Identifiers
Maven uses a set of identifiers, also called coordinates, to uniquely identify a project and specify how the project artifact should be packaged:

- _groupId_ – a unique base name of the company or group that created the project
- _artifactId_ – a unique name of the project
- _version_ – a version of the project
- _packaging_ – a packaging method (e.g. _WAR_/_JAR_/_ZIP_)

The first three of these (_groupId:artifactId:version_)(GAV) combine to form the unique identifier

Q:  What are the minimum required elements for POM? 
A:
The minimum required elements for POM are:
- project root
- modelVersion – should be 4.0.0
- groupId – project’s group id
- artifactId – artifact (project) id
- version – version of the artifact

The groupId is a parameter indicating the group or individual that created a project, which is often a reversed company domain name. The artifactId is the base package name used in the project
##### Dependencies
These external libraries that a project uses are called dependencies.
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>5.3.16</version>
</dependency>
```

##### Repositories
A repository in Maven is used to hold build artifacts and dependencies of varying types. The default local repository is located in the _.m2/repository_
If an artifact or a plugin is available in the local repository, Maven uses it. Otherwise, it is downloaded from a central repository and stored in the local repository. The default central repository is Maven Central.
Some libraries, such as the JBoss server, are not available at the central repository but are available at an alternate repository. For those libraries, you need to provide the URL to the alternate repository inside the _pom.xml_ file:
```xml
<repositories>
    <repository>
        <id>JBoss repository</id>
        <url>http://repository.jboss.org/nexus/content/groups/public/</url>
    </repository>
</repositories>
```
1. Local Repository
	1. Maven local repository is created by maven in your local system when you run any maven command. 
2. Central Repository
	1. Maven community creates maven central repository on the web.
3. Remote Repository
	1. Maven remote repository is located on the web by different vendors.

#### Properties
Maven properties are value-placeholders and are accessible anywhere within a _pom.xml_.

```xml
<properties>
    <spring.version>5.3.16</spring.version>
</properties>

<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>${spring.version}</version>
    </dependency>
```

##### Profiles:
A _profile_ is basically a set of configuration values. By using _profiles_, you can customize the build for different environments such as Production/Test/Development:
```java
<profiles>
    <profile>
        <id>production</id>
        <build>
            <plugins>
                <plugin>
                //...
                </plugin>
            </plugins>
        </build>
    </profile>
    <profile>
        <id>development</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <build>
            <plugins>
                <plugin>
                //...
                </plugin>
            </plugins>
        </build>
     </profile>
 </profiles>
```

```bash
mvn install -P<profile1>,<profile2>
mvn install -Pdev,test
```

#### Maven build and phases:
**A Maven phase represents a stage in the Maven build** lifecycle
- `validate` -This includes verifying that the project's POM (Project Object Model) is valid, that all required plugins and goals are defined, and that resources needed for the build are accessible
- `compile` - compile the source code of the project
- `test` - test the compiled source code using a suitable unit testing framework. These tests should not require the code be packaged or deployed
- `package` - take the compiled code and package it in its distributable format, such as a JAR.
- `verify` -integration-test phase in maven life-cycle is to test the packaged artifact,  run any checks on results of integration tests to ensure quality criteria are met,such as code coverage or performance thresholds, are met
- `install` - install the package into the local repository, for use as a dependency in other projects locally
- `deploy` - done in the build environment, copies the final package to the remote repository for sharing with other developers and projects.

There are two other Maven lifecycles of note beyond the _default_ list above. They are
- **clean**: cleans up artifacts created by prior builds
- **site**: generates site documentation for this project, t simply means that Maven can generate a website for your project and release it to the public based on the `pom.xml` 's configuration. Internally, it uses [Maven Site Plugin](https://maven.apache.org/plugins/maven-site-plugin/index.html) to do it.

> Maven executes every phase in the sequence up to and including the one defined.


**Each phase is a sequence of goals, and each goal is responsible for a specific task.**
Here are some of the phases and default goals bound to them:
- _compiler:compile_ – the _compile_ goal from the _compiler_ plugin is bound to the _compile_ phase
- _compiler:testCompile_ is bound to the _test-compile_ phase
- _surefire:test_ is bound to the _test_ phase
- _install:install_ is bound to the _install_ phase
- _jar:jar_ and _war:war_ is bound to the _package_ phase

#### Maven Plugin:
**A Maven plugin is a group of goals**; however, these goals aren’t necessarily all bound to the same phase.

#### Plugin vs dependencies:
Dependencies:
- can be third-party libraries, frameworks, or other projects that your project needs to compile and run successfully.
- Dependencies are used primarily during compilation and execution
- you declare a Spring dependency in your `pom.xml`. Maven will download the Spring JARs and make them available for your project to use.
Plugn:
- They provide additional functionality or tasks that go beyond what Maven's default build lifecycle provides. used to customize and extend the build process
- Plugins are declared in the `<build>` section of your project's `pom.xml` file.
- The `maven-compiler-plugin` is a common plugin used to specify the Java compiler version, source, and target compatibility for your project. Another example is the `maven-surefire-plugin`, used for running unit tests.
- Plugins are used to execute specific tasks at defined phases of the build lifecycle.


Q: What is the difference between Ant and Maven?

|Ant|Maven|
|---|---|
|It is **a toolbox**.|It is **a framework**.|
|It is **mainly a build tool**.|It is **mainly a project management tool**.|
|There is **no life cycle**.|There is a**life cycle**.|

Q: Define SNAPSHOT in terms of maven.
A: The snapshot indicates the current development copy.

Q: Archetype?
A: In the context of a Maven POM (Project Object Model), an "archetype" refers to a project template or a blueprint for creating new Maven projects. Archetypes provide a convenient way to bootstrap the creation of projects with predefined structures, configurations, and dependencies

Q: How does Maven Architecture work?
A:
Maven architecture works in three steps, which are as follows:

- The first step is to read the pom.xml file.
- Then, it downloads the dependencies defined in pom.xml into the local repository from the central repository.
- Lastly, it creates and generates a report according to the requirements, and executes life-cycles, phases, goals, plugins, etc.

> The full name of a project in Maven includes:
> <GroupId>: <artifactId>: <version>


Q: How does jar packaging work
A: 
**Compilation** First, Maven compiles your Java source code (found in the `src/main/java` directory by default) into bytecode class files.
**Resources** It also copies other resources (e.g., configuration files, properties files) from the `src/main/resources` directory to the target directory.
**JAR Packaging** During the "package" phase, Maven packages these compiled class files, along with resources and other files, into a JAR file.

Q: Scope:
1. compile: dependency present in all classpaths
2. runtime: present in runtime and test classpath
3. test: in just test classpath.