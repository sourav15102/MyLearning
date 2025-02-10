https://newsletter.systemdesigncodex.com/p/making-your-database-highly-available

### Redundancy
Redundancy is all about having options.

**But don’t mistake redundancy for backups.**
Backups are about keeping copy of the data safe in case of catastrophic loss.
If you have only one database server, it will still cost your business big time to make the backup data available again, also,  backup copy could be way behind the actual data.

**multiple database instances** working closely together such that the user still thinks it’s just one database instance.
There are 3 main patterns that help achieve this level of redundancy:

![](https://substackcdn.com/image/fetch/w_1456,c_limit,f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2F9f5b3c1e-35d8-4304-bec4-66ecad4f13b5_1024x768.png)

