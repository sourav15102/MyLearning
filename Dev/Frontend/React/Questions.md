
Questions:
- Q) in SPA when server send page to client, does it sends all the component included
- A) Most modern SPA frameworks, such as React, Angular, and Vue.js, use a component-based architecture, where each component is responsible for rendering a specific part of the application's user interface. When the client receives the initial page from the server, it may include the necessary components to render the initial view, but additional components may be loaded later on-demand as the user interacts with the application. For example, if the initial view of an SPA is a dashboard that includes several widgets, the server might send the initial HTML, CSS, and JavaScript files that are needed to render the dashboard view, as well as the components needed to render each widget. However, if the user clicks on a widget that opens a modal dialog, the server may not have sent the HTML, CSS, and JavaScript files required to render that modal dialog, and instead, the client-side JavaScript code would fetch those files on-demand from the server.
- Q) Keeping in mind the question above, how is it different from multipage applications?
- A)
