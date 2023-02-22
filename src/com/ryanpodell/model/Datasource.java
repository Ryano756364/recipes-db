package com.ryanpodell.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    //Connection setup
    public static final String DB_NAME = "recipedb.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\rpode\\Documents\\Coding\\recipes-db\\" + DB_NAME;

    //Primary Key
    public static final String PK = "_id";

    //Recipe Table
    public static final String TABLE_RECIPES = "recipes";
    public static final String COLUMN_RECIPES_ID = "_id";  //good to use multiple ID columns, so we don't impact other tables
    public static final String COLUMN_RECIPES_NAME = "recipe_name";
    public static final String COLUMN_LIKES = "likes";
    public static final String COLUMN_PHOTO_URL = "photo_url";

    //Steps Table
    public static final String TABLE_STEPS = "steps";
    public static final String COLUMN_STEPS_ID = "_id";
    public static final String COLUMN_STEP_NAME = "step_name";
    public static final String COLUMN_RECIPES_FK_INT = "recipe";

    //Ingredients Table
    public static final String TABLE_INGREDIENTS = "ingredients";
    public static final String COLUMN_INGREDIENTS_ID = "_id";
    public static final String COLUMN_INGREDIENT_NAME = "ingredient_name";
    public static final String COLUMN_INGREDIENT_AMOUNT = "ingredient_amount";
    public static final String COLUMN_STEP_FK_INT = "step";


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


    //List all recipes
    public List<Recipes> queryRecipes() {

        //Try with resources automatically closes statement and results; no finally needed as well below
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_RECIPES)){

            List<Recipes> recipes = new ArrayList<>();
            while(results.next()) {
                Recipes recipe = new Recipes();
                recipe.setId(results.getInt(COLUMN_RECIPES_ID));
                recipe.setRecipeName(results.getString(COLUMN_RECIPES_NAME));
                recipe.setLikes(results.getInt(COLUMN_LIKES));
                recipe.setPhotoUrl(results.getString(COLUMN_PHOTO_URL));
                recipes.add(recipe);
            }

            return recipes;

        } catch (SQLException e) {
            System.out.println("Query for recipes failed -> " + e);
            e.printStackTrace();
            return null;
        }
    }

}
