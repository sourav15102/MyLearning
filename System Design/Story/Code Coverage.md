
### **2. Code Coverage with Jacoco**

#### **How Jacoco Works**

- **Jacoco** is a Java library for measuring code coverage.
- It integrates with build tools like Maven or Gradle and generates reports showing:
    - Which lines of code were executed during tests.
    - Which branches and methods were covered.

---
#### **Code Example: Jacoco with Maven**
1. **Add Jacoco Plugin to `pom.xml`**:
    
    ```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.8</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report</id>
                        <phase>test</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
    ```
    
2. **Run Tests with Coverage**:
    - Execute tests with Maven:
        
        ```bash
        mvn clean test
        ```
        
    - Jacoco will generate a coverage report in `target/site/jacoco/index.html`.
3. **Configure Minimum Coverage Thresholds**:
    - To enforce minimum coverage:
        
        ```xml
        <configuration>
            <rules>
                <rule>
                    <element>CLASS</element>
                    <limits>
                        <limit>
                            <counter>LINE</counter>
                            <value>COVEREDRATIO</value>
                            <minimum>0.80</minimum>
                        </limit>
                    </limits>
                </rule>
            </rules>
        </configuration>
        ```
        

---

#### **How to Use Jacoco in CI/CD**

- Integrate Jacoco with Jenkins  to fail builds if coverage thresholds are not met.
- Include the coverage report as an artifact in your CI pipeline for visibility.

---
#### **How to Explain**
1. **High-Level Flow**:
    - Jacoco instruments the code during tests.
    - It tracks which methods, branches, and lines are executed.
    - After tests, it generates a report showing coverage details.
2. **Why Use Jacoco?**
    - Ensures code quality by verifying that all critical paths are tested.
    - Helps catch untested edge cases early in development.


