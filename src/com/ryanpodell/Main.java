package com.ryanpodell;

import javax.xml.transform.Result;
import java.sql.*;

public class Main {
    public static final String DB_NAME = "recipedb.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\rpode\\Documents\\Coding\\recipes-db\\" + DB_NAME;
    public static final String TABLE_RECIPES = "recipes";
    public static final String COLUMN_RECIPE_NAME = "recipe_name";
    public static final String COLUMN_LIKES = "likes";
    public static final String COLUMN_RECIPE_URL = "recipe_url";

    public static void main(String[] args) {

        // Make sure to first import SQLite JDBC driver -> File -> Project Structure -> Libraries -> "+" -> "Java" -> (Then import downloaded file)
        // This setup with automatically close the connection. Otherwise, it is mandatory to close as it is done below.
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = conn.createStatement()) {  //connection instance){

            statement.execute("DROP TABLE IF EXISTS " + TABLE_RECIPES);

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_RECIPES +
                    "(" + COLUMN_RECIPE_NAME + " text, " +
                          COLUMN_LIKES + " INTEGER, " +
                          COLUMN_RECIPE_URL + " text)"); //statement instance

            insertRecipe(statement,"broccoli and rice", 582, "www.google.com");
            insertRecipe(statement,"carrots and rice", 782, "www.google.com");
            insertRecipe(statement,"veggies and rice", 182, "www.google.com");
            insertRecipe(statement,"water and rice", 2, "www.google.com");

            //statement.execute("UPDATE recipes SET likes=100 WHERE recipe_name='spinach and rice'");

            //statement.execute("DELETE FROM recipes WHERE recipe_name='carrots and rice'");

            //statement.execute("SELECT * FROM recipes");
            //ResultSet results = statement.getResultSet();
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_RECIPES); //cleaner way to code this versus two lines above
            while (results.next()) {
                System.out.println(results.getString(COLUMN_RECIPE_NAME) + " " +
                        results.getInt(COLUMN_LIKES) + " " +
                        results.getString(COLUMN_RECIPE_URL));
            }
            results.close();

            statement.close();
            conn.close();

            System.out.println("\nComplete!");
        } catch (SQLException e){
            System.out.println("Error -> " + e);
        }
    }

    private static void insertRecipe(Statement statement, String recipeName, int likes, String recipeUrl) throws SQLException{
        statement.execute("INSERT INTO " + TABLE_RECIPES +
                " (" + COLUMN_RECIPE_NAME + ", " +
                COLUMN_LIKES + ", " +
                COLUMN_RECIPE_URL +
                " ) " +
                "VALUES('" + recipeName + "', " + likes + ", '" + recipeUrl + "')");
    }
}
