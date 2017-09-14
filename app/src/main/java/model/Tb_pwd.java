package model;

/**
 * Created by ios01 on 17/9/13.
 */

public class Tb_pwd {
    private String password;

    public Tb_pwd(String password) {
        super();
        this.password = password;
    }

    public Tb_pwd() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
