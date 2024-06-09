**Conflicts Resolution**:

- **Versioning**: Couchbase also maintains multiple versions of documents to handle conflicts. Each document has a unique key, and different versions of the same key can coexist.
- **Conflict Detection**: Similar to Riak, Couchbase uses vector clocks to detect conflicts. Vector clocks track the history of updates to documents across replicas.
- **Conflict Resolution**: Couchbase employs a variant of last write wins (LWW) conflict resolution strategy by default. In LWW, the version with the highest timestamp (last write) is considered the winning version. However, Couchbase also supports custom conflict resolution logic.
- **Timestamps**: Couchbase assigns timestamps to document versions to determine the order of updates. During conflict resolution, the version with the latest timestamp is chosen as the winner.