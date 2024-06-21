It shares data among multiple objects, it helps reduce memory usage.

Scenarios where it can be used:
1. Word processor/text editor
2. Game

From a very heav7y class, we extract out the heaviest properties in a separate immutable class called Flyweight, and we reuse that object in multiple places.

We might have a pool of flyweight objects in a factory class from we can call a function and get the required flyweight obj as needed., 

If we were to create 1Lakhs objects of a Robot in graphics, if we repeat the creation 1Lkakh times, it would take up a lot of memory, instead we can use Flyweight pattern, where we can create an immutable class of Robot with its image and everything, and have a function in that class to display that object at x,y coordinates.
We can save in a map the Robot --> Obj, each time we require robot, we can fetch that obj and display it wherever necessary.