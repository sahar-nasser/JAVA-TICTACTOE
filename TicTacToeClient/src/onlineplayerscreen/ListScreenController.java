/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineplayerscreen;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Player;
import request.FormRequest;

/**
 *
 * @author Sahar
 */
public class ListScreenController implements Initializable {
    @FXML
    private Button homeBtn;
     @FXML
    private Button logoutBtn;
      @FXML
    private Button historyBtn;

    @FXML
    private ListView<Button> playersList;

    @FXML
    private void handleHistoryAction(ActionEvent event) {
        System.out.println("history clicked!");
        
     Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/recordscreen/FXMLRecordList.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
           
    }
    
     @FXML
    private void handleHomeAction(ActionEvent event) {
         System.out.println("home clicked!");
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/welcomescreen/WelcomeScreen.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }  
           
    }
    
     @FXML
    private void handleLogoutAction(ActionEvent event) {
         System.out.println("logout clicked!");
           //end connection
           Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/welcomescreen/WelcomeScreen.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
     @FXML
    private void handleClickOnPlayerAction(ActionEvent event) {
         System.out.println("player clicked!");
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root;
         FormRequest request = new FormRequest();
         request.setName("sahar");
         request.sendPlayerRequest();
        try {
            root = FXMLLoader.load(getClass().getResource("/waitingconfirmation/FXMLWaiting.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //call method to talk to the server the pass the return which should be ArrayList<Player>
        //to the fillPlayerList method then we will have 
    }

    //call the method to receive the list of players in initialize
    private void fillPlayersList(ArrayList<Player> onlinePlayers){
        for(Player player : onlinePlayers){
            Button tempButton = new Button(player.getUsername());
            tempButton.setPrefHeight(74);
            tempButton.setPrefWidth(300);
            tempButton.setStyle("-fx-background-color: #ffb100;-fx-font-family: 'Action Jackson'; -fx-font-size: 32;");
            playersList.getItems().add(tempButton);
        }
    }
}
