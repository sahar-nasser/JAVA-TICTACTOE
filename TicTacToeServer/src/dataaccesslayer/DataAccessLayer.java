package dataaccesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DataAccessLayer {



        private static ResultSet rs;
        private static Connection con;
        private static void connect()
        {
            try {
                DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
                con= DriverManager.getConnection("jdbc:derby://localhost:1527/database","root","root");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
}
