package easylevel;
import models.GameLogic;
import java.util.Random;
public class EasyLogic extends GameLogic{

private Random mRand;



 public  int addInRandomPosition(){
     int  column;
     int row;

       do{
             row =(int)Math.floor(Math.random()*3);
             column=(int)Math.floor(Math.random()*3);
         }while(board[row][column] != (char) 0);
     board[row][column] = ai;
     System.out.println("row =" +row);
     System.out.println("col =" +column);
     return (3*row)+(column+1);
 }

    public int setPlayerMove(int row, int col) {
        board[row][col] = 'X';
        return (3*row)+(col+1);
    }

}
