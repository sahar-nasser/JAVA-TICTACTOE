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

import javax.swing.*;

/**
 *
 * @author CST
 */
public class LoginController implements Initializable {
    private String errorMsgOne = "Please check your username or password was incorrect!" ,
    errorMsgTwo = "Invalid username. please remove and ,";
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
          if(usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()){
              if(!labelVisibility){//if user return back to login, error label should be invisible
                  errorMsgLabel.setVisible(labelVisibility = true);
              }
          }else{
              openConnection(passwordTextField.getText());
              new Thread(()->{
                  try {
                      String msg=ClientConnection.getServerResponsible();
                      if ((MsgType.getMsgType(msg)+"").equals("1")){
                          PlayerData.USERNAME = usernameTextField.getText();
                          PlayerData.password = usernameTextField.getText();
                          PlayerData.SCORE = MsgType.getScore(msg);
                          Platform.runLater( ()->{
                              try {
                                  ClientConnection.closeConnection();
                                  navigateToOnlinePlayers(event);
                              } catch (IOException e) {
                                  System.out.println(e.getMessage());
                              }

                          });
                      }
                      else {
                          Platform.runLater(()->{
                              if(labelVisibility){
                                  errorMsgLabel.setVisible(labelVisibility = false);
                              }
                              try {
                                  ClientConnection.closeConnection();
                              } catch (IOException e) {
                                  System.out.println(e.getMessage());
                              }
                          });
                      }

                  } catch (IOException e) {
                      System.out.println(e.getMessage());
                      try {
                          ClientConnection.closeConnection();
                      } catch (IOException ex) {
                          System.out.println(ex.getMessage());
                      }
                  }


              }).start();
              if(checkUserData(passwordTextField.getText())){
                  //load the nextScreen and return the label visibility to false and setVisibile(false)
              }else{
                  if(!labelVisibility){
                      errorMsgLabel.setVisible(labelVisibility = true);
                  }
              }
          }
    }

    public void navigateToOnlinePlayers(ActionEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/onlineplayerscreen/FXMLPlayerList.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openConnection(String password){
        try {
            ClientConnection.establishConnection();
            ClientConnection.forwardMsg(MsgType.DATABASE_CONNECTION+","+ QueryType.LOGIN+","+PlayerData.USERNAME+","+password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
