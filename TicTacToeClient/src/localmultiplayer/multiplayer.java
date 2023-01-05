package localmultiplayer;

import models.GameLogic;
public class multiplayer extends GameLogic {
    @Override
    public void setup() {
        super.setup();
    }
    char curr ='X';
    char won;

    //to be called to start game logic
    void play(){
       for(int i=0; i<8; i++){
           playerOneTurn();
       }
       //array is full you can now check winner
     won = checkWinner();
    }

    void playerOneTurn(){
        if (availableCells()!=0){
            //check btn
            //if btn not clicked already
            //update ui set text = curr
            //set current in array in corresponding pos
        }
        curr='O';
        playerTwoTurn();
    }

    void playerTwoTurn(){
        if (availableCells()!=0){
            //check btn
            //if btn not clicked already
            //update ui set text = curr
            //set current in array in corresponding pos
        }
        curr='X';
    }

    public void gameEnded(){

    }
    void playvid(){
        if (won =='t'){
            //load tie video
        }
        if(won == 'X'){
            //play x won stuff
        }
        if(won == 'O'){
            //play x won stuff
        }
    }

}
