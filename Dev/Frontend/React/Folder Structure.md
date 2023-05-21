Folder structure:
src: minimum files required to use react:
src/:
	- index.js: entry point for react project.
		- react: react engine
		- reactDOM: for building web applications, can be reactNative for mobile apps
		- <App /> is main componeny
		- react.strictmode: whatever code is wirtten inder `App` component, react will check for depricated code.
		- so, react renders the 'App' in 'root' of index.html file


Folders:
- node_modules: contain dependencies including react
- public: files public to browser live here, contains index.html one html file serve to the browsere, all react code is injected here.
- src: almost all react code/component will go here.
- App.js : already created component for us.
- index.js: kickstarts our app, takes all react component and render it.
- 
