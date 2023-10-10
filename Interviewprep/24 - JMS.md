
JMS (Java Message Service) is an API that provides the facility to create, send and read messages. It provides loosely coupled, reliable and asynchronous communication.

JMS is also known as a messaging service.

1) **Asynchronous:** To receive the message, client is not required to send request. Message will arrive automatically to the client.

2) **Reliable:** It provides assurance that message is delivered.


There are two types of messaging domains in JMS.

1. Point-to-Point Messaging Domain
	1. In PTP model, one message is **delivered to one receiver** only. Here, **Queue** is used as a message oriented middleware (MOM).
	2. The Queue is responsible to hold the message until receiver is ready.
2. Publisher/Subscriber Messaging Domain
	1. In Pub/Sub model, one message is **delivered to all the subscribers**. It is like broadcasting. Here, **Topic** is used as a message oriented middleware that is responsible to hold and deliver messages.
	2. In PTP model, there is **timing dependency** between publisher and subscriber.
	3. Subscribers need to be actively listening to receive messages as they are published