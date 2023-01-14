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


import level.EasyLogic;
import level.HardLevel;
import helper.GameType;
import helper.MsgType;
import helper.PlayerData;
import helper.StatusGame;
import javafx.application.Platform;
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
import level.MediumLevel;
import level.OnlineGame;
import localmultiplayer.Multiplayer;
import replayrecord.GameReplayer;

import static java.lang.Thread.sleep;


/**
 * FXML Controller class
 *
 * @author ammar
 */
public class BoardController extends Thread implements Initializable {
    private Thread thread;
    private MediumLevel mediumLevel;
    private static MediaPlayer MEDIA_PLAYER;
    public static int TYPE;
    private static int STATUS_OF_GAME;

    private boolean isFirstMove = false;

    public static int PLAYER_TWO_SCORE;
    public static String PLAYER_TWO;
   private  static Stage STAGE_OF_VIEW_VIDEO;

    public static Stage STAGE_OF_BOARD;
   private  static boolean IS_VIEW_VIDEO;
   private  static boolean IS_FIRST;
    boolean isRecording;
   EasyLogic easy;

    OnlineGame online;
    Multiplayer mp = new Multiplayer();
    HardLevel hl = new HardLevel();



   @FXML
   private Button record;
   @FXML
   private Button close;
   @FXML
   private Button positionOne;
   @FXML
   private Button positionTwo;
   @FXML
   private Button positionThree;
   @FXML
   private Button positionFour;
   @FXML
   private Button positionFive;
   @FXML
   private Button positionSix;
   @FXML
   private Button positionSeven;
   @FXML
   private Button positionEight;
   @FXML
   private Button positionNine;

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

    private Button[] arr;

    @FXML
    public void recordGame(ActionEvent event){
        if(!isRecording){
            record.setStyle("-fx-background-color:#ff0000");
            record.setText("Recording");
            isRecording=true;
        }

    }

    @FXML
    public void closeGame(ActionEvent event){

        if (IS_VIEW_VIDEO){
            STAGE_OF_VIEW_VIDEO.close();
            MEDIA_PLAYER.stop();

        }
        switch (TYPE) {
            case helper.GameType.ONLINE_GAME:

                try{
                    if (!IS_VIEW_VIDEO)online.sendCloseGame(PLAYER_TWO);
                    IS_VIEW_VIDEO=false;
                    if (isRecording)online.sendRecording();
                    online.closeConnection();
                    Parent root = FXMLLoader.load(getClass().getResource("/onlineplayerscreen/FXMLPlayerList.fxml"));
                    Scene scene=new Scene(root);
                    STAGE_OF_BOARD.setScene(scene);
                    STAGE_OF_BOARD.show();
                }catch (IOException ex) {
                    System.out.println(ex.getMessage());
                 }
            break;
            default:
                try{
                    Parent root = FXMLLoader.load(getClass().getResource("/welcomescreen/WelcomeScreen.fxml"));
                    IS_VIEW_VIDEO=false;
                    Scene scene=new Scene(root);
                    STAGE_OF_BOARD.setScene(scene);
                    STAGE_OF_BOARD.show();
                }catch (IOException ex) {
                    System.out.println(ex.getMessage());
                 }
                break;

        }

    }

    @FXML
    public void clickPositionOne(ActionEvent event){
        calcNextMove(0,0,1);
    }

    @FXML
    public void clickPositionTwo(ActionEvent event){
        calcNextMove(0,1,2);
    }
    @FXML
    public void clickPositionThree(ActionEvent event){
        calcNextMove(0,2,3);
    }
    @FXML
    public void clickPositionFour(ActionEvent event){
        calcNextMove(1,0,4);
    }
    @FXML
    public void clickPositionFive(ActionEvent event){
        calcNextMove(1,1,5);
    }
    @FXML
    public void clickPositionSix(ActionEvent event){
        calcNextMove(1,2,6);
    }

    @FXML
    public void clickPositionSeven(ActionEvent event){
        calcNextMove(2,0,7);
    }
    @FXML
    public void clickPositionEight(ActionEvent event){
        calcNextMove(2,1,8);
    }
    @FXML
    public void clickPositionNine(ActionEvent event) {
        calcNextMove(2, 2, 9);
    }

