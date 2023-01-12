package level;

import clientconnection.ClientConnection;


import helper.MsgType;
import helper.PlayerData;
import helper.QueryType;
import models.GameLogic;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OnlineGame extends GameLogic {
    private String moveRecord;
    boolean isFirst;

    String playerTwo;
    private String msg;

    public OnlineGame(boolean isFirst, String playerTwo){
        this.isFirst=isFirst;
        this.playerTwo=playerTwo;
        try {
            ClientConnection.establishConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int setMove(int row  , int  col){
            board[row][col] = 'X';
            int x=(3*row)+(col+1);
            if(availableCells()==1)moveRecord+=x+"";
            else moveRecord+=x+",";
            return x;
        }


    public char getChar() {
        if(isFirst)return 'X';
        else return 'O';
    }
    public char getCharOfPlayerTwo() {
        if(isFirst)return 'O';
        else return 'X';
    }

    public void sendMoveMsg(int move) {
            ClientConnection.forwardMsg(MsgType.SEND_MOVE +","+playerTwo+","+move);
    }
    public void sendCloseGame(String playerTwo) {
        ClientConnection.forwardMsg(MsgType.QUIT_GAME +","+playerTwo);
    }
     public void sendScoreMsg(int pones) {
            ClientConnection.forwardMsg(MsgType.DATABASE_CONNECTION+","+QueryType.UPDATE_SCORE+","+ PlayerData.USERNAME +","+(PlayerData.SCORE+pones));
    }
    public void sendRecording() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM");
        ClientConnection.forwardMsg(MsgType.DATABASE_CONNECTION+","+QueryType.ADD_RECORD+","+ PlayerData.USERNAME +","+ playerTwo +","+moveRecord+","+isFirst+","+formatter.format(date));

    }
    public void reqMsg(){
        new  Thread( () -> {
            try {
                setMsg(ClientConnection.getServerResponsible());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void setMsg(String serverResponsible) {
        this.msg=serverResponsible;
    }

    public  String  getMsg(){
        reqMsg();
        return msg;
    }


    public void closeConnection() {
        try {
            ClientConnection.closeConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
