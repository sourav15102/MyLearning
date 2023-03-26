**Availability Zones(AZ):**
- physical location made up of one or more datacenters(a secured building contains hundreds or thousands of computers)
- AZ within a region will be isolate from​ each other (different buildings). But they will​ be _close enough to provide low-latency (< 10ms)_.​
- All AZs in an AWS Region are interconnected with high-bandwidth, low-latency networking, over fully redundant, dedicated metro fiber providing high-throughput
- AZs are within 100 km (60 miles) of each other.
- All traffic between AZs is encrypted
- they are isolated from each other regions are isolated.
- each AZ is designed as independent failure zone([[Fault domain(Failure Zone)]])
- The use of AZ’s give customers the ability to operate production applications and databases that are more:
	-   Highly available
	-   Fault tolerant
	-   Scalable
- Its common practice to run workloads in at least​ 3 AZs to ensure services remain available in case ​one or two datacenters fail **(High Availability)**.
- AZ are represented by a region followed by availability zone marker: us-east-1a: a here represent an AZ
- A [[Subnet]] is associated with AZ.
- we never choose AZ when launching resources, we choose subnets.
![[Pasted image 20230323235615.png]]
- Multi AZ architecture is adopted for high availability:
Applications which are across AZs, they are better protected from power outage, lightening strikes etc..