https://newsletter.systemdesigncodex.com/p/why-replication-lag-occurs-in-databases

In case of asynchronous replication, the leader node which accepts write sends replication msg to all read nodes, this delay between a write happening on the leader and being reflected on a follower node is known as the **Replication Lag**., which is eventual consistency.