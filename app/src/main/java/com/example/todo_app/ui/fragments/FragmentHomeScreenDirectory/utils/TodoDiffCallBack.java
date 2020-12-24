package com.example.todo_app.ui.fragments.FragmentHomeScreenDirectory.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.todo_app.model.TodoModel;

public class TodoDiffCallBack extends DiffUtil.ItemCallback<TodoModel> {
    @Override
    public boolean areItemsTheSame(@NonNull TodoModel oldItem, @NonNull TodoModel newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull TodoModel oldItem, @NonNull TodoModel newItem) {
        return oldItem.id == newItem.id;
    }
}
