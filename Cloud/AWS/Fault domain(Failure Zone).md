
> Fault Domain:
> It is a set of network that is vulnerable to fail if a critical system or device fails, not cascading that damage out(its main purpose).
> Fault level:
> collection of fault domains.
> Scope of fault domain:
> 1. a server in a rack
> 2. entire rack
> 3. entire room in datacenter
> 4. entire datacenter
> Is upto CSP to define fault domains.
> AWS takes care of it, but azure gives you flexibility to define fault domains.

**Failure Zone**
-   Availability Zones are physically separated within a typical metropolitan region and are located in lower risk flood plains
-   discrete uninterruptible power supply (UPS) and onsite backup generation facilities
-   data centers located in different Availability Zones are designed to be supplied by independent substations to reduce the risk of an event on the power grid impacting more than one Availability Zone.
-   Availability Zones are all redundantly connected to multiple tier-1 transit providers
