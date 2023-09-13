package com.example.machinegunkellyv2;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import java.util.ArrayList;
import android.util.Log;

public class MenuDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME =
            "menuDB";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_MENU = "menu";
    private static final String PRICE = "Price" ;
    private static final String QUANTITY = "Quantity" ;
    private static final String NAME = "Name" ;
    private static final String ID = "ID";
    private static final String INGREDIENTS = "ingredients";
    private IngredientsDatabase listOfIng;

    public MenuDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        listOfIng = new IngredientsDatabase(context, "IngDatabase", null, version);

    }

    // other constants for column names
// create candy table
    public void onCreate(SQLiteDatabase db) {
        // build sql create statement
        String sqlCreate = "create table " + TABLE_MENU +
                "( " + ID;
        sqlCreate += " integer primary key autoincrement, " +
                NAME;
        sqlCreate += " text, " + PRICE;
        sqlCreate += " text, " + QUANTITY;
        sqlCreate += " text, " + INGREDIENTS + " text )";

        db.execSQL(sqlCreate);
    }

    // drop candy table, recreate it
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Drop old table if it exists
        db.execSQL("drop table if exists " + TABLE_MENU);
    // Re-create table(s)
        onCreate(db);
    }
    
//    public void clearAndCreate( SQLiteDatabase db) {
//        // Drop old table if it exists
//        db.execSQL("drop table if exists " + TABLE_MENU);
//        // Re-create table(s)
//        onCreate(db);
//    }

    public void insert( Meal_Item dish ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " +
                TABLE_MENU;
        sqlInsert += " values( null, '" + dish.getName( );
        sqlInsert += "', '" + dish.getPrice( ) + "";

        int size = dish.getIngredients().size();
        int min = 200000000;
        for(int i = 0; i < size; i++){
            String ing = dish.getIngredients().get(i).getName();
            int q = listOfIng.nameToQuant(ing);
            if(q < min){
                min = q;
            }

        }
        sqlInsert += "', '" + min + "";
        String a = "";
        for(int i = 0; i < size; i++) {
            a+= dish.getIngredients().get(i).getName() +", ";
        }
        sqlInsert += "', '" + a + "' )";

        Log.d("MenuDatabase", "Inserting into menu table : " + sqlInsert);
        db.execSQL( sqlInsert );
        db.close( );

    }
    public void order(Meal_Item dish) {
        SQLiteDatabase db = this.getWritableDatabase( );
        /*
        String sqlQuery = "select * from " + TABLE_MENU;

        // call rawQuery to execute the select query
        Cursor cursor = db.rawQuery( sqlQuery, null );
        String quant = "0";

        while( cursor.moveToNext( ) ) {
            String currentIng = cursor.getString(1);
            if(currentIng.equals(dish.getName())){
                quant = cursor.getString(3);
                break;

            }
        }

        quant = "" + (Integer.parseInt(quant)-1);
        System.out.println(quant + "THIS FINAALLY WORKS");
        */
        String sqlQuery = "select "+QUANTITY +" from " + TABLE_MENU;
        sqlQuery += " where " + NAME + " = " + "'" +dish.getName() + "'";
        Cursor cursor = db.rawQuery( sqlQuery, null );

        String quant = "";
        if(cursor.moveToFirst()) {
            quant = cursor.getString(0);
        }
        quant = Integer.parseInt(quant)-1+"";
        System.out.println(quant + "THIS WORKD?");

        String sqlInsert = "UPDATE " +
                TABLE_MENU;
        sqlInsert += " SET QUANTITY = \"" + quant + "\"";
        sqlInsert += " WHERE NAME = " + "'"+dish.getName()+"'" + ";";

        db.execSQL( sqlInsert );

        String sqlQuery2 = "select "+INGREDIENTS +" from " + TABLE_MENU;
        sqlQuery2 += " where " + NAME + " = " + "'" +dish.getName() + "'";
        Cursor cursor2 = db.rawQuery( sqlQuery2, null );
        String n ="";
        if(cursor2.moveToFirst()) {
            n = cursor2.getString(0);
            String[] ings = n.split(", ");
            for(String a: ings) {
                listOfIng.decByName(a);
            }
        }
        db.close();




    }

    public String[] getIngredients(Meal_Item dish){
        SQLiteDatabase db = this.getWritableDatabase( );

        String sqlQuery2 = "select "+INGREDIENTS +" from " + TABLE_MENU;
        sqlQuery2 += " where " + NAME + " = " + "'" +dish.getName() + "'";
        Cursor cursor2 = db.rawQuery( sqlQuery2, null );
        String n ="";
        String[] ings = null;
        if(cursor2.moveToFirst()) {
            n = cursor2.getString(0);
            ings = n.split(", ");
            for(String d: ings){
                System.out.println(d);

            }
        }
        return ings;

    }




    public ArrayList<Meal_Item> selectAll( ) {
        // select all the rows in the candy table
        // return an ArrayList of Candy objects
        SQLiteDatabase db = this.getWritableDatabase( );
        // construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_MENU;
        // call rawQuery to execute the select query
        Cursor cursor = db.rawQuery( sqlQuery, null );
        ArrayList<Meal_Item> candies = new ArrayList<Meal_Item>();

        while( cursor.moveToNext( ) ) {
            String currentIng = cursor.getString(1);
            String a = cursor.getString(2);
            a = a.substring(0,a.length()-2);
            Double price = Double.parseDouble(a);
            candies.add( new Meal_Item(currentIng, price,null) );
        }
        db.close( );
        return candies;

    }




}