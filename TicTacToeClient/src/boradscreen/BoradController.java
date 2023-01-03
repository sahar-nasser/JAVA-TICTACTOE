/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package boradscreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ammar
 */
public class BoradController implements Initializable {
   public static int TYPE;
   @FXML
   private Button record;
   @FXML
   private Button close;
   @FXML
   private Button postionOne;
   @FXML
   private Button postionTwo;
   @FXML
   private Button postionThree;
   @FXML
   private Button postionFour;
   @FXML
   private Button postionFive;
   @FXML
   private Button postionSix;
   @FXML
   private Button postionSeven;
   @FXML
   private Button postionEight;
   @FXML
   private Button postionNine;
   
   @FXML
   private Text scoreOfPlayerOne;
   @FXML
   private Text scoreOfPlayerTwo;
   @FXML
   private Label scoreOne;
   @FXML
   private Label scoreTwo;

   @FXML
   private Text nameOfPlayerOne;
   @FXML
   private Text nameOfPlayerTwo;
  

      
    

    @FXML
    public void recordGame(ActionEvent event){
        record.setStyle("-fx-background-color:#ff0000");
        record.setText("Recording");
//        record.setEffect(value);
      
    }
    @FXML
    public void closeGame(ActionEvent event){
         switch (TYPE) {
            case helper.GameType.ONLINE_GAME:
                try{
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/onlineplayerscreen/FXMLPlayerList.fxml"));
                Scene scene=new Scene(root);
                stage.setScene(scene);
                stage.show();
                }catch (IOException ex) {
                    Logger.getLogger(BoradController.class.getName()).log(Level.SEVERE, null, ex);
                 }
            break;
            default:
                try{
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/welcomescreen/WelcomeScreen.fxml"));
                Scene scene=new Scene(root);
                stage.setScene(scene);
                stage.show();
                }catch (IOException ex) {
                    Logger.getLogger(BoradController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                break;
                
        }
      
    }
    
    @FXML
    public void clickPostionOne(ActionEvent event){

        postionOne.setText("O");
        postionOne.setDisable(true);
        postionOne.setOpacity(1);
    }
    @FXML
    public void clickPostionTwo(ActionEvent event){
        postionTwo.setText("O");
        postionTwo.setDisable(true);
        postionTwo.setOpacity(1);

        
    }
    @FXML
    public void clickPostionThree(ActionEvent event){
        postionThree.setText("O");
        postionThree.setDisable(true);
        postionThree.setOpacity(1);
        
    }
    @FXML
    public void clickPostionFour(ActionEvent event){
        postionFour.setText("O");
        postionFour.setDisable(true);
        postionFour.setOpacity(1);
        
    }
    @FXML
    public void clickPostionFive(ActionEvent event){
        postionFive.setText("O");
        postionFive.setDisable(true);
        postionFive.setOpacity(1);
    }
    @FXML
    public void clickPostionSix(ActionEvent event){
        postionSix.setText("O");
        postionSix.setDisable(true);
        postionSix.setOpacity(1);
    }
    @FXML
    public void clickPostionSeven(ActionEvent event){
        postionSeven.setText("O");
        postionSeven.setDisable(true);
        postionSeven.setOpacity(1);
    }
    @FXML
    public void clickPostionEight(ActionEvent event){
        postionEight.setText("O");
        postionEight.setDisable(true);
        postionEight.setOpacity(1);
    }
    @FXML
    public void clickPostionNine(ActionEvent event){
        postionNine.setText("O");
        postionNine.setDisable(true);
        postionNine.setOpacity(1);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("----------yep-----------  "+TYPE);
        switch (TYPE) {
            case helper.GameType.ONLINE_GAME:
                
            break;
            case helper.GameType.SINGLE_PLAYER_GAME:
                nameOfPlayerTwo.setText("COMPUTER");
                nameOfPlayerOne.setText("ME");

                
            default:
                scoreOfPlayerOne.setVisible(false);
                scoreOfPlayerTwo.setVisible(false);
                scoreOne.setVisible(false);
                scoreTwo.setVisible(false);
                record.setVisible(false);
                break;
                
        }
    }    
    
}
