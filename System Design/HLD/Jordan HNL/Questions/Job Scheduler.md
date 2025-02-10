1. Workers calculation [[Capacity Estimation]]
2. Object store for input/output
3. coordinate which machines are available. 
4. Dags/crons
	1. epocf

![[Pasted image 20241218203918.png]]

Simple:
![[Pasted image 20241218202613.png]]

1. Here We are sending jobs, they are getting processed as soon as they can be and the status s being update asap.

Scheduled Jobs:
![[Pasted image 20241218203512.png]]
- We have spearate db with scheduled time for job.
- scheduler can keep polling from it and if its jobs time to get worked on, it pushes to kafka.


DAG Jobs:
