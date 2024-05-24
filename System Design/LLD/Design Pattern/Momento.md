https://www.youtube.com/watch?v=nTo7e2lpGZ4&list=PL6W8uoQQ2c61X_9e6Net0WdYZidm7zooW&index=42

- Also called Snapshot design pattern.
- Has undo capability.
- Can revert back to previous version.

Process:
1. We have 3 classes: Caretaker, Originator, Momento
2. Originator: Class of which the Momento is created.
	3. Has method createMomento: which create obj of Momento, set desired values and store it in Caretaker's momento list.
3. Caretaker: Which stores the list of momentos.
4. Momento: Which stores the desired values of Originator.