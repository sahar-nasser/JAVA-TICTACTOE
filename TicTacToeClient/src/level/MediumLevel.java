package level;

import models.GameLogic;

public class MediumLevel extends GameLogic {

    //returns the position number

    public int decideFirstMove(char aiSymbol){
        /*
        * (int)Math.floor(Math.random() * (max - min + 1) + min);
        * adding +1 because it's missing a number so we add it like 9-1=8 i want 9
        * adding min because the numbers always under the limit so now i shift the numbers above to start from the min
        * */
        int row , col;
        do{
            row =(int)Math.floor(Math.random()*3);
            col=(int)Math.floor(Math.random()*3);
        }while(board[row][col] == 'X');
        addPlayerMove(row,col,aiSymbol);
        return  3*row + ++col;
    }
    public void addPlayerMove(int row , int col , char currentChar){
        board[row][col] = currentChar;
    }

    //output will be -1 or a new position for the next move
    //replace winningSymbol is the user symbol and losing symbol is computer symbol
    //winningSymbol because i want check winning situation for him and block it by the losing side
    public int checkPlayerPossibleWinning(char winningSymbol , char losingSymbol){
        int retValue = -1;//can't win
        for(int row = 0 ; row < 3 ; ++row){// checking winning in all rows
            int computerCharCounter = 0 , emptyCellIndex = -1;
            for(int col = 0 ; col < 3 ; ++col){
                if(board[row][col] == winningSymbol)
                    computerCharCounter++;
                else if(board[row][col] == (char)0)
                    emptyCellIndex = col;
            }
            if(computerCharCounter == 2 && emptyCellIndex!=-1){
                addPlayerMove(row,emptyCellIndex,losingSymbol);
                retValue = 3*row + ++emptyCellIndex;
                row = 3;
            }
        }

        if(retValue == -1){//if already there computer can't win through rows then check columns

            for(int col = 0 ; col < 3 ; ++col){// checking winning in all columns
                int computerCharCounter = 0 , emptyCellIndex = -1;
                for(int row = 0 ; row < 3 ; ++row){
                    if(board[row][col] == winningSymbol)
                        computerCharCounter++;
                    else if(board[row][col] == (char)0)
                        emptyCellIndex = row;
                }
                if(computerCharCounter == 2 && emptyCellIndex!=-1){
//                    board[emptyCellIndex][col] = winningSymbol;
                    addPlayerMove(emptyCellIndex,col,losingSymbol);
                    retValue = 3*emptyCellIndex + ++col;
                    col = 3;
                }
            }
        }

        if(retValue == -1){//checking diagonals
            if(((board[0][0] == winningSymbol) && (winningSymbol==board[1][1])) && board[2][2]==(char)0){//checking first diagonal
                addPlayerMove(2,2,losingSymbol);
                retValue = 9;
            }else if(((board[0][0] == winningSymbol) && (winningSymbol==board[2][2])) && board[1][1]==(char)0){
                addPlayerMove(1,1,losingSymbol);
                retValue = 5;
            }else if(((board[1][1] == winningSymbol) && (winningSymbol==board[2][2])) && board[0][0]==(char)0){
                addPlayerMove(0,0,losingSymbol);
                retValue = 1;
            }

            if(retValue == -1){//checking second diagonal
                if(((board[0][2] == winningSymbol) && (winningSymbol==board[1][1])) && board[2][0]==(char)0){
                    addPlayerMove(2,0,losingSymbol);
                    retValue = 7;
                }else if(((board[0][2] == winningSymbol) && (winningSymbol==board[2][0])) && board[1][1]==(char)0){
                    addPlayerMove(1,1,losingSymbol);
                    retValue = 5;
                }else if(((board[1][1] == winningSymbol) && (winningSymbol==board[2][0])) && board[0][2]==(char)0){
                    addPlayerMove(0,2,losingSymbol);
                    retValue = 3;
                }
            }
        }


        return retValue;
    }

    //generateRandomMove to be upgraded

    public int generateRandomMove(char computerSymbol){
        int retValue = -1;
        for(int row = 0 ; row < 3 ; ++row){
            for(int col = 0 ; col < 3 ; ++col){
                if(board[row][col] == (char)0){
                    addPlayerMove(row,col,computerSymbol);
                    retValue = 3*row + ++col;
                    row = col = 3;
                }
            }
        }
        return retValue;
    }

}
