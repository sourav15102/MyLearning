
### Idea:
- Uses same concept as [[435]].
#### Code:
```java
class Solution {
    public int minMeetingRooms(List<Interval> intervals) {
        int l = intervals.size();        
        int[] x = new int[l];
        int[] y = new int[l];

        for(int i=0;i<l;i++){
            x[i] = intervals.get(i).start;
            y[i] = intervals.get(i).end;
        }

        Arrays.sort(x);
        Arrays.sort(y);

        int a,b;
        a = b = 0;

        int cnt = 0;
        int ans = 0;

        while(a<l && b<l){
            if(x[a]<y[b]){ // it will become if(x[a]<=y[b]) if {1,2} {2,5} were overlapping
                cnt++;
                a++;
            }else if(x[a]>y[b]){
                cnt--;
                b++;
            }else{
                a++;
                b++;
            }
            ans = Math.max(ans, cnt);
        }

        while(a<l){
            cnt++;
            a++;
            ans = Math.max(ans, cnt);
        }

        while(b<l){
            cnt--;
            b++;
            ans = Math.max(ans, cnt);
        }


        return ans;

    }
}
```