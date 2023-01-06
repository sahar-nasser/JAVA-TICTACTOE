package easylevel;
import models.GameLogic;
import java.util.Random;
public class EasyLogic extends GameLogic{
/* when user click 1-chick for winner.
    2- if no winner -> get random number
    3- chick random number is fill of empty.
    4- if empty -> mark "o".
    5- change round.
    6- repeat from number 1.
    7- record human move.
    8-
 */
private Random mRand;

/*
private char turn;
    public void changeTurns() {
         if (turn == human )
            turn = ai;
        else
            turn = human;

    }*/


 public  int addInRandomPosition(){
     int  column;
     int row;
     int move;
     mRand = new Random();
     do
     {
         move = mRand.nextInt(9);
        column = move%3;
        row = move/3;
        row -=1;
        if(column == 0){
            column = 2;
        }else{
            column -=1;
        }
     } while (board[row][column] == human || board[row][column] == ai);
     board[row][column] = ai;
     return move;
 }

    public void setPlayerMove(int row, int col) {
        board[row][col] = 'X';

    }
}
