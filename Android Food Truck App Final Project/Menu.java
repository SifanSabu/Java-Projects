package com.example.machinegunkellyv2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Menu extends AppCompatActivity {

    private MenuDatabase MD;
    private ScrollView scrollView;
    private ButtonHandler bh = new ButtonHandler();
    private IngredientsDatabase ingD;
    public static Double price;
    public static Double total = 0.0;
    public static String name;
    public static String[] ia;
    public static String orderString = "";
    public static String orderIngString = "";
    private int version = 102;
    private Meal_Item meal_item;
    View popupView;
    CheckBox cb;
    Intent customIntent;
//    public MediaPlayer mediaPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_scroll);

//        mediaPlayer2 = MediaPlayer.create(this, R.raw.kk);
//        Log.w("Menu", "on menu start is playing: " + MainActivity.playFlag);
//        if( !( MainActivity.playFlag ) ) {
//            mediaPlayer2.start();
//            mediaPlayer2.setLooping(true);
//            MainActivity.playFlag = true;
//            Log.w("Menu", "is playing after starting: " + MainActivity.playFlag);
//        }

        MD = new MenuDatabase(this, "MenuDatabase", null, version);
        ingD = new IngredientsDatabase(this, "IngDatabase", null, version);

        Log.d("***", String.valueOf(this.getDatabasePath("MenuDatabase")));

        scrollView = findViewById(R.id.scrollView);
        customIntent = new Intent(this, menuCustom.class);

        updateView();
    }

    public void updateView() {
        // this is where we are populating our databases


///*
/*
        Ingredients a = new Ingredients("Buns", 30);
        Ingredients b = new Ingredients("Cheese", 30);
        Ingredients c = new Ingredients("Patty", 30);
        Ingredients d = new Ingredients("Tomatoes", 30);
        Ingredients e = new Ingredients("Rusted Nails", 30);
        Ingredients f = new Ingredients("Nike Shoelace", 30);
        Ingredients g = new Ingredients("MGK Autograph", 30);
        Ingredients h = new Ingredients("Lettuce", 30);
        Ingredients i = new Ingredients("Pickles", 30);
        Ingredients j = new Ingredients("Egg Shells", 30);
        Ingredients k = new Ingredients("Barbed Wire", 30);
        Ingredients l = new Ingredients("MGK Guitar", 30);
        Ingredients m = new Ingredients("MGK Hair Glands", 30);
        Ingredients n = new Ingredients("Tortilla", 30);
        Ingredients o = new Ingredients("Beef", 30);
        Ingredients p = new Ingredients("Chicken", 30);
        Ingredients q = new Ingredients("Salsa", 30);
        Ingredients r = new Ingredients("Fajita Veggies", 30);
        Ingredients s = new Ingredients("Sour Cream", 30);
        Ingredients t = new Ingredients("MGK Pop Socket", 30);
        Ingredients u = new Ingredients("Beans", 30);
        Ingredients v = new Ingredients("Living Dog", 30);
        Ingredients w = new Ingredients("Unplugged Microwave", 30);
        Ingredients x = new Ingredients("Chips", 30);
        Ingredients y = new Ingredients("MGK Sticker", 30);
        Ingredients z = new Ingredients("Spaghetti", 30);
        Ingredients A = new Ingredients("Parmesan", 30);
        Ingredients B = new Ingredients("MGK Physics Test", 30);
        Ingredients C = new Ingredients("Dough", 30);
        Ingredients D = new Ingredients("Sauce", 30);
        Ingredients E = new Ingredients("Pineapple", 30);
        Ingredients F = new Ingredients("Pepperoni", 30);
        Ingredients G = new Ingredients("Black Olives", 30);
        Ingredients H = new Ingredients("MGK Guitar Picks", 30);
        Ingredients I = new Ingredients("Ham", 30);
        Ingredients J = new Ingredients("Bacon", 30);
        Ingredients K = new Ingredients("Salami", 30);
        Ingredients L = new Ingredients("Onions", 30);
        Ingredients M = new Ingredients("MGK himself", 30);
        Ingredients N = new Ingredients("MGK Sweat", 30);
        Ingredients O = new Ingredients("Protein Powder", 30);
        Ingredients P = new Ingredients("2% Milk", 30);
        Ingredients Q = new Ingredients("Brownie", 30);
        Ingredients R = new Ingredients("Chocolate Chip Cookie", 30);
        Ingredients S = new Ingredients("Oatmeal Raisin Cookie", 30);
        Ingredients T = new Ingredients("Cranberry Walnut Cookie", 30);
        Ingredients U = new Ingredients("Carrot Cake", 30);
        Ingredients V = new Ingredients("Lemon Pound Cake", 30);
        Ingredients W = new Ingredients("Black Forest Cake", 30);
        Ingredients X = new Ingredients("Waffle Cone", 30);
        Ingredients Y = new Ingredients("Hot Chocolate", 30);
        Ingredients Z = new Ingredients("Lemonade", 30);
        Ingredients AA = new Ingredients("Whiskey", 30);
        Ingredients BB = new Ingredients("Vodka", 30);
        Ingredients CC = new Ingredients("Soft Drink", 30);
        Ingredients DD = new Ingredients("Waffle Cup", 30);



        ingD.insert(a);
        ingD.insert(b);
        ingD.insert(c);
        ingD.insert(d);
        ingD.insert(e);
        ingD.insert(f);
        ingD.insert(g);
        ingD.insert(h);
        ingD.insert(i);
        ingD.insert(j);
        ingD.insert(k);
        ingD.insert(l);
        ingD.insert(m);
        ingD.insert(n);
        ingD.insert(o);
        ingD.insert(p);
        ingD.insert(q);
        ingD.insert(r);
        ingD.insert(s);
        ingD.insert(t);
        ingD.insert(u);
        ingD.insert(v);
        ingD.insert(w);
        ingD.insert(x);
        ingD.insert(y);
        ingD.insert(z);
        ingD.insert(A);
        ingD.insert(B);
        ingD.insert(C);
        ingD.insert(D);
        ingD.insert(E);
        ingD.insert(F);
        ingD.insert(G);
        ingD.insert(H);
        ingD.insert(I);
        ingD.insert(J);
        ingD.insert(K);
        ingD.insert(L);
        ingD.insert(M);
        ingD.insert(N);
        ingD.insert(O);
        ingD.insert(P);
        ingD.insert(Q);
        ingD.insert(R);
        ingD.insert(S);
        ingD.insert(T);
        ingD.insert(U);
        ingD.insert(V);
        ingD.insert(W);
        ingD.insert(X);
        ingD.insert(Y);
        ingD.insert(Z);
        ingD.insert(AA);
        ingD.insert(BB);
        ingD.insert(CC);
        ingD.insert(DD);


        Meal_Item a0 = new Meal_Item("Grilled Cheese", 27, new ArrayList<>(Arrays.asList(a, b, c, d, e, f, g ) ) );
        Meal_Item a1 = new Meal_Item("Hamburger", 26, new ArrayList<>( Arrays.asList(a, b, c, h, d, i, j, k, m ) ) );
        Meal_Item a2 = new Meal_Item("Small Tacos", 25, new ArrayList<>( Arrays.asList( n, o, p, u, h, q, r, s, b, t ) ) );
        Meal_Item a3 = new Meal_Item("Burrito", 24, new ArrayList<>( Arrays.asList( n, o, p, u, h, q, r, s, b ) ) );
        Meal_Item a4 = new Meal_Item("Hot Dog", 23, new ArrayList<>( Arrays.asList( a, v, w ) ) );
        Meal_Item a5 = new Meal_Item("Nachos", 22, new ArrayList<>( Arrays.asList( x, b, y ) ) );
        Meal_Item a6 = new Meal_Item("Chicken Parmesan", 21, new ArrayList<>( Arrays.asList( z, p, A, B ) ) );
        Meal_Item a7 = new Meal_Item("Pizza", 20, new ArrayList<>( Arrays.asList( C, b, D, E, F, G, H ) ) );
        Meal_Item a8 = new Meal_Item("Philly Cheesesteak", 19, new ArrayList<>( Arrays.asList( a, I, b, J, K, h, d, L, M ) ) );

        Meal_Item a9 = new Meal_Item("Soft Drinks", 18, new ArrayList<>( Collections.singletonList( CC ) ) );
        Meal_Item a10 = new Meal_Item("Hot Chocolate", 17, new ArrayList<>( Collections.singletonList( Y ) ) );
        Meal_Item a11 = new Meal_Item("Water", 16, new ArrayList<>( Collections.singletonList( N ) ) );
        Meal_Item a12 = new Meal_Item("Protein Shake", 15, new ArrayList<>( Arrays.asList( O, P, N ) ) );
        Meal_Item a13 = new Meal_Item("Lemonade", 14, new ArrayList<>( Collections.singletonList( Z ) ) );
        Meal_Item a14 = new Meal_Item("Handle of Whiskey", 13, new ArrayList<>( Collections.singletonList( AA ) ) );
        Meal_Item a15 = new Meal_Item("Handle of Vodka", 12, new ArrayList<>( Collections.singletonList( BB ) ) );
        Meal_Item a16 = new Meal_Item("2% Milk", 11, new ArrayList<>( Collections.singletonList( P ) ) );

        Meal_Item a17 = new Meal_Item("Brownie", 10, new ArrayList<>( Collections.singletonList( Q ) ) );
        Meal_Item a18 = new Meal_Item("Chocolate Chip Cookie", 9, new ArrayList<>( Collections.singletonList( R ) ) );
        Meal_Item a19 = new Meal_Item("Oatmeal Raisin Cookie", 8, new ArrayList<>( Collections.singletonList( S ) ) );
        Meal_Item a20 = new Meal_Item("Cranberry Walnut Cookie", 7, new ArrayList<>( Collections.singletonList( T ) ) );
        Meal_Item a21 = new Meal_Item("Carrot Cake Slice", 6, new ArrayList<>( Collections.singletonList( U ) ) );
        Meal_Item a22 = new Meal_Item("Lemon Pound Cake Slice", 5, new ArrayList<>( Collections.singletonList( V ) ) );
        Meal_Item a23 = new Meal_Item("Black Forest Cake Slice", 4, new ArrayList<>( Collections.singletonList( W ) ) );
        Meal_Item a24 = new Meal_Item("Vanilla Ice Cream Scoop", 3, new ArrayList<>( Arrays.asList( X, DD ) ) );
        Meal_Item a25 = new Meal_Item("Chocolate Ice Cream Scoop", 2, new ArrayList<>( Arrays.asList( X, DD ) ) );
        Meal_Item a26 = new Meal_Item("Mango Sorbet Scoop", 1, new ArrayList<>( Arrays.asList( X, DD ) ) );

        MD.insert(a0);
        MD.insert(a1);
        MD.insert(a2);
        MD.insert(a3);
        MD.insert(a4);
        MD.insert(a5);
        MD.insert(a6);
        MD.insert(a7);
        MD.insert(a8);
        MD.insert(a9);
        MD.insert(a10);
        MD.insert(a11);
        MD.insert(a12);
        MD.insert(a13);
        MD.insert(a14);
        MD.insert(a15);
        MD.insert(a16);
        MD.insert(a17);
        MD.insert(a18);
        MD.insert(a19);
        MD.insert(a20);
        MD.insert(a21);
        MD.insert(a22);
        MD.insert(a23);
        MD.insert(a24);
        MD.insert(a25);
        MD.insert(a26);
*/
//*/
        // here we get all our MealItems and create buttons for each of them
        // we add these buttons to a GridView inside of a ScrollView
        ArrayList<Meal_Item> candies = MD.selectAll();

        scrollView.removeAllViewsInLayout();

        GridLayout gridLayout = new GridLayout(this);

        gridLayout.setColumnCount(1);
        gridLayout.setRowCount(candies.size() + 1);
        Meal_Item_Button[] buttons = new Meal_Item_Button[candies.size()];

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int buttonWidth = size.x;
        int buttonHeight = size.y / 20;

        for (int index = 0; index < candies.size(); index++) {
            Meal_Item candy = candies.get(index);
            buttons[index] = new Meal_Item_Button(this, candy);

            String string = NumberFormat.getCurrencyInstance().format(candy.getPrice()) + " - " + candy.getName();
            buttons[index].setTextColor( Color.rgb(255, 132, 39) );
            buttons[index].setText(string);
            buttons[index].setOnClickListener(bh);
            gridLayout.addView(buttons[index], buttonWidth, buttonHeight);
        }
        scrollView.addView(gridLayout);
    }

    // intent to move to Order
    public void goToOrder(View v) {
//        if(MainActivity.playFlag) {
//            mediaPlayer2.stop();
//            mediaPlayer2.release();
//            Log.w("Menu", "after move is playing: " + mediaPlayer2.isPlaying());
//        }
        Intent orderIntent = new Intent(this, Order.class);
        startActivity(orderIntent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
//        MainActivity.playFlag = false;
    }

    // intent to move back to Start
    public void goBackToStart(View v) {
//        if(MainActivity.playFlag) {
//            mediaPlayer2.stop();
//            mediaPlayer2.release();
//            Log.w("Menu", "after move is playing: " + mediaPlayer2.isPlaying());
//        }
        Intent orderIntent = new Intent(this, MainActivity.class);
        startActivity(orderIntent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
//        MainActivity.playFlag = false;
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            // here we get various values from the MealItemButton that was clicked
            price = ((Meal_Item_Button) v).getPrice();
            name = ((Meal_Item_Button) v).getName();
            meal_item = ((Meal_Item_Button) v).getMeal_item();
            ia = MD.getIngredients(meal_item);

            // we also move to the Customize screen
            startActivity(customIntent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        }
    }

}
