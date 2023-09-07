DNS uses hierarchical naming structure
.com
example.com
www.example.com
api.example.com


- Domain Registrar: Amazon Route 53, GODaddy
• DNS Records: A, AAAA, CNAME, NS, …
• Zone File: contains DNS records
• Name Server : resolves DNS queries (Authoritative or Non-Authoritative)
• Top Level Domain (TLD): .com, .us, .in, .gov, .org, …
• Second Level Domain (SLD): amazon.com, google.com
![[Pasted image 20230826154705.png]]

Root DNS: managed by ICANN
TLD DNS: managed by IANA, branch of ICANN
SLD DNS: Domain  , managed by route53

How DNS works:
https://www.pbrumby.com/2018/05/05/how-dns-and-domain-names-work/

Route 53: Managed DNS
- Authoritative DNS service.
- Authoritative: Handles DNS records updates.
- Also a Domain Registrar.
- Monitors resource health.
- Unique among AWS, 100% availability SLA.
- Named after DNS port (53).

**Route 53 - Records**
- Manage traffic routing for domains: we define domain names here.
- Each record includes:
    - Domain/subdomain Name (e.g., example.com)
    - Record Type (e.g., A or AAAA)
    - Value (e.g., 12.34.56.78)
    - Routing Policy for query responses.
    - TTL (Time to Live) for DNS Resolver caching.
- Supported DNS record types:
    - A / AAAA / CNAME / NS (essential)
    - CAA / DS / MX / NAPTR / PTR / SOA / TXT / SPF / SRV (advanced)


**Route 53 - Record Types**
- A: Maps hostname to IPv4.
- AAAA: Maps hostname to IPv6.
- CNAME: Maps hostname to another hostname.
  - Target must have A or AAAA record.
  - Can't be created for Zone Apex (e.g., example.com)., cant create for example.com but for www.example.com (more in future).
- NS: Name Servers for Hosted Zone: returns DNS names or IP addresses for services that can resolve hosted zone.
  - Control domain's traffic routing.

Route 53 – Hosted Zones
- Container for records that dictate traffic routing for a domain and subdomains.
- Public Hosted Zones: For routing Internet traffic (public domain names).
  Example: application1.mypublicdomain.com
- Private Hosted Zones: For routing traffic within VPCs (private domain names).
  Example: application1.company.internal
- Cost: $0.50/month per hosted zone. 

Route 53 – Records TTL (Time To Live)
- TTL determines how long DNS resolvers should cache the DNS record.
- High TTL (e.g., 24 hours) reduces traffic on Route 53 but might provide outdated records.
- Low TTL (e.g., 60 seconds) increases Route 53 traffic but ensures outdated records are cached for a shorter time.
- Easy to change records with low TTL.
- TTL is mandatory for all DNS records except Alias records.(automatically st by route53)
- TTL controls caching duration, affecting the frequency of Route 53 requests and the freshness of records.

CNAME vs Alias
CNAME:
- Points a hostname to any other hostname. (app.mydomain.com => blabla.anything.com)
- Works only for non-root domains (subdomains) (aka. something.mydomain.com)
- Commonly used for mapping hostnames.
- Example: app.mydomain.com => blabla.anything.com.
Alias:
- Directly associates a hostname with an AWS resource (app.mydomain.com => blabla.amazonaws.com).
- Works for both root and non-root domains (subdomains). (aka mydomain.com)
- Provided at no additional cost.
- Offers native health checks for routing accuracy.

**Route 53 – Alias Records**
- Maps a hostname to an AWS resource.
- An extension to DNS functionality.
- Automatically recognizes changes in the resource’s IP addresses.
- Unlike CNAME, it can be used for the top node of a DNS namespace (Zone Apex), e.g.: example.com.
- Alias Record is always of type A/AAAA for AWS resources (IPv4 / IPv6).
- You can’t set the TTL.(automatically set by route53)


Route 53 – Alias Records Targets:
• Elastic Load Balancers
• CloudFront Distributions
• API Gateway
• Elastic Beanstalk environments
• S3 Websites
• VPC Interface Endpoints
• Global Accelerator accelerator
• Route 53 record in the same hosted zone
• EC2 DNS name cannot have an ALIAS record.

Route 53 – Routing Policies:
• Define how Route 53 responds to DNS queries.
• DNS does not route traffic; it responds to DNS queries.
• Route 53 Supports these Routing Policies:

Routing Policies – Simple:
• Route traffic to a single resource.
• Multiple values can be specified in the same record. (some can be bad, as no health checks here)
• If multiple values are returned, a random one is chosen by the client.
• When Alias enabled, specify only one AWS resource.
• Cannot be associated with Health Checks.

