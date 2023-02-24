package com.nobsrecipebook;
import com.nobsrecipebook.model.ContactClass;
import com.nobsrecipebook.model.Datasource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        openDataSource(datasource);

        viewData(datasource);

        ArrayList<ContactClass> contactClassList = createClassObjectsWithData(datasource);
        viewClassData(contactClassList);

        closeDataSource(datasource);
    }

    public static void openDataSource(Datasource datasource){
        if (!datasource.open()) {
            System.out.println("\nCannot open datasource");
        } else {
            System.out.println("\nDatasource open!");
        }
    }

    public static void viewData(Datasource datasource){
        JSONObject jsonObject = datasource.listAllContacts();
        JSONArray jsonArray = (JSONArray) jsonObject.get("players_data");
        for (Object object : jsonArray) {
            System.out.println(object);
        }
    }

    public static ArrayList<ContactClass> createClassObjectsWithData(Datasource datasource){
        JSONObject jsonObject = datasource.listAllContacts();
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

    public static void closeDataSource(Datasource datasource){
        datasource.close();
    }
}
