package com.ryanpodell;

import java.sql.*;

public class Main {
    public static final String DB_NAME = "recipedb.db";
    public static final String PK = "_id";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\rpode\\Documents\\Coding\\recipes-db\\" + DB_NAME;

    //Recipe Table
    public static final String TABLE_RECIPES = "recipes";
    public static final String COLUMN_RECIPE_NAME = "recipe_name";
    public static final String COLUMN_LIKES = "likes";
    public static final String COLUMN_PHOTO_URL = "photo_url";

    //Steps Table
    public static final String TABLE_STEPS = "steps";
    public static final String COLUMN_STEP_NAME = "step_name";
    public static final String COLUMN_RECIPE_FK_INT = "recipe";

    //Ingredients Table
    public static final String TABLE_INGREDIENTS = "ingredients";
    public static final String COLUMN_INGREDIENT_NAME = "ingredient_name";
    public static final String COLUMN_INGREDIENT_AMOUNT = "ingredient_amount";
    public static final String COLUMN_STEP_FK_INT = "step";

    public static void main(String[] args) {

        // Make sure to first import SQLite JDBC driver -> File -> Project Structure -> Libraries -> "+" -> "Java" -> (Then import downloaded file)
        // This setup with automatically close the connection. Otherwise, it is mandatory to close as it is done below.
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = conn.createStatement()) {  //connection instance){

            statement.execute("DROP TABLE IF EXISTS " + TABLE_RECIPES);
            statement.execute("DROP TABLE IF EXISTS " + TABLE_STEPS);
            statement.execute("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_RECIPES +
                    "(" + PK + " INTEGER PRIMARY KEY, " +
                    COLUMN_RECIPE_NAME + " text, " +
                    COLUMN_LIKES + " INTEGER, " +
                    COLUMN_PHOTO_URL + " text)"); //statement instance

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_STEPS +
                    "(" + PK + " INTEGER PRIMARY KEY, " +
                    COLUMN_STEP_NAME + " text, " +
                    COLUMN_RECIPE_FK_INT + " INTEGER)"); //statement instance

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_INGREDIENTS +
                    "(" + PK + " INTEGER PRIMARY KEY, " +
                    COLUMN_INGREDIENT_NAME + " text, " +
                    COLUMN_INGREDIENT_AMOUNT + " INTEGER, " +
                    COLUMN_STEP_FK_INT + " INTEGER)"); //statement instance

            //Recipe Data
            insertRecipe(statement,"broccoli and rice", 582, "www.google.com");
            insertRecipe(statement,"carrots and rice", 382, "www.google.com");
            insertRecipe(statement,"veggies and rice", 182, "www.google.com");
            insertRecipe(statement,"water and rice", 2, "www.google.com");

            //Step Data
            insertStep(statement, "boil water", 1);
            insertStep(statement, "preheat oven", 1);
            insertStep(statement, "stir dry stuff", 1);
            insertStep(statement, "stir wet stuff", 1);
            insertStep(statement, "combine together", 2);
            insertStep(statement, "eat", 2);
            insertStep(statement, "brag about your great recipe", 4);

            //Ingredient Data
            insertIngredient(statement, "Broccoli", 2, 3);
            insertIngredient(statement, "Rice", 2, 3);
            insertIngredient(statement, "Water", 3, 4);
            insertIngredient(statement, "Chocolate", 1, 3);
            insertIngredient(statement, "Beans", 5, 4);
            insertIngredient(statement, "Carrots", 1, 5);
            insertIngredient(statement, "Lemons", 2, 4);
            insertIngredient(statement, "Limes", 12, 4);


            //statement.execute("UPDATE recipes SET likes=100 WHERE recipe_name='spinach and rice'");

            //statement.execute("DELETE FROM recipes WHERE recipe_name='carrots and rice'");

            //statement.execute("SELECT * FROM recipes");
            //ResultSet results = statement.getResultSet();
//            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_RECIPES); //cleaner way to code this versus two lines above
//            while (results.next()) {
//                System.out.println(results.getString(COLUMN_RECIPE_NAME) + " " +
//                        results.getInt(COLUMN_LIKES) + " " +
//                        results.getString(COLUMN_PHOTO_URL));
//            }
//            results.close();

            statement.close();
            conn.close();

            System.out.println("\nComplete!");
        } catch (SQLException e){
            System.out.println("Error -> " + e);
            e.printStackTrace();
        }
    }


    //Helper methods
    private static void insertRecipe(Statement statement, String recipeName, int likes, String photoUrl) throws SQLException{
        statement.execute("INSERT INTO " + TABLE_RECIPES +
                " (" + COLUMN_RECIPE_NAME + ", " +
                COLUMN_LIKES + ", " +
                COLUMN_PHOTO_URL +
                " ) " +
                "VALUES('" + recipeName + "', " + likes + ", '" + photoUrl + "')");
    }

    private static void insertStep(Statement statement, String stepName, int recipeForeignKey) throws SQLException{
        statement.execute("INSERT INTO " + TABLE_STEPS +
                " (" + COLUMN_STEP_NAME + ", " +
                COLUMN_RECIPE_FK_INT + " ) " +
                "VALUES('" + stepName + "', '" + recipeForeignKey + "')");
    }

    private static void insertIngredient(Statement statement, String ingredientName, int ingredientAmount, int stepForeignKey) throws SQLException{
        statement.execute("INSERT INTO " + TABLE_INGREDIENTS +
                " (" + COLUMN_INGREDIENT_NAME + ", " +
                COLUMN_INGREDIENT_AMOUNT + ", " +
                COLUMN_STEP_FK_INT +
                " ) " +
                "VALUES('" + ingredientName + "', " + ingredientAmount + ", '" + stepForeignKey + "')");
    }
}
