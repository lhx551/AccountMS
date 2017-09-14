package text.com.accountms;


import Dao.*;
import model.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.List;

public class Showinfo extends Activity {
    public static final String FLAG = "id";// ∂®“Â“ª∏ˆ≥£¡ø£¨”√¿¥◊˜Œ™«Î«Û¬Î
    Button btnoutinfo, btnininfo, btnflaginfo;// ¥¥Ω®3∏ˆButton∂‘œÛ
    ListView lvinfo;// ¥¥Ω®ListView∂‘œÛ
    String strType = "";// ¥¥Ω®◊÷∑˚¥Æ£¨º«¬ºπ‹¿Ì¿‡–Õ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showinfo);// …Ë÷√≤ºæ÷Œƒº˛

        lvinfo = (ListView) findViewById(R.id.lvinfo);// ªÒ»°≤ºæ÷Œƒº˛÷–µƒListView◊Èº˛
        btnoutinfo = (Button) findViewById(R.id.btnoutinfo);// ªÒ»°≤ºæ÷Œƒº˛÷–µƒ÷ß≥ˆ–≈œ¢∞¥≈•
        btnininfo = (Button) findViewById(R.id.btnininfo);// ªÒ»°≤ºæ÷Œƒº˛÷–µƒ ’»Î–≈œ¢∞¥≈•
        btnflaginfo = (Button) findViewById(R.id.btnflaginfo);// ªÒ»°≤ºæ÷Œƒº˛÷–µƒ±„«©–≈œ¢∞¥≈•

        ShowInfo(R.id.btnoutinfo);// ƒ¨»œœ‘ æ÷ß≥ˆ–≈œ¢

