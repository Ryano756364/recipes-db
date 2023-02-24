package com.nobsrecipebook.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Recipe {
    private int id;
    private boolean isRecipeVegetarian;
    private boolean isRecipeVegan;
    private boolean isRecipeGluentFree;
    private boolean isRecipeDairyFree;
    private boolean isRecipeVeryPopular;
    private String titleOfRecipe;
    private String sourceUrlOfRecipe;
    private String imageUrlOfRecipe;
    private String summaryOfRecipe;
    private String instructionsForRecipe;
    private ArrayList<String> cuisineTypeOfRecipe;
    private ArrayList<String> dishTypesOfRecipe;
    private ArrayList<String> dietsOfRecipe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isRecipeGluentFree() {
        return isRecipeGluentFree;
    }

    public void setRecipeGluentFree(boolean recipeGluentFree) {
        isRecipeGluentFree = recipeGluentFree;
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

    public ArrayList<String> getCuisineTypeOfRecipe() {
        return cuisineTypeOfRecipe;
    }

    public void setCuisineTypeOfRecipe(ArrayList<String> cuisineTypeOfRecipe) {
        this.cuisineTypeOfRecipe = cuisineTypeOfRecipe;
    }

    public ArrayList<String> getDishTypesOfRecipe() {
        return dishTypesOfRecipe;
    }

    public void setDishTypesOfRecipe(ArrayList<String> dishTypesOfRecipe) {
        this.dishTypesOfRecipe = dishTypesOfRecipe;
    }

    public ArrayList<String> getDietsOfRecipe() {
        return dietsOfRecipe;
    }

    public void setDietsOfRecipe(ArrayList<String> dietsOfRecipe) {
        this.dietsOfRecipe = dietsOfRecipe;
    }
}
