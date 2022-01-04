package com.example.foodorderingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodorderingapp.Models.OrdersModel;

import java.util.ArrayList;

public class dbhelper extends SQLiteOpenHelper {

   final static String dbname ="mydatabase.db";
   final static int dbversion = 2;


    public dbhelper(@Nullable Context context) {
        super(context, dbname, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table orders" +
                        "(id integer primary key autoincrement," +
                        "name text," +
                        "phone text," +
                        "price int," +
                        "image int," +
                        "description text," +
                        "foodname text," +
                        "quantity int)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP table if exists orders");
        onCreate(db);
    }

    public boolean insertorder(String name, String phone, int price, int image, String desc, String foodname, int quantity){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("quantity",quantity);
        values.put("description", desc);
        values.put("foodname",foodname);
       long id= database.insert("orders",null,values);

       if(id<=0){
           return false;
       }
       else
       {
           return true;
       }
    }

    public ArrayList<OrdersModel> getorders(){
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,foodname,image,price from orders",null);
        if(cursor.moveToFirst())
        {
            while(cursor.moveToNext()){
                OrdersModel model = new OrdersModel();
                model.setOrdernumbers(cursor.getInt(0)+"");
                model.setSolditemname(cursor.getString(1));
                model.setOrderimage(cursor.getInt(2));
                model.setOrdersampleprice(cursor.getInt(3)+"");

                orders.add(model);

            }
        }
        cursor.close();
        database.close();
        return orders;
    }

       public Cursor getorderbyid(int id)
       {
           SQLiteDatabase database = this.getWritableDatabase();
           Cursor cursor = database.rawQuery("Select *  from orders where id =" + id,null);

           if(cursor != null)
               cursor.moveToFirst();


           return cursor;

       }

    public boolean updateorder(String name, String phone, int price, int image, String desc, String foodname, int quantity, int id){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("quantity",quantity);
        values.put("description", desc);
        values.put("foodname",foodname);
        long row= database.update("orders",values,"id="+id,null);

        if(row<=0){
            return false;
        }
        else
        {
            return true;
        }
    }

    public int deleteorder(String id)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        return  database.delete("orders", "id="+id, null);
    }
}
