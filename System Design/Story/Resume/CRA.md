### **Potential Questions and Answers**
#### **1. How Did You Improve Microservice Performance by 40%?**
- **Answer**:
    - "I achieved this by identifying bottlenecks in the existing microservices through profiling tools like JProfiler. Key optimizations included:
        - **Database Query Optimization**: Replaced inefficient queries with indexed versions and reduced unnecessary joins.
        - **Caching**: Integrated Redis as a caching layer using Spring Cache to minimize repetitive database calls.

### **2. Event-Driven Architecture with AWS SNS**
#### **Potential Questions and Answers**
1. **How did you implement event-driven architecture at CRA?**
    - **Answer**:
        - "We implemented an event-driven architecture using **AWS SNS** to enable real-time inter-service communication. Services subscribed to SNS topics, and whenever a specific event occurred (e.g., user profile updates), a message was published to the topic.
        - For example:
            1. For formal file dispute, by any client, used SNS to send emails and notifications to Reps.
            2. Updates from Account transaction to Reps.
2. **What challenges did you face while implementing SNS?**
    - **Answer**:
        - "One challenge was ensuring message delivery reliability. To handle failures:
            - We enabled **dead-letter queues** for undeliverable messages.
3. **Why did you choose SNS over other messaging systems like Kafka or RabbitMQ?**
    - **Answer**:
        - "SNS was chosen because of its serverless, fully managed nature. It was ideal for our use case since:
            - It provided seamless integration with other AWS services (e.g., SQS for queues, Lambda for processing).

---

### **3. Caching with Spring Cache and Redis**
#### **Potential Questions and Answers**
1. **How did you implement caching to reduce redundant database calls?**
    - **Answer**:
        - "We used [[Spring Cache with Redis]] as the caching provider. Frequently accessed data, like user profiles, was cached to reduce database load. Key implementation steps:
            - Configured Redis as the cache store in the application.
            - Annotated methods with `@Cacheable` for automatic caching.
            - Implemented cache eviction policies using `@CacheEvict` to ensure data consistency."
2. **How did you ensure the cache stayed consistent with the database?**
    - **Answer**:
        - "We used a combination of:
            - **Cache eviction policies**: Data was evicted whenever updates occurred via `@CacheEvict`.
            - **Time-to-Live (TTL)**: Configured expiry times for cached data to avoid stale information.
            - **Message-driven invalidation**: Whenever a critical update occurred, we published invalidation messages via SNS to update caches across services."
3. **What benefits did caching provide?**
    - **Answer**:
        - "Caching reduced redundant database calls by 30%, improved API response times, and handled high read traffic efficiently."


### **4. Ensuring 90% Test Coverage**
#### **Potential Questions and Answers**
1. **How did you achieve 90% test coverage?**
    - **Answer**:
        - "We ensured high test coverage by:
            - Writing **unit tests** for every method in the service layer using **JUnit** and mocking dependencies with **Mockito**.
            - Covering edge cases like null inputs, invalid data formats, and exceptions.
            - Creating **integration tests** with `@SpringBootTest` to verify end-to-end workflows.
            - Using **Jacoco** to measure coverage and identify untested code paths."

### **5. Internationalization (i18n) Implementation**
#### **Potential Questions and Answers**
1. **How did you implement internationalization (i18n)?**
    - **Answer**:
        - "We used Angular’s built-in **i18n module** to support multiple languages:
            - Extracted static text into translation files (`.json`).
            - Used Angular pipes (`{{ value | translate }}`) to dynamically render text based on the selected language.
            - Configured language toggling based on user preferences or browser settings."
2. **What challenges did you face with i18n?**
    - **Answer**:
        - "A major challenge was dynamically translating content fetched from APIs. We solved it by:
            - Implementing translation texts in the API response.
3. **How did you test for language compatibility?**
    - **Answer**:
        - "We conducted localization testing by:
            - Pre-approved translations with native speakers teams.


### **6. Frontend Development with Angular and RxJS**
#### **Potential Questions and Answers**
1. **What features did you implement on the frontend?**
    - **Answer**:
        - "I developed reactive forms using **Angular** and **RxJS**.
            - Built forms with reactive forms to handle complex validations and dynamic inputs.
            - Used **RxJS Observables** to handle real-time data streams efficiently."
2. **How did you ensure accessibility (WCAG compliance)?**
    - **Answer**:
        - "We adhered to WCAG standards by:
            - Using WAVE attributes to enhance screen reader compatibility.
            - Ensuring keyboard navigability for all interactive elements.
            - Testing with tools like **NVDA screen reader**."
3. **How did you manage state in the application?**
    - **Answer**:
        - "We used **RxJS Subjects** to manage and share state between components.
            - Example: A shared service used a `BehaviorSubject` to propagate changes in user preferences across components."

