package models;

public class Player {
    private String username , password;
    private int score;
    private char playingSymbol;

    //setters and getters

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setPlayingSymbol(char playingSymbol){
        this.playingSymbol = playingSymbol;
    }
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public int getScore(){
        return score;
    }

    public char getPlayingSymbol(){
        return playingSymbol;
    }
}
