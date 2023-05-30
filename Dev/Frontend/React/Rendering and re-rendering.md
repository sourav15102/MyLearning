Be it classes or functional hooks, these are just ways to tell react when to render and re-render diff parts of website.

Rendering: just loading html,css,js to be interacted by user.
something is done rendering when it is on the page and user can interact with it.
Two ways to render:
1. return statement of functional component.
2. render statement of class component. [[Classes and Local State]]


- SO, react has state and JSX.
- if on an event in JSX< we just change the state like this: it wont do anything, cos we haven't changed the state object in the way that react recognizes it to be changed.
```js
return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            {this.state.name}
          </p>
          <button onClick={()=>{
            this.state.name = 'SG';
          }}>
          Change name
          </button>        
        </header>
      </div>
    )
```

so, the reason why react isnt re-rendering is it says it will re-render when it has a different object.
What is happening here is upon instantiation, react has state object --> {} point to a object in memory, when we change object.name = "blah", object doesnt change actually.

So, if we can crate anoither obj, completelt, by taking previous obj as templat we can succeed.
```js
render(){
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            {this.state.name}
          </p>
          <button onClick={() => {
            this.setState({name: 'Sghai'}); //shallow merge, updat existing keys and add rest.
          }}>
          Change name
          </button>        
        </header>
      </div>
    )
  }
```


