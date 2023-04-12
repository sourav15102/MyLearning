A port is just a concept for a connection between an application and a layer-4 protocol. There really isn't a "port" as such. When an application wants to receive traffic from a layer-4 protocol (TCP, UDP, etc.), it requests that the protocol send any layer-4 segments for that protocol which are addressed with a particular port number to the application.

When a layer-4 protocol has no application which has requested that layer-4 segments addressed to a particular port number, the port is **Closed**.

When an application has requested and been granted the use of a port number by a layer-4 protocol, the port is **Open**.

Firewalls can be set to drop layer-3 packets containing layer-4 segments for a particular layer-4 protocol addressed to a particular port. This means that the port is **Filtered** or **Blocked** on the firewall.

Each layer-4 protocol has its own ports, or it many not even use ports. For instance, one application can be receiving traffic for TCP 12345, and another application can be receiving traffic for UDP port 12345; they are not the same port because ports don't actually exist. The use of a particular port number on a particular layer-4 protocol is exclusive to the application granted use of the port by the layer-4 protocol.