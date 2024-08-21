### In-Memory Message Brokers

**Characteristics**:
1. **Volatile Storage**: In-memory brokers store messages in RAM, which means they are volatile and typically lose all data when the broker restarts or crashes.
2. **Ephemeral State**: The state in in-memory brokers is transient. Messages exist only as long as the broker's process is running, and there is no built-in persistence beyond memory.
3. **High Throughput and Low Latency**: Due to storage in RAM, these brokers can handle a high volume of messages with low latency, making them suitable for real-time systems where speed is critical, and data durability is not a concern.

**State Implications**:
- **No Durable State**: Since messages are not persisted, there's no durable state to recover after a failure. Once a message is processed or lost due to failure, it cannot be retrieved.
- **Usage Scenarios**: Used in scenarios where losing messages isn't critical, such as in real-time data processing pipelines, volatile state management, and systems with independent message sources.

### Log-Based Message Brokers

**Characteristics**:
1. **Persistent Storage**: Log-based brokers write messages to a persistent storage medium, often a disk. Each message is appended to a log file, which can be replayed or read at any time.
2. **Durable State**: The state in log-based brokers is persistent, meaning the message log retains all messages, allowing consumers to re-read or replay messages from a specific point in time.
3. **Message Replay**: Consumers can process messages at their own pace, rewind to reprocess, or skip ahead, offering flexibility in message consumption.

**State Implications**:
- **Durable State**: State is maintained persistently, even across broker restarts or crashes. This durability ensures that messages are not lost and can be processed reliably.
- **Auditability and Replay**: The log's persistent nature allows for auditability, debugging, and reprocessing of messages, making it suitable for event sourcing, data pipelines, and systems requiring message recovery.
- **Usage Scenarios**: Ideal for systems needing durable state, such as financial transactions, audit logs, data stream processing, and any application where message integrity and traceability are crucial.

### Key Differences

1. **Data Retention and Persistence**:
   - **In-Memory**: Short-lived, no persistent state. Data is lost on restart or failure.
   - **Log-Based**: Long-lived, durable state. Data is retained even after restarts or failures.

2. **State Management**:
   - **In-Memory**: Manages ephemeral state, ideal for fast, transient data processing.
   - **Log-Based**: Manages persistent state, allowing consumers to access the history of messages.

3. **Performance vs. Durability**:
   - **In-Memory**: Prioritizes performance (speed and low latency) over durability.
   - **Log-Based**: Balances performance with durability, offering data persistence and reliability.

### When to use what:
**Each type of message queue performs better in specific use cases:**
##### In memory:
- **Want maximum throughput, order of event processing doesn't matter!**
  - Users posting videos to YouTube and they want to encode them.
  - Users posting tweets that will be sent to "news feed caches" of followers.
##### Log based:
- **Want all items in queue to be handled by one consumer, in order, ability to replay**
  - Sensor metrics coming in, we want to take the average of the last 20.
  - Each write from a database that we will put in a search index.
