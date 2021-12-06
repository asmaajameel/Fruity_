package com.example.fruity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class JsonService {

    ArrayList<Fruit> fruitFromAPI;
    public ArrayList<Fruit> parseFruitsAPIJson(String jsonFruitString){
        Fruit fruit = new Fruit();
        fruitFromAPI = new ArrayList<>(0);
        try {
            JSONObject jsonObject = new JSONObject(jsonFruitString);// root
            JSONArray fruitArray = jsonObject.getJSONArray("results");
            for (int i = 0 ; i< fruitArray.length(); i++) {
                JSONObject fruitObject = fruitArray.getJSONObject(i);
                String pid = fruitObject.getString("tfvname");
                fruit = new Fruit(pid);
                fruitFromAPI.add(fruit);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  fruitFromAPI;
    }

//    ArrayList<Fruit> plantFromAPI;
//    public ArrayList<Fruit> parseCitiesAPI2Json(String jsonPlantString){
//        Fruit plant = new Fruit();
//        plantFromAPI = new ArrayList<>(0);
//        try {
//            JSONObject jsonObject = new JSONObject(jsonPlantString);// root
//            JSONArray plantArray = jsonObject.getJSONArray("results");
//            JSONObject plantObject = plantArray.getJSONObject(0);
//            String pid1 = plantObject.getString("imageurl");
//            String pid2 = plantObject.getString("tfvname");
//            String pid3 = plantObject.getString("uses");
//            String pid4 = plantObject.getString("health");
//
////        JSONObject mainObject = jsonObject.getJSONObject("main");
////        Double temp = mainObject.getDouble("temp");
//            plant = new Fruit(pid2);
//            plantFromAPI.add(plant);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return  plantFromAPI;
//
//    }
    public  FruitData  parseFruitsSecondAPIJson(String jsonFruitInfoString) throws JSONException {
        FruitData fruitData;
        JSONObject jsonObject = new JSONObject(jsonFruitInfoString);
        JSONArray fruitsInfoArray = jsonObject.getJSONArray("results");
        JSONObject fruitObject = fruitsInfoArray.getJSONObject(0);
        String imageString = fruitObject.getString("imageurl");
        String desString   = fruitObject.getString("description");
        String usesString  = fruitObject.getString("uses");
        String healthString = fruitObject.getString("health");

        fruitData = new FruitData(desString,imageString,usesString,healthString);
        return fruitData;
    }
}
