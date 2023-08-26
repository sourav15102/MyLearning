- When you stop and then start an EC2 instance, it can change its public IP.
- If you need to have a fixed public IP for your instance, you need an Elastic IP
- An Elastic IP is a public IPv4 IP you own as long as you don’t delete it
- You can attach it to one instance at a time
- 5 elastic Ips / account (may change)

Placement groups:
1. Cluster:
	1. ![[Pasted image 20230815173626.png]]
	2. On same rack(same hardware) and same AZ.
	3. low latency
	4. high risk
	5. NEED: low latency, high bandwidth, high throutput.
3. Spread:
	1. ![[Pasted image 20230815173643.png]]
	2. on different hardware.
	3. across AZs, maybe in same AZ.
	4. low risk
	5. 7 instances /AZ/placement group
	6. NEED: MAX availability, must be isolated
5. Partition:
	1. ![[Pasted image 20230815173659.png]]
	2. Partition represent rack in AWS.
	3. each partition can have multiple instances.
	4. 7 partition/ AZ
	5. instances in diff partition are isolated

ENI:
- Logical component in VPC --> Virtual network card.
- Eth0 - > priamary ENI
- Each instance has a default network interface, called the _primary network interface_. 
- You cannot detach a primary network interface from an instance. 
- You can create and attach additional network interfaces. 
- The maximum number of network interfaces that you can use varies by instance type
Attributes:
- A primary private IPv4 address from the IPv4 address range of your VPC
- One or more secondary private IPv4 addresses from the IPv4 address range of your VPC
- Can associate one Elastic IP address with each private IPv4 address.
- One public IPv4 address
- One or more IPv6 addresses
- One or more security groups
- A MAC address
- A source/destination check flag
- A description

Can be created independently and attached/moved on fly to ec2 instances for failover.
> Bound to AZ or subnet


EC2 Hibernate:
We can stop/terminate instances:
stop:
- data on disk(EBS) is kept for next start.
terminate:
- volumes setup to be destroyed will be destroyed otherwise they are kept.

Start:
- os boots, user data runs, cahche warms up

Hibernate:
- in-memory ram state is preserved.
- instance boot faster
- UNDER THE HOOD: ram data is written in file in root EBS volume.
- EBS --> must be encrypted.
Process:
![[Pasted image 20230815181521.png]]
> RAM Size < 150GB for it.
> Root must be EBS and must be encrypted and must be large to contain RAM data
> NO instance store supported
> NO bare metal supported
> FOR on demand, reserved, spot
> Cannot be hibernated for > 60 days