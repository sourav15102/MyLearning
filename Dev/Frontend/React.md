### Theory:
- it helps create SPAs(Single Page Applications)
	- SPAs, or Single Page Applications, are web applications that load and render a single HTML page dynamically as the user interacts with the application. In a SPA, the server only sends the initial HTML, CSS, and JavaScript files, and subsequent content is loaded via AJAX requests, without reloading the entire page. This allows for a more responsive and fluid user experience, as well as faster loading times and better performance.
- The flow of multipage applications:
	- When a user interacts with a multi-page application, the flow generally involves the following steps:
	
	1.  The user opens a page in their web browser by entering a URL or clicking a link. This triggers an HTTP request to the server hosting the application.
	2.  The server receives the request and processes it to determine which page the user is requesting. If the page requires dynamic content, the server may need to make requests to APIs or a database to retrieve the necessary data.
	3.  Once the server has all the necessary data, it generates an HTML document containing the page content and sends it back to the user's browser as an HTTP response.
	4.  The browser receives the response and renders the HTML to display the requested page to the user.
	5.  If the user interacts with the page, such as submitting a form or clicking a link to navigate to a different page, the browser sends another HTTP request to the server and the process repeats.

### Component:
- it contains all template and logic for that component.
- component is basically a function, and it returns  jsx template and we export that function so that it can be used elsewhere.
- function name (component  name) has to start with capital letter.
- the type of code returned by component is called jsx.
	- jsx allows you to write HTML-like code within your JavaScript code. However, the JSX code is ultimately compiled down to regular JavaScript code that the browser can understand.
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

Folders:
- node_modules: contain dependencies including react
- public: files public to browser live here, contains index.html one html file serve to the browsere, all react code is injected here.
- src: almost all react code/component will go here.
- App.js : already created component for us.
- index.js: kickstarts our app, takes all react component and render it.

Questions:
- Q) in SPA when server send page to client, does it sends all the component included
- A) Most modern SPA frameworks, such as React, Angular, and Vue.js, use a component-based architecture, where each component is responsible for rendering a specific part of the application's user interface. When the client receives the initial page from the server, it may include the necessary components to render the initial view, but additional components may be loaded later on-demand as the user interacts with the application. For example, if the initial view of an SPA is a dashboard that includes several widgets, the server might send the initial HTML, CSS, and JavaScript files that are needed to render the dashboard view, as well as the components needed to render each widget. However, if the user clicks on a widget that opens a modal dialog, the server may not have sent the HTML, CSS, and JavaScript files required to render that modal dialog, and instead, the client-side JavaScript code would fetch those files on-demand from the server.
- Q) Keeping in mind the question above, how is it different from multipage applications?
- A)

