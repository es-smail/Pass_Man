package com.example.newproject.methods;


import static java.util.Collections.sort;
import java.sql.Timestamp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.Collections;

public class DatabaseSqlite extends SQLiteOpenHelper {
    public static final String DatabaseName="Pass_Man.db";

    public DatabaseSqlite(Context con){
        super(con,DatabaseName,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table login  (id INTEGER primary key AUTOINCREMENT,title TEXT,email TEXT,password TEXT,date_l TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
        db.execSQL("create table cart  (id INTEGER primary key AUTOINCREMENT,name TEXT,type TEXT,number INTEGER,cvc INTEGER,date INTEGER,pin INTEGER,date_c TIMESTAMP DEFAULT CURRENT_TIMESTAMP,expiry INTEGER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS login");
        db.execSQL("DROP TABLE IF EXISTS cart");
        onCreate(db);
    }
    ////////////////////////////////////// Login Add
    public String  Insert_login(String t,String e,String p){
        SQLiteDatabase l= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("title",t);
        contentValues.put("email",e);
        contentValues.put("password",p);
         long r=l.insert("login",null,contentValues);
         if (r ==-1){
             return "ERROR !!!";
         }else return "add_login Valid";
    }
    ////////////////////////////////////// Cart Add
    public String  Insert_cart(String n,String t,int nu,int cvc,int date,int pin,int expiry){
        SQLiteDatabase c= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name",n);
        contentValues.put("type",t);
        contentValues.put("number",nu);
        contentValues.put("cvc",cvc);
        contentValues.put("date",date);
        contentValues.put("pin",pin);
        contentValues.put("expiry",expiry);
        long r=c.insert("cart",null,contentValues);
        if (r ==-1){
            return "ERROR !!!";
        }else return "add_cart Valid";
    }


    public ArrayList<item> getAllItems() {
        ArrayList<item> itemList = new ArrayList<item>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM login", null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false) {
            itemList.add(new UserLogin(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    Timestamp.valueOf(cursor.getString(4))
                    
            ));
            cursor.moveToNext();
        }
        cursor.close();
            Cursor cursor1 = db.rawQuery("SELECT * FROM cart", null);
            cursor1.moveToFirst();

            while (cursor1.isAfterLast()==false){
            itemList.add(new UserCart(cursor1.getInt(0),
                    cursor1.getString(1),
                    cursor1.getString(2),
                    cursor1.getInt(3),
                    cursor1.getInt(4),
                    cursor1.getInt(5),
                    cursor1.getInt(6),
                    Timestamp.valueOf(cursor1.getString(7)),
                    cursor1.getInt(8)

                      ));
            cursor1.moveToNext();
        }
            cursor1.close();
            db.close();
        Collections.sort(itemList);
        return itemList;

    }
  public void Update_Login(String id,String t,String e,String p){
        SQLiteDatabase sq=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",t);
        values.put("email",e);
        values.put("password",p);
        sq.update("login",values,"id=?",new String[]{id});

  }
    public void Update_Cart(String id,String n,String t,int nu,int cvc,int date,int pin,int expiry){
        SQLiteDatabase sq=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",n);
        values.put("type",t);
        values.put("number",nu);
        values.put("cvc",cvc);
        values.put("date",date);
        values.put("pin",pin);
        values.put("expiry",expiry);
        sq.update("cart",values,"id=?",new String[]{id});
    }

    public void delete_Login(String id){
        SQLiteDatabase sq=this.getWritableDatabase();
        sq.delete("login","id=?",new String[]{id});
    }
    public void delete_Cart(String id){
        SQLiteDatabase sq=this.getWritableDatabase();
        sq.delete("cart","id=?",new String[]{id});
    }
//    public UserLogin Get_login(String id_login) {
//        UserLogin l =  null;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM login WHERE id = id_login", new String[] { id_login });
//
//
//        if (cursor.moveToFirst()) {
//            l = (new UserLogin(cursor.getInt(0),
//                    cursor.getString(1),
//                    cursor.getString(2),
//                    cursor.getString(3),
//                    Timestamp.valueOf(cursor.getString(4))
//
//            ));
//        }
//        cursor.close();
//        return l;
//
//    }
}







