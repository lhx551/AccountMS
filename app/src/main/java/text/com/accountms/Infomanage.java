package text.com.accountms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import Dao.*;
import model.*;

public class Infomanage extends AppCompatActivity {
    protected static final int DATE_DIALOG_ID=0;
    TextView tvtitle,textView;
    EditText txtMoney,txtTime,txtHA,txtMark;
    Spinner spType;
    Button btnEdit,btnDel;
    String[] strInfos;
    String strid,strType;
    private int mYear;
    private int mMonth;
    private int mDay;
    outaccountDAO outaccountDAO=new outaccountDAO(Infomanage.this);
    InaccountDAO inaccountDAO=new InaccountDAO(Infomanage.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infomanage);

        tvtitle=(TextView)findViewById(R.id.inouttitle);
        textView=(TextView)findViewById(R.id.tvInOut);
        txtMoney=(EditText)findViewById(R.id.txtInOutMoney);
        txtTime=(EditText)findViewById(R.id.txtInOutTime);
        spType=(Spinner)findViewById(R.id.spInOutType);
        txtHA=(EditText)findViewById(R.id.txtInOut);
        txtMark=(EditText)findViewById(R.id.txtInOutMark);
        btnEdit=(Button)findViewById(R.id.btnInOutEdit);
        btnDel=(Button)findViewById(R.id.btnInOutDelete);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        strInfos=bundle.getStringArray(Showinfo.FLAG);
        strid=strInfos[0];
        strType=strInfos[1];
        if(strType.equals("btnoutinfo")){
            tvtitle.setText("支出管理");
            textView.setText("地  点：");
            Tb_outaccount tb_outaccount=outaccountDAO.find(Integer.parseInt(strid));
            txtMoney.setText(String.valueOf(tb_outaccount.getMoney()));
            txtTime.setText(tb_outaccount.getTime());
            spType.setPrompt(tb_outaccount.getType());
            txtHA.setText(tb_outaccount.getAddress());
            txtMark.setText(tb_outaccount.getMark());

        }
        else if(strType.equals("btnininfo")){
            tvtitle.setText("收入管理");
            textView.setText("付款方： ");
            Tb_inaccount tb_inaccount=inaccountDAO.find(Integer.parseInt(strid));
            txtMoney.setText(String.valueOf(tb_inaccount.getMoney()));
            txtTime.setText(tb_inaccount.getTime());
            spType.setPrompt(tb_inaccount.getType());
            txtHA.setText(tb_inaccount.getHandler());
            txtMark.setText(tb_inaccount.getMark());
        }
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (strType.equals("btnoutinfo")){
                    Tb_outaccount tb_outaccount=new Tb_outaccount();
                    tb_outaccount.setid(Integer.parseInt(strid));
                    tb_outaccount.setMoney(Double.parseDouble(txtMoney.getText().toString()));
                    tb_outaccount.setTime(txtTime.getText().toString());
                    tb_outaccount.setType(spType.getSelectedItem().toString());
                    tb_outaccount.setAddress(txtHA.getText().toString());
                    tb_outaccount.setMark(txtMark.getText().toString());
                    outaccountDAO.update(tb_outaccount);
                }
                else if (strType.equals("btnininfo")){
                    Tb_inaccount tb_inaccount=new Tb_inaccount();
                    tb_inaccount.setid(Integer.parseInt(strid));
                    tb_inaccount.setMoney(Double.parseDouble(txtMoney.getText().toString()));
                    tb_inaccount.setTime(txtTime.getText().toString());
                    tb_inaccount.setType(spType.getSelectedItem().toString());
                    tb_inaccount.setHandler(txtHA.getText().toString());
                    tb_inaccount.setMark(txtMark.getText().toString());
                    inaccountDAO.update(tb_inaccount);
                }
                Toast.makeText(Infomanage.this,"【数据】修改成功！",Toast.LENGTH_SHORT).show();
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (strType.equals("btnoutinfo")){
                    outaccountDAO.delete(Integer.parseInt(strid));
                }
                else if (strType.equals("btnininfo")){
                    inaccountDAO.delete(Integer.parseInt(strid));
                }
                Toast.makeText(Infomanage.this,"【数据】删除成功！",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
