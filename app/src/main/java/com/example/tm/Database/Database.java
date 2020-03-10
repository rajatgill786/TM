package com.example.tm.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class Database extends SQLiteOpenHelper {
    public static final String DatabaseName="TM.db";
    public static final String TStudent="Sudent";
    public SQLiteDatabase db;

    private Context context;
    String CreateTableTStudent,CreateTableFee;
    public Database(Context context) {
        super(context, DatabaseName,null,1);
        db=this.getWritableDatabase();
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

    public String[] getDayMonth(String name, Long ph){
        String mt= " ";//stores  date
        String month="";//stores month
        String day="";//stores day of month
        String Query="select JoiningDate  from Student where Name = '"+name +"'AND Phone_no = '"+ ph+"'";
        Cursor cr=db.rawQuery(Query,null);
        while(cr.moveToNext())
            mt  = cr.getString(0);

        String[] ar =mt.split("-");
        day=ar[0];
        month=ar[1];
        return new String[] {day,month};
    }

    /*
    *This method fetches whole data of Fee table
     */
    public String[] FeeData(String name,Long p){
        String n,m,phone;
        n=m="";
        Long ph=0L;
        String Query="select * from Fee where Name = '"+name +"'AND Phone_no = '"+ p+"'";
        Cursor cr=db.rawQuery(Query,null);
        while(cr.moveToNext()) {
            n = cr.getString(0);
            ph=cr.getLong(1);
            m=cr.getString(2);
        }
        phone=ph.toString();
            return new String[]{n, phone, m};
    }

    public void updateFee(String name,Long phone,String LastMY){

        String StringPhone=Long.toString(phone);
        ContentValues cv= new ContentValues();
        cv.put("LastMonthYear",LastMY);
        db.update("Fee",cv,"Name = ? AND Phone_no = ?",new String[]{name,StringPhone});
    }
}


