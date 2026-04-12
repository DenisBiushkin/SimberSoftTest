package com.example.simbersofttest.domain.usecases

import com.example.simbersofttest.domain.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(taskId: Int) {
        repository.deleteTaskById(taskId)
    }
}