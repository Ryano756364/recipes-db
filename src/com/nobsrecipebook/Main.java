package com.nobsrecipebook;
import com.nobsrecipebook.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Main {
    //Level 1 Keys  -- naming const to avoid silly mistakes such as 'recipe' versus 'recipes' in JSON data
    private static final String JSON_RECIPE_MAIN_KEY = "recipes";

    //Level 2 Keys
    private static final String JSON_CUISINE_MAIN_KEY = "cuisines";
    private static final String JSON_DISH_TYPE_MAIN_KEY = "dishTypes";
    private static final String JSON_DIET_MAIN_KEY = "diets";
    private static final String JSON_INGREDIENT_MAIN_KEY = "extendedIngredients";
    private static final String JSON_INSTRUCTION_MAIN_KEY = "analyzedInstructions";

    //Level 3 Keys
    private static final String JSON_INGREDIENT_ID = "id";
    private static final String JSON_INGREDIENT_NAME = "name";
    private static final String JSON_INGREDIENT_DESCRIPTION = "nameClean";
    private static final String JSON_INGREDIENT_AMOUNT = "amount";
    private static final String JSON_INGREDIENT_UNIT = "unit";
    private static final String JSON_INSTRUCTION_STEP_ARR = "steps";

    //Level 4 Keys
    private static final String JSON_INSTRUCTION_STEP_NUMBER = "number";
    private static final String JSON_INSTRUCTION_STEP_DESC = "step";
    private static final String JSON_INSTRUCTION_STEP_INGREDIENT_ARR = "ingredients";
    private static final String JSON_INSTRUCTION_STEP_EQUIPMENT_ARR = "equipment";

    //Level 5 Keys
    private static final String JSON_INSTRUCTION_STEP_INGREDIENT_NAME = "name";
    private static final String JSON_INSTRUCTION_STEP_EQUIPMENT_NAME = "name";

    //List of class objects to be stored in SQL database
    private static ArrayList<Recipe> RECIPE_OBJ_LIST = new ArrayList<>();
    private static ArrayList<Cuisine> CUISINE_OBJ_LIST = new ArrayList<>();
    private static ArrayList<DishType> DISH_TYPE_OBJ_LIST = new ArrayList<>();
    private static ArrayList<Diet> DIET_OBJ_LIST = new ArrayList<>();
    private static ArrayList<Ingredient> INGREDIENT_OBJ_LIST = new ArrayList<>();
    private static ArrayList<Instruction> INSTRUCTION_OBJ_LIST = new ArrayList<>();

    //SQL Table Names
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
        RECIPE_OBJ_LIST = createRecipeClassObjectsWithData(recipeDataSource, JSON_RECIPE_MAIN_KEY);
        //viewRecipeClassData(RECIPE_OBJ_LIST);
        viewCuisineClassData(CUISINE_OBJ_LIST);
        closeDataSource(recipeDataSource);

    }

    public static void openDataSource(Datasource datasource){
        if (!datasource.open()) {
            System.out.println("\nCannot open datasource");
        } else {
            System.out.println("\nDatasource open!");
        }
    }

    public static void viewData(Datasource datasource, String keyName){  //used for testing/production purposes
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
        int primaryKeyGenerator = 1;
        for(Object o : jsonArray){
            JSONObject jsonObject2 = new JSONObject((Map) o);
            Recipe recipeObjectHolder = new Recipe();
            int RecipePrimaryKey = Math.toIntExact((Long) jsonObject2.get("id"));
            recipeObjectHolder.setIdPrimaryKey(RecipePrimaryKey);
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

            //Write to Cuisine Class
            createCuisineClassObjectsWithJsonData(primaryKeyGenerator, RecipePrimaryKey, (JSONArray) jsonObject2.get("cuisines"));

            listToReturn.add(recipeObjectHolder);
            primaryKeyGenerator++;
        }
        return listToReturn;
    }

    //Cuisine data handling
    public static void createCuisineClassObjectsWithJsonData(int primaryKey, int foreignKey, JSONArray cuisineJsonArr) {
        Cuisine cuisine = new Cuisine();
        ArrayList<String> holderCuisineList = new ArrayList<>();

        cuisine.setIdPrimaryKey(primaryKey);
        cuisine.setRecipeIdForeignKey(foreignKey);
        if (!cuisineJsonArr.isEmpty()) {  //handles empty JSON array data
            for (Object o : cuisineJsonArr) {
                holderCuisineList.add((String) o);
            }
            cuisine.setCuisineListTypes(holderCuisineList);
        }

        CUISINE_OBJ_LIST.add(cuisine);
    }
    public static void viewCuisineClassData(ArrayList<Cuisine> cuisine){
        for(Cuisine c : cuisine){
            System.out.println(c.getIdPrimaryKey());
            System.out.println(c.getRecipeIdForeignKey());
            if(c.getCuisineListTypes() != null){
                for(String s : c.getCuisineListTypes()){
                    System.out.println(s);
                }
            }
        }
    }
    //End cuisine data handling

    //Dish type data handling
    //End dish type data handling

    //Diet data handling
    //End diet data handling

    //Ingredient data handling
    //End ingredient data handling

    //Instruction data handling
    //End instruction data handling

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
