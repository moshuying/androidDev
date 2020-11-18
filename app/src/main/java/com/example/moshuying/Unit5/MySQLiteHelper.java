package com.example.moshuying.Unit5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private Context sqlContext;
    public MySQLiteHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context, name, factory, version);
        sqlContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(id integer primary key autoincrement,userid text,password text,remembered integer)");
        db.execSQL("insert into users(userid,password,remembered) values (?,?,0)",new String[]{"admin","123456"});
        db.execSQL("insert into users(userid,password,remembered) values (?,?,0)",new String[]{"mike","123456"});
        Toast.makeText(sqlContext,"数据库初始化成功！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
