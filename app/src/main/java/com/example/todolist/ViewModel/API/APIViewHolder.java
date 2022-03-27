package com.example.todolist.ViewModel.API;

import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;


public class APIViewHolder extends RecyclerView.ViewHolder {
    CheckBox checkBox;
    public APIViewHolder(@NonNull View itemView) {
        super(itemView);
        checkBox = itemView.findViewById(R.id.checkbox);
    }


}
