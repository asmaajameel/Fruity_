package com.example.fruity;

public class FruitData {
    String description;
    String imageurl;
    String uses;
    String health;
    String othname;
    String propagation;
    String soil;
    String climate;

    public FruitData(String description, String imageurl, String uses, String health, String othname, String propagation, String soil, String climate) {
        this.description = description;
        this.imageurl = imageurl;
        this.uses = uses;
        this.health = health;
        this.othname = othname;
        this.propagation = propagation;
        this.soil = soil;
        this.climate = climate;
    }
    public FruitData(String description, String imageurl, String uses, String health) {
        this.description = description;
        this.imageurl = imageurl;
        this.uses = uses;
        this.health = health;
    }
}
