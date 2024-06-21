TypeScript is a superset of JavaScript. It is an object-oriented and tightly typed [programming language](https://www.simplilearn.com/tutorials/programming-tutorial/first-programming-language "programming language"). TypeScript code is transformed to JavaScript, which may be used in any environment that supports JavaScript, including browsers, [Node.js,](https://www.simplilearn.com/tutorials/nodejs-tutorial/what-is-nodejs "Node.js,") and your own applications.

```js
// Variables and Types
let isDone: boolean = false;
let decimal: number = 6;
let color: string = "blue";

// Class and Interface
interface Person {
  firstName: string;
  lastName: string;
}

class Student implements Person {
  constructor(public firstName: string, public lastName: string) {}
}

// Decorator
function sealed(target: Function): void {
  Object.seal(target);
  Object.seal(target.prototype);
}

@sealed
class Greeter {
  greeting: string;
  constructor(message: string) {
    this.greeting = message;
  }
}
```

### JS:
JavaScript is a high-level, interpreted programming language that is primarily known for its use in web development. It operates on a single-threaded event loop model, which is essential to understanding how JavaScript works internally and how it handles asynchronous operations. Let's break down how JavaScript works internally and then explain how asynchronous operations are handled in a single-threaded environment.

### How JavaScript Works Internally

1. **JavaScript Engine**: JavaScript code runs in a JavaScript engine. Popular engines include Google's V8 (used in Chrome and Node.js), SpiderMonkey (used in Firefox), and JavaScriptCore (used in Safari). The engine performs several key tasks:
   - **Parsing**: The engine reads the JavaScript code and converts it into an Abstract Syntax Tree (AST).
   - **Compilation/Interpretation**: The AST is either interpreted directly or compiled into bytecode or machine code. V8, for example, compiles JavaScript into machine code for faster execution.
   - **Execution**: The engine executes the compiled/interpreted code.

2. **Memory Management**: The engine handles memory allocation and garbage collection. It allocates memory for objects and variables, and it uses garbage collection algorithms (like Mark-and-Sweep) to free up memory that is no longer in use.

### JavaScript's Event Loop and Asynchronous Operations

JavaScript's ability to handle asynchronous operations despite being single-threaded is due to its event loop, along with the use of the call stack, task queue (or callback queue), and other specialized queues like the microtask queue.

#### Key Components:

1. **Call Stack**: A stack data structure that keeps track of the function calls. When a function is called, it is pushed onto the stack, and when it returns, it is popped off the stack.

2. **Web APIs**: Browser-provided or environment-provided (like Node.js) APIs that handle asynchronous operations. Examples include `setTimeout`, `fetch`, DOM events, etc. These are not part of the JavaScript engine but are provided by the host environment.

3. **Task Queue**: A queue where callback functions from asynchronous operations (like `setTimeout` or I/O operations) are placed once their associated work is completed.

4. **Microtask Queue**: A specialized queue for microtasks, which are usually promises. Microtasks have higher priority than tasks in the task queue.

5. **Event Loop**: The mechanism that coordinates the execution of code, handling events, and executing queued tasks. It continuously checks the call stack and task queue, ensuring that tasks are executed in the right order.

#### How Asynchronous Operations Work:

1. **Execution Context**: When JavaScript code starts executing, the call stack processes synchronous code sequentially.

2. **Asynchronous Operation Initiation**: When an asynchronous operation is encountered (e.g., `setTimeout`, network request, promise), the engine hands it off to the Web APIs or Node.js APIs.

3. **Background Work**: The asynchronous operation is handled in the background (outside the JavaScript engine).

4. **Queueing the Callback**: Once the asynchronous operation is completed, its callback (or promise resolution) is placed in the task queue or microtask queue.

5. **Event Loop Processing**:
   - The event loop checks if the call stack is empty.
   - If the call stack is empty, it first processes all microtasks in the microtask queue.
   - Then, it processes one task from the task queue and pushes its callback onto the call stack for execution.

### Example of Asynchronous Operation:

```javascript
console.log('Start');

setTimeout(() => {
  console.log('Timeout');
}, 1000);

Promise.resolve().then(() => {
  console.log('Promise');
});

console.log('End');
```

**Execution Flow**:
1. `'Start'` is logged immediately (synchronous).
2. `setTimeout` is called, and its callback is placed in the Web API for handling.
3. `Promise.resolve().then` is called, and its `.then` callback is placed in the microtask queue.
4. `'End'` is logged immediately (synchronous).
5. The call stack is now empty, so the event loop processes the microtasks first. `'Promise'` is logged.
6. After the microtask queue is empty, the event loop processes the task queue. `'Timeout'` is logged after approximately 1000ms.

JavaScript provides a rich set of built-in methods, particularly for arrays, which allow for efficient data manipulation and transformation. Here’s a rundown of some of the most commonly used array methods along with their purposes and examples:

### Array Methods

1. **forEach**
   - **Purpose**: Executes a provided function once for each array element.
   - **Syntax**: `array.forEach(callback(currentValue, index, array))`
   - **Example**:
     ```javascript
     const numbers = [1, 2, 3];
     numbers.forEach(number => console.log(number));
     // Output: 1, 2, 3
     ```

2. **map**
   - **Purpose**: Creates a new array populated with the results of calling a provided function on every element in the calling array.
   - **Syntax**: `array.map(callback(currentValue, index, array))`
   - **Example**:
     ```javascript
     const numbers = [1, 2, 3];
     const squared = numbers.map(number => number * number);
     console.log(squared);
     // Output: [1, 4, 9]
     ```

3. **filter**
   - **Purpose**: Creates a new array with all elements that pass the test implemented by the provided function.
   - **Syntax**: `array.filter(callback(element, index, array))`
   - **Example**:
     ```javascript
     const numbers = [1, 2, 3, 4];
     const evenNumbers = numbers.filter(number => number % 2 === 0);
     console.log(evenNumbers);
     // Output: [2, 4]
     ```

4. **reduce**
   - **Purpose**: Executes a reducer function on each element of the array, resulting in a single output value.
   - **Syntax**: `array.reduce(callback(accumulator, currentValue, index, array), initialValue)`
   - **Example**:
     ```javascript
     const numbers = [1, 2, 3, 4];
     const sum = numbers.reduce((acc, number) => acc + number, 0);
     console.log(sum);
     // Output: 10
     ```

5. **find**
   - **Purpose**: Returns the value of the first element in the array that satisfies the provided testing function.
   - **Syntax**: `array.find(callback(element, index, array))`
   - **Example**:
     ```javascript
     const numbers = [1, 2, 3, 4];
     const firstEven = numbers.find(number => number % 2 === 0);
     console.log(firstEven);
     // Output: 2
     ```

6. **findIndex**
   - **Purpose**: Returns the index of the first element in the array that satisfies the provided testing function.
   - **Syntax**: `array.findIndex(callback(element, index, array))`
   - **Example**:
     ```javascript
     const numbers = [1, 2, 3, 4];
     const firstEvenIndex = numbers.findIndex(number => number % 2 === 0);
     console.log(firstEvenIndex);
     // Output: 1
     ```

7. **some**
   - **Purpose**: Tests whether at least one element in the array passes the provided function. Returns a boolean value.
   - **Syntax**: `array.some(callback(element, index, array))`
   - **Example**:
     ```javascript
     const numbers = [1, 2, 3, 4];
     const hasEven = numbers.some(number => number % 2 === 0);
     console.log(hasEven);
     // Output: true
     ```

8. **every**
   - **Purpose**: Tests whether all elements in the array pass the provided function. Returns a boolean value.
   - **Syntax**: `array.every(callback(element, index, array))`
   - **Example**:
     ```javascript
     const numbers = [2, 4, 6];
     const allEven = numbers.every(number => number % 2 === 0);
     console.log(allEven);
     // Output: true
     ```

9. **includes**
   - **Purpose**: Determines whether an array includes a certain value among its entries. Returns a boolean value.
   - **Syntax**: `array.includes(valueToFind, fromIndex)`
   - **Example**:
     ```javascript
     const numbers = [1, 2, 3, 4];
     const includesThree = numbers.includes(3);
     console.log(includesThree);
     // Output: true
     ```

10. **concat**
    - **Purpose**: Merges two or more arrays. This method does not change the existing arrays but returns a new array.
    - **Syntax**: `array1.concat(array2, array3, ..., arrayN)`
    - **Example**:
      ```javascript
      const array1 = [1, 2];
      const array2 = [3, 4];
      const mergedArray = array1.concat(array2);
      console.log(mergedArray);
      // Output: [1, 2, 3, 4]
      ```

11. **slice**
    - **Purpose**: Returns a shallow copy of a portion of an array into a new array object selected from start to end (end not included).
    - **Syntax**: `array.slice(start, end)`
    - **Example**:
      ```javascript
      const numbers = [1, 2, 3, 4];
      const slicedArray = numbers.slice(1, 3);
      console.log(slicedArray);
      // Output: [2, 3]
      ```

12. **splice**
    - **Purpose**: Changes the contents of an array by removing or replacing existing elements and/or adding new elements in place.
    - **Syntax**: `array.splice(start, deleteCount, item1, item2, ..., itemN)`
    - **Example**:
      ```javascript
      const numbers = [1, 2, 3, 4];
      numbers.splice(2, 1, 'a', 'b');
      console.log(numbers);
      // Output: [1, 2, 'a', 'b', 4]
      ```

13. **join**
    - **Purpose**: Joins all elements of an array into a string and returns this string.
    - **Syntax**: `array.join(separator)`
    - **Example**:
      ```javascript
      const elements = ['Fire', 'Air', 'Water'];
      const joinedElements = elements.join('-');
      console.log(joinedElements);
      // Output: 'Fire-Air-Water'
      ```

14. **sort**
    - **Purpose**: Sorts the elements of an array in place and returns the sorted array.
    - **Syntax**: `array.sort(compareFunction)`
    - **Example**:
      ```javascript
      const numbers = [3, 1, 4, 1, 5, 9];
      numbers.sort((a, b) => a - b);
      console.log(numbers);
      // Output: [1, 1, 3, 4, 5, 9]
      ```

15. **reverse**
    - **Purpose**: Reverses the order of the elements in an array in place.
    - **Syntax**: `array.reverse()`
    - **Example**:
      ```javascript
      const numbers = [1, 2, 3, 4];
      numbers.reverse();
      console.log(numbers);
      // Output: [4, 3, 2, 1]
      ```

### Summary

These array methods provide a wide range of functionalities for data manipulation and transformation. Understanding these methods and their use cases allows for more efficient and readable code when working with arrays in JavaScript.
