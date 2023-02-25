package com.nobsrecipebook.model;
public class Ingredient {
    //Fields
    private int idPrimaryKey;
    private String aisleOfIngredient;
    private int recipeIdForeignKey;
    private String nameOfIngredient;
    private String descriptionOfIngredient;
    private Double amountOfIngredientNeeded;
    private String unitMeasurementOfIngredient;

    //Getters and setters
    public int getIdPrimaryKey() {
        return idPrimaryKey;
    }

    public void setIdPrimaryKey(int idPrimaryKey) {
        this.idPrimaryKey = idPrimaryKey;
    }

    public String getAisleOfIngredient() {
        return aisleOfIngredient;
    }

    public void setAisleOfIngredient(String aisleOfIngredient) {
        this.aisleOfIngredient = aisleOfIngredient;
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

    public Double getAmountOfIngredientNeeded() {
        return amountOfIngredientNeeded;
    }

    public void setAmountOfIngredientNeeded(Double amountOfIngredientNeeded) {
        this.amountOfIngredientNeeded = amountOfIngredientNeeded;
    }

    public String getUnitMeasurementOfIngredient() {
        return unitMeasurementOfIngredient;
    }

    public void setUnitMeasurementOfIngredient(String unitMeasurementOfIngredient) {
        this.unitMeasurementOfIngredient = unitMeasurementOfIngredient;
    }
}
