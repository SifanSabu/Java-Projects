package com.example.machinegunkellyv2;

public class Ingredients {

    private String name;
    private int quantity;

    public Ingredients(String a, int b){
        name = a;
        quantity = b;

    }
    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

}
