
### **Situation**

At MathWorks, the existing **Managerial Metric Tracking System** was slow, outdated, and only displayed raw numbers without providing actionable insights. Managers found it challenging to interpret the data, and the system was underutilized due to its lack of visual clarity and poor performance.

---

### **Task**

During an internal hackathon, I proposed and initiated a project to redesign the system into a **full-stack application** that would be fast, visually intuitive, and provide detailed analytics. The goal was to make it easy for managers to track employee metrics, analyze trends, and make informed decisions.

---

### **Action**

#### **1. Backend Architecture**:

- **Framework**: Built the backend using **Spring Boot** for its scalability, modular design, and seamless integration with APIs.
- **Database**:
    - Used **HBase** (NoSQL) for its high-performance, low-latency analytics capabilities, enabling the storage and retrieval of large amounts of employee metric data.
    - HBase was optimal because it supported **column-oriented storage**, which is efficient for queries involving aggregations and time-series analytics.
- **Data Pipeline**:
    - Every week, the system fetched employee metrics from multiple APIs (e.g., project tracking tools, timesheets) using **scheduled Spring Boot jobs**.
    - The aggregated data was transformed into structured records and stored in HBase, ensuring that analytical queries could run without impacting source systems.

---

#### **2. Frontend Architecture**:

- **Framework**: Used **Angular** for its dynamic component-based architecture, which allowed for building responsive and modular UI components.
- **Data Visualization**:
    - Leveraged **Chart.js** for interactive graphs and dashboards, providing insights into trends like employee productivity, project timelines, and resource allocation.
    - Created custom Angular components for drill-down visualizations, enabling managers to view team-level or individual-level metrics on demand.
- **Optimizations**: Implemented **lazy loading** and **state management** with **NgRx**, ensuring the app remained fast even with large datasets.

---

#### **3. Features Delivered**:

- **Graphical Dashboards**: Replaced raw numerical tables with interactive charts and heatmaps, providing actionable insights.
- **Role-Based Views**: Designed dashboards for both **managers** (team-level metrics) and **employees** (personal performance insights).
- **Search and Filtering**: Added advanced search and filter options to allow managers to focus on specific teams, projects, or time periods.
- **API Integration**: Integrated seamlessly with existing tools, ensuring no disruption to the existing workflows.

---

### **Result**

1. **Performance Improvement**: Reduced average load time from **10 seconds to under 2 seconds**, making the system far more responsive.
2. **User Adoption**: Managerial adoption increased by **50%**, as the system became a go-to tool for performance analysis.
3. **Scalability**: The use of HBase ensured the system could scale effortlessly with the growing dataset, handling **millions of records** without latency.
4. **Actionable Insights**: The graphical dashboards enabled managers to make **data-driven decisions**, such as reallocating resources or identifying performance bottlenecks.

---

### **Technical Summary**

- **Backend**: Spring Boot, HBase, RESTful APIs.
- **Frontend**: Angular, Chart.js, NgRx for state management, lazy loading for optimization.
- **Data Pipeline**: Scheduled API calls for weekly data ingestion, transformation, and storage.

---

This story showcases technical innovation, user-focused design, and the ability to scale a project from a hackathon to full production, while solving real business pain points. Let me know if you'd like refinements!