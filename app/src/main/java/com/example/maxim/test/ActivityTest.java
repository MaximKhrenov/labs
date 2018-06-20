package com.example.maxim.test;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ActivityTest extends AppCompatActivity implements View.OnClickListener {

    private TextView textName;
    private Button OK;
    private SharedPreferences preferences;
    private String messageText = "Тест не сдан!";
    private RadioGroup radio_group;
    private RadioButton first;
    private RadioButton second;
    private RadioButton third;
    private RadioButton fourth;
    private int index = 0;
    private int wrong_answer = 0;
    ControlSQL dbHelper;


    public Tests test1 = new Tests("JS 1 + 1 =",
            "3",
            "2",
            "6",
            "8");

    public Tests test2 = new Tests("JS '3' + 3 =",
            "6",
            "33",
            "9",
            "10");

    public Tests test3 = new Tests("JS '4' + '4' =",
            "8",
            "44",
            "9",
            "12");
    public Tests test4 = new Tests("JS 5 - '2' =",
            "52",
            "3",
            "0",
            "10");
    public Tests test5 = new Tests("JS '5' - '5' =",
            "null",
            "0",
            "10",
            "-1");
    public Tests test6 = new Tests("JS 2  + '7' - '3' = ",
            "7",
            "24",
            "6",
            "3");
    public Tests test7 = new Tests("JS '1' === 1",
            "true",
            "false",
            "undefined",
            "тык");

    public Tests[] all_tests = {
            test1,
            test2,
            test3,
            test4,
            test5,
            test6,
            test7
    };
    String[] answers = new String[7];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textName = (TextView) findViewById(R.id.textName);
        radio_group = (RadioGroup) findViewById(R.id.radio_group);
        first = (RadioButton) findViewById(R.id.first);
        second = (RadioButton) findViewById(R.id.second);
        third = (RadioButton) findViewById(R.id.third);
        fourth = (RadioButton) findViewById(R.id.fourth);
        textName.setText(all_tests[index].getTest());
        first.setText(all_tests[index].getAnswer1());
        second.setText(all_tests[index].getAnswer2());
        third.setText(all_tests[index].getAnswer3());
        fourth.setText(all_tests[index].getAnswer4());
        OK = (Button) findViewById(R.id.OK);
        OK.setOnClickListener(this);
        Intent intent = getIntent();
        dbHelper = new ControlSQL(this);
    }
    public void onClick(View v){
        RadioButton a = (RadioButton) radio_group.findViewById(radio_group.getCheckedRadioButtonId());

        if(a.getText() != all_tests[index].getAnswer2()) {
            ++wrong_answer;
        }
        answers[index] = a.getText().toString();
        ++index;

        if(index == all_tests.length) {
            --index;
            Intent intent = new Intent(this,ActivityFinishTest.class);
            if(wrong_answer <3 ) messageText = "Тест Сдан";
            preferences = getSharedPreferences( "MY_PREFS", Context.MODE_PRIVATE);
            String login = preferences.getString("login", "");
            int result = (all_tests.length - wrong_answer)*100/all_tests.length;
            intent.putExtra("messageText", messageText);
            intent.putExtra("rightAnswer", "Результат: " + ((all_tests.length - wrong_answer)*100/all_tests.length)+" %");
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            String sel = "login" + "=" + "'" + login + "'";

            ContentValues cv = new ContentValues();
            cv.put("login", login);
            cv.put("answer1", answers[0]);
            cv.put("answer2", answers[1]);
            cv.put("answer3", answers[2]);
            cv.put("answer4", answers[3]);
            cv.put("answer5", answers[4]);
            cv.put("answer6", answers[5]);
            cv.put("answer7", answers[6]);
            cv.put("raiting", result);
            Cursor c = database.query("table2", null, sel, null, null, null, null);
            if (c.moveToFirst()) {
                database.update("table2", cv, "login" + "=" + "'" + login + "'",new String[] {});
            }else {

                database.insert("table2", null, cv);
            }
            startActivity(intent);
        }
        textName.setText(all_tests[index].getTest());
        first.setText(all_tests[index].getAnswer1());
        second.setText(all_tests[index].getAnswer2());
        third.setText(all_tests[index].getAnswer3());
        fourth.setText(all_tests[index].getAnswer4());
    }
}