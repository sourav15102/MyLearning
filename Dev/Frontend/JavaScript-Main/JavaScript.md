### Basic:

Diff b/w 'var' and 'let'
```js
var: 
you can easily overwrite variable declarations

var camper = "James";
var camper = "David";

When using var, no matter where we define the variable it is defiend as global.
```

```js
let:
A keyword called `let` was introduced in ES6, a major update to JavaScript, to solve this potential issue with the `var` keyword

If you replace `var` with `let` in the code above, it results in an error:

let camper = "James";
let camper = "David";

The error can be seen in your browser console.

When using let keyword, it maintains its scope, however, a local let i can override one defined global let i.
```

```js
const:
it has all the things that let has, but with added bonus, where it is read-only.
variable names are uppercase.

const XYZ = 6;

However, it is important to understand that objects (including arrays and functions) assigned to a variable using `const` are still mutable


const s = [5, 6, 7];
s = [1, 2, 3];
s[2] = 45;
console.log(s);
```

Use of quotes in JS:
if we need to use "sdfs: "adfs" sdfsd ", we use "ada: \"asda\" adsfs";

Immutable string: [[Strings]]
Arrays: [[Arrays]]
Function: [[Function]]
Anonymous Function: [[Function]]
if/else: [[if-else]]
Objects: [[Objects]]
Hoisting: [[My learnings/Dev/Frontend/JavaScript-Main/Hoisting]]
Class: [[Class]]
Export & Import: [[Export-Import]]
Promise: [[My learnings/Dev/Frontend/JavaScript-Main/Promise]]





