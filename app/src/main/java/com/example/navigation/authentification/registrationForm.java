package com.example.navigation.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.navigation.Database.StoreDatabase;
import com.example.navigation.R;

import static com.example.navigation.Database.StoreDatabase.COLUMN_USER_EMAIL;
import static com.example.navigation.Database.StoreDatabase.COLUMN_USER_NAME;
import static com.example.navigation.Database.StoreDatabase.COLUMN_USER_PASSWORD;
import static com.example.navigation.Database.StoreDatabase.TABLE_USERS;

public class registrationForm extends AppCompatActivity implements View.OnClickListener {
    private StoreDatabase storeDb;
    private SQLiteDatabase sqdb;

    EditText email;
    EditText personName;
    EditText password;
    Button buttonenter;
    Button buttonlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        storeDb = new StoreDatabase(this);
        sqdb = storeDb.getWritableDatabase();

        email = findViewById(R.id.email);
        personName = findViewById(R.id.personName);
        password = findViewById(R.id.password);
        buttonenter = findViewById(R.id.buttonenter);
        buttonlogin = findViewById(R.id.buttonlogin);
        buttonenter.setOnClickListener(this);
        buttonlogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonenter:
                if(TextUtils.isEmpty(personName.getText())){
                    personName.setError("Fill again");
                    return;
                }
                if(TextUtils.isEmpty(email.getText())){
                    email.setError("Fill again");
                    return;

                }
                if(TextUtils.isEmpty(password.getText())){
                    password.setError("Fill again");
                    return;
                }
                ContentValues versionValues = new ContentValues();
                versionValues.put(COLUMN_USER_NAME ,personName.getText().toString());
                versionValues.put(COLUMN_USER_EMAIL ,email.getText().toString());
                versionValues.put(COLUMN_USER_PASSWORD ,password.getText().toString());
                sqdb.insert(TABLE_USERS,null,versionValues);
                Toast.makeText(this,"Succesfull",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,Login.class);
                startActivity(intent);
                break;
            case R.id.buttonlogin:
                Intent intent2 = new Intent(this,Login.class);
                startActivity(intent2);
                break;
        }




    }
}