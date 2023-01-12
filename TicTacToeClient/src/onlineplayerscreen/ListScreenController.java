/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineplayerscreen;

import java.io.IOException;
import java.net.URL;
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

/**
 *
 * @author Sahar
 */
public class ListScreenController implements Initializable {
    @FXML
    private AnchorPane anchorPane = new AnchorPane();
    

    private Button playerBtn = new Button("testetstets") , test = new Button ("hello") , test1= new Button ("hello") , test2= new Button ("hello") , test4 = new Button ("hello"),test5= new Button ("hello"),test7= new Button ("hello");
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
        playerBtn.setPrefHeight(74);
        playerBtn.setPrefWidth(300);
        playerBtn.setStyle("-fx-background-color: #ffb100;-fx-font-family: 'Action Jackson'; -fx-font-size: 32;");
        playersList.getItems().add(playerBtn);
        playersList.getItems().add(test);
        playersList.getItems().add(test1);
        playersList.getItems().add(test2);
        playersList.getItems().add(test4);
        playersList.getItems().add(test5);
        playersList.getItems().add(test7);
    }
    
}
