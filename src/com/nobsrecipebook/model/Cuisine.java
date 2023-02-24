package com.nobsrecipebook.model;

import java.util.ArrayList;

public class Cuisine {
    private int idPrimaryKey;
    private int recipeIdForeignKey;
    private ArrayList<String> cuisineListTypes;

    public int getIdPrimaryKey() {
        return idPrimaryKey;
    }

    public void setIdPrimaryKey(int idPrimaryKey) {
        this.idPrimaryKey = idPrimaryKey;
    }

    public int getRecipeIdForeignKey() {
        return recipeIdForeignKey;
    }

    public void setRecipeIdForeignKey(int recipeIdForeignKey) {
        this.recipeIdForeignKey = recipeIdForeignKey;
    }

    public ArrayList<String> getCuisineListTypes() {
        return cuisineListTypes;
    }

    public void setCuisineListTypes(ArrayList<String> cuisineListTypes) {
        this.cuisineListTypes = cuisineListTypes;
    }
}
