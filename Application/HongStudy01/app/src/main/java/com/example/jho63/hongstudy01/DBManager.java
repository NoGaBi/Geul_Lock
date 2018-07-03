package com.example.jho63.hongstudy01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class DBManager {

    private Context context;

    private MySQLOpenHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context){
        this.context = context;

        helper = new MySQLOpenHelper(context, "eos_contact.db",null,1);
        db = helper.getWritableDatabase();


    }

    public ArrayList<Person> selectAll(){
        ArrayList<Person> contactList = new ArrayList<>();

        Cursor c= db.query("contact", null, null, null, null, null, "name");
        while (c.moveToNext()){
            Person a = new Person(c.getString(c.getColumnIndex("name")),
                    c.getString(c.getColumnIndex("phonenum")),c.getString(c.getColumnIndex("email")));
            a.setProfileImg(c.getString(c.getColumnIndex("profile")));
            int cid = c.getInt(c.getColumnIndex("cid"));
            a.setCid(cid);
            contactList.add(a);
        }

        return contactList;
    }

    public void insertData(Person p){
        ContentValues values = new ContentValues();
        values.put("name", p.getName());
        values.put("phonenum", p.getPhoneNum());
        values.put("email",p.geteMail());
        values.put("profile",p.getProfileImg());
        db.insert("contact",null,values);
        db.close();
        Toast.makeText(context, "저장되었습니다. ", Toast.LENGTH_SHORT).show();
    }

    public void updateData(Person p){
        ContentValues values = new ContentValues();
        values.put("name", p.getName());
        values.put("phonenum", p.getPhoneNum());
        values.put("email",p.geteMail());
        values.put("profile",p.getProfileImg());

        db.update("contact", values, "cid=?", new String[]{p.getCid()+""});
        db.close();
        Toast.makeText(context, "수정되었습니다 ", Toast.LENGTH_SHORT).show();
    }

    public void deleteData(Person p){
        db.delete("contact", "cid=?", new String[]{p.getCid()+""});
        db.close();
        Toast.makeText(context, "수정되었습니다 ", Toast.LENGTH_SHORT).show();
    }

    Person p;
    public int getCid(int index){
        Cursor c = db.query("contact", null, null, null, null, null, "name");
        for(int i = 0; i < index+1; i++)
            c.moveToNext();
        return c.getInt(c.getColumnIndex("cid"));
    }

    public Person getData(int cid){

        Cursor c = db.query("contact", null, null, null, null, null, "cid");

        while(c.moveToNext()){
            if(cid==c.getInt(c.getColumnIndex("cid"))){
                p = new Person(c.getString(c.getColumnIndex("name")),
                        c.getString(c.getColumnIndex("phonenum")),c.getString(c.getColumnIndex("email")));
                p.setProfileImg(c.getString(c.getColumnIndex("profile")));
                p.setCid(c.getInt(c.getColumnIndex("cid")));
                break;
            }
        }

        return p;
    }

}