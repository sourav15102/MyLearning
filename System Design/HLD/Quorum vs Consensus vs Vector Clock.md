https://stackoverflow.com/questions/73377599/quorum-vs-consensus-vs-vector-clock

Problem1: I'd like to make a decision involving specific number of nodes. We will call that number - quorum. For example, in leaderless replication based on Dynamo, quorum is a number of nodes representing a majority.

To be clear, quorum does not have to be a majority - it all depends on specifics of the problem. E.g. you could say something like - in system X a quorum is a set of three oldest nodes.

Problem2: We have multiple nodes, we want them all to agree on something - we want nodes to get to a Consensus on a specific decision. E.g. there are 10 numbers (0..9) and 100 nodes. We want them all to pick the same number. So, the consensus is a general idea of agreement on something. Common algorithms are Paxos, Raft, etc.

Problem 3: I have a distributed system which processes events on each node. Some of those events will be concurrent to each other. How do I detect those? I'll use version clock for that.

Problem 4: I have several replicas of some data. These replicas may process some events locally and also synchronize to each other. When I do synchronize, how do I know which replica is more recent? And how do I detect if replicas have conflicting data? I'll use version vector for this.