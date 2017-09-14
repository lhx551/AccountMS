package model;

/**
 * Created by ios01 on 17/9/13.
 */

public class Tb_flag {
    private  int _id;
    private String flag;

    public Tb_flag() {
        super();
    }

    public Tb_flag(int _id, String flag) {
        super();
        this._id = _id;
        this.flag = flag;
    }

    public int getid() {
        return _id;
    }

    public void setid(int _id) {
        this._id = _id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
