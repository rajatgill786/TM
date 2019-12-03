package com.example.tm;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tm.Database.Database;
import com.example.tm.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddStudent extends AppCompatActivity {

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
        EditText editText1=(EditText)findViewById(R.id.editText1);
        EditText editText2=(EditText)findViewById(R.id.editText2);
        EditText editText3=(EditText)findViewById(R.id.editText3);


        String name= editText1.getText().toString();
        Long ph=Long.parseLong(editText2.getText().toString());
        String Class= editText3.getText().toString();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy ");// MM for month and mm is for minute
        Date obj= new Date();
        String date= df.format(obj);
        Toast.makeText(this,"Date of joining is : " + date, Toast.LENGTH_SHORT).show();
        Log.i("ANA ","First");
        Database db= new Database(this);
        db.getWritableDatabase();
        db.addStudent(name,ph,Class,date);
        String m= db.getMonth(name,ph);
        Log.i("ANA ","ujklFirst");
        Toast.makeText(this,"Month of joining is : " + m, Toast.LENGTH_SHORT).show();
    }
}
