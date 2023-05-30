
Whenever we need to make an API call in order to get data to render page, we make that call in this function.
There is an order in which these 3 fcns are called by react
1. constructor: super(), set template for state
2. render: mounts component, template
3. componentDidMount: after it mounts, this fcn is called.
