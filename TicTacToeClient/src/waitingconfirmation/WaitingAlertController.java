/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waitingconfirmation;

import java.io.IOException;
import onlineplayerscreen.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 *
 * @author Sahar
 */
public class WaitingAlertController implements Initializable {
    
    @FXML
    private Button cancelBtn;
    
    @FXML
    private void handleCancelAction(ActionEvent event) {
       System.out.println("user cancelled!"); 
      /* Stage stage = (Stage) cancelBtn.getScene().getWindow();
       stage.close();*/
      //Platform.exit();
       Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root;
        try {
            boradscreen.BoradController.TYPE=helper.GameType.ONLINE_GAME;
            root = FXMLLoader.load(getClass().getResource("/boradscreen/borad.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
