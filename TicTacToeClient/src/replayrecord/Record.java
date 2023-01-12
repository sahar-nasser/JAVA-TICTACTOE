package replayrecord;

public class Record {
    private int rec_id;
    private String userName;
    private String playerTwo;
    private boolean isHeFirst;
    private String moves;
    private String date;

    public void setRec_id(int rec_id){
        this.rec_id=rec_id;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setHeFirst(boolean isHeFirst) {
        this.isHeFirst = isHeFirst;
    }

    public void setMoves(String moves) {
        this.moves = moves;
    }

    public String getDate() {
        return date;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public String getUserName() {
        return userName;
    }

    public int getRec_id() {
        return rec_id;
    }

    public boolean getIsHeFirst() {
        return this.isHeFirst;
    }

    public String getMoves() {
        return moves;
    }
}
