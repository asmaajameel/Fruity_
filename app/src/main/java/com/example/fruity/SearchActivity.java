package com.example.fruity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements
        FruitAdapter.fruitClickListner, NetworkingService.NetworkingListener {

    ArrayList<Fruit> fruits = new ArrayList<Fruit>();
    RecyclerView recyclerView;
    FruitAdapter adapter;
    NetworkingService networkingService;
    JsonService jsonService;
    DatabaseService dbService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.fruitList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FruitAdapter(this,fruits);
        recyclerView.setAdapter(adapter);
        setTitle("Search for Favourite Fruit..");
        dbService = ((myApp)getApplication()).getDbService();
        networkingService = ( (myApp)getApplication()).getNetworkingService();
        jsonService = ( (myApp)getApplication()).getJsonService();


        networkingService.listener = this;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchViewMenuItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) searchViewMenuItem.getActionView();
        String searchFor = searchView.getQuery().toString();
        if (!searchFor.isEmpty()) {
            searchView.setIconified(false);
            searchView.setQuery(searchFor, false);
        }

        searchView.setQueryHint("Search for fruits");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("query", query);//
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("query change", newText);
                if (newText.length() >= 1) {
                    networkingService.fetchFruitsName(newText);
                } else {
                    adapter.fruitList = new ArrayList<>(0);
                    adapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();
        networkingService = ( (myApp)getApplication()).getNetworkingService();
        jsonService = ( (myApp)getApplication()).getJsonService();
        networkingService.listener = this;
    }

    @Override
    public void fruitClicked(Fruit selectedCity) {
        // show an alert to ask the usr for saving this city to db
        dbService.saveNewFruit(selectedCity);
        finish();
    }
//    @Override
//    public void fruitClicked(Fruit selectedFruit) {
//        Intent intent = new Intent(this,FruitInfoActivity.class);
//        intent.putExtra("SelectedFruit",selectedFruit.getFruitName());
//        startActivity(intent);
//    }

    @Override
    public void APINetworkListner(String jsonString) {
        Log.d("tag", jsonString);// not parsed yet.
        fruits =  jsonService.parseFruitsAPIJson(jsonString);
        adapter.fruitList = fruits;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void APINetworkListnerForImage(Bitmap image) {

    }

}
