package connection;

import dataaccesslayer.DataAccessLayer;
import Model.Player;
import helper.MsgType;
import helper.QueryType;

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

    public ClientHandler(Socket cs) {
        try {
            dis = new DataInputStream(cs.getInputStream());
            ps = new PrintStream(cs.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }
        ClientHandler.clientsVector.add(this);
        start();
    }

    public void run() {
        while (true) {
            String str = null;
            try {
                str = dis.readLine();
                str.isEmpty();
                checkMsgType(str);
            } catch (NullPointerException e) {
                ClientHandler.clientsVector.remove(this);
                System.out.println("Client disconnected!" + username);
                this.stop();
            }
            catch (IOException e) {
                ClientHandler.clientsVector.remove(this);
                System.out.println("Client disconnected!"+username);
                this.stop();
            }

        }
    }

    private void checkMsgType(String str) {

        switch (MsgType.getMsgType(str)) {
            case MsgType.SEND_MOVE:
                fowradMsgToClient(MsgType.getUsername(str), MsgType.SEND_MOVE + "," + "," + MsgType.getMove(str));
                break;
            case MsgType.DATABASECONNECTION:
            switch (QueryType.getQueryType(str)) {
                    case QueryType.GET_RECORD:
                        System.out.println("i got a request");

                }
                checkQueryType(str);
                break;
            case MsgType.lIST_AVAILABLE:
                fowradMsgToClient(MsgType.getUsername(str),getAvailablePlayers(this.username));
                break;
        }

    }

    private void checkQueryType(String str) {
        switch (QueryType.checkQueryMsg(str)) {
            case QueryType.SIGNUP:
                if (isNameUse(QueryType.getUsername(str)))
                    fowradMsgToClientObject(this, "-1");
                else {
                    this.username = QueryType.getUsername(str);
                    int x = DataAccessLayer
                            .addPlayer(new Player(QueryType.getUsername(str), QueryType.getPassword(str), 0));
                    fowradMsgToClient(QueryType.getUsername(str), x + "");
                }
                break;
            case QueryType.LOGIN:
                System.out.println("-------------------");
                if (isNameUse(QueryType.getUsername(str)))
                    fowradMsgToClientObject(this, "-1,");
                else {

                    this.username = QueryType.getUsername(str);
                    int res = DataAccessLayer.checkLoginCredintials(QueryType.getUsername(str),
                            QueryType.getPassword(str));
                    String msg = res + ",";
                    if (res > 0)
                        msg += DataAccessLayer.getScore(QueryType.getUsername(str));
                    System.out.println(msg + "-----");
                    fowradMsgToClient(QueryType.getUsername(str), msg);
                }
                break;

            case QueryType.GET_RECORD:
                System.out.println("i got a request to grab list");
                this.username = QueryType.getUserRecords(str);
                int queryState = DataAccessLayer.getRecords(QueryType.getUserRecords(str));
                System.out.println(queryState);
        }

    }

    public static String getAvailablePlayers(String username){
        String msg = MsgType.lIST_AVAILABLE+"";
        for(ClientHandler clientHandler : clientsVector){
            if(!clientHandler.isInGame && !clientHandler.username.equals(username)){
                msg += "," + clientHandler.username;
            }
        }
        return msg;
    }

    public static boolean isNameUse(String str) {
        boolean res = false;
        for (ClientHandler clientHandler : clientsVector) {
            if ((clientHandler.username != null && clientHandler.username.equals(str)))
                res = true;
        }
        return res;
    }

    public static int fowradMsgToClient(String username, String msg) {
        int res = 0;
        for (ClientHandler clientHandler : clientsVector) {
            if (clientHandler.username != null && clientHandler.username.equals(username)) {
                clientHandler.ps.println(msg);
                res = 1;
            }
        }
        return res;
    }

    public static void fowradMsgToClientObject(ClientHandler client, String msg) {
        client.ps.println(msg);
        ClientHandler.clientsVector.remove(client);
    }
}
