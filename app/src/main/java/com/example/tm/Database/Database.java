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

        String CreateTableFee="Create table Fee(Name Text not null, Phone_no Integer not null,fee Integer not null ,Months Text ,Foreign Key(Name,Phone_no) references Student(Name,Phone_no));";
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


    public void payFee(String name,Long ph, int fee){
        ContentValues cv= new ContentValues();
        cv.put("Name",name);
        cv.put("Phone_no",ph);
        cv.put("fee",fee);
        db.insert("fee",null,cv);
    }
public String fetchName(){
   // db.getReadableDatabase();
    String n="";
    Long ph=0L;
    String Query="select name  from Student";
    Cursor cr=db.rawQuery(Query,null);
    while(cr.moveToNext())
        n = cr.getString(0);

    return n;
}


    public Long fetchPh(){
        // db.getReadableDatabase();

        Long ph=0L;
        String Query="select phone_no from Student";
        Cursor cr=db.rawQuery(Query,null);
        while(cr.moveToNext())
            ph=cr.getLong(0);

        return ph;
    }
}


