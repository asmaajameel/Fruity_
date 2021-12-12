package com.example.fruity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

public class FruitInfoActivity extends AppCompatActivity implements
        NetworkingService.NetworkingListener,
        View.OnClickListener ,
        DescriptionFragment.AddDesFruitListener{

    TextView fruitTxt;
    String desText;
    TextView fruitInfoText;
    ImageView imageView;
    NetworkingService networkingService;
    JsonService jsonService;
  Button descriptionTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_info);

        networkingService = ( (myApp)getApplication()).getNetworkingService();
        jsonService = ( (myApp)getApplication()).getJsonService();
        networkingService.listener=this;

        String fruitName = getIntent().getStringExtra("SelectedFruit");
        networkingService.fetchFruitsInfo(fruitName);
   descriptionTxt = findViewById(R.id.first_fragment);
   descriptionTxt.setOnClickListener(this);

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
   desText=fruitData.description;
            fruitInfoText.setText("DESCRIPTION" +"\n" +fruitData.description+ "\n\n"
                    +"USES" +"\n" +fruitData.uses+ "\n\n" +"HEALTH"+"\n" +fruitData.health);
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

@Override
public void onClick(View v) {
        if(v.getId()==R.id.first_fragment){
            DescriptionFragment descriptionFragment = DescriptionFragment.newInstance(desText);
//            Bundle bundle = new Bundle();
//         bundle.putString("fruitInfo",fruitInfoText.getText().toString());
//         DescriptionFragment ds = new DescriptionFragment();
//         ds.setArguments(bundle);
getSupportFragmentManager().beginTransaction().replace(R.id.main_Frame,descriptionFragment,null).commit();
    }
  }

    @Override
    public void addDes(String fruitDes) {

    }
}