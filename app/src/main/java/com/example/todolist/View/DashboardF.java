package com.example.todolist.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.todolist.Model.ToDoItem;
import com.example.todolist.R;
import com.example.todolist.ViewModel.Adapter;
import com.example.todolist.ViewModel.FirebaseConnection;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DashboardF extends AppCompatActivity {

    RecyclerView recyclerView ;
    FloatingActionButton btn_add;
    FirebaseConnection firebaseConnection;
    public  static  Adapter adapter ;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_f);



        btn_add = findViewById(R.id.btn_add) ;
        recyclerView = findViewById(R.id.recyclerView);
       adapter = new Adapter(FirebaseConnection.toDoItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        firebaseConnection =new FirebaseConnection() ;
        recyclerView.setAdapter(adapter);
        firebaseConnection.Get();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


}