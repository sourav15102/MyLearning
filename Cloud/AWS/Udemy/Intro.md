
> there are notes for each video in the course.
> HOWEVER, those notes only contain material other than the slides downloaded.


>There could be some in-between notes on udemy.

SLS
SLA
Paas: some level of config freedom is required.
Cloud native
Cloud deployment:
how google photos is public.
Reserved Ec2



deployment models of cloud:
1. private cloud
2. public cloud
3. hybrid cloud
4. multi cloud

delivery  models of cloud:
1. iaas
2. paas
3. saas
![[Pasted image 20230506160819.png]]

Pricing fundamentals:
1. compute
2. storage
3. data transfer out

> most aws services region scoped

How to choose aws region:
- services available
- price
- proximity
- compliance gov

AZ:
- a region has usually 3
- min - max: 3-6

> AZ = 1 or more data centers

> Global services: IAM, route53, cloudfront, waf

### IAM:
> users, groups
> inline policies
>policies define permissions.
![[Pasted image 20230506165712.png]]

Account security
- can set password policies.
- MFA
	- virtual: google authen.., authy
	- u2f: universal 2nd factor, (3rd party)
	- hardware key fob, (3rd party)
	- hardware key fob for gov cloud, (3rd party)
- Access key ID and secret access key:
	- AWS management console: password+MFA
	- CLI: access key
	- SDK: access key

> Services are assigned roles.

Security tools: summary
- credential report(account-level): users, credential status.
- access advisor(user-level): 
	- services a user was permitted to access
	- when they were last accessed.

Shared responsibility(SRP): `[55]`

### EC2
configuration: ram,os,cpu,storage,network card,firewall rules(security group),bootstrap script(ec2 usr data)
> storage:
> network accessed: EBS, EFS
> hardware: ec2 instance store

>m5.2xLarge
>m: class
>5: generation
>2xLarge: size

Instance types: `[64]-[65]`
1. compute optimized 
2. memory optimized 
3. storage optimized

Security groups:
- reference ip addresses and other security groups.
- only have "allow"
- can be attached to multiple instances.
- locked to region/vpc combination.
> generally have diff security groups for inbound(blocked: default) and outbound traffic(authorized: default).
> also separate one for SSH access
![[Pasted image 20230506173608.png]]

how to connect to instance:
- ssh: all by windows(>=10)
- putty: all windows
- ec2 instance connect: all, on browser, amazon linux 2

Purchasing options: `[81]-[87]`
SRP: ``[90]``

### Storage:
EBS
- network drive
- for AZ
- mounted to 1 instance at a time.

> an option for ebs to be deleted on ec2 termination

Snapshots:
use to copy ebs across AZ
can store it in archive:
- retrieve in 24-72 hrs
- cheaper
Recycle bin:
- set retention

AMI: amazon machine image:
- customization of instance
- for a region
- types:
	- public: by amazon
	- own
	- marketplace: someone else
	 
image builder:
- automate process of building, maintaining, validating AMI.
- can be on schedule

EC2 Instance Store: hardware NOT network: block storage
- high performance: I/O
- ephemeral: loose if stopped

EFS: Network
- multi ec2 instance
- for linux instance: multi AZ
- highly available, scalable, expensive
EFS IA:
- cost optimized
- for files that were not accessed recently.

FSx: launch 3rd party high performance on aws
they both are made to access data from corporate data center
- FSx windows file server: SMB protocol, windows ntfs: integrated with MS AD
- FSx lustre: HPC, ml, video processing

### ASG:

H-scaling: load balancer, ASG
High avail: load balancer, ASG in multiple AZs

>Horizontal scaling linked to high availability
> high availability: app running at >=2 AZ

Load balancer helps in:
- one point DNS
- SSL termination, https certificate
- fault tolerance

elasticity: automatic scaling

ELB types:
1. Application: layer 7, https
	1. Static DNS(URL)
2. network: layer 4, tcp
	1. high performance
	2. static ip
