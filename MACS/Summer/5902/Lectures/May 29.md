Root volume vs general storage

Instances store:
uses for cahce.
> for reboot data wont be lost


EBS:
- ec2 and ebs has to be in same AZ
- when we create vol, it creats replicas in single AZs for failover.

IOPS vs Throuput:
gp2: 3XGB volume = iops, can burst
gp3: can input iops, cannot, not expensive

not every ec2 instance supports io2 block express

EBS is for one instance.
S3 is not optimal for frequnet changes
S3 should not be shared, it is not ideal, cos when changes occur, it changes the entire object.

Placement groups:
Partition placement grp: multiple AZs


### Database Layer:



