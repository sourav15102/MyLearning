https://www.youtube.com/playlist?list=PLTCrU9sGyburHcVKRuw2yXt7V7HEa6ZYY
##### Types of communication between microservices:
1. Sync (Blocking)
	1. Request-response cycle using GRPC/REST APIs
2. Async (Non-blocking)
	1. Event Based: Message Brokers
	2. Shared Database: Two microservices share a db.
		1. Only recommended in cases where communication is one way, i.e., one party is writing and rest are reading, if both can read/write problems can occur, hence this approach is not recommended.

##### Transactions in distributed system:
Single DB: If there are multiple tables, we can execute a transaction, i.e., we can make changes to multiple tables, and change state of multiple tables atomically.

Multiple DB: In a distributed system, there may be multiple db servers, and if we need to execute transaction across multiple db servers, and change state simultaneously, we can make use of 
**Two-phase commit.**
- Phase 1: Coordinator asks both services to check if they are ready to commit, and once it gets 'Yes' from both of them, both tables gets locked.
- Phase 2: Coordinator asks both services to make changes. 
If anything goes bad, changes are reverted.
**Issue**:
- Works fine with small db, however with databases with huge data, it is inefficient and impractical.
Solution:

![[Pasted image 20240116231958.png]]
SAGA: Any algorithm which can help you solve problem of updating distributed state in distributed system using events.

How it handle issues:
1. backward recovery: Lest say if anything goes wrong at step 3, we recover or 'take back' all previous calls.
2. forward recovery: lets say if anything goes bad at step 3, we retry it.

Approaches:
1. Orchestration:
	1. ![[Pasted image 20240116232201.png]]
	2. User is orchestrating here to manage transaction.
2. Choreography:
	1. ![[Pasted image 20240116232255.png]]
	2. One service is emitting the event and other is listening to those events to perform their action.
	3. here it is relatively a bit tougher to identify the culprit service if anything goes wrong.


##### Event based communication:
As it was earlier mentioned, in event based communication, we have multiple services running, and we can send messages via message brokers to fellow services. 
Messages vs Events:
- Messages are something which travels via message brokers.
- Events are contained by messages.
Other services can react to the events received by them, hence making it a event based communication.

Tradeoffs:
- While sending events aimed for multiple target services, different services might want different data, and we might overload the events with hige amount of data, or we may not want some services to see some data.
- So, either we can send multiple events, or combination of event + API (service B receives event E reacts to this event by making an API call to source service and get more info.)

Message Brokers:
1. Topic based: PubSUb
	1. if msg didnt go through, we can retry 
2. Queue based
	1. if msg dodnt go through, it goes to DLQ.


Coupling and Cohesion:
Types:
1. Domain: 
	1. Lest say User service asks Payment service to do its job.
	2. User service depends on Payment service.
2. Pass Through:
	1. User service send id, amount and email to Payment service, then payment service send email to Notify service.
	2. Email is not needed by payment service(typically), we are 'passing Through' email of user unnecessarily.
	3. if user change the name of email entry in json it will have to change what payment is sending to notify service, which is extra overhead.
3. Common coupling:
	1. Where two services are both reading and writing to a common db.
	2. basically, data at a common place is shared by multiple entities.
4. Content Coupling:
	1. Where A has direct access (read/modify) of entity of other service.

##### Side Car Pattern
https://newsletter.systemdesigncodex.com/p/this-pattern-saves-a-gazillion-hours
Pattern to move from monolith to microservice.
We can have a separate smaller component, language agnostic, which can handle different stuff and help the legacy application.
For ex:
1. Legacy app accepts http, we want https, we can have side car act as proxy to accept https, and then it can divert data to http.
2. We can have a new feature, we can have side car as a new component.



##### Event Sourcing