3. gateway: layer 3, GENEVE on IP packets
	1. traffic to firewall
	2. intrusion detection
4. classic: layer 4,7

ASG:
- scale in/out
- automatic register new instances to load balancer
	- set min-max
- replace unhealthy ones.

How it scales:
1. manual
2. dynamic scaling:
	1. step: cloud watch alarm CPU>70% add 1
	2. target tracking: keep avg 60%
	3. schedule
	4. predicative; ML


### S3
> infinite scaling

- store object(files) in buckets(dir)
- buckets have globally unique name, all region, all account
- NOT gloabal, buckets are defined for region.

objects:
- object have a key, values, metadata, tags, version id(if enabled).
- ![[Pasted image 20230506192054.png]]
- no dirs inside bucket
- just long nameed keys
- objct 

content of object:
1. key
2. value: content, 5TB, >5GB multi part upload.
3. metadata: key/value pairs
4. tags: key/value pairs, for security
5. version id

who can access S3:
- user based: IAM policy
- resource-based: Bucket policy, Obj ACL, bucket ACL, (Access control list)

> even if we have public access on bucket policy, public access is blocked on account level

Replication: Must enable versioning
since it is regional, we need replication:
CRR: lower latency
SRR: test/prod 

Classes:
Durability: 11 9s, 10mil -10thous.
1. general purpose: 99.99% availability
	1. low latency - high throughput, 
	2. big data analytics, mobile/pc gaming
2. IA:
	1. standard: 99.9%
		1. disaster recovery,backups
	2. one zone: 99.5%
		1. scondary backup on prem
3. Glacier: for archive backup
	1. instant: minisec
	2. flexible: 1-5min, 3-5hrs, 5-12hrs
	3. deep archive: for long term: 12 standard,24hrs bulk
4. Intelligent tering:
	1. automatic tiering b/w classes: only standard and standard IA
	2. no retrieval cost
	3. monitoring+auto tiering cost

Encryption:
- server side: default
- client side

>SRP: `[154]`

For edge location: if it takes >1 week to transfer data, use snow family.
- data migration:
	- snowcone
	- snowball edge
	- snowmobile
- edge computing:
	-  snowcone
	- snowball edge
Snowball:
- storage optimized
- compute optimized

Snowcone:
- lighter than snowball.
- can be sent back or DataSync

![[Pasted image 20230506195548.png]]
>if using more than 10pb than switch from ball to mobile.

OpsHub: software for managing snowfamily devices

> S3,EBS,glacier is proprietary: how can we use it on-prem: **Storage Gateway**
> Types of Storage Gateway:
• File Gateway
• Volume Gateway
• Tape Gateway
Amazon EBS 
S3 
Glacier


### Database:
- better than all until now.
- structure, index, relationship

Relational: for vertical scaling
No-SQL: horizontal scaling

RDS:
- relational
- OLTP
- SQL
	- mysql
	- oracle
	- postgre
	- maria
- all managed by aws
	- os oatching
	- multi az for Disaster recovery
	- maintenance
> cant ssh into instance

Replicas:
1. read replica: scale
	1. 15 nodes
	2. write only to main DB
2. Mutli AZ: failover, high availability
	1. write to main
	2. only 1 AZ extra for failover
3. multi region: disaster recovery
	1. local performance
	2. write to main DB, region (active-passive replication).

Aurora:
- proprietary
- more cloud native
- mysql, postgresql
- high performance
- self healing

Elastic Cache:
- for redis, memcache
- in-memory
- reduce load-off db for read intensive.

Dynamo DB:
- No-SQL
- low latency
- serverless
- key-value pair
- it does active-active replication.(Global tables)

DynamoDB accelarator: DAX
- 10X performance
- for only Dynamo DB.

Redshift: warehouse
- OLAP
- PostgreSQL
- columnar
- integrate with AWS quicksight or tablue.

EMR:
- hadoop
- provision 100s of ec2 itself.
- ml, data processing, big data

Athena: provides analysis
- serverless
- sql
- can query s3 using serverless sql

