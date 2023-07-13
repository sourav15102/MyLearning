There can be two kind of proxies:
1. Forward proxy
	1. It stands in front of client and "act" as client for server.
	2. server doesnt know whats going on behind the scenes.
	3. client and proxy can communicate.
2. Reverse proxy.
	1. It stands in front of server and "act" as server for client.
	2. client doesnt know whats going on behind the scenes.
	3. server and proxy can communicate.
	4. It can serve many other puproses:
		1. like balancing load: load balancers
		2. filter requests