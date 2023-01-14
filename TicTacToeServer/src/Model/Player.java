package Model;

public class Player {
    private String username , password;
    private int score;

    public Player(String name, String password, int score){
        setPassword(password);
        setScore(score);
        setUsername(name);
    }
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


    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public int getScore(){
        return score;
    }

}
