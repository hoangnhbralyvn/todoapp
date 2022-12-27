package com.example.todoapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.data.local.provideAppDb
import com.example.todoapp.data.local.repository.TaskRepository

class AppViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) = when(modelClass) {
        TaskListViewModel::class.java -> {
            val appDb = provideAppDb(context)
            val taskRepository = TaskRepository(appDb.taskDao())
            TaskListViewModel(taskRepository)
        }
        CreateTaskViewModel::class.java -> {
            val appDb = provideAppDb(context)
            val taskRepository = TaskRepository(appDb.taskDao())
            CreateTaskViewModel(taskRepository)
        }
        StatisticsViewModel::class.java -> {
            val appDb = provideAppDb(context)
            val taskRepository = TaskRepository(appDb.taskDao())
            StatisticsViewModel(taskRepository)
        }
        else -> throw IllegalStateException("Unknown ViewModel Class")
    } as T
}