package com.example.simbersofttest.presentation.feature_create_task.model

sealed class AddTaskUiEffect {
    object NavigateBack : AddTaskUiEffect()
    data class ShowError(val message: String) : AddTaskUiEffect()
}