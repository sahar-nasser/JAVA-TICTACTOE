/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginscreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author CST
 */
public class LoginController implements Initializable {
    @FXML
    private Label errorMsgLabel;
    @FXML
    private Button loginBtn;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    private boolean labelVisibility = false;
    
    @FXML
    public void navigateToSignUp(ActionEvent event) throws IOException{
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/signupscreen/SignUp.fxml"));

            Scene scene=new Scene(root);
            stage.sizeToScene();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
    }
    
    @FXML
    public void navigateToWelcomeScreen(ActionEvent event) throws IOException{
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/welcomescreen/WelcomeScreen.fxml"));

            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
      @FXML
    public void handleLoginButtonAction(ActionEvent event) throws IOException{
             //start connection + send data for authentication through client handler

            if(true){//checkuserData will be here
                //load the nextScreen and return the label visibility to false and setVisibile(false)

                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/onlineplayerscreen/FXMLPlayerList.fxml"));
                Scene scene=new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else{
               if(!labelVisibility){
                   Platform.runLater(new Runnable() {
                       @Override
                       public void run() {
                           errorMsgLabel.setVisible(true);
                       }
                   });
                   labelVisibility =  true;
               }else{
               }
            }
    }

    public void checkUserData(String username , String password){
        //go to database and then fill player object
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
