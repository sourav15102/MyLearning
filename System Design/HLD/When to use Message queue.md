When to use it?

Link: https://www.youtube.com/watch?v=W4_aGb_MOls
### Prerequisites 
- [[Sync vs Async]]
- [[My learnings/System Design/HLD/Load Balancer]]
- [[Consistent Hashing]]
- [[Heart-beat mechanism]]
- 

## Messaging queue:
- if we want to have all of the above functionality in one system, that would be a message queue.
- it basically takes stream of coming requests store it in a queue, assign it to servers without duplication.
- it queue is stored in persistent db.