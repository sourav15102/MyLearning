### 5409:
- why we need .java app file for packahging(for proto)
- https://dal.brightspace.com/d2l/le/content/271679/viewContent/3649575/View: watchdog
- how does clodufronyt cache?
- dynamic in cloudfront?
K8S:
- how ingress works with deployment (replication of containers).
- changes to configmap/secret reflects in all replicas  of app (created through deployment).
Docker:
- service vs containr
- is service in docker like service in k8s does load balacning
- if we cant create deployment without template, why do we have a separate pod
- when nwe delete cluster does it delete pv as well
- if we need to specify pod in template, why do we have pod separate yaml file. 
- if we cannot mount pv on multiple nodes, then when depoyment puts replication of same pods on different nodes how do they access the volume then.
Network:
- subnet group
- why rds doesnt need outbound rule for ec2.
- why ec2 doesnt need inbound rule for rds.
- why do we need nginx..
- savings plan vs reserved plan
- spot fleets
SQS:
- why sqs needs polling
- ec2 and rds: securoity groups rules, purvesh question

### 5902:
- diff between db engine and service
- diff between asycn adn sync replication.
- if new primary is runnign and old is down, how update happens: async replication.
- fail in read replicas and readible standby
- each read replica as differner endpoint what decides the route?
- could you explain why we prefer nosql in case of session/metadata
- why would we need mongodb comapatible documentdb instead of dynamodb.
- 5902:  
1. managd vs unmanaged  
2. 5 security grps to instance  
3. parameter store vs secret manager  
4. Snowball 1tb  
5. static vs dynamic  
6. However, in addition to the praise, customers often asked whether they could place online orders.  
7. www. html inside will paste code
8. EBS and EFS
9. one AZ multiple subnets from differnt VPCs?
10. secuirty gfroups can be attached to multiple vpcs
11. who can we attach security groups with
12. [[17 July]]
LAB
- Enables the Target tracking scaling policy configured as:      Metric type: Average CPU utilization     Target Value: 25     Instances need: 60
- is asg regional
- lis load balancer regional
- subnets for load balancer and security grp
- how asg connects to lb via target group
- 5th qustion in lab
- nginx, alb vs api gateway
- launch template aws


### 5410

deepsense student research and employment tool.
why HA might cause high latency or hight throughput