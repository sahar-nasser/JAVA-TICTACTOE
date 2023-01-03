/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestscreen;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 *
 * @author Sahar
 */
public class RequestController implements Initializable {
    
    @FXML
    private Button confirmBtn;
    @FXML
    private Button cancelBtn;
    
    @FXML
    private void handleConfirmAction(ActionEvent event) {
        //start logoc of loading game
        System.out.println("User Confirmed!");
        
    }
    
     @FXML
    private void handleCancelAction(ActionEvent event) {
        //close window, send msg of rejection to the other player
        System.out.println("User Cancelled");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
