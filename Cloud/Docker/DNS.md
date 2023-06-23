When containers are running on the default bridge network in Docker, they do not have their own DNS resolution capability. Instead, they rely on the DNS configuration of the Docker host machine. Docker uses the DNS settings defined in the host machine's `/etc/resolv.conf` file for DNS resolution within containers.

The `/etc/resolv.conf` file on the Docker host typically contains the DNS server(s) that the host machine uses for name resolution. Docker containers inherit these DNS settings, which allows them to use the same DNS servers for resolving domain names.

Containers that attach to a custom network use Docker's embedded DNS server. When you create a user-defined network in Docker and attach containers to that network, Docker automatically sets up a DNS server for that network.

