There are two different types of objects that reside in Heap memory, referenced and unreferenced. Referenced objects are those that still have active references within the application, whereas unreferenced objects don’t have any active references.

**The garbage collector removes unreferenced objects periodically, but it never collects the objects that are still being referenced.** This is where memory leaks can occur:

[![Memory Leak In Java](https://www.baeldung.com/wp-content/uploads/2018/11/Memory-_Leak-_In-_Java.png)](https://www.baeldung.com/wp-content/uploads/2018/11/Memory-_Leak-_In-_Java.png)

#### Solution:

1. Try-with-resources
A resource is an object that must be closed once your program is done using it. For example, a File resource or a Socket connection resource.  The try-with-resources statement ensures that each resource is closed at the end of the statement execution.

> You can pass any object as a resource that implements _java.lang.AutoCloseable_, which includes all objects which implement java.io.Closeable.

Now, let us discuss both the possible scenarios which are demonstrated below as an example as follows:
- **Case 1**: Single resource
- **Case 2:** Multiple resources

Case 1:
```java
		// Try block to check for exceptions
		try (

			// Creating an object of FileOutputStream
			// to write stream or raw data

			// Adding resource
			FileOutputStream fos
			= new FileOutputStream("gfgtextfile.txt")) {

			// Custom string input
			String text
				= "Hello World. This is my java program";

			// Converting string to bytes
			byte arr[] = text.getBytes();

			// Text written in the file
			fos.write(arr);
		}

		// Catch block to handle exceptions
		catch (Exception e) {

			// Display message for the occurred exception
			System.out.println(e);
		}

```

Case 2:
```java
		try (FileOutputStream fos
			= new FileOutputStream("outputfile.txt");

			// Adding resource

			// Reading the stream of character from
			BufferedReader br = new BufferedReader(
				new FileReader("gfgtextfile.txt"))) {

			// Declaring a string holding the
			// stream content of the file
			String text;

			// Condition check using readLine() method
			// which holds true till there is content
			// in the input file
			while ((text = br.readLine()) != null) {

				// Reading from input file passed above
				// using getBytes() method
				byte arr[] = text.getBytes();

				// String converted to bytes
				fos.write(arr);

				// Copying the content of passed input file
				// 'inputgfgtext' file to outputfile.txt
			}

			// Display message when
			// file is successfully copied
			System.out.println(
				"File content copied to another one.");
		}

		// Catch block to handle generic exceptions
		catch (Exception e) {

			// Display the exception on the
			// console window
			System.out.println(e);
		}
```


2. Minimum usage of static variables:
	1. If collections or large objects are declared as _static_, then they remain in the memory throughout the lifetime of the application, thus blocking vital memory that could otherwise be used elsewhere.
3. Proper implementation of overridden hashCode and equals methods
	1. While using Object (Person) as a key of HashMap, which generally doesnt allow duplicate keys but since we didnt implement hashCode and equals for Person, it would know under what conditions two Person objects are equal, henc, hashmap would contain duplicate keys, resulting in memory leaks.

References:
1. https://www.geeksforgeeks.org/try-with-resources-feature-in-java/
2. https://www.baeldung.com/java-memory-leaks

Q: How circular references are handled by Java GC?
A: https://stackoverflow.com/questions/1910194/how-does-java-garbage-collection-work-with-circular-references