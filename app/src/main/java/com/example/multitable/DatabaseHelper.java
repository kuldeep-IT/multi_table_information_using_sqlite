package com.example.multitable;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

   private static final String DATABASE_NAME = "product_database";
  private static final String TABLE1 = "table1";
   private static final String TABLE2 = "table2";
    private static final String TABLE3 = "table3";



    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String table1 = "CREATE TABLE "+TABLE1 + "(id INTEGER PRIMARY KEY,product TEXT)";
        String table2 = "CREATE TABLE "+TABLE2 + "(id INTEGER PRIMARY KEY,qty TEXT)";
        String table3 = "CREATE TABLE "+TABLE3 + "(id INTEGER PRIMARY KEY,price TEXT)";
        db.execSQL(table1);
        db.execSQL(table2);
        db.execSQL(table3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE3);

        onCreate(db);

    }

    public boolean insert(String product,String qty,String price)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues values1=new ContentValues();
        values1.put("product",product);
        sqLiteDatabase.insert(TABLE1,null,values1);

        ContentValues values2=new ContentValues();
        values2.put("qty",qty);
        sqLiteDatabase.insert(TABLE2,null,values2);

        ContentValues values3=new ContentValues();
        values3.put("price",price);
        sqLiteDatabase.insert(TABLE3,null,values3);

        return true;

    }

    public ArrayList getProduct()
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();

        ArrayList<String> arrayList= new ArrayList<>();

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE1,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            arrayList.add(cursor.getString(cursor.getColumnIndex("product")));
            cursor.moveToNext();
        }
        return arrayList;
    }

    public ArrayList getQty()
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();

        ArrayList<String> arrayList= new ArrayList<>();

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE2,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            arrayList.add(cursor.getString(cursor.getColumnIndex("qty")));
            cursor.moveToNext();
        }
        return arrayList;
    }

    public ArrayList getPrice()
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();

        ArrayList<String> arrayList= new ArrayList<>();

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE3,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            arrayList.add(cursor.getString(cursor.getColumnIndex("price")));
            cursor.moveToNext();
        }
        return arrayList;
    }
}
