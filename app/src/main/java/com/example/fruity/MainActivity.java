package com.example.fruity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatabaseService.DatabaseListener, dbFruitsAdapter.fruitClickListner{
    DatabaseService dbService;
    dbFruitsAdapter adapter;
    RecyclerView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbService = ((myApp)getApplication()).getDbService();

        dbService.getDbInstance(this);
        dbService.getAllFruitsFromDB();
        dbService.listener = this;

        list = findViewById(R.id.dbFruits);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new dbFruitsAdapter(this,new ArrayList<>(0));
        adapter.listner = this;
    }

    public void addNewFruit(View view) {
        Intent searchIntent = new Intent(this, SearchActivity.class);
        startActivity(searchIntent);

    }

    @Override
    public void databaseFruitListener(List<Fruit> dbFruit) {
        adapter.fruitList = dbFruit;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void fruitClicked(Fruit selectedCity) {
        Intent intent = new Intent(this,FruitInfoActivity.class);
        intent.putExtra("SelectedFruit",selectedCity.getFruitName());
        startActivity(intent);
    }
}