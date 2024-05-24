1. Functional requirements
	1. User can search any upcoming/current/previous cricket tournaments.
	2. User can search any player and see their stats.
	3. For tournaments, user can see the fixtures.
	4. For previous match, Players can see all the stats.
	5. For current/upcoming match: user can see live updates.
2. Actors: Use Case Diagram
	1. Admin: can CRUD on tournaments, matches, players, push live updates.
	2. Viewer: View commentary.
	3. Commentator: A/M/View commentary 
3. Entities: Class Diagram
	1. Player: Name, total runs, total wickets, teams
	2. Team: Name, Players, Country, Coaches, Tournaments.
	3. Match
		1. Ball
			1. Run
			2. Result: OK, Wicket, WIDE, NOBALL (BallResult)
			3. Bowler
		2. Over
			2. index
			3. Bowl: list
		3. MoM
		4. Location
		5. Date
		6. Teams
		7. PlayingPlayers
		8. Commentary
	4. Tournament
		1. MoS
		2. Winner
		3. Teams
		4. Matches
		5. Commentary
	5. Location
	6. Stats: interface
		1. Match, Player, Tournament
4. Implementation: Activity Diagram
	Two major things to focus on:
		1. Ball-by-ball commentary: A/M/V commentary: can use builder payter
		2. Stats: A/M/V stats, while searching for it from user end, one can use Chain responsibility pattern to see if the search entity is Player, then execute its flow, if tournament, then execute tournament flow.

Non-Functional Reqs:
1. Scalable
2. Flexible
3. Reliable
