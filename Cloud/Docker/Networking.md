- every container we spin-up gets added to the "bridge" network.
- so, by default any container can access another by IP.
- however, we can't access the ip of con containers directly from pc cos there is no bridge.
	- unless we have published our port 8080:8080 to some other port of container.
- but one container cannot call another by hostname.. why?

#### Hostname concept with docker:
![[Pasted image 20230517142017.png]]

- all containers in bridge network has IP.
- bridge network has a gateway using which containers can be used to call DNS when doing curl google.com
	- so, if we call google.com from container, it will first go to DNS hosted by docker which is outside the network.
	- it will first got to gateway --> DNS
- But, if we try to call by hostname of another container in same network, it will first go to gateway --> DNS, but DNS doesnt know the container by name since the DNS is outside the network.

#### How to fix that? By creating a network
```bash
docker network create backend --subnet 10.0.0.0/24
```
then connect both containers to this network:
```bash
docker network connect backend s1
docker network connect backend s2
```
now the containers are added to two networks.
Good practice: should remove containers from bridge network.
```bash
docker network disconnect backend s1
```






