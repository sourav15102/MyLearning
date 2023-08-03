- RDS is not engine, it is a service.
- when we order online: transactional process
- OLAP get history data to get pattern
- best place for data lake is s3: cheap
- RDS supports aurora engine as well.

RDS multi-AZ:
- both instances main and failover, do not work together, only on works at a time.
- so, they cant improve performance
- cant go across region.
- sync replication.
- they share same endpoint: to be accessed.

rds multi-az with two readible standby:
- separat endpoints for read and wirte, ELB can be used to divide.
- async replication
- as long as one readible send back that update is done, operation is done.

Single AZ:
- supports Aurora engine

Read replicas:
- can be cross AZ and cross region: read replicas can do that.
- has own dns endpoint.
- async reoplication.
- can make replica as a copy??


back ups:

OLAP vs OLTP:

Aurora:
- it can be considered service cos it can works on it own.

Scenario:
- arora: beter perfoirmance, availability 6 replicas of data, data scurity

Redshift:
- support 16PB.

DyanmoDB:
- for horizontal scaling
- nosql support h-scaling cos no join funcitons
- for online gaming.
- session data/metadata prefer nosql.
- sort key: improve flexibiltiy??

ACID:

Global Table??

Security:

RDS:
- is similar to ec2
- uses ec2 as backend

DynamoDB:
- roles, policies
- vpc endpoints.

SCT & DMS

### Networking environment:
VPC:
- it is regional
- need to specify region while creating vpc
- after creating vpc can be deployed anywhere.
- for subnets can mentioned AZ
- one subnet one az
- but one az xan have multiple subnets
- 



