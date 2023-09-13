package com.example.machinegunkellyv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.ArrayList;

public class menuCustom extends AppCompatActivity {

    private OrderDatabase OD;
    public static int quant;
    ScrollView customScroll;
    CheckBox checkBox;
    private ButtonHandler bh = new ButtonHandler();
    ArrayList<String> addOns = new ArrayList<String>();
    RadioGroup drinkDisplay1;
    RadioGroup drinkDisplay2;
    String drinkAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_menu);
        OD = new OrderDatabase(this, "OrderDatabase", null, 1);

        // getting references to various views
        drinkDisplay1 = findViewById(R.id.drink_display1);
        drinkDisplay2 = findViewById(R.id.drink_display2);
        customScroll = findViewById(R.id.customScroll);

        // creates a GridView inside a ScrollView to hold our CheckBoxes
        GridLayout gridLayout = new GridLayout(this);
        customScroll.removeAllViewsInLayout();
        customScroll.addView(gridLayout);

        // for every ingredient in our MealItem, we create a CheckBox and add it to GridView
        for(String ing : Menu.ia) {
            gridLayout.setColumnCount(1);
            checkBox = new CheckBox(this);
            checkBox.setText(ing);
            checkBox.setTextSize(30);
            checkBox.setChecked(false);
            checkBox.setOnClickListener(bh);
            gridLayout.addView(checkBox);
        }
    }

    // this clears values and goes back to our menu
    public void goToMenuBack(View v){
        addOns.clear();
        drinkAdd = "";
        Menu.orderIngString = "";

        Intent menuCustom = new Intent(this, Menu.class);
        startActivity(menuCustom);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    // this edits values and adds the MealItem to the order
    public void goToMenuAdd(View v){
        // this gets our quantity value
        EditText et = findViewById(R.id.quantity_display);
        if( !( String.valueOf(et.getText()).equals("") ) ) {
            quant = Integer.parseInt(et.getText().toString());
        } else {quant = 1;}

        // this takes the add ons and formats them in a string that we parse later
        for( int i = 0; i < addOns.size(); i++ ) {
            Menu.orderIngString += "          - " + addOns.get(i) + ",";
        }

        // this takes the drink and formats it in a string that we parse later
        if( drinkAdd != null) { Menu.orderIngString += "          - " + drinkAdd + ","; }

        // this is where we combine all our values into one string to store each MealItem's values
        Menu.orderString +=  NumberFormat.getCurrencyInstance().format(Menu.price)
                + " - " + Menu.name
                + " (" + quant + ")" + "\n"
                + Menu.orderIngString + ",";

        // this calculates total based on quantity ordered
        if(addOns != null || drinkAdd != null) {
            Menu.total += Menu.price * quant;

            // clears values for next item
            addOns.clear();
            drinkAdd = "";
            Menu.orderIngString = "";

            // starts intent to move back to Menu
            Intent menuCustom = new Intent(this, Menu.class);
            startActivity(menuCustom);
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

        }

        // if nothing was added, we make the user add something
        else if ( addOns == null){
            addOns.clear();
            drinkAdd = "";
            Menu.orderIngString = "";

            Intent menuCustom = new Intent(this, menuCustom.class);
            startActivity(menuCustom);
            overridePendingTransition(R.anim.bounce, R.anim.hold);
        }
        //OD.insert(findViewById("tip_button_group"));
    }

    public void oneRadioButtonClicked(View view) {
        // this handles what drink was added using switch-case
        switch(view.getId()) {
            case R.id.water_add:
            case R.id.lemonade_add:
            case R.id.soft_drink_add:
            case R.id.protein_shake_add:{
                drinkDisplay2.clearCheck();
                drinkDisplay1.check(view.getId());
                drinkAdd = ((RadioButton) view).getText() + "";
                break;
            }
            case R.id.whiskey_add:
            case R.id.vodka_add:
            case R.id.milk_add:
            case R.id.hot_chocolate_add:{
                drinkDisplay1.clearCheck();
                drinkDisplay2.check(view.getId());
                drinkAdd = ((RadioButton) view).getText() + "";
                break;
            }
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {

            // this handles what option was added
            if( ((CheckBox) v).isChecked() ){
                // if an option is added, we add it to an array to store it
                addOns.add( ((CheckBox)v).getText() + "");
                System.out.println("Checked");
            }

            else if ( !( ((CheckBox) v).isChecked() ) ){
                // if an option is removed we remove it from the array
                String temp = ((CheckBox)v).getText() + "";
                for ( int i = 0; i < addOns.size(); i++ ) {
                    if( addOns.get(i).equals(temp) ) {
                        addOns.remove(i);
                    }
                }
                System.out.println("Un-Checked");
            }
        }

    }

}
