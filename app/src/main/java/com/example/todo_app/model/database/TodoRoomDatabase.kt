package com.example.todo_app.model.database

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo_app.model.TodoModel
import com.example.todo_app.model.TodoModelDOA
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Database(entities = [TodoModel::class], version = 1, exportSchema = false)
abstract class TodoRoomDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoModelDOA

    companion object{
        @Volatile
        private  var INSTANCE : TodoRoomDatabase? = null

        const val NUMBER_OF_THREAD = 4

        val executorService : ExecutorService =
                Executors.newFixedThreadPool(NUMBER_OF_THREAD)

        fun getDatabase(context :Context) : TodoRoomDatabase{
            return INSTANCE ?: synchronized(this)
            {
                val instance = Room.
                databaseBuilder(context.applicationContext,TodoRoomDatabase::class.java,"todo-database")
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }

}