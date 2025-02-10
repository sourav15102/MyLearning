Authentication protocols are sets of rules that define how entities (like users, devices, or services) prove their identity to each other in a secure manner. These protocols ensure that the party attempting to gain access to a system or service is who they claim to be. Here's an overview of some common authentication protocols:

1. HTTP Basic Authentication
	- **Mechanism**: The client sends the username and password in the `Authorization` header of the HTTP request, encoded in base64.
	    - Example: `Authorization: Basic base64(username:password)`
	- **Usage**: Simple and commonly used in early web applications for straightforward authentication.
	- **Drawback**:
	    - **Security Risk**: Credentials are only base64-encoded, not encrypted, making them easy to decode if intercepted.
	    - **Requirement**: Must be used over HTTPS to ensure the credentials are encrypted during transmission.
2. [[OAuth2.0]]
	1.  [[SSO]] Uses **SAML**, **OAuth 2.0**, or **OpenID Connect (OIDC)**
3. [[My learnings/Interviewprep/Java/JWT]]
