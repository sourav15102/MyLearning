NAT stands for Network Address Translation. It is a technique used in computer networking to allow devices with private IP addresses to communicate with devices on the public Internet.

NAT works by maintaining a mapping table that associates the private IP address of a device on the local network with a public IP address assigned by the Internet Service Provider (ISP).

Here the public IP address is assigned to the device and is unique globally, whereas private IP address is not accessible from the internet.

When a device on the local network initiates a connection to a device on the Internet, the NAT device modifies the source IP address of the outgoing packet with the public IP address assigned by the ISP. When the response is received, the NAT device uses the mapping table to translate the public IP address back to the private IP address of the requesting device.

The translation is conducted using : [[Port multiplexing]].

NAT is commonly used in residential and small business networks to allow multiple devices on the local network to share a single public IP address.

[[Router]] typically act as a NAT as well.
