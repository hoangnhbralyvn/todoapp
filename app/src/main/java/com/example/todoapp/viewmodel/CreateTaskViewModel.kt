package com.example.todoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.SingleLiveEvent
import com.example.todoapp.data.local.repository.TaskRepository
import com.example.todoapp.model.Task
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.resume

class CreateTaskViewModel(
    private val taskRepository: TaskRepository
): ViewModel() {

    private val _isCreateSucceed = SingleLiveEvent<Boolean>()
    val isCreateSucceed: LiveData<Boolean?> get() = _isCreateSucceed

    //Hot flow: one time event
    private val _createSuccess = MutableSharedFlow<Boolean>()
    val createSuccess get() = _createSuccess.asSharedFlow()

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

    fun createNewTask2(task: Task) {
        viewModelScope.launch {
            val res = taskRepository.createTask2(task)
            _createSuccess.emit(res != 0L)
        }
    }
}