/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recordscreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import boardscreen.BoardController;
import clientconnection.ClientConnection;
import helper.GameType;
import helper.MsgType;
import helper.QueryType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static helper.MsgType.SEND_REQUEST;

/**
 *
 * @author Sahar
 */
public class RecordScreenController implements Initializable {
    
    @FXML
    private Button record;
    @FXML
    private Button homeBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private Button historyBtn;
    
    @FXML
    private void handleRecordAction(ActionEvent event) {
        //ask server to fetch record data to recreation
        System.out.println("record!");

        try {
            BoardController.TYPE = GameType.REPLAYED_GAME;
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/boardscreen/board.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
       
    }
    
    @FXML
    private void handleHistoryAction(ActionEvent event) throws IOException {  
        System.out.println("history clicked!");  
    }
    
     @FXML
    private void handleHomeAction(ActionEvent event) throws IOException {
         System.out.println("home clicked!");
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/welcomescreen/WelcomeScreen.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();   
    }
    
     @FXML
    private void handleLogoutAction(ActionEvent event) throws IOException {
        //end connection
         System.out.println("logout clicked!");
              
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/welcomescreen/WelcomeScreen.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //send req to server
        try {
            ClientConnection.establishConnection();
            String getListMsg =  new StringBuilder().append( MsgType.DATABASE_CONNECTION ).append(",")
                    .append(QueryType.GET_RECORD).append(",").toString();
            ClientConnection.forwardMsg(getListMsg);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
