- Open standard for access delegation/authorizations
- Used by third party apps to have limited access to user resources.
- token based authorization framework
- Can be used with OpenID connect to ake it for Authen and Author

Communication between systems:
1. Using 32 char string cryptographically generated API key.
	1. added to the header of the HTTP request
	2. Vulnerable as it is like password.
2. OAuth

![[1_rX8m94BM36otECLBsGgNCw.webp]]

### Grant type
#### **1. Authorization Code Grant (for comparison)**

- **Use Case:** Standard web authentication where the client does not directly handle credentials.
- **Actors:** Same as Password Grant.
- **How it Works:**
    1. The user is redirected to the **authorization server** (e.g., Google login).
    2. After approval, the authorization server provides an **authorization code**.
    3. The client exchanges this code (along with its **client ID & secret**) for an **access token**.
    4. The client uses the token to access the **resource server**.
- **Example:**
    - Logging into an app via **"Login with Google/Facebook"** instead of entering credentials.

![[boot-39_3 1.jpg]]

---
#### **2. Client Credentials Grant**

- **Use Case:** Machine-to-machine authentication where no user is involved.
- **Actors:**
    - **Client Application:** The machine that needs authentication.
    - **Authorization Server:** Issues access tokens.
    - **Resource Server:** Hosts the protected resources.
- **How it Works:**
    1. The client (machine) authenticates itself using its **client ID** and **client secret** to the authorization server.
    2. The authorization server issues an **access token**.
    3. The client uses the token to access the **resource server**.
- **Example:**
    - A **hotel aggregator (Trivago)** needs data from multiple third-party APIs (e.g., MakeMyTrip).
    - Trivago authenticates itself with MakeMyTrip’s authorization server, gets a token, and accesses MakeMyTrip’s API for hotel data.

![[boot_51-6.jpg]]

---

#### **3. Password Credentials Grant**

- **Use Case:** User authentication where the client collects user credentials.
- **Actors:**
    - **Resource Owner (User):** Provides credentials.
    - **Client Application:** Requests authentication.
    - **Authorization Server:** Issues access tokens.
    - **Resource Server:** Hosts the protected resources.
- **How it Works:**
    1. The **user enters a username & password** into the client application.
    2. The client application sends these credentials (along with its **client ID & client secret**) to the **authorization server**.
    3. The authorization server returns an **access token**.
    4. The client uses the token to request resources from the **resource server**.
- **Example:**
    - A **Facebook mobile app login** where the user enters credentials.
    - The app sends these credentials (along with its client ID & secret) to Facebook’s authorization server to get an access token and fetch user data.

![[rant.jpg]]

### Implicit:
the **Implicit Grant Flow** does **not** allow access without authentication. The **Resource Owner (User) still authenticates**, but the difference is that the **access token is returned immediately** instead of using an authorization code.

### **How the Implicit Grant Flow Works**

1. The **Client (Application)** redirects the user to the **Authorization Server**.
2. The **User logs in** (authentication).
3. The **Authorization Server immediately issues an access token** as part of the redirect **(without issuing an authorization code)**.
4. The **Client extracts the token** from the redirect URL **without a backend server exchanging an authorization code**.
5. there is no refresh token

6. Does the Implicit Grant Type Allow Access Without Authentication?

No, the Implicit Grant Flow does not allow access without authentication. The Resource Owner (User) still authenticates, but the difference is that the access token is returned immediately instead of using an authorization code.
How the Implicit Grant Flow Works

    The Client (Application) redirects the user to the Authorization Server.
    The User logs in (authentication).
    The Authorization Server immediately issues an access token as part of the redirect (without issuing an authorization code).
    The Client extracts the token from the redirect URL without a backend server exchanging an authorization code.

✅ It is still permission-based—the user must grant access before the token is issued.
7. What Permissions Does Implicit Grant Type Have?

    The scope of permissions is determined by what the user consents to during authorization.
    Since there is no refresh token, implicit flow is best for short-lived access tokens.
    Typically used for read-only permissions (e.g., fetching a user’s basic profile but not modifying settings).
    Due to security risks, it is now discouraged in favor of Authorization Code Flow with PKCE.

