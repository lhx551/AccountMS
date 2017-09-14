package Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Tb_flag;

/**
 * Created by ios01 on 17/9/13.
 */

public class flagDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    public flagDAO(Context context) {
        helper=new DBOpenHelper(context);
    }
    /**
     * 添加收入信息
     */
    public void add(Tb_flag tb){
        db=helper.getWritableDatabase();
        db.execSQL("insert into tb_flag (_id,flag) values (?,?)",new Object[]{tb.getid(),tb.getFlag()});

    }
    /**
     * 更新收入信息
     */
    public void update(Tb_flag tb){
        db=helper.getWritableDatabase();
        db.execSQL("update tb_flag set flag=? where _id=?",new Object[]{tb.getFlag(),tb.getid()});
    }
    /**
     * 查找收入信息
     */
    public Tb_flag find(int id){
        db=helper.getWritableDatabase();
        Cursor cu=db.rawQuery("select _id,flag from tb_flag where _id=?",new String[]{String.valueOf(id)});
        if (cu.moveToNext()){
            return new Tb_flag(cu.getInt(cu.getColumnIndex("_id")),cu.getString(cu.getColumnIndex("flag")));
        }
        return null;
    }
    /**
     * 删除收入信息
     */
    public void delete(int ids){
        db=helper.getWritableDatabase();
        db.execSQL("delete from tb_flag where _id=?",new String[]{String.valueOf(ids)});
    }
    /**
     * 获取收入信息
     * @param start 起始位置
     * @param count 每页显示数量
     */
    public List<Tb_flag> getScrollData(int start,int count){
        List<Tb_flag> tb=new ArrayList<Tb_flag>();
        db=helper.getWritableDatabase();
        Cursor cu=db.rawQuery("select * from tb_flag limit ?,?",new String[]{String.valueOf(start),String.valueOf(count)});
        while (cu.moveToNext()){
            tb.add(new Tb_flag(cu.getInt(cu.getColumnIndex("_id")),cu.getString(cu.getColumnIndex("flag"))));
        }
        return tb;
    }
    /**
     * 获取总记录数
     */
    public long getCount(){
        db=helper.getWritableDatabase();
        Cursor cu=db.rawQuery("select count(_id) from tb_flag",null);
        if(cu.moveToNext()){
            return cu.getLong(0);
        }
        return  0;
    }
    /**
     * 获取最大编号
     */
    public int getMaxid(){
        db=helper.getWritableDatabase();
        Cursor cu=db.rawQuery("select max(_id) from tb_flag",null);
        while (cu.moveToNext()){
            return cu.getInt(0);
        }
        return  0;
    }
}
