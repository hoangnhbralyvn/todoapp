package com.example.todoapp.data.local.repository

import com.example.todoapp.data.local.dao.TaskDao
import com.example.todoapp.model.Task

class TaskRepository(
    private val taskDao: TaskDao
) {

    fun getAllTasks() = taskDao.getAllTasks()

    fun createTask(task: Task) = taskDao.insertTask(task)

    fun updateTask(task: Task) = taskDao.updateTask(task)

    fun getCompleteTasksCount() = taskDao.getCompleteTasksCount()

    fun getAllTasksCount() = taskDao.getAllTasksCount()
}