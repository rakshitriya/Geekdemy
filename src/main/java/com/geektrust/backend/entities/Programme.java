package com.geektrust.backend.entities;

public class Programme  {
    private String type;
    private double price;

    public Programme(String type, double price){
        this.type = type;
        this.price = price;
    }
    public Programme(Programme programme){
        this(programme.getType(),programme.getPrice());
    }
    public String getType(){
        return type;
    }
    public double getPrice(){
        return price;
    }
}
