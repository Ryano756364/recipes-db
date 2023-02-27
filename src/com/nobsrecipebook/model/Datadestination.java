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
    public static final String COLUMN_INSTRUCTION_STEP_NUMBER = "step_number";
    public static final String COLUMN_INSTRUCTION = "instruction";
    public static final String COLUMN_INSTRUCTION_INGREDIENT_NEEDED = "ingredient_needed";
    public static final String COLUMN_INSTRUCTION_EQUIPMENT_NEEDED = "equipment_needed";
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
            insertIntoRecipe = conn.prepareStatement(INSERT_RECIPE);  //add in 'Statement.RETURN_GENERATED_KEYS' if parameter needed to return PK
            insertIntoCuisine = conn.prepareStatement(INSERT_CUISINE);
            insertIntoDishType = conn.prepareStatement(INSERT_DISH_TYPE);
            insertIntoDiet = conn.prepareStatement(INSERT_DIET);
            insertIntoIngredient = conn.prepareStatement(INSERT_INGREDIENT);
            insertIntoInstruction = conn.prepareStatement(INSERT_INSTRUCTION);
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
                System.out.println("Data destination closed!");
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
            COLUMN_RECIPE_INSTRUCTIONS + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_CUISINE = "INSERT INTO " + TABLE_CUISINE + "(" + COLUMN_CUISINE_ID + ", " +
            COLUMN_CUISINE_RECIPE_ID + ", " + COLUMN_CUISINE_TYPE + ") VALUES(?, ?, ?)";
    public static final String INSERT_DISH_TYPE = "INSERT INTO " + TABLE_DISH_TYPE + "(" + COLUMN_DISH_TYPE_ID + "," +
            COLUMN_DISH_TYPE_RECIPE_ID + ", " + COLUMN_DISH_TYPE + ") VALUES(?, ?, ?)";
    public static final String INSERT_DIET = "INSERT INTO " + TABLE_DIET + "(" + COLUMN_DIET_ID + ", " +
            COLUMN_DIET_RECIPE_ID + ", " + COLUMN_DIET + ") VALUES(?, ?, ?)";
    public static final String INSERT_INGREDIENT = "INSERT INTO " + TABLE_INGREDIENT + "(" + COLUMN_INGREDIENT_ID + ", " +
            COLUMN_INGREDIENT_RECIPE_ID + ", " + COLUMN_INGREDIENT + ", " + COLUMN_INGREDIENT_DESCRIPTION + ", " +
            COLUMN_INGREDIENT_AMOUNT + ", " + COLUMN_INGREDIENT_UNIT_OF_AMOUNT + ") VALUES(?, ?, ?, ?, ?, ?)";
    public static final String INSERT_INSTRUCTION = "INSERT INTO " + TABLE_INSTRUCTION + "(" + COLUMN_INSTRUCTION_ID + ", " +
            COLUMN_INSTRUCTION_RECIPE_ID + ", " + COLUMN_INSTRUCTION_STEP_NUMBER + ", " + COLUMN_INSTRUCTION + ", " +
            COLUMN_INSTRUCTION_INGREDIENT_NEEDED + ", " + COLUMN_INSTRUCTION_EQUIPMENT_NEEDED + ") " +
            "VALUES(?, ?, ?, ?, ?, ?)";
    //End SQL insert statements


    //SQL query statements
    public static final String QUERY_RECIPE = "SELECT " + COLUMN_RECIPE_ID + " FROM " + TABLE_RECIPE + " WHERE " +
            COLUMN_RECIPE_TITLE + " = ?";  //This looks for duplicate recipes based upon the recipe name
    //End SQL query statements


    //SQL insert methods
    public void insertRecipe(int id, boolean isVegetarian, boolean isVegan, boolean isGlutenFree, boolean isDairyFree,
                            boolean isVeryPopular, String title, String source_url, String image_url, String summary,
                            String instructions) {
        try {
            conn.setAutoCommit(false);

            insertIntoRecipe.setInt(1, id);
            insertIntoRecipe.setBoolean(2, isVegetarian);  //Automatically sends as BIT or BOOLEAN.(very cool).
            insertIntoRecipe.setBoolean(3, isVegan);
            insertIntoRecipe.setBoolean(4, isGlutenFree);
            insertIntoRecipe.setBoolean(5,isDairyFree);
            insertIntoRecipe.setBoolean(6, isVeryPopular);
            insertIntoRecipe.setString(7, title);
            insertIntoRecipe.setString(8, source_url);
            insertIntoRecipe.setString(9, image_url);
            insertIntoRecipe.setString(10, summary);
            insertIntoRecipe.setString(11, instructions);

            int affectedRows = insertIntoRecipe.executeUpdate();
            if (affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("The recipe insert failed");
            }
        } catch (SQLException e){
            System.out.println("Insert recipe exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch (SQLException e2){
                System.out.println("Error on the rollback: " + e.getMessage());
            }
        } finally {
            try {
                //System.out.println("Resetting default commit behavior.");  //Use for testing purposes
                conn.setAutoCommit(true);  //good practice to turn transaction back on immediately in same transaction where it was turned off
            } catch (SQLException e){
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
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
                //System.out.println("Resetting default commit behavior.");  //Use for testing purposes
                conn.setAutoCommit(true);  //good practice to turn transaction back on immediately after in same transaction where it was turned off
            } catch (SQLException e){
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
            }
        }
    }

    public void insertDishType(int id, int recipeId, String dishTypeListAsString){
        try {
            conn.setAutoCommit(false);

            insertIntoDishType.setInt(1, id);
            insertIntoDishType.setInt(2, recipeId);
            insertIntoDishType.setString(3, dishTypeListAsString);
            int affectedRows = insertIntoDishType.executeUpdate();
            if (affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("The dish type insert failed");
            }
        } catch (SQLException e){
            System.out.println("Insert dish type exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch (SQLException e2){
                System.out.println("Error on the rollback: " + e.getMessage());
            }
        } finally {
            try {
                //System.out.println("Resetting default commit behavior.");  //Use for testing purposes
                conn.setAutoCommit(true);  //good practice to turn transaction back on immediately after in same transaction where it was turned off
            } catch (SQLException e){
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
            }
        }
    }

    public void insertDiet(int id, int recipeId, String dietListAsString){
        try {
            conn.setAutoCommit(false);

            insertIntoDiet.setInt(1, id);
            insertIntoDiet.setInt(2, recipeId);
            insertIntoDiet.setString(3, dietListAsString);
            int affectedRows = insertIntoDiet.executeUpdate();
            if (affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("The diet insert failed");
            }
        } catch (SQLException e){
            System.out.println("Insert diet exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch (SQLException e2){
                System.out.println("Error on the rollback: " + e.getMessage());
            }
        } finally {
            try {
                //System.out.println("Resetting default commit behavior.");  //Use for testing purposes
                conn.setAutoCommit(true);  //good practice to turn transaction back on immediately after in same transaction where it was turned off
            } catch (SQLException e){
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
            }
        }
    }

    public void insertIngredient(int id, int recipeId, String ingredientName, String ingredientDescription, Double amount,
                                 String unitOfAmount){
        try {
            conn.setAutoCommit(false);

            insertIntoIngredient.setInt(1, id);
            insertIntoIngredient.setInt(2, recipeId);
            insertIntoIngredient.setString(3, ingredientName);
            insertIntoIngredient.setString(4, ingredientDescription);
            insertIntoIngredient.setLong(5, amount.longValue());
            insertIntoIngredient.setString(6, unitOfAmount);
            int affectedRows = insertIntoIngredient.executeUpdate();
            if (affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("The ingredient insert failed");
            }
        } catch (SQLException e){
            System.out.println("Insert ingredient exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch (SQLException e2){
                System.out.println("Error on the rollback: " + e.getMessage());
            }
        } finally {
            try {
                //System.out.println("Resetting default commit behavior.");  //Use for testing purposes
                conn.setAutoCommit(true);  //good practice to turn transaction back on immediately after in same transaction where it was turned off
            } catch (SQLException e){
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
            }
        }
    }

    public void insertInstruction(int id, int recipeId, int stepNumber, String instruction,
                                  String ingredientNeededListAsString, String equipmentNeededListAsString){
        try {
            conn.setAutoCommit(false);

            insertIntoInstruction.setInt(1, id);
            insertIntoInstruction.setInt(2, recipeId);
            insertIntoInstruction.setInt(3, stepNumber);
            insertIntoInstruction.setString(4, instruction);
            insertIntoInstruction.setString(5, ingredientNeededListAsString);
            insertIntoInstruction.setString(6, equipmentNeededListAsString);

            int affectedRows = insertIntoInstruction.executeUpdate();
            if (affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("The instruction insert failed");
            }
        } catch (SQLException e){
            System.out.println("Insert instruction exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch (SQLException e2){
                System.out.println("Error on the rollback: " + e.getMessage());
            }
        } finally {
            try {
                //System.out.println("Resetting default commit behavior.");  //Use for testing purposes
                conn.setAutoCommit(true);  //good practice to turn transaction back on immediately after in same transaction where it was turned off
            } catch (SQLException e){
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
            }
        }
    }
    //End SQL insert methods
}
