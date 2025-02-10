**Conflicts Resolution**:

CouchDB employs a master-master replication model, meaning that updates can be made to any replica, and these updates will be eventually propagated to all other replicas. When conflicts occur due to concurrent updates on different replicas, CouchDB uses a mechanism called multi-version concurrency control (MVCC) to manage conflicts.

Here's how CouchDB handles conflicts among master-master replicas:

1. **Versioning**: CouchDB maintains multiple versions of each document to handle conflicts. When a document is updated, CouchDB creates a new revision of the document, preserving the previous revisions. Each revision is assigned a unique revision ID.
    
2. **Conflict Detection**: When updates occur concurrently on different replicas, CouchDB detects conflicts during the replication process. It compares the revision IDs of the incoming updates with the revision IDs of the existing document versions.
    
3. **Conflict Resolution**: When a conflict is detected, CouchDB doesn't automatically resolve it. Instead, it stores all conflicting revisions of the document and marks the document as conflicted. It's then up to the application logic or the user to resolve the conflict.
    
4. **Conflict Resolution Strategies**: CouchDB provides several conflict resolution strategies:
    
    - **Last Write Wins (LWW)**: CouchDB can automatically resolve conflicts by choosing the revision with the highest revision ID (the "last write") as the winning revision. This strategy is suitable for scenarios where conflicts are rare and it's acceptable to lose some updates.
    - **Custom Conflict Resolution**: Developers can implement custom conflict resolution logic tailored to their application's requirements. This allows for more nuanced conflict resolution strategies based on application-specific criteria.