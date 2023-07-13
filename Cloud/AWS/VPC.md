### Communication within VPC:

All subnets (regardless of whether they are Public or Private) within the same Amazon VPC **can communicate with each other by default**.

Communication should be made via the **private IP address** of the resources, to ensure that the traffic stays _within_ the VPC.

### DNS resolver:
https://docs.aws.amazon.com/Route53/latest/DeveloperGuide/resolver.html
Amazon Route 53 Resolver responds recursively to DNS queries from AWS resources for public records, Amazon VPC-specific DNS names, and Amazon Route 53 private hosted zones, and is available by default in all VPCs.


### Flow:
1. create VPC
2. create both subnets, public and private
3. create internet gateway (IGW) connect it to VPC.
4. create route table and route all traffic from public subnet to IGW: thats why its public.
5. create route table and route traffic from privat subnet to NAT gatway.
6. ec2 insatnce create in public.
7. rds create in private.


Questions 1:
While creating public route table for IGW, when we have 0.0.0.0/0 (representing everything), why do we need  route for 'local' wont 0.0.0.0/0 conflict with it since it represent every IPV4 IP?
A: So initially when you create a VPC there is one route table which has local route with your VPC local IP address. It allows communication within the VPC. When we create IGW and assign it 0.0.0.0/0 (any IP) then it allows communication to and fro from the internet (Any IP) but when you're using any resources inside your VPC it still uses the local one so think of it like a precedence local > any IP address. Since there is a precedence it won't cause conflicts.

Questions 2:

While working on Network assignment, I found out that we dont need a outbound rule for RDS SG connecting to EC2, why is that, dosn't RDS need to send response back to EC2? and also our EC2 instance doesn't need inbound rule for RDS wont EC2 need it for receiving response from from RD


- how rsources within vpc can call each other by private DNS?



