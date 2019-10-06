package com.example.tm;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tm.Database.Database;
import com.example.tm.R;

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
        String date=date();

        Database db= new Database(this);
        db.getWritableDatabase();
        db.addStudent(name,ph,Class,date);
    }
}
