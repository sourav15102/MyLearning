#### Requirements:
1. Multiple buildings
3. Each building has multiple shafts
4. One button at each level.
5. Each shaft diff limit.
6. Car wont accept response when full
9. Car open door only when stopped.
7. Each shaft has live status.
8. Each level shows direction.
9. 200 floors, 50 shafts
10. Are there operational zones.
	1. certain cars cater to certain range of levels.
11. VIP elevators
#### Actors:
1. Elevator system
2. Building
3. Shaft

Attributes:
1. Elevator System
	1. id
	2. Buildings
2. Buildings:
	1. Id
	2. levels array
	3. shafts array
	4. call request queue
3. Shafts
	1. id
	2. capacity
	3. building
	5. curr level
	6. curr direction: UP, Down, Idle
	8. stop request array

Behaviors:
1. Elevator system:
	1. modify buildings
2. Buildings:
	1. modify levels
	2. modify shafts
	3. call request() : Dispatcher
	4. indicatingPosition of a shaft on each level.
3. Shaft
	1. stop request()
	2. move car()
		1. it stops at level it has been requested to stop at, changes its state to idle.
		2. before going into idle it saves its prev dir.
		3. after stopping, it takes prev dir and all requests int consideration before moving again and selecting its new dir.
	3. movedoors()
	4. IndicatePosition() inside car

Call Elevator Algorithm:
1. FCFS:
	1. Put all requests in a queue
	2. serve first re in the queue first.
	3. not optimal, as it cant club together all requests.
2. Shortest Seek Time First
	1. Basically here the car will pick the request closest to it.
	2. Starvation issues can be there as it might happen that car is serving passengers only at 5-8 levels if passengers keep coming in and it ignores all others.
3. SCAN
	1. Here we maintain two boolean arrays for UP and DOWN.
	2. Each car will move to all the way up first and then all the way down.
	3. and on its way, it will see if a corresponding index is set or not, if yes, then it will stop.
	4. Issue: All cars will be constantly moving.
4. LOOK: Look ahead SCAN algorithm
	1. It wont move unnecessarily.
	2. It will look ahead, if there is any requests above only then it will move up.
5. Destination dispatch algo:
	1. 


Final Algo:
1. Whenever a request comes, the dispatcher can scan through all cars which are coming in floor's dir, and assign the request to the closest car.


