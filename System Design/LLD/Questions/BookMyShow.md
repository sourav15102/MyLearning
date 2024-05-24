1. Functional requirements
	1. Apps asks for location.
	2. Filter by city, theater, movie, language, time
	3. Can search movie by name.
	4. Can select theater and time.
	5. Can see 10  popular movies by city.
	6. Can select seats
	7. payment option
	8. Generate ticket.
	9. Can view tickets.
	10. Anonymous users can browse but booking required logged in users.
2. Actors
	4. Admin
	5. Theaters
	6. Users
3. Entities
	1. Admin
	2. Cinema
		1. Seats
		2. Shows
		3. Theaters
	3. Users
	4. Movies
		1. Shows

Non-Functional Reqs:
1. Scalable
2. Flexible
3. Reliable
4. [[Concurrency]]

### Implementation:
- We can use Decorator pattern for filters:
	- Can keep 'filtering all cinemas by citites' as base decorator.
	- then apply filtering by others as flavours in case of decorator pattern
- After filtered list of Cinemas, can traverse through their shows for unique set of movies to show.
- Once Clicked on movie, can browse through its shows to display all Cinemas in that city.
- Once Clicked on Cinema, can browse through that particular Cinemas shows to get all shows pertaining to that Movie.
- Proxy pattern can be used for booking, Booking proxy cah check if user is anonymous, if not can proceed, if it is, then asks it to login.signup and then book.
- Strategy can be used for payments.