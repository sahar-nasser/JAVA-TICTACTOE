package mediumlevel;

import models.GameLogic;

public class MediumLevel extends GameLogic {

    //returns the position number
    public int decideFirstMove(int row , int col){
        row = (row+2) % 3;
        col = ++col % 3;
        addPlayerMove(row,col,ai);
        return  3*row + ++col;
    }

    public void addPlayerMove(int row , int col , char currentChar){
        board[row][col] = currentChar;
    }

    //output will be -1 or a new position for the next move
    //replace winningSymbol with O and losingSymbol with X to understand
    public int checkPlayerPossibleWinning(char winningSymbol , char losingSymbol){//by default here computer char is O
        int retValue = -1;//can't win
        for(int row = 0 ; row < 3 ; ++row){// checking winning in all rows
            int computerCharCounter = 0 , emptyCellIndex = 0;
            for(int col = 0 ; col < 3 ; ++col){
                if(board[row][col] == winningSymbol)
                    computerCharCounter++;
                else if(board[row][col] != losingSymbol)
                    emptyCellIndex = col;
            }
            if(computerCharCounter == 2){
//                board[row][emptyCellIndex] = winningSymbol;
                addPlayerMove(row,emptyCellIndex,losingSymbol);
                retValue = 3*row + ++emptyCellIndex;
                row = 3;
            }
        }

        if(retValue == -1){//if already there computer can't win through rows then check columns

            for(int col = 0 ; col < 3 ; ++col){// checking winning in all columns
                int computerCharCounter = 0 , emptyCellIndex = 0;
                for(int row = 0 ; row < 3 ; ++row){
                    if(board[row][col] == winningSymbol)
                        computerCharCounter++;
                    else if(board[row][col] != losingSymbol)
                        emptyCellIndex = row;
                }
                if(computerCharCounter == 2){
//                    board[emptyCellIndex][col] = winningSymbol;
                    addPlayerMove(emptyCellIndex,col,losingSymbol);
                    retValue = 3*emptyCellIndex + ++col;
                    col = 3;
                }
            }
        }

        if(retValue == -1){//checking diagonals
            if(((board[0][0] == winningSymbol) && (winningSymbol==board[1][1])) && board[2][2]!=losingSymbol){//checking first diagonal
//                board[2][2] = winningSymbol;
                addPlayerMove(2,2,winningSymbol);
                retValue = 9;
            }else if(((board[0][0] == winningSymbol) && (winningSymbol==board[2][2])) && board[1][1]!=losingSymbol){
                addPlayerMove(0,0,winningSymbol);
//                board[1][1] = winningSymbol;
                retValue = 5;
            }else if(((board[1][1] == winningSymbol) && (winningSymbol==board[2][2])) && board[0][0]!=losingSymbol){
                addPlayerMove(0,0,winningSymbol);
//                board[0][0] = winningSymbol;
                retValue = 1;
            }

            if(retValue == -1){//checking second diagonal
                if(((board[0][2] == winningSymbol) && (winningSymbol==board[1][1])) && board[2][0]!=losingSymbol){
                    addPlayerMove(2,0,winningSymbol);
//                    board[2][0] = winningSymbol;
                    retValue = 7;
                }else if(((board[0][2] == winningSymbol) && (winningSymbol==board[2][0])) && board[1][1]!=losingSymbol){
                    addPlayerMove(1,1,winningSymbol);
//                    board[1][1] = winningSymbol;
                    retValue = 5;
                }else if(((board[1][1] == winningSymbol) && (winningSymbol==board[2][0])) && board[0][2]!=losingSymbol){
                    addPlayerMove(0,2,winningSymbol);
//                    board[0][2] = winningSymbol;
                    retValue = 3;
                }
            }
        }


        return retValue;
    }

    public int generateRandomMove(char computerSymbol){
        int retValue = -1;
        for(int row = 0 ; row < 3 ; ++row){
            for(int col = 0 ; col < 3 ; ++col){
                if(board[row][col] == 'n'){
                    addPlayerMove(row,col,computerSymbol);
                    retValue = 3*row + ++col;
                    row = col = 3;
                }
            }
        }
        return retValue;
    }

}
