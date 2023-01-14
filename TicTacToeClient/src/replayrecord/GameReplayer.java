package replayrecord;
import models.Player;
import java.util.Arrays;
import java.util.List;


public class GameReplayer  {
       private String game;
       RecordsCreator currentRec;
       Record rec;
       Player player;
       boolean hasPlayed;
       boolean gameDone;
       char playerChar;
       public int index;
       int btn_pos;
       int recIndex;
       List<String> myRecord;

    public GameReplayer(){
        rec = new Record();
        player =new Player();
        currentRec=new RecordsCreator();
        game=" ";
        hasPlayed=false;
        gameDone=false;
        playerChar=' ';
        index=0;
        btn_pos =0;
        recIndex=0;
    }

//set index based on which record the user picked in list view
   public void setRecIndex(int recIndex){
        this.recIndex=recIndex;
   }
   public String getGameFromRec(){
       myRecord= currentRec.getRecord(recIndex);
       game=myRecord.get(4);
       return game;
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
