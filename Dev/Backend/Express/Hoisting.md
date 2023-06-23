Hoisting is a behavior in JavaScript where variable and function declarations are moved to the top of their containing scope during the compilation phase, before the code is executed. This means that you can use variables and functions before they are actually declared in your code.

However, it's important to note that hoisting only moves the declarations, not the initializations or assignments. Let's look at some examples to illustrate how hoisting works:

1. Variable Hoisting:
```javascript
console.log(x); // Output: undefined
var x = 5;
console.log(x); // Output: 5
```
In this example, the variable `x` is hoisted to the top of its scope (global scope in this case). However, only the declaration of `x` is hoisted, not the assignment. So, when we try to access `x` before its declaration, it will output `undefined`.

2. Function Hoisting:
```javascript
sayHello(); // Output: "Hello!"

function sayHello() {
  console.log("Hello!");
}
```
In this case, the function `sayHello` is hoisted to the top of its scope (global scope in this case). The entire function declaration, including the function body, is hoisted. This allows us to call the function before its actual declaration in the code.

However, it's important to note that function expressions (functions assigned to variables) are not hoisted in the same way. Only the variable declaration is hoisted, not the function assignment itself. Here's an example to illustrate this:
```javascript
sayHello(); // Error: sayHello is not a function

var sayHello = function() {
  console.log("Hello!");
};
```
In this example, the variable `sayHello` is hoisted, but its assignment to the function is not. As a result, when we try to call `sayHello` before the assignment, it results in an error.

To avoid issues related to hoisting, it is generally recommended to declare variables and functions before using them in your code, even though JavaScript allows you to use them before their declarations due to hoisting. This practice can make your code more readable and reduce the chances of encountering unexpected behavior.