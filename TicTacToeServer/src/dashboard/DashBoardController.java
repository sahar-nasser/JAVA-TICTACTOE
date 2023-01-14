/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dashboard;

import java.net.URL;
import java.util.ResourceBundle;

import connection.ClientHandler;
import connection.ServerConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author ammar
 */
public class DashBoardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        new ServerConnection();
    }  
    
     @FXML
    private Label numberOfPlayersLabel;
    @FXML
    private Label numberOfOnlinePlayersLabel;
    @FXML
    private Label playerWithHighScore;
    
    @FXML
    private void openConnection(ActionEvent event) {
        System.out.println("opeeeeeeen-----------------");

    }
    @FXML
    private void closeConnection(ActionEvent event) {
        System.out.println("close----------------------");
    }
    
    
    
}
