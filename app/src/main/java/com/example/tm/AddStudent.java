package com.example.tm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tm.Database.Database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddStudent extends AppCompatActivity {
    Pay py;

    public AddStudent() {
        py = new Pay();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        Button btn=(Button)findViewById(R.id.button1);

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
        EditText editText1=(EditText)findViewById(R.id.Name);
        EditText editText2=(EditText)findViewById(R.id.Phone);
        EditText editText3=(EditText)findViewById(R.id.editText3);


        String name= editText1.getText().toString().trim();
        Long ph=Long.parseLong(editText2.getText().toString().trim());
        String Class= editText3.getText().toString();
    /*    if(py.ifRecordExists(name,ph)){
            Toast.makeText(this,"Student already exist",Toast.LENGTH_SHORT).show();
        }else {*/
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy ");// MM for month and mm is for minute
        Date obj= new Date();
        String date= df.format(obj);
        Toast.makeText(this,"Date of joining is : " + date, Toast.LENGTH_SHORT).show();
        Database db= new Database(this);
        db.getWritableDatabase();

            db.addStudent(name, ph, Class, date);
            String m = db.getMonth(name, ph);
            Toast.makeText(this, "Month of joining is : " + m, Toast.LENGTH_SHORT).show();
      //  }
    }
}
