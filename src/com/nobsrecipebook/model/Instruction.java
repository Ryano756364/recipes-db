package com.nobsrecipebook.model;

import java.util.ArrayList;

public class Instruction {
    private int id;
    private int recipeIdForeignKey;
    private int stepNumber;
    private String descriptionOfInstructionStep;
    private ArrayList<String> listOfIngredientsNeeded;
    private ArrayList<String> listOfEquipmentNeeded;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescriptionOfInstructionStep() {
        return descriptionOfInstructionStep;
    }

    public void setDescriptionOfInstructionStep(String descriptionOfInstructionStep) {
        this.descriptionOfInstructionStep = descriptionOfInstructionStep;
    }

    public ArrayList<String> getListOfIngredientsNeeded() {
        return listOfIngredientsNeeded;
    }

    public void setListOfIngredientsNeeded(ArrayList<String> listOfIngredientsNeeded) {
        this.listOfIngredientsNeeded = listOfIngredientsNeeded;
    }

    public ArrayList<String> getListOfEquipmentNeeded() {
        return listOfEquipmentNeeded;
    }

    public void setListOfEquipmentNeeded(ArrayList<String> listOfEquipmentNeeded) {
        this.listOfEquipmentNeeded = listOfEquipmentNeeded;
    }
}
