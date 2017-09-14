package Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DBNAME = "account.db";
    public static final int VERSION = 1;

    //必须要有构造函数
    public DBOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    // 当第一次创建数据库的时候，调用该方法
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tb_outaccount(_id integer primary key,money decimal,time varchar(10),type varchar(10),address varchar(100),mark varchar(200)");
        db.execSQL("create table tb_inaccount(_id integer primary key,money decimal,time varchar(10),type varchar(10),handler varchar(100),mark varchar(200)");
        db.execSQL("create table tb_pwd(password varchar(20)");
        db.execSQL("create table tb_flag(_id integer primary key,flag varchar(200)");


    }

    //当更新数据库的时候执行该方法
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}