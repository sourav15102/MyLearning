
### Component:
- it contains all template and logic for that component.
- component is basically a function, and it returns  jsx template and we export that function so that it can be used elsewhere.
- function name (component  name) has to start with capital letter.
- the type of code returned by component is called [[My learnings/Dev/Frontend/JSX]].
- at the end we export our component function, so that we can use it other files.
- we can write any valid js code inside function, before we return jsx template.

**Root Component**:
- it is something which is rendered by index.js, it is rendered first.
- it is at the top of hierarchy.
- all the other components created are nested inside this one.
- and more components could be nested inside these ones and so on.
![[Pasted image 20230316013638.png]]

### Syntax:
```javascript
import './App.css';

function App() { // this is one way of creeating function, another way is arrow function
const title = "hello"; // varaible is created using const
const num = 50; // even though num is int, react will convert everything to string before output to browser, even an array, BUT boolean and objects cannot be output.
  return (
    <div className="App">
      <div className="content">
      <h1>{ title }</h1>
      <p>{ num }</p>
      </div>
    </div>
  );
}

export default App;
```
**Arrow Function**
```javascript
const Navbar = () => {
    return (
    );
}
export default Navbar;
```
