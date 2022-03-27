package com.example.todolist.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
                ShowDialog();
            }
       });
    }
  private   void ShowDialog(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DashboardF.this);
        alertDialog.setTitle(R.string.add_task);
        LayoutInflater inflater = this.getLayoutInflater();
        View add_task = inflater.inflate(R.layout.new_item, null);
        EditText editText=add_task.findViewById(R.id.edittext);
        RadioGroup radioGroup = add_task.findViewById(R.id.radio_group);
        alertDialog.setView(add_task) ;
        alertDialog.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = editText.getText().toString();
                View checked_btn = add_task.findViewById(radioGroup.getCheckedRadioButtonId());
                String priority = "0" ;
                if( !(checked_btn == null || checked_btn.getTag().toString().isEmpty() )){
                    priority =checked_btn.getTag().toString() ;
                }
                Long tsLong = System.currentTimeMillis()/1000;
                ToDoItem toDoItem = new ToDoItem(tsLong.toString(), title, priority);
                firebaseConnection.Post(toDoItem);
            }
        });
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }

}