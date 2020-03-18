package com.example.tm.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tm.AddStudent;


public class Database extends SQLiteOpenHelper {
    public static final String DatabaseName="TM.db";
    public static final String TStudent="Sudent";
    public SQLiteDatabase db;
    AddStudent add;

    private Context context;
    String CreateTableTStudent,CreateTableFee;
    public Database(Context context) {
        super(context, DatabaseName,null,1);
        db=this.getWritableDatabase();
        add= new AddStudent();
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        CreateTableTStudent="create table Student(Name Text not null, Phone_no Integer not null,Class Text not null,JoiningDate Text, Primary Key(Name,Phone_no));";
        db.execSQL(CreateTableTStudent);

        CreateTableFee="Create table Fee(Name Text not null, Phone_no Integer not null,LastMonthYear Text not null ,Foreign Key(Name,Phone_no) references Student(Name,Phone_no));";
        db.execSQL(CreateTableFee);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        switch(oldVersion){
            case 1:
                db.execSQL(CreateTableTStudent);
            case 2:
                db.execSQL(CreateTableFee);
        }
    }
    public void payFee(String name,Long ph, String monthYear){
        ContentValues cv= new ContentValues();
        cv.put("Name",name);
        cv.put("Phone_no",ph);
        cv.put("LastMonthYear",monthYear);
        db.insert("Fee",null,cv);
    }

    public void addStudent(String name,Long ph,String Class,String JoiningDate){
        ContentValues cv= new ContentValues();
        cv.put("Name",name);
        cv.put("Phone_no",ph);
        cv.put("Class",Class);
        cv.put("JoiningDate",JoiningDate);
        db.insert("Student",null,cv);

    }

    /*
    *This method fetches whole data of Fee table
     */
    public String[] FeeData(String name,Long p){
        Log.i("ANA ", "================================================first ");
        String n,m,phone;
        n=m="";
        Log.i("ANA ", "================================================Second ");
        Long ph=0L;
        Log.i("ANA ", "================================================ibefore ");
        String Query="select * from Fee where Name = '"+name +"'AND Phone_no = '"+ p+"'";
        Log.i("ANA ", "================================================after");
        Cursor cr=db.rawQuery(Query,null);
        Log.i("ANA ", "================================================Cursor ");
        while(cr.moveToNext()) {
            n = cr.getString(0);
            ph=cr.getLong(1);
            m=cr.getString(2);
        }
        Log.i("ANA ", "================================================after while ");
        phone=ph.toString();
        Log.i("ANA ", "================================================phone" +
                "" +
                "9");
            return new String[]{n, phone, m};
    }

    public void updateFee(String name,Long phone,String LastMY){

        String StringPhone=Long.toString(phone);
        ContentValues cv= new ContentValues();
        cv.put("LastMonthYear",LastMY);
        db.update("Fee",cv,"Name = ? AND Phone_no = ?",new String[]{name,StringPhone});
    }

    public void DeleteRecord(String name,Long phone){
        Log.i("ANA ","==================================Here 0");
        String StringPhone= Long.toString(phone);
        Log.i("ANA ","==================================Here 1");
        db.delete("Student ","Name = ? AND Phone_no = ?",new String[]{name,StringPhone});
        Log.i("ANA ","==================================Here 2");
        db.delete("Fee ","Name = ? AND Phone_no = ?",new String[]{name,StringPhone});
        Log.i("ANA ","==================================Here 3");
    }

    public int CheckDues(String name,Long phone){
        int TotalMonthsDue;
        String data[]= FeeData(name, phone);
        String LastPaidDate=data[2];
        String aa=data[0];
        String Today=add.getCurrentDate();
        String[] ArrLastPaid =LastPaidDate.split("/");

        String[] ArrToday =Today.split("-");
        int month1=Integer.parseInt(ArrToday[1]);
        int month2=Integer.parseInt(ArrLastPaid[0]);
        int MonthDifference =month1-month2;
       // if(!(ArrLastPaid[1].equals(ArrToday[2]))){
        int Year2=Integer.parseInt(ArrLastPaid[1]);
        /*
        * trim() to avoid nmber format exception
         */
        int Year1=Integer.parseInt(ArrToday[2].trim());
        int YearDifference=Year1-Year2;
        TotalMonthsDue=(YearDifference*12)+MonthDifference;
        return TotalMonthsDue;
    }
}


