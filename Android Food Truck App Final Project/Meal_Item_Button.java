package com.example.machinegunkellyv2;

import android.content.Context;

import java.util.ArrayList;

public class Meal_Item_Button extends androidx.appcompat.widget.AppCompatButton {

    private Meal_Item meal_item;

    public Meal_Item_Button(Context context, Meal_Item mt ) {
        super( context );
        meal_item = mt;
    }

    public double getPrice() {
        return meal_item.getPrice();
    }
    public String getName() {
        return meal_item.getName();
    }
    public ArrayList<Ingredients> getIngredients() {
        return meal_item.getIngredients();
    }
    public Meal_Item getMeal_item() {
        return meal_item;
    }





}