package replayrecord;
import javafx.application.Platform;
import models.Player;
import java.util.Arrays;


public class GameReplayer extends Thread {
 //awel 7aga agrab l ta2te3 wl donia
   String game;
   Record rec;
   Player player;
   boolean hasPlayed;
   boolean gameDone;
   Thread playMoveOnScreen = new Thread();
   int moves[];
   char playerChar;
   int index;
    Thread playGame ;


    ///check if array <9
    public GameReplayer(){
        rec = new Record();
        player =new Player();
        game = "1,4,5,8,9";
        hasPlayed=false;
        gameDone=false;
        playerChar=' ';
        index=0;
        playGame= new Thread();
    }

    public void setCurrentChar(char playerChar){
        this.playerChar=playerChar;
    }

    public void setPosition(int index){
        this.index=index;
    }
    public int[] createArrayMove() {
           moves = Arrays.stream(game.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
           return moves;
    }

    public int  getPosition(){
        return index;
    }

    public char currentChar(){
        return playerChar;
    }

      @Override
      public void run() {
          playGame.start();
        Platform.runLater(() -> {
            int gameMove[] = createArrayMove();
            //play move
            try {
                for (int i : gameMove) {
                    if (hasPlayed == false || i == 0) {
                        setCurrentChar('X');
                        setPosition(gameMove[i]);
                        playMoveOnScreen.sleep(5000);
                        hasPlayed = true;
                    } else {
                        setCurrentChar('O');
                        setPosition(gameMove[i]);
                        playMoveOnScreen.sleep(5000);
                        hasPlayed = false;
                    }
                }
                gameDone = true;
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });

            }


}
