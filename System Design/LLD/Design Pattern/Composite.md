### Define:
**Composite** is a structural design pattern that lets you compose objects into tree structures and then work with these structures as if they were individual objects.

### Problem:
Lets say your app's component follows a tree-like data structure. For instance, a box can have other boxes or items, here boxes represents regular nodes and items represent leaf nodes. 


### Solution:
A general idea is that we can make both box(regular node) and item(leaf node) implement the same interface. When we call a method X, if it is a regular node it will just delegates the work to its children and if it is a leaf node it will do the work itself.
![[Pasted image 20230819205135.png]]