https://www.youtube.com/watch?v=kwy-G1DEm0M&list=PL6W8uoQQ2c61X_9e6Net0WdYZidm7zooW&index=43

Usecase:
- When we want all classes to follow a particular process to perform a task.
- But we want all classes to have their flexibility in implementing those process's steps

Process: Lets say we needed to implement a Payment flow method where we need to call helper functions in a specific order.
1. Make a PaymentFlow abstract class.
2. Implement the pay() method where we call helper functions in the order we want.
3. Extend this abstract class and implement helper methods.
4. now make pay() method in abstract class `final` cos we dont want child classes to override this method and change the order of pay process.