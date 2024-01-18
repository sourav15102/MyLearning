### CSRF and Cookie Storage:
For example, lets assume I want to trick you into adding me as a friend on Facebook. I send a mail to you with an innocent looking link. But the link is actually [http://facebook.com/?addFriend=JacquesB&confirm=true](http://facebook.com/?addFriend=JacquesB&confirm=true) (or something to that effect). You click on the link, and since it goes to the facebook domain, your facebook authentication cookie is sent along with the request, so the request is authenticated and executed by Facebook.

1. **Automatic Inclusion:**
    - Cookies are automatically included in all requests to the domain, including potential CSRF attacks initiated by a malicious website.
2. **Same-Origin Policy:**
    - The Same-Origin Policy (SOP) prevents direct cross-origin requests initiated by scripts, but CSRF attacks rely on the implicit inclusion of cookies by the browser.

Q: Won't same origin policy prevent it from happening?
A: CSRF can bypass the same origin policy [[CORS]] as the rule doent allow response but request can be made anyways. 
Q: Wont preflight in CORS help?
A: Pre-flight requests don't prevent CSRF _in general_. For example not all cross-domain ajax calls generate a pre-flight request, plain POSTs don't. There may be specific cases when pre-flight requests do indeed help to reduce the risk though.
https://stackoverflow.com/questions/41148282/why-doesnt-pre-flight-cors-block-csrf-attacks

> Loading a different domain(xyz.com) wont sent cookie to say 'facebook.com'.
> but we can load an image with src="malicious request" to send request to facebook.com and include cookie.
> or we can have a form which submits automatically and thats how it sends request to facebook.com