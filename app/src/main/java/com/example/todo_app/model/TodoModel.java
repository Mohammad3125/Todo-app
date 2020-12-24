package com.example.todo_app.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class TodoModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "todo_name")
    @NonNull
    public String name;
    @ColumnInfo(name = "todo_body")
    @NonNull
    public String text;
    @ColumnInfo(name = "todo_time")
    @NonNull
    public String time;

    public TodoModel(int id, @NonNull String name, @NonNull String text, @NonNull String time) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.time = time;
    }
}
