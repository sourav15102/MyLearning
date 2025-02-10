**Anycast** is a **network routing technique** where the **same IP address** is assigned to multiple servers (nodes) in different geographic locations. When a user sends a request to that IP address, the request is automatically routed to the **nearest server** (based on network hop distance, latency, or routing policy).

---

## **How Anycast Works**

1. **Same IP Address:** Multiple edge servers (or nodes) across the globe are configured to use the **same IP address**.
2. **Routing Decision:** When a client sends a request to that IP, routers in the network use the **[[Border Gateway Protocol (BGP)]]** to determine which server is the closest.
    - The "closeness" is measured based on network metrics like **latency**, **hop count**, or routing cost.
3. **Response:** The closest server handles the request and sends a response to the user.