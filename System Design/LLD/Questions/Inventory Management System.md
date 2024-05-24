1. Functional requirements
	1. onboard products.
	2. Query products
	3. Filter by seller, price.
	4. update quantity.
	5. Notify if products are below threshold.
	6. Product placement: shelve
		1. upper shelf or lower shelve based on size S, M, L
	7. State of the unit: Idle, Ordered, Packaged, ready to be shipped.
2. Actors: 
	2. Employee: add products, update products, Query products, filter, view
	3. Seller:  product to inventory
	4. Robots: Product onboarding, product dispatch
3. Entities:
	1. Inventory: ProductOnboarder, ProductLocator, ProductDispatcher, DOA, NotifyList
	2. Employee
	3. Product: Name, quantity
	4. Unit: id, Product, Seller, Price, Size, State, shelve
	5. Seller: Name
	6. Shelve: id, Size
	7. ProductOnboarder: onboard-update DOA, place 
	8. ProductLocator
	9. ProductDispatcher
	10. DOA
4. Implementation

Non-Functional Reqs:
1. Scalable
2. Flexible
3. Reliable

Principles:
1. DRY: Do not repeat yourself.
2. KISS: Keep it simple stupid
3. YAGNI: You aren't gonna need it.