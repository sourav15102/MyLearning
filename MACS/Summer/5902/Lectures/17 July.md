- API gateway vs load balancers
- route 53 videos: exam, brightspace

SQS:
- Consumer has to remove msg from SQS.
- visibility timeout: consumer has to read and remove from Q within timeout, if cant others can read it.
- dead letter queue: 
- long polling saves money.
- it is 1-1

SNS:
- 1 SNS - N SQS: Fan out
- it is 1-N

ECS:
- EC2 vs Fargatet
- task vs service

- serverless functions ahs to be small not microservices ahs to be small?

Q:
- load balancer how is it a component of decoupling arch
- dead letter queue vs timeout: if one cant process -- passes to other, and can it go on n on.
- Ssytem des
- cloduwatch event vs alarms
- 