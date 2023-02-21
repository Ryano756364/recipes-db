package com.ryanpodell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

        // Make sure to first import SQLite JDBC driver -> File -> Project Structure -> Libraries -> "+" -> "Java" -> (Then import downloaded file)
        // This setup with automatically close the connection. Otherwise, it is mandatory to close as it is done below.
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\rpode\\Documents\\Coding\\recipes-db\\recipedb.db");
             Statement statement = conn.createStatement()) {  //connection instance){
            statement.execute("CREATE TABLE recipes (recipe_name TEXT, likes INTEGER, recipe_url TEXT)"); //statement instance

            statement.close();
            conn.close();
        } catch (SQLException e){
            System.out.println("Error -> " + e);
        }
    }
}
