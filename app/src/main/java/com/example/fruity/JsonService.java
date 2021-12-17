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
                String pid = fruitObject.getString("tfvname");//imageurl
                String imageurl = fruitObject.getString("imageurl");
                fruit = new Fruit(pid,imageurl);
                fruitFromAPI.add(fruit);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  fruitFromAPI;
    }

    public  FruitData  parseFruitsSecondAPIJson(String jsonFruitInfoString) throws JSONException {
        FruitData fruitData;
        JSONObject jsonObject = new JSONObject(jsonFruitInfoString);
        JSONArray fruitsInfoArray = jsonObject.getJSONArray("results");
        JSONObject fruitObject = fruitsInfoArray.getJSONObject(0);
        String imageString = fruitObject.getString("imageurl");
        String desString   = fruitObject.getString("description");
        String usesString  = fruitObject.getString("uses");
        String healthString = fruitObject.getString("health");
        String othname = fruitObject.getString("othname");
        String propagation = fruitObject.getString("propagation");
        String soil = fruitObject.getString("soil");
        String climate = fruitObject.getString("climate");

//        fruitData = new FruitData(desString,imageString,usesString,healthString);
        fruitData = new FruitData(desString,imageString,usesString,healthString,othname,propagation,soil,climate);
        return fruitData;
    }
}
