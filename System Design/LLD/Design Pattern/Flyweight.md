It shares data among multiple objects, it helps reduce memory usage.

Scenarios where it can be used:
1. Word processor/text editor
2. Game

If we were to create 1Lakhs objects of a Robot in graphics, if we repeat the creation 1Lkakh times, it would take up a lot of memory, instead we can use Flyweight pattern, where we can create an immutable class of Robot with its image and everything, and have a function in that class to display that object at x,y coordinates.
We can save in a map the Robot --> Obj, each time we require robot, we can fetch that obj and display it wherever necessary.