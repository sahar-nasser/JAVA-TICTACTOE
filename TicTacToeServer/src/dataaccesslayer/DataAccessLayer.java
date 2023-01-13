package dataaccesslayer;

import helper.QueryType;

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
        try (Connection connection = DriverManager
                .getConnection("jdbc:derby://localhost:1527/TicTacToe", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTACT_SQL)) {
            preparedStatement.setString(1, player.getUsername());
            preparedStatement.setString(2, player.getPassword());
            preparedStatement.setInt(3, player.getScore());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            // print SQL exception information
            System.out.println(e.getMessage()   );
        }
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}
