
package helper;

public class QueryType {

    public static final int LOGIN = 0;
    public static final int SIGNUP = 1;
    public static final int GET_RECORD = 2;
    public static final int ADD_RECORD = 3;

    public static int checkQueryMsg(String str) {
        return Integer.parseInt(str.split(",")[1]);
    }

    public static String getUsername(String str) {
        return str.split(",")[2];
    }

    public static String getPassword(String str) {
        return str.split(",")[3];
    }

    public static int getQueryType(String str) {
        return Integer.valueOf(str.split(",")[1]);
    }

}
