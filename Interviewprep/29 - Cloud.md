#### DynamoDB:
Fully managed NoSQL database service. highly scalable, serverless.
DynamoDB supports both key-value and document data structures.
encrypted at rest.
Components:
table: contains items
item: contains attributes

Primary key: Uniquely identifies item
- other than primary key, the table is schemeless (no data type, no data needs to be defined earlier).
- In addition to the table name, you must specify the primary key while creating table.
Two types of PK:
- Partition key only: partition key produces hash which determines partition, and no two items can have same partition in this case.
- Partition key vs Sort key(composite PK): partition key produces hash which determines partition, and alll items in same partition will be sorted by sort key.

Secondary Indexes: lets you query the data in the table using an alternate key
DynamoDB supports two kinds of indexes:
- Global secondary index – An index with a partition key and sort key that can be different from those on the table.
- Local secondary index – An index that has the same partition key as the table, but a different sort key.
Each table in DynamoDB has a quota of 20 global secondary indexes (default quota) and 5 local secondary indexes.

Query vs Scan:
Query operations only support an equal operator evaluation of the Primary Key, but conditional (=, <, <=, >, >=, Between, Begin) on the Sort Key.
Scan operations are generally slower and more expensive as the operation has to iterate through each item in your table to get the items you are requesting.

Aurora vs DynamoDB:

| Feature              | Amazon Aurora                              | Amazon DynamoDB                             |
|----------------------|--------------------------------------------|--------------------------------------------|
| **Database Type**    | Relational Database Management System (RDBMS) | NoSQL Database                              |
| **Data Model**       | Table-based with structured schema         | Key-Value and Document Store                |
| **Query Language**   | SQL (Structured Query Language)             | No SQL Query Language (DynamoDB Query)      |
| **Scaling**          | Vertical and Horizontal Scaling            | Automatically scales with traffic           |
| **ACID Compliance**  | Fully ACID compliant                        | Provides ACID transactions for specific operations |
| **Performance**      | High performance, suitable for complex queries | Low-latency, designed for high-speed, simple queries |
| **Use Cases**        | Suitable for complex applications with complex data models | Ideal for simple, high-traffic, and low-latency use cases |
| **Cost Structure**   | Pay for provisioned resources (instances and storage) | Pay per request capacity and storage used   |
| **Global Secondary Indexes** | Supports secondary indexes | Supports Global Secondary Indexes for flexible querying |
| **Backup and Restore** | Supports automatic backups and manual snapshots | Continuous backups and point-in-time recovery |
| **Managed Service**  | Managed by AWS, handles infrastructure and maintenance | Fully managed, serverless database service  |

DynamoDB Stream:
can be described as a stream of observed changes in data
Stream consists of _Shards_. Each Shard is a group of _Records_, where each _record_ corresponds to a single data modification in the table related to that stream.
options on what data should be pushed to the stream.
- `OLD_IMAGE` - Stream records will contain an item before it was modified
- `NEW_IMAGE` - Stream records will contain an item after it was modified
- `NEW_AND_OLD_IMAGES` - Stream records will contain both pre and post-change snapshots
- `KEYS_ONLY` - Self-explanatory

SNS:
Simple notification service, cloud based service follows PubSub model, publishers, subscribers. Subscribers get messages on SQS, HTTPS, Lambda protocol.
We can add filters to subscriptions (topic-subscribers relationship):
- that a message should only reach a set of subscribers.
Uses:
- seamless communication between distributed systems, microservices, and serverless applications.
- ability to support fan-out architecture, enabling parallel processing of notifications across numerous subscribers.

Q:  How does Amazon SNS ensure at-least-once delivery of messages? What can you do to handle duplicate messages in your application?
A:
Amazon SNS ensures at-least-once delivery by persisting messages in multiple servers across availability zones and retrying message delivery until a successful acknowledgment is received.
Duplication: maintaining a cache of recently processed message IDs and checking against it before processing new messages.

Q: SQS vs SNS:
A:
SNS is a publish-subscribe service that enables sending messages to multiple subscribers simultaneously.
SQS is a distributed queue system for decoupling components in a cloud application.
SQS is ideal for managing large volumes of messages between microservices or distributed systems.

|Aspect|Amazon SNS|Amazon SQS|
|---|---|---|
|**Messaging Paradigm**|Publish/Subscribe (Pub/Sub)|Message Queues|
|**Message Delivery**|Push mechanism, immediate delivery|Pull mechanism (Long Polling), polling for messages|
|**Consumer Behavior**|Messages delivered to multiple subscribers|Messages processed by a single consumer|
|**Real-time Notifications**|Ideal for real-time notification scenarios|Suited for asynchronous message processing|
|**Message Persistence**|Messages are not persisted; delivered to subscribers and deleted|Messages can be persisted for 1 minute to 14 days|
|**Delivery Guarantees**|At-least-once delivery with possible duplicates|Exactly-once delivery with deduplication|
How SQS maintains exactly once delivery:
**Visibility Timeout:** When a message is fetched by a consumer, it becomes temporarily invisible to other consumers. This visibility timeout ensures that only one consumer processes the message. If the processing consumer fails to ACK the message within the visibility timeout, SQS assumes the message hasn't been successfully processed and makes it visible again for other consumers.

