![[1711538560123 1.jpg]]

### **CDN Design Summary**

A **Content Delivery Network (CDN)** is a geographically distributed system of **proxy servers** designed to deliver content efficiently and securely.

#### **Key Components**:

- **Clients**: End-user devices requesting content.
- **Proxy Servers**: Edge servers that cache and serve content.
- **Origin Servers**: Source of the original content.
- **Routing System**: Directs users to the nearest proxy server.
- **Distribution System**: Manages content flow to edge servers.
- **Management System**: Monitors performance and security.

---

### **Content Distribution**

- **Push Model**: Content is proactively pushed to edge servers; suitable for **static content**.
- **Pull Model**: Content is fetched from the origin server **on-demand**; ideal for **dynamic content**.
- **Hybrid**: Combines both for efficiency.

---

### **Multi-Tier Content Distribution (Hierarchical CDN Architecture)**

A **multi-tier CDN** involves distributing content through a hierarchical structure of servers, typically consisting of:

- **Origin Servers** (Tier 1)
- **Regional CDN Servers** (Tier 2)
- **Edge Proxy Servers** (Tier 3)
#### **How it Works:**

1. Content flows **downstream** from the origin server to regional servers and then to edge servers.
2. Regional servers act as **intermediate caches**, reducing the load on the origin server.
3. Edge servers are closest to end-users and serve cached content directly to users.
#### **When to Use Multi-Tier CDN:**

- Large-scale systems serving **global audiences** with high content demands.
- Systems where **origin server load** must be minimized (e.g., video streaming, gaming).
### **Routing Techniques**

- **DNS Redirection**: Routes requests to the nearest proxy server via DNS resolution.
- **Anycast**: Routes requests to the geographically closest server using a shared IP.
- **HTTP Redirection**: Instructs the client to fetch content from a CDN-hosted URL.
- **Load Balancer**: Based on load and location

---
### **Consistency Maintenance**

- **Periodic Polling**: Proxy servers check for updates from the origin server.
- **Time-to-Live (TTL)**: Cached content expires after a set time and is refreshed.
- **Leases**: Proxy servers dynamically revalidate content with the origin server.
