package com.example.simbersofttest.presentation.feature_main_calendar_list.model


 //Одноразовые события для навигации
sealed class CalendarUiEffect {
    data class NavigateToDetails(val taskId: Int) : CalendarUiEffect()
    object NavigateToCreateTask : CalendarUiEffect()
}