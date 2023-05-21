
### Stephan test:
Q11:
AWS Marketplace offers two ways for sellers to deliver software to customers: Amazon Machine Image (AMI) and Software as a Service (SaaS).

Amazon Machine Image (AMI): Offering an AMI is the preferred option for listing products in AWS Marketplace. Partners have the option for free or paid products. Partners can offer paid products charged by the hour or month. Bring Your Own License (BYOL) is also available and enables customers with existing software licenses to easily migrate to AWS.

Software as a Service (SaaS): If you offer a SaaS solution running on AWS (and are unable to build your product into an AMI) the SaaS listing offers our partners a way to market their software to customers.

AMI
Saas
cloudformation temp
containers.

**Buy Amazon EC2 Standard Reserved Instances** - Amazon EC2 Standard Reserved Instances can be bought from the Amazon EC2 console

Q13:
**AWS Acceptable Use Policy**

The Acceptable Use Policy describes prohibited uses of the web services offered by Amazon Web Services, Inc. and its affiliates (the “Services”) and the website located at http://aws.amazon.com (the “AWS Site”). This policy is present at https://aws.amazon.com/aup/ and is updated on a need basis by AWS.

Q18:There is a one-minute minimum charge for Linux based EC2 instances, so this is the correct option.

Q25:
With Enterprise Support, you get access to online training with self-paced labs, 24x7 technical support from high-quality engineers, tools and technology to automatically manage the health of your environment, consultative architectural guidance, a designated Technical Account Manager (TAM) to coordinate access to proactive/preventative programs and AWS subject matter experts.

Q28:
AWS Service Health Dashboard offers the possibility to subscribe to an RSS feed to be notified of interruptions to each service.

Q29:
Amazon Relational Database Service (Amazon RDS) makes it easy to set up, operate, and scale a relational database in the cloud. Read Replicas allow you to create read-only copies that are synchronized with your master database. Read Replicas are used for improved read performance. You can also place your read replica in a different AWS Region closer to your users for better performance. Read Replicas are an example of horizontal scaling of resources.

**Read Replica reduces database usage costs**(usage cost, not read cost) - RDS with Read Replicas increases the database costs compared to the standard deployment. So this option is incorrect.

Q34
SSM: Systems Manager provides a central place to view and manage your AWS resources, so you can have complete visibility and control over your operations.
Operational insights

**AWS Personal Health Dashboard** - AWS Personal Health Dashboard provides alerts and remediation guidance when AWS is experiencing events that might affect you. It is not used to get operational insights of AWS resources.

**AWS Trusted Advisor** - AWS Trusted Advisor is an online resource to help you reduce cost, increase performance, and improve security by optimizing your AWS environment. Trusted Advisor provides real-time guidance to help you provision your resources following AWS best practices. It is not used to get operational insights of AWS resources.


Q35:
**S3** - Amazon Simple Storage Service (Amazon S3) is an object storage service that offers industry-leading scalability, data availability, security, and performance. S3 is object storage and it does not support file append operations, so this option is incorrect.

Q36:
DynamoDB global tables replicate data automatically across your choice of AWS Regions and automatically scale capacity to accommodate your workloads. With global tables, your globally distributed applications can access data locally in the selected regions to get single-digit millisecond read and write performance. DynamoDB offers active-active cross-region support that is needed for the company.


q37:
Compute Optimizer: helps choosing optimal configurations
Supported resources
• EC2 instances
• EC2 Auto Scaling Groups
• EBS volumes
• Lambda functions

Q45:
**Agility** - Agility refers to new IT resources being only a click away, which means that you reduce the time to make those resources available to your developers from weeks to just minutes. AWS Elastic Load Balancing does not help with agility.

Elastic Load Balancing offers three types of load balancers that all feature the high availability, automatic scaling, and robust security necessary to make your applications fault-tolerant: Application Load Balancer (best suited for HTTP and HTTPS traffic), Network Load Balancer (best suited for TCP traffic), and Classic Load Balancer.

