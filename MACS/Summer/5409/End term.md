### Security:

What do we need to protect:
1. resource.
2. data: threats during processing, rest, transmit.

Measure of security:
Confidentiality: Data in storage/transit is read/accessed by authorized parties.
Integrity:           Not altered by unauthorized party.
Authenticity:     Provided by authorized source.
Availability:       Accessible and usable during specific period of time.

Measure of lack of security:
Vulnerability:    weakness that can be exploited.
Causes:
		- configuration deficiencies
		- security policy weaknesses
		- user errors
		- hardware or firmware flaws
		- software bugs
		- poor security architecture.
Threat:  a potential security voilation/action that can cause harm.
Risk:      A possibility of loss and harm caused by some activity.
How to determine:
	- probabiolity of threat and exoising vulnerabilities.
	- expectation of loss, resource compriomised

Improve security:
Security controls: countermeasures taken against threats.
Security mechanism: countermeasures are described in terms of this, components of defense framework.
Security policies: rules of security and regulations


Threat agents:
1. person/software, internal or exteranl carrying out threat.
Types:
1. Anynoymous attacker:
	1. non trusted service consumer without permissions.
2. Malicious agent:
	1. can intercept or forward networm traffic.
3. Trusted attacker:
	1. uses leigitimate credentials to target cloud providers and other tenants of that provider with whom tey share resources.
4. Malicion insider:
	1. Act as cloud provider, current former employees.

Cloud specific attacks:
1. Traffic eavesdropping:
- data transferred to or within cloud, intercepted.
- passive in nature.

2. Malicious intermeidiary:
threats are intercepted and altrer by macilcious sercvice.

3. DDOS: overwhelming artifical requestes

4. Insufficient authrotization:
erronenous or too broad permission granted.
weak passwords, shared account

5. Virtulization attack:
- exploits vulnerabilities for virtulization environemnt.
- trusted attacker can harm underlying hardware for multiple tenants.

6. Overlapping trust boundaries: all cloud consuerms can eb trusted atatckers.


Cloud Security mechanisms:
Encryption:
Symmetric:
- uses single key/same private key to encypt and decrypt.
- used in moedrn cs system
- example
	- AES - 128,192, 256
- it is fast
Asymptric:
- uses both private to decrypt and public to encrypt.
- for online transactions, data b/w org
- Example: RSA, ECC,eigmal, DSAdiffie-hellman
- slow

SSL/TLS: Secure socket layer, transport layer security.
- when data leaves the consuemr trust boudnary, crosses internet and reaches provider system
- uses public key infra dn secure web transactions.


Hashing:
- one-way, non-reversible
- uniqueness: one hash value cannot represent two diff values.
- Uses cases:
	- easier to comapre hash values.
	- comapre files in db.
	- hash passwords

Public key infra: (PKI)
framwork of encryption and cybersecuirty.
3 components:
1. digital certificate: electronic identification of wbsites
2. certificat autyhrotiyt: authority that signs digital certifcate
3. RA, registeration authrotty: authorized by CA to sign gitial crtificate case0bycase basis.
it proetct against:
1. malicious insiders.

Public key: available to all client when they visit website
private key: created when connection is made adn is kept secret.
communicating: client uses public key to eencrypt and decrypt, server uses private key.

hardened virtual server images:
- stripping all necessary software to limit vulnerabilities.
- close unn ports
- disabel unused services, root accounts, guest access


SSO:
Standards: oAuth and OpenAM
broker service returns authenticatino toke, contains idntity information, allows to switch between sercices.


Security pillar:
principles:
1. protect data in transit and rest
2. enable tracibility.
3. security at all alyers
4. keep peiople away
5. prepare for events

best practices:
Security:
1. automate security process.
2. staying up to date
3. diving workloads by account based on function.
IAM:
authror, authent, user management, credential manasgement
Detection:
 config, cloudwatch, cloudtrails, guardduty
Infra protection: VPC, firewalls, gateways
Data protection: classificatino amd encryption
incident respone:


