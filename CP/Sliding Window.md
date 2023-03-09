Link: https://leetcode.com/tag/sliding-window/

### Problems:
- ### Basic
	- https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/
- ### Good
	- https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/
		- try to think about when the string be consider a valid string.
		- in this case, whenever we have atleast one count of each character(a,b,c), will be considered a string.
		- so, the program will go the following way:
		- move j unless you have a valid string.
		- once you have a valid string, start moving i, until you again have an invalid string
		- HOWEVER, the catch specific to this problem is:
		- if i-->j is valid string, then i-->(j+1) will be valid automatically, i-->(j+2) will be valid as well.
	- 

