package ngomanagement;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetConnection {
    public static Connection getConnection()
    {
        Connection connection=null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/ngoapplication", "root", "admin");
        }catch(Exception e){
            System.out.println("Connection Failed");
        }
        return connection;
    }

}
