package com.example.tm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tm.Database.Database;

public class MainActivity extends AppCompatActivity {
public static Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= new Database(this);

        Intent i= new Intent(this, Login.class);
        startActivity(i);
    }
}
