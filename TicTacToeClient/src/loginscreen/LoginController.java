/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginscreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import clientconnection.ClientConnection;
import helper.MsgType;
import helper.PlayerData;
import helper.QueryType;
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
          PlayerData.USERNAME = usernameTextField.getText();
            if(checkUserData(passwordTextField.getText())){
                //load the nextScreen and return the label visibility to false and setVisibile(false)

                if(labelVisibility){//if user return back to login, error label should be invisible
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            errorMsgLabel.setVisible(labelVisibility = false);
                        }
                    });
                }
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
                           errorMsgLabel.setVisible(labelVisibility = true);
                       }
                   });
               }
            }
    }

    public boolean checkUserData(String password){
        boolean returnValue;
        try {
            ClientConnection.establishConnection();
            int msgStatus = ClientConnection.forwardMsg(MsgType.DATABASE_CONNECTION+","+ QueryType.LOGIN+","+PlayerData.USERNAME+","+password);
            if(msgStatus == 1){
                if(ClientConnection.getServerResponsible().equals("yes")){

                    returnValue = true;
                }else{
                    returnValue = false;
                }
            }else{
                returnValue = true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return returnValue;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
