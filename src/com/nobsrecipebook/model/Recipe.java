package com.nobsrecipebook.model;

public class Recipe {
    //Fields
    private int idPrimaryKey;
    private boolean isRecipeVegetarian;
    private boolean isRecipeVegan;
    private boolean isRecipeGlutenFree;
    private boolean isRecipeDairyFree;
    private boolean isRecipeVeryPopular;
    private String titleOfRecipe;
    private String sourceUrlOfRecipe;
    private String imageUrlOfRecipe;
    private String summaryOfRecipe;
    private String instructionsForRecipe;

    //Getters and setters
    public int getIdPrimaryKey() {
        return idPrimaryKey;
    }

    public void setIdPrimaryKey(int idPrimaryKey) {
        this.idPrimaryKey = idPrimaryKey;
    }

    public boolean isRecipeVegetarian() {
        return isRecipeVegetarian;
    }

    public void setRecipeVegetarian(boolean recipeVegetarian) {
        isRecipeVegetarian = recipeVegetarian;
    }

    public boolean isRecipeVegan() {
        return isRecipeVegan;
    }

    public void setRecipeVegan(boolean recipeVegan) {
        isRecipeVegan = recipeVegan;
    }

    public boolean isRecipeGlutenFree() {
        return isRecipeGlutenFree;
    }

    public void setRecipeGlutenFree(boolean recipeGlutenFree) {
        isRecipeGlutenFree = recipeGlutenFree;
    }

    public boolean isRecipeDairyFree() {
        return isRecipeDairyFree;
    }

    public void setRecipeDairyFree(boolean recipeDairyFree) {
        isRecipeDairyFree = recipeDairyFree;
    }

    public boolean isRecipeVeryPopular() {
        return isRecipeVeryPopular;
    }

    public void setRecipeVeryPopular(boolean recipeVeryPopular) {
        isRecipeVeryPopular = recipeVeryPopular;
    }

    public String getTitleOfRecipe() {
        return titleOfRecipe;
    }

    public void setTitleOfRecipe(String titleOfRecipe) {
        this.titleOfRecipe = titleOfRecipe;
    }

    public String getSourceUrlOfRecipe() {
        return sourceUrlOfRecipe;
    }

    public void setSourceUrlOfRecipe(String sourceUrlOfRecipe) {
        this.sourceUrlOfRecipe = sourceUrlOfRecipe;
    }

    public String getImageUrlOfRecipe() {
        return imageUrlOfRecipe;
    }

    public void setImageUrlOfRecipe(String imageUrlOfRecipe) {
        this.imageUrlOfRecipe = imageUrlOfRecipe;
    }

    public String getSummaryOfRecipe() {
        return summaryOfRecipe;
    }

    public void setSummaryOfRecipe(String summaryOfRecipe) {
        this.summaryOfRecipe = summaryOfRecipe;
    }

    public String getInstructionsForRecipe() {
        return instructionsForRecipe;
    }

    public void setInstructionsForRecipe(String instructionsForRecipe) {
        this.instructionsForRecipe = instructionsForRecipe;
    }
}
