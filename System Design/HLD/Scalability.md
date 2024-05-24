Points for Discussing Scalability in Interviews:

1. Start with Vertical Scaling. It's simpler and usually works fine for most apps. Just make sure to have backups to handle any failures.

2. Switch to Horizontal Scaling when Vertical Scaling gets too expensive. You'll know it's time when adding more hardware doesn't make a big difference in performance.

3. Keep your apps simple and "stateless" to make scaling easier, especially when moving from vertical to horizontal scaling.

4. Choose databases that can scale horizontally. Many modern ones can, but don't ignore the setup challenges they may bring.