package com.example.todo_app.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TodoDOA {

    @Query("SELECT * FROM todo_table")
    LiveData<List<TodoModel>> getTodoList();

    @Query("SELECT * FROM todo_table WHERE id =:itemId")
    TodoModel getTodo(int itemId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TodoModel todoModel);

    @Delete
    void delete(TodoModel todoModel);

    @Query("DELETE FROM todo_table")
    void deleteAll();


}
