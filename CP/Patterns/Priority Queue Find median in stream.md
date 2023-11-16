### Idea:
- We need to keep the tree balance.
- add larger elements to right and smaller to left.
- and if diff in sizes of left and right heap goes beyond 1, rebalance it.
- for rebalancing we need smallest element in right heap and largest element in left heap.
- for above operation we can use priority queues.

#### Code:
```java
class MedianFinder {
    private int val;
    private PriorityQueue<Integer> l;
    private PriorityQueue<Integer> r;
    private boolean filled; 

    private void rebalance(){
        int tmp;
        if(l.size()>r.size()){
            tmp = l.poll();
            r.add(val);
            val = tmp;
        }else{
            tmp = r.poll();
            l.add(val);
            val = tmp;
        }
    }

    public MedianFinder() {
        l = new PriorityQueue<>(Collections.reverseOrder());
        r = new PriorityQueue<>();
        filled = false;
    }
    
    public void addNum(int num) {
        if(!filled){
            val = num;
            filled = true;
            return;
        }

        if(val<num){
            r.add(num);
        }else{
            l.add(num);
        }

        if(Math.abs(l.size() - r.size()) > 1 ){
            rebalance();
        }
    }
    
    public double findMedian() {
        double ans;
        if(l.size()==r.size()){
            ans = val*1.0;
        }else if(l.size()>r.size()){
            ans = (val+l.peek())/2.0;
        }else{
            ans = (val+r.peek())/2.0;
        }
        return ans;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
```

Questions:
- [[295]]