    private void viewVideo(){
        IS_VIEW_VIDEO=true;
        STAGE_OF_VIEW_VIDEO= new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/boardscreen/ViewVideo.fxml"));
        } catch (IOException e) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, e);
        }

        Scene scene = new Scene(root);
        STAGE_OF_VIEW_VIDEO.setScene(scene);
        STAGE_OF_VIEW_VIDEO.showAndWait();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        isRecording=false;
        if (!IS_VIEW_VIDEO){
            arr= new Button[]{positionOne, positionTwo, positionThree, positionFour, positionFive, positionSix, positionSeven, positionEight, positionNine};
        switch (TYPE) {
            case helper.GameType.ONLINE_GAME:
                online = new OnlineGame(IS_FIRST, PLAYER_TWO);
                if (IS_FIRST) {
                    scoreOfPlayerOne.setText(PlayerData.USERNAME);
                    scoreOfPlayerTwo.setText(PLAYER_TWO);
                    scoreOne.setText(PlayerData.SCORE + "");
                    scoreTwo.setText(PLAYER_TWO_SCORE + "");
                } else {

                    scoreOfPlayerTwo.setText(PlayerData.USERNAME);
                    scoreOfPlayerOne.setText(PLAYER_TWO);
                    scoreTwo.setText(PlayerData.SCORE + "");
                    scoreOne.setText(PLAYER_TWO_SCORE + "");
                    disableALL();
                    calcMovePlayer();

                }

                break;
            case GameType.REPLAYED_GAME:
                scoreOfPlayerOne.setVisible(false);
                scoreOfPlayerTwo.setVisible(false);
                scoreOne.setVisible(false);
                scoreTwo.setVisible(false);
                record.setVisible(false);
                System.out.println("this is game");
                //disableALL();
                new Thread(()->{
                    GameReplayer gameReplayer = new GameReplayer();
                    while (!gameReplayer.gameEnded()) {
                        try {
                            sleep(500);
                            gameReplayer.createMove();
                            Platform.runLater(() -> {
                                upgradeUi(gameReplayer.getPosition(), gameReplayer.currentChar());
                            });
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
                break;

            case GameType.SINGLE_PLAYER_EASY_LEVEL:
                easy = new EasyLogic();
            case GameType.SINGLE_PLAYER_MEDIUM_LEVEL:
                mediumLevel = new MediumLevel();
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
                    media = new Media(Paths.get("TicTacToeClient/src/resource/win.mp4").toUri().toString());
                    if (TYPE==GameType.ONLINE_GAME)online.sendScoreMsg(10);
                    break;
                case StatusGame.DRAW:
                    STAGE_OF_VIEW_VIDEO.setTitle("DRAW");
                    media = new Media(Paths.get("TicTacToeClient/src/resource/tie.mp4").toUri().toString());
                    break;
                default:
                    STAGE_OF_VIEW_VIDEO.setTitle("LOSE");
                    media = new Media(Paths.get("TicTacToeClient/src/resource/lose.mp4").toUri().toString());
                    if (TYPE==GameType.ONLINE_GAME)online.sendScoreMsg(-5);
            }
        MEDIA_PLAYER =new MediaPlayer(media);
        MEDIA_PLAYER.setAutoPlay(true);
        MEDIA_PLAYER.setOnReady(()->STAGE_OF_VIEW_VIDEO.sizeToScene());
        mediaView.setMediaPlayer(MEDIA_PLAYER);
        }
    }
    //call this function to send indices to (easy-medium-hard - etc...) class

    public void calcNextMove(int row , int col, int position){
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
                upgradeUi(position, mediumLevel.human);
                mediumLevel.addPlayerMove(row, col, mediumLevel.human);//add it in the array
                //check if X could win
                char winnerResult = mediumLevel.checkWinner();
                if (winnerResult == 'n') {
                    int computerMove;
                    if (!isFirstMove) {
                        computerMove = mediumLevel.decideFirstMove(mediumLevel.ai);
                        isFirstMove = true;//I want to enter here only once
                    } else {
                        /*
                         * I want computer to win, so I'm the winning side 'O' and i want to check if could win
                         * and if i can , I don't want the losingSymbol to be 'X' i want it to be me 'O'
                         * */
                        computerMove = mediumLevel.checkPlayerPossibleWinning(mediumLevel.ai, mediumLevel.ai);
                        if (computerMove == -1) {//-1 means I can't win
                            computerMove = mediumLevel.checkPlayerPossibleWinning(mediumLevel.human, mediumLevel.ai);//check blocking the X move
                            if (computerMove == -1) {//then I will generate random number to my move
                                computerMove = mediumLevel.generateRandomMove(mediumLevel.ai);
                            }
                        }
                        //1.check if I could win
                        //2.if not then check if x could win and don't let him
                        //3.if not then decide my next move which will be random I guess or not
                    }
                    int finalComputerMove = computerMove;
                    System.out.println("hasIn");
                    new Thread(()->{
                        try {
                            sleep(400);
                            System.out.println("======");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.println();
                                    upgradeUi(finalComputerMove, mediumLevel.ai);
                                }
                            });
                        } catch (InterruptedException e) {
                            System.out.println(e.getMessage());
                        }

                    }).start();

                } else {
                    if(mediumLevel.checkWinner() == 'X'){//player won
                        STATUS_OF_GAME=StatusGame.WIN;
                    }else if(mediumLevel.checkWinner() == 'O'){//player lost
                        STATUS_OF_GAME=StatusGame.LOSE;
                    }else{
                        STATUS_OF_GAME = StatusGame.DRAW;
                    }
                    viewVideo();
                }
                if(winnerResult == 'n' && mediumLevel.availableCells() == 0){//tie
                    STATUS_OF_GAME = StatusGame.DRAW;
                    viewVideo();
                }
                break;
            case GameType.SINGLE_PLAYER_HARD_LEVEL:
                //call hard method and pass row and column
                if(hl.humanPlayed){
                    hl.AiTurn();
                    upgradeUi(hl.forwardMove(),'O');
                }
                else if(hl.aiPlayed){
                    hl.getIndex(row,col);
                    hl.userTurn();
                    upgradeUi(position, 'X');
                }

                break;
            case GameType.TWO_PLAYER_GAME:
                 mp.getIndex(row,col);
                if(mp.onePlayed){
                    mp.playerTwoTurn();
                    upgradeUi(position,'O');
                }
                else if(mp.twoPlayed){
                    mp.playerOneTurn();
                    upgradeUi(position,'X');
                }
                //local game

                break;

            case GameType.ONLINE_GAME:

                    int x=online.setMove(row,col);
                    char c= online.checkWinner();
                    if(c==online.getChar()){viewVideo();STATUS_OF_GAME=StatusGame.WIN;}
                    else if(c=='t'){viewVideo();STATUS_OF_GAME=StatusGame.DRAW;}
                    else if(c=='n')
                        upgradeUi(x,online.getChar());
                    online.sendMoveMsg(x);

        }
    }

   /** public void setDataFromRecord(int buttonNumber, char playerChar){
            upgradeUi(buttonNumber, playerChar);
    }**/
    //setNextMove() will be called when we need to update UI after calculating the next move in (easy-medium-hard)level
    // in its own classes

    private void upgradeUi(int buttonNumber, char playerChar){
        Button tempButton;
        switch (buttonNumber) {
            case 1:
                tempButton= positionOne;
                break;
            case 2:
                tempButton= positionTwo;
                break;
            case 3:
                tempButton= positionThree;
                break;
            case 4:
                tempButton= positionFour;
                break;
            case 5:
                tempButton= positionFive;
                break;
            case 6:
                tempButton= positionSix;
                break;
            case 7:
                tempButton= positionSeven;
                break;
            case 8:
                tempButton= positionEight;
                break;
            default:
                tempButton= positionNine;
        }
        tempButton.setText(playerChar + "");
        tempButton.setDisable(true);
        tempButton.setOpacity(1);
    }

    void calcMovePlayer(){
        String msg=online.getMsg();
        switch (MsgType.getMsgType(msg)){
            case MsgType.SEND_MOVE:
                int move= MsgType.getMove(msg);
                online.setMove((move/3)-1,move%3);
                char c=online.checkWinner();
                if(c==online.getCharOfPlayerTwo()){viewVideo();STATUS_OF_GAME=StatusGame.LOSE;}
                else if(c=='t'){viewVideo();STATUS_OF_GAME=StatusGame.DRAW;}
                else if(c=='n'){
                    upgradeUi(move,online.getCharOfPlayerTwo());
                    enableButton();
                }
                break;
            case MsgType.QUIT_GAME:
                viewVideo();
                STATUS_OF_GAME=StatusGame.WIN;
                break;
            case MsgType.SERVER_CLOSE:
                online.closeConnection();
                TYPE=GameType.SINGLE_PLAYER_EASY_LEVEL;
                closeGame(new ActionEvent());
        }
    }

    private void enableButton() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (online.board[i][j] == (char)0) {
                    int x=(3*i)+(j+1);
                    arr[x].setDisable(false);
                }
            }
        }
    }
    private void disableALL() {
           for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (online.board[i][j] == (char)0) {
                    int x=(3*i)+(j+1);
                    System.out.println(x);
                    arr[x].setDisable(true);
                }
            }
        }
    }

}
