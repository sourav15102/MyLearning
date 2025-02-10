### **Situation**

 The customer from University, a neuroscience professor, for processing data, reported **performance bottlenecks** while processing **large data files (>10GB)** in their analytics pipeline. This delay violated their SLA of **45 minutes per file**, with processing times extending up to **60 minutes or more** during peak usage.

The issue lay in MATLAB's backend pipeline, particularly in handling **I/O operations, serialization, and data flow**. I initially committed to delivering a **50% performance improvement**, believing I could fully optimize the backend. However, architectural limitations posed challenges to achieving this ambitious goal.

---

### **Task**
My objective was to:
1. Identify and resolve bottlenecks in the **data pipeline framework** (using **DataJoint**) while staying compliant with the **45-minute SLA**.
2. Design and implement a scalable solution to handle large files efficiently within a tight timeline.
4. To diplomatically manage the customer’s expectations, refusing their demand for a complete elimination of delays while maintaining their trust.


---

### **Action**
#### **1. Root Cause Analysis**
- Conducted a detailed performance profiling using **MATLAB Profiler** to analyze the backend system. Key findings included:
    - **Serialization Overhead**: MATLAB’s default **MAT-files** serialization format added **15-20% additional processing time** due to unnecessary metadata storage and lack of compression.
    - **Data Flow Inefficiencies**: DataJoint’s pipeline was optimized for **small batches**, but larger files caused memory spikes due to single-threaded operations and linear file processing.

---

### **EG Data: Types of Human Brain Data**
1. **Raw EEG Signals**
2. Profile info
3. Reaction times
4. Reaction patterns
5. Metdata
### **Suggestions Given to the Customer**
1. **Preprocessing Files to Meet SLA**:
    - Split datasets exceeding 5GB into smaller chunks before uploading.
    - Example: Divide 10GB files into two 5GB parts.
2. **They suggested Streaming-Based Processing**: Isnt supported by DatJoiny
3. **Prioritize Essential Data**:
    - Analyze and remove redundant or unnecessary variables from files before submitting them for analysis.


#### **2. Breaking Down Large Files for Streaming I/O**

- Instead of reading files sequentially, I split them into **five parts** and processed each part in parallel:
```matlab
file = 'largefile.csv';
totalLines = countLines(file); 
chunkSize = floor(totalLines / 5); % Divide into 5 chunks
for i = 1:5
    startLine = (i-1)*chunkSize + 1;
    endLine = min(i*chunkSize, totalLines);
    processChunk(file, startLine, endLine); % Process part of the file
end

```

- Conducted benchmarks on the new approach:
    - Sequential processing took **60 minutes** for a 10GB file.
    - Dividing the file into **5 chunks** reduced processing time by **40%, down to 36 minutes**.
    - Enabled files to be processed incrementally, reducing memory utilization by **50%**.

---

#### **3. Serialization Optimization**

- The pipeline originally used MATLAB’s **MAT-files** format, which serialized both raw data and unnecessary metadata.
- Switched to a **custom binary serialization format** that:
    - Serialized only essential data (e.g., numeric arrays).
    - Applied gzip compression to reduce file sizes by **30%**.
    - This reduced serialization time by **15%** while improving storage efficiency.

---

#### **4. Managing SLA Compliance and Customer Expectations**

- Held weekly review meetings with the customer to keep them informed of progress.
- Communicated the architectural limitations of MATLAB’s single-threaded operations and DataJoint’s small-batch optimization.
- Iteratively refined the solution with their feedback, ensuring partial performance improvements were delivered incrementally.

---

### **Result**

1. Achieved a **40% improvement in processing times**, reducing the average file processing time from **60 minutes to 36 minutes**.
2. Improved memory utilization by **50%**, enabling stable processing of large datasets.
3. Maintained SLA compliance by aligning the customer’s expectations with feasible deliverables.
4. Documented the solution as part of a reusable framework for large-scale data processing, improving future pipeline performance by **20%**.

---

### **Follow-Up Questions to Prepare**

