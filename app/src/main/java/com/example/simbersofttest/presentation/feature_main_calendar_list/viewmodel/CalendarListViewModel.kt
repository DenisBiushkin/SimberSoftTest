package com.example.simbersofttest.presentation.feature_main_calendar_list.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simbersofttest.presentation.feature_main_calendar_list.model.CalendarListEvent
import com.example.simbersofttest.presentation.feature_main_calendar_list.model.CalendarListVMState
import com.example.simbersofttest.presentation.feature_main_calendar_list.model.CalendarUiEffect
import com.example.simbersofttest.сonstants.CalendarTestData
import com.example.simbersofttest.сonstants.TaskTestData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CalendarListViewModel  @Inject constructor(

): ViewModel() {

    private val _state = MutableStateFlow(CalendarListVMState())
    val state: StateFlow<CalendarListVMState> = _state.asStateFlow()

    // Канал для навигации
    private val _effect = Channel<CalendarUiEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        _state.value= _state.value.copy(
            monthYearTitle = "12 Апреля 2026",
            tasks = TaskTestData.list,
            calendarDaysUi = CalendarTestData.mockDays
        )
    }

    fun onEvent(event: CalendarListEvent) {
        when (event) {
            //клик по числу с датой (Возле слова Ежедневник)
            is CalendarListEvent.OnMonthYearHeaderClicked -> {
                _state.update { it.copy(isDatePickerVisible = true) }
            }

            //закрываем DatePicker
            is CalendarListEvent.OnDismissDatePicker -> {
                _state.update { it.copy(isDatePickerVisible = false) }
            }

            is CalendarListEvent.OnDateChanged -> {
                //выбрали дату
                event.timestamp?.let { newTimestamp ->
                    updateDate(newTimestamp)
                }
                //закрываем DatePicker
                _state.update { it.copy(isDatePickerVisible = false) }
            }

            //Выбрали день недели
            is CalendarListEvent.OnDaySelected -> {
                selectDay(event.day)
            }

            //Подробности задачи  -> GO To other Screen
            is CalendarListEvent.OnTaskClicked -> {
                viewModelScope.launch {
                    _effect.send(CalendarUiEffect.NavigateToDetails(event.taskId))
                }
            }

            //создать задачу -> GO To other Screen
            is CalendarListEvent.OnCreateTaskClicked -> {
                viewModelScope.launch {
                    _effect.send(CalendarUiEffect.NavigateToCreateTask)
                }
            }
        }
    }

    private fun selectDay(dayNumber: Int) {
        val updatedDays = _state.value.calendarDaysUi.map { day ->
            day.copy(isSelected = day.dayNumber == dayNumber)
        }
        _state.update { it.copy(calendarDaysUi = updatedDays) }


        //TODO  repository.getTasksForDate(
    }

    private fun updateDate(timestamp: Long) {
        // TODO Логика обновления месяца, года и списка дней на основе timestamp
        _state.update { it.copy(
            selectedDateMillis = timestamp,
            monthYearTitle = "Декабрь 2024" //TODO форматирование через SimpleDateFormat
        ) }
    }

}