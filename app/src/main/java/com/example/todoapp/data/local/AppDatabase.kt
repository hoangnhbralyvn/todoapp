package com.example.todoapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.data.local.dao.TaskDao
import com.example.todoapp.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

fun provideAppDb(context: Context) = Room.databaseBuilder(
    context,
    AppDatabase::class.java,
    "todo"
).build()