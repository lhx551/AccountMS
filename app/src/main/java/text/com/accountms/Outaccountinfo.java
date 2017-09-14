package text.com.accountms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import Dao.*;
import model.*;

public class Outaccountinfo extends AppCompatActivity {

    public static final String FLAG="id";
    ListView lvinfo;
    String strType="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outaccountinfo);

        lvinfo=(ListView)findViewById(R.id.lvoutaccountinfo);
        Showinfo(R.id.btnoutinfo);

        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strInfo=String.valueOf(((TextView)view).getText());
                String strid=strInfo.substring(0,strInfo.indexOf('|'));
                Intent intent=new Intent(Outaccountinfo.this,InfoManage.class);
                intent.putExtra(FLAG,new String[]{strid,strType});
                startActivity(intent);
            }
        });
    }

    private void Showinfo(int intType){
        String[]strInfos=null;
        ArrayAdapter<String> arrayAdapter=null;
        strType="btnoutinfo";
        outaccountDAO outaccountinfo=new outaccountDAO(Outaccountinfo.this);
        List<Tb_outaccount> listinfos=outaccountinfo.getScrollData(0,(int)outaccountinfo.getCount());
        strInfos=new String[listinfos.size()];
        int m=0;
        for(Tb_outaccount tb_outaccount:listinfos){
            strInfos[m]=tb_outaccount.getid()+"|"+tb_outaccount.getType()+" "+String.valueOf(tb_outaccount.getMoney())+"元"
                    +tb_outaccount.getTime();
            m++;
        }
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strInfos);
    }
}

}
