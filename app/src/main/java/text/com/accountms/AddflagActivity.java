package text.com.accountms;

/**
 * 添加便签信息
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import Dao.flagDAO;
import model.Tb_flag;

public class AddflagActivity extends AppCompatActivity {

    EditText txtFlag;//创建EditText组件对象
    Button btnflagSaveButton;//创建Button组件对象
    Button btnflagCancelButton;//创建Button组件对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addflag);

        txtFlag = (EditText)findViewById(R.id.txtFlag);
        btnflagSaveButton = (Button)findViewById(R.id.btnflagSave);
        btnflagCancelButton = (Button)findViewById(R.id.btnflagCancel);

        //"保存"按钮点击事件
        btnflagSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strFlag = txtFlag.getText().toString();//获取文本框的值
                if(!strFlag.isEmpty()){
                    flagDAO flagDAO = new flagDAO(AddflagActivity.this);//创建FlagDAO对象
                    Tb_flag tb_flag = new Tb_flag(flagDAO.getMaxid()+1,strFlag);//创建Tb_flag对象
                    flagDAO.add(tb_flag);//添加便签信息
                    //弹出提示框
                    Toast.makeText(AddflagActivity.this,"[新增便签]数据添加成功！",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddflagActivity.this,"请输入便签",Toast.LENGTH_SHORT).show();
                }
            }
        });


        //"取消"按钮点击事件
        btnflagCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtFlag.setText("");//清空便签文本框
            }
        });
    }
}
