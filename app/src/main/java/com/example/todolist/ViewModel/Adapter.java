package com.example.todolist.ViewModel;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Model.ToDoItem;
import com.example.todolist.R;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    ArrayList<ToDoItem> toDoItem ;

    public Adapter(ArrayList<ToDoItem> toDoItems){
        this.toDoItem = toDoItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.checkBox.setText(toDoItem.get(position).getName());
        String priority_id = toDoItem.get(position).getPriority();
        ColorStateList colorStateList = ColorStateList.valueOf(Color.rgb(92, 175, 82));

        if(toDoItem.get(position).getPriority().equals("1")){
            colorStateList = ColorStateList.valueOf(Color.rgb(34, 150, 243));
        }else if(toDoItem.get(position).getPriority().equals("2")){
            colorStateList = ColorStateList.valueOf(Color.rgb(233, 81, 68));
        }
holder.checkBox.setButtonTintList(colorStateList);

    }

    @Override
    public int getItemCount() {
        return toDoItem.size();
    }

}
