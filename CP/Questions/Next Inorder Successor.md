
### Idea: 
- QUESTION STATES: Node whose successor we need to find is given to us directly.
- Just Imagine a full inorder traversal.
- what happens when we pass a node, whats the progression.
- If there is a right subtree to Node x: find minimum in right subtree.
- If no right subtree, go recursively to parents till you find first one which greater than our curr node.
### Code:
```java
Node findInOrderSuccessor(Node inputNode) { //Node whose successor we need to find is given to us directly.
      if(inputNode==null){
        return inputNode;
      }
      Node tmp;
      if(inputNode.right != null){
        tmp = inputNode.right;
        while(tmp.left!=null){
          tmp = tmp.left;
        }
        return tmp;
      }
      
      int val = inputNode.key;
      while(inputNode!=null && inputNode.key<=val){
        inputNode = inputNode.parent;
      }
      return inputNode;
      
    }
```