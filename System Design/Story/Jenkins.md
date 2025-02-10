
Prerequisite: [[CICD]]

Here’s how you can explain the setup and workflow:
1. **Pipeline Overview**:
    - Used a **declarative pipeline** (`Jenkinsfile`) with stages for:
        - Code Checkout
        - Build
        - Test
        - Deploy
2. **Steps**:
    - **Code Checkout**:
        - Configured the pipeline to fetch code from GitHub using a webhook for changes.
    - **Build**:
        - Built the Java backend using Gradle or Maven.
        - Example:
```
	stage('Build') {
    steps {
        sh './mvnw clean install'
    }
}       
```
- **Testing**:
        - Integrated **JUnit** for backend tests and **Playwright** for frontend tests.
        - Generated reports for test results.
    - **Code Quality**:
        - Integrated **SonarQube** for analyzing code smells and bugs.
    - **Deployment**:
        - Used Docker to containerize the application.
        - Pushed Docker images to a registry and deployed to AWS ECS or EC2.
- **Notifications**:
    
    - Configured Slack or email notifications for build success/failure.
- **Example Jenkinsfile**:
```
pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh './mvnw clean install'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Code Quality') {
            steps {
                sh 'sonar-scanner'
            }
        }
        stage('Deploy') {
            steps {
                sh './deploy.sh'
            }
        }
    }
}

```