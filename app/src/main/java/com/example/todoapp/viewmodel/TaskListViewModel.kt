package com.example.todoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.local.repository.TaskRepository
import com.example.todoapp.model.Task
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskListViewModel(
    private val taskRepository: TaskRepository
): ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> get() = _tasks

    init {
        getTaskList3()
    }

    private fun getTaskList() = taskRepository
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

    private fun getTaskList2() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = taskRepository.getAllTasks2()
            withContext(Dispatchers.Main) {
                _tasks.value = list
            }
        }
    }

    private fun getTaskList25() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = async { taskRepository.getAllTasks2() }.await()
            withContext(Dispatchers.Main) {
                _tasks.value = list
            }
        }
    }

    private val _todoFlow = MutableStateFlow<List<Task>>(emptyList())
    val todoFlow get() = _todoFlow.asStateFlow()

    private fun getTaskList3() {
        viewModelScope.launch {
            taskRepository.getAllTasks3().collect {
                _todoFlow.value = it
            }
        }
    }

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