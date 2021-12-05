package com.example.fruity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FruitInfoActivity extends AppCompatActivity {
    TextView cityText;
    TextView weatherText;
    ImageView imageView;
    NetworkingService networkingService;
    JsonService jsonService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_info);
        networkingService = ( (myApp)getApplication()).getNetworkingService();
        jsonService = ( (myApp)getApplication()).getJsonService();

        String fruitName = getIntent().getStringExtra("SelectedFruit");



//
//        cityText = findViewById(R.id.cityName);
//        weatherText = findViewById(R.id.weather);
//        imageView = findViewById(R.id.image);
//        cityText.setText(cityName);



    }
}