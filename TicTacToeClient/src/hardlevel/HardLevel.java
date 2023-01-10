package hardlevel;

import models.GameLogic;

public class HardLevel extends GameLogic {

    public boolean humanPlayed;
    public boolean aiPlayed;
    int curr_r;
    int curr_c;
    static char won;
    public int btnPosition;

    public HardLevel() {
      humanPlayed=false;
      aiPlayed=true;
      curr_r=0;
      curr_c=0;
      won='n';
    }

    public void getIndex(int r, int c){
        curr_c=c;
        curr_r=r;
    }
    public void setPosition(int aiR, int aiC){
        btnPosition = (3*aiR)+(aiC+1);
    }
    public int forwardMove(){
        return btnPosition;
    }
    public  void userTurn() {
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
        humanPlayed=true;
        aiPlayed=false;
    }

    public void AiTurn() {
        // AI to make its turn
        int bestScore = Integer.MIN_VALUE;
        int[] move = {0, 0};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Is the spot available?
                if (board[i][j] == (char) 0) {
                    board[i][j] = 'O';
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
        board[move[0]][move[1]] = 'O';
        setPosition(move[0], move[1]);
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
        humanPlayed=false;
        aiPlayed=true;
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
                        board[i][j] = 'O'; //set ui with same index
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
                        board[i][j] = 'X';
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
