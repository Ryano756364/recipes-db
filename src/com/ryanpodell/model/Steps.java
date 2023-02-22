package com.ryanpodell.model;

public class Steps {

    //Fields
    private int id;
    private String stepName;
    private int recipesId;

    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public int getRecipesId() {
        return recipesId;
    }

    public void setRecipesId(int recipeId) {
        this.recipesId = recipeId;
    }
}
