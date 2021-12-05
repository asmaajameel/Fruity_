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

//    public ArrayList<Fruit> parseCitiesAPIJson(String jsonCities){
//        //
//        ArrayList<Fruit> allCitiesFromAPI = new ArrayList<>(0);
//        try {//
//            JSONArray jsonArray = new JSONArray(jsonCities);
//            for (int i = 0 ; i< jsonArray.length(); i++){
//               String cityName = jsonArray.getString(i);
//                Fruit newCity = new Fruit(jsonArray.getString(i));
//                allCitiesFromAPI.add(new Fruit(jsonArray.getString(i)));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return allCitiesFromAPI;
//   }
}




//    public ArrayList<City> parseCitiesAPIJson(String jsonCities){
//        //
//        ArrayList<City> allCitiesFromAPI = new ArrayList<>(0);
//        try {//
//            JSONArray jsonArray = new JSONArray(jsonCities);
//            for (int i = 0 ; i< jsonArray.length(); i++){
////                String cityName = jsonArray.getString(i);
////                City newCity = new City(jsonArray.getString(i));
//                allCitiesFromAPI.add(new City(jsonArray.getString(i)));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return allCitiesFromAPI;
//    }