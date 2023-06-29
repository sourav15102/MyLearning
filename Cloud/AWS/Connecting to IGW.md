https://docs.aws.amazon.com/vpc/latest/userguide/VPC_Internet_Gateway.html#ip-addresses-and-nat

When you assign a public IP address to an instance in a VPC, the only component that is aware of the public IP address is the Internet Gateway (IGW) in the VPC.

Here's how it works:

1. Assignment of the public IP: When you assign a public IP address to an instance in a VPC, the VPC's networking infrastructure, specifically the IGW, is made aware of the association between the instance and the public IP address.

2. Translation by the IGW: When the instance sends traffic out to the internet, the IGW performs network address translation (NAT) on behalf of the instance. The IGW translates the source IP address of the outgoing traffic from the private IP address of the instance to its associated public IP address.

3. Reply traffic: When the response traffic returns from the internet, it is directed to the public IP address of the instance. The IGW receives this traffic and performs reverse NAT, translating the destination IP address from the public IP to the private IP address of the instance, allowing the traffic to reach the correct instance within the VPC.

So, while the instance itself is not directly aware of its public IP address, the IGW is responsible for performing the necessary translation between the public and private IP addresses. The instance continues to use and communicate using its private IP address within the VPC, and the IGW handles the translation of traffic to and from the public IP address when it interacts with the internet.

This setup allows the instance to have a publicly accessible address for internet communication while maintaining its internal communication using the private IP address within the VPC.