
Function:
```js
function reusableFunction(){
console.log("Hi World");
}
reusableFunction();

function functionWithArgs (x, y){
console.log(x+y);
}
functionWithArgs(5,6);

function timesFive (num){
  return num*5;
}
let b = timesFive(5);
```


### Anonymous Function:
```js
const myFunc = function() {
  const myVar = "value";
  return myVar;
}
```

```js
const myFunc = () => {
  const myVar = "value";
  return myVar;
}
```

```js
When there is no function body, and only a return value, arrow function syntax allows you to omit the keyword `return` as well as the brackets surrounding the code. This helps simplify smaller functions into one-line statements:

const myFunc = () => "value";
```

Passing arguments:
```js
const doubler = (item) => item * 2;
doubler(4)

const multiplier = (item, multi) => item * multi;
multiplier(4, 2);
```

```js
If an arrow function has a single parameter, the parentheses enclosing the parameter may be omitted.

const doubler = item => item * 2;
```

Default Parameter:
```js

const greeting = (name = "Anonymous") => "Hello " + name;

console.log(greeting("John"));
console.log(greeting());

The default parameter kicks in when the argument is not specified (it is undefined).
```

Rest Parameters:
```js
With the rest parameter, you can create functions that take a variable number of arguments. These arguments are stored in an array that can be accessed later from inside the function.

function howMany(...args) {
  return "You have passed " + args.length + " arguments.";
}
console.log(howMany(0, 1, 2));
console.log(howMany("string", null, [1, 2, 3], { }));

```

Spread Operator:
```js
const arr1 = ['JAN', 'FEB', 'MAR', 'APR', 'MAY'];
let arr2;
arr2 = [...arr1]; 
console.log(arr2);


const arr = [6, 89, 3, 45];
const maximus = Math.max(...arr);

...arr returns an unpacked array. In other words, it _spreads_ the array.
```
