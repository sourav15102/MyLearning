![[Pasted image 20230827003408.png]]

Amazon S3 Lifecycle Rules:
- Transition: Move objects to different storage classes based on time.
- Transition Examples: Move to Standard IA after 60 days, Glacier after 6 months.
- Expiration: Set objects to auto-delete after a defined period.
- Deletion: Remove old versions, incomplete uploads, log files.
- Customization: Apply rules by prefix or object tags for targeted control.

How to decide in which class to keep and how to design: S3 Analytics
- Assists in transitioning objects to appropriate storage classes.
- Provides suggestions for Standard and Standard IA classes.
	- Not applicable to One-Zone IA or Glacier.
- Daily updated report.
- Takes 24 to 48 hours to display analyzed data.

S3 – Requester Pays
• Requester Pays buckets shift costs from bucket owner to requester for requests and data download.
• Useful for sharing large datasets.
• Requester must be authenticated in AWS, not anonymous.

 S3 Event Notifications
• Events: S3:ObjectCreated, S3:ObjectRemoved, S3:ObjectRestore, S3:Replication...
• Can trigger Lambda Function, SQS, or SNS.
• Object name filtering possible (e.g., *.jpg).
• Use case: generate thumbnails for uploaded images.
• Multiple "S3 events" can be created.
• Events are usually delivered in seconds but can take longer.

Advanced S3 Event Bridge:
• JSON rules for metadata, object size, name filtering.
• Multiple destinations: Step Functions, Kinesis Streams/Firehose.
• EventBridge capabilities: Archive, Replay Events, Reliable delivery.

S3 baseline performance:
• Scales automatically to high request rates with 100-200 ms latency.
• Achieve 3,500 PUT/COPY/POST/DELETE or 5,500 GET/HEAD requests per second per prefix.
• No limits on the number of prefixes in a bucket.
• Example: If evenly spread, 22,000 requests per second for GET and HEAD across four prefixes.


How to improve performance:
Multi-Part Upload:
• Recommended for files > 100MB, required for files > 5GB.
• Allows parallelized uploads for faster transfers.
S3 Transfer Acceleration:
  - Speeds up transfers by routing data through AWS edge locations.
  - Works in conjunction with multi-part uploads.

How to read faster: Byte-Range Fetches
• Parallelize GETs by requesting specific byte ranges
• Better resilience in case of failures
- Can be used to speed up downloads
> can only get some particular parts as well.


S3 and Glacier select:
• Utilize server-side filtering with SQL.
• Filter rows and columns with simple SQL statements.
• Minimize network transfer and client-side CPU costs.
- we retrieve file in CSV.
- S3 select scan range can specify the range of bytes to query.
- Amazon S3 Select works on objects stored in CSV, JSON, or Apache Parquet format. It also works with objects that are compressed with GZIP or BZIP2 (for CSV and JSON objects only), and server-side encrypted objects. You can specify the format of the results as either CSV or JSON, and you can determine how the records in the result are delimited.


S3 Batch Operations:
• Execute bulk actions on existing S3 objects in a single request.
• Modify metadata, copy between buckets, encrypt, change ACLs/tags, etc.
• Restore from Glacier, invoke Lambda functions.
• A Job include: object list, action, parameters.
• S3 Batch Operations handles retries, progress tracking, notifications, and reports.
• How do we get the onb list: S3 Inventory and S3 Select can be used for object list and filtering.
![[Pasted image 20230828001457.png]]

