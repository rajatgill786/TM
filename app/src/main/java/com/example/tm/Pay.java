package com.example.tm;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tm.Database.Database;

public class Pay extends AppCompatActivity {
    Database db= new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
    }



    private void payFee(String name,Long phone){

        db.getWritableDatabase();
        if(ifRecordExists(name,phone))
        {
            
        }

    }


    private boolean ifRecordExists(String name,Long ph){
      String n=db.fetchName();
      Long p=db.fetchPh();
      if(!(name.equals(n) && ph==p)){
          Toast.makeText(this,"Student is not registered",Toast.LENGTH_SHORT).show();
          return false;}
      return true;

    }
}
