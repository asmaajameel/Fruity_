package com.example.fruity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1,entities = {Fruit.class})
public abstract class FruitsDatabase extends RoomDatabase {
    abstract public FruitDAO getDao();

}
