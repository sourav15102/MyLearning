**Conflicts Resolution**:

- **Versioning**: Riak also employs a versioning mechanism to handle conflicts. Each object in Riak has a unique key, and multiple versions of the same key can exist concurrently.
- **Conflict Detection**: Riak detects conflicts during the merge process when concurrent updates occur on different replicas. It compares the vector clocks associated with each version to determine if conflicts exist.
- **Conflict Resolution**: Riak uses vector clocks to resolve conflicts. It tracks causal relationships between versions using vector clocks, which are metadata associated with each version. Riak merges conflicting versions based on vector clock reconciliation.
- **Vector Clocks**: Vector clocks in Riak represent the partial ordering of updates to replicas. Riak uses vector clock reconciliation to merge conflicting versions, preserving causality and ensuring eventual consistency.

> No concept of partiton + sort key as it is key-value store.


