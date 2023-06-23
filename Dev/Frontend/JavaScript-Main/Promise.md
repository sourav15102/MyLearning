Link: https://www.youtube.com/watch?v=TnhCX0KkPqs
Promise Creator:
```js
function fakeAsyncFunction() {
  return new Promise((resolve, reject) => {
	  if(condition here) {
	    resolve("Promise was fulfilled");
	  } else {
	    reject("Promise was rejected");
	  }
	});
}

```
- Every async func returns a promise object which takes another func as a parameter and it just tells it to run whatever there is inside that func and in the end, either resolve or reject it.

Promise Receiver:
```js
fakeAsyncFunction()
  .then(() => { //F2
    console.log('Promise resolved successfully');
  })
  .catch((error) => {
    console.error('An error occurred:', error);
  });

```
- here, it just tell it call the async func, and when it get resolves, whatever there is in the `resolve(xyz)` method i.e. `xyz` just call the function(F2) inside `.then` with `xyz` as its parameter. 