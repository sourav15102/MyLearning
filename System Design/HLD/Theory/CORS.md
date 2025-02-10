https://medium.com/@baphemot/understanding-cors-18ad6b478e2b

Cross-Origin Resource Sharing (CORS) is a protocol that enables scripts running on a browser client to interact with resources from a different origin. This is useful because, thanks to the [same-origin policy](https://developer.mozilla.org/en-US/docs/Web/Security/Same-origin_policy) followed by `XMLHttpRequest` and `fetch`, JavaScript can only make calls to URLs that live on the same origin as the location where the script is running. For example, if a JavaScript app wishes to make an AJAX call to an API running on a different domain, it would be blocked from doing so thanks to the same-origin policy.

CORS is basically a mechanism implemented by browser which gets triggered whenever you are making an HTTP request to:

- a different domain (eg. site at [example.com](http://www.example.com) calls [api.com)](http://www.api.com))
- a different sub domain (eg. site at [example.com](http://www.example.com) calls api.example.com)
- a different port (eg. site at [example.com](http://www.example.com) calls [example.com:3001)](http://www.example.com:3001))
- a different protocol (eg. site at [https://example.com](https://example.com) calls [http://example.com)](http://example.com))

>An origin is a combination of the protocol (e.g., HTTP or HTTPS), domain, and port of a web page.

Mechanism:
1. If the server does not respond with specific headers to a “simple” `GET` or `POST` request — it will still be send, the data still received but the browser will not allow JavaScript to access the response.
2. If your browser tries to make a “non simple” request (eg. an request that includes cookies, or which `Content-type` is other than `application/x-ww-form-urlencoded`, `multipart/form-data` or `text-plain`) an mechanism called **preflight** will be used and an `OPTIONS` request will be sent to the server. An common example of “non simple” request is to add cookies or custom headers — it your browser sends such a request and the server does not respond properly, only the preflight call will be made (without the extra headers) but the actual HTTP request the brower meant to make will not be sent.

