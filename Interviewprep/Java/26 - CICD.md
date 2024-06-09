#### CI:
,  push code to a branch attachd woth piplin, tst are run, code is built, 
**Continuous integration** (CI) methdology where we follow trunk based model, executes the sequence of steps required to build and test the project. CI runs automatically on every change committed to a shared repository, offering developers quick feedback about the project’s state.

[Continuous delivery](https://semaphoreci.com/blog/2017/07/27/what-is-the-difference-between-continuous-integration-continuous-deployment-and-continuous-delivery.html) is an extension of CI. Its goal is to automate every step required to package and release a piece of software. The output of a continuous delivery pipeline takes the form of a deployable binary, package, or container.

**Continuous deployment** is an optional step-up from continuous delivery. It is a process that takes the output from the delivery pipeline and deploys it to the production system in a safe and automated way.


Canary vs Blue green:
Canary Deployment:
**Pros:**
1. **Gradual Rollout:** Canary deployments allow for a controlled and gradual rollout of new features or updates to a small subset of users. This helps in early detection of issues.
2. **Risk Mitigation:** By exposing only a fraction of users or traffic to the new version, the impact of potential problems is limited, reducing overall risk.
  
**Cons:**
1. **Complexity:** Managing multiple versions and routing traffic based on conditions can be complex to set up and maintain.
2. **Limited Testing:** Canary deployments may not catch all issues, as they involve a smaller sample size. Some problems might only surface when the new version is deployed to the entire user base.

Blue-Green Deployment:
**Pros:**

1. **Zero Downtime:** Blue-green deployments are designed to minimize downtime during updates. Users can seamlessly switch from the old version (blue) to the new version (green).
    
2. **Quick Rollbacks:** If issues are detected in the new version, rollback to the previous version (blue) is swift and straightforward.
**Cons:**

1. **Resource Intensive:** Maintaining two separate environments (blue and green) requires additional resources and infrastructure.
    
2. **Risk of Data Inconsistency:** If the application involves significant database schema changes, managing data consistency between blue and green environments can be challenging.

Testing:
1. Unit
2. integration
3. system: typically has a broader scope than end-to-end testing, as it covers all aspects of the system, including its functionality, performance, security, and other features.
4. Acceptance: if the features adhere to the doc mentioning user requiremnt.
5. end to end: emulating user through UI
6. Smoke: fast testing, features work as expected and that there are no showstopper issues

TDD:
The TDD cycle consists of three steps:
1. **Red**: write a test that fails.
2. **Green**: write the minimal code that passes the test.
3. **Refactor**: improve the code, and make it more abstract, readable, and optimized.
While writing a BDD test, developers and testers are not interested in the technical details (how a feature works), rather in behavior (what the feature does). BDD tests are used to test and discover the features that bring the most value to users.


