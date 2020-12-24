package com.example.todo_app.model.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todo_app.model.TodoDOA;
import com.example.todo_app.model.TodoModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {TodoModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final int THREAD_NUMBER = 4;
    public static final ExecutorService databaseExecutorService =
            Executors.newFixedThreadPool(THREAD_NUMBER);
    private static final String DATABASE_NAME = "user_db";
    private static AppDatabase INSTANCE;

    public static AppDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.
                            databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract TodoDOA todoDOA();

}
