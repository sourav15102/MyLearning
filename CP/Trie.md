- It is used for prefix operations on words.
- It is used with dictionary operations on words.

##### Structure:
![[Pasted image 20231016151012.png]]

##### Operations:
```java
class TrieNode {
    Map<Character, TrieNode> mp;
    boolean isWord;

    TrieNode(){
        mp = new HashMap<>();
        isWord = false;
    }
}
class Trie {
    private TrieNode trieNode;
    public Trie() {
        trieNode = new TrieNode();        
    }
	
    private void addChar(TrieNode trieNode, char[] wr, int ind){
        if(wr.length == ind){
            trieNode.isWord = true;
            return;
        }
        Map<Character, TrieNode> pm = trieNode.mp;
        if(!pm.containsKey(wr[ind])){
            pm.put(wr[ind], new TrieNode());
        }

        addChar(pm.get(wr[ind]), wr, ind+1);
    }

    private boolean searchChar(TrieNode trieNode, char[] wr, int ind){
        if(ind == wr.length){
            return trieNode.isWord;
        }
        Map<Character, TrieNode> pm = trieNode.mp;
        if(!pm.containsKey(wr[ind])){
            return false;
        }
        return searchChar(pm.get(wr[ind]), wr, ind+1);
    }

    private boolean prefixSearch(TrieNode trieNode, char[] wr, int ind){
        if(ind == wr.length){
            return true;
        }
        Map<Character, TrieNode> pm = trieNode.mp;
        if(!pm.containsKey(wr[ind])){
            return false;
        }
        return prefixSearch(pm.get(wr[ind]), wr, ind+1);
    }

	// INSERT
    public void insert(String word) {
        char[] wr = word.toCharArray();
        addChar(trieNode, wr, 0);
    }
    // SEARCH
    public boolean search(String word) {
        char[] wr = word.toCharArray();
        return searchChar(trieNode, wr, 0);
    }
    // PREFIX SEARCH
    public boolean startsWith(String prefix) {
        char[] wr = prefix.toCharArray();
        return prefixSearch(trieNode, wr, 0);
    }
}
```
> The below code keeps the count of word as well.
```java
class TrieNode {
    Map<Character, TrieNode> mp;
    int cnt;
    TrieNode(){
        mp = new HashMap<>();
        cnt = 0;
    }
}
public class Trie {
    private TrieNode trie;
    public Trie() {
        trie = new TrieNode();
    }
    private void addChar(TrieNode trie, char[] wr, int ind){
        if(ind == wr.length){
            trie.cnt++;
            return;
        }
        Map<Character, TrieNode> pm = trie.mp;
        if(!pm.containsKey(wr[ind])){
            pm.put(wr[ind], new TrieNode());
        }
        addChar(pm.get(wr[ind]), wr, ind+1);
    }
    public void insert(String word) {
        char[] wr = word.toCharArray();
        addChar(trie, wr, 0);
    }
    private int cntWords(TrieNode trie, char[] wr, int ind){
        if(ind == wr.length){
            return trie.cnt;
        }
        Map<Character, TrieNode> pm = trie.mp;
        if(!pm.containsKey(wr[ind])){
            return 0;
        }
        return cntWords(pm.get(wr[ind]), wr, ind+1);
    }
    public int countWordsEqualTo(String word) {
        char[] wr = word.toCharArray();
        return cntWords(trie, wr, 0);
    }
    private int sumDFS(TrieNode trie){

        Map<Character, TrieNode> pm = trie.mp;
        int ans = trie.cnt;
        for(Map.Entry<Character, TrieNode> me: pm.entrySet()){
            ans = ans + sumDFS(me.getValue());
        }
        return ans;
    }

    private int cntWordsPrefix(TrieNode trie, char[] wr, int ind){
        if(ind == wr.length){
            return sumDFS(trie);
        }
        Map<Character, TrieNode> pm = trie.mp;
        if(!pm.containsKey(wr[ind])){
            return 0;
        }
        return cntWordsPrefix(pm.get(wr[ind]), wr, ind+1);
    }
    public int countWordsStartingWith(String word) {
        char[] wr = word.toCharArray();
        return cntWordsPrefix(trie, wr, 0);
    }
    private int delWord(TrieNode trie, char[] wr, int ind){
        if(ind == wr.length){
            if(trie.cnt>0){
                trie.cnt--;
            }
            if(trie.cnt==0 && trie.mp.isEmpty()){
                return -1;
            }
            /**
            * this here make sures if node the map's wr[ind] is pointing to has empty map  * and cnt is also 0, it basically means, that there are no words here which end or passes * through it, this node is no longer needed.
            **/
            return 1;
        }
        Map<Character, TrieNode> pm = trie.mp;
        if(!pm.containsKey(wr[ind])){
            return 1;
        }
        int gt = delWord(pm.get(wr[ind]), wr, ind+1);
        if(gt==-1){
            pm.remove(wr[ind]);
        }
        if(trie.cnt==0 && trie.mp.isEmpty()){
            return -1;
        }
        return 1;
    }
    // DELETE
    public void erase(String word) {
        char[] wr = word.toCharArray();
        int xx = delWord(trie, wr, 0);
        if(xx==-1){
            trie.mp.remove(wr[0]);
        }
    }
}
```


Coding Ninjas:
1. [[Complete String]]
2. 
