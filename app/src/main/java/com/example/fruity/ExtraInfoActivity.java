package com.example.fruity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONException;

public class ExtraInfoActivity extends AppCompatActivity implements
        NetworkingService.NetworkingListener{
    DatabaseService dbService;
    AlertDialog.Builder builder;
    TextView fruitTxt;
    String desText;
    TextView fruitInfoText;
    ImageView imageView;
    NetworkingService networkingService;
    JsonService jsonService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_info);
        dbService = ((myApp)getApplication()).getDbService();
        networkingService = ( (myApp)getApplication()).getNetworkingService();
        jsonService = ( (myApp)getApplication()).getJsonService();
        networkingService.listener=this;//SelectedFruit
        //String fruitName2 = getIntent().getStringExtra("SelectedFruit");

        String fruitName = getIntent().getStringExtra("MoreInformation");
        networkingService.fetchFruitsInfo(fruitName);

        fruitTxt = findViewById(R.id.fruitName);
        fruitTxt.setText(fruitName);
        builder = new AlertDialog.Builder(this);
        fruitInfoText = findViewById(R.id.fruits);
        imageView = findViewById(R.id.image);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void APINetworkListner(String jsonString) {
        try {
            FruitData fruitData =jsonService.parseFruitsSecondAPIJson(jsonString);
            //    fruitInfoText.setText(fruitData.description);
            desText=fruitData.description;
            fruitInfoText.setText("OTHER NAME" +"\n" +fruitData.othname+ "\n\n"
                    +"PROPAGATION" +"\n" +fruitData.propagation+ "\n\n" +"soil"+"\n" +fruitData.soil+ "\n\n"
                    +"climate" +"\n" +fruitData.climate);
            networkingService.listener=this;
            networkingService.fetchImage(fruitData.imageurl);
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