Q;  Explain how SNS supports fanout and how it can be used to achieve redundancy and fault tolerance in an application.
A:
steps:
1. Create multiple instances of critical components, such as databases or processing services.  
2. Subscribe all instances to the same SNS topic.  
3. Publish messages to the topic when changes occur or actions are required.  
4. Each subscribed instance receives the message, ensuring data consistency and parallel processing.


Q:  What is the purpose of message attributes in Amazon SNS? How do they differ from message body and metadata?
A:
Message attributes in Amazon SNS serve to provide metadata for messages, allowing subscribers to filter and process them. They differ from the message body, which includes information like timestamp and message ID. Message attributes are key-value pairs with data types.

Q: Dead letter queue:
A: Amazon SNS Dead Letter Queue (DLQ) is a feature that helps handle failures in message delivery by redirecting undeliverable messages to a specified queue. This process involves the following steps:
1. Create an Amazon SQS queue to serve as the DLQ.  
2. Configure the SNS topic’s Redrive policy, specifying the ARN of the created SQS queue and maxReceiveCount (number of retries before sending to DLQ).  
3. Publish messages to the SNS topic.

Q: SNS Logs:
A: logs the success and failure of msges, can see failure reasons, latencies.

> uses KMS to encrypt msges

> Supports, HTML, email, sms, sqs lambda protocol

> Amazon SNS integrates with CloudWatch to monitor and alarm on metrics such as NumberOfMessagesPublished, NumberOfNotificationsDelivered, and NumberOfNotificationsFailed


### API Gateway
Amazon API Gateway is a fully managed service helps create, publish, maintain, monitor, and secure APIs. A "front door" for applications to access functionality from your backend services. Can create RESTful APIs and WebSocket APIs that enable real-time two-way communication applications. 

API Gateway handles up to hundreds of thousands of concurrent API calls, including traffic management, CORS support, authorization and access control, throttling, monitoring, and API version management. API Gateway has no minimum fees or startup costs. You pay for the API calls you receive and the amount of data transferred out.
API vs LoadBalancer

|API Gateway|Load Balancer|
|---|---|
|interface b/w backend and client|Distribute Traffic
|application layer|Network Layer

#### Cloudformation:
- CloudFormation is an infrastructure automation platform that eliminates the need for manually setting up resources on cloud.
- Create & integrate services through a single template.

> A stack is a collection of AWS resources that you can manage as a single unit. In other words, you can create, update, or delete a collection of resources by creating, updating, or deleting stacks. All the resources in a stack are defined by the stack's AWS CloudFormation template

> CloudFormation Stacks: to manage and deploy AWS resources within a single AWS account. StackSets: for multiple AWS accounts or regions. In a simple deployment requiring a single AWS account, consider utilising CloudFormation Stacks. But if your deployment is large and complex, applying a CloudFormation StackSet

> Cloudformation roll backs the stack to prvious working state whn deploymnt fails.

Template vs stacks:
Templates are JSON or YAML formatted text files that define the AWS resources to be created and their properties.
Stacks are collections of AWS resources created from templates.

Resource vs Property:
A “Resource” represents an AWS service or component that can be created and managed, while a “Property” defines the configuration settings for a specific resource.
```yml
Resources:
  MyS3Bucket:
    Type: AWS::S3::Bucket
    Properties:
      BucketName: my-example-bucket
      AccessControl: PublicRead
```


Functions vs intrinsic functions and pseudo parameters:
**Functions**: general fcns, to perform various operations and transformations on values or modify values.

**Intrinsic Functions**: Used within your CloudFormation templates to assign values to properties. 
built-in functions provided by AWS CloudFormation. 
can be used  to assign values to properties. 
`Fn::Ref`, `Fn::Join`, `Fn::Sub`, `Fn::If`

**Pseudo Parameters**: predefined by CloudFormation. 
provide information about the stack, resources, and AWS environment. 
used as arguments for intrinsic functions like `Fn::Ref`. 
examples: `AWS::Region`, `AWS::AccountId`, `AWS::StackName`

Q: DependsOn vs GetAtt vs Ref
DependsOn: Ensures that a specific resource is created or deleted before another
Fn::GetAtt: Retrieves the value of an attribute from another resource in the stack.
Ref: References another resource’s logical ID to fetch its value.

Q: Mappings
A: they do allows us to define key value pairs in template.

> Custom resources can be lambda or sns backed, it case of lambda CF sends request to lambda while provisioning the custom resource, lambda contains the logic, in case of sns , http endpoints subscribers receive that.


>Build stage: get cloudformation package
>deploy state: use it to manage stacks

>COnsole and stack events are used to debug.

Json vs YAML:
Yaml: have comments, readible
json: more accepted unviersally, less ambiguis strict structure

> AWS monthly calc to get estimates

> Drift Detection in AWS CloudFormation is a feature that identifies discrepancies between the actual stack resources and expected configurations defined in the stack template.