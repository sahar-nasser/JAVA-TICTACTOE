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

import boardscreen.BoardController;
import clientconnection.ClientConnection;
import com.sun.javaws.IconUtil;
import helper.GameType;
import helper.MsgType;
import helper.PlayerData;
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

public class ListScreenController implements Initializable {
    public static Stage STAGE;
    @FXML
    private Button homeBtn;
     @FXML
    private Button logoutBtn;
      @FXML
    private Button historyBtn;

    @FXML
    private ListView<Button> playersList;
    Alert alert;
    private ActionEvent event;

    @FXML
    private void handleHistoryAction(ActionEvent event) {

        
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
    private void handleClickOnPlayerAction(String s) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("waiting");
         alert.setContentText("waiting for another player");
         ButtonType okButton = new ButtonType("cancel", ButtonBar.ButtonData.YES);
         alert.getButtonTypes().setAll(okButton);
         alert.showAndWait().ifPresent(type -> {
             if (type == ButtonType.OK) {
                 ClientConnection.forwardMsg(MsgType.CANCEL_REQ+","+s);
                 alert.close();
             }


         });


    }
    private void makeAlert(String username) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("request");
        alert.setContentText(username+"wants to play with you");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if (type.getButtonData() ==ButtonBar.ButtonData.YES) {
                System.out.println("------------ok------------");
                ClientConnection.forwardMsg(MsgType.CONFIRM_REQ+" , "+username);
                BoardController.TYPE= GameType.ONLINE_GAME;
                BoardController.PLAYER_TWO=username;
                try {
                    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    BoardController.STAGE_OF_BOARD = stage;
                    Parent root = FXMLLoader.load(getClass().getResource("/boardscreen/board.fxml"));
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    System.out.println(e.fillInStackTrace());
                }

            } else if (type == ButtonType.NO) {
                System.out.println("------------no-----------------");
                ClientConnection.forwardMsg(MsgType.CANCEL_REQ+" , "+username);
                alert.close();
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        new Thread(()->{
            while (true){
                Platform.runLater(()->{
                    playersList.getItems().clear();
                });
                openConnection();

                try {
                    String serverResponse = ClientConnection.getServerResponsible();

                    switch (MsgType.getMsgType(serverResponse)){
                        case MsgType.LIST_AVAILABLE:

                            String[] playerNames = serverResponse.split(",");
                            Platform.runLater(() -> {
                                fillPlayersList(playerNames);
                            });
                            break;
                        case MsgType.SEND_REQUEST:
                            Platform.runLater(()->{
                                makeAlert(MsgType.getUsernameOfReq(serverResponse));
                            });
                            break;
                        case MsgType.CANCEL_REQ:
                            alert.close();
                            break;
                        case MsgType.CONFIRM_REQ:
                            BoardController.TYPE= GameType.ONLINE_GAME;
                            BoardController.PLAYER_TWO=MsgType.getUsername(serverResponse);
                            try {
                                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                                BoardController.STAGE_OF_BOARD = stage;
                                Parent root = FXMLLoader.load(getClass().getResource("/boardscreen/board.fxml"));
                                Scene scene=new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException e) {
                                System.out.println(e.fillInStackTrace());
                            }
                    }
                    try {
                        Thread.sleep(1000);
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
                this.event=event;
                ClientConnection.forwardMsg(MsgType.SEND_REQUEST+","+ tempButton.getText().toString()+","+PlayerData.USERNAME);
                Platform.runLater(()->{
                    handleClickOnPlayerAction(tempButton.getText().toString());
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
