package helper;

public class Player {
    static private String username , password;
    static private int score;
    static private char playingSymbol;

    //setters and getters

    public static void setUsername(String username){
        Player.username = username;
    }

    public static void setPassword(String password){
        Player.password = password;
    }

    public static void setScore(int score){
        Player.score = score;
    }

    public static void setPlayingSymbol(char playingSymbol){
        Player.playingSymbol = playingSymbol;
    }
    public static String getUsername(){
        return username;
    }

    public static String getPassword(){
        return password;
    }

    public static int getScore(){
        return score;
    }

    public static char getPlayingSymbol(){
        return playingSymbol;
    }
}
