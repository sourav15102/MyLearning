
### Export
When you export a variable or function, you can import it in another file and use it without having to rewrite the code.

```js
const add = (x, y) => {
  return x + y;
}

export { add };
```

You can export multiple things by repeating the first example for each thing you want to export
```js
export { add, subtract };
```

Export default:
It is used to define a fallback for module.
we can hav multiple exports in a module and do one export default.
then we can do following:
```js
// import named functions
import { foo, bar } from './myModule.js';

// import default function
import baz from './myModule.js';
```

### Import:
```js
import { add, subtract } from './math_functions.js';
```

```js
import * as myMathModule from "./math_functions.js";
```

