package com.example.simbersofttest.domain.usecases

import com.example.simbersofttest.domain.model.Task
import com.example.simbersofttest.domain.repository.TaskRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import java.time.Instant
import java.time.ZoneId


//UseCase для получения задач на конкретный день
//логика расчета временных границ дня (00:00:00 - 23:59:59)
class GetTasksByDateUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    operator fun invoke(dateMillis: Long): Flow<List<Task>> {
        val localDate = Instant.ofEpochMilli(dateMillis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        val startOfDay = localDate.atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        val endOfDay = localDate.plusDays(1)
            .atStartOfDay(ZoneId.systemDefault())
            .minusNanos(1)
            .toInstant()
            .toEpochMilli()

        return repository.getTasksByDate(startOfDay, endOfDay)
    }
}