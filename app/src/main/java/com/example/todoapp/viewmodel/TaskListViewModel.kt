package com.example.todoapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.local.repository.TaskRepository
import com.example.todoapp.model.Task
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class TaskListViewModel(
    private val taskRepository: TaskRepository
): ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> get() = _tasks

    fun getTaskList() = taskRepository
        .getAllTasks()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            {
                _tasks.value = it
            },
            {
                it.printStackTrace()
            }
        )

    fun updateTask(task: Task) = taskRepository
        .updateTask(task)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({},
            {
                it.printStackTrace()
            }
        )
}