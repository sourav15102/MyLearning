Kth smallest element
```java
int kthSmallestElement(int *arr,int n,int k) {

    // Here we want smallest so we have to use MAX HEAP, default priority queue is max heap

    priority_queue<int> pq;

    for(int i = 0; i < n; i++) {
        pq.push(arr[i]);
        if(i >= k) {
            pq.pop(); // we only want k elements in the priority queue at any time 
        }
    }

    return pq.top();

}
```

Kth largest element

```java
int kthLargestElement(int *arr,int n,int k) {

    // Here we want largest so we have to use MIN HEAP.

    priority_queue<int, vector<int>, greater<int>> pq;

    for(int i = 0; i < n; i++) {
        pq.push(arr[i]);

        if(i >= k) {
            pq.pop(); // we only want k elements in the priority queue at any time 
        }
    }

    return pq.top();

}
```