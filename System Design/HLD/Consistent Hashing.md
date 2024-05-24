Consistent hashing is a technique used in distributed systems to efficiently distribute and manage data across a dynamic set of nodes. It provides a way to map keys (such as data items or tasks) to nodes in a consistent and balanced manner, even when the number of nodes in the system changes.

**Advantages:**

1. **Balanced Distribution:**
    - Consistent hashing ensures a balanced distribution of keys among nodes. When a node joins or leaves the system, only a fraction of the keys needs to be remapped, minimizing the impact on the overall system.
2. **Scalability:**
    - It's particularly beneficial in scenarios where nodes can be added or removed dynamically. When a new node is added, only a portion of the keys is reassigned, and when a node is removed, only the keys associated with that node need to be remapped.
3. **Efficient Query Routing:**
    - Consistent hashing provides an efficient mechanism for routing queries to the appropriate node. Given a key, the system can quickly determine the responsible node without needing to consult a centralized directory.
4. **Load Balancing:**
    - Nodes are spread evenly across the hash ring, promoting a balanced load distribution. This helps prevent hotspots where a disproportionate number of keys are assigned to a specific node.

https://newsletter.systemdesigncodex.com/p/amazon-dynamo-breakdown
It’s a straightforward process:
- We hash the key to determine its position on the hash ring.
- Then, we traverse the ring in **clockwise direction** starting from the position of key until a node is found
- When the node is found, we store the data object on that node.
    
The below illustration makes it crystal clear:
![](https://substackcdn.com/image/fetch/w_1456,c_limit,f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2F1b0a0faa-5f64-4bd2-aa7e-58446705ee0d_2876x1697.png)

As we move clockwise from K1, the first node is A. Hence, K1 is stored on A. Similarly, K2 is stored on B and K3 is stored on C.

To express things even more simply, each node is responsible for the region between it and its predecessor node on the ring. For example, node B is responsible for all the keys falling between node A and node B on the hash ring.

Sounds perfect?

Yes, it does. But the basic consistent hashing has two major downsides:
- First, random position of each node on the ring can result in **non-uniform data distribution**
- Second, it doesn’t take into account the node’s resource capacity. Bigger nodes and smaller nodes are treated in the same way even though they have different storage capacities.
To deal with these problems, Dynamo uses a variant of consistent hashing that applies the concept of **virtual nodes**.

**“What are virtual nodes?” - you may ask.**

With virtual nodes, you don’t map a node to a single point in the circle. Instead, each node gets mapped to multiple points in the ring. These points are also known as **tokens** and are determined using _different_ hash functions.

All of these multiple points are basically virtual nodes pointing to a single physical node.

Check out the below illustration that shows **consistent hashing with virtual nodes**.

![](https://substackcdn.com/image/fetch/w_1456,c_limit,f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2Fcc53e18d-fc05-462e-b783-53892c12a48d_2486x1947.png)

As you can see, each of the nodes A, B and C are mapped to three distinct positions on the ring.

For example, node A is represented with A0, A1 and A2. There are is only one physical node A. But it maps to three virtual nodes.

With virtual nodes, you get a few nice advantages:
- If a node becomes unavailable for some reason or is added again, there is an **even distribution of data**.
- Also, nodes can be assigned more tokens based on server capacity. This way, nodes with higher capacity get more data and smaller ones get less data.