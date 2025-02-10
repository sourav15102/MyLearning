### **1. How Spring Boot Handles Exception Management**

Spring Boot provides a powerful mechanism to handle exceptions globally or at a controller level using **`@ControllerAdvice`** and **`@ExceptionHandler`**.

---

#### **`@ControllerAdvice`**

- **Purpose**:
    
    - A specialized annotation used to define **global exception handlers**.
    - Applies to multiple controllers.
- **How It Works**:
    
    - When an exception is thrown in any controller, Spring looks for a handler method annotated with `@ExceptionHandler` in the `@ControllerAdvice` class.
- **Example**:
    
    ```java
    @ControllerAdvice
    public class GlobalExceptionHandler {
    
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleGenericException(Exception ex) {
            return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    ```
    

---

#### **`@ExceptionHandler`**

- **Purpose**:
    
    - Handles specific exceptions within a controller or globally (when combined with `@ControllerAdvice`).
- **How It Works**:
    
    - Attach it to a method that handles the specified exception.
    - You can return:
        - **Custom responses** (e.g., error messages, HTTP status codes).
        - Views (for web applications).
- **Example at the Controller Level**:
    
    ```java
    @RestController
    public class UserController {
    
        @GetMapping("/user/{id}")
        public User getUser(@PathVariable int id) {
            if (id <= 0) {
                throw new ResourceNotFoundException("User not found");
            }
            return new User(id, "John Doe");
        }
    
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<String> handleException(ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    ```
    

---
