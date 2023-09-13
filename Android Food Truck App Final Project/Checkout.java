package com.example.machinegunkellyv2;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Checkout extends AppCompatActivity {
    public static int orderNumber = 49;
    private ScrollView scrollView;

    protected void onCreate( Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_screen);

        // gets references to views and keeps count of the current order number
        TextView orderTracker = findViewById(R.id.order_counter);
        orderTracker.setText("Order #" + orderNumber);

        TextView tv = findViewById(R.id.checkout_total);
        String temp = tv.getText() + "";
        tv.setText(temp + Order.tip2);

        scrollView = findViewById(R.id.checkout_scroll);
        scrollView.removeAllViewsInLayout();

        // we parse the large order string we have, delimiting on the ',' that seperates each
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


    }

    // intent to proceed to Final, we clear values and increment order number
    public void goToFinal(View v) {
        Intent finishIntent = new Intent(this, Final.class);
        startActivity(finishIntent);
        if ( orderNumber > 50) { orderNumber = 1; }
        else { orderNumber++; }
        Menu.total = 0.0;
        Menu.orderString = "";
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    // intent to go back to Order
    public void goBackToOrder(View v) {
        Intent finishIntent = new Intent(this, Order.class);
        startActivity(finishIntent);
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }

    // intent to cancel the order and move to Start
    public void cancelToStart(View v) {
        Intent finishIntent = new Intent(this, MainActivity.class);
        startActivity(finishIntent);
        Menu.total = 0.0;
        Menu.orderString = "";
        overridePendingTransition(R.anim.bounce,R.anim.hold);
//        Order.mediaPlayer3.stop();
    }
}
