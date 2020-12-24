package com.example.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class activity2 extends AppCompatActivity {
    RecyclerView recyclerView;
    adapter foodListAdapter;
    ArrayList<food> foodList;
    Button btnChange;

    boolean LayoutType = false;
    private  RecyclerView.LayoutManager LinearLayoutManager,gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
        initViews();

    }

    public void  initViews(){
        recyclerView = findViewById(R.id.recycleview);
        btnChange = findViewById(R.id.btnChange);
        foodList = new ArrayList<>();


        foodList.add(new food(R.drawable.side_nav_bar,"Birnarse","pizza",500));
        foodList.add(new food(R.drawable.side_nav_bar,"Birnarse2","pizza",5300));
        foodList.add(new food(R.drawable.side_nav_bar,"Birnarse3","pizza",5200));
        foodListAdapter = new adapter(this, foodList);
        LinearLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(LinearLayoutManager);
        recyclerView.setAdapter(foodListAdapter);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutType=!LayoutType;
                if (LayoutType){
                    recyclerView.setLayoutManager(gridLayoutManager);
                    foodList.remove(0);
                }else {
                    recyclerView.setLayoutManager(LinearLayoutManager);
                }

                foodListAdapter.notifyDataSetChanged();
            }
        });

    }
}