8. What Was Implicit Grant Type Used For?
9. Historically, Implicit Grant was used for Single Page Applications (SPAs) or JavaScript-based front-end apps where:
    The app could not store client secrets securely (because JavaScript is exposed to the user).
    Tokens were short-lived to reduce security risks.

---

> In OAuth **Authorization Code Flow**, the first interaction (client → authorization server) happens **publicly** via redirection, but the **access token is exchanged securely over SSL** in a server-to-server request, ensuring it is not exposed.

> **How everything is public in Implicit Flow:**  
	In **Implicit Flow**, **both the authorization request and access token response happen publicly** in the browser (redirect URL). The access token is **directly included in the URL fragment**, making it visible in browser history, referrer logs, and accessible by JavaScript—hence, it's now considered insecure and deprecated.
### **4. What Response Type is Sent by Each Grant Type?**

|**OAuth 2.0 Grant Type**|**Response Type Sent by Client**|**What is Returned by Authorization Server?**|
|---|---|---|
|**Authorization Code Grant**|`response_type=code`|`code` (Authorization Code)|
|**Implicit Grant**|`response_type=token`|`access_token`|
|**Client Credentials Grant**|No response type (direct request)|`access_token` (No user interaction)|
|**Password Grant**|No response type (direct request)|`access_token` + `refresh_token`|
|**Device Authorization Grant**|No response type (direct request)|`device_code` (Used to poll for access token)|
### **Key Differences**

| Grant Type             | Involves User? | Credentials Shared?                 | Use Case                                       |
| ---------------------- | -------------- | ----------------------------------- | ---------------------------------------------- |
| **Client Credentials** | ❌ No           | Client authenticates itself         | Machine-to-machine authentication              |
| **Password Grant**     | ✅ Yes          | User provides credentials to client | Direct user login (e.g., mobile apps)          |
| **Authorization Code** | ✅ Yes          | No direct credential sharing        | Secure web authentication (e.g., Google login) |
|                        |                |                                     |                                                |

### Security
9. OAuth should only accept token-based authentication, as this provides more secure authorization than basic authentication. 
- Always use **HTTPS** to prevent token interception.
- Validate **redirect URIs** to avoid open redirects.
- Store **client secrets securely** (not in front-end applications).
- Use **short-lived access tokens** with refresh tokens.

#### **8. Explain the concept of scopes in OAuth.**
"Scopes define the **permissions** an access token grants. For example:
- `read:user` – Read user profile.
- `write:posts` – Create posts. The authorization server issues tokens with the requested scopes."

#### **15. What is the purpose of the redirect URI in the OAuth flow?** 
**Answer:** "It specifies where the authorization server sends the user after authorization. It **prevents redirection attacks** by ensuring the response is sent to a trusted client."

### **16. Explain the concept of state parameter in OAuth.** 
**Answer:** "The `state` parameter prevents **CSRF attacks**. The client sends a unique value with the authorization request, and the same value must be returned in the response."

OAuth 2.0 was introduced to **simplify** the process and **enhance security**, fixing key issues present in OAuth 1.0.

