package connection;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;

public class ClientHandler extends Thread {
    DataInputStream dis;
    PrintStream ps;
    boolean isInGame;
    String username;
    static Vector<ClientHandler> clientsVector = new Vector<>();

    public ClientHandler(Socket cs, String username) {
        try {
            this.username=username;
            dis = new DataInputStream(cs.getInputStream());
            ps = new PrintStream(cs.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);///eroooooooooooooo
        }
        ClientHandler.clientsVector.add(this);
        start();
    }

    public void run() {
        while (true) {
            String str = null;
            try {
                str = dis.readLine();

                checkMsgType(str);
            } catch (IOException e) {
                System.out.println("Client disconnected!");
                ClientHandler.clientsVector.remove(this);
                this.stop();
            }

        }
    }

    private void checkMsgType(String str) {
        //go with switch
    }
    public static int getAvailablePlayers(){return ClientHandler.clientsVector.size();}

    public static int sendMsg(String username,String msg){
        int res=0;
        for (ClientHandler clientHandler : clientsVector) {
            if (clientHandler.username!=null&&clientHandler.username.equals(username))
                clientHandler.ps.println(msg);
            res=1;
        }
        return res;
    }
}