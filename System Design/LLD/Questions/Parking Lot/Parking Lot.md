#### Requirements:
1. **Different Entrances and Exits for Each Building:**
    
    - Each building should have its own designated entrances and exits to facilitate traffic flow and organization within the parking lot system.
2. **One Company Has Many Buildings:**
    
    - The system should support multiple buildings owned by the same company, allowing centralized management and administration of parking facilities across different locations.
3. **Each Building Has Levels:**
    
    - Buildings may have multiple levels or floors to accommodate parking on different levels, such as ground level, basement, or rooftop parking.
4. **Each Level Has Spots:**
    
    - Each level within a building should contain a certain number of parking spots to accommodate vehicles. These spots may vary in size, type, and accessibility.
5. **Different Types of Spots:**
    
    - The parking lot should support various types of parking spots, such as regular spots, handicapped spots, compact spots, electric vehicle charging spots, etc.
6. **Different Payment Methods:**
    
    - The system should accept multiple payment methods for parking fees, including cash, credit/debit cards, mobile payments, and prepaid parking passes.
7. **Payment Depends on Hours and Type of Vehicle:**
    
    - The parking fee structure should consider factors such as the duration of parking and the type of vehicle (e.g., car, motorcycle, truck). Different rates may apply based on these factors.
8. **Different Strategies for Finding Free Spots:**
    
    - Implement various strategies for efficiently finding available parking spots, such as nearest available spot, optimal parking location based on vehicle size, or priority parking for specific vehicle types.
9. **Parking Guidance System:**
    
    - Integrate a parking guidance system that provides real-time information on available parking spots and guides drivers to the nearest vacant spot using digital signage or mobile apps.

Actors:
1. Parking Lot Company
2. Buildings
3. Spot
4. Entrances/Exits
5. Notification System
6. Ticket
7. Payment System

Non-Functional Reqs:
1. Scalable
2. Flexible
3. Reliable
4. [[Concurrency]]


Flow:
1. There can be strategy pattern for:
	1. Check in system : diff strategies for finding spot.
	2. Payment methods
2. Decorator Pattern:
	1. Notification system: user can ask for multiple and combination of many.
3. Singleton Pattern:
	1. only one object for company's object.

Q: If we have multiple entries, then there would be concurrency issue, cos we might be trying to book same free spot for two diff ppl. how to tackle that
A:
- **Locking Mechanisms:**
    - Implement locking mechanisms to ensure that only one entry can access and modify the state of a parking spot at a time. For example, you can use mutex locks or semaphores to synchronize access to shared resources and prevent concurrent modifications.
-  **Queueing Systems:**
    - Use queueing systems such as message brokers or task queues to serialize access to shared resources. Entries can enqueue booking requests, and a dedicated worker process dequeues and processes requests sequentially, ensuring data consistency.


## Github:
# Designing a Parking Lot System
## Requirements
1. The parking lot should have multiple levels, each level with a certain number of parking spots.
2. The parking lot should support different types of vehicles, such as cars, motorcycles, and trucks.
3. Each parking spot should be able to accommodate a specific type of vehicle.
4. The system should assign a parking spot to a vehicle upon entry and release it when the vehicle exits.
5. The system should track the availability of parking spots and provide real-time information to customers.
6. The system should handle multiple entry and exit points and support concurrent access.
### Java Implementation 
