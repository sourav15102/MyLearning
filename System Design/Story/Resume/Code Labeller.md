
**Improved Explanation**: The Code Labeller project is a platform specifically designed for a university, enabling professors to upload code snippets tagged with custom-built tags. Students can log in to view these projects, tag code snippets with the provided tags, and contribute to code analysis collaboratively.

The architecture consists of:
1. **Backend**:
    - Built with a monolithic architecture using REST APIs.
    - Created entities like `Projects`, `CodeSnippets`, `Tags`, `Users` (professors and students differentiated by roles).
    - AWS S3 was used for storing code snippets, with URLs stored in an RDS MySQL database.
    - One-to-many and many-to-many relationships between projects, code snippets, and tags.
2. **Frontend**:
    - A React-based UI for a seamless user experience, allowing tagging, code browsing, and project management.
3. **Authentication**:
    - Implemented JWT for securing endpoints, with role-based access control for professors and students.

This architecture ensured scalability and a smooth tagging experience for users.

**How Did You Integrate JWT Authentication with MySQL?**
[[My learnings/Interviewprep/Java/JWT]] (JSON Web Tokens) provides stateless, secure authentication for APIs. Here's how you can integrate JWT with MySQL:
1. **Steps**:
    - On successful login (validated against the `users` table in MySQL), a JWT token is generated.
    - The token contains a payload (e.g., user ID, role) and is signed with a secret key.
    - On each request, the token is verified and decoded to validate user access.
    - [[Bearer Token]] is typically the format in which the JWT is transmitted and used for authentication and authorization.
1. **Architecture**:
    - **Login Endpoint**:
        1. Validate user credentials against the MySQL database.
        2. If valid, generate a JWT using libraries like `jjwt` in Java.
        3. Send the token to the client.
    - **Middleware**:
        1. Intercept requests to secure endpoints.
        2. Validate the token, decode it, and attach user information to the request object.
2. **Code Snippet (Java)**:
```java
// Generate Token
String token = Jwts.builder()
    .setSubject(user.getUsername())
    .claim("role", user.getRole())
    .setIssuedAt(new Date())
    .setExpiration(new Date(System.currentTimeMillis() + 3600000))
    .signWith(SignatureAlgorithm.HS256, "yourSecretKey")
    .compact();

// Decode Token
Claims claims = Jwts.parser()
    .setSigningKey("yourSecretKey")
    .parseClaimsJws(token)
    .getBody();
```


[[JPA and Hibernate]]
### **Agile Methodology**
You can mention **Scrum** o depending on what fits:
- **Scrum**:
    - Sprint-based planning with daily stand-ups.
    - Involved two-week sprints, backlog grooming, sprint reviews, and retrospectives.

[[SonarQube]]

### **Storing Code Statements in Code Snippets Against Tags**
1. **Frontend Implementation**:
    - The HTML code snippet was broken down into **multiple `<span>` tags**, each representing a segment of code.
    - A library (possibly `CodeMirror` or `Monaco Editor`) was used for syntax highlighting and to split the code into manageable segments.
    - Each span was assigned an identifier (e.g., a range or line-column combination).
    - When a user selected a portion of code and tagged it, the selection’s **start and end indices** (or span range) were captured.
2. **Backend Storage**:
    - The selected range and associated tag were sent via a REST API to the backend.
    - Data was stored in a relational database:
        - **`Code_Snippets` Table**:
            - `id`, `project_id`, `s3_url` (link to the stored file in AWS S3).
        - **`Tags` Table**:
            - `id`, `name`, `description`.
        - **`Snippet_Tags` Table** (Many-to-Many relationship):
            - `snippet_id`, `tag_id`, `range_start`, `range_end`.
        - **`Student_Tags` Table**:
            - Tracks which student tagged which portion of the code.

This structure allowed flexibility in tagging and easy retrieval of tagged code snippets for the frontend.

### **Authentication and Authorization Framework**
1. **University-Only Access**:
    - Ensured only university members could access the platform using:
        - **Domain-Based Email Validation**:
            - Restricted registration to emails ending with `@university.edu` using regex or email domain checks
2. **Framework Used**:
	- **Authentication**:
	    - Implemented using **JWT (JSON Web Tokens)**:
	        - Tokens were generated upon successful login.
	        - Each token included the user’s role (student/professor) and was signed with a secret key.
	- **Authorization**:
	    - Role-Based Access Control (RBAC):
	        - Professors could upload and manage projects/snippets.
	        - Students could only view and tag snippets. 

### **Reducing Technical Debt**
Instead of just naming patterns, here’s how specific anti-patterns were addressed in your project:
1. **God Object**:
    - **Problem**: A single `CodeService` class handled uploading files, processing tags.
    - **Solution**:
        - Split responsibilities into specialized services:
            - `FileService`: Handled S3 uploads and URL generation.
            - `TagService`: Managed tag creation and updates.
2. **Hardcoding Logic**:
    - **Problem**: Hardcoded S3 bucket names and email domains in multiple classes.
    - **Solution**:
        - Externalized configurations to a `config.properties` or `.env` file.
        - Example:
		    ```
		    S3_BUCKET_NAME=my_bucket 
		    EMAIL_DOMAIN=university.edu
		    ```
1. **Tight Coupling**:
	- **Problem**: `CodeService` directly instantiated `FileService` and `TagService`.
	- **Solution**:
	    - Used Dependency Injection (via Spring Boot):
	    
```java
	@Service
public class CodeService {
    private final FileService fileService;
    private final TagService tagService;

    @Autowired
    public CodeService(FileService fileService, TagService tagService) {
        this.fileService = fileService;
        this.tagService = tagService;
    }
}

```

1. **Duplicated Code**:
	- **Problem**: Multiple functions across classes fetched tagged snippets.
	- **Solution**:
	    - Abstracted reusable logic into utility classes like `SnippetUtils`.


### **Managing Edge Cases in Testing**
1. **Input Validation**:
    - Wrote unit tests for:
        - Invalid formats in tag names (e.g., special characters).
        - Oversized code snippets exceeding database limits.