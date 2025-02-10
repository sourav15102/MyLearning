https://leetcode.com/problems/meeting-rooms-iii/

```java
class Solution {
    class Node{
        int[] meet;
        int ind;
        Node(int[] meet, int ind){
            this.meet = meet;
            this.ind = ind;
        }
    }
    public int mostBooked(int n, int[][] meetings) {
        int l = meetings.length;
        Arrays.sort(meetings, (a,b)->a[0]-b[0]);

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->{
            if(a.meet[1]==b.meet[1]){
                return a.ind - b.ind;
            } else{
                return a.meet[1] - b.meet[1];
            } 
        });

        int[] ans = new int[n];
        Arrays.fill(ans,0);

        PriorityQueue<Integer> fs = new PriorityQueue<>();
        for(int i=0;i<n;i++){
            fs.add(i);
        }

        int j=0,ss;
        Node temp;
        int sett = 0;
        while(j<l){
            if(pq.isEmpty()){
                ss = fs.poll();
                pq.add(new Node(meetings[j++], ss));
                ans[ss]++;
            } else{
                temp = pq.peek();
                if(temp.meet[1]<=meetings[j][0]){
                    fs.add(pq.poll().ind);
                }else{
                    if(fs.isEmpty()){
                        meetings[j][1] += temp.meet[1] - meetings[j][0];
                        meetings[j][0] = temp.meet[1];
                    }else{
                        ss = fs.poll();
                        pq.add(new Node(meetings[j++], ss));
                        ans[ss]++;
                    }
                }
            }
        }

        int aa = 0;
        for(int i=0;i<n;i++){
            if(ans[aa]<ans[i]){
                aa = i;
            }
        }

        return aa;


    }
}
```