### Define
**State** is a behavioral design pattern that lets an object alter its behavior when its internal state changes. It appears as if the object changed its class.

### Problem
Imagine that we have a `Document` class. A document can be in one of three states: `Draft`, `Moderation` and `Published`. The `publish` method of the document works a little bit differently in each state:

- In `Draft`, it moves the document to moderation.
- In `Moderation`, it makes the document public, but only if the current user is an administrator.
- In `Published`, it doesn’t do anything at all.

### Solution
![[Pasted image 20230817184450.png]]

Both Context and state have each other:
1. Context have state so that it can call the state's function via its function.
2. State have context so that it can point the context to another state.