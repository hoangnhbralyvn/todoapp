package com.example.todoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.SingleLiveEvent
import com.example.todoapp.data.local.repository.TaskRepository
import com.example.todoapp.model.Task
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CreateTaskViewModel(
    private val taskRepository: TaskRepository
): ViewModel() {

    private val _isCreateSucceed = SingleLiveEvent<Boolean>()
    val isCreateSucceed: LiveData<Boolean?> get() = _isCreateSucceed

    fun createNewTask(task: Task) = taskRepository
        .createTask(task)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            {
                _isCreateSucceed.value = true
            },
            {
                it.printStackTrace()
            }
        )
}