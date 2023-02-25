package com.nobsrecipebook;
import com.nobsrecipebook.model.ContactClass;
import com.nobsrecipebook.model.Datasource;
import com.nobsrecipebook.model.Recipe;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Main {
    private static final String RECIPES_JSON_MAIN_KEY = "recipes";  //JSON main key
    private static final String RECIPE_TABLE_NAME_IN_SQL = "recipe";
    private static final String CUISINE_TABLE_NAME_IN_SQL = "cuisine";
    private static final String DISHTYPE_TABLE_NAME_IN_SQL = "dish_type";
    private static final String DIET_TABLE_NAME_IN_SQL = "diet";
    private static final String INGREDIENT_TABLE_NAME_IN_SQL = "ingredient";
    private static final String INSTRUCTION_TABLE_NAME_IN_SQL = "instruction";

    public static void main(String[] args) {

        //Test data
        //Datasource datasource = new Datasource();
        //openDataSource(datasource);
        //viewData(datasource);  //make sure to pass in 'players_data' if using this again
        //ArrayList<ContactClass> contactClassList = createClassObjectsWithData(datasource);
        //viewClassData(contactClassList);
        //closeDataSource(datasource);

        //Recipe data
        Datasource recipeDataSource = new Datasource();
        openDataSource(recipeDataSource);
        //viewData(recipeDataSource, RECIPES_JSON_MAIN_KEY);
        ArrayList<Recipe> recipeClassList = createRecipeClassObjectsWithData(recipeDataSource, RECIPES_JSON_MAIN_KEY);
        viewRecipeClassData(recipeClassList);
        closeDataSource(recipeDataSource);
    }

    public static void openDataSource(Datasource datasource){
        if (!datasource.open()) {
            System.out.println("\nCannot open datasource");
        } else {
            System.out.println("\nDatasource open!");
        }
    }

    public static void viewData(Datasource datasource, String keyName){
        JSONObject jsonObject = datasource.listAllRecipes();
        JSONArray jsonArray = (JSONArray) jsonObject.get(keyName);
        for (Object object : jsonArray) {
            System.out.println(object);
        }
    }

    //Players Data - safe to delete once complete
    public static ArrayList<ContactClass> createClassObjectsWithData(Datasource datasource){
        JSONObject jsonObject = datasource.listAllRecipes();
        JSONArray jsonArray = (JSONArray) jsonObject.get("players_data");
        ArrayList<ContactClass> listToReturn = new ArrayList<>();
        for(Object o : jsonArray){
            JSONObject jsonObject2 = new JSONObject((Map) o);
            ContactClass contactClass = new ContactClass();
            contactClass.setId((String) jsonObject2.get("ID"));
            contactClass.setFirstName((String) jsonObject2.get("First_Name"));
            contactClass.setLastName((String) jsonObject2.get("Last_Name"));
            contactClass.setDateOfBirth((String) jsonObject2.get("Date_Of_Birth"));
            contactClass.setPlaceOfBirth((String) jsonObject2.get("Place_Of_Birth"));
            contactClass.setCountry((String) jsonObject2.get("Country"));
            if (jsonObject2.get("friends") != null) {
                JSONArray testArr = (JSONArray) jsonObject2.get("friends");
                List<String> pulledFriends = new ArrayList<>();
                for (Object object : testArr) {
                    pulledFriends.add((String) object);
                }
                contactClass.setFriends(pulledFriends);
            }
            listToReturn.add(contactClass);
        }

        return listToReturn;
    }
    public static void viewClassData(ArrayList<ContactClass> contactClass){
        for(ContactClass contact : contactClass){
            System.out.println(contact.getId());
            System.out.println(contact.getFirstName());
            System.out.println(contact.getLastName());
            System.out.println(contact.getDateOfBirth());
            if (contact.getFriends() != null){
                for(String friend : contact.getFriends()){
                    System.out.println(friend);
                }
            }
        }
    }
    //End Players Data

    //Recipe -> JSON Data to Java Object Data
    public static ArrayList<Recipe> createRecipeClassObjectsWithData(Datasource datasource, String recipesMainJsonKey){
        JSONObject jsonObject = datasource.listAllRecipes();
        JSONArray jsonArray = (JSONArray) jsonObject.get(recipesMainJsonKey);
        ArrayList<Recipe> listToReturn = new ArrayList<>();
        for(Object o : jsonArray){
            JSONObject jsonObject2 = new JSONObject((Map) o);
            Recipe recipeObjectHolder = new Recipe();
            recipeObjectHolder.setIdPrimaryKey(Math.toIntExact((Long) jsonObject2.get("id")));
            recipeObjectHolder.setRecipeVegetarian((Boolean) jsonObject2.get("vegetarian"));
            recipeObjectHolder.setRecipeVegan((Boolean) jsonObject2.get("vegan"));
            recipeObjectHolder.setRecipeGlutenFree((Boolean) jsonObject2.get("glutenFree"));
            recipeObjectHolder.setRecipeDairyFree((Boolean) jsonObject2.get("dairyFree"));
            recipeObjectHolder.setRecipeVeryPopular((Boolean) jsonObject2.get("veryPopular"));
            recipeObjectHolder.setTitleOfRecipe((String) jsonObject2.get("title"));
            recipeObjectHolder.setSourceUrlOfRecipe((String) jsonObject2.get("sourceUrl"));
            recipeObjectHolder.setImageUrlOfRecipe((String) jsonObject2.get("image"));
            recipeObjectHolder.setSummaryOfRecipe((String) jsonObject2.get("summary"));
            recipeObjectHolder.setInstructionsForRecipe((String) jsonObject2.get("instructions"));
            listToReturn.add(recipeObjectHolder);
        }
        return listToReturn;
    }
    public static void viewRecipeClassData(ArrayList<Recipe> recipeObjectList){
        for(Recipe r : recipeObjectList){
            System.out.println(r.getIdPrimaryKey());
            System.out.println(r.isRecipeVegetarian());
            System.out.println(r.isRecipeVegan());
            System.out.println(r.isRecipeGlutenFree());
            System.out.println(r.isRecipeDairyFree());
            System.out.println(r.isRecipeVeryPopular());
            System.out.println(r.getTitleOfRecipe());
            System.out.println(r.getSourceUrlOfRecipe());
            System.out.println(r.getImageUrlOfRecipe());
            System.out.println(r.getSummaryOfRecipe());
            System.out.println(r.getInstructionsForRecipe());
        }
    }
    //End Recipe Data

    public static void closeDataSource(Datasource datasource){
        datasource.close();
    }
}
