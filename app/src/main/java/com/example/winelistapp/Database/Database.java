package com.example.winelistapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.winelistapp.Model.Winelist;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME="WinelistAppDB.db";
    private static final int DB_VER=1;


    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Winelist> getCarts(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ID", "WineLabel", "WineId", "Quantity", "Price", "Discount", "Total"};
        String sqlTable = "WinelistDetail";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);

        final List<Winelist> result = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                result.add(new Winelist(
                        c.getInt(c.getColumnIndex("ID")),
                        c.getString(c.getColumnIndex("WineId")),
                        c.getString(c.getColumnIndex("WineLabel")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price")),
                        c.getString(c.getColumnIndex("Discount")),
                        c.getString(c.getColumnIndex("Total"))
                ));
            } while(c.moveToNext());
        }
        return result;
    }

    public void addCart(Winelist winelist){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO WinelistDetail(ID, WineID, WineLabel, Quantity, Price, Discount, Total) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                winelist.getID(),
                winelist.getWineID(),
                winelist.getWineLabel(),
                winelist.getQuantity(),
                winelist.getPrice(),
                winelist.getDiscount(),
                winelist.getTotal());
        db.execSQL(query);
    }

    public void cleanCart(){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM WinelistDetail");
        db.execSQL(query);
    }

    public int getCountCart() {
        int count = 0;
       SQLiteDatabase db = getReadableDatabase();
       String query = String.format("SELECT COUNT(*) FROM WinelistDetail");
       Cursor cursor = db.rawQuery(query, null);
       if(cursor.moveToFirst())
       {
           do {
               count = cursor.getInt(0);
           }while (cursor.moveToNext());
       }
       return count;
    }

    public void removeFromCart(String phone, String WineId){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM WinelistDetail WHERE WineId = '%s'", WineId);
        db.execSQL(query);
    }

    public void updateCart(Winelist winelist){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("UPDATE WinelistDetail SET Discount= $s WHERE ID = %d",winelist.getDiscount(), winelist.getID());
        db.execSQL(query);
    }

}
