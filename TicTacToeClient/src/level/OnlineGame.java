package level;

import clientconnection.ClientConnection;

import models.GameLogic;


import java.io.IOException;

public class OnlineGame extends GameLogic {
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
            return (3*row)+(col+1);
        }


    public char getChar() {
        if(isFirst)return 'X';
        else return 'O';
    }
    public char getCharOfPlayerTwo() {
        if(isFirst)return 'O';
        else return 'X';
    }

    public void sendMsg(int move,int type) {
            ClientConnection.forwardMsg(""+type+","+playerTwo+","+move);
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


}
