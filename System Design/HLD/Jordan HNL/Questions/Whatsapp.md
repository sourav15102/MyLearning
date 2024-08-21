Two major feature to focus on here are group-memeber relationship which is same as X's follower-following.

another is how one message is going to be propagated to all:
First when we connect chatting service load balancer, it will use consistent hashing ring to assign the user to a server, user-server websocket persistent connection.
When a user sends a message it will be pushed to kafka and will be consumed by flink, flink will send message to all users, it will basically send it to the service load balancer, and it will figure out (with consistent hashing), which server the user belong to.

Or for unpopular chunks Flink can send update to each user manually (Flink can send update request to the load balancer, which can use Consistent hashing ring to determine which server the user belongs to and thus sending update.) (Consistent hashing ring can be maintained by Coordination service Zookeeper) 