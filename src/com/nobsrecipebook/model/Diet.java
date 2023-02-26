package com.nobsrecipebook.model;
import java.util.ArrayList;

public class Diet {
    //Fields
    private int idPrimaryKey;
    private int recipeIdForeignKey;
    private ArrayList<String> dietListTypes;

    //Getters and setters
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

    public String getDietListTypesAsString() {
        StringBuilder stringToReturn = new StringBuilder();

        for(String t : dietListTypes){
            if (t != null) {
                stringToReturn.append(t).append(", ");
            }
        }
        stringToReturn.setLength(stringToReturn.length()-2);
        return stringToReturn.toString();
    }

    public void setDietListTypes(ArrayList<String> dietListTypes) {
        this.dietListTypes = dietListTypes;
    }
}
