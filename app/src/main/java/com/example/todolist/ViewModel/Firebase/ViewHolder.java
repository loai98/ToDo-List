package com.example.todolist.ViewModel.Firebase;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todolist.R;


public class ViewHolder extends RecyclerView.ViewHolder {
    CheckBox checkBox;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        checkBox = itemView.findViewById(R.id.checkbox);
    }


}
