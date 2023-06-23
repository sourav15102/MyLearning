Middleware in Express is a function that can perform tasks, modify requests or responses, and pass control to the next middleware or route handler in the chain. It allows you to add functionality and handle common tasks in a flexible way.

In Express, middleware functions are executed sequentially in the order they are defined. When a request comes in, Express starts executing the middleware functions that match the route before reaching the route handlers.

### what will happen if I dont call next in middleware function?
If you don't call `next()` within a middleware function, the request will hang or timeout, and the subsequent middleware or route handlers will not be executed.

In Express, the `next()` function is used to pass control to the next middleware or route handler in the chain. If you don't call `next()`, the request will not be forwarded to the next middleware or route handler, causing it to be stuck in the current middleware.

Here's an example to illustrate the behavior without calling `next()`:

```javascript
let express = require('express');
let app = express();

let middleware = (req, res, next) => {
  console.log('This middleware will not call next()');
  // next() is not called here
};

app.use(middleware);

app.get('/', (req, res) => {
  console.log('This route handler will not be reached');
});

app.listen(3000, () => {
  console.log('Server listening on port 3000');
});
```

In this example, the middleware function is defined without calling `next()`. When a request is made to `'/'`, the middleware will be executed, and it will log a message to the console. However, since `next()` is not called, the subsequent route handler (`app.get('/')`) will not be reached, and the response will never be sent. As a result, the request will hang indefinitely.

To avoid this issue, always remember to call `next()` within your middleware function to pass control to the next middleware or route handler in the chain.


# Chain Middleware to Create a Time Server

Middleware can be mounted at a specific route using `app.METHOD(path, middlewareFunction)`. Middleware can also be chained within a route definition.

Look at the following example:

```js
app.get('/user', function(req, res, next) {
  req.user = getTheUserSync();  // Hypothetical synchronous operation
  next();
}, function(req, res) {
  res.send(req.user);
});
```

This approach is useful to split the server operations into smaller units. That leads to a better app structure, and the possibility to reuse code in different places. This approach can also be used to perform some validation on the data. At each point of the middleware stack you can block the execution of the current chain and pass control to functions specifically designed to handle errors. Or you can pass control to the next matching route, to handle special cases. We will see how in the advanced Express section.