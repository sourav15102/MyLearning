
Objects:
```js
const cat = {
  "name": "Whiskers",
  "legs": 4,
  "tails": 1,
  "enemies": ["Water", "Dogs"]
};

numbers or one-word can be without quotes:
const cat = {
  "name": "Whiskers",
  legs: 4,
  5: 1,
  "enemies": ["Water", "Dogs"]
};

const myObj = {
  prop1: "val1",
  prop2: "val2"
};

const prop1val = myObj.prop1;
const prop2val = myObj.prop2;


const myObj = {
  "Space Name": "Kirk",
  "More Space": "Spock",
  "NoSpace": "USS Enterprise"
};

myObj["Space Name"];
myObj['More Space'];
myObj["NoSpace"]

Note that property names with spaces in them must be in quotes (single or double).

const testObj = {
  12: "Namath",
  16: "Montana",
  19: "Unitas"
};
const playerNumber = 16; 
const player = testObj[playerNumber];

Here's how we would add a `bark` property to `ourDog`:
ourDog.bark = "bow-wow";
or
ourDog["bark"] = "bow-wow";


delete ourDog.bark; or delete ourDog["bark"];

function checkObj(obj, checkProp) {
  if(obj.hasOwnProperty(checkProp)){
    return obj[checkProp];
  }else{
    return "Not Found";
  }
}

```


### Object Freeze
```js
As we know that we can change the array and objects even if they are defined as const:

let obj = {
  name:"FreeCodeCamp",
  review:"Awesome"
};
Object.freeze(obj);
obj.review = "bad";
obj.newProp = "Test";
console.log(obj);

```

Object Descrusting:
```js
const user = { name: 'John Doe', age: 34 };

const name = user.name;
const age = user.age;

const { name, age } = user;
// name and age has to be same as object
```

```js
If you want to give different names:

const { name: userName, age: userAge } = user;
```

```js
Another example:

const user = {
  johnDoe: { 
    age: 34,
    email: 'johnDoe@freeCodeCamp.com'
  }
};

const { johnDoe: { age, email }} = user;

const { johnDoe: { age: userAge, email: userEmail }} = user;
```

Array Deconstruction:
```js
const [a, b, ...arr] = [1, 2, 3, 4, 5, 7];
console.log(a, b);
console.log(arr);
```

Template literal:
```js
const person = {
  name: "Zodiac Hasbro",
  age: 56
};

const greeting = `Hello, my name is ${person.name}!
I am ${person.age} years old.`;

console.log(greeting);
```
1. the example uses backticks (`` ` ``), not quotes (`'` or `"`), to wrap the string. Secondly, notice that the string is multi-line, both in the code and the output. This saves inserting `\n` within strings.
2. The `${variable}` syntax used above is a placeholder. Basically, you won't have to use concatenation with the `+` operator anymore. To add variables to strings, you just drop the variable in a template string and wrap it with `${` and `}`. Similarly, you can include other expressions in your string literal, for example `${a + b}`. This new way of creating strings gives you more flexibility to create robust strings.

