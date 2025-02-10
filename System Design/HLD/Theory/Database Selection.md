https://twitter.com/i/bookmarks?post_id=1757032972350321005
![[GGEzXP8XAAANYCU.jpeg]]

##### OLTP vs OLAP
https://www.linkedin.com/pulse/columnar-vs-row-based-data-structures-oltp-olap-systems-alex-merced-nh2ee/

Row-based:
Data is organized as a sequence of records, storing all values of a single row together. This organization allows for efficient read and write operations on a complete record, making it ideal for OLTP systems where transactions often involve inserting, updating, or deleting records.

**Advantages and Disadvantages**
- **Efficiency in Transactional Processing**: Row-based storage excels in scenarios where the application frequently accesses complete records. This makes it highly efficient for transactional processing that involves operations like CRUD (Create, Read, Update, Delete).
- **Limitations in Analytical Queries**: The main drawback of row-based storage is its performance in analytical queries that read specific columns from large datasets. Since data from a single column is spread across different blocks on the disk, the system must read through entire rows to retrieve the needed information, which can be slower and less efficient.


Column Based:
In columnar systems, each column of a table is stored separately, which means that reading a specific column's data can be done quickly without the need to process the rest of the row data. This is particularly beneficial for OLAP systems where queries often involve aggregations and scans over large volumes of data.

**Advantages and Disadvantages**
- **Efficiency in Analytical Queries**: The strength of columnar storage lies in its ability to quickly access and aggregate data across a wide dataset. It's optimized for read-heavy analytical processing, making it ideal for OLAP systems.
- **Limitations in Transactional Processing**: While columnar storage provides significant benefits for analytical querying, it is generally less efficient for transactional processing. The overhead of assembling and disassembling rows for operations that affect only a few columns can lead to performance bottlenecks in OLTP scenarios.