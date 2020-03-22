package com.example.tm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.tm.Database.Database;

public class Pay extends AppCompatActivity {
    Database db;

    private Button chk,py;
    private EditText nm,ph,UptoMonthYear,lastPaidMonth;

    String MYear,Ename,lastPaid;
    //public String [] FeeDetail;
    Long phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        db= new Database(this);

        chk= (Button)findViewById(R.id.Check);
        py= (Button)findViewById(R.id.pay);

        nm=(EditText)findViewById(R.id.Name);
        ph=(EditText)findViewById(R.id.Phone);
        UptoMonthYear=(EditText)findViewById(R.id.MonthYear);
        lastPaidMonth=(EditText)findViewById(R.id.LastPaid);

        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ename=nm.getText().toString().trim();
                phone=Long.parseLong(ph.getText().toString().trim());
                Check(Ename,phone);
            }
        });

        py.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*
               *place MYear here only otherwise this will always be empty
               */
                MYear =UptoMonthYear.getText().toString().trim();
                db.updateFee(Ename,phone,MYear);

            }
        });

    }


    private void Check(String name,Long phone){
        db.getWritableDatabase();

        if(ifRecordExists(name,phone)) {

            String start[]= db.FeeData(name, phone);
            lastPaid=start[2];

           if(lastPaid.equals("null"))
               lastPaidMonth.setText("No Fee Paid Yet");
           else
               lastPaidMonth.setText(lastPaid);
            }
        }



    public final  boolean ifRecordExists(String name,Long ph){
        String[] FeeDetail = db.FeeData(name,ph);
        Long p=Long.parseLong(FeeDetail[1].trim());
        if(!(name.equals(FeeDetail[0]) && ph==p)){
          Toast.makeText(this,"Student is not registered",Toast.LENGTH_SHORT).show();
          return false;}
          Toast.makeText(this,"Student is  registered",Toast.LENGTH_SHORT).show();
        return true;
    }

}