| **Aspect**                   | **OAuth 1.0**                                                | **OAuth 2.0**                                                                                       |
| ---------------------------- | ------------------------------------------------------------ | --------------------------------------------------------------------------------------------------- |
| **Token Type**               | Uses **signed requests** (complex signature method).         | Uses **Bearer tokens** (simpler, but must be secured with HTTPS).                                   |
| **Security**                 | More secure, but **complex to implement** due to signatures. | **Relies on HTTPS** for security (easier to use, but must be properly secured).                     |
| **Access Token Expiry**      | Tokens do **not expire**, requiring manual revocation.       | Tokens **expire automatically**; refresh tokens can be used to extend sessions.                     |
| **Authentication Mechanism** | Uses **OAuth token signatures** for every request.           | Uses **SSL/TLS (HTTPS) instead of signatures**.                                                     |
| **Flexibility**              | Single authentication flow.                                  | **Multiple grant types** (Authorization Code, Implicit, Client Credentials, Password, Device Flow). |
| **Mobile & Browser Support** | Not designed for mobile apps.                                | Introduced **PKCE (Proof Key for Code Exchange)** to secure mobile clients.                         |
| **Refresh Tokens**           | Not supported.                                               | Supported, enabling **long-lived authentication** without storing passwords.                        |
| **Adoption & Usability**     | Complex and developer-unfriendly.                            | Easier for developers and supports more use cases.                                                  |
|                              |                                                              |                                                                                                     |


### **19. What is the PKCE extension in OAuth 2.0 and why is it important?**
**Answer:** "PKCE (Proof Key for Code Exchange) prevents **authorization code interception** by adding a `code_challenge` and `code_verifier` during authentication."

```
GET https://auth.example.com/oauth/authorize?
    response_type=code
    &client_id=CLIENT_ID
    &redirect_uri=https://client.example.com/callback
    &scope=read_profile email
    &state=RANDOM_STRING
    &code_challenge=CODE_CHALLENGE_STRING
    &code_challenge_method=S256

```
####### **Why Do We Need PKCE (Proof Key for Code Exchange) for Mobile Apps?**
**PKCE (Proof Key for Code Exchange)** is essential for **public clients like mobile apps** to prevent **authorization code interception attacks**.
📌 **The Problem (Without PKCE in Mobile Apps)**
1. **Mobile apps cannot store client secrets securely**
    - Unlike web apps, mobile apps **run on user devices** where any attacker can **decompile and extract the client secret**.
    - This means **a stolen authorization code** can be **exchanged for an access token** by an attacker, impersonating the real client.
2. **Authorization Code Interception Attack**
    - A malicious app on the phone could **register the same redirect URI**.
    - If an attacker intercepts the **authorization code**, they can use it to obtain an access token.


![[Pasted image 20250203140536.png]]
### **22. Common OAuth pitfalls.**
- **Storing secrets in client-side apps**.
- **Not validating redirect URIs**.
- **Exposing long-lived access tokens**.

### Debug:
3. OAuth-related errors is by using an OAuth debugging tool.  These tools allow developers to easily pinpoint which part of the authorization process is causing the errors.  The debugging tool can provide detailed information about the API call and response that can help inform the troubleshooting process.
4. it's important to look over the OAuth server configurations and see if there are any inconsistencies.  
5. The OAuth server may be set up incorrectly or be attempting to authenticate a user with the wrong credentials.  
6. If the code is out of date or doesn't match the settings from the server, this could cause authentication problems. 


### Revoke and logout
1️⃣ **Token Revocation:** OAuth 2.0 allows clients to revoke **Access Tokens** and **Refresh Tokens** via the `/revoke` endpoint, ensuring they can no longer be used.  
2️⃣ **Access Token Revocation:** If revoked, the API will reject further requests using the token.  
3️⃣ **Refresh Token Revocation:** If revoked, the client loses the ability to request new access tokens, forcing re-authentication.  
4️⃣ **Logout is Not Defined in OAuth 2.0:** OAuth 2.0 does **not** have a built-in logout mechanism because it focuses on authorization, not session management.  
5️⃣ **Alternative Logout Approach:** The client can revoke the Refresh Token and clear its own session, but it cannot log the user out globally.  
6️⃣ **Resource Owner Must Re-Authenticate:** Once tokens are revoked, the user must go through the OAuth flow again to obtain new tokens.


---
---

Stack:
1. how it would operate without OAuth
2. what http req looks like with token
3. How revoke?lgout implications


- Java versions new features
- encryption vs hashing
- SSL vs TLS

Java garbage collection, JVM
Java regex
Java 8 features
