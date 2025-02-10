How Normal Feed Service Works:
1. Every time a person posts something, we check who their followers are and create a feed for each of them by maintaining a separate cache for each follower's feed, essentially copying all posts _X (number of followers)_ times.

But in the case of popular people with millions of followers:
1. We can create a shared cache specifically for popular people and maintain only one copy of their posts.

While generating a person's feed:
1. We can fetch posts normally for regular users.
2. Fetch posts from the shared popular cache for popular people.

![[Pasted image 20241217202553.png]]