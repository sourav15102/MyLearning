
In Amazon Elastic File System (EFS), a mount target is a network interface that allows Amazon EC2 instances to access an EFS file system. Mount targets provide an entry point for EC2 instances within a specific Amazon Virtual Private Cloud (VPC) to mount and access the EFS file system.

one mount target per vpc
**You can create one mount target in each Availability Zone in your VPC**. All EC2 instances in a VPC within a given Availability Zone share a single mount target for a given file system. If you have multiple subnets in an Availability Zone, you create a mount target in one of the subnets.

can efs be attached to multiple vpcs at a time?
Now we need to determine the Availability Zone ID of the EC2 instance. After connecting to your instance from a local machine run the following command and we will get the ZoneId. This way **you can create multiple instances in different VPCs and mount the EFS file system to those instances**.