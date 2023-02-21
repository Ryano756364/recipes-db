package com.ryanpodell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\rpode\\Documents\\Coding\\recipes-db\\recipedb.db");
        } catch (SQLException e){
            System.out.println("Error -> " + e);
        }
    }
}
