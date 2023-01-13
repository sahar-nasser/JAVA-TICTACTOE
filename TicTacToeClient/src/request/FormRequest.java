package request;

import clientconnection.ClientConnection;
import helper.MsgType;
import models.Player;

import java.io.IOException;

import static helper.MsgType.CANCEL_REQ;
import static helper.MsgType.SEND_REQUEST;

public class FormRequest {

    private String name;
  /*  public void sendRequest() {
        ClientConnection.forwardMsg(Integer.toString(MsgType.LIST_AVAILABLE)) ;

    }*/
    public void setName(String username){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    Player player;
    public void sendPlayerRequest() {
        try {
            ClientConnection.establishConnection();
            player= new Player();
            player.setUsername("clara");
            String msg =   new StringBuilder().append( SEND_REQUEST ).append(",")
                    .append( player.getUsername() ).append(",").append(name).toString();

            ClientConnection.forwardMsg(msg);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}

