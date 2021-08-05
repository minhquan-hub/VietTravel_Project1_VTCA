package Project1.Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

public class DataConnection {
    private String URL = "jdbc:mysql://localhost:3306/Stravel";
    private String USER = "root";
    private String PASS = "quan01864565731";

    public DataConnection(){

    }

    public Connection getConnection(){
        return makeConnection();
    }

    public Connection makeConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return (Connection) DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
