package com.example.tm;

import android.util.Log;
import android.widget.Toast;

import com.example.tm.Database.Database;

public class GeneralMethods {
    public  boolean ifRecordExists(String name,Long ph){
        Log.i("ANA ", "================================================IF name"+name);
        Log.i("ANA ", "================================================IF name"+ph);
        String[] FeeDetail = MainActivity.db.FeeData(name,ph);
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
}
