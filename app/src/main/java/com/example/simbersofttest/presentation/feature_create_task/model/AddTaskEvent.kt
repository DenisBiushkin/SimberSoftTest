package com.example.simbersofttest.presentation.feature_create_task.model

sealed class AddTaskEvent {
    data class OnTitleChanged(val title: String) : AddTaskEvent()
    data class OnDescChanged(val desc: String) : AddTaskEvent()
    data class OnHourSelected(val hour: Int) : AddTaskEvent()
    data class OnCategorySelected(val category: TaskCategoryUi) : AddTaskEvent()
    data class OnDateSelected(val millis: Long?) : AddTaskEvent()
    object ToggleDatePicker : AddTaskEvent()
    object OnSaveClick : AddTaskEvent()
    object OnCancelClick : AddTaskEvent()
}
