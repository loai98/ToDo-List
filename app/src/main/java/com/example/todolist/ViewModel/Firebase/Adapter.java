package com.example.todolist.ViewModel.Firebase;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todolist.Model.ToDoItem;
import com.example.todolist.R;
import com.example.todolist.View.DashboardF;
import com.example.todolist.ViewModel.FirebaseConnection;
import com.example.todolist.ViewModel.Firebase.ViewHolder;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {


    ArrayList<ToDoItem> toDoItem ;
   public static ArrayList<String>checkedKeys = new ArrayList<>();
   DashboardF dashboardF = new DashboardF();
   FirebaseConnection firebaseConnection = new FirebaseConnection();

    public Adapter(ArrayList<ToDoItem> toDoItems ){
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
        holder.checkBox.setChecked(false);
        holder.checkBox.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dashboardF.Update(toDoItem.get(holder.getAdapterPosition()).getKey());
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
                    DashboardF.btn_remove.setVisibility(View.INVISIBLE);
                }else{
                    DashboardF.btn_remove.setVisibility(View.VISIBLE);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return toDoItem.size();
    }

}
