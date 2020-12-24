package com.example.todo_app.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class TodoModel(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id : Int,
        @ColumnInfo(name = "todo_name")
        val name : String ,
        @ColumnInfo(name = "todo_body")
        val text : String
)
