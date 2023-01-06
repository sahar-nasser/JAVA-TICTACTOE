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
 */
private Random mRand;
private char turn;
    public void changeTurns() {
         if (turn == human )
            turn = ai;
        else
            turn = human;

    }

 @Override
 public void setup(){
     super.setup();

 }
 public  void addInRandomPosition(){
     int  column;
     int row;
     int move;
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
 }

 public void buttonClicked(int  index){
     char winner = checkWinner();
     if (winner == 'n' && turn == ai  ){
          addInRandomPosition();
         changeTurns();
         checkWinner();
     }
 }

}
