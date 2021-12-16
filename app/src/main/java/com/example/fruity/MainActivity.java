package com.example.fruity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        DatabaseService.DatabaseListener,
        dbFruitsAdapter.fruitClickListner{//,NetworkingService.NetworkingListener{

    NetworkingService networkingService;
    JsonService jsonService;
    DatabaseService dbService;
    dbFruitsAdapter adapter;
    RecyclerView list;
    ArrayList<Fruit> newFruit=new ArrayList<>(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbService = ((myApp)getApplication()).getDbService();
        dbService.listener = this;
        dbService.getDbInstance(this);
        dbService.getAllFruitsFromDB();

        list = findViewById(R.id.dbFruits);
        adapter = new dbFruitsAdapter(this,newFruit);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter.listner = this;
        adapter.notifyDataSetChanged();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(list);
        //LinearLayoutManager layoutManager
        //    = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //RecyclerView myItems = findViewById(R.id.my_recycler_view);
        //myItems.setLayoutManager(layoutManager);

    }
    @Override
    protected void onResume() {
        super.onResume();
        dbService.getAllFruitsFromDB();
       // adapter.notifyDataSetChanged();
//        networkingService = ( (myApp)getApplication()).getNetworkingService();
//        jsonService = ( (myApp)getApplication()).getJsonService();
//        networkingService.listener = this;
    }
    public void addNewFruit(View view) {
        Intent searchIntent = new Intent(this, SearchActivity.class);
        startActivity(searchIntent);

    }

    @Override
    public void databaseFruitListener(List<Fruit> dbFruit) {
//        adapter.fruitList = dbFruit;
//        adapter.notifyDataSetChanged();
      newFruit=new ArrayList<Fruit>(dbFruit);
      //  dbFruitsAdapter myAdapter = new dbFruitsAdapter(this,newFruit);
        adapter = new dbFruitsAdapter(this,newFruit);
        list.setAdapter(adapter);
        adapter.fruitList=newFruit;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void fruitClickedListener(Fruit selectedFruit) {
        Intent intent = new Intent(this,FruitInfoActivity.class);
        intent.putExtra("SelectedFruit",selectedFruit.getFruitName());
        startActivity(intent);
    }

    // table view deleget
    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.DOWN, ItemTouchHelper.LEFT |
            ItemTouchHelper.RIGHT |
            ItemTouchHelper.DOWN |
            ItemTouchHelper.UP) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Toast.makeText(MainActivity.this, "Item Moveing", Toast.LENGTH_SHORT).show();

            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            //Remove swiped item from list and notify the RecyclerView
            int position = viewHolder.getAdapterPosition();
            //dbService.deleteFruitName(adapter.fruitList.get(position));
          dbService.deleteFruitName(newFruit.get(position));
            //newFruit.remove(position);
            adapter.fruitList.remove(position);
           // deleteDatabase(String.valueOf(position));
            // we have to remove it from db as well
           // adapter.notifyItemRemoved(position);
            adapter.notifyDataSetChanged();

        }
    };

//    @Override
//    public void APINetworkListner(String jsonString) {
//
//    }
//
//    @Override
//    public void APINetworkListnerForImage(Bitmap image) {
//
//    }
}
