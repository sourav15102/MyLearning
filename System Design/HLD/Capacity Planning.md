 Capacity Planning
	5. QPS (Query per second) (read and write)
	6. bandwidth: `QPS X size of obj`
	7. RAM needed
	8. Storage need (lets say for 5 years)
	9. Servers needed
	10. CAP tradeoff.

##### Step 1: Traffic estimate:
1. Total users: X
2. DAU (Daily Active users): 25% of X = Y
3. Every user making
	1. Read: a
	2. write: b
4. Query per second: (Y * (a+b))/(`24*60*60`) = Z
	1. read QPS
	2. write QPS

##### Step 2: Storage estimate:
- Calculate everything by day/month: how much capacity we need in a day/month
- Then, calculate ow much storage would be need if we were to store it for 5-10 yrs.
- Calculate it diff for lets say text, data or images/videos (block storage)

##### Step 3: Memory estimate:
- Lets say for each DAU, I am saving last 5 posts.
- Calculate how much RAM is needed for a day.
- 1 machine can contain 75GB of data in ram. (running at 50% efficiency)
##### Step 4: Latency Bandwidth estimate:
- latency: One request takes 500ms to be completed (1 sec  - 2 requests)
- 1 server: 50 threads, so 1 sec = 100 requests.
- QPS = Z, Z/100 would be number of servers needed
- 1 Post request has OZ size of obj. 
	- QPS for write is b, then `b*OZ`.

##### Step 5: CAP tradeoff