Quicksight:
- serverless
- ML powered
- dashboards
- business analytics drawn out of data

DocumentDB
- mongodb

Neptune:
- graph database

QLDB:
- record financial transactions
- immutable
- track history of data change

Amazon managed blockchain:
- same as QLDB but decentralized.

Glue:
- Serverless
- ETL
- catalog of datasets stored at **Glue Data Catalog**

DMS: database migration service:
- securely migrate db.
- the source remains available
- homogenous: same dbms
- heterogenous: different dbms


### Docker:
- images are stored in docker repo.
- public: docker hub
- private: ECR -: Elastic Container Registry
	- this is where ECS/fragate get their registry from.

ECS: Elastic container service:
- launch container on AWS
- takes care if starting stopping containers.
- We must provision infra, ec2 instances.

Fargate: serverless
- launch containers on AWS.
- we dont provision infra.

Lambda:
- serverless: scaling is automated
- virtual functions
- short executions
- on demand.
- event driven.
- pricing:
	- call
	- RAM X duration
- Serverless thumbnail creation
- cron jobs
> if we want container to run on lambda: docker image should implement Lambda Runtime API

API Gateway:
- serverless 
- supports RestFul/websocket API.

AWS Batch:
- batch processing at scale.
- It can give 100Ks of batch jobs(docker images) to ECS.
	- jobs have start-end time
- can dynamically launch ec2 instances and sport instances.

Lambda vs batch:
- lambda has time limit, batch doesn't.

Lightsail:
- for ppl with little cloud ex
- simple web app, wbsites, dev/test env
- everything you need to launch your project quickly – virtual machines, containers, databases, CDN, load balancers, DNS management etc.

### Infra at scale

Cloudformation:
- infra as code.
- creates in right order
- can estimate cost of resources.
- can see relationship between resources.
- can create or delete template on schedule/on the fly.
- can pickup templates from web.
- can create diagrams.

CDK:
- can write infra code in JS, Java, Python.
- CDK will compile that code into Cloudform template.
- can deploy infra/app code together.

Elastic beanstalk
- PaaS
- health agent pushes metrics to cloudwatch.
- still can control config
- free but pay for underlying instances.
- if any language is not supported, can write our own platform.

### Deployment

CodeDeploy: can deploy code automatically.
- hybrid service.
	- on prem/ec2 instances
	- need code deploy agent on them.

AWS System Manager: SSM
- hybrid service
- run commands, patches, configuration on ec2 instances and on-prem servers at scale.
- need system manager agent on them.
- can SSH into them. 
	- no SSH keys, 
	- no port 22

> Chef and Puppet: automatically configure servers and on-prem vm.

OpsWork: (OpsHub: for snowfamily)
- hybrid
- manages chef and puppet.
- alternate to SSM.

Code Commit:
- uses git
- collaborate
- versioned

CodeBuild:
- builds code.
	- compile
	- test
	- package: ready to be deployed.

CodePipeline:
- orchestrate diff steps

CodeStar:
- unified UI
- can do all above in UI

Cloud9:
- online editor.


### Global Application:

Route 53: DNS;
![[Pasted image 20230506230721.png]]
Routing Policy:
- simple: NO health checkup.
- weighted: health checkup
- latency: health checkup
- failover: health checkup on primary

CloudFront:
- CDN
- Cached content at edge location(point of presence).
- DDoS protection(global)
- Integration with WAF, Shield.
Origins:
- S3 bucket:
	- security with OAC: origin access control
	- can be used as ingree: to upload files to S3
- HTTP backend: Custom origin
	- app load balancer
	- ec2
	- s3 static website

Cloudfront vs S3 replication:
- Cloufront: for static content
- S3: for dynamic content with low latency at few regions.

S3 Acceleration:
- pass file to edge location that will use private aws to s3.

Global Accelerator(1):
- improves availability, performance
- 2 analyst IP created for app.
vs Cloudfront(2):
- both have shield for DDOS protection.
- 1) No caching, proxying packets.
- 2): cached , served at the end

