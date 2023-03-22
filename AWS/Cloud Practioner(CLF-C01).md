# Course

### Cloud Concepts: 26%
- define aws cloud and why it is chosen over competitors.
- apsects of aws cloud economics
- diff cloud architecture design principles
### Security Domain: 25%
- desifn aws sahred resp model
- aws security and compliance conpts
- aws access management capabilities
- ways to find resoufrces for security related iessiues
### Technology: 33%
- method to deploy and operate IT applications in aws cloud.
- aws glocal infra
- core aws services
- how to contact support
### Billing and pricing: 12%
- diff pricing models
- variosu account structure
- resources for support


- a new hip way to define internet.
- it is pay as you go
- on payment it resources, storeage, software
- mothly paymenty for only the amount we used.
- mainframe computer: central computer has all comouting power
- all other computer sending request to main pc, are dump terminals


### Cloud Computing model:
- IAAS
	- Provides most flexibility.
	- closest thing we have to on-premis data center.
	- modify almost all infrastructure to fit needs
	- aws,google cloud,azure
-  PAAS
	- allows to deploy apps without worrying about underlying infrastructure
	- examples: heroku
	- less flexibility than IAAS.
-  SAAS
	- entire software ready to use for you
	- ex: outlook, gmail
- 

### Cloud Deployment models:
- cloud deployment/public cloud
	- when an org uses cloud deployment, it means all its IT infra is migrated to cloud or were created on cloud.
	- depends on cloud service provider to manage resources.
	- may use, office 365, microsoft teams, ms azure.
	- USE: if company has none of its apps deployed yet(early stage), it can use clopud deployment.
- on premises/private cloud
	- resources cant be accessed using internet, cos they are onsite.
	- no need to download things cos they are onsite
	- means org is not sharing it with other org
	- USE: need privacy, can use it to kep it secure.
- hybrid
	- can have both
	- generally, for com panies who use cloud as backup
	- or maybe they are in the progress of migrating apps to cloud
	- USE:  org with legacy it resources which will take long time to upload to the cloud but need to extend functionality to cloud.  
- Cross cloud
	- using multiple CSP at the same time.  

###  Well architectured framwork:
- Avoid unnecessary costs:
	- use only what you need, turnoff resources not in use.
	- reserve resources in advance if you know you will need it in future.
	- monitor to optimize resources.
- Reliability
	- test disaster recovery settings reguilary.
	- incorporate redundancies in infra, so that in case of emergency you have backup.
- Efficiency:
	- it should be adjustable to  changes
- Security
	- data should be protected.
		- it should be protected while in transit or at rest
	- enable tracebility:
		- should be able to see who did what
		- there should be string identification foundation
	- manage access
		- who can access what should be well defined
	- Automation:
		- these best practices should be automated
- Operational excellence
	- document everything
	- update process regularly
	- learn from failures
- Sustainability

### Advantages of cloud computing over on-premises
![[Pasted image 20230319230604.png]]
Here "Massive economies of scale" means that service providers have huge space with them and they can offer a smaller portion of it to you at cheaper price.
![[Pasted image 20230319230812.png]]


### AWS
![[Pasted image 20230319213918.png]]




### Landscape of CSPs
**Tier-1 (Top Tier)** – Early to market, wide offering, strong synergies between services, well recognized in the industry

-   Amazon Web Services (AWS)
-   Microsoft Azure
-   Google Cloud Platform (GCP)
-   Alibaba Cloud

**Tier-2 (Mid Tier)** – Backed by well-known tech companies, slow to innovate and turned to specialization.

-   IBM Cloud
-   Oracle Cloud
-   Rackspace (OpenStack)

**Tier-3 (Light Tier)** – Virtual Private Servers (VPS) turned to offer core IaaS offering. Simple, cost-effective

-   Vultr
-   Digital Ocean
-   Linode

### Magic Qudrant:
metric to see what CSP is better

### Common Cloud Services
All cloud services can be grouped in various types, the most common are:
Cloud comoputing can be referred to all of these:
-   **Compute​** - Imagine having a virtual computer that​ can run applications, programs, and code.
-   **Networking​** - Imagine having a virtual network that allows you to define internet connections or network isolations
-   **Storage​**- Imagine having a virtual hard-drive that​ can store files
-   **Databases​** - Imagine a virtual database for storing and reporting data or a database for general-purpose web-application

> **Note:** - AWS has over 200+ cloud services
> Any CSP that has IaaS will have these 4 core cloud services.

### Dedicated server vs VM vs Containers vs Functions 
Dedicated Server:
![[Pasted image 20230319220714.png]]

VMs
![[Pasted image 20230319220837.png]]

Containers:
![[Pasted image 20230319220946.png]]
Its a lot of work to maintian.

Functions:
![[Pasted image 20230319221153.png]]





### Innovation Waves:
Kondratiev waves, it is a cycle like phenomena in global world economy.
### Burning platforms:
when org abadons old tech to adopt new tech with incertainity of success and can be motivated by fear.

### Computing power
General computing: EC2 
GPU Computing: AWS inferentiare (inf1)
Quantum Computing: AWS bracket
### AWS Global Infrastructure
Network of globally distributed hardware and datacenters physically connected together to act as one.

Regions: 
- geogrpahuically distinct locations, can contain one or more *availabiltiy zones*(generally 3)(some new users are limited to 2).
- physically isolated and independent from every other regions, in terms of power,water supply, and location.
- new services almost always become available at US EAST-1(northern virginia)
- service costs vary with region.
- not all services are availabl at all regions.
4 things need to be considered while choosing region:
![[Pasted image 20230320141723.png]]



## Questions:
- cloud computing vs legacy it infra
- diff between cloud and cloud computing
- diff between cloud and vm
	- cloud is computer services over internet, db storage, computing sercices 
	- vm is another machine given to you.
- how hypervisors related to vm and cloudi aksed 
	- hypervisors helps manage vms in a single phyiscal storage/server
	- cloud can make use of hypervisors in its various operations
- What is a CSP?
- Diff between cloud platform vs CSP?
- Diff between storage and database?
- why files delete from VM
- what are functions in serverless?