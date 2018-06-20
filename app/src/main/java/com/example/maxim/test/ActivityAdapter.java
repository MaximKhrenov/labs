package com.example.maxim.test;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityAdapter extends Activity {

    ArrayList<User> users = new ArrayList<User>();
    AdapterList adapterListView;
    private TextView Login;
    private TextView Rating;

    // field of class DBHelper
    ControlSQL dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dbHelper = new ControlSQL(this);

        adapterListView = new AdapterList(this, users);

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor c = database.query("table2", null, null, null, null, null, null);

        if(c.moveToFirst()){
            int loginColIndex = c.getColumnIndex("login");
            int ratingColIndex = c.getColumnIndex("raiting");


            do{
                users.add(new User( c.getString(loginColIndex),
                        String.valueOf(c.getString(ratingColIndex) + "%")));
            }while(c.moveToNext());
        }
        final ListView lvMain = (ListView)findViewById(R.id.lvMain);
        lvMain.setAdapter(adapterListView);
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Login = (TextView) view.findViewById(R.id.textLogin);


                Intent intent = new Intent(getApplicationContext(), ActivityAboutUser.class);
                intent.putExtra("currentLoginUser", Login.getText());

                startActivity(intent);

            }
        });


    }


}