Outposts:
- Hybrid
- AWS install outposts racks on premise
- WE must physically secure the servers now.
- start leveraging AWS services on-premises

Wavelength:
- extends telecommunications providers’ datacenters at the edge of the 5G networks.

Local region:
- extension of AWS region.
- low latency for end suer

Global Architecture: `[271-272]`
- Region help with latency
- AZ help with Availability

### Cloud Integration
- apps wants to async talk
- how to decouple:
	- SQS: queue
	- SNS: pub/sub
	- Kinesis: real time data processing
SQS: queue
- decouple
- message deleted after reading
- mutliple producers/consumers.
- consumer share msgs
SNS: notification, topic
- even pubs submit to one topic.
- even subs subscribe to topics
- all get msgs
- no retention

Amazon MQ:
- cloud broker for rabbitMQ and Active MQ. 

Kinesis:
- real time: collect, process, analyze
- Data Stream: ingest data from mutiple sources
- Data firehose: load stream into s3, redshift, elasticsearch
- Data analytics: real time analytics using SQL
- Video Stream: real time video stream monitor for ML, Analytics


### Monitoring:
- CloudWatch provide metrics for every service.

Cloudwatch metrics: to see usage
CloudWatch Alarms:
- trigger notification for metric.
- Actions:
	- create/terminate ec2 instance.
	- inc/dec ec2 instance.
	- SNS notification.
- States:
	- OK
	- INSUFFICIENT_DATA
	- ALARAM

CloudWatch Logs:
- Need agents to collect logs from ec2 instance and on-prem servers.

EventBridge: cloudwatch events
- trigger events for within aws accounts.
- Ex:
	- schedule cron jobs
	- send SNS/trigger labmda every time root user logs in.
- Schema Registry: model event schema
- Can archive events to event  bus and replay them.

Cloudtrail:
- provides governance.
- keep track of events/api calls made within account by console,sdk,cli,service
- can be applied to one or all region.
- If a resource is deleted in AWS, investigate CloudTrail first!
- can send results to cloudwatch logs or S3
Cloudtrail insights:
- automatic analysis if trail events.

X ray:
- collecting and analyzing logs from diff microservice architecture is difficult.
- using this we can:
	- troubleshoot issues/performance.
	- where service throttle.
	- which users will be impacted

CodeGuru:
- Code reviewer: static code analysis, development
- code profiler: performance recommendations, runtime, preprod and in production.

AWS Health Dashboard: - service history
- all region, all service health
- show history for each day.
AWS account health dashboard - Your account
- personalized view into services health, that might impact you.
- can aggregate data from entire org.

### VPC:
IP:
- public IP
	- ec2 instance gets new public ip every time it starts or stops.
- private ip:
	- fixed for ec2 instance
- elastic ip:
	- can fix public ip on instance
	- gets billed if ip not attached or instance stopped.
- IPV6
	- every ip address is public.

VPC:
- regional resource:
Subnets:
- AZ resource
- partition VPC
Public subnet: accessible from internet.
Private subnet: not accessible from internet.
Route Tables:
- to define access from internet and b/w subnets.

Internet Gateway: allows VPC to connect to internet.(public subnet)
NAT gateways(aws managewd): allows private subnet to access internet while remaining private.
NAT instances(self managed): same as nat gateways but self managed.

Both control in-out traffic
NACL: network ACL:
- for subnet
- for allow and deny
- refer IPs
- stateless
Security Groups:
- for instances
- only allow
- refer IPs and other security groups.
- stateful

VPC flow logs;
- monitor flows logs for:
	- VPC
	- Subnet
	- ENIs: elastic network interface / ec2 instance

VPC peering:
- connect two vpc, beahve like one
- cant do if  overlapping cidr
- not transitive

VPC endpoints:
- allows instances to use aws services using private network
- VPC endpoint gateway: s3 and dynamoDB
- VPC endpoint interface: others

