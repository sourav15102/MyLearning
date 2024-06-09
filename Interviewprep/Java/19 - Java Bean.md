
A JavaBean is just a [standard](http://www.oracle.com/technetwork/java/javase/documentation/spec-136004.html). It is a regular Java `class`, except it follows certain conventions:
In simple terms, JavaBeans are [classes](https://www.edureka.co/blog/java-objects-and-classes/#javaclass) which encapsulate several [objects](https://www.edureka.co/blog/java-object/) into a single object. It helps in accessing these object from multiple places.

1. All properties are private (use [getters/setters](http://en.wikipedia.org/wiki/Mutator_method))
2. A public [no-argument constructor](http://en.wikipedia.org/wiki/Nullary_constructor)
3. Implements [`Serializable`](http://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html).

it is a reusable software component. A bean encapsulates many objects into one object so that we can access this object from multiple places. Moreover, it provides the easy maintenance.

### Pojo:
Any class that can be used on its own.
1. no extending class
2. no implementing interface
3. no annotations


