package com.ryanpodell;

import com.ryanpodell.model.Datasource;
import com.ryanpodell.model.Recipes;

import java.sql.*;
import java.util.List;

public class Main {



    public static void main(String[] args) {

        //Remember to make sure to import the JDBC SQL driver to file
        Datasource datasource = new Datasource();
        if (!datasource.open()) {
            System.out.println("\nCannot open datasource");
            return;
        } else {
            System.out.println("\nDatasource open!");
        }


        //List all recipes
        List<Recipes> recipes = datasource.queryRecipes();
        if(recipes == null){
            System.out.println("No recipes!");
        }

        assert recipes != null;
        for(Recipes recipe : recipes){
            System.out.println("\nID = " + recipe.getId() +
                    "\nRecipe Name = " + recipe.getRecipeName() +
                    "\nLikes = " + recipe.getLikes() +
                    "\nRecipe Image = " + recipe.getPhotoUrl());
        }



        // Close connection
        datasource.close();
    }
}





        /*




        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = conn.createStatement()) {

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



         */