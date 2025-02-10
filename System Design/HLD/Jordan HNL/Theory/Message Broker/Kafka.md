
Kafka producer:
A producer can be any server pushing data to kafka cluster ().

Kafka Cluster:
a group of Kafka server or brokers

Kafka Topic:
A topic is a stream in which multiple producers can push messages to.

Kafka partition:
A topic can be partitioned and multiple brokers can handle them, one broker can handle one partition of each topic.
> Partition size is decided by us while making Topic.

Offset:
It is an id with each message and is local to the partition. They increment as the messages arrive to partition.

Consumer group:
A group of consumers sharing the workload of consuming messages of a topic.
Each partition can be read by only one consumer, however, a consumer can read multiple partitions.

#### Kafka Connect:
It can be used to connect to db for importing/exporting data from/to a db.
We need kafka connectors framework where we need to implement a few classes which will decided how to get/push data from/to db and what to do with it.
#### Why Kafka with Flink
Flink is commonly used with Kafka because Kafka serves as a robust, scalable, and fault-tolerant messaging system that complements Flink's strengths in stream processing. Here's why this combination is preferred:

### 1. **Decoupling Data Production and Consumption**

Kafka acts as a buffer between data producers and consumers, allowing each to operate independently and at their own pace. This decoupling provides several benefits:

- **Scalability**: Kafka can handle high throughput, making it ideal for use cases where large volumes of data are generated. It can manage the data ingestion and ensure that Flink can process it at a sustainable rate, even if the data production rate fluctuates.

- **Fault Tolerance**: Kafka provides durable storage, meaning data is not lost if a consumer (like Flink) temporarily goes down. It guarantees that messages can be replayed and consumed once the consumer is back online, ensuring reliable data processing.

### 2. **Stream Retention and Replayability**

- **Data Retention**: Kafka retains messages for a configurable amount of time. This retention allows consumers like Flink to reprocess data if needed, such as for debugging, auditing, or adapting to new business logic.

- **Replayability**: Kafka's ability to replay streams is essential for ensuring exactly-once or at-least-once processing guarantees. If Flink encounters an issue or needs to reprocess data, it can replay the necessary messages from Kafka.

### 3. **Handling Multiple Consumers**

- **Multiple Consumer Groups**: Kafka supports multiple consumer groups, allowing different applications or instances of Flink jobs to consume the same stream of messages independently. This feature is useful for different processing needs, like real-time analytics and alerting.

- **Scaling Consumers**: Kafka's partitioning model allows Flink to scale horizontally by consuming from multiple partitions in parallel. Each partition can be consumed by a different Flink task, enhancing parallelism and processing throughput.

### 4. **Durable Event Storage**

Kafka provides durable storage for streams, ensuring data integrity and reliability. This is particularly valuable for systems that require strong consistency and need to process data exactly once. Flink can leverage Kafka's durable storage to recover from failures and continue processing without data loss.

### 5. **Integration with Other Systems**

Kafka serves as a central hub for data streams, integrating well with other systems such as databases, monitoring tools, and data lakes. This ecosystem integration allows Flink to pull in data from diverse sources and push processed results to various sinks.

### 6. **Event Ordering and Log Compaction**

- **Ordering Guarantees**: Kafka guarantees order within partitions, which is crucial for processing time-sensitive data streams correctly. Flink can leverage these ordering guarantees to implement stateful stream processing accurately.

- **Log Compaction**: Kafka's log compaction feature ensures that the most recent updates for each key are retained, providing an efficient way to manage large data streams with changing states.

### Direct Pushing vs. Kafka Integration

While it is technically possible to push data directly to Flink, using Kafka offers the following advantages:

- **Data Persistence**: Without Kafka, data loss can occur if Flink or the network goes down. Kafka ensures data is stored persistently.
- **Scalability**: Kafka can manage data ingestion at scale and distribute load across multiple Flink instances.
- **Flexibility**: Kafka decouples the data pipeline, making it easier to modify or extend the system without disrupting the data flow.

In summary, Kafka provides a durable, scalable, and reliable message queue that complements Flink's capabilities in stream processing. This combination allows for robust, fault-tolerant, and scalable data processing architectures.


### Numbers
1. 1MB per message limit

| **Tool**    | **Suitable TPS Range**      | **Scaling Capability**            | **Best For**                                         |
| ----------- | --------------------------- | --------------------------------- | ---------------------------------------------------- |
| **Kafka**   | **1M+ TPS (per cluster)**   | Scales horizontally (add brokers) | High-throughput, large-scale data pipelines, events. |


