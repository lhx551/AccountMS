package Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Tb_pwd;

/**
 * Created by ios01 on 17/9/13.
 */

public class pwdDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    public pwdDAO(Context context) {
        helper=new DBOpenHelper(context);
    }
    /**
     * 添加密码信息
     */
    public void add(Tb_pwd tb){
        db=helper.getWritableDatabase();
        db.execSQL("insert into tb_pwd (password) values (?)",new Object[]{tb.getPassword()});

    }
    /**
     * 更新密码信息
     */
    public void update(Tb_pwd tb){
        db=helper.getWritableDatabase();
        db.execSQL("update tb_pwd set password=?",new Object[]{tb.getPassword()});
    }
    /**
     * 查找密码信息
     */
    public Tb_pwd find(){
        db=helper.getWritableDatabase();
        Cursor cu=db.rawQuery("select * from tb_pwd",null);
        if (cu.moveToNext()){
            return new Tb_pwd(cu.getString(cu.getColumnIndex("password")));
        }
        return null;
    }
    /**
     * 删除密码信息
     */
    public Boolean delete(String password){
        if(password!=""&&password.equals("")){
            return  false;
        }
        else {
            db=helper.getWritableDatabase();
            db.execSQL("delete from tb_pwd where password=? ",new String[]{password});
            return true;
        }
    }

    public long getCount(){
        db=helper.getWritableDatabase();
        Cursor cu=db.rawQuery("select count(password) from tb_pwd",null);
        if(cu.moveToNext()){
            return cu.getLong(0);
        }
        return  0;
    }
}
