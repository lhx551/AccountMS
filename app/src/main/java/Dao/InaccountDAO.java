package Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Tb_inaccount;

/**
 * Created by ios01 on 17/9/13.
 */

public class InaccountDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    public InaccountDAO(Context context) {
        helper=new DBOpenHelper(context);
    }
    /**
     * 添加收入信息
     */
    public void add(Tb_inaccount tb){
        db=helper.getWritableDatabase();
        db.execSQL("insert into tb_inaccount (_id,money,time,type,handler,mark) values (?,?,?,?,?,?)",new Object[]{tb.getid(),tb.getMoney(),tb.getTime(),tb.getType(),tb.getHandler(),tb.getMark()});

    }
    /**
     * 更新收入信息
     */
    public void update(Tb_inaccount tb){
        db=helper.getWritableDatabase();
        db.execSQL("update tb_inaccount set money=?,time=?,type=?,handler=?,mark=? where _id=?",new Object[]{tb.getMoney(),tb.getTime(),tb.getType(),tb.getHandler(),tb.getMark(),tb.getid()});
    }
    /**
     * 查找收入信息
     */
    public Tb_inaccount find(int id){
        db=helper.getWritableDatabase();
        Cursor cu=db.rawQuery("select _id,money,time,type,handler,mark from tb_inaccount where _id=?",new String[]{String.valueOf(id)});
        if (cu.moveToNext()){
            return new Tb_inaccount(cu.getInt(cu.getColumnIndex("_id")),cu.getDouble(cu.getColumnIndex("money")),cu.getString(cu.getColumnIndex("time")),cu.getString(cu.getColumnIndex("type")),cu.getString(cu.getColumnIndex("handler")),cu.getString(cu.getColumnIndex("mark")));
        }
        return null;
    }
    /**
     * 删除收入信息
     */
    public void delete(Integer[] ids){
        if(ids.length>0){
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<ids.length;i++){
                sb.append('?').append(',');
            }
            sb.deleteCharAt(sb.length()-1);
            db=helper.getWritableDatabase();
            db.execSQL("delete from tb_inaccount where _id in ("+sb+") ",(Object[])ids);
        }
    }
    /**
     * 获取收入信息
     * @param start 起始位置
     * @param count 每页显示数量
     */
    public List<Tb_inaccount> getScrollData(int start,int count){
        List<Tb_inaccount> tb=new ArrayList<Tb_inaccount>();
        db=helper.getWritableDatabase();
        Cursor cu=db.rawQuery("select * from tb_inaccount limit ?,?",new String[]{String.valueOf(start),String.valueOf(count)});
        while (cu.moveToNext()){
            tb.add(new Tb_inaccount(cu.getInt(cu.getColumnIndex("_id")),cu.getDouble(cu.getColumnIndex("money")),cu.getString(cu.getColumnIndex("time")),cu.getString(cu.getColumnIndex("type")),cu.getString(cu.getColumnIndex("handler")),cu.getString(cu.getColumnIndex("mark"))));
        }
        return tb;
    }
    /**
     * 获取总记录数
     */
    public long getCount(){
        db=helper.getWritableDatabase();
        Cursor cu=db.rawQuery("select count(_id) from tb_inaccount",null);
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
        Cursor cu=db.rawQuery("select max(_id) from tb_inaccount",null);
        while (cu.moveToNext()){
            return cu.getInt(0);
        }
        return  0;
    }
}
