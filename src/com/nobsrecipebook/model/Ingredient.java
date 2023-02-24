package com.nobsrecipebook.model;

public class Ingredient {

    private int idPrimaryKey;
    private int recipeIdForeignKey;
    private String nameOfIngredient;
    private String descriptionOfIngredient;
    private int amountOfIngredientNeeded;
    private String unitMeasurementOfIngredient;

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

    public String getNameOfIngredient() {
        return nameOfIngredient;
    }

    public void setNameOfIngredient(String nameOfIngredient) {
        this.nameOfIngredient = nameOfIngredient;
    }

    public String getDescriptionOfIngredient() {
        return descriptionOfIngredient;
    }

    public void setDescriptionOfIngredient(String descriptionOfIngredient) {
        this.descriptionOfIngredient = descriptionOfIngredient;
    }

    public int getAmountOfIngredientNeeded() {
        return amountOfIngredientNeeded;
    }

    public void setAmountOfIngredientNeeded(int amountOfIngredientNeeded) {
        this.amountOfIngredientNeeded = amountOfIngredientNeeded;
    }

    public String getUnitMeasurementOfIngredient() {
        return unitMeasurementOfIngredient;
    }

    public void setUnitMeasurementOfIngredient(String unitMeasurementOfIngredient) {
        this.unitMeasurementOfIngredient = unitMeasurementOfIngredient;
    }
}
