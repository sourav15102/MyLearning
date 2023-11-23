[[My learnings/Dev/Frontend/JavaScript-Main/Promise|Promise]]
https://www.youtube.com/watch?v=spvYqO_Kp9Q

```js
async function executeAsyncFunction() {
  try {
    await fakeAsyncFunction();
    console.log('Promise resolved successfully');
  } catch (error) {
    console.error('An error occurred:', error);
  }
}

executeAsyncFunction();

```

1. we need use `await` inside a `async` function, and it is to be used in front of fcn returning promise (or await)
2. every `async` function returns promise
3. we can add `await` to any function returning `promise`.
4. so basically what it does it when we use `await` in front of any `async` function, whatever value that `async` function had when it resolved using `resolve(xyz)`, instead of passing that value as parameter to the function inside of `.then`, it just returns the value directly.
5. 


```js
async function outerAsyncFunction() {
  console.log('Start outer function');

  // This pauses the execution of outerAsyncFunction until innerAsyncFunction is resolved.
  await innerAsyncFunction();

  console.log('After inner function');
}

async function innerAsyncFunction() {
  console.log('Start inner function');

  // This pauses the execution of innerAsyncFunction until the promise is resolved.
  await someAsyncOperation();

  console.log('After async operation in inner function');
}

function someAsyncOperation() {
    console.log('inside SAO');
  return new Promise(resolve => {
    setTimeout(() => {
      resolve('Async operation completed');
    }, 1000);
  });
}

outerAsyncFunction();
console.log('Function calls are not blocked');
```
Output:
```
Start outer function
Start inner function
inside SAO
Function calls are not blocked
After async operation in inner function
After inner function
```

Asynchronicity is added by remote calls, i/o calls, setTimeout anything that might take time

```js
function outerFunction() {

  console.log('Start outer function');

  

  innerFunction()

    .then(() => {

      console.log('After inner function');

    });

}

  

function innerFunction() {

  console.log('Start inner function');

  

  return someAsyncOperation()

    .then(() => {

      console.log('After async operation in inner function');

    });

}

  

function someAsyncOperation() {

  console.log('inside SAO');

  return new Promise(resolve => {

    setTimeout(() => {

      resolve('Async operation completed');

    }, 1000);

  });

}

  

outerFunction();

console.log('Function calls are not blocked');
```


Note:
- await will stop the execution at its line, while, if we use .then.. it will continue.
- and if you want async function to coninue, just simply call it and dont use await in front of it.