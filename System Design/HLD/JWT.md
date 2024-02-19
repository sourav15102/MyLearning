Ref: https://newsletter.systemdesigncodex.com/p/json-web-tokens-and-authentication

![](https://substackcdn.com/image/fetch/w_1456,c_limit,f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2F52826dfe-e466-4d07-ad53-acdeef2d3625_1292x733.jpeg)
### Header
- Every JWT carries a header with claims about itself to specify the algorithms used for signing and/or encrypting the JWT.
- The only mandatory claim for an unencrypted JWT header is the `alg` claim.
### Payload
There are three types of claims:
- **Registered claims** or predefined set of claims that are not mandatory but recommended.
- **Public claims** are claims registered with the IANA JWT Claims registry.
- **Private claims** that can be defined by the users (consumers and producers) of the JWTs
### Signature
The signature part of the JWT is created by taking the encoded header, encoded payload, a secret key,  to verify that the message wasn’t changed along the way and to verify whether the sender of the JWT is who it says it is.

##### Cons of JWT:
Invalidation
1. Once your server signs a JWT, it’s not easy to invalidate it.
2. If the signature is valid and the JWT’s expiry timestamp has not passed, it will be considered valid by the server.
3. If a user requests to log out of all devices, you can theoretically revoke the secret key used to sign the JWT but that would invalidate all JWTs used by that key.
4. With session-based authentication, you can easily revoke a particular session ID by deleting it from the storage.
5. You could  keep track of invalidated tokens but that introduces stateful behavior similar to sessions. In defense of this point, however, the list of invalidated tokens will be far smaller than the list of valid sessions in the session-based approach.

Stale Data
- Similar to a cache, the data in a stateless token can **“go stale”**.
- For example, it can mean that somebody has a token with the role of “admin” even though you may have revoked their “admin role”. Since there’s no easy way to invalidate a token, you may find it hard to remove their administrator access reliably.