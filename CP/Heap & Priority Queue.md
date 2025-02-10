
Link: https://leetcode.com/discuss/general-discussion/1127238/master-heap-by-solving-23-questions-in-4-patterns-category

# What is Heap?

1. It is mainly used to represent a priority queue.
2. It is represented as a Binary Tree (a tree structure where a node of a tree has a maximum of two child nodes). Heaps are complete binary trees.
3. A simple array can be used to represent a Heap where array indices refer to the node position in the tree.
4. Parent and child nodes can be accessed with indices:
    - A root node｜i = 0, the first item of the array
    - A parent node｜parent(i) = (i-1) / 2
    - A left child node｜left(i) = 2i+1
    - A right child node｜right(i)=2i+2
5. Two type of Heaps — Min Heap, Max Heap  
    Min Heap — the parent node always has a smaller value than the child nodes.  
    Max Heap — the parent node is always larger than the child node value.
6. Usually, when a type is not mentioned, it refers to the MinHeap.
7. minHeap are used in tasks related to scheduling or assignment.

Heap implementation:
```java
import java.util.ArrayList;
import java.util.List;

public class PriorityQueue {
    private List<Integer> pq;

    public PriorityQueue() {
        pq = new ArrayList<>();
    }

    public boolean isEmpty() {
        return pq.size() == 0;
    }

    public int getSize() {
        return pq.size();
    }

	public void heapifyDown(int i) {
	    int smallest = i; 
	    int left = 2 * i + 1;
	    int right = 2 * i + 2;
	
	    if (left < n && arr[left] < arr[smallest]) {
	        smallest = left;
	    }
	
	    if (right < n && arr[right] < arr[smallest]) {
	        smallest = right;
	    }
	

	    if (smallest != i) {
	        int swap = pq.get(i);
	        pq.set(i, pq.get(smallest));
	        pq.set(smallest, swap);

	        heapify(smallest);
	    }
	}


	private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (pq.get(parentIndex) > pq.get(index)) {
                swap(parentIndex, index);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    public void insert(int elem) {
        pq.add(elem);
		heapifyUp(pq.size() - 1);
    }


	public void delete(int index) {
	    if (index >= 0 && index < pq.size()) {
	        int lastIndex = pq.size() - 1;
	        swap(index, lastIndex); // Swap the element to be deleted with the last element
	        pq.remove(lastIndex);  // Remove the last element
	        heapifyDown(index);    // Perform heapify-down to maintain the heap property
	    }
	}

    public int removeMin() {
	    if (pq.size() == 0) {
	        return -1; // Heap is empty
	    }
	
	    int minVal = pq.get(0); // The minimum value is at the root
	    delete(0);
	
	    return minVal;
	}


}
```


Problem Patterns where HEAP is used
- Top K Pattern
- Merge K Sorted Pattern
- Two Heaps Pattern
- Minimum Number Pattern

Theory: 
1. [[Priority Queue]]
2. [[K largest and k smallest pattern]]
3. [[Queue]]

Questions:
LC discussions: 
1. [1](https://leetcode.com/discuss/general-discussion/1127238/master-heap-by-solving-23-questions-in-4-patterns-category)
2. [2](https://leetcode.com/discuss/general-discussion/1113631/important-concepts-problems-in-priority-queueheaps)

Heap:
1. [[378]]*
2. [[373]]**

LC:
1. [[703]]
2. [[1046]]
3. [[973]]
4. [[621]] (Task Scheduler)
5. [[355]]
6. [[295]]**
7. [[1851]]** (pq and intervals)
8. [[950]]*
9. [[2073]]**
10. [[1508]]**
11. [[767]]***

Coding Ninja:
1. [[Maximum Sum Combination]]**

Contest:
- [[3044. Most Frequent Prime]]*