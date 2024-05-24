Ref Link: https://www.youtube.com/watch?v=E1lce5CWhE0&list=PL6W8uoQQ2c61X_9e6Net0WdYZidm7zooW&index=35

1. Invoker: remote control
2. Receiver: AC, TV
3. Command: on, off command

1. We set the command into the invoker.
2. Command already has the receiver.
3. On Pressing button, invoker just execute the command.

For undo functionality:
1. each command obj has its undo method as well.
2. invoker can keep a stack of commands it has executed, so while executing undo from invoker, invoker can just pop command from stack and execute its undo method.