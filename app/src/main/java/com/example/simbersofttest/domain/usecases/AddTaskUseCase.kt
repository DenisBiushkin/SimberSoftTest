package com.example.simbersofttest.domain.usecases

import com.example.simbersofttest.domain.repository.TaskRepository
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    fun execute(
        title: String,
        description: String,
        dateMillis: Long,
        hour: Int,
        category: String
    ) {

    }
}