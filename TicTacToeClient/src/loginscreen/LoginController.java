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
    public void handleLoginButtonAction(ActionEvent event) {
          if(usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()){
              if(!labelVisibility){//if user return back to login, error label should be invisible
                  errorMsgLabel.setVisible(labelVisibility = true);
              }
          }else{
              openConnection(usernameTextField.getText(),passwordTextField.getText());
              new Thread(()->{
                  try {
                      String msg=ClientConnection.getServerResponsible();
                      System.out.println(msg+" ----------");
                      if ((MsgType.getMsgType(msg)+"").equals("1")){
                          PlayerData.USERNAME = usernameTextField.getText();
                          PlayerData.password = usernameTextField.getText();
                          PlayerData.SCORE = MsgType.getScore(msg);
                          Platform.runLater( ()->{
                                  navigateToOnlinePlayers(event);
                          });
                      }
                      else {
                          Platform.runLater(()->{
                              if(labelVisibility){
                                  errorMsgLabel.setText("username or password is incorrect");

                              }
                              try {
                                  ClientConnection.closeConnection();
                              } catch (IOException e) {
                                  System.out.println(e.fillInStackTrace());
                              }
                          });
                      }

                  } catch (IOException e) {
                      System.out.println(e.fillInStackTrace());
                      try {
                          ClientConnection.closeConnection();
                      } catch (IOException ex) {
                          System.out.println(ex.fillInStackTrace());
                      }
                  }
              }).start();
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
    public void openConnection(String username , String password){
        try {
            ClientConnection.establishConnection();
            ClientConnection.forwardMsg(MsgType.DATABASE_CONNECTION+","+ QueryType.LOGIN+","+username+","+password);
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
