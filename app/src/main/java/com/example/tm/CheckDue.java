package com.example.tm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tm.Database.Database;

public class CheckDue extends AppCompatActivity {

    EditText EditName, EditPhone;
    TextView Detail;
    Button ChkDue;
    Database db;
    GeneralMethods gm;

    String nm;
    Long phone_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_due);

        EditName=(EditText)findViewById(R.id.ENAME);
        EditPhone=(EditText)findViewById(R.id.EPHONE);
        Detail=(TextView)findViewById(R.id.Detail);
        ChkDue=(Button)findViewById(R.id.CheckDues);

        db= new Database(this);
       ChkDue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                nm=EditName.getText().toString().trim();
                phone_no=Long.parseLong(EditPhone.getText().toString().trim());
                if(ifRecordExists( nm, phone_no)) {
                    int due = db.CheckDues(nm, phone_no);
                    String[] LastDateArr = db.FeeData(nm, phone_no);
                    String LastDate = LastDateArr[2];
                    Detail.setText("Last Date Upto which fee is paid : " + LastDate);
                    Detail.append("\n\nDue fee : " + due + " months");
                }

                else
                    toast();
            }
        });
    }

    void toast(){
        Toast.makeText(this,"NO SUCH RECORD EXIST",Toast.LENGTH_SHORT).show();
    }

    public  boolean ifRecordExists(String name,Long ph){
        String[] FeeDetail = db.FeeData(name,ph);
        Long p=Long.parseLong(FeeDetail[1].trim());
        if(!(name.equals(FeeDetail[0]) && ph==p)){
            return false;}
        return true;
    }
    /*
       public  boolean ifRecordExists(String name,Long ph){
        Log.i("ANA ", "================================================IF name"+name);
        Log.i("ANA ", "================================================IF name"+ph);
        String[] FeeDetail = db.FeeData(name,ph);
        Log.i("ANA ", "================================================LONG P "+FeeDetail[1]);
        Long p=Long.parseLong(FeeDetail[1].trim());
        Log.i("ANA ", "================================================LONG P "+p);
        if(!(name.equals(FeeDetail[0]) && ph==p)){
            Log.i("ANA ", "================================================inside IF ");
           // Toast.makeText(this,"Student is not registered",Toast.LENGTH_SHORT).show();
            return false;}
      //  Toast.makeText(this,"Student is  registered",Toast.LENGTH_SHORT).show();
        return true;
    }
     */

}
