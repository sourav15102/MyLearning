## Server vs client:
- servers are special computers which are directly connected to the internet and web pages are stored there., they are generally present in data centers, which can provide the power and resources to run these servers.
- client are computers which are directly connected to internet via ISP.


## How to build a server
1. Decide on what kind of server
2. Decide on the OS.
3. When you have a server running on the machine but it is not connected to any network, that would mean that the server is listening for incoming requests on a [[port]](server binds itself to a port) but it has no address.
4. When it is connect to network, it has a private IP address, now machines on same network can connect to it, but not the internet.
5. To do that we need either [[VPN]] or [[Port Forwarding]].
6. Once you have a server running connect to NAT gateway to enable communication between server and internet.


### Questions:
Q) Diff between web, database, file server?
A) A *web server* is a server that is designed to host and serve web pages and other web content over the internet. A web server is a software program that listens for incoming HTTP or HTTPS requests from clients (usually web browsers) and responds by serving web pages, assets, or data.  Web servers use protocols like HTTP (Hypertext Transfer Protocol) and HTTPS (HTTP Secure) to communicate with web browsers.

A *database server* is a server that is designed to store and manage large amounts of structured data. Database servers allow multiple users to access and manipulate data simultaneously, and provide features like data indexing, query optimization, and transaction management. Database servers typically use SQL (Structured Query Language) or other database-specific languages to manage data.

A *file server* is a server that is designed to store and share files over a network. File servers provide centralized storage for files, and allow users to access and modify files from multiple devices and locations. File servers typically use protocols like FTP (File Transfer Protocol), SMB (Server Message Block), or NFS (Network File System) to share files over a network

Q) Diff between web frontend servers like run by react node and one by spring boot/express js?
A) hen we start a React application, the server is typically used to serve the static web pages and assets (HTML, CSS, JavaScript files) that make up the React application. The server may also handle client-side routing, allowing the React application to behave like a single-page application (SPA).
On the other hand, when we start a Spring Boot/Express.js application, the server is typically used to serve APIs that return JSON results. These APIs can be used to provide data and functionality to other applications or services, including front-end applications like React.

These two can be considered web servers: A web server is a software program that listens for incoming HTTP or HTTPS requests from clients (usually web browsers) and responds by serving web pages, assets, or data. Both React and Spring Boot/Express.js applications have web servers built-in that can handle incoming HTTP or HTTPS requests from clients and respond accordingly.

Q) What is NIC?
A) [[NIC]]