package com.example.simbersofttest.presentation.feature_detail_task.model

data class TaskDetailVMState(
    val name: String = "",
    val description: String = "",
    val formattedDate: String = "",
    val formattedTimeRange: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)