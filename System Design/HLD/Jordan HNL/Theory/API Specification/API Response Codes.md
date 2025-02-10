### **1. Informational Responses (100-199)**

Used for interim responses before the final response is sent.

- **100 Continue**:
    - Indicates the initial part of a request has been received and the client can continue.
- **102 Processing**:
    - Used in cases where the server acknowledges the request but processing is not yet complete (e.g., long-running operations).

---

### **2. Successful Responses (200-299)**

Indicates the request was successfully processed.

- **200 OK**:
    - General success response (e.g., `GET /refunds/{id}` to fetch refund details).
- **201 Created**:
    - Resource successfully created (e.g., `POST /refunds` returns the refund ID).
- **202 Accepted**:
    - Request has been received but is still being processed (e.g., asynchronous refund processing).
- **204 No Content**:
    - Request was successful, but no data is returned (e.g., successful `DELETE` operation).

---

### **3. Redirection Responses (300-399)**

Used to redirect the client to another resource.

- **301 Moved Permanently**:
    - Resource has been moved to a new URL permanently.
- **302 Found**:
    - Temporary redirect (e.g., resource temporarily available at a different location).
- **304 Not Modified**:
    - Used for caching; indicates the resource hasn’t changed since the last request.

---

### **4. Client Error Responses (400-499)**

Indicates issues with the client’s request.

- **400 Bad Request**:
    - Malformed request (e.g., missing required fields or invalid JSON structure).
- **401 Unauthorized**:
    - Authentication is required but missing or invalid (e.g., missing API key or token).
- **403 Forbidden**:
    - Client is authenticated but does not have permission to access the resource.
- **404 Not Found**:
    - Resource does not exist (e.g., refund ID not found).
- **405 Method Not Allowed**:
    - HTTP method not allowed for the resource (e.g., `DELETE /refunds` not supported).
- **409 Conflict**:
    - Conflict in the request (e.g., duplicate refund request for the same order).
- **410 Gone**:
    - Resource is no longer available (e.g., expired refund ID).
- **422 Unprocessable Entity**:
    - Valid request format but logical error (e.g., refund amount exceeds order amount).
- **429 Too Many Requests**:
    - Rate limit exceeded (e.g., retrying refund status too frequently).

---

### **5. Server Error Responses (500-599)**

Indicates issues with the server.

- **500 Internal Server Error**:
    - Generic error when the server encounters an unexpected condition.
- **501 Not Implemented**:
    - Server does not support the requested functionality (e.g., endpoint under development).
- **502 Bad Gateway**:
    - Server acting as a gateway received an invalid response from the upstream server.
- **503 Service Unavailable**:
    - Server is temporarily unavailable (e.g., maintenance or overload).
- **504 Gateway Timeout**:
    - Upstream server failed to respond in time.