#### **1. Why did you commit to a 50% performance improvement initially?**

- _(Answer)_: The commitment was based on **initial profiling**, where I identified areas like serialization and I/O inefficiencies that showed significant potential for optimization. However, I underestimated the architectural constraints of MATLAB’s single-threaded backend and DataJoint’s small-batch design, which limited achievable improvements within the timeline.

#### **2. What were the architectural limitations that prevented the full 50% improvement?**

- MATLAB’s backend relies heavily on **single-threaded operations**, which restricted the degree of parallelism achievable in the pipeline.
- DataJoint’s design was optimized for **smaller batches** of data, and processing large files required additional modifications to handle memory and throughput effectively.
- Serialization overhead from **MAT-files** added a fixed delay that couldn’t be completely eliminated without transitioning to a fully different backend.

#### **3. Why didn’t you use external distributed systems for parallel processing?**

- The system was tightly integrated with MATLAB, and introducing external distributed systems (e.g., Spark or Hadoop) would have required a significant architectural overhaul and violated SLA timelines. Instead, I focused on improving the system within the existing framework.

#### **4. How did splitting files into 5 parts reduce I/O time?**

- Instead of sequentially reading and processing a 10GB file, splitting it into 5 parts allowed **parallel processing** across worker threads. Each part was smaller and faster to read, reducing memory spikes and disk I/O contention.
- This approach avoided the need for the system to load the entire dataset into memory at once, which previously caused significant delays.

#### **5. What were the risks of using a custom serialization format?**

- Using a custom binary format risked **compatibility issues** with existing downstream tools and required additional testing to ensure robustness. To mitigate this, I created a fallback mechanism that allowed serialized data to be reverted to the default MAT-file format in case of failures.


Answers to Questions
1. Why was DataJoint struggling with large files?

    DataJoint is a framework designed for managing scientific workflows, particularly in neuroscience. It is optimized for relational data models and batch processing of small to medium-sized datasets.
    Limitations:
        In-Memory Operations: DataJoint loads intermediate results and metadata into memory, which becomes problematic for datasets exceeding memory capacity.
        Batch-Oriented Design: It’s not designed for efficient streaming or handling very large datasets in a single transaction.
        Indexing Overhead: Relational models and queries introduce overhead, especially for large, unoptimized data operations.




#### **What Serialization Was Originally Doing**

- **MATLAB’s Default MAT-File Serialization** stored unnecessary metadata alongside the actual data, leading to overheads. The extra information included:
    1. **Variable Descriptions**:
        - Detailed descriptions and hierarchy for every variable, even if redundant or unused.
        - Example: `EEG_signal_block: "EEG signals from sensor Fp1 sampled at 1000 Hz"`.
    2. **Intermediate States**:
        - Stored transient variables and partial results from prior computations, adding unnecessary bulk.
        - Example: Temporary arrays created during preprocessing (e.g., filtered signals or partially normalized values).
    3. **Endianness and Compatibility Data**:
        - Included unnecessary endianness markers to enable cross-platform compatibility, which was irrelevant as the environment was uniform.
    4. **Redundant Metadata for Structs**:
        - Stored full hierarchy details for nested structs, even for small, straightforward datasets.
        - Example: Storing detailed schema information for `ReactionPatterns` and `ReactionTimes` fields.

These inefficiencies caused **15-20% additional overhead** in both size and processing time.

---

#### **What We Did Differently in Custom Serialization**

1. **Raw Data Focus**:
    
    - Serialized only the **essential data**:
        - Raw EEG signals (numeric arrays).
        - Compact metadata like sampling rate and channel names, stripped of redundant descriptions.
        - Reaction times and patterns as simple arrays, omitting structural hierarchies.
    - Skipped storing temporary or intermediate variables.
2. **Optimized Encoding**:
    
    - Used **binary encoding** for numeric arrays:
        - Reduced size by converting variables directly into a compact binary format.
    - Applied **gzip compression** to minimize storage size further without affecting read performance.