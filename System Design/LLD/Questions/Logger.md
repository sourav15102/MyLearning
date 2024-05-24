https://www.youtube.com/watch?v=gvIn5QBdGDk&list=PL6W8uoQQ2c61X_9e6Net0WdYZidm7zooW&index=12

We use [[Chain of Responsibility]] pattern here:
- This pattern can be used for [[ATM]] as well
- With input x and notes section 200,500,2000.
- it pass through each section and see if and how much each section can make contribution.

### Code:
Logger.java
```java
public abstract class Logger {  
    public static final String INFO = "Info";  
    public static final String ERROR = "Error";  
    public static final String DEBUG = "Debug";  
  
    private Logger nextLogger = null;  
  
    Logger(Logger nextLogger){  
        this.nextLogger = nextLogger;  
    }  
  
    public void handle(String handle, String message){  
        if(this.nextLogger!=null){  
            this.nextLogger.handle(handle, message);  
        }  
    }  
  
    public static void main(String[] args) {  
        Logger logger = new InfoLogger(new DebugLogger(new ErrorLogger(null)));  
        logger.handle("Error", "Heyyy!!");  
    }  
}
```

InfoLogger.java
```java
public class InfoLogger extends Logger{  
    InfoLogger(Logger nextLogger) {  
        super(nextLogger);  
    }  
  
    @Override  
    public void handle(String handle, String message){  
        if(handle.equals(Logger.INFO)){  
            System.out.println("Info" + message);  
        }else{  
            super.handle(handle,message);  
        }  
    }  
}
```

ErrorLogger.java
```java
public class ErrorLogger extends Logger{  
    ErrorLogger(Logger nextLogger) {  
        super(nextLogger);  
    }  
    @Override  
    public void handle(String handle, String message){  
        if(handle.equals(Logger.ERROR)){  
            System.out.println("Error" + message);  
        }else{  
            super.handle(handle,message);  
        }  
    }  
}
```

DebugLogger.java
```java
public class DebugLogger extends Logger{  
    DebugLogger(Logger nextLogger) {  
        super(nextLogger);  
    }  
  
    @Override  
    public void handle(String handle, String message){  
        if(handle.equals(Logger.DEBUG)){  
            System.out.println("Debug" + message);  
        }else{  
            super.handle(handle,message);  
        }  
    }  
}
```