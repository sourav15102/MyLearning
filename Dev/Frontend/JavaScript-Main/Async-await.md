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

1. we need use `await` inside a `async` function
2. every `async` function returns promise
3. we can add `await` to any function returning `promise`.
4. so basically what it does it when we use `await` in front of any `async` function, whatever value that `async` function had when it resolved using `resolve(xyz)`, instead of passing that value as parameter to the function inside of `.then`, it just returns the value directly.
5. 
