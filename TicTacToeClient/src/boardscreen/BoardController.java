/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package boardscreen;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import helper.StatusGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ammar
 */
public class BoardController implements Initializable {
    private static MediaPlayer MEDIA_PLAYER;
   public static int TYPE;

    private static int STATUS_OF_GAME;
   private  static Stage STAGE_OF_VIEW_VIDEO;
    public static Stage STAGE_OF_BORAD;
   private  static boolean IS_VIEW_VIDEO;
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
    private MediaView mediaView;
      
    

    @FXML
    public void recordGame(ActionEvent event){
        record.setStyle("-fx-background-color:#ff0000");
        record.setText("Recording");
        viewVideo();
//        record.setEffect(value);
      
    }
    @FXML
    public void closeGame(ActionEvent event){
        if (IS_VIEW_VIDEO){
            STAGE_OF_VIEW_VIDEO.close();
            MEDIA_PLAYER.stop();
            IS_VIEW_VIDEO=false;
        }
         switch (TYPE) {
            case helper.GameType.ONLINE_GAME:
                try{
                Parent root = FXMLLoader.load(getClass().getResource("/onlineplayerscreen/FXMLPlayerList.fxml"));
                Scene scene=new Scene(root);
                STAGE_OF_BORAD.setScene(scene);
                STAGE_OF_BORAD.show();
                }catch (IOException ex) {
                    Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
                 }
            break;
            default:
                try{
                Parent root = FXMLLoader.load(getClass().getResource("/welcomescreen/WelcomeScreen.fxml"));
                Scene scene=new Scene(root);
                STAGE_OF_BORAD.setScene(scene);
                STAGE_OF_BORAD.show();
                }catch (IOException ex) {
                    Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void viewVideo(){
        STATUS_OF_GAME=StatusGame.WIN;
        IS_VIEW_VIDEO=true;
        STAGE_OF_VIEW_VIDEO= new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/boardscreen/ViewVideo.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene=new Scene(root);
        STAGE_OF_VIEW_VIDEO.setScene(scene);
        STAGE_OF_VIEW_VIDEO.showAndWait();



    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!IS_VIEW_VIDEO){
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
        else{
        Media media;
        switch (STATUS_OF_GAME) {
            case StatusGame.WIN:
                STAGE_OF_VIEW_VIDEO.setTitle("WIN");
                media = new Media(Paths.get("TicTacToeClient/src/resource/WinningVideo.mp4").toUri().toString());
                break;
            case StatusGame.DRAW:
                STAGE_OF_VIEW_VIDEO.setTitle("DRAW");
                media = new Media(Paths.get("TicTacToeClient/src/resource/WinningVideo.mp4").toUri().toString());
                break;
            default:
                STAGE_OF_VIEW_VIDEO.setTitle("LOSE");
                media = new Media(Paths.get("TicTacToeClient/src/resource/WinningVideo.mp4").toUri().toString());
        }
        MEDIA_PLAYER =new MediaPlayer(media);
        MEDIA_PLAYER.setAutoPlay(true);
        MEDIA_PLAYER.setOnReady(()->STAGE_OF_VIEW_VIDEO.sizeToScene());
        mediaView.setMediaPlayer(MEDIA_PLAYER);
    }
    }

    
}
