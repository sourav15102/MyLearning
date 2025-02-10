### **Situation**

At CRA, the **Statement of Account)** feature relied on three independent services—**MyA**, **MyBA**, and **T3**—each with its own APIs, models, and business logic.

This created **significant redundancy**:
- Duplicate code for similar operations across sectors.
- Overlapping data fetches, increasing latency and database load.
- Inconsistent data models, complicating integration and updates.

Representatives managing accounts across sectors had to make **separate API calls** for each, which degraded user experience and increased errors.

The team decided to **amalgamate these services** into a **single unified service**. However, they were using **JAX-RS**, a framework I had no prior experience with, to build RESTful APIs.

---
### **Task**

1. Quickly learn **JAX-RS** to design and implement a **unified AMR service** with modular and extensible architecture.
2. Ensure the new service eliminated redundancy, improved performance, and scaled for peak tax-filing seasons.

---
### **Action**

#### **1. Learning JAX-RS**

- Spent a week learning JAX-RS fundamentals:
    - Built prototypes using **annotations** like `@Path`, `@GET`, and `@POST` for defining endpoints.
    - Explored **filters and interceptors** to handle shared logic like authentication and logging.
    - Mastered **exception mapping** for consistent error handling across endpoints.

---

#### **2. Designing the Unified Service**

- **Factory Pattern**:
    
    - Implemented a `SectorFactory` to dynamically determine which business logic to invoke based on the request’s `sectorType` (e.g., MyA, MyBA, T3).
    - This eliminated hardcoding and allowed easy addition of new sectors in the future.
    
    ```java
    public SectorHandler getHandler(String sectorType) {
        switch (sectorType) {
            case "MyA":
                return new MyAHandler();
            case "MyBA":
                return new MyBAHandler();
            case "T3":
                return new T3Handler();
            default:
                throw new IllegalArgumentException("Invalid sector type");
        }
    }
    ```
    
- **Strategy Pattern**:
    
    - Encapsulated sector-specific business logic (e.g., MyBA authorization checks) into **strategy classes**, making the service modular and maintainable.
- **Unified Endpoint**:
    
    - Created a single `/authorize-representative` endpoint in JAX-RS, which routed requests to the appropriate sector handler based on the factory output.

---

#### **3. Optimizing Performance**

- **Redis Caching**:
    
    - Cached frequently accessed taxpayer data (e.g., account status) to avoid redundant calls to downstream services, reducing API latency by **70%**.
    - Used an **SNS-triggered invalidation mechanism** to ensure cache consistency.
- **Pagination**:
    
    - Implemented paginated responses for queries involving large datasets, such as retrieving all authorizations for a representative.
- **Asynchronous Processing**:
    
    - Offloaded resource-intensive tasks like compliance validations to an **SQS-based job queue**, enabling faster API responses.

---

### **Result**

1. Delivered a **unified AMR service** with one endpoint for all sectors, simplifying the process for representatives.
2. Reduced API response times by **45%**, achieving SLA compliance (<300ms for most requests).
3. Decreased downstream service calls by **70%** through caching and modularized logic.
4. Improved maintainability, allowing new sectors to be added with minimal effort.

---

This version is concise, incorporates **JAX-RS learning**, and explains the technical choices clearly. Let me know if further adjustments are needed!