VPC private link:
- scalable way to expose VPC to many users.(not  peering).
- service VPC needs Load balancer
- costumer VPC needs ENI

Connect On-prem to VPC:
Site to site VPN & Direct connect(DX:
S to S:
- public network
- encrypted
- on-prem needs: Customer gateway
- VPC: virtual private gateway
DX:
- private
- fast
- physical connection.

Remotely connect to AWS private network or on-prem as if you were there:
AWS Client VPN:
- public network

Topology can be complicated:
To have transitive connection between 1000s VPC adn on-prem, hub-and-spoke(star):
AWS transit gateway:


### Security and compliance:
SRP: ``[329-332]`` // important
Shared by both:
Patch Management, Configuration Management, Awareness & Training

DDoS protected by:
- Shield standard: 
	- free
	- SYN/UDP Floods, 
	- Reflection attack
	- other layer 3/layer 4 attacks
- shield advanced: 
	- not free, 
	- access to DDoS team, 
	- more sohpisticated attacks
	- 24/7 premium protection
- WAF: filter requests based on rules.
	- layer 7
	- Web ACL:
		- Ip address based rules
		- SQL injection and Cross-Site Scripting (XSS)
		- Geo match: block countries
		- Rate based DDoS protection

Cloudfront/global accelerator/load balancer/api gateway: shield, WAF
route53: shield

Penetration testing:
- allowed on 8 services without approval:
- Amazon EC2 instances, NAT Gateways, and Elastic Load Balancers
• Amazon RDS
• Amazon CloudFront
• Amazon Aurora
• Amazon API Gateways
• AWS Lambda and Lambda Edge functions
• Amazon Lightsail resources
• Amazon Elastic Beanstalk environments
- Progibited:
	- DNS walking
	- DDoS
	  flodding attacks

Encrypt both data in transit and at rest
- leverage encryption keys:
KMS: aws managed encryption keys for us
Encryption Opt-in:
• EBS volumes: encrypt volumes
• S3 buckets: Server-side encryption of objects
• Redshift database: encryption of data
• RDS database: encryption of data
• EFS drives: encryption of data
Encryption Automatically enabled:
• CloudTrail Logs
• S3 Glacier
• Storage Gateway

CloudHSM:
- hardware: we manage our own encryption keys entirely.
- however, aws manages the hardware.
- we use cloudhsm client to manage keys

Types of CMK: customer master keys:
- customer managed: 
- aws managed
- aws owned: used for accounts
- Cloudhsm: keys generated from cloudhsm hardware: custom keystore

AWS certificate manager (ACM):
- provision, manage, deploy: SSL/TLS certificate

AWS secret manager: RDS integrated
- stores secret
- rotation of secret
- integration with rds

AWS artifact:
- allows to access compliance docs and agreements.
- artifacts reports and agreements

AWS Guard Duty:
- collect logs and detect intelligent threats using ML
- cryptocurrency attacks
-  can use eventbridge to send notifications

AWS Inspector:
works on:
1. ec2 instance
	1. unintended network access
	2. running os vulnerabilities
	3. uses SSM agent
2. container images
	1. when images are pushed to ecr.
3. lambda functions
	1. assessment of vulnerabilities
	2. code

AWS config
- audits and records compliance
- records changes in config over time.
Questions that can be solved by AWS Config:
• Is there unrestricted SSH access to my security groups?
• Do my buckets have any public access?
• How has my ALB configuration changed over time?

Macie:
- uses ML
- discover sensitive info: PII: personal identifiable information

AWS security HUB:
- central security tool to manage  security across all accounts
- aggregates data from all security services

AWS detective:
- finds root cause using ML.
- uses VPC flow logs.

AWS abuse:
- reports abuse
- Abusive & prohibited behaviors are:
- Spam
- Port scanning
- DoS or DDoS attacks 
- Intrusion attempts
- Hosting objectionable or copyrighted content
- Distributing malware

Root privileges:
Actions that can be performed only by the root user:
• **Change account settings** (account name, email address, root user password, root user access keys) -- 
• View certain tax invoices
• **Close your AWS account** -- 
• Restore IAM user permissions
• **Change or cancel your AWS Support plan** -- 
• **Register as a seller in the Reserved Instance Marketplace** -- 
• Configure an Amazon S3 bucket to enable MFA
• Edit or delete an Amazon S3 bucket policy that includes an invalid VPC ID or VPC endpoint ID
• Sign up for GovCloud

IAM access analyzer:
- to find out which resources are shared externally.
- zone of trust.
- outside of zone == findings

### Machine learning:
AWS rekognition:
- detect objects, people, text, scenes, images, videoes
- facial analysis
- facial search
- pathing: sports
- content moderation

Transcribe:
- voice to text
- DL, ASR: automatic speech recognition
- remove PII
- detect language

Polly: opposite of transcibe
- text into speech

AWS translate: translate

Lex & Connect:
- LEX:
	- for chatbots
	- ASR
	- NLP
- Connect:
	- Virtual contact center
	- CRM

AWS Comprehend: NLP

AWS SageMaker: Build ML models
- fully managed service to build ML models.

AWS forecast:
- uses ML to make highly accurate forecast

AWS Kendra:
- Documents search service
- incremental learning: from user interaction

AWS personalize:
- personalize product recommendation, direct marketing

AWS textract:
- extract text, handwriting from scanned docs

### Advanced Identity Section
AWS STS:
temporary, limited-privileges credentials to access aws. resources

Amazon Cognito:
- login users
- login with google, fb, twitter

MS Active Directory:
- found on windows server with AD domain services.
- it is a db of objects: user, accounts, printers
- ![[Pasted image 20230507221333.png]]

AWS AD services:
1. AWS managed MS AD: 
	1. create own ad in aws, establish trust with on-prem AD.
2. AD connector:
	1. directory gateway proxy redirect to on-prem AD.
3. Simple AD
	1. on aws, cannot be joined to on-prem


IAM Identity center:
- single sign on
- one login for all aws acounts


### Other Services:
Workspaces:
- DaaS
- provision windows or linux desktops
- on multiple regions

Appstream:
- app delivered from within web browser
- configure CPI, ram, GPU

IoT Core:
- connect iot devices to aws cloud.

Elastic transcoder:
- convert media files stored in s3 into formats required by devices.

AppSync:
- store and sync data acorss multiple web and mobile apps

AWS Amplify:
- set of tools and services helps you develop & deploy we/mobile applications 

Device Farm:
- can test app against farm of devices

AWS backup:
- manage and ***automate backups/scheduled backups*** across services
- PITR: point-in-time-recovery
- cross-region/cross-account

Disaster recovery strategy: ***(least to most costly)***
- Backup & restore: S3 backup
- pilot light: 1 ec2 with light verison of app
- warm standby: 1 ec2 with full version, min size
- multisite/hotsite: multi site with full version, full size

Elastic Disaster recovery: Cloud endure disaster recovery
![[Pasted image 20230507202812.png]]

DataSync:
- large data from on-prem to aws
- incremental after its full
- has datasync agent --> datasync in region.

While migrating from on-prem to aws, tough to find out server config
- agentless discovery: small things, memory, ussage, cpu, performance cpu
- agent-based discovery: system config, network connection

AWS AMS: app migration service:
- does everything on its own
- ![[Pasted image 20230507203337.png]]

Fault injection:
- chaos engineering
- stresses app
- pre-built template

Step Function:
- visual workflow to build process, microservice, parallel processing

Ground Station:
- satellite communication
- send data to s3 or ec2

Pin-point:
- 2-way inbound/outbound msges
- pushes in-app, sms
- Versus Amazon SNS or Amazon SES(simple email service)
		• In SNS & SES you managed each message's audience,
		content, and delivery schedule
		• In Amazon Pinpoint, you create message templates,
		delivery schedules, highly-targeted segments, and full
		campaigns



### Billing
Organizations Units(OU): manage multiple accounts
- has master and child accounts.
- consolidate billing
- aggregate usage
- pool of reserved ec2
	- management account can turn off discount sharing for sny account in aws including itself.
- API to automate account creation
- restrict account privileges using SCP: service control policies

SCP:
- applied at account level or OU level.
- doesnt apply on master account.
- applies even on root user of an account.
- within an account applies on user and roles.
- doesnt apply on service-linked roles. 
- must have explicit allow- doesnt allow anything by default.
- explicit deny trumps everything

AWS control tower:
- sits on top of organizatrions
- can csetup and govern multi account aws nvironemnt

 Service catalogs:
 - new users want to build something quickly.
 - admin can create products(CloudForm templates)
 - add them to portfolio
 - add permissions, who can access what.

Pricing Models:
- pay as you go
- save when reserve.
- pay less when use more
- aws grows pay less

Free tier:
- IAM
- VPC
- Consolidate billing
Pay for resource created
- elastic beanstalk
- CloudFormation
- ASG
Free tier:
- EC2 t2.micro instance for a year
- S3, EBS, ELB, AWS Data transfer

Pricing:
EC2:
- number of instances
- configuration
Lambda:
- per call
- ram X duration
ECS and fargate:
- no pay
- but for resources used we pay
S3:
- no. and size of objects
- type of requests
- data out
- class
- s3 transfer acceleration
EBS:
- storage volume in gb per month
- data transfer out
RDS:
- engine
- size
- class
- purchasing type
- in-out request
- data out
Cloudfront:
- depends on region
- http request
- data out
Network:
![[Pasted image 20230507234820.png]]
Savings plan:
- Ec2:
	- commit to family and region
	- commit to payment per hour for 1or 3 yrs
- Compute:
	- less discount than above
	- commit to nothing

Compute optimizer:
- use ML to analyze your resource configuration
- usage using CloudWatch metrics
- reduce costs and improve performance
- Supported resources
	• EC2 instances
	• EC2 Auto Scaling Groups
	• EBS volumes
	• Lambda functions

Billing tools:
Estimate:
- pricing calculator: estimate price of architecture
Tracks:
- Billing dashboard
- Cost Allocation tags:
	- used to create resource groups
	- track costs in details
	- aws generated: aws:
	- user generated: user: 
- Cost and usage reports:
	- deeper dive
	- most comprehensive set of cost and usage data
	- integrated with athena, redshift
Cost explorer:
- forcast usag upto 12 months
- optimal saving plan choose

> for cloudwatch: billing data is for worldwide costs
> data metric is stored in us-east-1

AWS cost anomaly detection:
- uses ML
- monitor costs and usage to detect anomalies

AWS service quatoas:
- notify when you are closr servic quota valu threshold


Trusted advisor:
- cost optimization
- fault tolerance
- performance
- security
- service limits

7 core checks: basic, developer
Full check: business, enterprise
- CloudWatch alarms
- programmatic access using support API.

Basic: again
- customer service 24/7: discussion , doc, white,
- 7 core trusted advisor: not for technical assistance
- personal health dashboard

Developer:
- Business hour email access: Cloud support associates
- General guidance < 24 business hours
- system impaired < 12 business hours
Business: production workload
- 24/7 access to email,chat,phone to Cloud Support Engineers
- full check trsuted advisor
- Production system impaired: < 4 hours
- production system down: 1< hour
Enterprise on ramp: production and business critical workload
- pool of Technical Account Managers (TAM)
- Concierge team: for biling, best practices
- Infrastructure Event Management, Well-Architected & Operations Reviews
- Business-critical system down: < 30 minutes
Enterprise: mission critical workloads
- designated Technical Account Managers (TAM)
- Concierge team: for biling, best practices
- Infrastructure Event Management, Well-Architected & Operations Reviews
- Business-critical system down: < 15 minutes


### CAF:
https://www.w3schools.com/aws/aws_cloudessentials_mig_awscaf.php


CAF
architecture
what are regional services

billing
IOPS




















