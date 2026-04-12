package com.example.simbersofttest.presentation.feature_main_calendar_list.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simbersofttest.domain.repository.TaskRepository
import com.example.simbersofttest.domain.usecases.GetTasksByDateUseCase
import com.example.simbersofttest.presentation.feature_main_calendar_list.model.CalendarDayUi
import com.example.simbersofttest.presentation.feature_main_calendar_list.model.CalendarListEvent
import com.example.simbersofttest.presentation.feature_main_calendar_list.model.CalendarListVMState
import com.example.simbersofttest.presentation.feature_main_calendar_list.model.CalendarUiEffect
import com.example.simbersofttest.сonstants.CalendarTestData
import com.example.simbersofttest.сonstants.TaskTestData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class CalendarListViewModel  @Inject constructor(
    private val getTasksByDateUseCase: GetTasksByDateUseCase,
    private val taskRepository: TaskRepository
): ViewModel() {

    private val _state = MutableStateFlow(CalendarListVMState())
    val state: StateFlow<CalendarListVMState> = _state.asStateFlow()

    // Канал для навигации
    private val _effect = Channel<CalendarUiEffect>()
    val effect = _effect.receiveAsFlow()

    private var tasksJob: Job? = null
    private val monthYearFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))

    init {
        //TODO убрать до релиза
        viewModelScope.launch {
            taskRepository.addTask( TaskTestData.list.first())
        }

        initializeDefaultState()
    }

    private fun initializeDefaultState() {
        val currentTimestamp = System.currentTimeMillis()
        updateDate(currentTimestamp)
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
        val currentDays = _state.value.calendarDaysUi
        val updatedDays = currentDays.map { it.copy(isSelected = it.dayNumber == dayNumber) }

        val selectedDate = Instant.ofEpochMilli(_state.value.selectedDateMillis)
            .atZone(ZoneId.systemDefault())
            .withDayOfMonth(dayNumber)
            .toInstant()
            .toEpochMilli()

        _state.update { it.copy(
            calendarDaysUi = updatedDays,
            selectedDateMillis = selectedDate
        ) }
        observeTasks(selectedDate)
    }

    private fun updateDate(timestamp: Long) {
        val localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate()
        _state.update { it.copy(
            selectedDateMillis = timestamp,
            monthYearTitle = localDate.format(monthYearFormatter),
            calendarDaysUi = generateDaysForMonth(localDate)
        ) }
        observeTasks(timestamp)
    }

    private fun observeTasks(timestamp: Long) {
        tasksJob?.cancel()//так то можно обойтись
        tasksJob = viewModelScope.launch {
            getTasksByDateUseCase(timestamp).collect { taskList ->
                _state.update { it.copy(tasks = taskList) }
            }
        }
    }

    private fun generateDaysForMonth(date: LocalDate): List<CalendarDayUi> {
        val firstDay = date.withDayOfMonth(1)
        return (1..date.lengthOfMonth()).map { i ->
            val current = firstDay.withDayOfMonth(i)
            CalendarDayUi(
                dayNumber = i,
                dayOfWeek = current.format(DateTimeFormatter.ofPattern("EE", Locale("ru"))).uppercase(),
                isSelected = i == date.dayOfMonth
            )
        }
    }

}