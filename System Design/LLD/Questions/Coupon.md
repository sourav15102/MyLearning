1. Functional requirements
	1. People should be able to get coupon for specific user.
	2. People should be able to get coupon for specific item and quantity.
	3. People should be able to get coupon for specific total price.
	4. Coupon has Discount
	5. Discount: percentage, cap
2. Actors: Use Case diagram
	1. Coupon: ID, Discount, `Contraint[]`
	2. Order: coupons[]
3. Entities: Class Diagram
4. Implementation

> We can use [[Decorator]] pattern here, Coupon abstract class can implement Order interface, which has get total method.

> Alternatively, we can have Coupon as Product, and while adding each product i.e. coupon cart and getting its total price, we get it via decorator pattern.

https://www.youtube.com/watch?v=EfQesfKZ3Jw&list=PL6W8uoQQ2c61X_9e6Net0WdYZidm7zooW&index=39&pp=iAQB