package com.example.simbersofttest.presentation.feature_main_calendar_list.viewmodel


import androidx.lifecycle.ViewModel
import com.example.simbersofttest.presentation.feature_main_calendar_list.model.CalendarListVMState
import com.example.simbersofttest.сonstants.CalendarTestData
import com.example.simbersofttest.сonstants.TaskTestData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class CalendarListViewModel  @Inject constructor(

): ViewModel() {

    private val _state = MutableStateFlow(CalendarListVMState())
    val state: StateFlow<CalendarListVMState> = _state.asStateFlow()

    init {
        _state.value= _state.value.copy(
            monthYearTitle = "12 Апреля 2026",
            tasks = TaskTestData.list,
            calendarDaysUi = CalendarTestData.mockDays
        )
    }
}