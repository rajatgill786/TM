package com.example.tm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tm.Database.Database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddStudent extends AppCompatActivity {
    EditText editText1,editText2,editText3;
    Pay py;

    public AddStudent() {
        py = new Pay();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        Button btn=(Button)findViewById(R.id.button1);
        editText1=(EditText)findViewById(R.id.Name);
        editText2=(EditText)findViewById(R.id.Phone);
        editText3=(EditText)findViewById(R.id.Class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
            }
        });
    }

    private String date(){
        Date date=new Date();
        String a=date.toString();
        return a;
    }

    private void addStudent(){
        String name= editText1.getText().toString().trim();
        Long ph=Long.parseLong(editText2.getText().toString().trim());
        String Class= editText3.getText().toString();
        Long p=111L;


        Toast.makeText(this,"Date of joining is : " + getCurrentDate(), Toast.LENGTH_SHORT).show();
        Database db= new Database(this);
        db.getWritableDatabase();

            db.addStudent(name, ph, Class, getCurrentDate());

        /*
         * For first data insertion in Fee table
          */
            db.payFee(name,ph,"null");
        //   db.addStudent("111",p,"6","09-01-1-2020");
        Toast.makeText(this,"Inserted Successfully",Toast.LENGTH_SHORT).show();
    }

    public String getCurrentDate(){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy ");// MM for month and mm is for minute
        Date obj= new Date();
        String date= df.format(obj);
        return date;
    }
}
