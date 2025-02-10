A **Bearer Token** is a type of **token** used in [[Authentication Protocols]], particularly in **OAuth 2.0**, to grant access to a resource. It is called a "bearer" token because the bearer (the person or system holding the token) is granted access to the resource, similar to a ticket. The bearer doesn't need additional identification as long as the token is valid.

### Key Features of a Bearer Token:
- **Self-contained**: It usually contains enough information (claims) about the user or the access rights, so no further lookup is needed.
- **Stateless**: The server doesn't need to keep session information about the bearer of the token.
- **Simple Usage**: The client just includes the token in the HTTP Authorization header when making a request to the server.

### Role of a Bearer Token in JWT:
1. **Authentication**: The JWT acts as a Bearer Token when a user or client sends it as part of an HTTP request to prove their identity. The server can then validate the token to confirm the user's identity without needing to check a session or database repeatedly.
2. **Authorization**: The JWT can contain claims about the user's permissions or roles. When used as a Bearer Token, it allows the server to determine what actions the user is authorized to perform based on the information embedded in the token.