package com.example.todolist.ViewModel.API;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todolist.ViewModel.API.APIViewHolder;

import com.example.todolist.Model.ToDoItem;
import com.example.todolist.R;
import com.example.todolist.View.DashboardA;
import com.example.todolist.View.DashboardF;
import com.example.todolist.ViewModel.FirebaseConnection;

import java.util.ArrayList;

public class APIAdapter extends RecyclerView.Adapter<APIViewHolder> {

    ArrayList<ToDoItem> toDoItem ;
    public static ArrayList<String>checkedKeys = new ArrayList<>();
    DashboardA dashboardA = new DashboardA();
    FirebaseConnection firebaseConnection = new FirebaseConnection();

    public APIAdapter(ArrayList<ToDoItem> toDoItems ){
        this.toDoItem = toDoItems;
    }

    @NonNull
    @Override
    public APIViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent,false);
        APIViewHolder viewHolder = new APIViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull APIViewHolder holder, int position) {
        holder.checkBox.setText(toDoItem.get(position).getName());
        holder.checkBox.setChecked(false);
        holder.checkBox.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dashboardA.Update(toDoItem.get(holder.getAdapterPosition()).getKey());
                return true;
            }
        });

        ColorStateList colorStateList = ColorStateList.valueOf(Color.rgb(92, 175, 82));

        if(toDoItem.get(position).getPriority().equals("1")){
            colorStateList = ColorStateList.valueOf(Color.rgb(34, 150, 243));
        }else if(toDoItem.get(position).getPriority().equals("2")){
            colorStateList = ColorStateList.valueOf(Color.rgb(233, 81, 68));
        }
        holder.checkBox.setButtonTintList(colorStateList);


        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkedKeys.add(toDoItem.get(holder.getAdapterPosition()).getKey());
                }else{
                    checkedKeys.remove(toDoItem.get(holder.getAdapterPosition()).getKey()) ;
                }if(checkedKeys.isEmpty()){
                    DashboardA.btn_remove.setVisibility(View.INVISIBLE);
                }else{
                    DashboardA.btn_remove.setVisibility(View.VISIBLE);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return toDoItem.size();
    }

}
