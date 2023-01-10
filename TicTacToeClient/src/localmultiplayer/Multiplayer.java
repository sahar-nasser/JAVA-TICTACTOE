package localmultiplayer;

import models.GameLogic;

public class Multiplayer extends GameLogic  {
    int curr_r;
    int curr_c;
    public boolean onePlayed;
    public boolean twoPlayed;
    static char won;

    public  Multiplayer(){
        curr_c=0;
        curr_r=0;
        won='n';
        onePlayed=false;
        twoPlayed=true;
    }

    public void getIndex(int r, int c){
        curr_r=r;
        curr_c=c;
    }
    public void playerOneTurn(){
        //if (availableCells()!=0){
        //set current in array in corresponding pos
        board[curr_r][curr_c]='X';
        won = checkWinner();
        if (won=='X'){
            //call playvid
            System.out.println("X won");
        } else if (won=='O'){
            System.out.println("O won");
        } else if (won=='t') {
            System.out.println("tie");
        } else {
            System.out.println("not yet");
        }
        onePlayed=true;
        twoPlayed=false;

    }
    public void playerTwoTurn(){
        // if (availableCells()!=0){
        //set current in array in corresponding pos
        board[curr_r][curr_c]='O';
        won = checkWinner();
        if (won=='X'){
            //call playvid
            System.out.println("X won");
        } else if (won=='O'){
            System.out.println("O won");
        } else if (won=='t'){
            System.out.println("tie");
        }
        else {
            System.out.println("not yet");
        }
        onePlayed=false;
        twoPlayed=true;

    }
    /*  public void endGame(){
          playvid();
      }*/
    void playvid(char w){
        if (w =='t'){
            //load tie video
        }
        if(w== 'X'){
            //play x won stuff
        }
        if(w == 'O'){
            //play x won stuff
        }
    }
}
