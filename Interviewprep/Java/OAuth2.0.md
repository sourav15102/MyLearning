
**Scenario**:
1. A **game client app** uses **OAuth 2.0** to request Google (the **Authorization Server**) to authenticate the user.
2. Google provides an **Access Token** to the game client app.
3. The game client app uses the **Access Token** to access the **Calendar Server** (the **Resource Server**) from Google and retrieve permitted information without the user providing their password to the game client.

**Key Points**:
- **OAuth 2.0** is an **authorization framework**, not an authentication protocol.
- **Authorization Server**: The entity (Google in this case) that authenticates the user and issues the access token.
- **Access Token**: A short-lived token that grants the client app permission to access the user's resources.
- **Refresh Token**: A long-lived token used to obtain a new access token without re-authenticating the user.
- **Resource Server**: The server hosting the user's data (e.g., Google's Calendar Server) that the client app wants to access.
- **User Credentials**: The user does not share their credentials (password) with the game client app, enhancing security.

**Flow**:
1. **User Authorization**: The user authorizes the game client app to access their Google resources.
2. **Access Token Issuance**: Google issues an access token (and potentially a refresh token) to the game client app.
3. **Resource Access**: The game client app uses the access token to request information from the Calendar Server.
4. **Token Refresh**: When the access token expires, the client can use the refresh token to get a new access token without re-authenticating the user.

**Conclusion**: OAuth 2.0 ensures secure, delegated access to user resources by third-party applications, focusing on **authorization** without exposing user credentials. The use of **access tokens** and **refresh tokens** enhances security and efficiency.