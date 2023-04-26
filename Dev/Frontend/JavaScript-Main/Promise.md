
A promise in JavaScript is exactly what it sounds like - you use it to make a promise to do something, usually asynchronously. When the task completes, you either fulfill your promise or fail to do so. `Promise` is a constructor function, so you need to use the `new` keyword to create one. It takes a function, as its argument, with two parameters - `resolve` and `reject`. These are methods used to determine the outcome of the promise. The syntax looks like this:

```js
const myPromise = new Promise((resolve, reject) => {

});
```

A promise has three states: `pending`, `fulfilled`, and `rejected`. The promise you created in the last challenge is forever stuck in the `pending` state because you did not add a way to complete the promise. The `resolve` and `reject` parameters given to the promise argument are used to do this. `resolve` is used when you want your promise to succeed, and `reject` is used when you want it to fail. These are methods that take an argument, as seen below.

```js
const myPromise = new Promise((resolve, reject) => {
  if(condition here) {
    resolve("Promise was fulfilled");
  } else {
    reject("Promise was rejected");
  }
});
```

The example above uses strings for the argument of these functions, but it can really be anything. Often, it might be an object, that you would use data from, to put on your website or elsewhere.

Promises are most useful when you have a process that takes an unknown amount of time in your code (i.e. something asynchronous), often a server request. When you make a server request it takes some amount of time, and after it completes you usually want to do something with the response from the server. This can be achieved by using the `then` method. ***The `then` method is executed immediately after your promise is fulfilled with `resolve`***. Here’s an example:

```js
myPromise.then(result => {
  
});
```

`result` comes from the argument given to the `resolve` method.

`catch` is the method used when your promise has been rejected. It is executed immediately after a promise's `reject` method is called. Here’s the syntax:

```js
myPromise.catch(error => {
  
});
```

