package com.example.machinegunkellyv2;

import java.util.ArrayList;

public class Meal_Item {

    private String name;
    private double price;
    private ArrayList<Ingredients> ingredients;

    public Meal_Item(String a, double c, ArrayList<Ingredients> b){
        name = a;
        price = c;
        ingredients = b;

    }
    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }
    public String getName() {
        return name;
    }
    public double getPrice(){
        return price;
    }
}
