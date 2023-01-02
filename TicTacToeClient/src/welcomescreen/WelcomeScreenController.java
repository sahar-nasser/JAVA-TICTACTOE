/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package welcomescreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author claramouniradly
 */
public class WelcomeScreenController implements Initializable {

    @FXML
    private Button easy;
    
    @FXML
    private Button medium;
    
    @FXML
    private Button hard;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   @FXML
   private  void showLevel(ActionEvent event){
       easy.setVisible(true);
       medium.setVisible(true);
       hard.setVisible(true);

   }
   @FXML
   private void goToGame(ActionEvent event) throws IOException{
       Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(".fxml"));

 
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
   }
 @FXML
   private  void login(ActionEvent event) throws IOException{
       Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/loginScreen/Login.fxml"));

 
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
   }
}
