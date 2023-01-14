package helper;

public class MsgType {


    public static final int SEND_REQUEST=0;
    public static final int DATABASECONNECTION = 1;
    public static final int SEND_MOVE=2;
    public static final int lIST_AVAILABLE =3;
    public static final int SERVER_CLOSE =4;
    public static final int QUIT_GAME =5;
    public static final int CANCEL_REQ =6;
    public static final int CONFIRM_REQ =7;
    public  static int getMsgType(String str){
        System.out.println(str);
        return Integer.valueOf(str.split(",")[0]);}
    public  static int getMove(String str){
            return Integer.valueOf(str.split(",")[2]);
        }
    public static String getUsername(String str) {return str.split(",")[1];}


}