### IaC
Benefits:
1. increased speed.
2. error reduction: handoffs
3.  consistneyc: helps in: global diversity adn disaster recovery
4. prevent configuration drift
5. cost reduction: no mistakaes, less manual power
6. version control
Iac helps in cicd

Ifc:
Infrastructure-from-Code (IfC) is a process that analyzes your application code to infer the cloud resources you need, and then creates and maintains them without you having to manually define them.


### Devops:
Average release process for web/cloud apps:
1. Release planning – Deciding new features or bug fixes for the sprint.
2. Sprint begins – Developers code and work on release goals.
3. Testing – Deploying the app to a test server for QA to find bugs.
4. Staging – App staging on a production-like environment, further testing.
5. Release – Deploying the app to the production environment. QA performs regression testing and approves the release.

Agile principles:
1. Early and continuous delivery of valuable software.
2. Welcome changing requirements, even late in development.
3. Deliver working software frequently.
4. Business people and developers must work together daily throughout the project.
5. Build projects around motivated individuals, giving them the necessary support and trust to get the job done.
6. Face-to-face conversation is the most efficient and effective method of conveying information within a development team.
7. Working software is the primary measure of progress.
8. Sustainable development, maintaining a constant pace.
9. Continuous attention to technical excellence and good design enhances agility.
10. Simplicity—maximizing the amount of work not done—is essential.
11. The best architectures, requirements, and designs emerge from self-organizing teams.
12. Regularly reflecting on becoming more effective and adjusting behavior accordingly.


- Lead Time: The duration between the customer's request and the customer receiving the product or service.
- Deployment Lead Time: The time from a developer committing work to being operational in production. 
- DevOps emphasizes reducing Deployment Lead Time to just minutes to enhance organizational competitiveness.

DevOps is crucial to Cloud Computing because:
- provisioning complex architectures adds complexity to projects  affecting deployment lead time.
- Without planning deployment lead time can become excessively long.
- Prolonged deployment lead time diminishes an organization's competitiveness.
- Eventually, if an organization is not competitive, their software becomes obsolete.

The Three Ways :
• Flow Principles: Fast left-to-right work flow from Development to Operations to the customer.
• Feedback Principles: Swift and constant feedback flow from right-to-left across all stages of the value stream.
• Continuous Learning and Experimentation: Fostering a generative, risk-taking for organizational learning from both success and failures.


The First Way: The Principles of Flow
- Make work visible: Utilize tools like Kanban boards and sprint planning boards to track work, identify bottlenecks, and measure lead time.
- Limit work in progress (WIP): Reduce context switching, solve delays rather than starting new work, and focus on completing tasks.
- Reduce batch sizes: Smaller batch sizes lead to faster lead times, quicker error detection, and less rework.
- Reduce handoffs: Automate processes to reduce dependency on others and retain knowledge within the team.
- Continually identify and elevate constraints: Recognize and address bottlenecks like environment creation, code deployment, testing, and tight architecture to improve flow.
- Eliminate hardships and waste: Address burnout, avoid partial work, minimize extra processes and features, reduce task switching and waiting, and automate everything possible.

The Strangler Application Pattern:
- Addresses tightly coupled or "monolithic" architectures, hindering change and causing fear.
- Puts existing functionality behind an unchanged API with an interface.
- Implements new functionality using the desired architecture, making calls to the old system when needed.
- Introduces new versions of the API over time.
- Eventually, the old system is replaced, and deprecation/decommissioning occurs.


Second Way: Principles of Feedback:
- Telemetry: Monitoring and logging at all levels of the application stack enable graphing, visualization of metrics, anomaly detection, proactive alerting, and escalation.
	- Log everything and add metrics for business logic events, DB ops, etc.
	- Start with the most important items, then expand to cover everything.
- The Andon Cord: Inspired by Toyota manufacturing plant practice, where any worker can pull the cord when something goes wrong, leading to immediate investigation and assistance from the entire team.
- Swarming problems prevents issues from progressing downstream, reducing costs and effort to fix, and avoiding the introduction of new work that could create additional errors.


