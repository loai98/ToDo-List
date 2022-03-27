package com.example.todolist.ViewModel;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.todolist.Model.ToDoItem;
import com.example.todolist.View.DashboardF;
import com.example.todolist.ViewModel.Firebase.Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseConnection {

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();;
   public static ArrayList<ToDoItem>toDoItems= new ArrayList<>() ;
    ToDoItem toDoItem = new ToDoItem() ;


    public void Get(){
        DashboardF.progressBar.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener (new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                toDoItems.clear();
                for (DataSnapshot list : dataSnapshot.getChildren()) {
                    toDoItem = list.getValue(ToDoItem.class);
                    toDoItem.setKey(list.getRef().getKey());
                    toDoItems.add(toDoItem);
                }
                DashboardF.adapter.notifyDataSetChanged();
                DashboardF.progressBar.setVisibility(View.INVISIBLE);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                DashboardF.progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

   public  void Post(ToDoItem toDoItem){
        databaseReference.push().setValue(toDoItem).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                }else {
                }
            }
        });
    }

    public void Remove(ArrayList<String> keys){
        for(  String key : keys ){
            databaseReference.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Adapter.checkedKeys.remove(key);
                        if(Adapter.checkedKeys.isEmpty()){
                            DashboardF.btn_remove.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            });
        }
    }

    public void Update(String key ,ToDoItem toDoItem ) {
        databaseReference.child(key).setValue(toDoItem).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                }
            }
        });
    }
}
