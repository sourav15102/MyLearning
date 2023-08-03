So, lets say we are using in-memory caching and we have load balancer in front which might map the same request to diff servers multiple times leading to cache-miss.
If we had a way to decide which server to map the request to:
1. mod way: simple hashing, we just hash the request to a number and % it with the number of servers.
	1. ISSUE: this will produce cache-miss if we scale up or down the servers.
2. Consistent hashing: we hash the servers on a circle (conceptually), then we hash the request on that circle and choose the first server we encounter going clockwise(or anti-clockwise).
	1. We need to choose hashing algo that evenly distributes the servers across the circle.
	2. if one of our server is more powerful than others and can handle more traffic, we can have more instances of that server on the circle, by hashing it with multiple algos.
	3. It is consistent and scalable.
3. Rendezvous hashing: for each request, we iterate over the servers and calculate the highest scored server for that request and go with it.
	1. It is consistent and scalable.