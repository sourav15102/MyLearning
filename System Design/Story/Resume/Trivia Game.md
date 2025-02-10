#### **Possible Interview Questions and Responses**
1. **How did you implement real-time communication in the Trivia Game?**
    - **Response**:
        - Real-time communication was enabled using **WebSockets** for bidirectional communication between the server and players.
        - Each player’s session was tracked using a unique connection ID.
        - **Redis** was used as a message broker to manage communication efficiently across multiple servers for scalability.
        - Example flow:
            1. A player sends an answer through the WebSocket connection.
            2. The server broadcasts the updated game state (e.g., leaderboard) to all players in real time.
2. **How did you ensure the scalability of the game?**
    - **Response**:
        - Deployed the backend using **AWS ECS** with auto-scaling enabled based on CPU and memory usage.
        - Used **API Gateway** for managing game-related API endpoints.
        - Leveraged **DynamoDB** for storing player sessions and game states, as it supports high-throughput reads and writes.
3. **What were the challenges with implementing Redis for player interactions?**
    - **Response**:
        - Managing consistent game states across multiple servers was challenging.
        - Solved it by using **Redis Pub/Sub** to synchronize updates like player scores or game timers.
        - Also used Redis to manage session timeouts and detect inactive players.
4. **How was the leaderboard implemented?**
    - **Response**:
        - Designed a microservice that:
            - Updates the leaderboard in real time using WebSocket events.
            - Stores scores in **DynamoDB**, optimized for high-frequency writes.
            - Ranks players dynamically by querying scores efficiently using partition keys and sort keys.