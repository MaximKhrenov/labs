package com.example.maxim.test;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ActivityAboutUser extends AppCompatActivity {
    private TextView currentUserFIO;
    private TextView currentUserRating;
    private TextView a1;
    private TextView a2;
    private TextView a3;
    private TextView a4;
    private TextView a5;
    private TextView a6;
    private TextView a7;
    private String strLogin;
    private String FIO;

    // field of class DBHelper
    ControlSQL dbHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_user);
        Intent intent = getIntent();
        dbHelper = new ControlSQL(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        strLogin = intent.getStringExtra("currentLoginUser");
        String sel = "login" + "=" + "'" + strLogin+ "'";
        Cursor c = database.query("table1", null, sel, null, null, null, null);
        Cursor c1 = database.query("table2", null, sel, null, null, null, null);
        c.moveToNext();
        c1.moveToNext();
        int idColIndex = c.getColumnIndex("id");
        int nameColIndex = c.getColumnIndex("name");
        int familyColIndex = c.getColumnIndex("family");
        int surnameColIndex = c.getColumnIndex("surname");
        int raitingColIndex = c1.getColumnIndex("raiting");
        int answer1 = c1.getColumnIndex("answer1");
        int answer2 = c1.getColumnIndex("answer2");
        int answer3 = c1.getColumnIndex("answer3");
        int answer4 = c1.getColumnIndex("answer4");
        int answer5 = c1.getColumnIndex("answer5");
        int answer6 = c1.getColumnIndex("answer6");
        int answer7 = c1.getColumnIndex("answer7");


        String userName = c.getString(nameColIndex);
        String userFamily = c.getString(familyColIndex);
        String userSurname = c.getString(surnameColIndex);
        String userRaiting = c1.getString(raitingColIndex);
        String Ans1 = c1.getString(answer1);
        String Ans2 = c1.getString(answer2);
        String Ans3 = c1.getString(answer3);
        String Ans4 = c1.getString(answer4);
        String Ans5 = c1.getString(answer5);
        String Ans6 = c1.getString(answer6);
        String Ans7 = c1.getString(answer7);

        currentUserFIO = findViewById(R.id.currentUserFIO);
        currentUserRating = findViewById(R.id.currentUserRating);
        a1 = findViewById(R.id.answer1);
        a2 = findViewById(R.id.answer2);
        a3 = findViewById(R.id.answer3);
        a4 = findViewById(R.id.answer4);
        a5 = findViewById(R.id.answer5);
        a6 = findViewById(R.id.answer6);
        a7 = findViewById(R.id.answer7);

        currentUserRating.setText(userRaiting + "%");
        currentUserFIO.setText(userName + " " + userFamily + " "+ userSurname);
        a1.setText("2+2=" + Ans1);
        a2.setText("3+3="+ Ans2);
        a3.setText("4+4="+Ans3);
        a4.setText("5+2="+Ans4);
        a5.setText("6+6="+Ans5);
        a6.setText("7+7="+Ans6);
        a7.setText("8+8="+Ans7);





        c.close();
        database.close();
    }
}