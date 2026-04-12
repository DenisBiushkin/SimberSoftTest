package com.example.simbersofttest.presentation.feature_main_calendar_list.model

sealed class CalendarListEvent {
    data class OnDaySelected(val day: Int) : CalendarListEvent()
    data class OnDateChanged(val timestamp: Long?) : CalendarListEvent()
    data class OnTaskClicked(val taskId: Int) : CalendarListEvent()
    object OnMonthYearHeaderClicked : CalendarListEvent()
    object OnDismissDatePicker : CalendarListEvent()
    object OnCreateTaskClicked : CalendarListEvent()
}