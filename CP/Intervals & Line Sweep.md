- Line sweep algorithm
- Segment tree

### Theory:
https://www.techinterviewhandbook.org/algorithms/interval/
#### Checking if two intervals overlap
Be familiar with writing code to check if two intervals overlap.
```python
def is_overlap(a, b):  
	return a[0] < b[1] and b[0] < a[1]
```
#### Merging two intervals
```python
def merge_overlapping_intervals(a, b):  
	return [min(a[0], b[0]), max(a[1], b[1])]
```

Questions:
1. [[57]]
2. [[56]]
3. [[435]]**
10. [[Meeting Rooms II]]***
11. [[1851]]** (pq and intervals)

### Line Sweep
https://www.youtube.com/watch?v=YnIxejYW7cE&t=3s

