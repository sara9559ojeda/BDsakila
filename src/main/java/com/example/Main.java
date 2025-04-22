package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResultSet = null;
        try {
          myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root","root");
          myStatement = myConnection.createStatement();

          int rowsAffected = myStatement.executeUpdate(
            "Update sakila.actor set first_name = 'PepitoR'");
            System.out.println("filas actualizadas:"+rowsAffected);
          myResultSet = myStatement.executeQuery("Select * from sakila.actor");
          while (myResultSet.next()) {
            System.out.println(myResultSet.getString("first_name") 
            +" "+myResultSet.getString("last_name") );
          }

          System.out.println("Conexion exitosa");
        } catch (Exception e) {
           System.out.println("Error Conexion");
        }


    }
}