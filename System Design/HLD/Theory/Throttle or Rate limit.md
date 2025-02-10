https://www.youtube.com/watch?v=CW4gVlU0xtU&list=PLsdq-3Z1EPT27BuTnJ_trF7BsaTpYLqst&index=33&pp=iAQB

Crux: We use rate limiter for throttling requests.

Kinds:
1. External: Normal ones where we throttle requests coming from users directly to your servers, lets say to protect DDoS attacks.
2. Internal: Used internally in systems lets say where we want to know whether a particular user has crossed its limit according to their pay scale, or lets say company is making an expensive 3d party call and I want to see if its crosses my budget.