package com.example.todo_app.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todo_app.model.TodoModel;
import com.example.todo_app.repository.TodoRepository;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    private final LiveData<List<TodoModel>> notes;
    private final TodoRepository todoRepository;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        todoRepository = new TodoRepository(application);
        notes = todoRepository.getNotes();
    }

    public LiveData<List<TodoModel>> getNotes() {
        return notes;
    }

    public void insert(TodoModel todo) {
        todoRepository.insert(todo);
    }

    public void deleteAll() {
        todoRepository.deleteAll();
    }

    public void delete(TodoModel todoModel) {
        todoRepository.delete(todoModel);
    }

}
