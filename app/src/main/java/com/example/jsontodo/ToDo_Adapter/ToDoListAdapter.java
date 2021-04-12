package com.example.jsontodo.ToDo_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jsontodo.Model.ToDo;
import com.example.jsontodo.R;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;
import java.util.zip.Inflater;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder> {

    List<ToDo> toDoList;
    Context context;

    public ToDoListAdapter(List<ToDo> toDoList, Context context) {
        this.toDoList = toDoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_todo_list_item, parent,false);
        ToDoViewHolder viewHolder = new ToDoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        ToDo toDo = toDoList.get(position);
        holder.txtViewUserId.setText(String.valueOf(toDo.getUserId()));
        holder.checkbox.setChecked(toDo.isCompleted());
        holder.checkbox.setText(toDo.getTitle());
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }


    //-----------------------------------------------------------
    // View Holder Class
    //-----------------------------------------------------------
    public class ToDoViewHolder extends RecyclerView.ViewHolder{
        MaterialTextView txtViewUserId;
        MaterialCheckBox checkbox;


        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewUserId = itemView.findViewById(R.id.txtViewUserId);
            checkbox = itemView.findViewById(R.id.checkboxTitle);
        }
    }

}
