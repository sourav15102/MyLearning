### **`var` (Local Variable Type Inference):**
- **Purpose:** Introduced in Java 10, `var` allows you to declare local variables without explicitly specifying their type. The compiler infers the type from the initializer expression.
- **Benefits:**
    - **Conciseness:** Reduces boilerplate code, making code more readable, especially with complex types.
    - **Maintainability:** If the type of the initializer changes, you don't need to update the variable declaration (in most cases).
- **Limitations:**
    - **Only for local variables:** `var` cannot be used for instance variables, class variables, method parameters, or return types.
    - **Must have an initializer:** The compiler needs an initializer to infer the type. You can't declare `var x;` without assigning a value immediately.
    - **Type must be clear:** The initializer must provide enough information for the compiler to unambiguously determine the type.
- **Example:**
    Java
    ```java
    var name = "Alice"; // String
    var age = 30;       // int
    var price = 99.99;  // double
    var list = new ArrayList<String>(); // ArrayList<String>
    ```


### **Spread Operator (`...`):**
- **Purpose:** Introduced in Java 5 (though not called "spread" until later, as it was primarily for variable arguments), the spread operator allows you to pass a variable number of arguments to a method. It's also used with arrays.
- **Variable Arguments (varargs):**
    - **How it works:** When used in a method parameter list, `...` indicates that the method can accept zero or more arguments of the specified type. Inside the method, these arguments are treated as an array.
    - **Example:**
        ```java
        public static void printNumbers(int... numbers) {
            for (int number : numbers) {
                System.out.println(number);
            }
        }
        
        public static void main(String[] args) {
            printNumbers(1, 2, 3);      // Output: 1 2 3
            printNumbers(10, 20);       // Output: 10 20
            printNumbers();             // No output (zero arguments)
            int[] nums = {1, 2, 3};
            printNumbers(nums);         // Output: 1 2 3 (passing an array)
        
        }
        ```
        
- **Array Copying/Combining (Java 10+):** The spread operator can also be used with arrays to create copies or combine arrays.
    ```java
    int[] arr1 = {1, 2, 3};
    int[] arr2 = {4, 5, 6};
    
    int[] combined = {0, ...arr1, ...arr2, 7}; // {0, 1, 2, 3, 4, 5, 6, 7}
    int[] copy = {...arr1}; // creates a new array with the same elements as arr1
    ```