Q49

Q56:
Types of Storage Gateway:
• File Gateway
• Volume Gateway
• Tape Gateway
Amazon EBS 
S3 
Glacier

Q59:
**CloudTrail**

You can use CloudTrail to log, monitor and retain account activity related to actions across your AWS infrastructure. CloudTrail provides an event history of your AWS account activity, including actions taken through the AWS Management Console, AWS SDKs, command-line tools, and other AWS services.

**Config** - AWS Config is a service that enables you to assess, audit, and evaluate the configurations of your AWS resources. Config continuously monitors and records your AWS resource configurations and allows you to automate the evaluation of recorded configurations against desired configurations.

Q60:
**AWS Cost Explorer**
The rightsizing recommendations feature in Cost Explorer helps you identify cost-saving opportunities by downsizing or terminating EC2 instances. You can see all of your underutilized EC2 instances across member accounts in a single view to immediately identify how much you can save.

**AWS Cost and Usage Reports** - You can receive reports that break down your costs by the hour or month, by product or product resource, or by tags that you define yourself. Cost and Usage Reports cannot be used to identify under-utilized EC2 instances.

**AWS Trusted Advisor**:
under utilized

Q62:
APN Consulting Partners are professional services firms that help customers of all types and sizes design, architect, build, migrate, and manage their workloads and applications on AWS, accelerating their migration to AWS cloud.

**APN Technology Partner** - APN Technology Partners provide hardware, connectivity services, or software solutions that are either hosted on or integrated with, the AWS Cloud. APN Technology Partners cannot help in migrating to AWS and managing applications on AWS Cloud.


Q64:
EBS, Instance store block level;
instance store: temp block levl
**EFS** - Amazon Elastic File System (Amazon EFS) provides a simple, scalable, fully managed, elastic NFS file system.

Q65:
cloudwathch events i.e. event bridge can be usd for cron jobs

step functions can aggregates different services but cant schedule


### Dojo:
Q15
Amazon S3 is optimal for storing numerous classes of information that are relatively static and benefit from its durability, availability, and elasticity features. However, in a number of situations, Amazon S3 is not the optimal solution. It has the following anti-patterns:

