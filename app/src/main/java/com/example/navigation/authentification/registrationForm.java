package com.example.navigation.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation.Database.StoreDatabase;
import com.example.navigation.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_spinner_item;
import static com.example.navigation.Database.StoreDatabase.COLUMN_CITY;
import static com.example.navigation.Database.StoreDatabase.COLUMN_CITY_ID;
import static com.example.navigation.Database.StoreDatabase.COLUMN_GINFO;
import static com.example.navigation.Database.StoreDatabase.COLUMN_STUDENT_GROUP_ID;
import static com.example.navigation.Database.StoreDatabase.COLUMN_SUM;
import static com.example.navigation.Database.StoreDatabase.COLUMN_USER_EMAIL;
import static com.example.navigation.Database.StoreDatabase.COLUMN_USER_NAME;
import static com.example.navigation.Database.StoreDatabase.COLUMN_USER_PASSWORD;
import static com.example.navigation.Database.StoreDatabase.TABLE_CITY;
import static com.example.navigation.Database.StoreDatabase.TABLE_GROUPS;
import static com.example.navigation.Database.StoreDatabase.TABLE_STUDENTS;
import static com.example.navigation.Database.StoreDatabase.TABLE_STUDENTS;

public class registrationForm extends AppCompatActivity implements View.OnClickListener {
    private StoreDatabase storeDb;
    private SQLiteDatabase sqdb;
    List<String> list = new ArrayList<>();



    EditText email;
    EditText personName;
    EditText password;
    Button buttonenter;
    Button buttonlogin;
    Spinner groupSpinner;
    Spinner citySpinner;

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);
        initViews();
        radioGroup = findViewById(R.id.radioGroup);
        textView= findViewById(R.id.text_view_selected);
        Button buttonApply = findViewById(R.id.buttonenter);

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                textView.setText("Your choise:" + radioButton.getText());

            }
        });




    }
    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton= findViewById(radioId);
        Toast.makeText(this, "Selected Button" + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }







    public void initViews(){
        email = findViewById(R.id.email);
        personName = findViewById(R.id.personName);
        password = findViewById(R.id.password);
        buttonenter = findViewById(R.id.buttonenter);
        buttonlogin = findViewById(R.id.buttonlogin);
        groupSpinner = findViewById(R.id.groupSpinner);




        buttonenter.setOnClickListener(this);
        buttonlogin.setOnClickListener(this);


        initSpinner();

        storeDb = new StoreDatabase(this);
        sqdb = storeDb.getWritableDatabase();

    }
    public void initSpinner(){
        Cursor cursor = sqdb.rawQuery("SELECT * FROM " + TABLE_GROUPS,null);
        Cursor cursor1 = sqdb.rawQuery("SELECT * FROM " + TABLE_CITY,null);

        if((cursor !=null && cursor.getCount()>0)){
            while (cursor.moveToNext()){
                String ginfo = cursor.getString(cursor.getColumnIndex(COLUMN_GINFO));
                String gid = cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_GROUP_ID));
                String sum = cursor.getString(cursor.getColumnIndex(COLUMN_SUM));


                Log.i("Database","fullName: " + ginfo);
                Log.i("Database","email: " + gid);
                Log.i("Database","password: " +sum);
                list.add(gid);
            }
        }
        if((cursor1 !=null && cursor1.getCount()>0)){
            while (cursor1.moveToNext()){
                String cityname = cursor.getString(cursor1.getColumnIndex(COLUMN_CITY));
                String cityid = cursor.getString(cursor1.getColumnIndex(COLUMN_CITY_ID));

                Log.i("Database","CITY Name: " + cityname);
                Log.i("Database","id: " + cityid);

                list.add(cityname);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.item_group, list);
        groupSpinner.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                R.layout.item_group2, list);
        citySpinner.setAdapter(adapter);

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
                sqdb.insert(TABLE_STUDENTS,null,versionValues);
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