package com.example.todo_app.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todo_app.model.TodoDOA;
import com.example.todo_app.model.TodoModel;
import com.example.todo_app.model.database.AppDatabase;

import java.util.List;

public class TodoRepository {

    public TodoDOA todoDOA;
    private final LiveData<List<TodoModel>> notes;

    public TodoRepository(Application application) {
        AppDatabase database = AppDatabase.getINSTANCE(application);
        todoDOA = database.todoDOA();
        notes = todoDOA.getTodoList();
    }

    public LiveData<List<TodoModel>> getNotes() {
        return notes;
    }

    public void insert(TodoModel todo) {
        AppDatabase.databaseExecutorService.execute(
                () -> todoDOA.insert(todo)
        );
    }

    public void deleteAll() {
        todoDOA.deleteAll();
    }

    public void delete(TodoModel todoModel) {
        AppDatabase.databaseExecutorService.execute(
                () -> todoDOA.delete(todoModel)
        );
    }


}
