[[Zookeeper]]
[[ASCII vs Unicode|ASCII vs Unicode]]
hashing/encoding:
- MD5: Hashing algorithm, generates 128bit value.
- Base64: Base64 is a binary to a text encoding scheme that **represents binary data in an American Standard Code for Information Interchange (ASCII) string format**.
Key generation service.

[[My learnings/System Design/HLD/ASCII vs Unicode]]
One obj: 500bytes

- **URL Encoding and Decoding:**
    - In URLs, certain characters have special meanings and must be encoded to be transmitted safely over the internet. This process is known as URL encoding.
    - The symbols "+" and "/" have special meanings in URLs, and if they appear in data that needs to be included in a URL (e.g., query parameters), they must be encoded.
    - For example, if you have a query parameter value that contains a space, it should be encoded as "%20". If you have a forward slash ("/") or a plus sign ("+"), they should be encoded as "%2F" and "%2B", respectively.