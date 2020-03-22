package com.example.tm;

import android.util.Log;
import android.widget.Toast;

import com.example.tm.Database.Database;

public class GeneralMethods {
    public  boolean ifRecordExists(String name,Long ph){
        String[] FeeDetail = MainActivity.db.FeeData(name,ph);

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
