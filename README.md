# VoIP-Communication-System

Description:
This project implements a basic Voice over IP (VoIP) communication system using Java standard libraries. The system consists of a server that handles multiple client connections and allows clients to communicate with each other by sending text messages. The primary goal of this project is to demonstrate the fundamentals of network programming and real-time communication in Java.

Features:

Server:

Listens for incoming client connections on a specified port.
Manages connected clients and maintains a list of active client sockets.
Forwards messages received from one client to all other connected clients.
Handles multiple client connections concurrently using multithreading.

Client:

Connects to the server using a specified IP address and port.
Sends messages to the server, which are then broadcast to all other connected clients.
Receives messages from the server and displays them in the client terminal.
Supports concurrent sending and receiving of messages using multithreading.
Technologies Used:

Java Standard Library

java.net for networking (Socket, ServerSocket)
java.io for input and output (BufferedReader, InputStreamReader, PrintWriter)
java.util for data structures (Set, HashSet)
java.lang for multithreading (Thread, Runnable)

How It Works:

Server:

The server starts and listens for incoming client connections on a specified port.
When a client connects, the server accepts the connection and creates a new ClientHandler thread to manage communication with that client.
The ClientHandler reads messages from the client and uses the server's broadcast method to send the message to all other connected clients.

Client:

The client connects to the server using the server's IP address and port number.
The client starts two threads: one for reading user input from the terminal and sending it to the server, and another for receiving messages from the server and displaying them in the terminal.

Use Case:

This VoIP communication system can be used as a foundation for more complex real-time communication applications, such as chat applications, collaborative tools, or even simple online multiplayer games. It demonstrates the core concepts of network communication, concurrency, and message broadcasting.

Example Output:

When the server and clients are running, the server's terminal will indicate that it has started, and each client's terminal will allow users to send and receive messages. For instance:

Server Terminal:

arduino
Copy code
VoIP Server started...
Client 1 Terminal:

vbnet
Copy code
Hello from Client 1!
Received: Hello from Client 2!
Received: Hello from Client 3!
Client 2 Terminal:

vbnet
Copy code
Hello from Client 2!
Received: Hello from Client 1!
Received: Hello from Client 3!
Client 3 Terminal:

vbnet
Copy code
Hello from Client 3!
Received: Hello from Client 1!
Received: Hello from Client 2!

