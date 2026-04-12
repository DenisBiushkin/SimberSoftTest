package com.example.simbersofttest.domain.usecases

import com.example.simbersofttest.domain.model.Task
import com.example.simbersofttest.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskDetailsUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(taskId: Int): Task? {
        return repository.getTaskById(taskId)

    }
}