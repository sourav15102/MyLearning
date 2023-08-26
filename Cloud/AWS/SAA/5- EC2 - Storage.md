EBS:
- Network drive NOT physical drive.
	- hence there might be latency.
- Block storage
	- Can be either SSD or HDD.
- Can be with ONLY ONE instance at a time.
	- However, one instance CAN have multiple EBS volumes. 
	- Can be UNATTACHED.
- Bound to ONE AZ.
	- can be moved across with help of snapshot.
- Have provisioned capacity: GB and IOPS
	- billed for this property.

There is delete on termination attribute for EBS.
- by default, it is set to delete for root EBS volume.
- by default, it is NOT set to delete for other EBS volumes.

EBS Snapshots:
- can be done at any point in time.
- should detach before snapshotting but NOT necessary.
- can move to another AZ or region.
- By default they are in standard tier.
Features:
1. EBS Snapshot archive:
	1. move to a archive storage tier, it is cheaper.
	2. 24-72 hrs to restore.
2. Recycle bin
	1. can recover after accidental deletion.
	2. retention can be set from 1 day to 1 year.
3. Fast Snapshot Restore
	1. faster restoration and fully initialized at creation.

Volume types: 6 types
SSD: ONLY these can be used for boot volume.
	- gp2/gp3: general purpose SSD, price and performance balance.
	- io1/io2: high performance, low latency, high throughput
HDD:
	- st1: low cost HDD for frequently accessed and throughput intensive workloads.
	- sc1: lowest cost HDD, for less frequently accessed.

Three characteristics:
1. IOPS
2. Size
3. Throughput

SSD:
gp2/gp3: General purpose
Good For: System boot volumes, Virtual desktops, Development and test environments
Size: 1 Gib - 16 Tib
gp3:
	- Baseline:    3K IOPS and 125 MiB/s throughput
	- Increment: 16K IOPS and 1000 MiB/s throughput
	- IOPS and throughput are not linked.
gp2:
	- Small gp2 - 3K IOPS
	- Size <--> linked with <---> IOPS
	- Increment 16K IOPS (5334 Gb)
	- Get 3 IOPS/GB

io1/io2: Provisioned IOPS:
Good for: sustained IOPS performance, database workloads, >16k IOPS needed
Size: 4Gib - 16 Tib 
MAX IOPS: 32K 
MAX IOPS with Nitro EC2 instances: 64K
Increase independently of storage
Io2 == io1 same price
But, io2 has more IOPS per GB

io2 block express: 
	Size: 4Gib to 64Tib
	submilisecond latency
	MAX PIOS: 256k  (if need more than this IOP use instance store)
	IOPS:Gib == 1000:1
	Supports EBS multi-attach

HDD:
st1/sc1: Cannot be boot volumes
SIZE: 125Gib - 16Tib
St1: Throughput optimized:
For: big data warehouses, log processing (stream of data)
MAX througput: 500 Mib/s
MAX IOPS: 500

sc1:
For: infrequent data accessed
MAX Through: 250 Mib/s
MAX IOPS: 250

![[Pasted image 20230815224440.png]]

EBS multi attach:
- same volume to multiple ec2 instances in same AZ.
- Only for io1/io2 block express
- each instance has full read/write permission
- Need diff file system: XFS< EXT4 --> cluster aware
Use:
- higher application availability
- concurrent write.
Limitations:
- 16 instances at a time.
- cant be attached with instances in different AZ.

EBS encryption:
When we encrypt an EBS volume, we get the following encrypted things:
1. data at rest
2. data in transit/in flight between volume and instance.
3. snapshots created from it.
4. volumes created from above snapshot.
More benefits:
1. encryption and decryption are handled transparently.
2. Minimal latency
3. KMS keys used

Process of encrypting EBS volume:
1. Create EBS snapshot from unencrypted volume.
2. make a copy of snapshot.
3. encrypt the copy.
4. create volume from the copy.


AMI:
- specific for region.
- types:
	- public AMI: from AWS
	- own AMI
	- AWS marketplace AMI: someone else's

Process of building AMI:
1.  start instance and customize it.
2. stop it for data integrity.
3. build AMI from it - EBS snapshots are created under the hood.

Instance store:
- network drives can have limited performance.
- Block storage
- Some special kind of instances have the actual physical server with a hardware called instance store attached with them.
- Why?
	- high performance
	- Better i/o
	- for buffer/cahce/scratch/temporary content
- Features:
	- is lost when instance is stopped(ephemeral)

EFS:
- NFS: Network file system
- can be attached to multiple instances EVEN in diff AZs
	- diff mount targets in diff AZs
- highly available.
- expensive ~= 3Xgp2(EBS general purpose volume)
Features:
- Uses security group to control access.
- compatible ONLY with Linux based AMI.(not windows)
- Uses POSIX file system.
- KMS  - encryption at rest.
- Scales automatically.
- pay-per use for each GB of data used.
- Used NFSv4 protocol.

Performance:
- Scale to concurrent 1000 NFS cleiunts
- Performance mode:
	- Gneral purpose: latency lowest
	- MAX IO: for parallel worklods, high latency
- Throughput mode:
	- Elastic:
		- for spiky workloads
	- Provisioned: 
		- set throughput rgarless of stoprage.
	- Bursting:
		- bursting thre throughput from intial value.
- Storage classes:
	-  StandrdL frequently accessd
	- IA: for infrequent access
- Availability abd duravbility:
	- standardL multi AZ
	- One zone: for Development, backup enabled by default
	- One zone IA: max discount