**File system -** Amazon S3 uses a flat namespace and isn’t meant to serve as a standalone, POSIX-compliant file system. However, by using delimiters (commonly either the '/' or '\' character) you are able to construct your keys to emulate the hierarchical folder structure of a file system within a given bucket. Alternatively, you can simply use EFS.

**Structured data with query** - To retrieve a specific S3 object you need to know the bucket name and key. Thus, you can’t use Amazon S3 as a traditional database by itself. Instead, you need to pair Amazon S3 with a database, such as DynamoDB for example, to index and query metadata about Amazon S3 buckets and objects. Or you need to use Amazon Redshift Spectrum to allow you to query through your objects.

**Rapidly changing data -** Data that must be updated very frequently might be better served by a storage solution with lower read / write latencies, such as Amazon EBS volumes, Amazon RDS or other relational databases, or Amazon DynamoDB.

Q22:
**Amazon Web Services** offers you the flexibility to run Microsoft SQL Server for as much or as little time as you need and select from a number of versions and editions. SQL Server on Amazon Elastic Compute Cloud (Amazon EC2) and Amazon Elastic Block Store (Amazon EBS) gives you complete control over every setting, just like when it’s installed on-premises. Amazon Relational Database Service (Amazon RDS) is a managed service that takes care of all the maintenance, backups, and patching for you

Q29

Q34:
**AWS Cost Explorer** is incorrect because this is just a tool that enables you to view and analyze your costs and usage. It does not specifically provide detailed information about Amazon EC2 Reserved Instance (RI) usage or the discounted RI rate charged to your resources as what the scenario required.

**AWS Budgets** is incorrect because it simply gives you the ability to set custom budgets that alert you when your costs or usage exceed (or are forecasted to exceed) your budgeted amount.


Q37:
Q44
Q46: object storage and static is S3
Q47: 
**AWS Budgets** gives you the ability to set custom budgets that alert you when your costs or usage exceed (or are forecasted to exceed) your budgeted amount.

You can also use AWS Budgets to set reservation utilization or coverage targets and receive alerts when your utilization drops below the threshold you define. Reservation alerts are supported for Amazon EC2, Amazon RDS, Amazon Redshift, Amazon ElastiCache, and Amazon OpenSearch reservations.

Q49:
 The AWS Management Console provides a web-based way to administer AWS services. You can sign in to the console and create, list, and perform other tasks with AWS services for your account. These tasks might include starting and stopping Amazon EC2 instances and Amazon RDS databases, creating Amazon DynamoDB tables, creating IAM users, and so on. The AWS Command Line Interface (CLI), on the other hand, is a unified tool to manage your AWS services.

**AWS Systems Manager** is incorrect because this is just a unified user interface so you can view operational data from multiple AWS services, and allows you to automate operational tasks across your AWS resources

Q53
: volume pricing: consilidat biling

Q54
**Amazon Route 53** is a highly available and scalable cloud Domain Name System (DNS) web service. It is designed to give developers and businesses an extremely reliable and cost-effective way to route end users to Internet applications by translating names like www.tutorialsdojo.com into the numeric IP addresses like 192.0.2.1 that computers use to connect to each other.

This service can also help you create a hybrid cloud architecture using the Amazon Route 53 Resolver, which provides recursive DNS for your Amazon VPC and on-premises networks over AWS Direct Connect or a VPN solution.

Q55:
**Amazon EventBridge** is a serverless event bus service that enables the rapid development of event-driven applications. Amazon EventBridge is a service that provides a scalable, reliable, and secure solution for applications such as SaaS tools and AWS services to respond to events in real-time. By decoupling your application components, you can reduce the complexity of your architecture, make your applications more resilient and scalable, and create an event-driven workflow that reacts to events as they happen.

Q56:
**AWS Security Token Service** **and** **Amazon Route 53** are both incorrect because these are considered global services.

**Amazon EC2** is incorrect because this is considered as a zonal service since it is tied to a particular Availability Zone where it was launched.

**AWS Batch** is a regional service that simplifies running batch jobs across multiple Availability Zones within a region. You can create AWS Batch compute environments within a new or existing VPC. After a compute environment is up and associated with a job queue, you can define job definitions that specify which Docker container images to run your jobs.

EFS: regional

Q61:


### Test 3:
Q7:
The option that says: **Provisioning Elastic IPs and attaching them to running EC2 instances** is incorrect because Elastic IPs are only charged if they are not attached to running instances.

Q17:
penetration without approbal
Amazon EC2 instances, NAT Gateways, and Elastic Load Balancers
• Amazon RDS
• Amazon CloudFront
• Amazon Aurora
• Amazon API Gateways
• AWS Lambda and Lambda Edge functions
• Amazon Lightsail resources
• Amazon Elastic Beanstalk environments


Q21:
The option that says: **Deferred payments to their operational expenditures** is incorrect because this type of payment is not supported when you move to AWS.

reduce time to market correct


Q22:
The option that says: **Groups can be nested** is incorrect since this is not allowed in IAM Groups.
The option that says: **There's no limit to the number of groups you can have** is incorrect because there is actually a certain limit to the number of groups you can have as well as a limit to how many groups a user can be in.

The option that says: **There is a default group that automatically includes all users in the AWS account** is incorrect because there is no such thing as this in IAM

Q24:
**Concierge Support** is incorrect because this is a team composed of AWS billing and account experts that specialize in working with enterprise accounts. They will quickly and efficiently assist you with your billing and account inquiries and work with you to implement billing and account best practices so that you can focus on running your business.

**Technical Account Management** is incorrect because this is a part of AWS Enterprise Support which provides advocacy and guidance to help plan and build solutions using best practices, coordinate access to subject matter experts and product teams, and proactively keep your AWS environment operationally healthy.

Q47:
#### Explanation

Instances that use Amazon EBS for the root device automatically have an Amazon EBS volume attached. When you launch an Amazon EBS-backed instance, we create an Amazon EBS volume for each Amazon EBS snapshot referenced by the AMI you use. You can optionally use other Amazon EBS volumes or instance store volumes, depending on the instance type.

we dont need elastic ip address.

Q48:
**Amazon Machine Image** is incorrect because this mainly provides the information required to launch an instance, which is a virtual server in the cloud

Instance metadata can be used to access info.

Q49:
**- Allows the change of instance family, operating system, tenancy, and payment option.**

**- Has the capability to change the attributes of the RI as long as the exchange results in the creation of Reserved Instances of equal or greater value.**

Q54
The main thing to note here is that **the life cycle management supports all the S3 Storage classes, unlike the Intelligent Tiering which only supports Standard and Standard-IA**

Q59
**Amazon Cognito Identity Pool** provides temporary AWS credentials for users who are guests (unauthenticated) and for users who have been authenticated and received a token. An identity pool is a store of user identity data specific to your account.
**Amazon Cognito User Pool** is incorrect because a user pool is a user directory in Amazon Cognito. In addition, it doesn't enable access to unauthenticated identities. You have to use an Identity Pool instead.


what are serverless/global/regional
- is RDS
- is dyanodb
- https://jayendrapatil.com/aws-global-vs-regional-vs-az-resources/
8 services allowed for penetration testing

caf
arch meaning all
tam vs conceirge vs all..


decouple your components
think parallel

cloud best practices.

s3-IA times



### Test 4:
Q10:
glacier:
- instant retreival: milisecond, minimum storag: 90 days
- flexible: Expidited: 1-5 min, standard: 3-5 hours, bulk: 5-12 hours.minimum storag: 90 days
- deep glacier: standard: 12 hrs, buld 24 hrs, minimum storag: 180 days

Q13:
- amazonmq used for following industry standard messaging.

Q14
![[Pasted image 20230513184816.png]]


Q17:
cloudformation & beanstalk cannot deploy on-prem
ssm/opswork: can deeploy /operate apps on on-rpm & cloud
opswork: can rollbacl as well.


Q26:
 aws chime helps in internal communication of comapny.

Q32:
EBS snapshots can be stored in s3

Q33: can get reserved instances for RDS as well.

Q37:
Hence, the correct answer is: **You are the owner of the data you store in AWS**.

The option that says: **Encryption is required for all data at rest and in transit** is incorrect since there is no such requirement imposed by AWS. Customers are free to encrypt data they wish to encrypt.

The option that says: **All data are stored durably and redundantly in different AZs** is incorrect since not all data receive such treatment. It is up to the customer to decide how they want their data stored in AWS.

The option that says: **AWS has the right to review any data stored for potential threats** is incorrect since the customer owns their data. AWS cannot simply access customer data without permission.

Q47:
S3 these costs money

**- Egress data to the internet.**

**- Setting up S3 lifecycle policies.**

Q55:
**Amazon S3** **Infrequent Access** is incorrect because this may not be the storage choice for a static website. Since this is a website, you expect multiple visitors a day who will also be accessing your objects at a frequent rate. S3 infrequent access GET requests cost more than a standard S3 storage type.

----

### Test 5
- EC2 is IaaS
- enterprise plan still has production from business plan which is <1 hr for prod system down
- technical support and customer service are diff and developer onwards has unlimited technical support
- there is no way yo disable root account credentials
- aws artifact can access point for compliance but cant monitor it, config can
- storage gateway can help extend storage from on-prem to aws
- aurora is self healing
- security hub: provides a comprehensive view of security alerts and compliance status across your AWS accounts.
