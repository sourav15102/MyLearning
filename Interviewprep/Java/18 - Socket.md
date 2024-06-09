
Q: What is socket programming?
A:
Socket programming lets communication between two machines on same or different JRE.
Connection-oriented:
- Socket and ServerSocket classes are used
Connectionless:
- DatagramSocket, and DatagramPacket classes are used here. 
The client in socket programming must know two information:
- IP address of the server
- port number

Q: Libraries needed?
A:
```java
- import java.io.*;    
- import java.net.*;
```

Q: Get hostname from IP?
A:
```java
- import java.io.*;    
- import java.net.*;    
- public class InetDemo{    
- public static void main(String[] args){    
- try{    
- InetAddress ip=InetAddress.getByName("195.201.10.8");    

- System.out.println("Host Name: "+ip.getHostName());    
- }catch(Exception e){System.out.println(e);}    
- }    
- }
```

Example code:
Server:
```java
public class GreetServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String greeting = in.readLine();
            if ("hello server".equals(greeting)) {
                out.println("hello client");
            }
            else {
                out.println("unrecognised greeting");
            }
    }

    public void stop() {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
    public static void main(String[] args) {
        GreetServer server=new GreetServer();
        server.start(6666);
    }
}
```
Client:
```java
public class GreetClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() {
        in.close();
        out.close();
        clientSocket.close();
    }
}

@Test
public void givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect() {
    GreetClient client = new GreetClient();
    client.startConnection("127.0.0.1", 6666);
    String response = client.sendMessage("hello server");
    assertEquals("hello client", response);
}
```

### Socket:
A _socket_ is one endpoint of a two-way communication link between two programs running on different computers on a network. [A socket is bound to a port number](https://www.baeldung.com/cs/port-vs-socket) so that the transport layer can identify the application that data is destined to be sent to.


