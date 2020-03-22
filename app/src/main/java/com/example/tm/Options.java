package com.example.tm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Options extends AppCompatActivity {
    Pay RButtons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        RButtons=new Pay();
        onClickAdd();
        onClickDelete();
        onClickPay();
        onClickCheckDue();
    }

    private void startAdd(){
        Intent i = new Intent(this, AddStudent.class);
        startActivity(i);
     }
    private void startDelete(){
        Intent i = new Intent(this, DeleteStudent.class);
        startActivity(i);
    }
    private void startPay(){
        Intent i = new Intent(this, Pay.class);
        startActivity(i);
    }
    private void startCheckDue(){
        Intent i = new Intent(this, CheckDue.class);
        startActivity(i);
    }

    private void onClickAdd(){
        Button btn1=(Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startAdd();
            }
        });
    }
    private void onClickDelete(){
        Button btn1=(Button)findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDelete();
            }
        });

    }
    private void onClickPay(){
        Button btn1=(Button)findViewById(R.id.button3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPay();
            }
        });
    }
    private void onClickCheckDue(){
        Button btn1=(Button)findViewById(R.id.button4);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCheckDue();
            }
        });
    }
}
