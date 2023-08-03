### EFS and EC2

- EFS can be attached to instances in multiple azs.
- You can mount the file system on instances in only one virtual private cloud (VPC) at a time. Both the file system and the VPC must be in the same Region.

### Mount target
EFS file systems can be mounted to multiple EC2 instances that run in different Availability Zones in the same Region. These instances use _mount targets_ that are created in each _Availability Zone_ to mount the file system by using standard NFSv4.1 semantics.
