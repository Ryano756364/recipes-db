package com.nobsrecipebook.model;

import java.util.ArrayList;

public class DishType {
    private int idPrimaryKey;
    private int recipeIdForeignKey;
    private ArrayList<String> dishListTypes;

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

    public ArrayList<String> getDishListTypes() {
        return dishListTypes;
    }

    public void setDishListTypes(ArrayList<String> dishListTypes) {
        this.dishListTypes = dishListTypes;
    }
}
