```
1. Functional Req: top 3.
```

```
non-functional requirements should be quantified. For example, The system should have low latency search, < 500ms," is much more useful 

- The system should be highly availability, prioritizing availability over consistency
- The system should be able to scale to support 100M+ DAUs
- The system should be low latency, rendering feeds in under 200ms
```

```
Make something quick, correct: to satisfy all FR.

Focus on a relatively simple design that meets the core functional requirements, and then layer on complexity to satisfy the non-functional requirements in your deep dives section
```

```
DOnt just talk, look for hints
```

```
Many candidates trip themselves up by trying to insert a comparison of relational and NoSQL databases into their answer. The reality is that these two technologies are _highly overlapping_ and broad statements like "I need to use a relational database because I have relationships in my data" (NoSQL databases can work great for this) or "I've gotta use NoSQL because I need scale and performance" (relational databases, used correctly, perform and scale incredibly well) are often yellow flags that reveal inexperience.

Here's the truth: _most interviewers don't need an explicit comparison of SQL and NoSQL databases_ in your session and it's a pothole you should completely avoid. Instead, talk about what you know about the database you're using and how it will help you solve the problem at hand. If you're asked to compare, focus on the differences in the databases you're familiar with and how they would impact your design. So "I'm using Postgres here because its ACID properties will allow me to maintain data integrity" is a great leader.

make sure you're not making broad statements but instead discussing the specific features of the database you're using and how they will help you solve the problem at hand.
```

```
Be careful of introducing queues into synchronous workloads. If you have strong latency requirements (e.g. < 500ms), introducing a queue will likely break your latency constraints.
```
