package com.example.fruity;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.room.Room;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseService {
    public static FruitsDatabase dbInstance;

    interface DatabaseListener{
        void databaseFruitListener(List<Fruit> dbFruit);
    }
    public DatabaseListener listener;

    ExecutorService fruitExecutor = Executors.newFixedThreadPool(4);
    Handler fruitsHandler = new Handler(Looper.getMainLooper());


//    private void buildDBInstance(Context context) {//here we will great our Unique instance
//        dbInstance = Room.databaseBuilder(context,FruitsDatabase.class,"fruit_database").build();
//    }
    //next function it will call when the app start to run for first time.it will build the instance above
    public FruitsDatabase getDbInstance(Context context){
        // if the app start run for first time
        if(dbInstance == null);
        //build our db
            dbInstance = Room.databaseBuilder(context.getApplicationContext(),FruitsDatabase.class,"fruit_database").build();
//        buildDBInstance(context);
        //else(this is happened evey time we use the database except the first
        //  time when it will create it in the if  statement) return it when we need it
        return dbInstance;
    }

    void  getAllFruitsFromDB(){
        fruitExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Fruit> fruits = dbInstance.getDao().getAllFruit();
                fruitsHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.databaseFruitListener(fruits);
                    }
                });
            }
        });
    }

    void saveNewFruit(Fruit f){
        fruitExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dbInstance.getDao().addNewFruit(f);
            }
        });
    }
    public void deleteFruitName(Fruit f){
        fruitExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dbInstance.getDao().deleteFruit(f);
            }
        });
    }
}
