package com.example.navigation.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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

public class Login extends AppCompatActivity implements View.OnClickListener{

    private StoreDatabase storeDb;
    private SQLiteDatabase sqdb;

    EditText email;

    EditText password;
    Button buttonenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        storeDb = new StoreDatabase(this);
        sqdb = storeDb.getWritableDatabase();

        email = findViewById(R.id.email);

        password = findViewById(R.id.password);
        buttonenter = findViewById(R.id.buttonenter);
        buttonenter.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {


            if(TextUtils.isEmpty(email.getText())){
                email.setError("Fill again");
                return;

            }
            if(TextUtils.isEmpty(password.getText())){
                password.setError("Fill again");
                return;
            }
            Cursor userCursor = sqdb.rawQuery("SELECT * FROM" + TABLE_USERS +
                    "WHERE" + COLUMN_USER_EMAIL+  "=? AND "+COLUMN_USER_PASSWORD +"=?",new String[]{email.getText().toString(), password.getText().toString()});

            if(((userCursor != null) &&(userCursor.getCount()>0))){
                userCursor.moveToFirst();
                String userName=userCursor.getString(userCursor.getColumnIndex(COLUMN_USER_NAME));
                Toast.makeText(this,"tapty"+userName,Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"tappad",Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this,"Succesfull",Toast.LENGTH_SHORT).show();
            ///Intent intent = new Intent(this,Login.class);
            ///startActivity(intent);

        }
}