package com.nobsrecipebook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Datasource {

    //Fields

    //public static final String DB_NAME = "recipedatacomplete.db";
    public static final String DB_NAME = "jsonPracticeDb.db";

    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\rpode\\Documents\\" +
            "Coding\\recipes-db\\src\\com\\nobsrecipebook\\database" + DB_NAME;


    //Connection control
    private Connection conn;
    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Error -> " + e);
            e.printStackTrace();
            return false;
        }
    }
    public void close() {  //Added close for extra precaution
        try {
            if (conn != null) {
                conn.close();
                System.out.println("\nConnection successfully closed!");
            }
        } catch (SQLException e) {
            System.out.println("\nCouldn't close connection -> " + e);
            e.printStackTrace();
        }
    }


}


