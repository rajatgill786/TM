package com.example.tm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.tm.Database.Database;

public class Pay extends AppCompatActivity {
    Database db;

    Button chk,py;
    EditText nm,ph,UptoMonthYear,lastPaidMonth;
    AddStudent CurrrentDate;

    String MYear,name,lastPaid;
    String [] FeeDetail;
    Long phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        db= new Database(this);

        chk= (Button)findViewById(R.id.check);
        py= (Button)findViewById(R.id.pay);

        nm=(EditText)findViewById(R.id.Name);
        ph=(EditText)findViewById(R.id.Phone);
        UptoMonthYear=(EditText)findViewById(R.id.MonthYear);
        lastPaidMonth=(EditText)findViewById(R.id.LastPaid);
        CurrrentDate =new AddStudent();

        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=nm.getText().toString().trim();
                phone=Long.parseLong(ph.getText().toString().trim());
                FeeDetail = db.FeeData(name,phone);
                Check(name,phone);
            }
        });

        py.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*
               *place MYear here only otherwise this will always be empty
               */
                MYear =UptoMonthYear.getText().toString().trim();
                db.updateFee(name,phone,MYear);

            }
        });

    }


    private void Check(String name,Long phone){
        db.getWritableDatabase();

        if(ifRecordExists(name,phone)) {

            String start[]= db.FeeData(name, phone);
            lastPaid=start[2];

           /* int AdmissionDay=Integer.parseInt(start[0]);
            int AdmissionMonth=Integer.parseInt(start[1]);
            String[] CurrDate = CurrrentDate.getCurrentDate().split("-");
            int CurrentDay=Integer.parseInt(CurrDate[0]);
            int CurrentMonth=Integer.parseInt(CurrDate[1]);*/
           if(lastPaid.equals("null"))
               lastPaidMonth.setText("No Fee Paid Yet");
           else
               lastPaidMonth.setText(lastPaid);
            }
        }



    public  boolean ifRecordExists(String name,Long ph){
        Long p=Long.parseLong(FeeDetail[1]);
      if(!(name.equals(FeeDetail[0]) && ph==p)){
          Toast.makeText(this,"Student is not registered",Toast.LENGTH_SHORT).show();
          return false;}
        Toast.makeText(this,"Student is  registered",Toast.LENGTH_SHORT).show();
      return true;

    }

}
