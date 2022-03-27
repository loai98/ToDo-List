package com.example.todolist.View;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import com.example.todolist.Model.ToDoItem;
import com.example.todolist.R;
import com.example.todolist.ViewModel.APIConnection;
import com.example.todolist.ViewModel.API.APIAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class DashboardA extends AppCompatActivity  {
    private RecyclerView recyclerView;
    FloatingActionButton btn_add;

    public static APIAdapter adapter ;
    APIConnection apiConnection;
    public  static   FloatingActionButton  btn_remove;
    public static ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_a);
        btn_add = findViewById(R.id.btn_add) ;
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new APIAdapter(APIConnection.toDoItems);
        recyclerView.setAdapter(adapter);
        progressBar = findViewById(R.id.progress_bar);
        btn_remove= findViewById(R.id.btn_remove);
         apiConnection = new APIConnection();
        apiConnection.getData(this);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private  void ShowDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DashboardA.this);
        alertDialog.setTitle(R.string.add_task);
        alertDialog.setCancelable(false);
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
                apiConnection.Post(toDoItem);
            }
        });
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }
    public  void Update(String key ){
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//        alertDialog.setTitle(R.string.edit_task);
//        alertDialog.setCancelable(false);
//        LayoutInflater inflater = this.getLayoutInflater();
//        View edit_task = inflater.inflate(R.layout.update_item, null);
//        EditText editText=edit_task.findViewById(R.id.editupdate);
//        RadioGroup radioGroup = edit_task.findViewById(R.id.radio_group2);
//        alertDialog.setView(edit_task) ;
//        alertDialog.setPositiveButton(R.string.update, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String title = editText.getText().toString();
//                View checked_btn = edit_task.findViewById(radioGroup.getCheckedRadioButtonId());
//                String priority = "0" ;
//                if( !(checked_btn == null || checked_btn.getTag().toString().isEmpty() )){
//                    priority =checked_btn.getTag().toString() ;
//                }
//                Long tsLong = System.currentTimeMillis()/1000;
//                ToDoItem toDoItem = new ToDoItem(tsLong.toString(), "title", priority);
//                apiConnection.Update(key,toDoItem);
//            }
//        });
//        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        });
//        alertDialog.show();
    }
}