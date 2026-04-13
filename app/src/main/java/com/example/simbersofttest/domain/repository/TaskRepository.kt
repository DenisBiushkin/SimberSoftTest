package com.example.simbersofttest.domain.repository

import com.example.simbersofttest.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasksByDate(
        dateStartOfDay: Long,
        dateEndOfDay: Long
    ): Flow<List<Task>>

    suspend fun addTask(task: Task)
    suspend fun getTaskById(id: Int): Task?

    suspend fun deleteTaskById(id: Int): Unit
}