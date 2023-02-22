package com.ryanpodell.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    //Connection setup
    public static final String DB_NAME = "recipedb.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\rpode\\Documents\\Coding\\recipes-db\\" + DB_NAME;

    //Recipe Table
    public static final String TABLE_RECIPES = "recipes";
    public static final String COLUMN_RECIPES_ID = "_id";  //good to use multiple ID columns, so we don't impact other tables
    public static final String COLUMN_RECIPES_NAME = "recipe_name";
    public static final String COLUMN_LIKES = "likes";
    public static final String COLUMN_PHOTO_URL = "photo_url";
    public static final int INDEX_RECIPE_ID = 1; //more efficient to use int with getters and setters
    public static final int INDEX_RECIPE_NAME = 2;
    public static final int INDEX_RECIPE_LIKES = 3;
    public static final int INDEX_RECIPE_PHOTO_URL = 4;

    //Steps Table
    public static final String TABLE_STEPS = "steps";
    public static final String COLUMN_STEPS_ID = "_id";
    public static final String COLUMN_STEP_NAME = "step_name";
    public static final String COLUMN_RECIPES_FK_INT = "recipe";
    public static final int INDEX_STEPS_ID = 1;
    public static final int INDEX_STEPS_NAME = 2;
    public static final int INDEX_RECIPES_FK_INT = 3;

    //Ingredients Table
    public static final String TABLE_INGREDIENTS = "ingredients";
    public static final String COLUMN_INGREDIENTS_ID = "_id";
    public static final String COLUMN_INGREDIENT_NAME = "ingredient_name";
    public static final String COLUMN_INGREDIENT_AMOUNT = "ingredient_amount";
    public static final String COLUMN_STEP_FK_INT = "step";
    public static final int INDEX_INGREDIENTS_ID = 1;
    public static final int INDEX_INGREDIENTS_NAME = 2;
    public static final int INDEX_INGREDIENTS_AMOUNT = 3;
    public static final int INDEX_INGREDIENTS_FK_INT = 4;

    // General query strings
    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    //Query specific string (MAKE SURE TO APPEND INGREDIENT SEARCHED FOR IN METHOD)
    public static final String QUERY_RECIPE_WITH_GIVEN_INGREDIENT =
            "SELECT " + TABLE_INGREDIENTS + "." + COLUMN_INGREDIENT_NAME + ", " + TABLE_STEPS + "." + COLUMN_STEP_NAME +
                    ", " + TABLE_RECIPES + "." + COLUMN_RECIPES_NAME + ", " + TABLE_RECIPES + "." + COLUMN_LIKES + ", " +
                    TABLE_RECIPES + "." + COLUMN_PHOTO_URL +
                    " FROM " + TABLE_INGREDIENTS +
                    " INNER JOIN " + TABLE_STEPS +
                    " ON " + TABLE_INGREDIENTS + "." + COLUMN_STEP_FK_INT + " = " + TABLE_STEPS + "." + COLUMN_STEPS_ID +
                    " INNER JOIN " + TABLE_RECIPES +
                    " ON " + TABLE_RECIPES + "." + COLUMN_RECIPES_ID + " = " + TABLE_STEPS + "." + COLUMN_RECIPES_FK_INT +
                    " WHERE " + TABLE_INGREDIENTS + "." + COLUMN_INGREDIENT_NAME + " = \"";

    public static final String  QUERY_RECIPE_WITH_GIVEN_INGREDIENT_SORT =
            "ORDER BY " + TABLE_RECIPES + "." + COLUMN_RECIPES_NAME + " COLLATE NOCASE ";


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
    public List<Recipes> queryRecipes(int sortOrder) {

        //SQL statement helper
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_RECIPES);
        if (sortOrder != ORDER_BY_NONE){
            sb.append(" ORDER BY ");
            sb.append(COLUMN_RECIPES_NAME);
            sb.append(" COLLATE NOCASE ");
            if(sortOrder == ORDER_BY_DESC){
                sb.append("DESC");
            } else { //used this default behavior versus throwing an exception
                sb.append("ASC");
            }
        }

        //Try with resources automatically closes statement and results; no finally needed as well below
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())){

            List<Recipes> recipes = new ArrayList<>();
            while(results.next()) {
                Recipes recipe = new Recipes();
                recipe.setId(results.getInt(INDEX_RECIPE_ID));
                recipe.setRecipeName(results.getString(INDEX_RECIPE_NAME));
                recipe.setLikes(results.getInt(INDEX_RECIPE_LIKES));
                recipe.setPhotoUrl(results.getString(INDEX_RECIPE_PHOTO_URL));
                recipes.add(recipe);
            }

            return recipes;

        } catch (SQLException e) {
            System.out.println("Query for recipes failed -> " + e);
            e.printStackTrace();
            return null;
        }
    }

    //List all recipes with their respective steps with decision on how to sort
    //Could also put many of those appends into a string constant above to avoid repetition if more queries are done
    public List<Recipes> queryStepsForRecipes(int sortOrder){
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_RECIPES);
        sb.append(" INNER JOIN ");
        sb.append(TABLE_STEPS);
        sb.append(" ON ");
        sb.append(TABLE_RECIPES);
        sb.append(".");
        sb.append(COLUMN_RECIPES_ID);
        sb.append(" = ");
        sb.append(TABLE_STEPS);
        sb.append(".");
        sb.append(COLUMN_RECIPES_FK_INT);

        if(sortOrder != ORDER_BY_NONE){
            sb.append(" ORDER BY ");
            sb.append(COLUMN_RECIPES_NAME);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC){
                sb.append("DESC;");
            } else {
                sb.append("ASC;");
            }

        }

        //System.out.println("SQL STATEMENT... " + sb.toString()); //good measure for testing purposes

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())){


            List<Recipes> recipes = new ArrayList<>();
            while(results.next()){
                Recipes recipe = new Recipes();
                recipe.setId(results.getInt(INDEX_RECIPE_ID));
                recipe.setRecipeName(results.getString(INDEX_RECIPE_NAME));
                recipe.setStepNumber(results.getInt(5));
                recipe.setStepDesc(results.getString(INDEX_STEPS_NAME));
                recipes.add(recipe);
            }

            return recipes;


        } catch (SQLException e){
            System.out.println("Query failed -> " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    //Method to query list of recipes with particular ingredient
    public List<IngredientRecipe> queryRecipeBasedOnIngredient(String ingredient, int sortOrder) {
        StringBuilder sb = new StringBuilder(QUERY_RECIPE_WITH_GIVEN_INGREDIENT);
        sb.append(ingredient);
        sb.append("\"");

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_RECIPE_WITH_GIVEN_INGREDIENT_SORT);
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        //System.out.println("SQL STATEMENT: " + sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<IngredientRecipe> ingredientRecipe = new ArrayList<>();

            while (results.next()) {
                IngredientRecipe holder = new IngredientRecipe();
                holder.setRecipeName(results.getString(3));
                holder.setStepNumber(results.getInt(4));
                holder.setIngredient(results.getString(1));
                ingredientRecipe.add(holder);
            }
            return ingredientRecipe;
        } catch (SQLException e) {
            System.out.println("Error in fetching data -> " + e);
            return null;
        }

    }

}
