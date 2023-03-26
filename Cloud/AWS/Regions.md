- geographically distinct locations, can contain one or more *availability zones*(generally 3)(some new users are limited to 2).
- physically isolated and independent from every other regions, in terms of power, water supply, and location.
- new services almost always become available at US EAST-1(northern virginia)
- service costs vary with region.
- not all services are available at all regions.
4 things need to be considered while choosing region:
![[Pasted image 20230320141723.png]]

**Regional vs global services:**
AWS Scopes their AWS Management Console on a selected Region.
This will determine where an AWS service will be launched and what will be seen within an AWS Service's console
You generally don't explicitly set the Region for service at the time of the creation
**Global Services**
Some AWS Services operates across multiple regions and the region will be fixed to "Global"
E.g. Amazon S3, Cloud Front, Route53, IAM

> for regional services, there is no need to select the region.
> but, for some of these global services, it is a bit different:
> 1. like for IAM, it will be global, no need to select region.
> 2. for S3 bucket, it ahs to be in one region, so need to select a region while starting up, (global means they are available everywhere, but need a region while starting up)
> 3. for CloudFront: it is like distribution, so need to select a bunch of regions.
