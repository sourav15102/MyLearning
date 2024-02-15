https://systemdesign.one/leaderboard-system-design/

#### Questions to ask the Interviewer
##### Candidate
1. What are the primary use cases of the system?
2. Are the clients distributed across the globe?
3. What is the amount of Daily Active Users (DAU) for writes?
4. What is the anticipated read: write ratio?
5. Should the leaderboard be available in real-time?
##### Interviewer
1. Update the score and display the leaderboard
2. Yes
3. 50 million DAU
4. 5: 1
5. Yes

RPC vs REST
The best practice to expose public APIs is through REST because of the loose coupling and the easiness to debug. Once the services harden and performance should be tuned further, switch to RPC for internal communications between services. The tradeoffs of RPC are tight coupling and difficulty in debugging.

As long as the number of concurrent players in the game remains low to moderate (below 100 thousand), the relational database can provide sufficient leaderboard functionality. However, as the concurrent game access increases, the relational data schema becomes non-trivial to [scale](https://newsletter.systemdesign.one/p/scalable-software-architecture). The batch mode generation and result caching of the leaderboard reduce recurring computation impact at the expense of user experience[6](https://systemdesign.one/leaderboard-system-design/#fn:6).

### Type of data store
An in-memory database such as Redis can be used to build a scalable and performant real-time leaderboard. The sorted set data type in Redis offers in-memory and responsive access to the requirements of the leaderboard in logarithmic time complexity, O(log(n)), where n is the number of elements in the sorted set [8](https://systemdesign.one/leaderboard-system-design/#fn:8). The sorted set data type in Redis contains a set of **items** (players) with associated scores, which are used to rank the items in ascending order.