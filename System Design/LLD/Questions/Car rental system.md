1. Functional requirements
	1. User can registre/login
	2. User can rent car selecting its pickup and drop off time/date.
	3. User can pay in multiple ways.
	4. Extra charges levied for overtime.
	5. User needs to be approved by uploading required docs.
	6. User can filter my make, time, date.
	7. User needs to lock docs before pickup and release them after drop-off.
	8. User will be notified at each stage.

3. Actors:
	1. Admin
	2. User
	3. Rental worker
4. Entities
	1. User
	2. Rental locations.
		1. Vehicles: types: Normal, XL, XXL, SUV
	3. Booking: chain of responsibility
			1. stage: StageHandler
	4. Admin
	5. Invoice
	6. PaymentMethod: types: credit, debit, UPI: Strategy
	7. Notification Service: Email, Msg, Whatsapp (any combination): Decorator
	8. StageHandler: DocHandler, PaymentHandler, PickUp Handler, DropOffHandler, DocReleaseHandler
5. Implementation
	1. BookingService(User, Vehicle, Start, end)
	2. InvoiceService(Vehcile, start, end)
	3. PaymentService(User, Invoice, PaymentMethod)

Non-Functional Reqs:
1. Scalable
2. Flexible
3. Reliable