https://www.lintcode.com/problem/1897/description

Sol link: https://www.lintcode.com/problem/1897/solution/18279

Idea:
- So the idea here is that between the range of all the intervals we need to figure out at each point whether there is a room available or not.
- Since each query or "ask" Will be treated independently we just need to see at each point whether there is room for just  one interval or not.
- So if one represents at least one domain available and zero represents no room available here is the following representation:
	- 1 0 1 1 0 1 1 1 0 0 1
- so now for interval `[x,y)`, So now from x to y-1, We need to figure out if the rooms are available.