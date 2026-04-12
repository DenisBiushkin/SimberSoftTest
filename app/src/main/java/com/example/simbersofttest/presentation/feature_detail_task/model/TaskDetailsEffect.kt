package com.example.simbersofttest.presentation.feature_detail_task.model

sealed class TaskDetailsEffect {
    object NavigateBack : TaskDetailsEffect()
    data class ShowError(val message: String) : TaskDetailsEffect()
}