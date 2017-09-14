package text.com.accountms;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Dao.DBOpenHelper;
import Dao.pwdDAO;
import model.Tb_pwd;

public class Login extends AppCompatActivity {
    private EditText txtlogin;
    private Button btnclose;
    private Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        txtlogin=(EditText) findViewById(R.id.txtLogin);
        btnlogin= (Button) findViewById(R.id.btnLogin);
        btnclose= (Button) findViewById(R.id.btnClose);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Login.this,MainActivity.class);
                pwdDAO pwd=new pwdDAO(Login.this);
                if ((pwd.getCount()==0|pwd.find().getPassword().isEmpty())&&txtlogin.getText().toString().isEmpty()){
                    startActivity(intent);
                }
                else {
                    if (pwd.find().getPassword().equals(txtlogin.getText().toString())){
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Login.this,"请输入正确的密码！",Toast.LENGTH_SHORT).show();
                    }
                }
                txtlogin.setText("");
            }
        });
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwdDAO pwd=new pwdDAO(Login.this);
                Tb_pwd tb=new Tb_pwd("123456");
                if(pwd.getCount()==0){
                    pwd.add(tb);
                }
                else{
                    pwd.update(tb);
                }
            }
        });
    }
}
