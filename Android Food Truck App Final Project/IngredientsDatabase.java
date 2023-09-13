package com.example.machinegunkellyv2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import android.util.Log;

public class IngredientsDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME =
            "ingDB";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_ING = "Ing";
    private static final String QUANTITY = "Quantity" ;
    private static final String NAME = "Name" ;
    private static final String ID = "ID";

    public IngredientsDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // other constants for column names
    public void onCreate(SQLiteDatabase db) {
        // build sql create statement
        String sqlCreate = "create table " + TABLE_ING +
                "( " + ID;
        sqlCreate += " integer primary key autoincrement, " +
                NAME;
        sqlCreate += " text, " + QUANTITY + " real )";
        db.execSQL(sqlCreate);
    }

    // drop candy table, recreate it
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop old table if it exists
        db.execSQL("drop table if exists " + TABLE_ING);
// Re-create table(s)
        onCreate(db);
    }
    public void insert(Ingredients i) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " +
                TABLE_ING;
        sqlInsert += " values( null, '" + i.getName( );
        sqlInsert += "', '" + i.getQuantity( ) + "' )";
        db.execSQL( sqlInsert );
        db.close( );
        ArrayList<Ingredients> ing = selectAll( );
        for( Ingredients in : ing )
            Log.w( "MainActivity", "ing = " + in.toString( ) );
    }

    public void updateById(int id, String name, int quant){
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "UPDATE " +
                TABLE_ING;
        sqlInsert += " SET NAME = \"" + name + "\"";
        sqlInsert += ", QUANTITY = " + quant;
        sqlInsert += " WHERE ID = " + id + ";";
        db.execSQL( sqlInsert );
        db.close( );
    }


    public void deleteById(int id){
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "DELETE FROM " +
                TABLE_ING;
        sqlInsert += " WHERE ID=" + id;
        db.execSQL( sqlInsert );
        db.close( );
    }
    public Ingredients selectById( int id ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        // construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_ING;
        sqlQuery += " where " + ID + " = " + id;
        // call rawQuery to execute the select query
        Cursor cursor = db.rawQuery( sqlQuery, null );
        // process the results
        // build a Candy object, then return it
        // construct sqlQuery, a select query

// process the result of the query
        Ingredients ing = null;
        if( cursor.moveToFirst( ) )
            ing = new Ingredients( cursor.getString( 0 )
                    , Integer.parseInt(cursor.getString( 1 ) ));
        return ing;

    }

    public int nameToQuant( String n ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        // construct sqlQuery, a select query
        String sqlQuery = "select "+QUANTITY +" from " + TABLE_ING;
        sqlQuery += " where " + NAME + " = " + "'" +n + "'";
        // call rawQuery to execute the select query
        Cursor cursor = db.rawQuery( sqlQuery, null );

// process the result of the query
        if( cursor.moveToFirst( ) ) {
            db.close();
            return Integer.parseInt(cursor.getString(0));

        }
        db.close();
        return 0;

    }
    public void decByName( String n ) {
        int newQuant = nameToQuant(n)-1;
        SQLiteDatabase db = this.getWritableDatabase( );

        String sqlInsert = "UPDATE " +
                TABLE_ING;
        sqlInsert += " SET QUANTITY = \"" + newQuant + "\"";
        sqlInsert += " WHERE NAME = " + "'"+n+"'" + ";";
        System.out.println(sqlInsert);
        db.execSQL( sqlInsert );
        db.close();
    }

    public ArrayList<Ingredients> selectAll( ) {
        // select all the rows in the candy table
        // return an ArrayList of Candy objects
        SQLiteDatabase db = this.getWritableDatabase( );
        // construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_ING;
        // call rawQuery to execute the select query
        Cursor cursor = db.rawQuery( sqlQuery, null );
        ArrayList<Ingredients> candies = new ArrayList<Ingredients>( );

        while( cursor.moveToNext( ) ) {
            Ingredients currentIng = new Ingredients( cursor.getString( 1 ) , cursor.getInt( 2 ) );
            candies.add( currentIng );
        }
        db.close( );
        return candies;

    }


}