package com.example.machinegunkellyv2;

import android.content.Context;

import java.util.ArrayList;

public class Ing_Button extends androidx.appcompat.widget.AppCompatButton {

    private Ingredients ing;

    public Ing_Button(Context context, Ingredients mt ) {
        super( context );
        ing = mt;
    }

    public String getName() {
        return ing.getName();
    }





}