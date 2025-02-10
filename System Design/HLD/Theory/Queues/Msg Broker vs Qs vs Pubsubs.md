Message brokers offer two basic message distribution patterns or messaging styles:

- **[Point-to-Point messaging](https://karanpratapsingh.com/courses/system-design/message-queues)**: This is the distribution pattern utilized in message queues with a one-to-one relationship between the message's sender and receiver.
- **[Publish-Subscribe messaging](https://karanpratapsingh.com/courses/system-design/publish-subscribe)**: In this message distribution pattern, often referred to as _"pub/sub"_, the producer of each message publishes it to a topic, and multiple message consumers subscribe to topics from which they want to receive messages.


Broker vs Queues:
A message broker (also know a service bus) is a piece of middleware responsible with persisting and routing of message while allowing you to decouple your system into smaller parts. A message queue is a part of a message broker and is just a persistence mechanism.

In order to provide reliable message storage and guaranteed delivery, message brokers often rely on a substructure or component called a **message queue** that stores and orders the messages until the consuming applications can process them