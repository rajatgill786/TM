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
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4,radioButton5,radioButton6,
            radioButton7,radioButton8,radioButton9,radioButton10,radioButton11,radioButton12;
    RadioButton radioButton []={radioButton1,radioButton2,radioButton3,radioButton4,radioButton5,radioButton6,
            radioButton7,radioButton8,radioButton9,radioButton10,radioButton11,radioButton12};
    private RadioButton [] rbs= new RadioButton[12];

    Button chk,py;
    EditText nm,ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        db= new Database(this);
        radioButton[0]=(RadioButton)findViewById(R.id.radioButton1);
        radioButton[1]=(RadioButton)findViewById(R.id.radioButton2);
        radioButton[2]=(RadioButton)findViewById(R.id.radioButton3);
        radioButton[3]=(RadioButton)findViewById(R.id.radioButton4);
        radioButton[4]=(RadioButton)findViewById(R.id.radioButton5);
        radioButton[5]=(RadioButton)findViewById(R.id.radioButton6);
        radioButton[6]=(RadioButton)findViewById(R.id.radioButton7);
        radioButton[7]=(RadioButton)findViewById(R.id.radioButton8);
        radioButton[8]=(RadioButton)findViewById(R.id.radioButton9);
        radioButton[9]=(RadioButton)findViewById(R.id.radioButton10);
        radioButton[10]=(RadioButton)findViewById(R.id.radioButton11);
        radioButton[11]=(RadioButton)findViewById(R.id.radioButton12);

        chk= (Button)findViewById(R.id.check);
        py= (Button)findViewById(R.id.pay);

        nm=(EditText)findViewById(R.id.Name);
        ph=(EditText)findViewById(R.id.Phone);




        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=nm.getText().toString().trim();
                Long phone=Long.parseLong(ph.getText().toString().trim());
                Check(name,phone);
            }
        });
    }



    private void Check(String name,Long phone){

        db.getWritableDatabase();
        if(ifRecordExists(name,phone)) {
            int month = Integer.parseInt(db.getMonth(name, phone));
            for(int i=0;i<month;i++)
                radioButton[i].setEnabled(false);
            }
        }



    public  boolean ifRecordExists(String name,Long ph){
      String n=db.fetchName(name, ph);
      Long p=db.fetchPh(name, ph);
      if(!(name.equals(n) && ph==p)){
          Toast.makeText(this,"Student is not registered",Toast.LENGTH_SHORT).show();
          return false;}
        Toast.makeText(this,"Student is  registered",Toast.LENGTH_SHORT).show();
      return true;

    }
}
