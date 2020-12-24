package com.example.todo_app.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class TodoModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "todo_name")
    public String name;
    @ColumnInfo(name = "todo_body")
    public String text;
    @ColumnInfo(name = "todo_time")
    public String time;

    public TodoModel(int id, String name, String text, String time) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.time = time;
    }
}