Routing Policies – Weighted:
• Control the percentage of requests going to specific resources.
• Assign relative weights to records.
• Weighted calculation: Traffic (%) = (Weight of Record) / (Sum of Weights)
• Weights don't have to total 100.
• DNS records must have the same name and type.
• Can be associated with Health Checks.
• Use cases: load balancing between regions, testing new application versions...
• Assigning a weight of 0 to a record stops traffic to that resource.
• If all records have a weight of 0, they are returned equally.

Routing Policies – Latency-based:
• Redirect to the resource with the least latency.
• Useful when minimizing user latency is important.
• Latency based on traffic between users and AWS Regions.
• For instance, German users could be directed to the US if it has lower latency.
• Can be associated with Health Checks (with failover capability).

Routing Policies – Failover (Active-Passive):
- there can be olny primary and 1 DR insatnce.
- can have health checks.

Routing Policies – Geolocation
• Distinct from Latency-based approach.
• Routes based on user location.
• Designate by Continent, Country, or US State (precise if overlaps).
• Establish a "Default" record for unmatched locations.
• Applications: localization, content control, load balancing.
• Possible association with Health Checks.

Routing Policies – Geoproximity
• Directs traffic based on user and resource location.
• Adjust traffic allocation with defined bias.
• Alter geographic region size using bias values:
• Expand (1 to 99) – more traffic to resource, inc bias of the region.
• Shrink (-1 to -99) – less traffic to resource, dec bias of reagion
• Supports AWS and non-AWS resources.
• Use Route 53 Traffic Flow for this feature.
Basically you can expand and shrink region based on bias and all users within that region will go to that resource.

Routing Policies – IP-based Routing
• Routing is determined by clients’ IP addresses.
• You supply a list of CIDRs for clients along with their associated endpoints/locations (user-IP-to-endpoint mappings).
• Useful for optimizing performance and lowering network expenses.
• For instance, you can route users from a specific ISP to a designated endpoint.
- health checks happen(confirm once)

Routing Policies – Multi-Value
• Applicable for distributing traffic across multiple resources.
• Route 53 delivers multiple values/resources.
• Health Checks can be linked to show only healthy resources.
• Each Multi-Value query can yield up to 8 healthy records.
• Remember, Multi-Value doesn't replace the need for an ELB (Elastic Load Balancer).


Route 53 – Health Checks:
• HTTP Health Checks are for public resources.
• Health Checks enable automated DNS Failover:
  1. Monitor endpoints like applications, servers, or other AWS resources.
  2. Monitor other health checks (Calculated Health Checks).
  3. Monitor CloudWatch Alarms (e.g., DynamoDB throttles, RDS alarms, custom metrics) – useful for private resources.
• Health Checks are integrated with CloudWatch metrics.

Health Checks – Monitor an Endpoint:
• Around 15 global health checkers assess endpoint health.
• Default Healthy/Unhealthy Threshold: 3.
• Interval: 30 seconds (can be set to 10 seconds for higher cost).
• Supported protocols: HTTP, HTTPS, and TCP.
• If over 18% of checkers say the endpoint is healthy, Route 53 considers it so; otherwise, it's unhealthy.
• Locations for health checkers can be selected.
• Health Checks succeed only when the endpoint responds with 2xx and 3xx status codes.
• Pass/Fail based on text in the first 5120 bytes of response can be set.
• Router/firewall setup must allow incoming requests from Route 53 Health Checkers.

Route 53 – Calculated Health Checks
• Combine results of several Health Checks into one.
• Use logical operations: OR, AND, NOT.
• Up to 256 Child Health Checks can be monitored.
• Define how many health checks must pass for the parent to pass.
• Useful to conduct website maintenance without triggering all health checks to fail.

Health Checks – Private Hosted Zones
• Route 53 health checkers are external to the VPC.
• Inaccessible to private endpoints (private VPC or on-premises resource).
• Solution: Create a CloudWatch Metric and Alarm, then use a Health Check for alarm checking.


Domain Registrar vs. DNS Service
• Domain Registrar: Where you purchase/register your domain name, like GoDaddy or Amazon Registrar Inc.
• Domain Registrar often offers a DNS service to manage DNS records.
• However, you can opt for a different DNS service to manage your records.
• For instance, you can buy a domain from GoDaddy and use Route 53 to handle DNS records.

Using 3rd Party Registrar with Amazon Route 53
• Even if you purchase a domain from a 3rd party registrar, you can utilize
Route 53 as your DNS Service provider.
1. Set up a Hosted Zone in Route 53.
2. Modify NS Records on the 3rd party website to point to Route 53's Name Servers.
• Note that Domain Registrar and DNS Service are distinct, although most registrars offer DNS features.




