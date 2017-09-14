package text.com.accountms;
/**
 * 便签管理（修改、删除）
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Dao.flagDAO;
import model.Tb_flag;

public class EditflagActivity extends AppCompatActivity {

    EditText txtFlag;
    Button btnEdit,btnDel;
    String strid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editflag);

        txtFlag = (EditText)findViewById(R.id.txtFlagManage);
        btnEdit = (Button)findViewById(R.id.btnFlagManageEdit);
        btnDel = (Button)findViewById(R.id.btnFlagManageDelet);

        Intent intent = new Intent();
        Bundle bundle = intent.getExtras();//获取便签id
        strid = bundle.getString("FLAG");//将便签id转换为字符串
        final flagDAO flagDAO = new flagDAO(EditflagActivity.this);
        txtFlag.setText(flagDAO.find(Integer.parseInt(strid)).getFlag());//根据便签id查找便签信息，并显示在文本框中

        //"修改"按钮点击事件
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tb_flag tb_flag = new Tb_flag();
                tb_flag.setid(Integer.parseInt(strid));//设置便签id
                tb_flag.setFlag(txtFlag.getText().toString());//设置便签值
                flagDAO.update(tb_flag);//修改便签信息
                Toast.makeText(EditflagActivity.this,"[便签数据]修改成功！",Toast.LENGTH_SHORT).show();
            }
        });

        //"删除"按钮点击事件
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagDAO.delete(Integer.parseInt(strid));//根据指定的id删除便签信息
                Toast.makeText(EditflagActivity.this,"[便签数据]删除成功！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
