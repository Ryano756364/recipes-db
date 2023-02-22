package com.ryanpodell;
import com.ryanpodell.model.Datasource;
import com.ryanpodell.model.IngredientRecipe;
import com.ryanpodell.model.Recipes;
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
        //activateAllRecipeMethod(datasource);

        //List all recipes with their respective steps in descending order
        //activateAllRecipesWithStepsMethod(datasource);

        //List all recipes with a given ingredient
        activateRecipesWithGivenIngredient("Carrots", datasource);

        // Close connection
        datasource.close();
    }


    public static void activateAllRecipeMethod(Datasource datasource){
        List<Recipes> recipes = datasource.queryRecipes(Datasource.ORDER_BY_ASC);
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
    }

    public static void activateAllRecipesWithStepsMethod(Datasource datasource){
        List<Recipes> stepsForRecipes = datasource.queryStepsForRecipes(Datasource.ORDER_BY_DESC);
        for(Recipes recipe : stepsForRecipes){
            System.out.println("\nRecipe ID: " + recipe.getId() +
                    "\nRecipe Name: " + recipe.getRecipeName() +
                    "\nStep Number: " + recipe.getStepNumber() +
                    "\nStep Description: " + recipe.getStepDesc());
        }
    }

    public static void activateRecipesWithGivenIngredient(String ingredient, Datasource datasource){
        List<IngredientRecipe> ingredientRecipe = datasource.queryRecipeBasedOnIngredient(ingredient, Datasource.ORDER_BY_ASC);
        if (ingredientRecipe == null){
            System.out.println("Couldn't find any recipes with given ingredient!");
        } else {
            for (IngredientRecipe recipe : ingredientRecipe){
                System.out.println("\nRecipe name: " + recipe.getRecipeName());
                System.out.println("Recipe likes: " + recipe.getStepNumber());
                System.out.println("Ingredient name: " + recipe.getIngredient());
            }
        }

    }

}
