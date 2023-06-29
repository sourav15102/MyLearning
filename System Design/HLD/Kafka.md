It is a "Distributed event streaming platform"

Link: https://www.youtube.com/playlist?list=PLa7VYi0yPIH2PelhRHoFR5iQgflg-y6JA
Watch from 3rd video onwards

[[When to use Message queue]]
[[Pubsub vs queue]]


## Kafka:(https://www.youtube.com/watch?v=LN_HcJVbySw)
- it is used when we want to process/analyse live stream of data.
- how would you do it? we have a consumer lets say a website that displays live stock prices.
- we have a producer that has the data for stcok prices for companies.
- now if consumer were to talk directly to producer, the consumer would have to make a call to api where it would get data process it, see which data it displayed previously and what next update it needs.
- it can be combursome, we can have a queue in between, producer can push to queue and consumer can read from that queue, it doesnt need to qorry about what was next data iot wanted to publish cos of FIFO of queue.
- now what if producer pushes a lot to queue and ir becomes large, then what we do we do [[Sharding]],  i.e. logical partition of data, in kafka we call it **Partition**, so a each **topic**, each topic(queue) to which the producer is pusing can be called a topic, each topic ca have multiple partitioning, now producer can push to a topic and to a certain partition, same for consumer, they need to pull from topic-partition.
- we can tink of partition as independent queues, for example a match between ind vs pak can be in one partiton and aus vs nez can be in another, cos they are independent and update on one wont affect update on another.
- No we have a problem that a consumer needs to be aware of the partiton, we dont want that..
- answer is 


**Consumer group**: it solves the issue of consumer awareness of partition plus allows parallel procesing of partitions. 
- how? one consumer need to be consumed with atleast one partition
- and if there is one consumer in consumer group, then it would consume all partitions.
- for example if we have 3 partitoons, and 3 consumers in consumer group, each consumer will consume one partition.
- and now we can consume these consumers parallely.
- i you want to act like queue: put all consumers in one group.
- if want to act like pub sub put all in unique groups.