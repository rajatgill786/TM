package com.example.tm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn =(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Login();
            }
        });
    }

    private void newActivity(){
        Intent i= new Intent(this,Options.class);
        startActivity(i);
    }
    private void Login()
    {
        EditText editText1=(EditText)findViewById(R.id.editText);
        EditText editText2=(EditText)findViewById(R.id.editText2);
        String id=editText1.getText().toString();
        String pass=editText2.getText().toString();
        Toast.makeText(this,""+id + "  "+pass,Toast.LENGTH_SHORT).show();
        if(id.equals("a") && pass.equals("a"))
          newActivity();
        else if(id.equals("ID")|| pass.equals(""))
            Toast.makeText(this,"Missing ID/Password",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Wrong ID/Password",Toast.LENGTH_SHORT).show();
    }
}
