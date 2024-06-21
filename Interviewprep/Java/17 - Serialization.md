- We can serialize object.
- static fields do not belong to object so cant be serializable.
- transient keyword: prevents field from being serialized
	- even if value is assigned in class, like
	- private int x = 6;
	- after deserialization it will be 0, cos?
	- during deserilization whenever it sees transient variable it will just give it 0,null.
	- IF, it is final then its value is stored in constant pool and will be 6.
	- if it is private final transient String x = new String().. it will be not in constant pool so null.

How to serialize objects:
```java
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    static String country = "ITALY";
    private int age;
    private String name;
    transient int height;

    // getters and setters
}
```

```java
@Test 
public void whenSerializingAndDeserializing_ThenObjectIsTheSame() () 
  throws IOException, ClassNotFoundException { 
    Person person = new Person();
    person.setAge(20);
    person.setName("Joe");
    
    FileOutputStream fileOutputStream
      = new FileOutputStream("yourfile.txt");
    ObjectOutputStream objectOutputStream 
      = new ObjectOutputStream(fileOutputStream);
    objectOutputStream.writeObject(person);
    objectOutputStream.flush();
    objectOutputStream.close();
    
    FileInputStream fileInputStream
      = new FileInputStream("yourfile.txt");
    ObjectInputStream objectInputStream
      = new ObjectInputStream(fileInputStream);
    Person p2 = (Person) objectInputStream.readObject();
    objectInputStream.close(); 
 
    assertTrue(p2.getAge() == person.getAge());
    assertTrue(p2.getName().equals(person.getName()));
}
```

- Caveats
When a class implements the _java.io.Serializable_ interface, all its sub-classes are serializable as well. Conversely, when an object has a reference to another object, these objects must implement the _Serializable_ interface separately, or else a _NotSerializableException_ will be thrown:

```java
public class Person implements Serializable {
    private int age;
    private String name;
    private Address country; // must be serializable too
}
```

If one of the fields in a serializable object consists of an array of objects, then all of these objects must be serializable as well, or else a _NotSerializableException_ will be thrown.

- Custom Serialization in Java
Java specifies a default way to serialize objects, but Java classes can override this default behavior. Custom serialization can be particularly useful when trying to serialize an object that has some unserializable attributes. We can do this by providing two methods inside the class that we want to serialize:

```java
private void writeObject(ObjectOutputStream out) throws IOException;
```

and

```java
private void readObject(ObjectInputStream in) 
  throws IOException, ClassNotFoundException;
```

With these methods, we can serialize the unserializable attributes into other forms that we can serialize:

```java
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient Address address;
    private transient Details details;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject(); // This line writes the non-static and non-transient fields.
        oos.writeObject(address.getHouseNumber());
        oos.writeObject(details.getInfo());
        // The order here matters, should be same while reading 
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject(); 
        Integer houseNumber = (Integer) ois.readObject(); 
        String detailsInfo = (String) ois.readObject();
		
        Address a = new Address();
        a.setHouseNumber(houseNumber);
        this.setAddress(a);

        Details d = new Details();
        d.setInfo(detailsInfo);
        this.setDetails(d);
    }
}
```

> ### Deserialization (`readObject` method)
1. `ois.defaultReadObject();` - This line reads the non-static and non-transient fields of the current object from the stream using the default mechanism.
2. `Integer houseNumber = (Integer) ois.readObject();` - This line reads the next object from the stream, which should be the house number (an `Integer`), in the same order it was written.
3. `String detailsInfo = (String) ois.readObject();` - This line reads the next object from the stream, which should be the details info (a `String`), again in the same order it was written.



```java
public class Address {
    private int houseNumber;

    // setters and getters
}
```

We can run the following unit test to test this custom serialization:

```java
@Test
public void whenCustomSerializingAndDeserializing_ThenObjectIsTheSame() 
  throws IOException, ClassNotFoundException {
    Person p = new Person();
    p.setAge(20);
    p.setName("Joe");

    Address a = new Address();
    a.setHouseNumber(1);

    Employee e = new Employee();
    e.setPerson(p);
    e.setAddress(a);

    FileOutputStream fileOutputStream
      = new FileOutputStream("yourfile2.txt");
    ObjectOutputStream objectOutputStream 
      = new ObjectOutputStream(fileOutputStream);
    objectOutputStream.writeObject(e);
    objectOutputStream.flush();
    objectOutputStream.close();

    FileInputStream fileInputStream 
      = new FileInputStream("yourfile2.txt");
    ObjectInputStream objectInputStream 
      = new ObjectInputStream(fileInputStream);
    Employee e2 = (Employee) objectInputStream.readObject();
    objectInputStream.close();

    assertTrue(
      e2.getPerson().getAge() == e.getPerson().getAge());
    assertTrue(
      e2.getAddress().getHouseNumber() == e.getAddress().getHouseNumber());
}
```

In this code, we can see how to save some unserializable attributes by serializing _Address_ with custom serialization. Note that we must mark the unserializable attributes as _transient_ to avoid the _NotSerializableException._

> This customization can be used make a child class, un-serializable.

Q: how does it know what class's object are we trying to deserialize
```java
Student s = (Student) in.readObject();
```
A:
The Java deserialization process knows which class's object to deserialize based on the data present in the serialized stream. When you serialize an object using `ObjectOutputStream`, it includes metadata in the serialized data that specifies the class of the object being serialized. This metadata includes the fully qualified class name of the object's class.

Q: What is Externalizable?
A:
The Externalizable interface is used to write the state of an object into a byte stream in a compressed format. It is not a marker interface.

Q: What is the difference between Serializable and Externalizable interface?
A:

|No.|Serializable|Externalizable|
|---|---|---|
|1)|The Serializable interface does not have any method, i.e., it is a marker interface.|The Externalizable interface contains is not a marker interface, It contains two methods, i.e., writeExternal() and readExternal().|
|2)|It is used to "mark" Java classes so that objects of these classes may get the certain capability.|The Externalizable interface provides control of the serialization logic to the programmer.|
|3)|It is easy to implement but has the higher performance cost.|It is used to perform the serialization and often result in better performance.|
|4)|No class constructor is called in serialization.|We must call a public default constructor while using this interface.|

