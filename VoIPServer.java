import java.io.*;
import java.net.*;
import java.util.*;

public class VoIPServer {
    private static final int PORT = 12345;
    private static Set<Socket> clientSockets = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("VoIP Server started...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                synchronized (clientSockets) {
                    clientSockets.add(clientSocket);
                }
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String message;
                while ((message = in.readLine()) != null) {
                    broadcast(message, socket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                synchronized (clientSockets) {
                    clientSockets.remove(socket);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void broadcast(String message, Socket senderSocket) {
            synchronized (clientSockets) {
                for (Socket clientSocket : clientSockets) {
                    if (clientSocket != senderSocket) {
                        try {
                            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                            out.println(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}

