package com.example.simbersofttest.presentation.feature_main_calendar_list.model

import com.example.simbersofttest.domain.model.Task

data class CalendarListVMState (
    val monthYearTitle: String ="",
    val calendarDaysUi: List<CalendarDayUi> =emptyList(),
    val tasks: List<Task> =emptyList()
)