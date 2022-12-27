package com.example.todoapp.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "task")
class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    var checked: Boolean
) {
    fun invertedCopy() = Task(id, title, description, !checked)
}