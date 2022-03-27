package com.example.todolist.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.todolist.Model.ToDoItem;
import com.example.todolist.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button firebase, API ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebase = findViewById(R.id.firebase_btn);
        API = findViewById(R.id.api_btn);


        firebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , DashboardF.class);
                startActivity(intent);
            }
        });
        API.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , DashboardA.class);
                startActivity(intent);
            }
        });

    }
}