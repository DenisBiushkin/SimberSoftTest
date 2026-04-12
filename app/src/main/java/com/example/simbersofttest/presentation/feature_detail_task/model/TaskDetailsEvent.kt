package com.example.simbersofttest.presentation.feature_detail_task.model

sealed class TaskDetailsEvent {
    object OnBackClicked : TaskDetailsEvent()
    object OnEditClicked : TaskDetailsEvent()
    object OnDeleteClicked : TaskDetailsEvent()
    object OnErrorDismissed : TaskDetailsEvent()
}