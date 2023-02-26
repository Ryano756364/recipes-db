package com.nobsrecipebook.model;

import java.sql.*;

public class Datadestination {

    //Connection setup fields
    public static final String DB_NAME = "recipesDb.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\rpode\\Documents\\Coding\\recipes-db\\src\\com\\nobsrecipebook\\database\\" + DB_NAME;
    //End connection setup fields

    //Database blueprint fields
    private static final String TABLE_RECIPE = "recipe";
    public static final String COLUMN_RECIPE_ID = "id";
    public static final String COLUMN_RECIPE_IS_VEGETARIAN = "is_vegetarian";
    public static final String COLUMN_RECIPE_IS_VEGAN = "is_vegan";
    public static final String COLUMN_RECIPE_IS_GLUTEN_FREE = "is_gluten_free";
    public static final String COLUMN_RECIPE_IS_DAIRY_FREE = "is_dairy_free";
    public static final String COLUMN_RECIPE_IS_VERY_POPULAR = "is_very_popular";
    public static final String COLUMN_RECIPE_TITLE = "title";
    public static final String COLUMN_RECIPE_SOURCE_URL = "source_url";
    public static final String COLUMN_RECIPE_IMAGE_URL = "image_url";
    public static final String COLUMN_RECIPE_SUMMARY = "summary";
    public static final String COLUMN_RECIPE_INSTRUCTIONS = "instructions";

    private static final String TABLE_CUISINE = "cuisine";
    public static final String COLUMN_CUISINE_ID = "id";
    public static final String COLUMN_CUISINE_RECIPE_ID = "recipe_id";
    public static final String COLUMN_CUISINE_TYPE = "cuisine_type";

    private static final String TABLE_DISH_TYPE = "dish_type";
    public static final String COLUMN_DISH_TYPE_ID = "id";
    public static final String COLUMN_DISH_TYPE_RECIPE_ID = "recipe_id";
    public static final String COLUMN_DISH_TYPE = "dish_type";

    private static final String TABLE_DIET = "diet";
    public static final String COLUMN_DIET_ID = "id";
    public static final String COLUMN_DIET_RECIPE_ID = "recipe_id";
    public static final String COLUMN_DIET = "diet_type";

    private static final String TABLE_INGREDIENT = "ingredient";
    public static final String COLUMN_INGREDIENT_ID = "id";
    public static final String COLUMN_INGREDIENT_RECIPE_ID = "recipe_id";
    public static final String COLUMN_INGREDIENT = "ingredient";
    public static final String COLUMN_INGREDIENT_DESCRIPTION = "ingredient_description";
    public static final String COLUMN_INGREDIENT_AMOUNT = "amount";
    public static final String COLUMN_INGREDIENT_UNIT_OF_AMOUNT = "unit_of_amount";

    private static final String TABLE_INSTRUCTION = "instruction";
    public static final String COLUMN_INSTRUCTION_ID = "id";
    public static final String COLUMN_INSTRUCTION_RECIPE_ID = "recipe_id";
    public static final String COLUMN_INSTRUCTION = "instruction";
    public static final String COLUMN_INSTRUCTION_INGREDIENT_NEEDED = "ingredient_needed";
    public static final String COLUMN_INSTRUCTION_EQUIPMENT_NEEDED = "equipment_needed";

    public static final String QUERY_RECIPE = "SELECT " + COLUMN_RECIPE_ID + " FROM " + TABLE_RECIPE + " WHERE " +
            COLUMN_RECIPE_TITLE + " = ?";  //This looks for duplicate recipes based upon the recipe name

    //End database blueprint fields


    //Connection control
    private Connection conn;

    private PreparedStatement insertIntoRecipe;
    private PreparedStatement insertIntoCuisine;
    private PreparedStatement insertIntoDishType;
    private PreparedStatement insertIntoDiet;
    private PreparedStatement insertIntoIngredient;
    private PreparedStatement insertIntoInstruction;

