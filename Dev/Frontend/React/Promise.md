[[My learnings/Dev/Frontend/JavaScript-Main/Promise|Promise]]
So, when we call a asynchronous method, instead of giving the answer right away it gives us a "promise" object, which will represent the actual answer later on.

so we do this instead:
```js
  componentDidMount(){
    fetch('https://jsonplaceholder.typicode.com/users')
    .then((resp) => resp.json()) //.json is an asynchronous call as well.
    .then((resp) => { // then tell us what to do when we infact get the answer.
      this.setState(
        () => {
          return {monsters: resp};
        },
        () => {
          console.log(resp);
        }
      );
    });
    
  }
```

.then can only be called on resolved promise and .catch can only be called on rejected promise.

when .then returns something , it wraps it up in a resolve promise, so we can call another .then on it.

> when we write .then, behind the scenes  we are just attaching a call back function to the previous(asynchronous) function we called, that call back function means that whenever i get value back from that async function please call this callback on the successfully fetched results

