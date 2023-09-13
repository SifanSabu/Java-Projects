//package com.example.machinegunkellyv2;
//
//import android.os.Bundle;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.PopupWindow;
//import android.widget.RelativeLayout;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class CustomizePopup extends AppCompatActivity {
//
//    View popupView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate( savedInstanceState );
//        setContentView( R.layout.customize_popup);
//    }
//
//    public void onButtonShowPopupWindow(View view) {
//
//        // inflate the layout of the popup window
//        LayoutInflater inflater = (LayoutInflater) getSystemService (LAYOUT_INFLATER_SERVICE);
//        popupView = inflater.inflate( R.layout.customize_popup, null);
//
//        // create the popup window
//        int width = RelativeLayout.LayoutParams.WRAP_CONTENT;
//        int height = RelativeLayout.LayoutParams.WRAP_CONTENT;
//        boolean focusable = true; // lets taps outside the popup dismiss it
//        final PopupWindow popupWindow = new PopupWindow( popupView, width, height, focusable );
//
//        // show the popup window
//        // which view you pass in doesn't matter, it is only used for the window token
//        popupWindow.showAtLocation( view, Gravity.CENTER, 0, 0);
//
//        // dismiss the window when touched
//        popupView.setOnTouchListener( new View.OnTouchListener() {
//            public boolean onTouch( View v, MotionEvent event) {
//                popupWindow.dismiss();
//                return true;
//            }
//        });
//    }
//}
