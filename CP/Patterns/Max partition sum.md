Question:
For a given array `nums`, can we divide it into `k` partitions, such that maximum of all partition's sum is `<=` m.

Example:
`nums = [7,2,5,8,10]`
`k = 3`
`m = 27`

one of many possible partition is: `7,2` and `8,5` and `10`, then the maximum sum is 13 which is <=27.
*We just need to find that for all possible `k` partitions of `nums` is there a set of `k` partitions where the maximum of partition's sum <= m*


### Idea:
the idea is something like `kadane's` algorithm, where we can traverse from left to right until the sum we encounter is <=m, the moment it goes above, m, we start a new subarray.

at the end, if number of subarrays we had to start again goes beyond k, that would indicate that such partition is not possible.

