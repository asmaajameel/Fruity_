package com.example.fruity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Fruit {
 @PrimaryKey(autoGenerate = true)
    public int id;
    public String fruitName;

    Fruit(){}
    public Fruit(String fruit) {
        this.fruitName = fruit;
    }
    public int getId() {
        return id;
    }
    public String getFruitName() {
        return fruitName;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }
}


