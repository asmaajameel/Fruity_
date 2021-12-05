package com.example.fruity;

public class Fruit {
    private int id;

    private String fruitName;

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


