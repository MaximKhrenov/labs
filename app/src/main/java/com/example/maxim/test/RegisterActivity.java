package com.example.maxim.test;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private EditText family;
    private EditText surname;
    private EditText login;
    private EditText pass1;
    private EditText pass2;
    private Button reg;
    ControlSQL dbHelper;
    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.name);
        family = findViewById(R.id.family);
        surname = findViewById(R.id.surname);
        login = findViewById(R.id.log);
        pass1 = findViewById(R.id.pass1);
        pass2 = findViewById(R.id.pass2);
        reg = findViewById(R.id.regist);
        reg.setOnClickListener(this);
        dbHelper = new ControlSQL(this);
    }

    @Override
    public void onClick(View v) {
        String strname = name.getText().toString();
        String strfamily = family.getText().toString();
        String strsurname = surname.getText().toString();
        String strlogin = login.getText().toString();
        String strpass1 = pass1.getText().toString();
        String strpass2 = pass2.getText().toString();

        if (!(strpass1.equalsIgnoreCase(strpass2))) {

            Toast.makeText(this, "Пароли не совпадают!", Toast.LENGTH_LONG).show();

        } else if (strname.length() == 0
                || strfamily.length() == 0
                || strsurname.length() == 0
                || strlogin.length() == 0
                || strpass1.length() == 0
                ) {

            Toast.makeText(this, "Есть пустые поля!", Toast.LENGTH_LONG).show();


        } else {
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            Log.d(LOG_TAG, "--- Insert in mytable: ---");
            // подготовим данные для вставки в виде пар: наименование столбца - значение

            cv.put("name", strname);
            cv.put("family", strfamily);
            cv.put("surname", strsurname);
            cv.put("login", strlogin);
            cv.put("password", strpass1);
            cv.put("birthday", 1998);
            long rowID = database.insert("table1", null, cv);
            Log.d(LOG_TAG, "row inserted, ID = " + rowID);


            Intent intent = new Intent(this, LoginActivity.class);

            startActivity(intent);
        }
    }


}