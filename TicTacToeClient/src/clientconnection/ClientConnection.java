package clientconnection;

import helper.PlayerData;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientConnection {
    static private Socket mySocket;
    static private DataInputStream dis;
    static private PrintStream ps;

    public static boolean isConnect(){
        return mySocket.isConnected();
    }
    public static void establishConnection() throws IOException {

            mySocket= new Socket("127.0.0.1", 5005);

            ps = new PrintStream(mySocket.getOutputStream());
            dis = new DataInputStream(mySocket.getInputStream());

    }

    public static int forwardMsg(String msg){
        int res=0;
        if (msg!=null) {
            ps.println(msg);
            System.out.println(msg);
            res = 1;
        }
        return res;
    }

    public static String  getServerResponsible() throws IOException {

        return dis.readLine();
    }

    public static void closeConnection() throws IOException {
        mySocket.close();
    }
}

