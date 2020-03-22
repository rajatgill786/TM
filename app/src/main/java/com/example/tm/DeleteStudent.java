package com.example.tm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tm.Database.Database;

public class DeleteStudent extends AppCompatActivity {
    Button chk,delete;
    EditText name ,phone;
    TextView dueMonths;
    CheckBox DCheck;

    String nm;
    Long phone_no;

    Database db;
    Pay py;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student);
        chk= (Button)findViewById(R.id.Check);
        delete=(Button)findViewById(R.id.Delete);
        name=(EditText)findViewById(R.id.Name) ;
        phone=(EditText)findViewById(R.id.Phone);
        dueMonths=(TextView) findViewById(R.id.DueMonths);
        DCheck=(CheckBox)findViewById(R.id.CheckDelete);

        db=new Database(this);
        py=new Pay();

        delete.setEnabled(false);
        DCheck.setEnabled(false);

        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nm= name.getText().toString().trim();
                phone_no=Long.parseLong(phone.getText().toString().trim());
                if(ifRecordExists(nm,phone_no)) {
                    int DueMonths = db.CheckDues(nm, phone_no);
                    dueMonths.setText("Haven't paid " + DueMonths + " months fee");
                    if (DueMonths == 0)
                        delete.setEnabled(true);
                    else
                        DCheck.setEnabled(true);
               }
              else
                    noRecordToast();
            }

        });

        DCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                delete.setEnabled(b);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.DeleteRecord(nm,phone_no);
                deleteToast();
            }
        });
    }
   void  deleteToast(){
       Toast.makeText(this,"Record Deleted",Toast.LENGTH_SHORT).show();
   }

   void noRecordToast(){
       Toast.makeText(this,"No Such Record Exist",Toast.LENGTH_SHORT).show();
   }

    public  boolean ifRecordExists(String name,Long ph){
        String[] FeeDetail = db.FeeData(name,ph);
        Long p=Long.parseLong(FeeDetail[1].trim());
        if(!(name.equals(FeeDetail[0]) && ph==p)){
            Toast.makeText(this,"Student is not registered",Toast.LENGTH_SHORT).show();
            return false;
        }
        Toast.makeText(this,"Student is  registered",Toast.LENGTH_SHORT).show();
        return true;
    }

}
