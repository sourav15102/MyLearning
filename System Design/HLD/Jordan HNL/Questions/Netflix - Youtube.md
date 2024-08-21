**Problem Requirements**
1. Users can post videos
2. Users can watch videos
3. Users can comment on videos
4. Users can search for videos by name

Capacity estimation:
1. **1 billion users**
2. **On average videos have 1000 views, some have more.**
3. **On average videos are 100 MB.**
4. **1 million videos posted per day**

At the bottom, there is a calculation:

- **100 MB * 1 million videos * 400 days = 40 PB per year**

The numbers seem to be part of an estimation regarding the storage or bandwidth requirements for a video platform based on the data provided. Would you like any further analysis or explanation on these details?

- Feature APIs
- Feature DB schemas
- Decide on DB choices:

#### Schemas and DB choices:
We decide on the db based on whether we need read and write.
- Subscribers/SubscribedTo:
	- It is not a very frequent event, also more read is MySQL (Single leader replication)
- User,videos,metadata
	- More read than write, so MySQL (Single leader replication)
- userId, email, password
	- More read than write, so MySQL (Single leader replication)
- comments: videoId, userId, comment, timestamp
	- might be frequent write, so assuming no child comments and deletion/edition, we cna go with Cassandra (NoSQL, Leaderless replication).
	- With Child comments and modification: [Link](https://youtu.be/S2y9_XYOZsg?list=PLjTveVh7FakJOoY6GPZGWHHl4shhDT8iV&t=1739)
		- In that case we can simply go for MySQL (Single leader replication)

#### Video Streaming/Upload:

 Summary: 
 - We need to support videos for a few resolutions (480P, 1080P) and different encoding to support different platforms. We need to store all combinations of resolution and encoding.
 - We need to divide the video in chunks.
 - At first client will download first chunk and somewhere around streaming 1st chunk, client will decide based on the network speed, which resolution does it need to bring in for next chunk.
 - After uploading lets say ith chunk to S3, we can push its reference to message broker, which can be consumed by the services which will process the chunk for different encoding/resolutions, then store diff versions to S3, also we can push the updated about the video to Kafka -> Flink which can store it in CDN if it is popular and store it in db. 
 - Last think is what kind of [[Message Broker]] to use when sending S3 likes of video chunks to be consumed by processing services, Answer: In-memory message broker.

#### Search Index
- We can do CDC on userID- >> Videos schema and then using Kafak -> Flink we can process those vidoes description to use tokenization and then use Elastic search for search index.



![[Pasted image 20240803155504.png]]