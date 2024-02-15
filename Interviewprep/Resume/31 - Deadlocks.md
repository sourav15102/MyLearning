Deadlock occurs when two threads block each other forever because they are waiting for the other thread to relinquish the lock
The following conditions must be met for a deadlock to occur:
- At least one resource must be mutually exclusive (like a mutex) so that only one thread can access it simultaneously.
- **Hold and wait**: A thread must hold onto one resource while it waits for another to come along.
- **No preemption**: You cannot forcibly remove a lock on a resource once it has been acquired by a thread (i.e., the lock cannot be preempted).
- **Circular wait**: Each thread must wait on another for a resource in a circular fashion.


How to avoid:
1.  Using Thread.join()
	To guarantee that one thread finishes before starting another.

2.  Using Synchronization Objects
	Deadlock can be avoided by synchronization and using synchronization primitives. Using synchronization objects, like mutexes or semaphores, is another way to prevent deadlock.  Always ensure that synchronized blocks are used in a fixed order to avoid deadlocks in Java. This means that if multiple threads are trying to access the same resources.

3. Avoid Nested Locks
	Developers can also avoid deadlocks by avoiding nested locks, i.e., by avoiding acquiring another lock when a lock on an object has already been acquired. 

4. Timeout
	implementing timeout policies for acquiring locks and ensuring that resources are accessed in the same order across different threads.

References:
1. https://www.developer.com/java/java-prevent-thread-deadlock/