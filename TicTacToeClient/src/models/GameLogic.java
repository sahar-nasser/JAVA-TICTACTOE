package models;

import java.util.HashMap;

public class GameLogic {

    /*initializing an empty 3*3 array to mirror my actual game board
    * AI will start will be given O and user will be given x, we need a boolean to check
    * if the current player is the user or the machine + a map of scores for checking
    * */
    public char[][] board = {
            {(char)0, (char)0, (char)0},
            {(char)0, (char)0, (char)0},
            {(char)0, (char)0, (char)0}
    };
    public char ai = 'O';
    public char human = 'X';
    public boolean currentPlayerIsHuman = true;
    public HashMap<Character, Integer> scores = new HashMap<>();
    public void setup(){
        scores.put('X',0);
        scores.put('O',1);
        scores.put('t',-1); //t for tie
    }

    //checks if 3 equal characters were equal and not empty
     boolean connectThree(char one, char two, char three){
      return one == two && two == three && one != (char)0;
    }

    //looks for available cells, the value of the var available is 0 on case the board is full
   public int availableCells(){
        int available = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == (char)0) {
                    available++;
                }
            }
        }
        return  available;
    }
    public char checkWinner(){
         char winner = 'n'; //in case function couldn't compute it returns char n for null

        // check horizontal lines
        for (int i = 0; i < 3; i++) {
            if (connectThree(board[i][0], board[i][1], board[i][2])) {
                winner = board[i][0];
            }
        }

        // check vertical lines and set winner with the value of the char in the three
        for (int i = 0; i < 3; i++) {
            if (connectThree(board[0][i], board[1][i], board[2][i])) {
                winner = board[0][i];
            }
        }

        // check the two diagonal lines and set winner with the value of the char in the three
        if (connectThree(board[0][0], board[1][1], board[2][2])) {
            winner = board[0][0];
        }
        if (connectThree(board[2][0], board[1][1], board[0][2])) {
            winner = board[2][0];
        }

        //check if there are any free cells and if not and no one won then it's a tie
        int free=availableCells();

        if (winner=='n'&& free==0){
            winner='t';
        }
         return winner;
    }

}
