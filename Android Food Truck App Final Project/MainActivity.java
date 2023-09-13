package com.example.machinegunkellyv2;



import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    public String key = "Colson Baker";
    View popupView;
    public static MediaPlayer mediaPlayer1;
//    public static boolean playFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

//        playFlag = false;
        mediaPlayer1 = MediaPlayer.create(this, R.raw.exbf);
        if( ! (mediaPlayer1.isPlaying()) ) {
            mediaPlayer1.start();
            mediaPlayer1.setLooping(true);
        }
        else {
            mediaPlayer1.reset();
        }
    }

    // intent to move to Menu
    public void goToMenu(View v){
        Intent menuIntent = new Intent(this, Menu.class );
        startActivity( menuIntent );
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
//        mediaPlayer1.stop();
    }

    // intent to move from Menu Manager to Start
    public void goToStart(View v){
        Intent startIntent = new Intent(this, MainActivity.class );
        startActivity(startIntent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void onButtonShowPopupWindow( View view ) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService (LAYOUT_INFLATER_SERVICE);
        popupView = inflater.inflate( R.layout.password_popup, null);

        // create the popup window
        int width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        int height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup dismiss it
        final PopupWindow popupWindow = new PopupWindow( popupView, width, height, focusable );

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation( view, Gravity.CENTER, 0, 0);

        // dismiss the window when touched
        popupView.setOnTouchListener( new View.OnTouchListener() {
            public boolean onTouch( View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    public void checkPassword(View v) {
        // this checks the entry against the key "Colson Baker"
        EditText editText = popupView.findViewById(R.id.pass_enter);
        String entry = "" + editText.getText();
        // if entry is correct, we start intent to move to Menu Manager
        if(entry.equals(key)){
            Intent managerIntent = new Intent(this, MenuManager.class );
            startActivity( managerIntent );
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        }
        else{
            editText.setText("");
        }
    }

}