package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.utils.DatabaseConnection;

public class Select {
     public static void main(String[] args) throws SQLException{

        try(Connection myConnection = DatabaseConnection.getInstance();
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
