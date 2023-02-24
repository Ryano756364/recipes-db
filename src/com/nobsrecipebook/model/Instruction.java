package com.nobsrecipebook.model;
import java.util.ArrayList;

public class Instruction {
    //Fields
    private int idPrimaryKey;
    private int recipeIdForeignKey;
    private int stepNumber;
    private String descriptionOfStep;
    private ArrayList<String> ingredientsNeeded;
    private ArrayList<String> equipmentNeeded;

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

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getDescriptionOfStep() {
        return descriptionOfStep;
    }

    public void setDescriptionOfStep(String descriptionOfStep) {
        this.descriptionOfStep = descriptionOfStep;
    }

    public ArrayList<String> getIngredientsNeeded() {
        return ingredientsNeeded;
    }

    public void setIngredientsNeeded(ArrayList<String> ingredientsNeeded) {
        this.ingredientsNeeded = ingredientsNeeded;
    }

    public ArrayList<String> getEquipmentNeeded() {
        return equipmentNeeded;
    }

    public void setEquipmentNeeded(ArrayList<String> equipmentNeeded) {
        this.equipmentNeeded = equipmentNeeded;
    }
}
