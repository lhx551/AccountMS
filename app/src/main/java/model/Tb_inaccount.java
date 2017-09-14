package model;

/**
 * Created by ios01 on 17/9/13.
 */

public class Tb_inaccount {//收入信息

    private  int _id;//收入编号
    private double money;//收入金额
    private String time;//收入时间
    private String type;//收入类别
    private String handler;//付款方
    private String mark;//备注

    public Tb_inaccount() {
        super();
    }

    public Tb_inaccount(int _id, double money, String time, String type, String handler, String mark) {
        super();
        this._id = _id;
        this.money = money;
        this.time = time;
        this.type = type;
        this.handler = handler;
        this.mark = mark;
    }

    public void setid(int _id) {
        this._id = _id;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getid() {
        return _id;
    }

    public double getMoney() {
        return money;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getHandler() {
        return handler;
    }

    public String getMark() {
        return mark;
    }
}
