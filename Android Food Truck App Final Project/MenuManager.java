package com.example.machinegunkellyv2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;


import java.text.NumberFormat;
import java.util.ArrayList;

public class MenuManager extends Activity {

    private MenuDatabase dbManager;
    private ScrollView scrollView;
    private IngredientsDatabase ingD;
    private ButtonHandler bh = new ButtonHandler();
    private ArrayList<Ingredients> newIng;
    private ArrayList<Ingredients> candies;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_activity);
        dbManager = new MenuDatabase(this, "MenuDatabase", null, 102);
        ingD = new IngredientsDatabase(this, "IngDatabase", null, 102);

        scrollView = findViewById(R.id.scrollView);
        newIng = new ArrayList<Ingredients>();

        updateView();
    }

    // this will move the user back to the Start screen only if something was added
    public void goBack()
    {
        this.finish();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    // this will move the user back to the Start screen if nothing was added
    public void goToStart(View v){
        Intent startIntent = new Intent(this, MainActivity.class );
        startActivity( startIntent );
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
    public void insert(View v){
        EditText nameEditText = findViewById(R.id.input_name);
        EditText priceEditText = findViewById(R.id.input_price);

        String name = nameEditText.getText().toString();
        String priceString = priceEditText.getText().toString();

        dbManager.insert(new Meal_Item(name, Double.parseDouble(priceString), newIng));
        dbManager.order(new Meal_Item(name, Double.parseDouble(priceString), newIng));
        Intent startIntent = new Intent(this, MainActivity.class );
        startActivity( startIntent );
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

    }
    // this is a work in progress
    public void updateView()
    {
        EditText nameEditText = findViewById(R.id.input_name);
        EditText priceEditText = findViewById(R.id.input_price);

        String name = nameEditText.getText().toString();
        String priceString = priceEditText.getText().toString();


        nameEditText.setText("");
        priceEditText.setText("");

//        dbManager.insert(new Meal_Item(name, Integer.parseInt(priceString),ArrayList<Ingredients> );

        Toast.makeText(this, "Candy Added", Toast.LENGTH_LONG).show();
        candies = ingD.selectAll();


        scrollView.removeAllViewsInLayout();

        GridLayout gridLayout = new GridLayout(this);

        gridLayout.setColumnCount(1);
        gridLayout.setRowCount(candies.size() + 1);
        Ing_Button[] buttons = new Ing_Button[candies.size()];
        System.out.println(candies.size()+"HDHDHD");
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int buttonWidth = size.x;
        int buttonHeight = size.y / 20;

        for (int index = 0; index < candies.size(); index++) {
            Ingredients candy = candies.get(index);
            buttons[index] = new Ing_Button(this, candy);

            String string = candy.getName();
            buttons[index].setTextColor( Color.rgb(255, 132, 39) );
            buttons[index].setText(string);
            buttons[index].setOnClickListener(bh);
            gridLayout.addView(buttons[index], buttonWidth, buttonHeight);
        }
        scrollView.addView(gridLayout);

        //goBack();


    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            String a = ((Ing_Button) v).getName();
            Ingredients b = new Ingredients(a,0);
            newIng.add(b);


        }
    }

}
