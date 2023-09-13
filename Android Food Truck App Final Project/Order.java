package com.example.machinegunkellyv2;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Order extends AppCompatActivity {

    String mealItemName;
    Double mealItemPrice;
    private ButtonHandler bh = new ButtonHandler();
    public static String tip2;
    Double tipTotal = -1.0;
    ScrollView scrollView;
//    public static MediaPlayer mediaPlayer3;



    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_screen);

//        mediaPlayer3 = MediaPlayer.create(this, R.raw.df);
//        Log.w("Menu", MainActivity.playFlag + "");
//        if( !( MainActivity.playFlag ) ) {
//            mediaPlayer3.start();
//            mediaPlayer3.setLooping(true);
//            MainActivity.playFlag = true;
//            Log.w("Menu", MainActivity.playFlag + "");
//        }

        // getting references to various views
        scrollView = findViewById(R.id.order_scroll);
        scrollView.removeAllViewsInLayout();

        TextView tv = findViewById(R.id.subtotal_display);
        TextView tv2 = findViewById(R.id.total_display);

        RadioButton rb05 = ( RadioButton ) findViewById( R.id.tip_05 );
        RadioButton rb10 = ( RadioButton ) findViewById( R.id.tip_10 );
        RadioButton rb15 = ( RadioButton ) findViewById( R.id.tip_15 );
        RadioButton rb20 = ( RadioButton ) findViewById( R.id.tip_20 );

        // we set listeners for the tip options
        rb05.setOnClickListener(bh);
        rb10.setOnClickListener(bh);
        rb15.setOnClickListener(bh);
        rb20.setOnClickListener(bh);

        // we format the total cost as a money value
        String tip = NumberFormat.getCurrencyInstance().format(Menu.total);

        // we parse the large order string we have, delimiting on the ',' that separates each
        // MealItem
        String[] orders = Menu.orderString.split(",");
        for (String o : orders) { Log.w("Order", o);}
        if( orders.length > 0) {

            GridLayout gridLayout = new GridLayout(this);

            gridLayout.setColumnCount(1);
            gridLayout.setRowCount(orders.length + 1);
            TextView[] buttons = new TextView[orders.length];
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int viewWidth = size.x;

            for(int i = 0; i < orders.length; i++) {
                String order = orders[i];
                buttons[i] = new TextView(this);
                buttons[i].setText(order);
                buttons[i].setTextSize(25);
                gridLayout.addView(buttons[i], viewWidth, GridLayout.LayoutParams.WRAP_CONTENT);
            }
            scrollView.addView(gridLayout);
        }

        tv.setText(tip);
        tv2.setText(tip);

    }

    // intent to go back to Menu
    public void goToMenu(View v){
        Intent menuIntent = new Intent(this, Menu.class );
        startActivity( menuIntent );
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
//        mediaPlayer3.stop();
    }

    // intent to proceed to Checkout
    public void goToCheckout(View v) {
//        if(MainActivity.playFlag) {mediaPlayer3.stop();}
        Intent checkoutIntent = new Intent(this, Checkout.class);
        startActivity(checkoutIntent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
//        MainActivity.playFlag = false;
    }

    private class ButtonHandler implements View.OnClickListener {
        // this is where we do math based on which tip option was clicked
        public void onClick(View v) {
            RadioButton rb05 = ( RadioButton ) findViewById( R.id.tip_05 );
            RadioButton rb10 = ( RadioButton ) findViewById( R.id.tip_10 );
            RadioButton rb15 = ( RadioButton ) findViewById( R.id.tip_15 );
            RadioButton rb20 = ( RadioButton ) findViewById( R.id.tip_20 );
            TextView tv2 = findViewById(R.id.total_display);

            if (rb05.isChecked() ) {
                tipTotal = Menu.total*1.05;
                tip2 = NumberFormat.getCurrencyInstance().format(tipTotal);

                tv2.setText(tip2);
            }
            else if (rb10.isChecked() ) {
                tipTotal = Menu.total*1.1;
                tip2 = NumberFormat.getCurrencyInstance().format(tipTotal);

                tv2.setText(tip2);
            }
            else if (rb15.isChecked() ) {
                tipTotal = Menu.total*1.15;
                tip2 = NumberFormat.getCurrencyInstance().format(tipTotal);

                tv2.setText(tip2);

            }
            else if (rb20.isChecked() ) {
                tipTotal = Menu.total*1.2;
                tip2 = NumberFormat.getCurrencyInstance().format(tipTotal);

                tv2.setText(tip2);
            }
            else {
                String tip = NumberFormat.getCurrencyInstance().format(Menu.total);
                tv2.setText(tip);
            }
        }
    }
}
