
Capacity estimation:
1. we  have 20 service to rate limit.
2. We have 1B users
3. for each user we need to maintain count.
4. db schema could be userID/IP address(8bytes) -> count(4 bytes) + metadata(8bytes)
5. `1B*20*(8+4+8)`

### what to rate limit on:
1. User ID: wont work in case of login service, as it doesnt have user ID.
2. IP address

### Where to store rate limiting data:
1. We might perform rate limiting data on each service's servers. 
	1. PRO: No extra network call. 
	2. CON: But it would have scaling issues has we might not able to store all RL data on one service server(assuming we have one), also if we are scaling service, then we might need to replicate RL data as well.
2. Have a separate RL service:
	1. PRO: Can scale independently.
	2. CON: extra network call
		1. We have have a write back cache on Load balancer to counter it.

### DB to use:
1. DB read/write might be expensive.
2. WE can use in-memory DB: Redis/Memcache
###### Replication:
- We need to use single leader replication, as if two diff reads to go two diff nodes, they might read data before `anti-entropy`

#### Algos for RL:
- Single window:
- We can have a Service -> UserID -> tuple mapping, where a tuple would contain the xth window count. if the new request falls outside xth window, it update the tuple to x+1th -> 1 count.
- ![[Pasted image 20240820213851.png]]
- ![[Pasted image 20240820213908.png]]

- Sliding window:
- We will use linked list here, every time a new request comes in, we will start purging the elements in linked list which are expired (fall out of the window) starting from oldest to recent.
- and then try to add new element if it doesnt cross the count allowed.
- ![[Pasted image 20240820214050.png]]
- ![[Pasted image 20240820214106.png]]


#### Locking:
- even with the single leader replication, we will need locking to avoid update loss. Because two threads might try to update the count or add new node to linked list at once. 

### Algos:
1. Fixed window 
2. Sliding window


![[Pasted image 20240820212655.png]]