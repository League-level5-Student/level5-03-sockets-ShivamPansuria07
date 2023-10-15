package _02_Chat_Application;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	public static void main(String[] args) {
	    Server server = new Server(12345); // or any port number you prefer
	    server.start();
	}

    private int port;
    private ServerSocket server;
    private List<ObjectOutputStream> clientOutputStreams = new ArrayList<>();

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started on port: " + port);

            while (true) {
                new ClientHandler(server.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler extends Thread {
        private Socket socket;
        private ObjectOutputStream os;
        private ObjectInputStream is;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                os = new ObjectOutputStream(socket.getOutputStream());
                is = new ObjectInputStream(socket.getInputStream());
                os.flush();

                synchronized (clientOutputStreams) {
                    clientOutputStreams.add(os);
                }

                Object message;
                while ((message = is.readObject()) != null) {
                    System.out.println("Received: " + message);
                    broadcast(message);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                synchronized (clientOutputStreams) {
                    clientOutputStreams.remove(os);
                }
            }
        }

        private void broadcast(Object message) {
            synchronized (clientOutputStreams) {
                for (ObjectOutputStream writer : clientOutputStreams) {
                    try {
                        writer.writeObject(message);
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
