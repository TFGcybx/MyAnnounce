package com.example.myannounce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class new_announce extends AppCompatActivity {
    private DBHelper helper;
    private EditText et_announce_uname;
    private EditText et_announce_title;
    private EditText et_announce_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_announce);
        initView();
    }

    private void initView(){
        helper=new DBHelper(new_announce.this);
        et_announce_uname=findViewById(R.id.et_announce_uname);
        et_announce_title=findViewById(R.id.et_announce_title);
        et_announce_details=findViewById(R.id.et_announce_details);
    }

    public void okButton(View view){
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
        String unameStr=et_announce_uname.getText().toString().trim();
        String titleStr=et_announce_title.getText().toString().trim();
        String detailsStr=et_announce_details.getText().toString().trim();
        String dateStr=sDateFormat.format(new java.util.Date());;
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("UName",unameStr);
        values.put("Title",titleStr);
        values.put("Details",detailsStr);
        values.put("Date",dateStr);
        long account=db.insert("announce",null,values);
        if(account>0){
            Toast toast=Toast.makeText(this,"发表成功",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            setResult(1);
            finish();
        }else{
            Toast toast=Toast.makeText(this,"请重试",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            db.close();
        }
        setResult(1);
        finish();
    }
}