package com.example.todo_app.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoModelDOA {

    @Query("SELECT * FROM todo_table")
    fun getTodoList(): LiveData<List<TodoModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(todo: TodoModel)

    @Delete
    fun delete(todoId: Int)

    @Query("DELETE FROM todo_table")
    fun deleteAll()


}