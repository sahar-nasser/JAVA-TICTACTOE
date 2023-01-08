package localmultiplayer;

import javafx.application.Platform;
import models.GameLogic;
import boardscreen.BoardController;

public class Multiplayer extends GameLogic  {
    //int btnNum;
    int curr_r;
    int curr_c;
    public int counter=0;
    public boolean onePlayed;
    public boolean twoPlayed;
    static char won;

    public  Multiplayer(){
        curr_c=0;
        curr_r=0;
        counter=0;
        won='n';
        onePlayed=false;
        twoPlayed=true;
    }

    //to be called to start game logic
    /** public void play(){
     for(int i=0; i<8; i++){
     playerOneTurn();
     }
     //array is full
     }**/


    /** public int setBtn(int r, int c){
     btnNum= (r+1)*(c+1);
     return btnNum;
     }
     public char sendChar(){
     return curr;
     }**/
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
        counter++;
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
        counter++;
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
