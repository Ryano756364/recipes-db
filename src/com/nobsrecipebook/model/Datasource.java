package com.nobsrecipebook.model;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;


public class Datasource {

    //Fields
    public static final String RECIPE_DB_NAME = "recipesDb.db";  //recipe
    //public static final String DB_NAME = "jsonPracticeDb.db";  //test
    public static final String RECIPE_CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\rpode\\Documents\\" +
            "Coding\\recipes-db\\src\\com\\nobsrecipebook\\database\\" + RECIPE_DB_NAME;  //recipe
    //public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\rpode\\Documents\\" +
    //        "Coding\\recipes-db\\src\\com\\nobsrecipebook\\database\\" + DB_NAME; //test


    //Connection control
    private Connection conn;
    public boolean open() {
        try {
            //conn = DriverManager.getConnection(CONNECTION_STRING);  //delete when done with test
            conn = DriverManager.getConnection(RECIPE_CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Error -> " + e);
            e.printStackTrace();
            return false;
        }
    }
    public void close() {  //Added close for extra precaution even though try with resources is used
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


    //Program methods
    public JSONObject listAllRecipes() {
        JSONParser parser = new JSONParser();
        try {
            //Object obj = parser.parse(new FileReader("C:\\Users\\rpode\\Documents\\Coding\\recipes-db\\src\\
            // com\\nobsrecipebook\\assets\\testdata.json"));  //delete when done with test
            Object obj = parser.parse(new FileReader("C:\\Users\\rpode\\Documents\\Coding\\recipes-db\\src\\" +
                    "com\\nobsrecipebook\\assets\\1000recipes.json"));
            return (JSONObject)obj;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
