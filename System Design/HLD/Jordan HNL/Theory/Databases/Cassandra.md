### Conflict detection:
Conflicts among two nodes in Cassandra are detected when there are different writes on same row, how would you identify if row is same, by its partition key.

Table 1:
```
| User ID | Follower |
| ------- | -------- |
| X       | A        |
| X       | B        |
| X       | C        |
```

Table 2:
```
| User ID | Follower |
| ------- | -------- |
| X       | A        |
| X       | B        |
| Y       | D        |
```

There are no conflicts in this scenario because each node is writing to different rows with different partition keys. In Cassandra, each row within a partition is uniquely identified by its row key and partition key combination. Since the nodes are writing to different partitions (different User IDs in this case), there is no conflict. Each node is responsible for its own partition, and updates to different partitions can occur concurrently without conflicts.

### **Conflicts Resolution**:

- **Versioning**: Cassandra uses a combination of partition keys and clustering columns to uniquely identify rows. Each row can have multiple versions, and conflicts can occur if updates happen concurrently.
- **Conflict Detection**: Cassandra detects conflicts during the reconciliation process when concurrent updates occur on different replicas. It compares the timestamps associated with different versions to identify conflicts.
- **Conflict Resolution**: Cassandra supports multiple conflict resolution strategies, including last write wins (LWW) and user-defined conflict resolution. By default, Cassandra uses the timestamp of the most recent write to resolve conflicts. However, developers can implement custom conflict resolution logic based on application requirements.

