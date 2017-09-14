package text.com.accountms;

/**
 * 显示所有便签信息
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import Dao.InaccountDAO;
import Dao.flagDAO;
import Dao.outaccountDAO;
import model.Tb_flag;
import model.Tb_inaccount;
import model.Tb_outaccount;

import static text.com.accountms.R.id.lvinfo;
import static text.com.accountms.R.id.parent;

public class ShowflagActivity extends AppCompatActivity {

    public String strType;
    private ListView lvinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showflag);
        //listview
        lvinfo = (ListView) findViewById(R.id.lvinfo);
        //显示便签信息
        Button btnflaginfo = (Button) findViewById(R.id.btnflaginfo);

        //"便签信息"按钮点击事件
        btnflaginfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowInfo(R.id.btnflaginfo);//显示便签信息
            }
        });

        //当点击ListView列表中的某条便签记录时，为其设置监听事件
        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String srtinfo =String.valueOf(((TextView)view).getText());
                String strid = srtinfo.substring(0,srtinfo.indexOf('|'));
                Intent intent = null;
                if(strType=="btnoutinfo"|strType=="btnininfo")
                {
                    intent = new Intent(ShowflagActivity.this,EditflagActivity.class);
                    intent.putExtra("FLAG",new String[]{strid,strType});
                }
                else if(strType=="btnflaginfo")
                {
                    intent = new Intent(ShowflagActivity.this,EditflagActivity.class);
                    intent.putExtra("FLAG",new String[]{strid,strid});
                }
                startActivity(intent);
            }
        });
    }

    private void ShowInfo(int intType) {//用来根据传入的管理类型显示相应的信息
        String[] strInfos = null;//定义字符串数组，用来存储收入信息
        ArrayAdapter<String> arrayAdapter = null;//创建ArrayAdapter对象
        switch (intType) {

            //支出信息
            case R.id.btnoutinfo:
                strType = "btnoutinfo";
                outaccountDAO outaccountinfo = new outaccountDAO(ShowflagActivity.this);

                //获取所以支出信息，并存储到List泛型集合中
                List<Tb_outaccount> listoutinfos = outaccountinfo.getScrollData(0, (int) outaccountinfo.getCount());
                strInfos = new String[listoutinfos.size()];
                int i = 0;
                for (Tb_outaccount tb_outaccount : listoutinfos) {//遍历List泛型集合
                    //将支出相关信息组合成一个字符串，存储到字符串数组的相应位置
                    strInfos[i] = tb_outaccount.getid() + "|" + tb_outaccount.getType() + ""
                            + String.valueOf(tb_outaccount.getMoney()) + "元    " + tb_outaccount.getTime();
                    i++;
                }
                break;
            //收入信息
            case R.id.btnininfo:
                strType = "btnininfo";
                InaccountDAO inaccountinfo = new InaccountDAO(ShowflagActivity.this);
                List<Tb_inaccount> listinfos = inaccountinfo.getScrollData(0, (int) inaccountinfo.getCount());
                strInfos = new String[listinfos.size()];
                int m = 0;
                for (Tb_inaccount tb_inaccount : listinfos) {//遍历List泛型集合
                    //将收入相关信息组合成一个字符串，存储到字符串数组的相应位置
                    strInfos[m] = tb_inaccount.getid() + "|" + tb_inaccount.getType() + ""
                            + String.valueOf(tb_inaccount.getMoney()) + "元    " + tb_inaccount.getTime();
                    m++;
                }
                break;
            //便签信息
            case R.id.btnflaginfo:
                strType = "btnflaginfo";
                flagDAO flaginfo = new flagDAO(ShowflagActivity.this);
                List<Tb_flag> listFlags = flaginfo.getScrollData(0, (int) flaginfo.getCount());
                strInfos = new String[listFlags.size()];
                int n = 0;
                for (Tb_flag tb_flag : listFlags) {//遍历List泛型集合
                    //将便签相关信息组合成一个字符串，存储到字符串数组的相应位置
                    strInfos[n] = tb_flag.getid() + "|" + tb_flag.getFlag();
                    if (strInfos[n].length() > 15)
                        strInfos[n] = strInfos[n].substring(0, 15) + "......";
                    n++;
                }
                break;
        }
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInfos);
        lvinfo.setAdapter(arrayAdapter);
    }
}
