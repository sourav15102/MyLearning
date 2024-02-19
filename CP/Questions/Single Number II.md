Question:
**Given an array of integers, every element appears three times except for one. Find that single one. Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?**

Answer:
The logic for this approach is to think the numbers in bits and then add all the bits of each position. So after adding you will see that the sum is either multiple of 3 or multiple of 3 + 1 (Because the other number is occurring once only). After this, if you do a modulo on this sum you will have the result. You will understand better with the example.  
  
**Example**: Array - [5, 5, 5, 6]  
 5 represented in bits: 101  
 6 represented in bits: 110  
  
 [ 101, 101, 101, 110] (Binary Reprenstation of values)  
 After adding to particular positions, we will have  
  0th -> 3, 1th -> 1,  2nd -> 4  
 and if you mod by 3 it will become  
  0th -> 0,  1th -> 1, 2nd -> 1  
 which in decimal representation is our answer 6.  
Now we need to code the same. I have explained the code using comments.

