package com.example.todo_app.ui.fragments.FragmentHomeScreenDirectory.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_app.R;
import com.example.todo_app.model.TodoModel;

public class TodoViewHolder extends RecyclerView.ViewHolder {
    TextView todoNameTextView;
    TextView todoDetailTextView;
    TextView todoTimeTextView;

    View view;
    ImageView deleteTodoIconImageView;


    public TodoViewHolder(@NonNull View itemView) {
        super(itemView);
        initView(itemView);
        view = itemView;
    }

    private void initView(View view) {
        todoNameTextView = view.findViewById(R.id.item_todo_name_text_view);
        todoDetailTextView = view.findViewById(R.id.item_todo_detail_text_view);
        todoTimeTextView = view.findViewById(R.id.item_todo_time_text_view);
        deleteTodoIconImageView = view.findViewById(R.id.item_todo_delete_icon);

    }

    public void bind(TodoModel todoModel, OnTodoItemClickListener onTodoItemClickListener, OnTodoItemDeleteListener onTodoItemDeleteListener) {
        todoNameTextView.setText(todoModel.name);
        todoDetailTextView.setText(todoModel.text);
        todoTimeTextView.setText(todoModel.time);

        deleteTodoIconImageView.setOnClickListener(v1 ->
                onTodoItemDeleteListener.onDelete(todoModel)
        );

        view.setOnClickListener(v -> {
            onTodoItemClickListener.onClick(todoModel.id, view);
        });
    }

}
