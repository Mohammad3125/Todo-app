package com.example.todo_app.ui.fragments.FragmentHomeScreenDirectory.utils;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.todo_app.R;
import com.example.todo_app.model.TodoModel;

public class TodoRecyclerViewAdapter extends ListAdapter<TodoModel, TodoViewHolder> {

    OnTodoItemDeleteListener onTodoItemDeleteListener;
    OnTodoItemClickListener onTodoItemClickListener;

    public TodoRecyclerViewAdapter(@NonNull DiffUtil.ItemCallback<TodoModel> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_todo_recycler_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        holder.bind(getItem(position), onTodoItemClickListener, onTodoItemDeleteListener);
    }

    public void setOnTodoItemClickListener(OnTodoItemClickListener onTodoItemClickListener) {
        this.onTodoItemClickListener = onTodoItemClickListener;
    }

    public void setOnTodoItemDeleteListener(OnTodoItemDeleteListener onTodoItemDeleteListener) {
        this.onTodoItemDeleteListener = onTodoItemDeleteListener;
    }
}





