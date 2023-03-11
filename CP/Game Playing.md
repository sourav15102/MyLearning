
### Prerequisites 
- [[Dynamic Programming]] 

keep to things in mind: 
1) when calling the recurse function, if other player wins then you loose and vice-versa, assuming players play optmially. 
2) remeber to handle case where both loose, or both cant win, in your program may think that since other player lost, you won, but you didn't. 
3) remeber to handle case where you won and called recursive function and other player might think they won, hence you lost.

Questions:
1. https://leetcode.com/problems/can-i-win/description/?q=time+complexity&orderBy=most_relevant 
2. https://leetcode.com/problems/guess-number-higher-or-lower-ii/description/
3. https://leetcode.com/problems/predict-the-winner/description/