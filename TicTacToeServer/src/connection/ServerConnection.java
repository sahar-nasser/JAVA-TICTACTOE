package connection;

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
            serverSocket = new ServerSocket(5000);

        } catch (IOException e) {

        }
        new Thread(() -> {
            while (true) {
            Socket s;
                try {
                    s = serverSocket.accept();
                    DataInputStream dis = new DataInputStream(s.getInputStream());//testing
                    new ClientHandler(s,dis.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }}).start();
    }
}