        btnoutinfo.setOnClickListener(new OnClickListener() {// Œ™÷ß≥ˆ–≈œ¢∞¥≈•…Ë÷√º‡Ã˝ ¬º˛
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ShowInfo(R.id.btnoutinfo);// œ‘ æ÷ß≥ˆ–≈œ¢
            }
        });

        btnininfo.setOnClickListener(new OnClickListener() {// Œ™ ’»Î–≈œ¢∞¥≈•…Ë÷√º‡Ã˝ ¬º˛
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ShowInfo(R.id.btnininfo);// œ‘ æ ’»Î–≈œ¢
            }
        });
        btnflaginfo.setOnClickListener(new OnClickListener() {// Œ™±„«©–≈œ¢∞¥≈•…Ë÷√º‡Ã˝ ¬º˛
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ShowInfo(R.id.btnflaginfo);// œ‘ æ±„«©–≈œ¢
            }
        });

        lvinfo.setOnItemClickListener(new OnItemClickListener()// Œ™ListViewÃÌº”œÓµ•ª˜ ¬º˛
        {
            // ∏≤–¥onItemClick∑Ω∑®
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String strInfo = String.valueOf(((TextView) view).getText());// º«¬ºµ•ª˜µƒœÓ–≈œ¢
                String strid = strInfo.substring(0, strInfo.indexOf('|'));// ¥”œÓ–≈œ¢÷–Ωÿ»°±‡∫≈
                Intent intent = null;// ¥¥Ω®Intent∂‘œÛ
                if (strType == "btnoutinfo" | strType == "btnininfo") {// ≈–∂œ»Áπ˚ «÷ß≥ˆªÚ’ﬂ ’»Î–≈œ¢
                    intent = new Intent(Showinfo.this, Infomanage.class);//  π”√InfoManage¥∞ø⁄≥ı ºªØIntent∂‘œÛ
                    intent.putExtra(FLAG, new String[] { strid, strType });// …Ë÷√“™¥´µ›µƒ ˝æ›
                } else if (strType == "btnflaginfo") {// ≈–∂œ»Áπ˚ «±„«©–≈œ¢
                    intent = new Intent(Showinfo.this, FlagManage.class);//  π”√FlagManage¥∞ø⁄≥ı ºªØIntent∂‘œÛ
                    intent.putExtra(FLAG, strid);// …Ë÷√“™¥´µ›µƒ ˝æ›
                }
                startActivity(intent);// ÷¥––Intent£¨¥Úø™œ‡”¶µƒActivity
            }
        });
    }

    private void ShowInfo(int intType) {// ”√¿¥∏˘æ›¥´»Îµƒπ‹¿Ì¿‡–Õ£¨œ‘ æœ‡”¶µƒ–≈œ¢
        String[] strInfos = null;// ∂®“Â◊÷∑˚¥Æ ˝◊È£¨”√¿¥¥Ê¥¢ ’»Î–≈œ¢
        ArrayAdapter<String> arrayAdapter = null;// ¥¥Ω®ArrayAdapter∂‘œÛ
        switch (intType) {// “‘intTypeŒ™Ãıº˛Ω¯––≈–∂œ
            case R.id.btnoutinfo:// »Áπ˚ «btnoutinfo∞¥≈•
                strType = "btnoutinfo";// Œ™strType±‰¡ø∏≥÷µ
                outaccountDAO outaccountinfo = new outaccountDAO(Showinfo.this);// ¥¥Ω®OutaccountDAO∂‘œÛ
                // ªÒ»°À˘”–÷ß≥ˆ–≈œ¢£¨≤¢¥Ê¥¢µΩList∑∫–ÕºØ∫œ÷–
                List<Tb_outaccount> listoutinfos = outaccountinfo.getScrollData(0,
                        (int) outaccountinfo.getCount());
                strInfos = new String[listoutinfos.size()];// …Ë÷√◊÷∑˚¥Æ ˝◊Èµƒ≥§∂»
                int i = 0;// ∂®“Â“ª∏ˆø™ º±Í ∂
                for (Tb_outaccount tb_outaccount : listoutinfos) {// ±È¿˙List∑∫–ÕºØ∫œ
                    // Ω´÷ß≥ˆœ‡πÿ–≈œ¢◊È∫œ≥…“ª∏ˆ◊÷∑˚¥Æ£¨¥Ê¥¢µΩ◊÷∑˚¥Æ ˝◊Èµƒœ‡”¶Œª÷√
                    strInfos[i] = tb_outaccount.getid() + "|"
                            + tb_outaccount.getType() + " "
                            + String.valueOf(tb_outaccount.getMoney()) + "‘™     "
                            + tb_outaccount.getTime();
                    i++;// ±Í ∂º”1
                }
                break;
            case R.id.btnininfo:// »Áπ˚ «btnininfo∞¥≈•
                strType = "btnininfo";// Œ™strType±‰¡ø∏≥÷µ
                InaccountDAO inaccountinfo = new InaccountDAO(Showinfo.this);// ¥¥Ω®InaccountDAO∂‘œÛ
                // ªÒ»°À˘”– ’»Î–≈œ¢£¨≤¢¥Ê¥¢µΩList∑∫–ÕºØ∫œ÷–
                List<Tb_inaccount> listinfos = inaccountinfo.getScrollData(0,
                        (int) inaccountinfo.getCount());
                strInfos = new String[listinfos.size()];// …Ë÷√◊÷∑˚¥Æ ˝◊Èµƒ≥§∂»
                int m = 0;// ∂®“Â“ª∏ˆø™ º±Í ∂
                for (Tb_inaccount tb_inaccount : listinfos) {// ±È¿˙List∑∫–ÕºØ∫œ
                    // Ω´ ’»Îœ‡πÿ–≈œ¢◊È∫œ≥…“ª∏ˆ◊÷∑˚¥Æ£¨¥Ê¥¢µΩ◊÷∑˚¥Æ ˝◊Èµƒœ‡”¶Œª÷√
                    strInfos[m] = tb_inaccount.getid() + "|"
                            + tb_inaccount.getType() + " "
                            + String.valueOf(tb_inaccount.getMoney()) + "‘™     "
                            + tb_inaccount.getTime();
                    m++;// ±Í ∂º”1
                }
                break;
            case R.id.btnflaginfo:// »Áπ˚ «btnflaginfo∞¥≈•
                strType = "btnflaginfo";// Œ™strType±‰¡ø∏≥÷µ
                flagDAO flaginfo = new flagDAO(Showinfo.this);// ¥¥Ω®FlagDAO∂‘œÛ
                // ªÒ»°À˘”–±„«©–≈œ¢£¨≤¢¥Ê¥¢µΩList∑∫–ÕºØ∫œ÷–
                List<Tb_flag> listFlags = flaginfo.getScrollData(0,
                        (int) flaginfo.getCount());
                strInfos = new String[listFlags.size()];// …Ë÷√◊÷∑˚¥Æ ˝◊Èµƒ≥§∂»
                int n = 0;// ∂®“Â“ª∏ˆø™ º±Í ∂
                for (Tb_flag tb_flag : listFlags) {// ±È¿˙List∑∫–ÕºØ∫œ
                    // Ω´±„«©œ‡πÿ–≈œ¢◊È∫œ≥…“ª∏ˆ◊÷∑˚¥Æ£¨¥Ê¥¢µΩ◊÷∑˚¥Æ ˝◊Èµƒœ‡”¶Œª÷√
                    strInfos[n] = tb_flag.getid() + "|" + tb_flag.getFlag();
                    if (strInfos[n].length() > 15)// ≈–∂œ±„«©–≈œ¢µƒ≥§∂» «∑Ò¥Û”⁄15
                        strInfos[n] = strInfos[n].substring(0, 15) + "°≠°≠";// Ω´Œª÷√¥Û”⁄15÷Æ∫Ûµƒ◊÷∑˚¥Æ”√°≠°≠¥˙ÃÊ
                    n++;// ±Í ∂º”1
                }
                break;
        }
        //  π”√◊÷∑˚¥Æ ˝◊È≥ı ºªØArrayAdapter∂‘œÛ
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strInfos);
        lvinfo.setAdapter(arrayAdapter);// Œ™ListView¡–±Ì…Ë÷√ ˝æ›‘¥
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();//  µœ÷ª˘¿‡÷–µƒ∑Ω∑®
        ShowInfo(R.id.btnoutinfo);// œ‘ æ÷ß≥ˆ–≈œ¢
    }
}
