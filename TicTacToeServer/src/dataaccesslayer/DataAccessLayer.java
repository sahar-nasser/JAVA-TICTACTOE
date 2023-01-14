package dataaccesslayer;

import Model.Player;

import java.sql.*;

public class DataAccessLayer {



        private static ResultSet rs;
        private static Connection con;
        private static void connect()
        {
            try {
                DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
                con= DriverManager.getConnection("jdbc:derby://localhost:1527/TicTacToe","root","root");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    public  static int addPlayer(Player player) {
        connect();
        final String INSERT_CONTACT_SQL = "INSERT INTO player" +
                "  (username, password, score) VALUES " +
                " (?, ?, ?)";

        // Step 1: Establishing a Connection
        int result=-1;

         try {
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = con.prepareStatement(INSERT_CONTACT_SQL);
            preparedStatement.setString(1, player.getUsername());
            preparedStatement.setString(2, player.getPassword());
            preparedStatement.setInt(3, player.getScore());

            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static int checkLoginCredintials(String userName , String password){
        int result = -1;
        connect();
        try {
            PreparedStatement preparedStatement = con.prepareStatement("select USERNAME , PASSWORD from PLAYER where USERNAME= ? and PASSWORD = ?");
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            ResultSet re=preparedStatement.executeQuery();
            if(re.next())result=1;
            con.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return result;
    }
    public static int getScore(String str) {
        int i=0;
        PreparedStatement ps = null;
        try {
            connect();
            ps = con.prepareStatement
                    ("select score from player where username= ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1,str);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                i= rs.getInt("score");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("from score");
        }
        return i;
    }

}
