1. Generally we do ETL (Extract, transform and load), and move data from transactional DB (Db used directly by users) to analytical DB (which stores data in different format). 
2. We perform ETL as a batch job.
3. After loading it in a analytics file system such as HDFS, we have a programming model called MapReduce to perform batch processing.
	1. Instead MapReduce we can also use Apache spark (Data flow engine) which improves upon MapReduce to perform batch processing.
	2. They both use HDFS underneath.
4. Batch processing is done on a bounded set of data.
5. As oppose to stream processing which is done unbounded set of data.

Analytical DB:
- We have column oriented db, entire column is stored close, and analysis can be formed quickly.
- Column compression can be performed using many techniques: basically it is that 1,1,1,1,2,2,2,2,3,3,3, all 1s, 2s  and 3s can be compressed.

Materialized view:
- It pre calculates the values of heavy calculation produces answer quickly.

Data cube: just another type of materialized view where it precalculates the values in many dimensions.

Overview: Companies can dump a lot of unstructured data in lets say HDFS or Apache Spark and we can run batch processing on that unstructured data to get useful insights.