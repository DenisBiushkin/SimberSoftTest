package com.example.simbersofttest.domain.usecases

import com.example.simbersofttest.domain.model.Task
import com.example.simbersofttest.domain.model.TaskCategory
import com.example.simbersofttest.domain.repository.TaskRepository
import com.example.simbersofttest.presentation.feature_create_task.model.TaskCategoryUi
import java.util.Calendar
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend fun execute(
        title: String,
        description: String,
        dateMillis: Long,
        hour: Int,
        category: TaskCategory
    ) {
        // Устанавливаем точное время начала
        val calendar = Calendar.getInstance().apply {
            timeInMillis = dateMillis
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val startTime = calendar.timeInMillis

        // Устанавливаем время завершения (+1 час)
        calendar.add(Calendar.HOUR_OF_DAY, 1)
        val endTime = calendar.timeInMillis

        val newTask = Task(
            id=0,
            name = title,
            description = description,
            dateStart = startTime,
            dateFinish = endTime,
            category = category
        )

        repository.addTask(newTask)
    }
}