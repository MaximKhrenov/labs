package com.example.maxim.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private SharedPreferences preferences;

    private Button Start;
    private Button Raiting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Start = findViewById(R.id.start);
        Raiting = findViewById(R.id.reiting);
        Start.setOnClickListener(this);
        Raiting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {



        switch (v.getId()) {
            case R.id.start:
                Intent intent = new Intent(MainActivity.this,ActivityTest.class);

                startActivity(intent);
                break;
            case R.id.reiting:
                Intent intent2 = new Intent(MainActivity.this,ActivityAdapter.class);

                startActivity(intent2);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        menu.add(0,1,0,"О нас");
        menu.add(0,2,1,"Выход");
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){
            case 1 :
                Intent intent = new Intent(MainActivity.this, ActivityAbout.class);
                startActivity(intent);
                break;
            case 2 :
                preferences = getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("is_auth",0);

                editor.apply();

                Intent intent1 = new Intent(MainActivity.this,LoginActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}