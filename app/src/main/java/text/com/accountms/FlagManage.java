package text.com.accountms;

import Dao.*;
import model.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FlagManage extends Activity {
    EditText txtFlag;// ¥¥Ω®EditText∂‘œÛ
    Button btnEdit, btnDel;// ¥¥Ω®¡Ω∏ˆButton∂‘œÛ
    String strid;// ¥¥Ω®◊÷∑˚¥Æ£¨±Ì æ±„«©µƒid

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flagmanage);// …Ë÷√≤ºæ÷Œƒº˛
        txtFlag = (EditText) findViewById(R.id.txtFlagManage);// ªÒ»°±„«©Œƒ±æøÚ
        btnEdit = (Button) findViewById(R.id.btnFlagManageEdit);// ªÒ»°–ﬁ∏ƒ∞¥≈•
        btnDel = (Button) findViewById(R.id.btnFlagManageDelete);// ªÒ»°…æ≥˝∞¥≈•

        Intent intent = getIntent();// ¥¥Ω®Intent∂‘œÛ
        Bundle bundle = intent.getExtras();// ªÒ»°±„«©id
        strid = bundle.getString(Showinfo.FLAG);// Ω´±„«©id◊™ªªŒ™◊÷∑˚¥Æ
        final flagDAO flagDAO = new flagDAO(FlagManage.this);// ¥¥Ω®FlagDAO∂‘œÛ
        txtFlag.setText(flagDAO.find(Integer.parseInt(strid)).getFlag());// ∏˘æ›±„«©id≤È’“±„«©–≈œ¢£¨≤¢œ‘ æ‘⁄Œƒ±æøÚ÷–

        btnEdit.setOnClickListener(new OnClickListener() {// Œ™–ﬁ∏ƒ∞¥≈•…Ë÷√º‡Ã˝ ¬º˛
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Tb_flag tb_flag = new Tb_flag();// ¥¥Ω®Tb_flag∂‘œÛ
                tb_flag.setid(Integer.parseInt(strid));// …Ë÷√±„«©id
                tb_flag.setFlag(txtFlag.getText().toString());// …Ë÷√±„«©÷µ
                flagDAO.update(tb_flag);// –ﬁ∏ƒ±„«©–≈œ¢
                // µØ≥ˆ–≈œ¢Ã· æ
                Toast.makeText(FlagManage.this, "°º±„«© ˝æ›°Ω–ﬁ∏ƒ≥…π¶£°",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnDel.setOnClickListener(new OnClickListener() {// Œ™…æ≥˝∞¥≈•…Ë÷√º‡Ã˝ ¬º˛
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                flagDAO.delete(Integer.parseInt(strid));
                Toast.makeText(FlagManage.this, "°º±„«© ˝æ›°Ω…æ≥˝≥…π¶£°",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
