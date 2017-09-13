package text.com.accountms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class AddInaccount extends AppCompatActivity {

    protected static final int DATE_DIALOG_ID=0;
    EditText txtInMoney,txtInTime,txtInHandler,txtInMark;
    Spinner spInType;
    Button btnInSaveButton;
    Button btnInCancelButton;
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addinaccount);

        txtInMoney=(EditText)findViewById(R.id.txtInMoney);
        txtInTime=(EditText)findViewById(R.id.txtInTime);
        txtInHandler=(EditText)findViewById(R.id.txtInHandler);
        txtInMark=(EditText)findViewById(R.id.txtInMark);
        spInType=(Spinner)findViewById(R.id.spInType);
        btnInCancelButton=(Button)findViewById(R.id.btnInCancel);
        btnInSaveButton=(Button)findViewById(R.id.btnInSave);

        txtInTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        final Calendar c=Calendar.getInstance();
        mYear=c.get(Calendar.YEAR);
        mMonth=c.get(Calendar.MONTH);
        mDay=c.get(Calendar.DAY_OF_MONTH);
        updataDisplay();
    }
    private void updataDisplay(){
        txtInTime.setText(new StringBuilder().append(mYear).append
                ("-").append(mMonth+1).append("-").append(mDay));
    }

}
