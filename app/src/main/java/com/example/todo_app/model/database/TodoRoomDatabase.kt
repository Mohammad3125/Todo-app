package com.example.todo_app.model.database

import android.provider.ContactsContract
import androidx.room.Database
import com.example.todo_app.model.TodoModel
import com.example.todo_app.model.TodoModelDOA


@Database(entities = [TodoModel::class], version = 1, exportSchema = false)
abstract class Database {
    abstract fun todoDao() : TodoModelDOA

    companion object{
        private  var INSTANCE : Data



    }

}