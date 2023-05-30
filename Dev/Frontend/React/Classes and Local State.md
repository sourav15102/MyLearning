
```js
import logo from './logo.svg';
import './App.css';
import { Component } from 'react';

class App extends Component {
  constructor(){
    super();
    this.state = { // LOCAL STATE
      name: 'Sourav'
    };
  }

  render(){
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            {this.state.name}
          </p>
          <button>Change name</button>        
        </header>
      </div>
    )
  }
  
}

export default App;

```