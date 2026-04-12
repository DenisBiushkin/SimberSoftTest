package com.example.simbersofttest.presentation.feature_detail_task.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simbersofttest.domain.repository.TaskRepository
import com.example.simbersofttest.domain.usecases.DeleteTaskUseCase
import com.example.simbersofttest.presentation.feature_detail_task.model.TaskDetailVMState
import com.example.simbersofttest.presentation.feature_detail_task.model.TaskDetailsEffect
import com.example.simbersofttest.presentation.feature_detail_task.model.TaskDetailsEvent
import com.example.simbersofttest.presentation.feature_main_calendar_list.model.CalendarListVMState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
//TODO добавить редактирование задачи
class DetailTaskViewModel @Inject constructor(
    private val repository: TaskRepository,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val taskId: Int = savedStateHandle.get<Int>("taskId") ?: -1
    private val _state = MutableStateFlow(TaskDetailVMState())
    val state: StateFlow<TaskDetailVMState> = _state.asStateFlow()

    private val _effect = Channel<TaskDetailsEffect>()
    val effect = _effect.receiveAsFlow()

    private val dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    init {
        loadTaskDetails()
    }

    fun onEvent(event: TaskDetailsEvent) {
        when (event) {
            is TaskDetailsEvent.OnDeleteClicked -> deleteTask()
            is TaskDetailsEvent.OnBackClicked -> {
                viewModelScope.launch { _effect.send(TaskDetailsEffect.NavigateBack) }
            }
            is TaskDetailsEvent.OnErrorDismissed -> {
                _state.update { it.copy(errorMessage = null) }
            }
            is TaskDetailsEvent.OnEditClicked -> {

            }
//            is TaskDetailsEvent.OnRetryClicked -> {
//                loadTaskDetails()
//            }
        }
    }

    private fun loadTaskDetails() {
        if (taskId == -1) {
            _state.update { it.copy(errorMessage = "Некорректный ID задачи") }
            return
        }
        //TODO спрятать всю логику в Usecase
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val task = repository.getTaskById(taskId)

                if (task != null) {
                    _state.update {
                        it.copy(
                            name = task.name,
                            description = task.description,
                            formattedDate = formatTimestamp(task.dateStart, dateFormatter),
                            formattedTimeRange = "${formatTimestamp(task.dateStart, timeFormatter)} - ${formatTimestamp(task.dateFinish, timeFormatter)}",
                            isLoading = false
                        )
                    }
                } else {
                    _state.update {
                        it.copy(isLoading = false, errorMessage = "Задача не найдена в базе данных")
                    }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(isLoading = false, errorMessage = "Ошибка при загрузке: ${e.localizedMessage}")
                }
            }
        }
    }

    private fun deleteTask() {
        viewModelScope.launch {
            try {
                // Предполагается, что deleteTaskUseCase тоже suspend
                deleteTaskUseCase(taskId)
                _effect.send(TaskDetailsEffect.NavigateBack)
            } catch (e: Exception) {
                _effect.send(TaskDetailsEffect.ShowError("Не удалось удалить задачу"))
            }
        }
    }


    //TODO сделать общий форматер для всех экранов
     //Форматирование времени
    private fun formatTimestamp(timestamp: Long, formatter: DateTimeFormatter): String {
        return try {
            Instant.ofEpochMilli(timestamp)
                .atZone(ZoneId.systemDefault())
                .format(formatter)
        } catch (e: Exception) {
            "--:--"
        }
    }
}