package Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Tb_outaccount;

/**
 * Created by ios01 on 17/9/13.
 */

public class outaccountDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    public outaccountDAO(Context context) {
        helper=new DBOpenHelper(context);
    }
    /**
     * 添加支出信息
     */
    public void add(Tb_outaccount tb){
        db=helper.getWritableDatabase();
        db.execSQL("insert into tb_outaccount (_id,money,time,type,address,mark) values (?,?,?,?,?,?)",new Object[]{tb.getid(),tb.getMoney(),tb.getTime(),tb.getType(),tb.getAddress(),tb.getMark()});

    }
    /**
     * 更新支出信息
     */
    public void update(Tb_outaccount tb){
        db=helper.getWritableDatabase();
        db.execSQL("update tb_outaccount set money=?,time=?,type=?,address=?,mark=? where _id=?",new Object[]{tb.getMoney(),tb.getTime(),tb.getType(),tb.getAddress(),tb.getMark(),tb.getid()});
    }
    /**
     * 查找支出信息
     */
    public Tb_outaccount find(int id){
        db=helper.getWritableDatabase();
        Cursor cu=db.rawQuery("select _id,money,time,type,address,mark from tb_outaccount where _id=?",new String[]{String.valueOf(id)});
        if (cu.moveToNext()){
            return new Tb_outaccount(cu.getInt(cu.getColumnIndex("_id")),cu.getDouble(cu.getColumnIndex("money")),cu.getString(cu.getColumnIndex("time")),cu.getString(cu.getColumnIndex("type")),cu.getString(cu.getColumnIndex("address")),cu.getString(cu.getColumnIndex("mark")));
        }
        return null;
    }
    /**
     * 删除支出信息
     */
    public void delete(int ids){
        db=helper.getWritableDatabase();
        db.execSQL("delete from tb_outaccount where _id=?",new String[]{String.valueOf(ids)});
    }
    /**
     * 获取支出信息
     * @param start 起始位置
     * @param count 每页显示数量
     */
    public List<Tb_outaccount> getScrollData(int start,int count){
        List<Tb_outaccount> tb=new ArrayList<Tb_outaccount>();
        db=helper.getWritableDatabase();
        Cursor cu=db.rawQuery("select * from tb_ouaccount limit ?,?",new String[]{String.valueOf(start),String.valueOf(count)});
        while (cu.moveToNext()){
            tb.add(new Tb_outaccount(cu.getInt(cu.getColumnIndex("_id")),cu.getDouble(cu.getColumnIndex("money")),cu.getString(cu.getColumnIndex("time")),cu.getString(cu.getColumnIndex("type")),cu.getString(cu.getColumnIndex("address")),cu.getString(cu.getColumnIndex("mark"))));
        }
        return tb;
    }
    /**
     * 获取总记录数
     */
    public long getCount(){
        db=helper.getWritableDatabase();
        Cursor cu=db.rawQuery("select count(_id) from tb_outaccount",null);
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
        Cursor cu=db.rawQuery("select max(_id) from tb_outaccount",null);
        while (cu.moveToNext()){
            return cu.getInt(0);
        }
        return  0;
    }
}
