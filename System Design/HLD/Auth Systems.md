Links:
- https://www.bhavaniravi.com/software-engineering/authentication-in-python

### Cookie/Session based vs Token based
##### Cookie:
1. **User Authentication:**
   - User logs in with credentials.
   - Server validates credentials and creates a session in the database.
2. **Cookie Creation and Server Response:**
   - Server responds with a cookie in the Set-Cookie header.
   - Cookie includes a unique ID and optional details like expiry date, domain, and security attributes.
3. **Browser Storage and Subsequent Requests:**
   - Browser stores the cookie and includes it in subsequent requests.
   - Server validates the user by comparing the session ID in the cookie with the database.
4. **Logout Process:**
   - Upon user logout, server deletes the session from the database.
   - Browser expires the cookie by removing it from storage.
5. **Authentication Lifecycle:**
   - Cookies facilitate user authentication by maintaining session information.
   - Features include simplicity, automatic inclusion in requests, and easy management via browser tools.
   - Pros: Simplicity, automatic handling, and easy management.
   - Cons: Vulnerable to CSRF and XSS; server-side session storage limitations.

> 1. the browser automatically adds them to (almost) every request to the same domain using the Cookie HTTP-header.

Q: Why [[CSRF]] is only possible in cookie/session based?
A: https://www.youtube.com/watch?v=eWEgUcHPle0
1. Cookie can be accessed by another domain as long as they are sending the request to the correct website.

##### Token based auth:
1. **User Authentication:**
   - User logs in with credentials.
   - Server verifies credentials, generates a token, signs it with a secret key, and sends it to the browser.
2. **Token Generation and Structure:**
   - Use libraries like jsonwebtoken to generate tokens.
   - Tokens consist of header, payload, and signature, providing encoded user information.
3. **Token Storage and Inclusion:**
   - Store the token in browser storage (Local, Session, or Cookie).
   - Include the token in the authorization header of subsequent requests using JavaScript (`Authorization: Bearer <token>`).
4. **Logout Process:**
   - Upon user logout, manually delete the token from storage.
   - Clearing the token makes it unavailable for further requests.
5. **Token-Based Authentication Features:**
   - Token-based authentication facilitates secure user validation.
   - Features include simplicity, statelessness, and enhanced security with encryption.
   - Pros: Stateless nature, scalability, and flexibility.
   - Cons: Requires additional measures for token refresh and handling token storage securely.

Q: How token based authentication system helps with CSRF?
A: Web storage(local, session) The storage is bound to the origin (domain/protocol/port triplet). That is, **different protocols or subdomains infer different storage objects, they can't access data from each other**.

A: Q: isnt storing jwt token in cookie dangerous as CSRF can happen
A: Yes, storing a JWT token in a cookie can introduce security risks, including the potential for Cross-Site Request Forgery (CSRF) attacks. Here are some considerations and best practices to address these concerns:

1. **Use HttpOnly Flag:**
    - Set the HttpOnly flag on the cookie storing the JWT.
    - This prevents client-side scripts (JavaScript) from accessing the cookie, reducing the risk of token theft via XSS.
(The `HttpOnly` flag is an attribute that can be set on an HTTP cookie. When this flag is present, it instructs the browser that the cookie should not be accessed by client-side scripts, limiting its exposure to potential security risks such as Cross-Site Scripting (XSS) attacks.)

[[XSS]]:

### OAuth (https://www.youtube.com/watch?v=ZDuRmhLSLOY)
![[Pasted image 20240116194401.png]]
Scenario:
1. Here the game client app is using OAuth 2.0 to ask google(authorization Server) to authenticate the user and provide access token.
2. That access token can be used by client app to access calendar server(Resource Server) from google and get requested and permitted info without user providing any password to game client.
> OAuth 2.0 itself is an authorization framework, not an authentication protocol

> Idp or Identity provider and Authorization server can be different things in OAuth 2.0 framework, Idp(can be a 3rd party)can be used to authenticate users, and company's own authorization server can be used to provide access token for its protected resources. 


JWT:
> It can be used with JWT, i.e., after game client receives the access token from google authorization server, it will import it as JWT and use it then to access resources instead.

Benefits:
**JWTs can be Easily Verified by Resource Servers:**
- Resource servers (APIs) can easily verify JWTs by checking their signature. JWTs are self contained, meaning they contain every information about them which is needed to verify them.
- The self-contained nature of JWTs means that resource servers can extract the necessary information directly from the token without needing to make additional calls to an authorization server.

Claims:
claims are statements about an entity (typically, the user) and additional metadata. These claims are represented as key-value pairs within the JSON structure of the token
1. Reserved claims
2. Public claims
3. Private claims

OAuth is also used with OpenID Connect or OIDC.
As mentioned above that Idp or Identity provider and Authorization server can be different things in OAuth 2.0 framework.
OIDC is a Identity provider.

The OpenID Connect (OIDC) protocol defines the following entities:
- **Relying Party** (Client App): The current application.
- **OpenID Provider**(Identity Provider): This is essentially an intermediate service that provides a one-time code to the Relying Party.
- **Token Endpoint**(Authorization Server): A web server that accepts the One-Time Code (OTC) and provides an access code that's valid for an hour. The main difference between OIDC and OAuth 2.0 is that the token is provided using JSON Web Token (JWT).
- **UserInfo Endpoint**(Resource Server): The Relying Party communicates with this endpoint, providing a secure token and receiving information about the end-user
### SSO (https://www.youtube.com/watch?v=4ULlJEupV-I)
OAuth 2.0 is an authorization framework , whereas SSO focusses on providing authentication and authorization information to other apps.
![[Pasted image 20240116203314.png]]

1. Service Provider or apps we want to access redirects us to Idp, we give our usrname and password.
2. It authenticates und redirects us to Service Provider with SAML Assertions(XML way to exchange authentication and authorization detail).
3. This way we can access multiple apps, give our Idp.

Q: Why this is not used in microservice architectures?
A: Because, they mostly rely on JSON and it works with SAML(XML markup language). So microservice architecture is better suited with JWT based OAuth 2.0


