package com.example.todolist.ViewModel;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.todolist.Model.ToDoItem;
import com.example.todolist.R;
import com.example.todolist.View.DashboardF;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseConnection {

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();;
   public static ArrayList<ToDoItem>toDoItems= new ArrayList<>() ;
    ToDoItem toDoItem = new ToDoItem() ;


    public void Get(){

        databaseReference.addValueEventListener (new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                toDoItems.clear();
                for (DataSnapshot list : dataSnapshot.getChildren()) {
                    toDoItem = list.getValue(ToDoItem.class);
                    toDoItems.add(toDoItem);
                }
                DashboardF.adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(FirebaseConnection.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
//                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

   public  void Post(ToDoItem toDoItem){
        databaseReference.push().setValue(toDoItem).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
               //     Toast.makeText(DashboardF.this, R.string.success, Toast.LENGTH_LONG).show();
                }else {
                  //  Toast.makeText(DashboardF.this, R.string.fail, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
