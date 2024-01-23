package com.example.todoapp.data.local.repository

import com.example.todoapp.data.local.dao.TaskDao
import com.example.todoapp.model.Task

class TaskRepository(
    private val taskDao: TaskDao
) {

    fun getAllTasks() = taskDao.getAllTasks()

    suspend fun getAllTasks2() = taskDao.getAllTasks2()

    fun getAllTasks3() = taskDao.getAllTasks3()

    fun createTask(task: Task) = taskDao.insertTask(task)

    suspend fun createTask2(task: Task) = taskDao.insertTask2(task)

    fun updateTask(task: Task) = taskDao.updateTask(task)

    fun getCompleteTasksCount() = taskDao.getCompleteTasksCount()

    fun getAllTasksCount() = taskDao.getAllTasksCount()
}