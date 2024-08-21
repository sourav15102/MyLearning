NPM vs NPX
```
NPM is a installation vs executable env.
We can use npm to download and install packages gobally/locally to be used by us.

Instead what npx does is, that we can use npx to use a library without keeping it,
Behind the scenes, npx downloads library, executes it and uninstall it.
By default it uses the latest version of library.

Like create-react-app
```

Entry point
```
it is index.js file which uses index.html residing in public folder.
```

react-scripts: start, build, test, eject
```
start:
it uses all the code in application and pushes through the port

build:
It builds an optimized/low memory version of the code.

test:
helps us run the test files.

eject:
Different browsers understadn JS/HTML/CSS differently, maybe some of them can undertsand very old version of JS. 
In build we use libraries babel and webpack:
Babel: it tried to build the react code into very basic JS.
Webpack: it makes code more efficient, it modularize the code for optimization.
Eject helps us modify the bable and webpack, it will eject the file for us to be modified.
```

manifest. json
```
it helps us be progressive web app (PWA) compliant.
```

JSX:
```
syntax extension of Javascript, it allows us to write html like code
```

When react renders or re-render our app:
```
1. It re-renders the component when the state of the component is completely different. (different state object)
 
When we do setState and updates the state, the function shallow merges, and just updates the corresponding keys regardless of how complex the previous value of that key was.

When we click on button, and shallow merge of the state happens, it happens asynchronously, thats why the console.log will print the previous value of the state.

2. When props change
3. When parent component re-renders.
4. Context changes
```

```js
import logo from './logo.svg';
import './App.css';
import { Component } from 'react';

class App extends Component {
  constructor(){
    super();

    this.state = {
      name: 'XTYZ',
      age: '28'
    };
  }

  changeName = () => {
    this.setState({name: 'Sourav'});
    console.log(this.state);
  }

  render(){
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            { this.state.name } { this.state.age }
          </p>
          <button onClick={this.changeName}>Change Name</button>
        </header>
      </div>
    );
  }
}

export default App;
```

Why setState is asynchronous?
```
It is because React bacthes the setStates, and finds the most optimal way to re-render the components.
```

Different version of setState
```js
this.setState(()=>{return {name: 'Sourav'} }, ()=>{console.log(this.state);});
```
```
the first callback returns the obj which will be alter shallow merged, the 2nd one runs after the state has been actually updated.
```

key value in array
```
each element in array has to have a unique key because it is used to render elements optimally by React
React uniquely idenitifies elements from array and re-redners only the changed elements.
So, whenever we return elements by map, remember to include key in highest element of the return.
```
```js
class App extends Component {
  constructor(){
    super();

    this.state = {
      monsters: [
        {
          id: '1',
          name: 'Sourav'
        },
        {
          id: '2',
          name: 'Ghai'
        },
        {
          id: '3',
          name: 'Varos'
        },
        {
          id: '4',
          name: 'Iagh'
        }
      ]
    };
  }

  changeName = () => {
    this.setState(()=>{return {name: 'Sourav'} }, ()=>{console.log(this.state);});
  }

  render(){
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          {
            this.state.monsters.map((monster)=>{
              return <h1 key={monster.id}>{ monster.name }</h1>
            })
          }
        </header>
      </div>
    );
  }
  
}
```

Lifecycles:
```
constructor: it always runs first, it is used to initalize the state
```
```js
 constructor(){
    super();
    this.state = {
      monsters: []
    };
  }
```
```
renders: it runs next in component, it return what we need to show.
```
```
componentDidMount:
This function is called when the component is mounted on the DOM for the first time.
the only time, it will be called again if the component has been unmounted - compeltely removed from the DOM, i.e the new instance of component is here now..
```

Props:
```
```