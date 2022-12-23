package com.example.todoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.local.repository.TaskRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class StatisticsViewModel(
    private val taskRepository: TaskRepository
): ViewModel() {

    private val _completePercent = MutableLiveData<Double>()
    private val _incompletePercent = MutableLiveData<Double>()
    val completePercent: LiveData<Double> get() = _completePercent
    val incompletePercent: LiveData<Double> get() = _incompletePercent

    fun getTasksStatistics() = Observable.combineLatest(
        taskRepository.getCompleteTasksCount(),
        taskRepository.getAllTasksCount()
    ) { completed, total ->
        Pair(completed, total)
    }
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(
        {
            val completed = it.first
            val total = it.second
            val incomplete = total - completed

            if (total == 0) {
                _completePercent.value = 0.0
                _incompletePercent.value = 0.0
            } else {
                _completePercent.value = completed.toDouble() / total * 100
                _incompletePercent.value = incomplete.toDouble() / total * 100
            }
        },
        {
            it.printStackTrace()
        }
    )
}