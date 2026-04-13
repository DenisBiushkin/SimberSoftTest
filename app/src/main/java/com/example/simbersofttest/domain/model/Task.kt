package com.example.simbersofttest.domain.model

data class Task(
    val id: Int,
    val dateStart: Long,
    val dateFinish: Long,
    val name: String,
    val description: String,
    val category: TaskCategory = TaskCategory.PERSONAL
)