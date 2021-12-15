package com.example.fruity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

public class FruitInfoActivity extends AppCompatActivity implements
        NetworkingService.NetworkingListener,
        View.OnClickListener ,
        DescriptionFragment.AddDesFruitListener{
    String AddFruitName;
    Button save;
    DatabaseService dbService;
    AlertDialog.Builder builder;
    TextView fruitTxt;
    String desText;
    Fruit obj;
    TextView fruitInfoText;
    ImageView imageView;
    NetworkingService networkingService;
    JsonService jsonService;
  Button descriptionTxt;
    Button moreInformation;
    Fruit fruit = new Fruit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_info);
        dbService = ((myApp)getApplication()).getDbService();
        networkingService = ( (myApp)getApplication()).getNetworkingService();
        jsonService = ( (myApp)getApplication()).getJsonService();
        networkingService.listener=this;


        String fruitName = getIntent().getStringExtra("SelectedFruit");
        networkingService.fetchFruitsInfo(fruitName);
   descriptionTxt = findViewById(R.id.first_fragment);
   descriptionTxt.setOnClickListener(this);

        fruitTxt = findViewById(R.id.fruitName);
        fruitTxt.setText(fruitName);
        AddFruitName=fruitName;
        builder = new AlertDialog.Builder(this);
        fruitInfoText = findViewById(R.id.fruits);
        imageView = findViewById(R.id.image);
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnAlert();

            }
        });

        moreInformation = findViewById(R.id.Extra);
       // Intent intent = new Intent(this,ExtraInfoActivity.class);
        moreInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FruitInfoActivity.this,ExtraInfoActivity.class);
                intent.putExtra("MoreInformation",AddFruitName);
                startActivity(intent);
            }
        });
    }
    private void showAnAlert(){
        builder.create();
        builder.setTitle("Would you like to save this Fruit?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                fruit.fruitName=AddFruitName;
                dbService.saveNewFruit(fruit);
                finish();

                Log.d("Fruity App","in dialog ok button");
            }
        });
        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("Fruity App","in dialog cancel button");
            }
        });
        builder.show();
    }

    //@SuppressLint("SetTextI18n")
    @Override
    public void APINetworkListner(String jsonString) {
        try {

            FruitData fruitData =jsonService.parseFruitsSecondAPIJson(jsonString);
        //    fruitInfoText.setText(fruitData.description);
   desText=fruitData.description;
            fruitInfoText.setText("DESCRIPTION" +"\n" +fruitData.description+ "\n\n"
                    +"USES" +"\n" +fruitData.uses+ "\n\n" +"HEALTH"+"\n" +fruitData.health+ "\n\n");
            networkingService.listener=this;
           // String url = "https://tropicalfruitandveg.com/thumb.php?image=images/almondfruit.jpg";
           networkingService.fetchImage(fruitData.imageurl);
            //networkingService.fetchImage(url);
            networkingService.makeURL(fruitData.imageurl);
           // System.out.println(fruitData.othname+"##########################");

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
    if (v.getId() == R.id.first_fragment) {

        //public void updateQuestionFragment(int questionID, int colorID) {
        //        FragmentManager fm = getSupportFragmentManager();
        //        Fragment questionFragmentObject = (QuestionFragment) fm.findFragmentById(R.id.question_container);
        //        QuestionFragment questionFragment = QuestionFragment.newInstance(questionID, colorID);
        //
        //        if (questionFragmentObject == null) {// that mean the area is empty
        //            fm.beginTransaction().add(R.id.question_container,questionFragment,"tag").commit();
        //
        ////
        //        } else {// the area is already has third fragment
        //            // I'm able to delete that fragment
        //            //fm.beginTransaction().replace(R.id.question_container,questionFragment);
        //
        //          //  fm.beginTransaction().remove(questionFragmentObject);
        //
        //            fm.beginTransaction().replace(R.id.question_container, questionFragment).commit();
        //        }
        //    }

        FragmentManager fm = getSupportFragmentManager();
        Fragment questionFragmentObject = (DescriptionFragment) fm.findFragmentById(R.id.main_Frame);
        DescriptionFragment descriptionFragment = DescriptionFragment.newInstance(desText);
        if (questionFragmentObject == null) {// that mean the area is empty
            fm.beginTransaction().add(R.id.main_Frame, descriptionFragment, "tag").commit();
        } else {// the area is already has third fragment
            fm.beginTransaction().replace(R.id.main_Frame, descriptionFragment).commit();
////            Bundle bundle = new Bundle();
////         bundle.putString("fruitInfo",fruitInfoText.getText().toString());
////         DescriptionFragment ds = new DescriptionFragment();
////         ds.setArguments(bundle);
//getSupportFragmentManager().beginTransaction().replace(R.id.main_Frame,descriptionFragment,null).commit();
        }
    }
}

    @Override
    public void addDes(String fruitDes) {

    }

//    private void openListActivity(){
//        Intent toListActivity = new Intent(this,ExtraInfoActivity.class);
//
//        toListActivity.putParcelableArrayListExtra("MoreInformation",.getListOfDonations());
//        toListActivity.putExtra("total",DonationManager.getTotal(donationManager.getListOfDonations()));
//        startActivity(toListActivity);
//    }
//


}