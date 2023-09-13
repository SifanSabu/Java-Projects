package com.example.machinegunkellyv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Final extends AppCompatActivity {

    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_screen);
    }

    // intent to move to Start
    public void goToStart(View v){
        Intent startIntent = new Intent(this, MainActivity.class );
        startActivity(startIntent);
        overridePendingTransition(R.anim.bounce,R.anim.hold);
//        Order.mediaPlayer3.stop();
    }
}