    private PreparedStatement queryRecipe;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            //insertIntoRecipe = conn.prepareStatement(INSERT_RECIPE, Statement.RETURN_GENERATED_KEYS);
            insertIntoCuisine = conn.prepareStatement(INSERT_CUISINE);
            //insertIntoDishType = conn.prepareStatement(INSERT_DISH_TYPE);
            //insertIntoDiet = conn.prepareStatement(INSERT_DIET);
            //insertIntoIngredient = conn.prepareStatement(INSERT_INGREDIENT);
            //insertIntoInstruction = conn.prepareStatement(INSERT_INSTRUCTION);
            queryRecipe = conn.prepareStatement(QUERY_RECIPE);
            return true;
        } catch (SQLException e) {
            System.out.println("Error -> " + e);
            e.printStackTrace();
            return false;
        }
    }
    public void close() {  //Added close for extra precaution
        try {
            if(insertIntoRecipe != null){
                insertIntoRecipe.close();
            }
            if(insertIntoCuisine != null){
                insertIntoCuisine.close();
            }
            if(insertIntoDishType != null){
                insertIntoDishType.close();
            }
            if(insertIntoDiet != null){
                insertIntoDiet.close();
            }
            if(insertIntoIngredient != null){
                insertIntoIngredient.close();
            }
            if(insertIntoInstruction != null){
                insertIntoInstruction.close();
            }
            if(queryRecipe != null){
                queryRecipe.close();
            }
            if (conn != null) {
                conn.close();
                System.out.println("\nData destination connection successfully closed!");
            }
        } catch (SQLException e) {
            System.out.println("\nCouldn't close data destination connection -> " + e);
            e.printStackTrace();
        }
    }
    //End connection control


    //SQL insert statements
    public static final String INSERT_RECIPE = "INSERT INTO " + TABLE_RECIPE + " (" + COLUMN_RECIPE_ID + ", " +
            COLUMN_RECIPE_IS_VEGETARIAN + ", " + COLUMN_RECIPE_IS_VEGAN + ", " + COLUMN_RECIPE_IS_GLUTEN_FREE + ", " +
            COLUMN_RECIPE_IS_DAIRY_FREE + ", " + COLUMN_RECIPE_IS_VERY_POPULAR + ", " + COLUMN_RECIPE_TITLE + ", " +
            COLUMN_RECIPE_SOURCE_URL + ", " + COLUMN_RECIPE_IMAGE_URL + ", " + COLUMN_RECIPE_SUMMARY + ", " +
            COLUMN_RECIPE_INSTRUCTIONS + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_CUISINE = "INSERT INTO " + TABLE_CUISINE + "(" + COLUMN_CUISINE_ID + ", " +
            COLUMN_CUISINE_RECIPE_ID + ", " + COLUMN_CUISINE_TYPE + ") VALUES(?, ?, ?)";
    public static final String INSERT_DISH_TYPE = "";
    public static final String INSERT_DIET = "";
    public static final String INSERT_INGREDIENT = "";
    public static final String INSERT_INSTRUCTION = "";
    //End SQL insert statements


    //SQL insert methods
    public int insertRecipe(int id) throws SQLException {

        queryRecipe.setInt(1, id);
        ResultSet results = queryRecipe.executeQuery(); //sees if recipe already exists...
        if(results.next()){
            return results.getInt(1);  //...and if it does, return that id already on file, it's in column 1 so that's why we use that
        } else {
            // Insert recipe because it's currently not on file
            insertIntoRecipe.setInt(1, id);
            int affectedRows = insertIntoRecipe.executeUpdate();

            if(affectedRows != 1){
                throw new SQLException("Couldn't insert recipe!");
            }

            ResultSet generatedKeys = insertIntoRecipe.getGeneratedKeys();
            if(generatedKeys.next()){
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get id for new recipe");
            }
        }
    }

    public void insertCuisine(int id, int recipeId, String cuisineType){
        try {
            conn.setAutoCommit(false);

            insertIntoCuisine.setInt(1, id);
            insertIntoCuisine.setInt(2, recipeId);
            insertIntoCuisine.setString(3, cuisineType);
            int affectedRows = insertIntoCuisine.executeUpdate();
            if (affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("The cuisine insert failed");
            }
        } catch (SQLException e){
            System.out.println("Insert cuisine exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch (SQLException e2){
                System.out.println("Error on the rollback: " + e.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behavior.");
                conn.setAutoCommit(true);  //good practice to turn transaction back on immediately after in same transaction where it was turned off
            } catch (SQLException e){
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
            }
        }
    }
    //End SQL insert methods
}
