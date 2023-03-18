

Questions:
- Q) mvn package vs mvn install:
- A) The main difference between them is the final destination of the build artifacts:
   `mvn package`: This command packages the project into a JAR, WAR, or other format, and places the resulting artifact in the `target` directory of the project. The resulting artifact is not installed in the local Maven repository, so it cannot be used as a dependency for other projects.
   `mvn install`: This command packages the project into a JAR, WAR, or other format, and installs the resulting artifact in the local Maven repository.
- Q) mvn local repo or .m2 folder?
- A) all the dependencies we add in pom.xml are present in .m2 folder