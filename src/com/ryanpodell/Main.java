package com.ryanpodell;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        // Make sure to first import SQLite JDBC driver -> File -> Project Structure -> Libraries -> "+" -> "Java" -> (Then import downloaded file)
        // This setup with automatically close the connection. Otherwise, it is mandatory to close as it is done below.
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\rpode\\Documents\\Coding\\recipes-db\\recipedb.db");
             Statement statement = conn.createStatement()) {  //connection instance){
            statement.execute("CREATE TABLE IF NOT EXISTS recipes " +
                    "(recipe_name TEXT, likes INTEGER, recipe_url TEXT)"); //statement instance
//            statement.execute("INSERT INTO recipes (recipe_name, likes, recipe_url) " +
//                    "VALUES('brocolli and rice', 782, 'www.google.com')");
//            statement.execute("INSERT INTO recipes (recipe_name, likes, recipe_url) " +
//                    "VALUES('carrots and rice', 182, 'www.google.com')");
//            statement.execute("INSERT INTO recipes (recipe_name, likes, recipe_url) " +
//                    "VALUES('spinach and rice', 282, 'www.google.com')");

            //statement.execute("UPDATE recipes SET likes=100 WHERE recipe_name='spinach and rice'");

            //statement.execute("DELETE FROM recipes WHERE recipe_name='carrots and rice'");

            statement.execute("SELECT * FROM recipes");
            ResultSet results = statement.getResultSet();
            while(results.next()) {
                System.out.println(results.getString("recipe_name") + " " +
                                   results.getInt("likes") + " " +
                                   results.getString("recipe_url"));
            }
            results.close();

            statement.close();
            conn.close();
            System.out.println("\nComplete!");
        } catch (SQLException e){
            System.out.println("Error -> " + e);
        }
    }
}
