/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineplayerscreen;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import clientconnection.ClientConnection;
import helper.MsgType;
import helper.PlayerData;
import helper.QueryType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import models.Player;
import request.FormRequest;

public class ListScreenController implements Initializable {
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
            try {
                ClientConnection.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            root = FXMLLoader.load(getClass().getResource("/welcomescreen/WelcomeScreen.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
     @FXML
    private void handleClickOnPlayerAction() {
         System.out.println("player clicked!");
         Alert alert = new Alert(Alert.AlertType.INFORMATION, "WAITING FOR RESPONSE...", ButtonType.CANCEL);
         alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
         alert.show();
         DialogPane dialogPane = alert.getDialogPane();
         dialogPane.getStylesheets().add(
                 getClass().getResource("Dialog.css").toExternalForm());


    }
    private void makeAlert(String username) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("request");
        alert.setContentText(username+"wants to play with you");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(okButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == ButtonType.OK) {
                ClientConnection.forwardMsg(MsgType.CONFIRM_REQ+" , "+username);

            } else if (type == ButtonType.NO) {
                ClientConnection.forwardMsg(MsgType.CANCEL_REQ+" , "+username);

            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("hi");
        new Thread(()->{
            while (true){
                Platform.runLater(()->{
                    playersList.getItems().clear();
                });
                openConnection();
                System.out.println("whilee");
                try {
                    String serverResponse = ClientConnection.getServerResponsible();
                    System.out.println(serverResponse);
                    switch (MsgType.getMsgType(serverResponse)){
                        case MsgType.LIST_AVAILABLE:
                            System.out.println("entered");
                            String[] playerNames = serverResponse.split(",");
                            Platform.runLater(() -> {
                                System.out.println("in plat");
                                fillPlayersList(playerNames);
                            });
                            break;
                        case MsgType.SEND_REQUEST:
                            Platform.runLater(()->{
                                makeAlert(MsgType.getUsername(serverResponse));
                            });
                            break;
                        case MsgType.CANCEL_REQ:


                            break;
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //call method to talk to the server the pass the return which should be ArrayList<Player>
        //to the fillPlayerList method then we will have 
    }

    //call the method to receive the list of players in initialize
    private void fillPlayersList(String[] onlinePlayers){
        for(int i = 1 ; i < onlinePlayers.length ; ++i){
            Button tempButton = new Button(onlinePlayers[i]);
            tempButton.setOnAction(event -> {
                ClientConnection.forwardMsg(MsgType.SEND_REQUEST+","+ tempButton.getText());
                Platform.runLater(()->{
                    handleClickOnPlayerAction();
                });
            });
            tempButton.setPrefHeight(74);
            tempButton.setPrefWidth(300);
            tempButton.setStyle("-fx-background-color: #ffb100;-fx-font-family: 'Action Jackson'; -fx-font-size: 32;");
            playersList.getItems().add(tempButton);
        }
    }

    public void openConnection(){
        ClientConnection.forwardMsg(MsgType.LIST_AVAILABLE+","+ PlayerData.USERNAME);
    }
}
