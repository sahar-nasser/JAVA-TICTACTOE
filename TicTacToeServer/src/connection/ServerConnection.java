package connection;

import helper.QueryType;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerConnection {
    private  ServerSocket serverSocket;

    public ServerConnection() {
        try {
            new QueryType();
            serverSocket = new ServerSocket(5000);
            new Thread(() -> {
                while (true) {
                    Socket s;
                    try {
                        s = serverSocket.accept();
                        new ClientHandler(s);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }}).start();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
