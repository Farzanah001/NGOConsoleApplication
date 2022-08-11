package ngomanagement;

import java.sql.*;


public class CreateDatabaseNGO{
    public static void main(String[] args) {
        String DB_URL="jdbc:mysql://localhost/";
        final String user="root";
        final String pass="admin";
        try {
            Connection con=DriverManager.getConnection(DB_URL,user,pass);
            Statement st=con.createStatement();
            String sqlQry="create database ngoapplication";
            st.executeUpdate(sqlQry);
            System.out.println("Database Created Successfully.");
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }

}
