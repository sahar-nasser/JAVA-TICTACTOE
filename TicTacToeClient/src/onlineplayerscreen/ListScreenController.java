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
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 *
 * @author Sahar
 */
public class ListScreenController implements Initializable {
    
    @FXML
    private Button playerBtn;
     @FXML
    private Button homeBtn;
     @FXML
    private Button logoutBtn;
      @FXML
    private Button historyBtn;
     
    
    
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
         Alert alert = new Alert(Alert.AlertType.INFORMATION, "WAITING FOR RESPONSE...", ButtonType.CANCEL);
         alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
         alert.show();
         DialogPane dialogPane = alert.getDialogPane();
         dialogPane.getStylesheets().add(
                 getClass().getResource("Dialog.css").toExternalForm());
         dialogPane.getStyleClass().add("Dialog");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
