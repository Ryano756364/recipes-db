package com.ryanpodell.model;

//Purpose of this class is to create a blueprint if user wants to know what recipes contain a certain ingredient
public class IngredientRecipe {

    private String recipeName;
    private int stepNumber;
    private String ingredient;

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
