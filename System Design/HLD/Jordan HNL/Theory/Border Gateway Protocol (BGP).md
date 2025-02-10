**BGP (Border Gateway Protocol)** is the **routing protocol** used to exchange routing information between different networks (Autonomous Systems or AS) on the **Internet**.

---

### **Key Points:**

1. **Purpose:**
    
    - BGP determines the **best path** for routing data packets between networks.
2. **How It Works:**
    
    - BGP routers maintain a **routing table** that lists all available paths to different destinations.
    - Routes are exchanged between routers using **BGP messages**.
    - Routers select the **best path** based on metrics like **AS hop count**, **latency**, or **routing policies**.
3. **Path Selection Criteria:**
    
    - Fewer AS hops (shortest path).
    - Preference for specific paths based on routing rules (e.g., cost, latency).
    - Policies set by network administrators (e.g., prefer routes via trusted peers).
4. **Types of BGP:**
    
    - **External BGP (eBGP):** Routing between different AS (public networks).
    - **Internal BGP (iBGP):** Routing within the same AS (private networks).
5. **Why It’s Important:**
    
    - BGP keeps the **Internet connected** by determining how traffic flows globally.
    - It allows ISPs and large networks to advertise routes to one another.