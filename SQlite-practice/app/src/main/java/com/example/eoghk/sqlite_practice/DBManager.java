package com.example.eoghk.sqlite_practice;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by eoghk on 2017-05-09.
 */

public class DBManager extends SQLiteOpenHelper{
    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE COMMENT (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, date TEXT)");
        //COMMENT이라는 이름의 테이블 생성
        //_id는 자동으로 내가 입력할 때 증가하는 숫자
        //title, date 모두 문자열이고 하나 씩 표에서 열 임
    }
    // DB 업그레이드를 위해 버전이 변경될 때
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insert(String title, String date,String create_at){
        //Writable한 DB열기
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO COMMENT VALUES(null, '"+ title +"','"+ date +"');");
        db.close();
    }
    public void update(String title,String date){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE COMMENT SET date='"+date+"' WHERE title='" + title + "';");//where title(title이 일치하는 곳에) set date(date를 바꾼다.)
        db.close();
    }
    public void delete(String title){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM COMMENT WHERE title='" + title + "';");
        db.close();
    }
    public String getResult(){
        //Readable한 DB열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM COMMENT",null); //Query : 조회하다 -> 행단위로 모두 조회
        while(cursor.moveToNext()){
            result += "김대화: "+cursor.getString(1) +"("+cursor.getString(2)+"에 달린 댓글)"+"\n"; //0부터 순서대로 하나의 row에 담긴 data
        }
        return result;
    }
}
