package com.example.todo_app.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todo_app.model.TodoModel;
import com.example.todo_app.model.TodoModelDOA;
import com.example.todo_app.model.database.TodoRoomDatabase;

import java.util.List;

public class TodoRepository {

    public TodoModelDOA todoModelDOA;
    private final LiveData<List<TodoModel>> notes;

    public TodoRepository(Application application) {
        TodoRoomDatabase database = TodoRoomDatabase.Companion.getDatabase(application);
        todoModelDOA = database.todoDao();
        notes = todoModelDOA.getTodoList();
    }

    public LiveData<List<TodoModel>> getNotes() {
        return notes;
    }

    public void insert(TodoModel todo) {
        TodoRoomDatabase.Companion.getExecutorService().execute(
                () -> todoModelDOA.insert(todo)
        );
    }

    public void deleteAll() {
        todoModelDOA.deleteAll();
    }

    public void delete(int id) {
        TodoRoomDatabase.Companion.getExecutorService().execute(
                () -> todoModelDOA.delete(id)
        );
    }


}
