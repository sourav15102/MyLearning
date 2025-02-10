It uses Hypertable and its Chunk tables:

![[Pasted image 20241219010221.png]]- It uses _SSTables_ (Sorted String Tables) and _LSM Trees_ (_Log-Structured Merge_-_Trees_)

Example:
- **InfluxDB**: Balances availability and partition tolerance, offering tunable consistency (e.g., through replication factor and quorum settings).
- **TimescaleDB**: Prioritizes strong consistency due to its PostgreSQL foundation but can scale horizontally with Citus for distributed setups.
- **Prometheus**: Emphasizes availability and partition tolerance in its single-node design; federation provides redundancy.
