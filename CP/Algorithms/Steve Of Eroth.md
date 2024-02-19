
### Code:
```java
private final boolean[] prime = new boolean[(int)1e6+1];
    private void steve(){
        prime[0] = prime[1] = false;
        for(int i=2;i*i<=prime.length;i++){
            if(prime[i]){
                for(int j=i*i;j<prime.length;j=j+i){
                    prime[j] = false;
                }
            }
        }
    }
```