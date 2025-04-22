package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {
     public static void main(String[] args) throws SQLException{
        String url = "jdbc:mysql://localhost:3306";
        String user = "root";
        String pass = "root";

        try(Connection myConnection = DriverManager.getConnection(url, user,pass);
            Statement myStatement = myConnection.createStatement();
            ResultSet myResultSet = myStatement.executeQuery("Select * from sakila.actor limit 5")
        )  {
        
            while (myResultSet.next()) {
                System.out.println(myResultSet.getString("first_name")
                +" "+ myResultSet.getString("last_name"));
            }
            
          System.out.println("Conexion exitosa");
        } catch (Exception e) {
           System.out.println("Error Conexion");
        }
    }
}
