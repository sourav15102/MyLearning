1. Functional requirements
	1. User can add other users using emailid.
	2. User can add money with another user.
		1. paid by x,y.
		2. Split/all
	3. User can create groups.
		1. Add money to group, by selecting members.
		2. paid by user
		3. divided among list
	4. user can add money in any currency.
	5. user can settle.
	6. User can see result at top, at grp lvl, at friend lvl.
	7. User can see history
	8. User can normalize.
2. Actors
	1. Admin
	2. Users
3. Entities
	1. User: Name, List Groups, List of transactions
		1. Friends is also a group of two.
	2. Transaction
		1. Map of user vs owe
	3. Groups
	4. Splitter
		1. has Group
	5. GroupCreated
	6. CreateFriend
	7. To see total at user level, go through all txn by user
	8. To see total at Friend/Group level, go through txn by that group for that user
	9. Normalize: 
4. Implementation

