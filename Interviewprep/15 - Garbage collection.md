
Garbage Collection (GC) effectively manages memory in the Java Virtual Machine (JVM) by tracking and removing unused objects from the heap space. The process involves two main steps: Mark and Sweep.
1. **Mark Phase:**
   - Identifies actively used memory by objects in the heap.
   - Determines which memory segments are in use and which are not.
2. **Sweep Phase:**
   - Removes objects that have been marked as unused during the Mark phase.
   - Reclaims memory occupied by these unused objects, making it available for future allocation.

Advantages of Garbage Collection:
1. **Automatic Memory Management:**
   - Eliminates the need for manual memory allocation and deallocation, reducing programming complexity.
2. **Dangling Pointer Prevention:**
   - Automatically handles pointers that reference memory locations no longer valid, avoiding potential crashes or erratic behavior.
3. **Automatic Memory Leak Management:**
   - Although GC doesn't eliminate all memory leaks, it helps prevent a significant portion by reclaiming memory from unreachable objects.
Disadvantages of Garbage Collection:
1. **CPU Overhead:**
   - Garbage Collection adds additional CPU overhead due to tracking object references, potentially affecting the performance of memory-intensive requests.
2. **Lack of Control:**
   - Programmers don't have direct control over when and how GC occurs, leading to uncertainty about the scheduling of memory deallocation.
3. **Unpredictable Stops:**
   - In some cases, certain Garbage Collection implementations might lead to unexpected pauses or stops in the application's execution, impacting user experience.
4. **Efficiency vs. Manual Management:**
   - While automated memory management is convenient, it may not be as efficient as meticulous manual memory allocation and deallocation by skilled programmers.

types:

| GC Type       | Threading  | Freezing Impact | Application Responsiveness |
|---------------|------------|-----------------|---------------------------|
| Serial GC     | Single     | Freezes App     | Not Responsive            |
| Parallel GC   | Multi      | Freezes App     | Not Responsive            |
| CMS GC        | Multi      | Minimal Freeze  | Slower Response           |
| G1 GC         | Multi      | Minimal Freeze       | Responsive                |

> It runs concurrently like the CMS collector, but it very rarely freezes execution and can collect both the young and old generations concurrently.
> G1 most efficient.

>New GC: Z GC:
 With G1GC, the average pause time: few milliseconds to a few seconds, depending on the size of the heap and other factors. 
 ZGC aims to have consistently low pause times, : < 10 milliseconds, regardless of the heap size.

> It only cleans objects created by new keyword.


**Q: What is gc()?**
A:
suggest JVM to garbage collect, it is not necessary that JVM will do that.
```java
1. public class TestGarbage1{  
2.  public void finalize(){System.out.println("object is garbage collected");}  
3.  public static void main(String args[]){  
4.   TestGarbage1 s1=new TestGarbage1();  
5.   TestGarbage1 s2=new TestGarbage1();  
6.   s1=null;  
7.   s2=null;  
8.   System.gc();  
9.  }  
10. }
```

**Q: Who controls garbage collection?**
A:
JVM, it is performed when there is not enough space in the memory and memory is running low.

Manually invoking `System.gc()` may not necessarily result in an immediate or guaranteed garbage collection, and it can even have performance implications since it can disrupt the natural memory management and garbage collection process.

**Q: How can an object be unreferenced?**
A:
There are many ways:
- By nulling the reference
- By assigning a reference to another
- By anonymous object etc.
```java
new Employee();
```

Q: What is finalize method?
A:
- finalize method of obj is invoked just before the object is garbage collected
- used to perform cleanup processing.
- It is NOT a keyword
- GC of JVM collects objects created by new keyword. So obj created without new can use the finalize method to perform cleanup processing
	- However, there might be cases where objects are created using mechanisms other than `new`, such as using native methods or external resources. In such cases, the JVM's garbage collector might not be aware of these objects.
```java
public class ResourceCleanupExample {
    private NativeResource nativeResource;

    public ResourceCleanupExample() {
        nativeResource = new NativeResource(); // Assume this resource is managed externally
    }

    public void performOperation() {
        // Do some operations using the nativeResource
        System.out.println("Performing operation using nativeResource");
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            // Cleanup and release external resources in the finalize method
            nativeResource.cleanup();
        } finally {
            super.finalize();
        }
    }

    public static void main(String[] args) {
        ResourceCleanupExample example = new ResourceCleanupExample();
        example.performOperation();

        // At this point, the 'example' object might become unreachable

        // Explicitly suggest garbage collection (Note: This is just a suggestion)
        System.gc();

        // Other operations in the application

        // The 'finalize' method might be called before object deallocation
    }
}

class NativeResource {
    // Simulate native resource
    public void cleanup() {
        System.out.println("Cleaning up native resource");
    }
}

```


