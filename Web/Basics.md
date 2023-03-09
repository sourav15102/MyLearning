## Web vs Internet
- Internet is the gloabal network of interconnected computers and servers.
- web is just one of the many internet applications.
- web is built on top of  internet.
- The Web, is a collection of web pages, resources, and applications that are accessed through the internet using web browsers.

## Server vs client:
- servers are special computers which are directly connected to the internet and web pages are stored there., they are generally present in data centers, which can provide the power and resouces to run these servers.
- client are computers which are idrectly connected to internet via ISP.

## HTTP
- it is a tcp/ip based applicatino layer protocol.
- it is an standardization on how client and servers(hosts) talk to each other.
- The request and response are made up of a series of text messages, called HTTP messages, that follow a specific format and set of rules.
- It uses tcp/ip to get responses to and from client and servers
- it is a statless protocol.


### REST:
- it is service built on top of HTTP.
- it uses standard http methods(GET, POST, PUT, DELETE).
- Rest api hit is an http request, which is specifically designed to interact with web services.
- Rest APi is concerned with endpoints, which represents a service,a functionality a server can provide.
- so, http is protocol used to transfer data b/w client and server, but rest is specific type of http request which is used to access a functioanlity of web server.
- it imposes certain rules in top of http,like:
	- stateless
	- responses can be cached.
	- URIs


### Questions:
- Q: Another protocols like http on tcp?
- A: FTP, SMTP, DNS, SSH, Telnet
- Q: Protocols on top of UDP?
- A: DNS(uses tcp for zone transfer and UDP for name, queries etc), VoIP
- Q: TCP/UDP bi/uni directional?
- A: TCP has  bi-directional capabilities and UDP has uni-directional capabilities.
- Q: DIfference between URI adn URL?
- A: URI is unique resource identifier, URL is just a subset of URI which uniquely identifies the resource on the internet. another type of URI can be URN, unique resource name.
- Q: what is payload?
- A: in the case of a web page, the payload would be the actual HTML, CSS, and JavaScript code that make up the page content. In the case of an email message, the payload would be the actual text of the message, any attachments, and any embedded images or other media.
- Q: why vm eill losr files?
- Q: invalidating cache

http vs rest
grpc

synchr/asynch/single threa multithread

lsm database
http2.0

session management vs jwt

websockets?