package com.example.simbersofttest.сonstants

import com.example.simbersofttest.presentation.feature_main_calendar_list.model.CalendarDayUi

object CalendarTestData {
    val mockDays: List<CalendarDayUi> = listOf(
        CalendarDayUi(
            dayNumber = 12,
            dayOfWeek = "ПН",
            isSelected = false
        ),
        CalendarDayUi(
            dayNumber = 13,
            dayOfWeek = "ВТ",
            isSelected = true
        ),
        CalendarDayUi(
            dayNumber = 14,
            dayOfWeek = "СР",
            isSelected = true // Среда выбрана по умолчанию
        ),
        CalendarDayUi(
            dayNumber = 15,
            dayOfWeek = "ЧТ",
            isSelected = false
        ),
        CalendarDayUi(
            dayNumber = 16,
            dayOfWeek = "ПТ",
            isSelected = false
        ),
        CalendarDayUi(
            dayNumber = 17,
            dayOfWeek = "СБ",
            isSelected = false
        ),
        CalendarDayUi(
            dayNumber = 18,
            dayOfWeek = "ВС",
            isSelected = false
        )
    )


}