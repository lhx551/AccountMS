package text.com.accountms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Inaccountinfo extends AppCompatActivity {

    public static final String FLAG="id";
    ListView lvinfo;
    String strType="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inaccountinfo);

        lvinfo=(ListView)findViewById(R.id.lvinaccountinfo);
        Showinfo(R.id.btnininfo);

        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strInfo=String.valueOf(((TextView)view).getText());
                String strid=strInfo.substring(0,strInfo.indexOf('|'));
                Intent intent=new Intent(Inaccountinfo.this,InfoManage.class);
                intent.putExtra(FLAG,new String[]{strid,strType});
                startActivity(intent);
            }
        });
    }

    private void Showinfo(int intType){
        String[]strInfos=null;
        ArrayAdapter<String>arrayAdapter=null;
        strType="btnininfo";
        inaccountDAO inaccountinfo=new InaccountDAO(Inaccountinfo.this);
        List<Tb_inaccount>listinfos=inaccountinfo.getScrollData(0,(int)inaccountinfo.getCount());
        strInfos=new String[listinfos.size()];
        int m=0;
        for(Tb_inaccount tb_inaccount:listinfos){
            strInfos[m]=tb_inaccount.getid()+"|"+tb_inaccount.getType()+" "+String.valueOf(tb_inaccount.getMoney())+"元"
                    +tb_inaccount.getTime();
            m++;
        }
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strInfos);
    }
}
