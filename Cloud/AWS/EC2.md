Beanstalk vs ECS
Elastic Container Service is appropriate when you need to run microservices that require integration with other AWS services, or  
use custom or managed schedulers to run batch workloads on EC2 On-Demand, Reserved, or Spot Instances.
Beanstalk:
For businesses new to AWS or new to the containerization concept, just getting started with Docker, or developing new  
applications, Elastic Beanstalk may be the best approach to support Docker containers

- When you launch an EC2 instance, you must choose an AMI and an instance type. Launching an instance involves specifying configuration parameters, including network, security, storage, and user data settings.


AMI:
has what we need to laucnh instance:
- template for root volume: OS,  other softwares (both instance store and ebs can act as root volumes)
- permissions: which account can access ami.
- block device mappings: storage volume attached
- Instance store: a device physically attched to host machine where the VM, ec2 instance is running.

Configuration of AMI:
- region.
- OS
- root volume: ebs instance store
- archtecture
- Virtualization type: HVM/ParaVirtual
- For best performance, use an AMI with HVM virtualization type



EBS vs Instance store backed instances:
![[Pasted image 20230618173544.png]]
![[Pasted image 20230618173617.png]]

EC2 Image Buidler:
- Enforces version control

### Instance type:
What it tells:
CPU, memory, storage, and network performance characteristics.

Compute Optimizer:
1. EC2
2. lambda
3. ECS on fargate
4. ASG
5. EBS
Classifies instance findings as  
Under-provisioned, Over-  
provisioned, Optimized, or None

User Data:
- shell script or cloud-init directive
- 
Metadata:
http://169.254.169.254/latest/meta-data/

AMI vs UUser data
Baking configurations into an AMI   increases AMI build time, but decreases   instance boot time. Configuring an   instance by using user data decreases AMI   build time, but increases instance boot   time.

Root Volume:
this contains guest OS:
- isnatc store
- ebs backed (SSD)

An EC2 instance will always have a root volume, and can optionally have one or more data volumes.

Instance store:
- non presistent
- on sam physical server where VM is present
- HDD or SSD
- no data lost on reboot
- buffer cache
- scratch data
- For instance types with **NVMe instance store volumes**, all of the supported instance store volumes are automatically attached to the instance at launch
- It cant be attached to instacn after launch

EBS:
- can by encrypted
- Automatically replicated within a single AZ
- Dynamically increase capacity and change the volume type without downtime or performance to the life systems
Types:
- General Purpose SSD volumes (`gp2` and `gp3`) balance price and performance for a wide variety of transactional workloads. These volumes are ideal for use cases such as boot volumes, medium-size single instance databases, and development and test environments.
- Provisioned IOPS SSD volumes (`io1` and `io2`) are designed to meet the needs of I/O-intensive workloads that are sensitive to storage performance and consistency. They provide a consistent IOPS rate that you specify when you create the volume. This enables you to predictably scale to tens of thousands of IOPS per instance. Additionally, io2 volumes provide the highest levels of volume durability.
- only this has multi attach supported
- Throughput Optimized HDD volumes (`st1`) provide low-cost magnetic storage that defines performance in terms of throughput rather than IOPS. These volumes are ideal for large, sequential workloads such as Amazon EMR, ETL, data warehouses, and log processing.
- Cold HDD volumes (`sc1`) provide low-cost magnetic storage that defines performance in terms of throughput rather than IOPS. These volumes are ideal for large, sequential, cold-data workloads. If you require infrequent access to your data and are looking to save costs, these volumes provides inexpensive block storage.

IOPS vs Throughput
- IOPS and Throughput depend on block size of the disk.
- Storage vendors define those block size to be 512 Bytes for HDD & 4K for SSD  based.
- Throughput: number of bits /sec  (MB/s)

EFS:
- serverless
- Scales automatically up or down as files are added and removed  
- Petabytes of capacity
- expensive
- Read-after-write consistency
- For linux instances
- For windows: Fsx for Windows File Server
- For a data volume that servers extremely high-performance workloads, use Amazon FSx for Lustre.


Pricing
- Per-second billing is available only for On- Demand Instances, Reserved Instances, and Spot Instances that run Amazon Linux or Ubuntu
- Use a combination of Reserved Instances, On-Demand Instances, and Spot Instances to optimize Amazon EC2 compute costs
- linux/win: per sc , 1min minimum, otherwise; per hour


Placement group:
No charge for creating one
- https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/placement-groups.html

- An instance can be launched in only one placement group at a time  
- Instances with a tenancy of host cannot be launched in a placement group  
- Only certain types of instances can be launched in a placement group (compute optimized, GPU, memory optimized, storage optimized)  
- You cannot merge placement groups  
- You can move an existing instance into a placement group. The instance must be stopped and moved. You can only move or remove an instance using AWS CLI or AWS SDK, but you cannot do this via the console.
- 



``

### Extra:
Process of creating AMI as EBS snapshot:
Here's the process in more detail:

1. Prepare the EC2 Instance: Before creating an AMI, you ensure that your EC2 instance is in a stable state and any necessary data or configurations are already in place.
    
2. Create an EBS Snapshot: An EBS snapshot is taken of the EBS volume that serves as the root volume for the instance. This snapshot captures the entire contents of the volume, including the operating system, applications, and data.
    
3. Create an AMI from the Snapshot: Once the snapshot is created, you can create a new AMI using that snapshot. The AMI serves as a template for launching new EC2 instances with the same configuration as the original instance. The AMI includes all the information required to boot an instance, including the snapshot of the root volume.
    
4. Launch Instances from the AMI: With the AMI created from the EBS snapshot, you can launch new EC2 instances based on that AMI. These instances will have the same data, configuration, and operating system as the original instance.

For instance store backed instances:
The bundle process involves the following steps:

1. Instance Preparation: Before bundling, you typically ensure that your instance is in a stable state and any necessary data or configurations are already in place.
    
2. Create a Bundle: You initiate the bundle process, which involves bundling the instance's root file system, along with any additional specified directories or files, into a single bundle file. This bundle contains all the necessary data to recreate the instance, including the operating system, applications, and data.
    
3. Upload Bundle to Amazon S3: The bundle file is then uploaded to an Amazon S3 (Simple Storage Service) bucket, where it is securely stored.
    
4. Register the Bundle as an AMI: After the bundle is successfully uploaded, you register it as an AMI. This creates a reusable template that you can use to launch new instances with the same configuration.
    
5. Launch Instances from the AMI: With the registered AMI, you can launch new instances that replicate the configuration of the original instance. These new instances will have the same data, software, and settings as the bundled instance.


