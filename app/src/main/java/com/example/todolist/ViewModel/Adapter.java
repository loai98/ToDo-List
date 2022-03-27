package com.example.todolist.ViewModel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
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


    }

    @Override
    public int getItemCount() {
        return toDoItem.size();
    }

}
