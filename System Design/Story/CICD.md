### **1. Workflow: From Feature Branch to Deployment**

#### **Step 1: Code Push and Commit**

- **Developer Action:** Push changes to a **feature branch** in the source control system (e.g., GitHub, GitLab, or Bitbucket).
- **Trigger:** Pushing to the repository triggers the CI pipeline via a webhook configured in the version control system.

#### **Step 2: CI Pipeline – Build Stage**

- **What Happens:**
    - Code is pulled from the feature branch.
    - Dependencies are installed (e.g., `npm install`, `pip install`, or Maven commands).
    - The application is compiled (if needed).
    - Static code analysis tools like **SonarQube** or **CodeQL** are run to detect **code smells**, security vulnerabilities, or style violations.
- **Artifacts Created:** Build artifacts (e.g., JARs, Docker images) are generated and stored in an **artifact repository** like Artifactory or Nexus.

#### **Step 3: CI Pipeline – Test Stage**

- **What Happens:**
    - Unit tests are executed using frameworks like **JUnit**, **pytest**, **Mocha**, etc.
    - Code coverage tools (e.g., **JaCoCo**, **Coverage.py**) measure how much of the codebase was exercised.
    - Results are logged and reported in the CI system (e.g., Jenkins, GitHub Actions, or CircleCI).
    - Any failure here blocks further stages.

#### **Step 4: CD Pipeline – Deploy to Staging**

- **What Happens:**
    - Built artifacts (e.g., Docker containers or executables) are deployed to a **staging environment** using tools like **Terraform**, **Kubernetes**, or **AWS CodeDeploy**.
    - Integration tests and end-to-end (E2E) tests run here using tools like **Cypress**, **Playwright**, or **Selenium**.
    - If the tests fail, deployment to higher environments is blocked.

#### **Step 5: Approval and Deployment to Production**

- **Manual or Automated Approval:** If the pipeline is configured for **continuous delivery**, approval from a team lead or manager may be required to deploy to production.
- **Production Deployment:**
    - For microservices: Rolling deployments are used (e.g., **Kubernetes** with Helm charts).
    - For monoliths: Blue-Green or Canary deployments ensure minimal downtime.
    - Monitoring systems (e.g., **Datadog**, **New Relic**, **AWS CloudWatch**) verify the system’s health post-deployment.

---

### **2. Debugging Logs and CI/CD Failures**

#### **Check CI Pipeline Logs**

- Open your CI/CD tool (e.g., Jenkins, GitHub Actions, CircleCI, or GitLab CI/CD).
- Navigate to the **failed job** or stage (Build, Test, Deploy).
- Look at the logs specific to:
    - **Dependency installation:** Errors like "package not found" or version mismatches.
    - **Build process:** Compilation or bundling errors.
    - **Tests:** Failed assertions, coverage thresholds not met.

#### **Check Deployment Logs**

- Access the deployment logs in tools like **Kubernetes**, **AWS CodeDeploy**, or **Terraform Cloud**.
- For Kubernetes:

### **Scenario: Kubernetes on EC2**

When using EC2 instances as the underlying infrastructure for Kubernetes, the instances become **nodes** in your Kubernetes cluster. The deployment flow would look like this:

---

#### **1. Provisioning EC2 Instances**

- **Using Terraform:**
    - Terraform provisions a set of EC2 instances that will serve as the worker nodes and control plane (if you're not using a managed Kubernetes service like EKS).
    - Example Terraform script:

### **Why Use Different Strategies for Microservices vs. Monoliths?**

#### **For Microservices: Rolling Deployment**

- **Why It Works:**
    - Microservices are independent and loosely coupled. Updating one service does not affect others.
    - Rolling deployment limits the impact of issues to only the new pods being deployed.
- **When Issues Occur:**
    - Rolling back involves replacing the new pods with old ones, which is simple and quick.

#### **For Monoliths: Blue-Green or Canary Deployment**

- **Why Not Rolling Deployment?**
    - Monoliths are tightly coupled. Updating only part of the system could result in incompatibility or inconsistent states.
- **Blue-Green Deployment:**
    - A completely separate environment (`Green`) is created with the new version.
    - The entire traffic is switched from `Blue` (old version) to `Green` (new version) at once.
    - If issues occur, traffic can be instantly switched back to `Blue`, avoiding downtime.
- **Canary Deployment:**
    - Only a small percentage (e.g., 10%) of traffic is routed to the new version initially.
    - Gradual traffic increases allow monitoring for issues before routing 100% of traffic.