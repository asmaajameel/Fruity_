package com.example.fruity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

public class FruitInfoActivity extends AppCompatActivity implements NetworkingService.NetworkingListener{
    TextView fruitTxt;
    TextView fruitInfoText;
    ImageView imageView;
    NetworkingService networkingService;
    JsonService jsonService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_info);
        networkingService = ( (myApp)getApplication()).getNetworkingService();
        jsonService = ( (myApp)getApplication()).getJsonService();
        networkingService.listener=this;

        String fruitName = getIntent().getStringExtra("SelectedFruit");
        networkingService.fetchFruitsInfo(fruitName);




        fruitTxt = findViewById(R.id.fruitName);
        fruitTxt.setText(fruitName);

        fruitInfoText = findViewById(R.id.fruits);
        imageView = findViewById(R.id.image);



    }

    @Override
    public void APINetworkListner(String jsonString) {
        try {
            FruitData fruitData =jsonService.parseFruitsSecondAPIJson(jsonString);
        //    fruitInfoText.setText(fruitData.description);

            fruitInfoText.setText("HEALTH"+fruitData.health+ "\n" +"Uses" +fruitData.uses);//+ "\n" +"DESCRIPTION" +fruitData.description);
            networkingService.listener=this;
           // String url = "https://tropicalfruitandveg.com/thumb.php?image=images/almondfruit.jpg";
           networkingService.fetchImage(fruitData.imageurl);
            //networkingService.fetchImage(url);
            networkingService.makeURL(fruitData.imageurl);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void APINetworkListnerForImage(Bitmap image) {
        imageView.setImageBitmap(image);
    }
}