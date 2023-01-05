package hardlevel;

import models.GameLogic;

public class HardLevel extends GameLogic implements  Runnable{

    Thread th = new Thread();
    void setupgame() {
        setup();
        AiTurn();
    }

       public  void userTurn() {
            if (currentPlayerIsHuman) {
                // Human make turn
                //know btn
                //if cell/btn is empty place user character
                currentPlayerIsHuman = false;
                AiTurn();
            }
        }

       public void AiTurn() {
            // AI to make its turn
            int bestScore = Integer.MIN_VALUE;
            int[] move = {0, 0};
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Is the spot available?
                    if (board[i][j] == (char) 0) {
                        board[i][j] = ai;
                        int score = minimax(board, 0, false);
                        board[i][j] = (char) 0;
                        if (score > bestScore) {
                            bestScore = score;
                            move[0] = i;
                            move[1] = j;
                        }
                    }
                }
            }
            board[move[0]][move[1]] = ai;
            currentPlayerIsHuman = true;
        }

    public void run(){
        while(true){
            try {
                userTurn();
                th.sleep(1500);
                AiTurn();
                if (availableCells()==0){
                    th.sleep(1000);
                }
            }
            catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }

    int minimax(char[][] board, int depth, boolean isMaximizing) {
        char result = checkWinner();
        if (result != 'n') {
            return scores.get(result);
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Is the spot available?
                    if (board[i][j] == (char) 0) {
                        board[i][j] = ai; //set ui with same index
                        int score = minimax(board, depth + 1, false);
                        board[i][j] = (char) 0;
                        if (bestScore>score){
                            bestScore=score;
                        }
                        //bestScore = max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Is the spot available?
                    if (board[i][j] == (char) 0) {
                        board[i][j] = human;
                        int score = minimax(board, depth + 1, true);
                        board[i][j] = (char) 0;
                        if (bestScore<score){
                            bestScore=score;
                        }
                        //bestScore = min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
}
