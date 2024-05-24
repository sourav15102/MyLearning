When to use:
1. When we need to hide system's complexity from clients.
2. For example, what if client class wanted to place an order, which includes, get product, make payment, ship product, either client class can call all those on its own, or it can simply call facade's method which can handle all complexity.
3. SO, facade is something that clients talks to, and then facade interacts with the system.
4. We shouldn't force clients to use facade (it is optional), if client wants they can use the system directly.
5. Facade can also use other facades.

Facade vs Proxy
- It act has a proxy for a obejct.
- It kinds of handle requests coming towards the actual service's object.
- Where as the facade acts as a director for interactions between multiple objects.

Facade vs Adaptor