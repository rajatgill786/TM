package com.example.tm.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class Database extends SQLiteOpenHelper {
    public static final String DatabaseName="TM.db";
    public static final String TStudent="Sudent";
    public SQLiteDatabase db;

    private Context context;
    public Database(Context context) {
        super(context, DatabaseName,null,1);
        db=this.getWritableDatabase();
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CreateTableTStudent="create table Student(Name Text not null, Phone_no Integer not null,Class Text not null,JoiningDate Text, Primary Key(Name,Phone_no));";
        db.execSQL(CreateTableTStudent);

        String CreateTableFee="Create table Fee(Name Text not null, Phone_no Integer not null,fee Integer not null ,Year Text not null,Months Text ,Foreign Key(Name,Phone_no) references Student(Name,Phone_no));";
        db.execSQL(CreateTableFee);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void addStudent(String name,Long ph,String Class,String JoiningDate){
        ContentValues cv= new ContentValues();
        cv.put("Name",name);
        cv.put("Phone_no",ph);
        cv.put("Class",Class);
        cv.put("JoiningDate",JoiningDate);
        db.insert("Student",null,cv);
    }

    public String getMonth(String name, Long ph){
        String m="";//stores month
        String mt= " ";//stores  date
        String Query="select JoiningDate  from Student where Name = '"+name +"'AND Phone_no = '"+ ph+"'";
        Cursor cr=db.rawQuery(Query,null);
        while(cr.moveToNext())
            mt  = cr.getString(0);

        String ar[]=mt.split("-");
        m=ar[1];

        return m;
    }

    public void payFee(String name,Long ph, int fee,String yr,String month){
        ContentValues cv= new ContentValues();
        cv.put("Name",name);
        cv.put("Phone_no",ph);
        cv.put("fee",fee);
        cv.put("Year",yr);
        cv.put("Months",month);
        db.insert("Fee",null,cv);
    }

    public String fetchName(String name,Long p){
   // db.getReadableDatabase();
        String n="";
        Long ph=0L;
       // String Query="select name  from Student where  ";
        String Query="select name  from Student where Name = '"+name +"'AND Phone_no = '"+ p+"'";
        Cursor cr=db.rawQuery(Query,null);
        while(cr.moveToNext())
            n = cr.getString(0);

       return n;
    }


    public Long fetchPh(String name,Long p){
        // db.getReadableDatabase();

        Long ph=0L;
        //String Query="select phone_no from Student";
        String Query="select  Phone_no from Student where Name = '"+name +"'AND Phone_no = '"+ p+"'";
        Cursor cr=db.rawQuery(Query,null);
        while(cr.moveToNext())
            ph=cr.getLong(0);

        return ph;
    }
}


