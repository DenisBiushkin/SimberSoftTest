package com.example.simbersofttest.presentation.feature_create_task.model

data class AddTaskState(
    val title: String = "",
    val description: String = "",
    val selectedDateMillis: Long = System.currentTimeMillis(),
    val startHour: Int = 10,
    val category: TaskCategoryUi = TaskCategoryUi.WORK,
    val isDatePickerOpen: Boolean = false
)


