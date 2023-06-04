### REST vs HTTP:
- http is a protocol.
- rest is an design principle/architectural style.
- rest just tells you that we will using Http-json(in most cases), and this is how will make apis.
- it uses standard http methods(GET, POST, PUT, DELETE).
- Rest api hit is an http request, which is specifically designed to interact with web services.
- Rest APi is concerned with endpoints, which represents a service,a functionality a server can provide.
- so, http is protocol used to transfer data b/w client and server, but rest is specific type of http request which is used to access a functioanlity of web server.
- it imposes certain rules in top of http,like:
	- stateless
	- responses can be cached.
	- URIs
- Rest adds few more contraints on HTTP.

The term "REST" (Representational State Transfer) is commonly associated with the HTTP protocol and is often used to build RESTful APIs over HTTP. RESTful APIs adhere to a set of principles and constraints defined in the REST architectural style.

While HTTP is the most prevalent protocol used with REST, it's important to note that REST is an architectural style and is not limited to a specific protocol. The principles and concepts of REST can be applied to other protocols as well, but the specific implementation details may vary.

Some examples of protocols other than HTTP that can be used with REST-like architectures include:

1.  CoAP (Constrained Application Protocol): CoAP is a lightweight protocol designed for constrained devices and networks, such as IoT devices. It supports RESTful interactions and can be used in a similar manner to HTTP in RESTful architectures.
    
2.  MQTT (Message Queuing Telemetry Transport): MQTT is a messaging protocol often used in IoT applications. While not inherently RESTful, MQTT can be combined with RESTful principles to build APIs over MQTT for resource-based interactions.
    
3.  WebSocket: WebSocket is a communication protocol that enables full-duplex communication between a client and a server. Although not strictly RESTful, WebSocket can be used to build real-time or event-driven systems with REST-like principles.
    

When using REST-like principles with protocols other than HTTP, it's important to adapt the concepts to the specific characteristics and requirements of the chosen protocol. This may involve mapping RESTful concepts such as resources, representations, and state transitions to the corresponding constructs and capabilities of the chosen protocol.

In summary, while REST is commonly associated with the HTTP protocol, the principles and concepts of REST can be applied to other protocols as well. The specific implementation details will depend on the characteristics and capabilities of the chosen protocol.