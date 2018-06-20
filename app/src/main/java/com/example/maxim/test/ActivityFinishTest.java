package com.example.maxim.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ActivityFinishTest extends AppCompatActivity {

    private TextView first_text;
    private TextView right_answer;


    private SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        first_text = (TextView) findViewById(R.id.first_text);
        right_answer = (TextView) findViewById(R.id.right_answer);

        Intent intent = getIntent();
        first_text.setText(intent.getStringExtra("messageText"));
        right_answer.setText(intent.getStringExtra("rightAnswer"));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        menu.add(0,1,0,"Пересдать тест");
        menu.add(0,2,0,"О нас");
        menu.add(0,3,1,"Выход");
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){
            case 1 :
                Intent intent2 = new Intent(ActivityFinishTest.this, ActivityTest.class);
                startActivity(intent2);
                break;
            case 2 :
                Intent intent = new Intent(ActivityFinishTest.this, ActivityAbout.class);
                startActivity(intent);
                break;
            case 3 :
                preferences = getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("is_auth",0);

                editor.apply();

                Intent intent1 = new Intent(ActivityFinishTest.this,LoginActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}