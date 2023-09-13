package com.example.machinegunkellyv2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import android.util.Log;
import android.util.Printer;

import org.w3c.dom.CDATASection;

public class OrderDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME =
            "ordDB";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_ORD = "Ord";
    private static final String QUANTITY = "Quantity" ;
    private static final String NAME = "Name" ;
    private static final String ADD = "Add-On" ;
    private static final String DRINK = "Drink" ;

    private static final String ID = "ID";

    public OrderDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // other constants for column names
    public void onCreate(SQLiteDatabase db) {
        // build sql create statement
        String sqlCreate = "create table " + TABLE_ORD +
                "( " + ID;
        sqlCreate += " integer primary key autoincrement, " +
                NAME;
        sqlCreate += " text, " + QUANTITY ;
        sqlCreate += " text, " + ADD ;
        sqlCreate += " text, " + DRINK + " text )" ;


        db.execSQL(sqlCreate);
    }

    // drop candy table, recreate it
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop old table if it exists
        db.execSQL("drop table if exists " + TABLE_ORD);
// Re-create table(s)
        onCreate(db);
    }
    public void insert(String n, String q, String a, String d) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " +
                TABLE_ORD;
        sqlInsert += " values( null, '" + n;
        sqlInsert += "', '" + q;
        sqlInsert += "', '" + a;
        sqlInsert += "', '" + d+ "' )";

        db.execSQL( sqlInsert );
        db.close( );
        ArrayList<String> ing = selectAll( );
        for( String in : ing )
            Log.w( "MainActivity",  in );
    }




    public void deleteById(String ides){
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "DELETE FROM " +
                TABLE_ORD;
        sqlInsert += " WHERE ID=" + ides;
        db.execSQL( sqlInsert );
        db.close( );

    }





    public ArrayList<String> selectAll( ) {
        // select all the rows in the candy table
        // return an ArrayList of Candy objects
        SQLiteDatabase db = this.getWritableDatabase( );
        // construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_ORD;
        // call rawQuery to execute the select query
        Cursor cursor = db.rawQuery( sqlQuery, null );
        ArrayList<String> candies = new ArrayList<String>( );

        while( cursor.moveToNext( ) ) {
            candies.add( cursor.getString(0) );
            candies.add( cursor.getString(1) );
            candies.add( cursor.getString(2) );
            candies.add( cursor.getString(3) );
        }
        db.close( );
        return candies;

    }


}