Third way: Principles of Continual Learning and Experimentation:
- Allocate time for improving daily work and fostering learning to avoid technical debt and stagnation.
- Emphasize to superiors that this work is crucial for creating quality architectures and systems, not optional or wasted effort.
- Frequent improvement leads to increased agility.
- Introduce stress into systems to force continual improvement.
- Simulate and inject failures in production services under controlled conditions to enhance resilience (e.g., Netflix's "Chaos Monkey").


The Importance Of Organizational Culture

| Aspect                | Pathological Culture  | Bureaucratic Culture | Generative Culture |
|-----------------------|----------------------|----------------------|--------------------|
| Information           | Hidden               | May be ignored       | Actively sought    |
| Messengers            | "Shot"               | Tolerated            | Trained            |
| Responsibilities      | Shirked              | Compartmented        | Shared             |
| Bridging between teams| Discouraged          | Allowed but discouraged/blocked| Rewarded          |
| Handling Failure      | Covered up           | Just and merciful    | Causes inquiry     |
| New Ideas             | Crushed              | Create problems      | Welcomed           |


Release Management: IDEALLY
1. Continuous integration: Build and run unit tests automatically.
2. Automatic provisioning: Instantly set up a production-like environment.
3. Code deployment: Automate code deployment process.
4. Automated testing: Replace manual testing with automated tests.
5. Information security and compliance checks: Automated checks for code compliance.
6. Production deployment: Automatic deployment to the production environment.

Blue/Green Deployment Strategy:
1. Utilizes two identical environments: "blue" (live) and "green" (new version).
2. Enables seamless traffic switching for continuous app service availability.

Dealing With Database Changes:
1. Code release complexity arises due to tight database coupling.
2. Schema changes make supporting multiple code versions challenging.
3. To avoid issues:
	   a. Reduce coupling with stored procedures.
	   b. Consider blue/green databases for seamless release.
1. Decouple DB changes by making additive changes, using a version table, and migrating data.
2. Avoid code assumptions about the production DB version.
3. Cons:
	   a. Data duplication may occur.
	   b. Rollbacks can be complicated with database changes.

The Canary and Cluster Immune System Release Patterns:
1. Internal release: Code is released exclusively to employees (A1).
2. Canary release: A small set of users are directed to servers running the new code (A2).
3. Full release: All users receive the new code (A3).
> Canary deployment is an update strategy where traffic is gradually shifted to the new version


Feature Toggles and Dark Launches:
- Feature toggles use conditional statements to enable/disable app logic or UI elements based on config settings.
- Enable:
  - Easy rollback
  - Graceful performance degradation by turning off less critical features during high loads.
  - Increase resilience with a service-oriented architecture and independent feature releases.
- Dark Launches:
  - Deploy features to production without customer access.
  - Test directly in production.
  - Gradually enable the feature for specific users.
  - Finally, make the feature available to all users after testing.


> Availability, or the amount of time a system is up, measured in terms of time  
	loss
> reliability is measured in terms of the number and impact of failures. Reliability may be  
	seen as a measure of availability.

### Microservices:

- An architectural style that organizes an application as a collection of services.
- Each service is:
	- independently deployable
	- loosely coupled
	- focuses on specific business capabilities.
	- Owned by small teams
	- ensuring ease of maintenance and testability.
- Enables:
	- rapid, frequent, and reliable delivery of large, complex applications.
	- Allows an organization to evolve/test its technology stack over time.


Characteristics of Microservices:
1. Autonomous:
   - Each service is independent  communicating through well-defined APIs.
2. Specialized:
   - Each service is designed for specific capabilities and addresses a specific problem.


Benefits of Microservices:
- Agility: Small, independent teams take ownership of their services, working quickly within their context.
- Flexible Scaling: Each service can be independently scaled to meet demand, optimizing infrastructure needs and cost.
- Easy Deployment: Continuous integration and delivery enable quick experimentation and easy rollbacks.
- Technological Freedom: Teams can choose the best tools for each job, avoiding a one-size-fits-all approach.
- Resilience (Fault Tolerance): Service independence reduces the impact of failures, preventing total application crashes.


Things to be Aware of:

Developers must handle the additional complexity of creating a distributed system:
- Implementing inter-service communication is hard.
- Difficulty in implementing requests spanning multiple services.
- Testing interactions between services becomes more challenging.
- Coordinating between teams for requests spanning multiple services.
- Managing "state" is challenging as services tend to be stateless for efficiency.


Stateless Communication:
- Communication between client and service must be stateless.
- Services have no memory of any previous client interaction.
- However, authentication of requests is still performed.
- Stateless services allow better load balancing and scaling without the need for "client stickiness".

Complexity:
Deployment complexity:
- Managing a system with multiple services adds complexity during deployment.
Increased memory consumption:
- Each service running in its own VM.
- Each service may require its own load balancer.

Issues:
- Internal network latency.
- Designing message formats for communication between services.
- Ensuring backup, availability, and consistency of services.
- Dealing with increased network traffic and potential performance issues.
- Managing a higher number of interface points, leading to increased architectural complexity.
- Implementing load balancing to handle the distribution of requests.
- Ensuring fault tolerance to handle service failures and errors.

Excessive Independence:
- Can result in poor code re-use, teams may become "siloed," unintentionally hiding knowledge
	- Solution:
		- The use of shared libraries.
- Excessive use of technology may occur, leading to unnecessary proliferation of tools making it harder to share code and knowledge among teams.


Anti patterns:
1. Don't start with microservices until you experience complexity.
2. Don't do microservices without DevOps or cloud services support.
3. Don't make microservices too small; consider larger services first.
	1. only break when:
		- changes become hard, slow to deploy.
		- common data model becomes complex.
		- diff services have different load/scale requirements.
1. Don't try to imitate Netflix; start at a manageable pace and avoid complexity.


Deathstars (a.k.a. The Knot): Develops after transitioning to microservices.
- Any service can call any other service, leading to complex interactions.
- Revealed during testing as integration testing becomes cumbersome.
- To avoid it:
	- implement monitoring, observability of services and interactions.
	- mediation:
		- Use traffic management
		- strategy of interaction
		- security policy.


Difference between microservices and SOA:
- Microservices involve refactoring an application for easier management.
- SOA focuses on changing the way IT services work enterprise-wide.
- Microservices are used to create an application.
- SOA combines applications to form a complex system.

Monolithic vs. Microservices Authentication:

Monolithic Application:
- Single, indivisible unit.
- Authentication is only needed for user access to the app.
- No need for authentication within the monolithic application itself.
Microservices Application:
- Consists of multiple independent components integrated via APIs.
- Authentication is required for every communication between microservices.
- End-users also need to be authenticated.

Challenges in Microservices Authentication:
1. Central Dependency: Each microservice handles authentication separately.
2. Violating SRP: Adding global authentication to microservices makes them less reliable.
3. Complexity: Microservices authentication can become complex.

Solutions for Microservices Authentication:
1. Edge-Level Authorization: API Gateway centralizes authentication but may be less secure and harder to manage.
	1. Disadvantages of Edge-Level Authorization (API Gateway):
		1. Less secure: Attacker bypass can lead to unrestricted access to microservices.
		2. Difficult to manage: Complexity with roles and rules can make management challenging.
		3. Limited access control: Developers may have restricted permissions, leading to communication issues.
1. Service-Level Authorization: Each microservice directly handles authentication, offering more control over access policies.
	1. PAP: Admins create, manage, test access rules.
		PDP: Checks which policy applies, grants/denies access.
		PEP: Enforces access decisions for specific requests.
		PIP: Provides policy data and attributes for decisions.
1. External Entity Identity Propagation: Authorization decisions based on user context, but may have security issues and require multiple authentication techniques.
	1. granular
	2. need entity access token passed down
	3. not secure as JWT is shared.
	4. may need support for JWT, OIDC, cookies.

Cross-cutting concerns affect multiple services.
Microservices can complicate implementing cross-cutting concerns like authentication.
Examples: Caching, data validation, logging, monitoring, real-time constraints, etc.

