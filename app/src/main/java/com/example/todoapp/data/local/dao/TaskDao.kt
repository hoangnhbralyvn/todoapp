package com.example.todoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.model.Task
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAllTasks(): Observable<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task): Single<Long>

    @Update
    fun updateTask(task: Task): Completable

    @Query("SELECT COUNT(id) FROM task WHERE checked = :checked")
    fun getCompleteTasksCount(checked: Boolean = true): Observable<Int>

    @Query("SELECT COUNT(id) FROM task")
    fun getAllTasksCount(): Observable<Int>
}