Q: How can a unreferenced obj be ressuracted?
A:
```java
public class Resurrect {

    static Resurrect resurrect = null;

    public static void main(String[] args) {
        Resurrect localInstance = new Resurrect();
        System.out.println("first instance: " + localInstance.hashCode());

        // after this code there is no more reference to the first instance
        localInstance = new Resurrect();
        System.out.println("second instance: " + localInstance.hashCode());

        // will (in this simple example) request the execution of the finalize() method of the first instance
        System.gc(); 
    }

    @Override
    public void finalize() {
        resurrect = this;
        System.out.println("resurrected: " + resurrect.hashCode());
    }
}
```

**Q: What kind of thread is the Garbage collector thread?**
A: Daemon thread.

Q: What is Daemon Thread in Java? 
A: Daemon thread in Java is **a low-priority thread that performs background operations such as garbage collection, finalizer, Action Listeners, Signal dispatches, etc**.

**Q: what is runtime class in java**
A:
the **Runtime class** is used to interact with Every Java application that has a single instance of class Runtime that allows the application to interface with the environment in which the application is running. The current runtime can be obtained from the getRuntime() method: we get a single object here from a singleton class.
```java
`Runtime run = Runtime.getRuntime();`
```

| Method                      | Action Performed                                                                                |
|-----------------------------|--------------------------------------------------------------------------------------------------|
| addShutdownHook(Thread hook)| Registers a new virtual-machine shutdown hook thread.                                        |
| availableProcessors()       | Returns the number of processors available to the JVM (Java virtual machine)                   |
| exec(String command)        | Executes the given command in a separate process                                              |
| exec(String[] cmd)          | Executes the specified command and arguments in a separate process.                            |
| exec(String command, String[] envp, File dir) | Executes the specified string command in a separate process with the specified environment and working directory. |
| exec(String command, String[] envp) | Executes the specified string command in a separate process with the specified environment.  |
| exec(String[] cmdarray, String[] envp, File dir) | Executes the specified command and arguments in a separate process with the specified environment and working directory. |
| exec(String[] cmdarray, String[] envp) | Executes the specified command and arguments in a separate process with the specified environment.  |
| exit(int status)            | Terminates the currently running Java virtual machine by initiating its shutdown sequence.      |
| freeMemory()                | Returns the amount of free memory in the JVM(Java Virtual Machine)                              |
| gc()                        | Runs the garbage collector. Calling this method suggests that the Java virtual machine expands effort toward recycling unused objects in order to make the memory they currently occupy available for quick reuse. |
| getRuntime()                | Returns the instance or Runtime object associated with the current Java application              |
| halt(int status)            | Forcibly terminates the currently running Java virtual machine. This method never returns normally. This method should be used with extreme caution. |
| load(String filename)       | Loads the specified filename as a dynamic library. The filename argument must be a complete pathname. |
| loadLibrary(String libname) | Loads the dynamic library with the specified library name. A file containing code is loaded from the local system from a place where library files are conventionally obtained. |
| maxMemory()                 | Returns the maximum amount of memory that the Java virtual machine will attempt to use. If there is no inherent limit then the value Long.MAX_VALUE will be returned |
| removeShutdownHook(Thread hook) | De-registers a previously-registered virtual machine shutdown hook.                            |
| runFinalization()           | Runs the finalization methods of any objects pending finalization. It suggests that JVM (Java virtual machine) expands effort toward running the finalize methods of objects that have been found to be discarded but whose finalize methods have not yet been run. |
| totalMemory()               | Returns the amount of total memory in the JVM(Java Virtual Machine)                             |
| traceInstructions(boolean a)| Enables or disables tracing of instructions. If the boolean argument is true then it will suggest that the JVM emits debugging information for each instruction in the virtual machine as it is executed. |
| traceMethodCalls(boolean a) | Enables or disables tracing of method calls. If the boolean argument is true then it will suggest that the Java virtual machine emits debugging information for each method in the virtual machine as it is called. |



**Q: How will you invoke any external process in Java?**
A:
```java
1. public class Runtime1{  
2.  public static void main(String args[])throws Exception{  
3.   Runtime.getRuntime().exec("notepad");//will open a new notepad  
4.  }  
5. }
```


