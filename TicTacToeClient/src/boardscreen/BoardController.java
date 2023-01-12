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

import clientconnection.ClientConnection;
import easylevel.EasyLogic;
import helper.GameType;
import helper.MsgType;
import helper.PlayerData;
import helper.StatusGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import level.OnlineGame;

/**
 * FXML Controller class
 *
 * @author ammar
 */
public class BoardController implements Initializable {
    private static MediaPlayer MEDIA_PLAYER;
   public static int TYPE;
    private static int STATUS_OF_GAME;
    public static int PLAYER_TWO_SCORE;
    public static String PLAYER_TWO;
   private  static Stage STAGE_OF_VIEW_VIDEO;

    public static Stage STAGE_OF_BOARD;
   private  static boolean IS_VIEW_VIDEO;
   private  static boolean IS_FIRST;

   EasyLogic easy;
   OnlineGame online;

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
                STAGE_OF_BOARD.setScene(scene);
                STAGE_OF_BOARD.show();
                }catch (IOException ex) {
                    Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
                 }
            break;
            default:
                try{
                Parent root = FXMLLoader.load(getClass().getResource("/welcomescreen/WelcomeScreen.fxml"));
                Scene scene=new Scene(root);
                STAGE_OF_BOARD.setScene(scene);
                STAGE_OF_BOARD.show();
                }catch (IOException ex) {
                    Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                break;
                
        }
      
    }
    
    @FXML
    public void clickPostionOne(ActionEvent event){
        upgradeUi(1,'X');
        calcNextMove(0,0);
    }



    @FXML
    public void clickPostionTwo(ActionEvent event){
        upgradeUi(2,'X');
        calcNextMove(0,1);
    }
    @FXML
    public void clickPostionThree(ActionEvent event){
        upgradeUi(3,'X');
        calcNextMove(0,2);
    }
    @FXML
    public void clickPostionFour(ActionEvent event){
        upgradeUi(4,'X');
        calcNextMove(1,0);
    }
    @FXML
    public void clickPostionFive(ActionEvent event){
        upgradeUi(5,'X');
        calcNextMove(1,1);

    }
    @FXML
    public void clickPostionSix(ActionEvent event){
        upgradeUi(6,'X');
        calcNextMove(1,2);

    }
    @FXML
    public void clickPostionSeven(ActionEvent event){
        upgradeUi(7,'X');
        calcNextMove(2,0);
    }
    @FXML
    public void clickPostionEight(ActionEvent event){
        upgradeUi(8,'X');
        calcNextMove(2,1);

    }
    @FXML
    public void clickPostionNine(ActionEvent event){
        upgradeUi(9,'X');
        calcNextMove(2,2);
    }
    
    /**
     * Initializes the controller class.
     */

    private void viewVideo(){
        if (TYPE==GameType.ONLINE_GAME) {
            try {
                ClientConnection.closeConnection();
            } catch (IOException e) {
                Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        else
            STATUS_OF_GAME=StatusGame.WIN;

        IS_VIEW_VIDEO=true;
        STAGE_OF_VIEW_VIDEO= new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/boardscreen/ViewVideo.fxml"));
        } catch (IOException e) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, e);
        }

        Scene scene=new Scene(root);
        STAGE_OF_VIEW_VIDEO.setScene(scene);
        STAGE_OF_VIEW_VIDEO.showAndWait();

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        easy = new EasyLogic();

        if (!IS_VIEW_VIDEO){
        switch (TYPE) {
            case helper.GameType.ONLINE_GAME:
                online=new OnlineGame(IS_FIRST,PLAYER_TWO);
                if (IS_FIRST){
                    scoreOfPlayerOne.setText(PlayerData.USERNAME);
                    scoreOfPlayerTwo.setText(PLAYER_TWO);
                    scoreOne.setText(PlayerData.SCORE+"");
                    scoreTwo.setText(PLAYER_TWO_SCORE+"");
                }
                else {

                    scoreOfPlayerTwo.setText(PlayerData.USERNAME);
                    scoreOfPlayerOne.setText(PLAYER_TWO);
                    scoreTwo.setText(PlayerData.SCORE+"");
                    scoreOne.setText(PLAYER_TWO_SCORE+"");
                    disableALL();
                    calcMovePlayer();

                }

            break;


            case GameType.SINGLE_PLAYER_EASY_LEVEL:
                nameOfPlayerTwo.setText("COMPUTER");
                nameOfPlayerOne.setText("ME");

            case GameType.SINGLE_PLAYER_MEDIUM_LEVEL:
                nameOfPlayerTwo.setText("COMPUTER");
                nameOfPlayerOne.setText("ME");

            case GameType.SINGLE_PLAYER_HARD_LEVEL:
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


    //call this function to send indices to (easy-medium-hard - etc...) class
    public void calcNextMove(int row , int col){
        switch (TYPE){// The return type for any method should be the new position and
            case GameType.SINGLE_PLAYER_EASY_LEVEL:
                //call easy method and pass row and column
                System.out.println(easy.checkWinner());


                System.out.println(easy.checkWinner());
                upgradeUi( easy.setPlayerMove(row , col),'X');
                if (easy.checkWinner()== 'X'){

                   viewVideo();

               }else {

                   int move = easy.addInRandomPosition();
                   System.out.println(easy.checkWinner());
                    upgradeUi(move,'O');
                   if (easy.checkWinner() == 'O') {

                       viewVideo();
                   }


               }
                //example: upgradeUi(MediumLevel.calcNextMove() , MediumLevel.playerSymbol());
                //calcMove() should return the position (1 - 9)
                //playerSymbol() should return the 'X' or 'O'
                break;
            case GameType.SINGLE_PLAYER_MEDIUM_LEVEL:
                //call medium method and pass row and column
                break;
            case GameType.SINGLE_PLAYER_HARD_LEVEL:
                //call hard method and pass row and column
                break;
            case GameType.TWO_PLAYER_GAME:
                //local game
                break;
            case GameType.ONLINE_GAME:
                if(online.isTurn){
                    int x=online.setMove(row,col);
                    char c= online.checkWinner();
                    if(c==online.getChar()){viewVideo();STATUS_OF_GAME=StatusGame.WIN;}
                    else if(c=='t'){viewVideo();STATUS_OF_GAME=StatusGame.DRAW;}
                    else if(c=='n')
                        upgradeUi(x,online.getChar());
                    online.sendMsg(x, MsgType.SEND_MOVE);

                }
                else {


                }
        }
    }


    //setNextMove() will be called when we need to update UI after calculating the next move in (easy-medium-hard)level
    // in its own classes

    //buttonNumber = (row+1)*(col+1)
    private void upgradeUi(int buttonNumber, char playerChar){
        Button tempButton;
        switch (buttonNumber){
            case 1:
                tempButton= postionOne;
                break;
            case 2:
                tempButton= postionTwo;
                break;
            case 3:
                tempButton= postionThree;
                break;
            case 4:
                tempButton= postionFour;
                break;
            case 5:
                tempButton= postionFive;
                break;
            case 6:
                tempButton= postionSix;
                break;
            case 7:
                tempButton= postionSeven;
                break;
            case 8:
                tempButton= postionEight;
                break;
            default:
                tempButton= postionNine;

        }
        tempButton.setText(playerChar+"");
        tempButton.setDisable(true);
        tempButton.setOpacity(1);
    }

    void calcMovePlayer(){
        String msg=online.getMsg();
        switch (MsgType.getMsgType(msg)){
            case MsgType.SEND_MOVE:
                int move= MsgType.getmove(msg);
                online.setMove((move/3)-1,move%3);
                char c=online.checkWinner();
                if(c==online.getCharOfPlayerTwo()){viewVideo();STATUS_OF_GAME=StatusGame.LOSE;}
                else if(c=='t'){viewVideo();STATUS_OF_GAME=StatusGame.DRAW;}
                else if(c=='n'){
                    upgradeUi(move,online.getCharOfPlayerTwo());
                    enableButton();
                }


        }
    }

    private void enableButton() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (online.board[i][j] == (char)0) {
                    int x=(3*i)+(j+1);
                }
            }
        }
    }
    private void disableALL() {
    }

}
