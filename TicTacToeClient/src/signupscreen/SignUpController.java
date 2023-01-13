/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupscreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import clientconnection.ClientConnection;
import helper.MsgType;
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
public class SignUpController implements Initializable {
    @FXML
    private Label errorMsg;
    private boolean msg = false;
    @FXML
    private TextField name;

    @FXML
    private TextField password;
@FXML
    private TextField checkpassword;

    @FXML
    private Button submitButton;

    @FXML
    public void register(ActionEvent event) throws IOException{

        System.out.println(name.getText());
        System.out.println(password.getText());
        System.out.println(checkpassword.getText());

        if (name.getText().isEmpty()||password.getText().isEmpty()||checkpassword.getText().isEmpty()) {
            System.out.println("empty");
            if (msg == false){ errorMsg.setText("Please make sure to fill your data");
              errorMsg.setTextFill(Color.RED);
                errorMsg.setVisible(true);
                msg = true;
            }
        }
        else {
            System.out.println("---");
            errorMsg.setVisible(false);
            msg = false;
        }
        if (checkpassword.equals(password)){
            System.out.println("password incoreect");
            errorMsg.setText("please make sure that your password is correct");
        }
        else {
            System.out.println("in send");
            sendPlayerData();
            new Thread(()->{
                try {
                    String msg=ClientConnection.getServerResponsible();
                    System.out.println(msg);
                    if (msg.equals("1")){
                        Platform.runLater( ()->{
                            navigateToLogin(event);
                        });
                      
                    }
                    else {
                        Platform.runLater(()->{
                            System.out.println("-----");
                            errorMsg.setVisible(true);
                            errorMsg.setText("username is already exists!!!");

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

        }

    }
    void navigateToLogin(ActionEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/loginscreen/Login.fxml"));
            Scene scene=new Scene(root);
            stage.sizeToScene();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
    public void sendPlayerData() {
        try {
            ClientConnection.establishConnection();
            String msg =   new StringBuilder().append(MsgType.DATABASE_CONNECTION).append(",")
                    .append(QueryType.SIGNUP).append(",").append(name.getText()).append(",").append(password.getText()).toString();

            ClientConnection.forwardMsg(msg);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            try {
                ClientConnection.closeConnection();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
