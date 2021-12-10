package com.example.fruity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

    @Dao
    public interface FruitDAO {
        @Insert
        void addNewFruit (Fruit fruit);

        @Delete
        void deleteFruit (Fruit fruit);

        @Query("SELECT * FROM Fruit")
        List<Fruit> getAllFruit();


}
