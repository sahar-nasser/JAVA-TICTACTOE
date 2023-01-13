package replayrecord;
import models.Player;
import java.util.Arrays;


public class GameReplayer  {
 //awel 7aga agrab l ta2te3 wl donia
   String game;
   Record rec;
   Player player;
   boolean hasPlayed;
   boolean gameDone;
   int moves[];
   char playerChar;
  public int index;
  int btn_pos;


    ///check if array <9
    public GameReplayer(){
        rec = new Record();
        player =new Player();
        game = "1,4,5,8,9";
        hasPlayed=false;
        gameDone=false;
        playerChar=' ';
        index=0;
        btn_pos =0;
    }

    public void setCurrentChar(char playerChar){
        this.playerChar=playerChar;
    }

    public void setPosition(int i){
            btn_pos=i;
    }
    public int[] createArrayMove() {
           return Arrays.stream(game.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
    }

    public int  getPosition(){
        return btn_pos;
    }

    public char currentChar(){

       return playerChar;
    }

    public void createMove(){
        if(hasPlayed){
            playerChar='O';
            hasPlayed=false;
        }else{
            playerChar='X';
            hasPlayed=true;
        }
        setCurrentChar(playerChar);
        setPosition(createArrayMove()[index]);
        index++;
    }

    public boolean gameEnded(){
        if(index == createArrayMove().length){
            System.out.println(createArrayMove().length+ ""+ index);
             gameDone=true;
        }
        return gameDone;
    }

}
