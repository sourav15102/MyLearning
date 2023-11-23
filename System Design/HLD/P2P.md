Imagine a scenario where we need to transfer/deploy large files/file to multiple receivers repeatedly.

Even with a seemingly extremely high network throughput, this can be problematic. As there will be a few bunch of machines transferring the large files to 1000's of other machines. Those few servers can become bottleneck.

Solution: P2P
Here, a file can be divided in numerous very small pieces(numbered), and those pieces can be distributed to all receivers (peers); and then we can let all the peers talk to each other to exchange the missing pieces. Therefore, via P2P method we can actually transfer the large file parallelly. 

Peer connection:
After we let peers communicate with each other, how can one peer know which peer to talk to next. There are few ways to achieve this:
1. Central db: this machine would know which peer a particular peer talk to next. It means whenever a peer is talking another, they also communicate that with this central DB, to let it know.. not such a good solution.
2. Gossip/epidemic protocol: It kinds of mimics how people gossip, each peer has DHT(distributed hash table) which has the mappings of whatever chunks a particular peer has and what chunks a peer which it has talked to has, and thats how the DHT or information about chunks spread. 

checkout [Kraken](https://github.com/uber/kraken)
